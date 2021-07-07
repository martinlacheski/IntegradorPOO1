package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detallecosecha")
public class DetalleCosecha implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleCosecha;
    @Column(nullable = false)
    private double kgsEmpleado;
    @Column(nullable = false)
    private boolean estado = true;
    @ManyToOne
    private Empleado empleado;
    @ManyToOne
    private Cuadro cuadro;
    @ManyToOne
    private Cosecha cosecha;
    
    //Constructores
    public DetalleCosecha() {
    }

    public DetalleCosecha(int idDetalleCosecha, double kgsEmpleado, 
            boolean estado, Empleado empleado, Cuadro cuadro, Cosecha cosecha) {
        this.idDetalleCosecha = idDetalleCosecha;
        this.kgsEmpleado = kgsEmpleado;
        this.estado = estado;
        this.empleado = empleado;
        this.cuadro = cuadro;
        this.cosecha = cosecha;
    }

    //Getters y Setters
    public int getIdDetalleCosecha() {
        return idDetalleCosecha;
    }

    public void setIdDetalleCosecha(int idDetalleCosecha) {
        this.idDetalleCosecha = idDetalleCosecha;
    }

    public double getKgsEmpleado() {
        return kgsEmpleado;
    }

    public void setKgsEmpleado(double kgsEmpleado) {
        this.kgsEmpleado = kgsEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cuadro getCuadro() {
        return cuadro;
    }

    public void setCuadro(Cuadro cuadro) {
        this.cuadro = cuadro;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cosecha getCosecha() {
        return cosecha;
    }

    public void setCosecha(Cosecha cosecha) {
        this.cosecha = cosecha;
    }
   
    //Metodo toString
    @Override
    public String toString() {
        return "DetalleCosecha{" + "kgsEmpleado=" + kgsEmpleado + 
                ", empleado=" + empleado + ", cuadro=" + cuadro + '}';
    }
    
    //Ingresar detalle de la Cosecha
    public void ingresarDetalle(Empleado empleado, double kgsEmpleado, 
            Cuadro cuadro){
        this.empleado = empleado;
        this.kgsEmpleado = kgsEmpleado;
        this.cuadro = cuadro;
    }  
}

