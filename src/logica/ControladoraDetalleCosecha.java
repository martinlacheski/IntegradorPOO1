package logica;

import java.util.List;
import modelo.Cosecha;
import modelo.DetalleCosecha;
import modelo.Cuadro;
import modelo.Empleado;
import persistencia.DetalleCosechaControladoraPersistencia;

public class ControladoraDetalleCosecha {
    
    DetalleCosechaControladoraPersistencia controladoraDetalleCosecha = new 
        DetalleCosechaControladoraPersistencia();
    
    //Creamos una variable de tipo list para obtener todos los DetalleCosechas
    private List <DetalleCosecha> listaDetalleCosecha;
    
    //Metodo Listar DetalleCosecha
    public List<DetalleCosecha> listarDetalleCosecha() {
        listaDetalleCosecha = controladoraDetalleCosecha.listarDetalleCosecha(); 
        return listaDetalleCosecha;
    }

    //Metodo Alta de DetalleCosecha
    public void altaDetalleCosecha(Cosecha cosecha, Cuadro cuadro, 
            Empleado empleado, double kilos) {
        
        //Se crea una instancia de DetalleCosecha
        DetalleCosecha detalleCosecha = new DetalleCosecha();
        
        //Asignamos los valores al DetalleCosecha, de la Vista
        detalleCosecha.setCosecha(cosecha);
        detalleCosecha.setCuadro(cuadro);
        detalleCosecha.setEmpleado(empleado);
        detalleCosecha.setKgsEmpleado(kilos);
        
        //Llamamos al metodo de la persistencia para realizar la insercion
        controladoraDetalleCosecha.altaDetalleCosecha(detalleCosecha);                
    }
    
    //Metodo Actualizar DetalleCosecha
    public void actualizarDetalleCosecha(int idDetalle, Cosecha cosecha, Cuadro cuadro, 
            Empleado empleado, double kilos) {
        
        //Se crea una instancia de DetalleCosecha
        DetalleCosecha detalleCosecha = new DetalleCosecha();
        
        //Asignamos los valores al DetalleCosecha, de la Vista
        detalleCosecha.setIdDetalleCosecha(idDetalle);
        detalleCosecha.setCosecha(cosecha);
        detalleCosecha.setCuadro(cuadro);
        detalleCosecha.setEmpleado(empleado);
        detalleCosecha.setKgsEmpleado(kilos);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraDetalleCosecha.actualizarDetalleCosecha(detalleCosecha);
    }
    
    //Metodo Eliminar DetalleCosecha
    public void eliminarDetalleCosecha(int idDetalle, Cosecha cosecha, 
            Cuadro cuadro, Empleado empleado, double kilos) {
        
        //Se crea una instancia de DetalleCosecha
        DetalleCosecha detalleCosecha = new DetalleCosecha();
        
       //Asignamos los valores al DetalleCosecha, de la Vista
        detalleCosecha.setIdDetalleCosecha(idDetalle);
        detalleCosecha.setCosecha(cosecha);
        detalleCosecha.setCuadro(cuadro);
        detalleCosecha.setEmpleado(empleado);
        detalleCosecha.setKgsEmpleado(kilos);
        detalleCosecha.setEstado(false);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraDetalleCosecha.actualizarDetalleCosecha(detalleCosecha);
    }  
}
