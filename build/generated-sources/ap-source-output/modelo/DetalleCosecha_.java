package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cosecha;
import modelo.Cuadro;
import modelo.Empleado;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-07-07T16:05:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(DetalleCosecha.class)
public class DetalleCosecha_ { 

    public static volatile SingularAttribute<DetalleCosecha, Boolean> estado;
    public static volatile SingularAttribute<DetalleCosecha, Empleado> empleado;
    public static volatile SingularAttribute<DetalleCosecha, Cosecha> cosecha;
    public static volatile SingularAttribute<DetalleCosecha, Integer> idDetalleCosecha;
    public static volatile SingularAttribute<DetalleCosecha, Double> kgsEmpleado;
    public static volatile SingularAttribute<DetalleCosecha, Cuadro> cuadro;

}