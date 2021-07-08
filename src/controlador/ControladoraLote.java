package controlador;

import java.util.List;
import modelo.Lote;
import modelo.Productor;
import persistencia.LoteControladoraPersistencia;

public class ControladoraLote {
    
    LoteControladoraPersistencia controladoraLote = new LoteControladoraPersistencia();
    
    //Creamos una variable de tipo list para obtener todos los Empleados
    private List <Lote> listaLotes;
    
    //Metodo Listar Empleados
    public List<Lote> listarLotes() {
        listaLotes = controladoraLote.listarLotes(); 
        return listaLotes;
    }

    //Metodo Alta de Productor
    public void altaLote(Productor productor, String nombre, String direccion) {
        
        //Se crea una instancia de Productor
        Lote lote = new Lote();
        
        //Asignamos los valores al Productor, de la Vista
        lote.setProductor(productor);
        lote.setNombreLote(nombre);
        lote.setDireccionLote(direccion);
        
        //Llamamos al metodo de la persistencia para realizar la insercion
        controladoraLote.altaLote(lote);                
    }
    
    //Metodo Actualizar Lote
    public void actualizarLote(int idLote, Productor productor, String nombre, 
            String direccion) {
        
        //Se crea una instancia de Lote
        Lote lote = new Lote();
        
        //Asignamos los valores al Lote, de la Vista
        lote.setIdLote(idLote);
        lote.setProductor(productor);
        lote.setNombreLote(nombre);
        lote.setDireccionLote(direccion);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraLote.actualizarLote(lote);
    }
    
    //Metodo Eliminar Lote
    public void eliminarLote(int idLote, Productor productor, String nombre, 
            String direccion) {
        
         //Se crea una instancia de Lote
        Lote lote = new Lote();
        
       //Asignamos los valores al Lote, de la Vista
        lote.setIdLote(idLote);
        lote.setProductor(productor);
        lote.setNombreLote(nombre);
        lote.setDireccionLote(direccion);
        lote.setEstado(false);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraLote.actualizarLote(lote);
    }
   
    
}
