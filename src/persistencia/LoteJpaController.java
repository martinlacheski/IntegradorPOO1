package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Productor;
import modelo.Cuadro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Lote;
import persistencia.exceptions.NonexistentEntityException;

public class LoteJpaController implements Serializable {

    public LoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public LoteJpaController() {
        emf = Persistence.createEntityManagerFactory("RepositorioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lote lote) {
        if (lote.getCuadros() == null) {
            lote.setCuadros(new ArrayList<Cuadro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productor productor = lote.getProductor();
            if (productor != null) {
                productor = em.getReference(productor.getClass(), productor.getLegajo());
                lote.setProductor(productor);
            }
            List<Cuadro> attachedCuadros = new ArrayList<Cuadro>();
            for (Cuadro cuadrosCuadroToAttach : lote.getCuadros()) {
                cuadrosCuadroToAttach = em.getReference(cuadrosCuadroToAttach.getClass(), cuadrosCuadroToAttach.getIdCuadro());
                attachedCuadros.add(cuadrosCuadroToAttach);
            }
            lote.setCuadros(attachedCuadros);
            em.persist(lote);
            if (productor != null) {
                productor.getLotes().add(lote);
                productor = em.merge(productor);
            }
            for (Cuadro cuadrosCuadro : lote.getCuadros()) {
                Lote oldLoteOfCuadrosCuadro = cuadrosCuadro.getLote();
                cuadrosCuadro.setLote(lote);
                cuadrosCuadro = em.merge(cuadrosCuadro);
                if (oldLoteOfCuadrosCuadro != null) {
                    oldLoteOfCuadrosCuadro.getCuadros().remove(cuadrosCuadro);
                    oldLoteOfCuadrosCuadro = em.merge(oldLoteOfCuadrosCuadro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lote lote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote persistentLote = em.find(Lote.class, lote.getIdLote());
            Productor productorOld = persistentLote.getProductor();
            Productor productorNew = lote.getProductor();
            List<Cuadro> cuadrosOld = persistentLote.getCuadros();
            List<Cuadro> cuadrosNew = lote.getCuadros();
            if (productorNew != null) {
                productorNew = em.getReference(productorNew.getClass(), productorNew.getLegajo());
                lote.setProductor(productorNew);
            }
            List<Cuadro> attachedCuadrosNew = new ArrayList<Cuadro>();
            for (Cuadro cuadrosNewCuadroToAttach : cuadrosNew) {
                cuadrosNewCuadroToAttach = em.getReference(cuadrosNewCuadroToAttach.getClass(), cuadrosNewCuadroToAttach.getIdCuadro());
                attachedCuadrosNew.add(cuadrosNewCuadroToAttach);
            }
            cuadrosNew = attachedCuadrosNew;
            lote.setCuadros(cuadrosNew);
            lote = em.merge(lote);
            if (productorOld != null && !productorOld.equals(productorNew)) {
                productorOld.getLotes().remove(lote);
                productorOld = em.merge(productorOld);
            }
            if (productorNew != null && !productorNew.equals(productorOld)) {
                productorNew.getLotes().add(lote);
                productorNew = em.merge(productorNew);
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = lote.getIdLote();
                if (findLote(id) == null) {
                    throw new NonexistentEntityException("The lote with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote lote;
            try {
                lote = em.getReference(Lote.class, id);
                lote.getIdLote();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lote with id " + id + " no longer exists.", enfe);
            }
            Productor productor = lote.getProductor();
            if (productor != null) {
                productor.getLotes().remove(lote);
                productor = em.merge(productor);
            }
            List<Cuadro> cuadros = lote.getCuadros();
            for (Cuadro cuadrosCuadro : cuadros) {
                cuadrosCuadro.setLote(null);
                cuadrosCuadro = em.merge(cuadrosCuadro);
            }
            em.remove(lote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lote> findLoteEntities() {
        return findLoteEntities(true, -1, -1);
    }

    public List<Lote> findLoteEntities(int maxResults, int firstResult) {
        return findLoteEntities(false, maxResults, firstResult);
    }

    private List<Lote> findLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lote.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Lote findLote(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lote.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lote> rt = cq.from(Lote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
