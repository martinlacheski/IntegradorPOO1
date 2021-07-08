package controlador;

import java.util.List;
import modelo.Productor;
import persistencia.ProductorControladoraPersistencia;

public class ControladoraProductor {
    
    ProductorControladoraPersistencia controladoraProductor = new ProductorControladoraPersistencia();
    
    //Creamos una variable de tipo list para obtener todos los Productores
    private List <Productor> listaProductores;
    
    //Metodo Listar Productores
    public List<Productor> listarProductores() {
        listaProductores = controladoraProductor.listarProductores(); 
        return listaProductores;
    }

    //Metodo Alta de Productor
    public void altaProductor(String razonSocial, String cuit, String direccion, String telefono) {
        
        //Se crea una instancia de Productor
        Productor productor = new Productor();
        
        //Asignamos los valores al Productor, de la Vista
        productor.setRazonSocial(razonSocial);
        productor.setCuit(cuit);
        productor.setDireccion(direccion);
        productor.setTelefono(telefono);
        
        //Llamamos al metodo de la persistencia para realizar la insercion
        controladoraProductor.altaProductor(productor);                
    }
    
    //Metodo Actualizar Empleado
    public void actualizarProductor(int legajo, String razonSocial,String cuit, String direccion, String telefono) {
        
        //Se crea una instancia de Productor
        Productor productor = new Productor();
        
        //Asignamos los valores al Productor, de la Vista
        productor.setLegajo(legajo);
        productor.setRazonSocial(razonSocial);
        productor.setCuit(cuit);
        productor.setDireccion(direccion);
        productor.setTelefono(telefono);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraProductor.actualizarProductor(productor);
    }
    
    //Metodo Eliminar Productor
    public void eliminarProductor(int legajo, String razonSocial, String cuit, String direccion, String telefono) {
        
        //Se crea una instancia de Productor
        Productor productor = new Productor();
        
        //Asignamos los valores al Productor, de la Vista
        productor.setLegajo(legajo);
        productor.setRazonSocial(razonSocial);
        productor.setCuit(cuit);
        productor.setDireccion(direccion);
        productor.setTelefono(telefono);
        productor.setEstado(false);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraProductor.actualizarProductor(productor);
    }
   
    
}
