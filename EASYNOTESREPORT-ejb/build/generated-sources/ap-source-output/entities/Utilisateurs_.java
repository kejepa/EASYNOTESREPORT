package entities;

import entities.Operations;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Utilisateurs.class)
public class Utilisateurs_ { 

    public static volatile CollectionAttribute<Utilisateurs, Operations> operationsCollection;
    public static volatile SingularAttribute<Utilisateurs, String> password;
    public static volatile SingularAttribute<Utilisateurs, String> role;
    public static volatile SingularAttribute<Utilisateurs, String> login;
    public static volatile SingularAttribute<Utilisateurs, String> autorisation;

}