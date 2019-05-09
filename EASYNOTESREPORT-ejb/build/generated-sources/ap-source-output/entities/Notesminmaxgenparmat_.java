package entities;

import entities.Classes;
import entities.Matieres;
import entities.NotesminmaxgenparmatPK;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Notesminmaxgenparmat.class)
public class Notesminmaxgenparmat_ { 

    public static volatile SingularAttribute<Notesminmaxgenparmat, Matieres> matieres;
    public static volatile SingularAttribute<Notesminmaxgenparmat, NotesminmaxgenparmatPK> notesminmaxgenparmatPK;
    public static volatile SingularAttribute<Notesminmaxgenparmat, Classes> classes;
    public static volatile SingularAttribute<Notesminmaxgenparmat, BigDecimal> notemin;
    public static volatile SingularAttribute<Notesminmaxgenparmat, BigDecimal> notemax;
    public static volatile SingularAttribute<Notesminmaxgenparmat, BigDecimal> moyenne;

}