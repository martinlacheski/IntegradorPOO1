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
@Table(name = "lote")
public class Lote implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLote;
    @Column(nullable = false)
    private String nombreLote;
    @Column(nullable = false)
    private String direccionLote;
    @Column(nullable = false)
    private boolean estado = true;
    @ManyToOne
    private Productor productor;
    @OneToMany (mappedBy = "lote")
    private List<Cuadro> cuadros = new ArrayList<>();
    @OneToMany (mappedBy = "lote")
    private List<Cosecha> cosechas = new ArrayList<>();
    
    //Constructores
    public Lote() {
    }

    public Lote(int idLote, String nombreLote, String direccionLote, 
             boolean estado, Productor productor) {
        this.idLote = idLote;
        this.nombreLote = nombreLote;
        this.direccionLote = direccionLote;
        this.estado = estado;
        this.productor = productor;
    }

    //Getters y Setters
    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public String getNombreLote() {
        return nombreLote;
    }

    public void setNombreLote(String nombreLote) {
        this.nombreLote = nombreLote;
    }
    
    public String getDireccionLote() {
        return direccionLote;
    }

    public void setDireccionLote(String direccionLote) {
        this.direccionLote = direccionLote;
    }

    public boolean getEstado() {
        return estado;
    }   
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public List<Cuadro> getCuadros() {
        return cuadros;
    }

    public void setCuadros(List<Cuadro> cuadros) {
        this.cuadros = cuadros;
    }
       
    //Metodo toString
    @Override
    public String toString() {
        return nombreLote;
    }
    
    
}
