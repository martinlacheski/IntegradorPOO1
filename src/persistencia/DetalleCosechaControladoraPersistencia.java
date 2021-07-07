package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.DetalleCosecha;

public class DetalleCosechaControladoraPersistencia {
    
    DetalleCosechaJpaController jpaDetalleCosecha = new DetalleCosechaJpaController();
    
    //Metodo para listar los DetalleCosecha actuales en la BD por estado TRUE y Lote seleccionado
    public List<DetalleCosecha> listarDetalleCosecha() {
        List<DetalleCosecha> listaDetalleCosecha;
        listaDetalleCosecha = jpaDetalleCosecha.findDetalleCosechaEntities();
        List<DetalleCosecha> listaFiltrada = new ArrayList<DetalleCosecha>();
        for (DetalleCosecha detalleCosecha : listaDetalleCosecha) {
            if (detalleCosecha.getEstado() == true){
                listaFiltrada.add(detalleCosecha);
            }
        }
        return listaFiltrada;
    }

    //Recibe el objeto DetalleCosecha creado desde la Logica y se encarga de persistir
    public void altaDetalleCosecha(DetalleCosecha detalleCosecha) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD
            jpaDetalleCosecha.create(detalleCosecha);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto DetalleCosecha desde la Logica y se encarga de persistir la actualizacion
    public void actualizarDetalleCosecha(DetalleCosecha detalleCosecha) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaDetalleCosecha.edit(detalleCosecha);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Cuadro desde la Logica y se encarga de persistir la actualizacion
    public void eliminarDetalleCosecha(DetalleCosecha detalleCosecha) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaDetalleCosecha.edit(detalleCosecha);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
