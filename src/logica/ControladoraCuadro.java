package logica;

import java.util.List;
import modelo.Cuadro;
import modelo.Lote;
import persistencia.CuadroControladoraPersistencia;

public class ControladoraCuadro {
    
    CuadroControladoraPersistencia controladoraCuadro = new CuadroControladoraPersistencia();
    
    //Creamos una variable de tipo list para obtener todos los Cuadros
    private List <Cuadro> listaCuadros;
    
    //Metodo Listar Cuadros
    public List<Cuadro> listarCuadros() {
        listaCuadros = controladoraCuadro.listarCuadros(); 
        return listaCuadros;
    }

    //Metodo Alta de Cuadro
    public void altaCuadro(Lote lote, String nombre) {
        
        //Se crea una instancia de Cuadro
        Cuadro cuadro = new Cuadro();
        
        //Asignamos los valores al Cuadro, de la Vista
        cuadro.setLote(lote);
        cuadro.setNombreCuadro(nombre);
        
        //Llamamos al metodo de la persistencia para realizar la insercion
        controladoraCuadro.altaCuadro(cuadro);                
    }
    
    //Metodo Actualizar Cuadro
    public void actualizarCuadro(int idCuadro, Lote lote, String nombre) {
        
        //Se crea una instancia de Cuadro
        Cuadro cuadro = new Cuadro();
        
        //Asignamos los valores al Cuadro, de la Vista
        cuadro.setIdCuadro(idCuadro);
        cuadro.setLote(lote);
        cuadro.setNombreCuadro(nombre);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraCuadro.actualizarCuadro(cuadro);
    }
    
    //Metodo Eliminar Lote
    public void eliminarCuadro(int idCuadro, Lote lote, String nombre) {
        
         //Se crea una instancia de Cuadro
        Cuadro cuadro = new Cuadro();
        
       //Asignamos los valores al Cuadro, de la Vista
        cuadro.setIdCuadro(idCuadro);
        cuadro.setLote(lote);
        cuadro.setNombreCuadro(nombre);
        cuadro.setEstado(false);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraCuadro.actualizarCuadro(cuadro);
    }
   
    
}
