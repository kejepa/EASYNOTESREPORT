/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevesseq1;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Notesdeselevesseq1FacadeLocal {

    void create(Notesdeselevesseq1 notesdeselevesseq1);

    void edit(Notesdeselevesseq1 notesdeselevesseq1);

    void remove(Notesdeselevesseq1 notesdeselevesseq1);

    Notesdeselevesseq1 find(Object id);

    List<Notesdeselevesseq1> findAll();

    List<Notesdeselevesseq1> findRange(int[] range);

    int count();

    Integer nextId();

    List<Notesdeselevesseq1> listeMatiereClasse(String codeMatiere, String codeClsse);

    List<Object[]> listedesMatieresParClasse(String codeClas);

    List<Listedeseleves> listeDesNouveauxEleves(String codeMatiere, String codeclasse);

    boolean findEnseignatByPwd(String password, String codematiere);

    BigDecimal findPetiteMoyenne(String codeMatiere, String codeclasse);

    BigDecimal findGandeMoyenne(String codeMatiere, String codeclasse);

    BigDecimal findTauxreussite(String codeMatiere, String codeclasse);

    BigDecimal findMoyenne(String codeMatiere, String codeclasse);

    BigDecimal noteEleve(String matriculeEleve, String codeMatiere);

    int eleveComposé(String codeMatiere, String codeclasse);

    int eleveAbsent(String codeMatiere, String codeclasse);

    List<Notesdeselevesseq1> liste_des_eleves_par_classe(String codeclasse);

    List<Object[]> listedesMatieresParClasseDeEleve(String codeClas);

    boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere);

    List<Notesdeselevesseq1> Eleve_et_sa_matiere(String matriculeEleve);

    Long effectifclasse(String codeclasse);

    String pp(String codeclasse);

    List<Object[]> ppEts();

    List<String> listeElevesParClasse(String codeclasse);

    List<Object[]> bulletinSeq1(String codeclasse);

    List<Object[]> bulletinSeq1ParEleve(String codeclass, String matriculeEleve);

    List<Object[]> bulletinSeq1ParSerie(String codeclass, String matriculeEleve, String matriculeEleveA);

    List<BigDecimal> borneTravail();

    List<Object[]> pvSeq1(String codeclass);

    List<Object[]> synthSeq1(String codeclass);

    List<Object[]> effG_F8_par_interval(String codeclass);

    List<Object[]> Moy_premier_Dernier(String codeclass);

    List<Object []> noteElevesSeq1(String codeclass);

}
