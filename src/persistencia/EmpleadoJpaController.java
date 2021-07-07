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
import modelo.Empleado;
import persistencia.exceptions.NonexistentEntityException;

public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    //Apuntamos la EMF a la UNIDAD DE PERSISTENCIA QUE CREAMOS
    //Ingresamos en nombre de la Unidad de Persistencia en el "persistence.xml"
    public EmpleadoJpaController() {
        emf = Persistence.createEntityManagerFactory("RepositorioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getDetallesCosechas() == null) {
            empleado.setDetallesCosechas(new ArrayList<DetalleCosecha>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleCosecha> attachedDetallesCosechas = new ArrayList<DetalleCosecha>();
            for (DetalleCosecha detallesCosechasDetalleCosechaToAttach : empleado.getDetallesCosechas()) {
                detallesCosechasDetalleCosechaToAttach = em.getReference(detallesCosechasDetalleCosechaToAttach.getClass(), detallesCosechasDetalleCosechaToAttach.getIdDetalleCosecha());
                attachedDetallesCosechas.add(detallesCosechasDetalleCosechaToAttach);
            }
            empleado.setDetallesCosechas(attachedDetallesCosechas);
            em.persist(empleado);
            for (DetalleCosecha detallesCosechasDetalleCosecha : empleado.getDetallesCosechas()) {
                Empleado oldEmpleadoOfDetallesCosechasDetalleCosecha = detallesCosechasDetalleCosecha.getEmpleado();
                detallesCosechasDetalleCosecha.setEmpleado(empleado);
                detallesCosechasDetalleCosecha = em.merge(detallesCosechasDetalleCosecha);
                if (oldEmpleadoOfDetallesCosechasDetalleCosecha != null) {
                    oldEmpleadoOfDetallesCosechasDetalleCosecha.getDetallesCosechas().remove(detallesCosechasDetalleCosecha);
                    oldEmpleadoOfDetallesCosechasDetalleCosecha = em.merge(oldEmpleadoOfDetallesCosechasDetalleCosecha);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getLegajo());
            List<DetalleCosecha> detallesCosechasOld = persistentEmpleado.getDetallesCosechas();
            List<DetalleCosecha> detallesCosechasNew = empleado.getDetallesCosechas();
            List<DetalleCosecha> attachedDetallesCosechasNew = new ArrayList<DetalleCosecha>();
            for (DetalleCosecha detallesCosechasNewDetalleCosechaToAttach : detallesCosechasNew) {
                detallesCosechasNewDetalleCosechaToAttach = em.getReference(detallesCosechasNewDetalleCosechaToAttach.getClass(), detallesCosechasNewDetalleCosechaToAttach.getIdDetalleCosecha());
                attachedDetallesCosechasNew.add(detallesCosechasNewDetalleCosechaToAttach);
            }
            detallesCosechasNew = attachedDetallesCosechasNew;
            empleado.setDetallesCosechas(detallesCosechasNew);
            empleado = em.merge(empleado);
            for (DetalleCosecha detallesCosechasOldDetalleCosecha : detallesCosechasOld) {
                if (!detallesCosechasNew.contains(detallesCosechasOldDetalleCosecha)) {
                    detallesCosechasOldDetalleCosecha.setEmpleado(null);
                    detallesCosechasOldDetalleCosecha = em.merge(detallesCosechasOldDetalleCosecha);
                }
            }
            for (DetalleCosecha detallesCosechasNewDetalleCosecha : detallesCosechasNew) {
                if (!detallesCosechasOld.contains(detallesCosechasNewDetalleCosecha)) {
                    Empleado oldEmpleadoOfDetallesCosechasNewDetalleCosecha = detallesCosechasNewDetalleCosecha.getEmpleado();
                    detallesCosechasNewDetalleCosecha.setEmpleado(empleado);
                    detallesCosechasNewDetalleCosecha = em.merge(detallesCosechasNewDetalleCosecha);
                    if (oldEmpleadoOfDetallesCosechasNewDetalleCosecha != null && !oldEmpleadoOfDetallesCosechasNewDetalleCosecha.equals(empleado)) {
                        oldEmpleadoOfDetallesCosechasNewDetalleCosecha.getDetallesCosechas().remove(detallesCosechasNewDetalleCosecha);
                        oldEmpleadoOfDetallesCosechasNewDetalleCosecha = em.merge(oldEmpleadoOfDetallesCosechasNewDetalleCosecha);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleado.getLegajo();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getLegajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<DetalleCosecha> detallesCosechas = empleado.getDetallesCosechas();
            for (DetalleCosecha detallesCosechasDetalleCosecha : detallesCosechas) {
                detallesCosechasDetalleCosecha.setEmpleado(null);
                detallesCosechasDetalleCosecha = em.merge(detallesCosechasDetalleCosecha);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
