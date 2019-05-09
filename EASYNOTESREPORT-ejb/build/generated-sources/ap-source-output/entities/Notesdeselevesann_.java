package entities;

import entities.Listedeseleves;
import entities.Matieres;
import entities.NotesdeselevesannPK;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Notesdeselevesann.class)
public class Notesdeselevesann_ { 

    public static volatile SingularAttribute<Notesdeselevesann, Listedeseleves> listedeseleves;
    public static volatile SingularAttribute<Notesdeselevesann, Matieres> matieres;
    public static volatile SingularAttribute<Notesdeselevesann, BigDecimal> lanote;
    public static volatile SingularAttribute<Notesdeselevesann, NotesdeselevesannPK> notesdeselevesannPK;

}