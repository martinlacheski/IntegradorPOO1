package controlador;

import java.time.LocalDate;
import java.util.List;
import modelo.Cosecha;
import modelo.Lote;
import persistencia.CosechaControladoraPersistencia;

public class ControladoraCosecha{
    
    CosechaControladoraPersistencia controladoraCosecha = new CosechaControladoraPersistencia();
    
    //Creamos una variable de tipo list para obtener todos las Cosechas
    private List <Cosecha> listaCosechas;
    
    //Metodo Listar Cosechas
    public List<Cosecha> listarCosechas() {
        listaCosechas = controladoraCosecha.listarCosechas(); 
        return listaCosechas;
    }

    //Metodo Alta de Cosecha
    public void altaCosecha(LocalDate fechaCosecha, Lote lote, double kgsCampo, double kgsSecadero, double kgsCosecha) {
        
        //Se crea una instancia de Cosecha
        Cosecha cosecha = new Cosecha();
        
        //Asignamos los valores al Cosecha, de la Vista
        cosecha.setFechaCosecha(fechaCosecha);
        cosecha.setLote(lote);
        cosecha.setKgsCampo(kgsCampo);
        cosecha.setKgsSecadero(kgsSecadero);
        cosecha.setKgsCosecha(kgsCosecha);
        
        //Llamamos al metodo de la persistencia para realizar la insercion
        controladoraCosecha.altaCosecha(cosecha);                
    }
    
    
    
    //Metodo Actualizar Cosecha
    public void actualizarCosecha(int idCosecha, LocalDate fecha, Lote lote, double kgsCampo, double kgsSecadero, double kgsCosecha) {
        
        //Se crea una instancia de Cosecha
        Cosecha cosecha = new Cosecha();
        
        //Asignamos los valores al Cosecha, de la Vista
        cosecha.setIdCosecha(idCosecha);
        cosecha.setFechaCosecha(fecha);
        cosecha.setLote(lote);
        cosecha.setKgsCampo(kgsCampo);
        cosecha.setKgsSecadero(kgsSecadero);
        cosecha.setKgsCosecha(kgsCosecha);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraCosecha.actualizarCosecha(cosecha);
    }
    
    //Metodo Eliminar Cosecha
    public void eliminarCosecha(int idCosecha, LocalDate fecha, Lote lote, double kgsCampo, double kgsSecadero, double kgsCosecha) {
        
         //Se crea una instancia de Cosecha
        Cosecha cosecha = new Cosecha();
        
       //Asignamos los valores al Cuadro, de la Vista
        cosecha.setIdCosecha(idCosecha);
        cosecha.setFechaCosecha(fecha);
        cosecha.setLote(lote);
        cosecha.setKgsCampo(kgsCampo);
        cosecha.setKgsSecadero(kgsSecadero);
        cosecha.setKgsCosecha(kgsCosecha);
        cosecha.setEstado(false);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraCosecha.actualizarCosecha(cosecha);
    }
    
    //Metodo Actualizar Cosecha
    public void actualizarKgsCosecha(int idCosecha, LocalDate fecha, Lote lote, double kgsCampo, double kgsSecadero, double kgsCosecha) {
        
        //Se crea una instancia de Cosecha
        Cosecha cosecha = new Cosecha();
        
        //Asignamos los valores al Cosecha, de la Vista
        cosecha.setIdCosecha(idCosecha);
        cosecha.setFechaCosecha(fecha);
        cosecha.setLote(lote);
        cosecha.setKgsCampo(kgsCampo);
        cosecha.setKgsSecadero(kgsSecadero);
        cosecha.setKgsCosecha(kgsCosecha);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraCosecha.modificarKgsCosecha(cosecha);
    }
   
    
}
