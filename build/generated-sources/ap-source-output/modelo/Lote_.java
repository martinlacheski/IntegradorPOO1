package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cosecha;
import modelo.Cuadro;
import modelo.Productor;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-07-08T13:56:46", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Lote.class)
public class Lote_ { 

    public static volatile SingularAttribute<Lote, Boolean> estado;
    public static volatile SingularAttribute<Lote, String> nombreLote;
    public static volatile SingularAttribute<Lote, String> direccionLote;
    public static volatile ListAttribute<Lote, Cosecha> cosechas;
    public static volatile ListAttribute<Lote, Cuadro> cuadros;
    public static volatile SingularAttribute<Lote, Productor> productor;
    public static volatile SingularAttribute<Lote, Integer> idLote;

}