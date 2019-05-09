package entities;

import entities.Utilisateurs;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Operations.class)
public class Operations_ { 

    public static volatile SingularAttribute<Operations, Integer> idoperations;
    public static volatile SingularAttribute<Operations, String> typeoperation;
    public static volatile SingularAttribute<Operations, String> description;
    public static volatile SingularAttribute<Operations, Date> dateoperation;
    public static volatile SingularAttribute<Operations, Utilisateurs> login;

}