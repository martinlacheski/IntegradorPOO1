package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int legajo;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String cuil;
    @Column(nullable = false)
    private String direccion;
    private String telefono;
    @Column(nullable = false)
    //@Temporal(TemporalType.DATE)
    private LocalDate fechaNacimiento;
    @Column(nullable = false)
    //@Temporal(TemporalType.DATE)
    private LocalDate fechaIngreso;
    
    @Column(nullable = false)
    private boolean estado = true;
    @OneToMany (mappedBy = "empleado")
    private List<DetalleCosecha> detallesCosechas = new ArrayList<>();

    //Constructores
    public Empleado() {
    }

    public Empleado(String apellido, String nombres, String cuil, 
            String direccion, String telefono, LocalDate fechaIngreso, 
            LocalDate fechaNacimiento) {
        this.apellido = apellido;
        this.nombres = nombres;
        this.cuil = cuil;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Getters y Setters
    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<DetalleCosecha> getDetallesCosechas() {
        return detallesCosechas;
    }

    public void setDetallesCosechas(List<DetalleCosecha> detallesCosechas) {
        this.detallesCosechas = detallesCosechas;
    }
       
    //Metodo ToString
    @Override
    public String toString() {
        return  apellido + ", " + nombres;
    }
    
    public double getKilos(LocalDate inicio, LocalDate fin){
        return 0;
    }
}

