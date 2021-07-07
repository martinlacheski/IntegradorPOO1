package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoControladoraPersistencia {

    EmpleadoJpaController jpaEmpleado = new EmpleadoJpaController();
    
    //Metodo para listar los empleados actuales en la BD por estado TRUE
    public List<Empleado> listarEmpleados() {
        List<Empleado> listaEmpleados;
        listaEmpleados = jpaEmpleado.findEmpleadoEntities();
        List<Empleado> listaFiltrada = new ArrayList<Empleado>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getEstado() == true){
                listaFiltrada.add(empleado);
            }
        }
        return listaFiltrada;
    }

    //Recibe el objeto Empleado creado desde la Logica y se encarga de persistir
    public void altaEmpleado(Empleado empleado) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD
            jpaEmpleado.create(empleado);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Empleado desde la Logica y se encarga de persistir la actualizacion
    public void actualizarEmpleado(Empleado empleado) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaEmpleado.edit(empleado);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Empleado desde la Logica y se encarga de persistir la actualizacion
    public void eliminarEmpleado(Empleado empleado) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaEmpleado.edit(empleado);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
