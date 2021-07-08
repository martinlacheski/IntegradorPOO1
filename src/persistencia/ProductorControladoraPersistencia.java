package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Productor;

public class ProductorControladoraPersistencia {
    
    ProductorJpaController jpaProductor = new ProductorJpaController();
    
    //Metodo para listar los Productores actuales en la BD por estado TRUE
    public List<Productor> listarProductores() {
        List<Productor> listaProductores;
        listaProductores = jpaProductor.findProductorEntities();
        List<Productor> listaFiltrada = new ArrayList<Productor>();
        for (Productor productor : listaProductores) {
            if (productor.getEstado() == true){
                listaFiltrada.add(productor);
            }
        }
        return listaFiltrada;
    }

    //Recibe el objeto Productor creado desde la Logica y se encarga de persistir
    public void altaProductor(Productor productor) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD
            jpaProductor.create(productor);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ProductorControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Productor desde la Logica y se encarga de persistir la actualizacion
    public void actualizarProductor(Productor productor) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaProductor.edit(productor);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ProductorControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Productor desde la Logica y se encarga de persistir la actualizacion
    public void eliminarProductor(Productor productor) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaProductor.edit(productor);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ProductorControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }    
}
