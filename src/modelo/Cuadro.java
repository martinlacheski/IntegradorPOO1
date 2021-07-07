package modelo;

import java.io.Serializable;
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
@Table(name = "cuadro")
public class Cuadro implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCuadro;
    @Column(nullable = false)
    private String nombreCuadro;
    @Column(nullable = false)
    private boolean estado = true;
    @ManyToOne
    private Lote lote;
    @OneToMany (mappedBy = "cuadro")
    private List<DetalleCosecha> detallesCosechas = new ArrayList<>();
    
    //Constructores
    public Cuadro() {
    }

    public Cuadro(int idCuadro, String nombreCuadro, boolean estado, Lote lote){
        this.idCuadro = idCuadro;
        this.nombreCuadro = nombreCuadro;
        this.estado = estado;
        this.lote = lote;
    }

    //Getters y Setters
    public int getIdCuadro() {
        return idCuadro;
    }

    public void setIdCuadro(int idCuadro) {
        this.idCuadro = idCuadro;
    }

    public String getNombreCuadro() {
        return nombreCuadro;
    }

    public void setNombreCuadro(String nombreCuadro) {
        this.nombreCuadro = nombreCuadro;
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
    
    //metodo toString
    @Override
    public String toString() {
        return nombreCuadro;
    }
}

