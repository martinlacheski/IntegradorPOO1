package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Cuadro;

public class CuadroControladoraPersistencia {
    
    CuadroJpaController jpaCuadro = new CuadroJpaController();
    
    //Metodo para listar los Cuadros actuales en la BD por estado TRUE y Lote seleccionado
    public List<Cuadro> listarCuadros() {
        List<Cuadro> listaCuadros;
        listaCuadros = jpaCuadro.findCuadroEntities();
        List<Cuadro> listaFiltrada = new ArrayList<Cuadro>();
        for (Cuadro cuadro : listaCuadros) {
            if (cuadro.getEstado() == true){
                listaFiltrada.add(cuadro);
            }
        }
        return listaFiltrada;
    }

    //Recibe el objeto Cuadro creado desde la Logica y se encarga de persistir
    public void altaCuadro(Cuadro cuadro) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD
            jpaCuadro.create(cuadro);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Cuadro desde la Logica y se encarga de persistir la actualizacion
    public void actualizarCuadro(Cuadro cuadro) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaCuadro.edit(cuadro);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Cuadro desde la Logica y se encarga de persistir la actualizacion
    public void eliminarCuadro(Cuadro cuadro) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaCuadro.edit(cuadro);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
