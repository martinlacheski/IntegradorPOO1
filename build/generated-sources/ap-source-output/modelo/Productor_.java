package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Lote;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-07-07T16:05:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Productor.class)
public class Productor_ { 

    public static volatile SingularAttribute<Productor, Integer> legajo;
    public static volatile SingularAttribute<Productor, String> razonSocial;
    public static volatile SingularAttribute<Productor, Boolean> estado;
    public static volatile SingularAttribute<Productor, String> cuit;
    public static volatile SingularAttribute<Productor, String> direccion;
    public static volatile ListAttribute<Productor, Lote> lotes;
    public static volatile SingularAttribute<Productor, String> telefono;

}