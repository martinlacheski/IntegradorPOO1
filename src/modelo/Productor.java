package modelo;

import java.io.Serializable;
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
@Table(name = "productor")
public class Productor implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int legajo;
    @Column(nullable = false)
    private String razonSocial;
    @Column(nullable = false)
    private String cuit;
    @Column(nullable = false)
    private String direccion;
    private String telefono;
    @Column(nullable = false)
    private boolean estado = true;
    @OneToMany (mappedBy = "productor")
    private List<Lote> lotes = new ArrayList<>();
    
    //Constructores
    public Productor() {
    }

    public Productor(int legajo, String razonSocial, String cuit, 
            String direccion, String telefono, boolean estado) {
        this.legajo = legajo;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    //Getters y Setters
    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }
    
    //Metodo toString
    @Override
    public String toString() {
        /*return "Productor{" + "legajo=" + legajo + ", razonSocial=" + 
                razonSocial + ", cuit=" + cuit + ", direccion=" + direccion + 
                ", telefono=" + telefono + ", estado=" + estado + '}';*/
        return razonSocial;
    }

        
}

