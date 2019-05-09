package entities;

import entities.ClamatcoeffgrpePK;
import entities.Classes;
import entities.Matieres;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Clamatcoeffgrpe.class)
public class Clamatcoeffgrpe_ { 

    public static volatile SingularAttribute<Clamatcoeffgrpe, Matieres> matieres;
    public static volatile SingularAttribute<Clamatcoeffgrpe, ClamatcoeffgrpePK> clamatcoeffgrpePK;
    public static volatile SingularAttribute<Clamatcoeffgrpe, Classes> classes;
    public static volatile SingularAttribute<Clamatcoeffgrpe, BigDecimal> coefficient;
    public static volatile SingularAttribute<Clamatcoeffgrpe, Integer> groupe;

}