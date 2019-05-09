package entities;

import entities.Matieres;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Specialite.class)
public class Specialite_ { 

    public static volatile SingularAttribute<Specialite, String> nomspecialite;
    public static volatile SingularAttribute<Specialite, String> codespecialite;
    public static volatile CollectionAttribute<Specialite, Matieres> matieresCollection;

}