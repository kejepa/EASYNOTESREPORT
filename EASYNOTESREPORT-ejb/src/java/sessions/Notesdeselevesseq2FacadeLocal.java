/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevesseq2;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Notesdeselevesseq2FacadeLocal {

    void create(Notesdeselevesseq2 notesdeselevesseq2);

    void edit(Notesdeselevesseq2 notesdeselevesseq2);

    void remove(Notesdeselevesseq2 notesdeselevesseq2);

    Notesdeselevesseq2 find(Object id);

    List<Notesdeselevesseq2> findAll();

    List<Notesdeselevesseq2> findRange(int[] range);

    int count();

    Integer nextId();

    List<Notesdeselevesseq2> listeMatiereClasse(String codeMatiere, String codeClsse);

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

    boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere);

    List<Notesdeselevesseq2> Eleve_et_sa_matiere(String matriculeEleve);

    List<Object[]> listedesMatieresParClasseDeEleve(String codeClas);

    List<Object[]> bulletinSeq2(String codeclasse);

    List<Object[]> bulletinSeq2ParEleve(String codeclass, String matriculeEleve);

    List<Object[]> bulletinSeq2ParSerie(String codeclass, String matriculeEleve, String matriculeEleveA);

    List<Object[]> pvSeq2(String codeclass);

    List<Object[]> synthSeq2(String codeclass);

    List<Object[]> effG_F8_par_interval(String codeclass);

    List<Object[]> Moy_premier_Dernier(String codeclass);

    List<Object[]> noteElevesSeq2(String codeclass);

}
