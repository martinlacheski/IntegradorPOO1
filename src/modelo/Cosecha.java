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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "cosecha")
public class Cosecha implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCosecha;
    @Column(nullable = false)
    //@Temporal(TemporalType.LOCALDATE)
    private LocalDate fechaCosecha;
    @Column(nullable = false)
    private double kgsCampo;
    @Column(nullable = false)
    private double kgsSecadero;
    @Column(nullable = false)
    private double kgsCosecha;
    @Column(nullable = false)
    private boolean estado = true;
    @ManyToOne
    private Lote lote;
    @OneToMany (mappedBy = "cosecha")
    private List<DetalleCosecha> detallesCosechas = new ArrayList<>();

    //Constructores
    public Cosecha() {
    }

    public Cosecha(int idCosecha, LocalDate fechaCosecha, double kgsCampo, 
            double kgsSecadero, double kgsCosecha, boolean estado) {
        this.idCosecha = idCosecha;
        this.fechaCosecha = fechaCosecha;
        this.kgsCampo = kgsCampo;
        this.kgsSecadero = kgsSecadero;
        this.kgsCosecha = kgsCosecha;
        this.estado = estado;
    }

    //Getters y Setters
    public int getIdCosecha() {
        return idCosecha;
    }

    public void setIdCosecha(int idCosecha) {
        this.idCosecha = idCosecha;
    }

    public LocalDate getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(LocalDate fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

    public double getKgsCampo() {
        return kgsCampo;
    }

    public void setKgsCampo(double kgsCampo) {
        this.kgsCampo = kgsCampo;
    }

    public double getKgsSecadero() {
        return kgsSecadero;
    }

    public void setKgsSecadero(double kgsSecadero) {
        this.kgsSecadero = kgsSecadero;
    }

    public double getKgsCosecha() {
        return kgsCosecha;
    }

    public void setKgsCosecha(double kgsCosecha) {
        this.kgsCosecha = kgsCosecha;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<DetalleCosecha> getDetallesCosechas() {
        return detallesCosechas;
    }

    public void setDetallesCosechas(List<DetalleCosecha> detallesCosechas) {
        this.detallesCosechas = detallesCosechas;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Cosecha{" + "fechaCosecha= " + fechaCosecha + ", lote = " + 
                lote + ", kgsCampo= " + kgsCampo + ", kgsSecadero= " + 
                kgsSecadero + ", kgsCosecha= " + kgsCosecha + '}';
    }
    
    //Metodo obtener detalle de la cosecha por el empleado
    public List<DetalleCosecha> getDetallesEmpleados(Empleado empleado) {
        return detallesCosechas;
    }
    
    //Calcular diferencia de pesaje
    public void diferenciaPesaje(double kgsCampo, double kgsSecadero){
        this.kgsCosecha = kgsCampo - kgsSecadero;
    }

    
}
