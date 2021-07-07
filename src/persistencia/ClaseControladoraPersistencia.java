package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Cuadro;
import modelo.Empleado;
import modelo.Lote;
import modelo.Productor;

public class ClaseControladoraPersistencia {

    EmpleadoJpaController jpaEmpleado = new EmpleadoJpaController();
    ProductorJpaController jpaProductor = new ProductorJpaController();
    LoteJpaController jpaLote = new LoteJpaController();
    CuadroJpaController jpaCuadro = new CuadroJpaController();
    CosechaJpaController jpaCosecha = new CosechaJpaController();
    DetalleCosechaJpaController jpaDetalleCosecha = new DetalleCosechaJpaController();

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
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Productor desde la Logica y se encarga de persistir la actualizacion
    public void actualizarProductor(Productor productor) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaProductor.edit(productor);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Productor desde la Logica y se encarga de persistir la actualizacion
    public void eliminarProductor(Productor productor) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaProductor.edit(productor);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
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
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Lote desde la Logica y se encarga de persistir la actualizacion
    public void actualizarLote(Lote lote) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaLote.edit(lote);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //Recibe el objeto Lote desde la Logica y se encarga de persistir la actualizacion
    public void eliminarLote(Lote lote) {
        //Utilizamos el TRY por si hay algun problema
        try {
            //Insertamos dentro de la BD el UPDATE
            jpaLote.edit(lote);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClaseControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    //Metodo para listar los Cuadros actuales en la BD por estado TRUE y Lote seleccionado
    public List<Cuadro> listarCuadros(Lote lote) {
        List<Cuadro> listaCuadros;
        listaCuadros = jpaCuadro.findCuadroEntities();
        List<Cuadro> listaFiltrada = new ArrayList<Cuadro>();
        for (Cuadro cuadro : listaCuadros) {
            if ((cuadro.getEstado() == true) && (cuadro.getLote() == lote)){
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
