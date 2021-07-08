package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Cosecha;

public class CosechaControladoraPersistencia {
    
    CosechaJpaController jpaCosecha = new CosechaJpaController();
    
    //Metodo para listar las Cosechas actuales en la BD por estado TRUE
    public List<Cosecha> listarCosechas() {
        List<Cosecha> listaCosechas;
        listaCosechas = jpaCosecha.findCosechaEntities();
        List<Cosecha> listaFiltrada = new ArrayList<Cosecha>();
        for (Cosecha cosecha : listaCosechas) {
            if (cosecha.getEstado() == true){
                listaFiltrada.add(cosecha);
            }
        }
        return listaFiltrada;
    }

    //Recibe el objeto Cosecha creado desde la Logica y se encarga de persistir
    public void altaCosecha(Cosecha cosecha) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD
            jpaCosecha.create(cosecha);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Cosecha desde la Logica y se encarga de persistir la actualizacion
    public void actualizarCosecha(Cosecha cosecha) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaCosecha.edit(cosecha);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Cosecha desde la Logica y se encarga de persistir la actualizacion
    public void eliminarCosecha(Cosecha cosecha) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaCosecha.edit(cosecha);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void modificarKgsCosecha(Cosecha cosecha) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaCosecha.modificarKgsCosecha(cosecha);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
