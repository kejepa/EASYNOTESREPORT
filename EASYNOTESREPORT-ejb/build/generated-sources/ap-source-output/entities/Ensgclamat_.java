package entities;

import entities.Classes;
import entities.EnsgclamatPK;
import entities.Matieres;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Ensgclamat.class)
public class Ensgclamat_ { 

    public static volatile SingularAttribute<Ensgclamat, Matieres> matieres;
    public static volatile SingularAttribute<Ensgclamat, Classes> classes;
    public static volatile SingularAttribute<Ensgclamat, EnsgclamatPK> ensgclamatPK;
    public static volatile SingularAttribute<Ensgclamat, String> nom;
    public static volatile SingularAttribute<Ensgclamat, String> matriculeenseignant;

}