package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Lote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Productor;
import persistencia.exceptions.NonexistentEntityException;

public class ProductorJpaController implements Serializable {

    public ProductorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    //Apuntamos la EMF a la UNIDAD DE PERSISTENCIA QUE CREAMOS
    //Ingresamos en nombre de la Unidad de Persistencia en el "persistence.xml"
    public ProductorJpaController() {
        emf = Persistence.createEntityManagerFactory("RepositorioPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productor productor) {
        if (productor.getLotes() == null) {
            productor.setLotes(new ArrayList<Lote>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Lote> attachedLotes = new ArrayList<Lote>();
            for (Lote lotesLoteToAttach : productor.getLotes()) {
                lotesLoteToAttach = em.getReference(lotesLoteToAttach.getClass(), lotesLoteToAttach.getIdLote());
                attachedLotes.add(lotesLoteToAttach);
            }
            productor.setLotes(attachedLotes);
            em.persist(productor);
            for (Lote lotesLote : productor.getLotes()) {
                Productor oldProductorOfLotesLote = lotesLote.getProductor();
                lotesLote.setProductor(productor);
                lotesLote = em.merge(lotesLote);
                if (oldProductorOfLotesLote != null) {
                    oldProductorOfLotesLote.getLotes().remove(lotesLote);
                    oldProductorOfLotesLote = em.merge(oldProductorOfLotesLote);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productor productor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productor persistentProductor = em.find(Productor.class, productor.getLegajo());
            List<Lote> lotesOld = persistentProductor.getLotes();
            List<Lote> lotesNew = productor.getLotes();
            List<Lote> attachedLotesNew = new ArrayList<Lote>();
            for (Lote lotesNewLoteToAttach : lotesNew) {
                lotesNewLoteToAttach = em.getReference(lotesNewLoteToAttach.getClass(), lotesNewLoteToAttach.getIdLote());
                attachedLotesNew.add(lotesNewLoteToAttach);
            }
            lotesNew = attachedLotesNew;
            productor.setLotes(lotesNew);
            productor = em.merge(productor);
            for (Lote lotesOldLote : lotesOld) {
                if (!lotesNew.contains(lotesOldLote)) {
                    lotesOldLote.setProductor(null);
                    lotesOldLote = em.merge(lotesOldLote);
                }
            }
            for (Lote lotesNewLote : lotesNew) {
                if (!lotesOld.contains(lotesNewLote)) {
                    Productor oldProductorOfLotesNewLote = lotesNewLote.getProductor();
                    lotesNewLote.setProductor(productor);
                    lotesNewLote = em.merge(lotesNewLote);
                    if (oldProductorOfLotesNewLote != null && !oldProductorOfLotesNewLote.equals(productor)) {
                        oldProductorOfLotesNewLote.getLotes().remove(lotesNewLote);
                        oldProductorOfLotesNewLote = em.merge(oldProductorOfLotesNewLote);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = productor.getLegajo();
                if (findProductor(id) == null) {
                    throw new NonexistentEntityException("The productor with id " + id + " no longer exists.");
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
            Productor productor;
            try {
                productor = em.getReference(Productor.class, id);
                productor.getLegajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productor with id " + id + " no longer exists.", enfe);
            }
            List<Lote> lotes = productor.getLotes();
            for (Lote lotesLote : lotes) {
                lotesLote.setProductor(null);
                lotesLote = em.merge(lotesLote);
            }
            em.remove(productor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productor> findProductorEntities() {
        return findProductorEntities(true, -1, -1);
    }

    public List<Productor> findProductorEntities(int maxResults, int firstResult) {
        return findProductorEntities(false, maxResults, firstResult);
    }

    private List<Productor> findProductorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productor.class));
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

    public Productor findProductor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productor> rt = cq.from(Productor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
