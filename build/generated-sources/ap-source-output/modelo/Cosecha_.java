package modelo;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleCosecha;
import modelo.Lote;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-07-07T19:23:47", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Cosecha.class)
public class Cosecha_ { 

    public static volatile SingularAttribute<Cosecha, Boolean> estado;
    public static volatile ListAttribute<Cosecha, DetalleCosecha> detallesCosechas;
    public static volatile SingularAttribute<Cosecha, Integer> idCosecha;
    public static volatile SingularAttribute<Cosecha, LocalDate> fechaCosecha;
    public static volatile SingularAttribute<Cosecha, Double> kgsCosecha;
    public static volatile SingularAttribute<Cosecha, Lote> lote;
    public static volatile SingularAttribute<Cosecha, Double> kgsSecadero;
    public static volatile SingularAttribute<Cosecha, Double> kgsCampo;

}