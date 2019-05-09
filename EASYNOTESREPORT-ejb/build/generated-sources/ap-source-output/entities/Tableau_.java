package entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Tableau.class)
public class Tableau_ { 

    public static volatile SingularAttribute<Tableau, String> codeclasse;
    public static volatile SingularAttribute<Tableau, Long> id;
    public static volatile SingularAttribute<Tableau, String> nom;
    public static volatile SingularAttribute<Tableau, String> prenom;
    public static volatile SingularAttribute<Tableau, BigDecimal> moyenne;

}