package modelo;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleCosecha;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-07-08T13:56:46", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, LocalDate> fechaIngreso;
    public static volatile SingularAttribute<Empleado, Integer> legajo;
    public static volatile SingularAttribute<Empleado, Boolean> estado;
    public static volatile ListAttribute<Empleado, DetalleCosecha> detallesCosechas;
    public static volatile SingularAttribute<Empleado, LocalDate> fechaNacimiento;
    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile SingularAttribute<Empleado, String> direccion;
    public static volatile SingularAttribute<Empleado, String> cuil;
    public static volatile SingularAttribute<Empleado, String> telefono;
    public static volatile SingularAttribute<Empleado, String> nombres;

}