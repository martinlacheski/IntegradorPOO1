package controlador;

import java.time.LocalDate;
import java.util.List;
import modelo.Empleado;
import persistencia.EmpleadoControladoraPersistencia;

public class ControladoraEmpleado {
    
    EmpleadoControladoraPersistencia controladoraEmpleado = new EmpleadoControladoraPersistencia();
    
    //Creamos una variable de tipo list para obtener todos los Empleados
    private List <Empleado> listaEmpleados;
    
    //Metodo Listar Empleados
    public List<Empleado> listarEmpleados() {
        listaEmpleados = controladoraEmpleado.listarEmpleados(); 
        return listaEmpleados;
    }

    //Metodo Alta de Empleado
    public void altaEmpleado(String apellido, String nombres, String cuil, String direccion, String telefono, 
            LocalDate nacimiento, LocalDate ingreso) {
        
        //Se crea una instancia de Empleado
        Empleado empleado = new Empleado();
        
        //Asignamos los valores al Empleado, de la Vista
        empleado.setApellido(apellido);
        empleado.setNombres(nombres);
        empleado.setCuil(cuil);
        empleado.setDireccion(direccion);
        empleado.setTelefono(telefono);
        empleado.setFechaNacimiento(nacimiento);
        empleado.setFechaIngreso(ingreso);
        
        //Llamamos al metodo de la persistencia para realizar la insercion
        controladoraEmpleado.altaEmpleado(empleado);                
    }
    
    //Metodo Actualizar Empleado
    public void actualizarEmpleado(int legajo, String apellido, String nombres, String cuil, String direccion, String telefono, 
            LocalDate nacimiento, LocalDate ingreso) {
        
        //Se crea una instancia de Empleado
        Empleado empleado = new Empleado();
        
        //Asignamos los valores al Empleado, de la Vista
        empleado.setLegajo(legajo);
        empleado.setApellido(apellido);
        empleado.setNombres(nombres);
        empleado.setCuil(cuil);
        empleado.setDireccion(direccion);
        empleado.setTelefono(telefono);
        empleado.setFechaNacimiento(nacimiento);
        empleado.setFechaIngreso(ingreso);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraEmpleado.actualizarEmpleado(empleado);
    }
    
    //Metodo Eliminar Empleado
    public void eliminarEmpleado(int legajo, String apellido, String nombres, String cuil, String direccion, String telefono, 
            LocalDate nacimiento, LocalDate ingreso) {
        
        //Se crea una instancia de Empleado
        Empleado empleado = new Empleado();
        
        //Asignamos los valores al Empleado, de la Vista
        empleado.setLegajo(legajo);
        empleado.setApellido(apellido);
        empleado.setNombres(nombres);
        empleado.setCuil(cuil);
        empleado.setDireccion(direccion);
        empleado.setTelefono(telefono);
        empleado.setFechaNacimiento(nacimiento);
        empleado.setFechaIngreso(ingreso);
        empleado.setEstado(false);
        
        //Llamamos al metodo de la persistencia para realizar la actualizacion
        controladoraEmpleado.actualizarEmpleado(empleado);
    } 
}
