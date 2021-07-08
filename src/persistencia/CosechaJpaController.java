package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.DetalleCosecha;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Cosecha;
import persistencia.exceptions.NonexistentEntityException;

public class CosechaJpaController implements Serializable {

    public CosechaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    //Apuntamos la EMF a la UNIDAD DE PERSISTENCIA QUE CREAMOS
    //Ingresamos en nombre de la Unidad de Persistencia en el "persistence.xml"
    public CosechaJpaController() {
        emf = Persistence.createEntityManagerFactory("RepositorioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cosecha cosecha) {
        if (cosecha.getDetallesCosechas() == null) {
            cosecha.setDetallesCosechas(new ArrayList<DetalleCosecha>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleCosecha> attachedDetallesCosechas = new ArrayList<DetalleCosecha>();
            for (DetalleCosecha detallesCosechasDetalleCosechaToAttach : cosecha.getDetallesCosechas()) {
                detallesCosechasDetalleCosechaToAttach = em.getReference(detallesCosechasDetalleCosechaToAttach.getClass(), detallesCosechasDetalleCosechaToAttach.getIdDetalleCosecha());
                attachedDetallesCosechas.add(detallesCosechasDetalleCosechaToAttach);
            }
            cosecha.setDetallesCosechas(attachedDetallesCosechas);
            em.persist(cosecha);
            for (DetalleCosecha detallesCosechasDetalleCosecha : cosecha.getDetallesCosechas()) {
                Cosecha oldCosechaOfDetallesCosechasDetalleCosecha = detallesCosechasDetalleCosecha.getCosecha();
                detallesCosechasDetalleCosecha.setCosecha(cosecha);
                detallesCosechasDetalleCosecha = em.merge(detallesCosechasDetalleCosecha);
                if (oldCosechaOfDetallesCosechasDetalleCosecha != null) {
                    oldCosechaOfDetallesCosechasDetalleCosecha.getDetallesCosechas().remove(detallesCosechasDetalleCosecha);
                    oldCosechaOfDetallesCosechasDetalleCosecha = em.merge(oldCosechaOfDetallesCosechasDetalleCosecha);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void modificarKgsCosecha(Cosecha cosecha){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(cosecha);
            em.getTransaction().commit();
            em.close();
        } catch (Exception ex) {
            
        }
        
    }

    public void edit(Cosecha cosecha) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cosecha persistentCosecha = em.find(Cosecha.class, cosecha.getIdCosecha());
            List<DetalleCosecha> detallesCosechasOld = persistentCosecha.getDetallesCosechas();
            List<DetalleCosecha> detallesCosechasNew = cosecha.getDetallesCosechas();
            List<DetalleCosecha> attachedDetallesCosechasNew = new ArrayList<DetalleCosecha>();
            for (DetalleCosecha detallesCosechasNewDetalleCosechaToAttach : detallesCosechasNew) {
                detallesCosechasNewDetalleCosechaToAttach = em.getReference(detallesCosechasNewDetalleCosechaToAttach.getClass(), detallesCosechasNewDetalleCosechaToAttach.getIdDetalleCosecha());
                attachedDetallesCosechasNew.add(detallesCosechasNewDetalleCosechaToAttach);
            }
            detallesCosechasNew = attachedDetallesCosechasNew;
            cosecha.setDetallesCosechas(detallesCosechasNew);
            cosecha = em.merge(cosecha);
            for (DetalleCosecha detallesCosechasOldDetalleCosecha : detallesCosechasOld) {
                if (!detallesCosechasNew.contains(detallesCosechasOldDetalleCosecha)) {
                    detallesCosechasOldDetalleCosecha.setCosecha(null);
                    detallesCosechasOldDetalleCosecha = em.merge(detallesCosechasOldDetalleCosecha);
                }
            }
            for (DetalleCosecha detallesCosechasNewDetalleCosecha : detallesCosechasNew) {
                if (!detallesCosechasOld.contains(detallesCosechasNewDetalleCosecha)) {
                    Cosecha oldCosechaOfDetallesCosechasNewDetalleCosecha = detallesCosechasNewDetalleCosecha.getCosecha();
                    detallesCosechasNewDetalleCosecha.setCosecha(cosecha);
                    detallesCosechasNewDetalleCosecha = em.merge(detallesCosechasNewDetalleCosecha);
                    if (oldCosechaOfDetallesCosechasNewDetalleCosecha != null && !oldCosechaOfDetallesCosechasNewDetalleCosecha.equals(cosecha)) {
                        oldCosechaOfDetallesCosechasNewDetalleCosecha.getDetallesCosechas().remove(detallesCosechasNewDetalleCosecha);
                        oldCosechaOfDetallesCosechasNewDetalleCosecha = em.merge(oldCosechaOfDetallesCosechasNewDetalleCosecha);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cosecha.getIdCosecha();
                if (findCosecha(id) == null) {
                    throw new NonexistentEntityException("The cosecha with id " + id + " no longer exists.");
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
            Cosecha cosecha;
            try {
                cosecha = em.getReference(Cosecha.class, id);
                cosecha.getIdCosecha();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cosecha with id " + id + " no longer exists.", enfe);
            }
            List<DetalleCosecha> detallesCosechas = cosecha.getDetallesCosechas();
            for (DetalleCosecha detallesCosechasDetalleCosecha : detallesCosechas) {
                detallesCosechasDetalleCosecha.setCosecha(null);
                detallesCosechasDetalleCosecha = em.merge(detallesCosechasDetalleCosecha);
            }
            em.remove(cosecha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cosecha> findCosechaEntities() {
        return findCosechaEntities(true, -1, -1);
    }

    public List<Cosecha> findCosechaEntities(int maxResults, int firstResult) {
        return findCosechaEntities(false, maxResults, firstResult);
    }

    private List<Cosecha> findCosechaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cosecha.class));
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

    public Cosecha findCosecha(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cosecha.class, id);
        } finally {
            em.close();
        }
    }

    public int getCosechaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cosecha> rt = cq.from(Cosecha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
