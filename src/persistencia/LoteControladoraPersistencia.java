package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Lote;

public class LoteControladoraPersistencia {
    
    LoteJpaController jpaLote = new LoteJpaController();
    
    //Metodo para listar los Lotes actuales en la BD por estado TRUE
    public List<Lote> listarLotes() {
        List<Lote> listaLotes;
        listaLotes = jpaLote.findLoteEntities();
        List<Lote> listaFiltrada = new ArrayList<Lote>();
        for (Lote lote : listaLotes) {
            if (lote.getEstado() == true){
                listaFiltrada.add(lote);
            }
        }
        return listaFiltrada;
    }

    //Recibe el objeto Lote creado desde la Logica y se encarga de persistir
    public void altaLote(Lote lote) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD
            jpaLote.create(lote);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoteControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Lote desde la Logica y se encarga de persistir la actualizacion
    public void actualizarLote(Lote lote) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaLote.edit(lote);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoteControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Lote desde la Logica y se encarga de persistir la actualizacion
    public void eliminarLote(Lote lote) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaLote.edit(lote);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoteControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}
