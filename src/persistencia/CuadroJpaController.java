package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Lote;
import modelo.DetalleCosecha;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Cuadro;
import persistencia.exceptions.NonexistentEntityException;

public class CuadroJpaController implements Serializable {

    public CuadroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    //Apuntamos la EMF a la UNIDAD DE PERSISTENCIA QUE CREAMOS
    //Ingresamos en nombre de la Unidad de Persistencia en el "persistence.xml"
    public CuadroJpaController() {
        emf = Persistence.createEntityManagerFactory("RepositorioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuadro cuadro) {
        if (cuadro.getDetallesCosechas() == null) {
            cuadro.setDetallesCosechas(new ArrayList<DetalleCosecha>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote lote = cuadro.getLote();
            if (lote != null) {
                lote = em.getReference(lote.getClass(), lote.getIdLote());
                cuadro.setLote(lote);
            }
            List<DetalleCosecha> attachedDetallesCosechas = new ArrayList<DetalleCosecha>();
            for (DetalleCosecha detallesCosechasDetalleCosechaToAttach : cuadro.getDetallesCosechas()) {
                detallesCosechasDetalleCosechaToAttach = em.getReference(detallesCosechasDetalleCosechaToAttach.getClass(), detallesCosechasDetalleCosechaToAttach.getIdDetalleCosecha());
                attachedDetallesCosechas.add(detallesCosechasDetalleCosechaToAttach);
            }
            cuadro.setDetallesCosechas(attachedDetallesCosechas);
            em.persist(cuadro);
            if (lote != null) {
                lote.getCuadros().add(cuadro);
                lote = em.merge(lote);
            }
            for (DetalleCosecha detallesCosechasDetalleCosecha : cuadro.getDetallesCosechas()) {
                Cuadro oldCuadroOfDetallesCosechasDetalleCosecha = detallesCosechasDetalleCosecha.getCuadro();
                detallesCosechasDetalleCosecha.setCuadro(cuadro);
                detallesCosechasDetalleCosecha = em.merge(detallesCosechasDetalleCosecha);
                if (oldCuadroOfDetallesCosechasDetalleCosecha != null) {
                    oldCuadroOfDetallesCosechasDetalleCosecha.getDetallesCosechas().remove(detallesCosechasDetalleCosecha);
                    oldCuadroOfDetallesCosechasDetalleCosecha = em.merge(oldCuadroOfDetallesCosechasDetalleCosecha);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuadro cuadro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadro persistentCuadro = em.find(Cuadro.class, cuadro.getIdCuadro());
            Lote loteOld = persistentCuadro.getLote();
            Lote loteNew = cuadro.getLote();
            List<DetalleCosecha> detallesCosechasOld = persistentCuadro.getDetallesCosechas();
            List<DetalleCosecha> detallesCosechasNew = cuadro.getDetallesCosechas();
            if (loteNew != null) {
                loteNew = em.getReference(loteNew.getClass(), loteNew.getIdLote());
                cuadro.setLote(loteNew);
            }
            List<DetalleCosecha> attachedDetallesCosechasNew = new ArrayList<DetalleCosecha>();
            for (DetalleCosecha detallesCosechasNewDetalleCosechaToAttach : detallesCosechasNew) {
                detallesCosechasNewDetalleCosechaToAttach = em.getReference(detallesCosechasNewDetalleCosechaToAttach.getClass(), detallesCosechasNewDetalleCosechaToAttach.getIdDetalleCosecha());
                attachedDetallesCosechasNew.add(detallesCosechasNewDetalleCosechaToAttach);
            }
            detallesCosechasNew = attachedDetallesCosechasNew;
            cuadro.setDetallesCosechas(detallesCosechasNew);
            cuadro = em.merge(cuadro);
            if (loteOld != null && !loteOld.equals(loteNew)) {
                loteOld.getCuadros().remove(cuadro);
                loteOld = em.merge(loteOld);
            }
            if (loteNew != null && !loteNew.equals(loteOld)) {
                loteNew.getCuadros().add(cuadro);
                loteNew = em.merge(loteNew);
            }
            for (DetalleCosecha detallesCosechasOldDetalleCosecha : detallesCosechasOld) {
                if (!detallesCosechasNew.contains(detallesCosechasOldDetalleCosecha)) {
                    detallesCosechasOldDetalleCosecha.setCuadro(null);
                    detallesCosechasOldDetalleCosecha = em.merge(detallesCosechasOldDetalleCosecha);
                }
            }
            for (DetalleCosecha detallesCosechasNewDetalleCosecha : detallesCosechasNew) {
                if (!detallesCosechasOld.contains(detallesCosechasNewDetalleCosecha)) {
                    Cuadro oldCuadroOfDetallesCosechasNewDetalleCosecha = detallesCosechasNewDetalleCosecha.getCuadro();
                    detallesCosechasNewDetalleCosecha.setCuadro(cuadro);
                    detallesCosechasNewDetalleCosecha = em.merge(detallesCosechasNewDetalleCosecha);
                    if (oldCuadroOfDetallesCosechasNewDetalleCosecha != null && !oldCuadroOfDetallesCosechasNewDetalleCosecha.equals(cuadro)) {
                        oldCuadroOfDetallesCosechasNewDetalleCosecha.getDetallesCosechas().remove(detallesCosechasNewDetalleCosecha);
                        oldCuadroOfDetallesCosechasNewDetalleCosecha = em.merge(oldCuadroOfDetallesCosechasNewDetalleCosecha);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cuadro.getIdCuadro();
                if (findCuadro(id) == null) {
                    throw new NonexistentEntityException("The cuadro with id " + id + " no longer exists.");
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
            Cuadro cuadro;
            try {
                cuadro = em.getReference(Cuadro.class, id);
                cuadro.getIdCuadro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuadro with id " + id + " no longer exists.", enfe);
            }
            Lote lote = cuadro.getLote();
            if (lote != null) {
                lote.getCuadros().remove(cuadro);
                lote = em.merge(lote);
            }
            List<DetalleCosecha> detallesCosechas = cuadro.getDetallesCosechas();
            for (DetalleCosecha detallesCosechasDetalleCosecha : detallesCosechas) {
                detallesCosechasDetalleCosecha.setCuadro(null);
                detallesCosechasDetalleCosecha = em.merge(detallesCosechasDetalleCosecha);
            }
            em.remove(cuadro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuadro> findCuadroEntities() {
        return findCuadroEntities(true, -1, -1);
    }

    public List<Cuadro> findCuadroEntities(int maxResults, int firstResult) {
        return findCuadroEntities(false, maxResults, firstResult);
    }

    private List<Cuadro> findCuadroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuadro.class));
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

    public Cuadro findCuadro(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuadro.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuadroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuadro> rt = cq.from(Cuadro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
