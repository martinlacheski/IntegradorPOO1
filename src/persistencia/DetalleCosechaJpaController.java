package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Empleado;
import modelo.Cuadro;
import modelo.Cosecha;
import modelo.DetalleCosecha;
import persistencia.exceptions.NonexistentEntityException;

public class DetalleCosechaJpaController implements Serializable {

    public DetalleCosechaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    //Apuntamos la EMF a la UNIDAD DE PERSISTENCIA QUE CREAMOS
    //Ingresamos en nombre de la Unidad de Persistencia en el "persistence.xml"
    public DetalleCosechaJpaController() {
        emf = Persistence.createEntityManagerFactory("RepositorioPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleCosecha detalleCosecha) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = detalleCosecha.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getLegajo());
                detalleCosecha.setEmpleado(empleado);
            }
            Cuadro cuadro = detalleCosecha.getCuadro();
            if (cuadro != null) {
                cuadro = em.getReference(cuadro.getClass(), cuadro.getIdCuadro());
                detalleCosecha.setCuadro(cuadro);
            }
            Cosecha cosecha = detalleCosecha.getCosecha();
            if (cosecha != null) {
                cosecha = em.getReference(cosecha.getClass(), cosecha.getIdCosecha());
                detalleCosecha.setCosecha(cosecha);
            }
            em.persist(detalleCosecha);
            if (empleado != null) {
                empleado.getDetallesCosechas().add(detalleCosecha);
                empleado = em.merge(empleado);
            }
            if (cuadro != null) {
                cuadro.getDetallesCosechas().add(detalleCosecha);
                cuadro = em.merge(cuadro);
            }
            if (cosecha != null) {
                cosecha.getDetallesCosechas().add(detalleCosecha);
                cosecha = em.merge(cosecha);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleCosecha detalleCosecha) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleCosecha persistentDetalleCosecha = em.find(DetalleCosecha.class, detalleCosecha.getIdDetalleCosecha());
            Empleado empleadoOld = persistentDetalleCosecha.getEmpleado();
            Empleado empleadoNew = detalleCosecha.getEmpleado();
            Cuadro cuadroOld = persistentDetalleCosecha.getCuadro();
            Cuadro cuadroNew = detalleCosecha.getCuadro();
            Cosecha cosechaOld = persistentDetalleCosecha.getCosecha();
            Cosecha cosechaNew = detalleCosecha.getCosecha();
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getLegajo());
                detalleCosecha.setEmpleado(empleadoNew);
            }
            if (cuadroNew != null) {
                cuadroNew = em.getReference(cuadroNew.getClass(), cuadroNew.getIdCuadro());
                detalleCosecha.setCuadro(cuadroNew);
            }
            if (cosechaNew != null) {
                cosechaNew = em.getReference(cosechaNew.getClass(), cosechaNew.getIdCosecha());
                detalleCosecha.setCosecha(cosechaNew);
            }
            detalleCosecha = em.merge(detalleCosecha);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getDetallesCosechas().remove(detalleCosecha);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getDetallesCosechas().add(detalleCosecha);
                empleadoNew = em.merge(empleadoNew);
            }
            if (cuadroOld != null && !cuadroOld.equals(cuadroNew)) {
                cuadroOld.getDetallesCosechas().remove(detalleCosecha);
                cuadroOld = em.merge(cuadroOld);
            }
            if (cuadroNew != null && !cuadroNew.equals(cuadroOld)) {
                cuadroNew.getDetallesCosechas().add(detalleCosecha);
                cuadroNew = em.merge(cuadroNew);
            }
            if (cosechaOld != null && !cosechaOld.equals(cosechaNew)) {
                cosechaOld.getDetallesCosechas().remove(detalleCosecha);
                cosechaOld = em.merge(cosechaOld);
            }
            if (cosechaNew != null && !cosechaNew.equals(cosechaOld)) {
                cosechaNew.getDetallesCosechas().add(detalleCosecha);
                cosechaNew = em.merge(cosechaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = detalleCosecha.getIdDetalleCosecha();
                if (findDetalleCosecha(id) == null) {
                    throw new NonexistentEntityException("The detalleCosecha with id " + id + " no longer exists.");
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
            DetalleCosecha detalleCosecha;
            try {
                detalleCosecha = em.getReference(DetalleCosecha.class, id);
                detalleCosecha.getIdDetalleCosecha();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleCosecha with id " + id + " no longer exists.", enfe);
            }
            Empleado empleado = detalleCosecha.getEmpleado();
            if (empleado != null) {
                empleado.getDetallesCosechas().remove(detalleCosecha);
                empleado = em.merge(empleado);
            }
            Cuadro cuadro = detalleCosecha.getCuadro();
            if (cuadro != null) {
                cuadro.getDetallesCosechas().remove(detalleCosecha);
                cuadro = em.merge(cuadro);
            }
            Cosecha cosecha = detalleCosecha.getCosecha();
            if (cosecha != null) {
                cosecha.getDetallesCosechas().remove(detalleCosecha);
                cosecha = em.merge(cosecha);
            }
            em.remove(detalleCosecha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleCosecha> findDetalleCosechaEntities() {
        return findDetalleCosechaEntities(true, -1, -1);
    }

    public List<DetalleCosecha> findDetalleCosechaEntities(int maxResults, int firstResult) {
        return findDetalleCosechaEntities(false, maxResults, firstResult);
    }

    private List<DetalleCosecha> findDetalleCosechaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleCosecha.class));
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

    public DetalleCosecha findDetalleCosecha(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleCosecha.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleCosechaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleCosecha> rt = cq.from(DetalleCosecha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
