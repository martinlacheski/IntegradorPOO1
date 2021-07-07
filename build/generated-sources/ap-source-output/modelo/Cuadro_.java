package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleCosecha;
import modelo.Lote;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-07-07T18:41:02", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Cuadro.class)
public class Cuadro_ { 

    public static volatile SingularAttribute<Cuadro, Integer> idCuadro;
    public static volatile SingularAttribute<Cuadro, Boolean> estado;
    public static volatile ListAttribute<Cuadro, DetalleCosecha> detallesCosechas;
    public static volatile SingularAttribute<Cuadro, Lote> lote;
    public static volatile SingularAttribute<Cuadro, String> nombreCuadro;

}