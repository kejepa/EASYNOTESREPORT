package entities;

import entities.Annrangdeselevesparmatiere;
import entities.Calculnotesdesclasses;
import entities.Clamatcoeffgrpe;
import entities.Ensgclamat;
import entities.Listedeseleves;
import entities.Personnels;
import entities.Profprinc;
import entities.Seq1rangdeselevesparmatiere;
import entities.Seq2rangdeselevesparmatiere;
import entities.Seq3rangdeselevesparmatiere;
import entities.Seq4rangdeselevesparmatiere;
import entities.Seq5rangdeselevesparmatiere;
import entities.Seq6rangdeselevesparmatiere;
import entities.Trim1rangdeselevesparmatiere;
import entities.Trim2rangdeselevesparmatiere;
import entities.Trim3rangdeselevesparmatiere;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(Classes.class)
public class Classes_ { 

    public static volatile CollectionAttribute<Classes, Calculnotesdesclasses> calculnotesdesclassesCollection;
    public static volatile SingularAttribute<Classes, String> section;
    public static volatile SingularAttribute<Classes, String> classcorresp;
    public static volatile CollectionAttribute<Classes, Listedeseleves> listedeselevesCollection;
    public static volatile SingularAttribute<Classes, String> niveau;
    public static volatile CollectionAttribute<Classes, Annrangdeselevesparmatiere> annmoyennesrangsdeselevesCollection;
    public static volatile CollectionAttribute<Classes, Seq5rangdeselevesparmatiere> seq5rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Clamatcoeffgrpe> clamatcoeffgrpeCollection;
    public static volatile CollectionAttribute<Classes, Seq4rangdeselevesparmatiere> seq4rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Trim3rangdeselevesparmatiere> trim3rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Seq6rangdeselevesparmatiere> seq6rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Trim2rangdeselevesparmatiere> trim2rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Trim1rangdeselevesparmatiere> trim1rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Ensgclamat> ensgclamatCollection;
    public static volatile CollectionAttribute<Classes, Personnels> personnelsCollection;
    public static volatile SingularAttribute<Classes, String> codeclasse;
    public static volatile SingularAttribute<Classes, String> nomclasse;
    public static volatile SingularAttribute<Classes, String> typedeclasse;
    public static volatile SingularAttribute<Classes, Profprinc> profprinc;
    public static volatile CollectionAttribute<Classes, Seq1rangdeselevesparmatiere> seq1rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Seq2rangdeselevesparmatiere> seq2rangdeselevesparmatiereCollection;
    public static volatile CollectionAttribute<Classes, Seq3rangdeselevesparmatiere> seq3rangdeselevesparmatiereCollection;

}