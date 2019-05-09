package entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-08T11:43:32")
@StaticMetamodel(FicheConseil.class)
public class FicheConseil_ { 

    public static volatile SingularAttribute<FicheConseil, Integer> consigne;
    public static volatile SingularAttribute<FicheConseil, BigDecimal> moyenne_classe;
    public static volatile SingularAttribute<FicheConseil, Integer> exclusion;
    public static volatile SingularAttribute<FicheConseil, String> sexe;
    public static volatile SingularAttribute<FicheConseil, BigDecimal> moyenne1;
    public static volatile SingularAttribute<FicheConseil, String> matriculeeleve;
    public static volatile SingularAttribute<FicheConseil, String> tauxreussite;
    public static volatile SingularAttribute<FicheConseil, Integer> absn;
    public static volatile SingularAttribute<FicheConseil, String> nomeleve;
    public static volatile SingularAttribute<FicheConseil, Long> id;
    public static volatile SingularAttribute<FicheConseil, String> to_char;
    public static volatile SingularAttribute<FicheConseil, String> rang;
    public static volatile SingularAttribute<FicheConseil, String> redoublant;

}