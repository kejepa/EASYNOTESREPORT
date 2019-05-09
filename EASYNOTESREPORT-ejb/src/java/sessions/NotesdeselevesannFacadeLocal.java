/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevesann;
import entities.Notesdeselevesseq5;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface NotesdeselevesannFacadeLocal {

    void create(Notesdeselevesann notesdeselevesann);

    void edit(Notesdeselevesann notesdeselevesann);

    void remove(Notesdeselevesann notesdeselevesann);

    Notesdeselevesann find(Object id);

    List<Notesdeselevesann> findAll();

    List<Notesdeselevesann> findRange(int[] range);

    int count();

    Integer nextId();

    List<Listedeseleves> listeDesNouveauxElevesAnn(String codeMatiere, String codeclasse);

    Notesdeselevesann upadateNoteAnuelleEleves(String codeMatiere, String matricule);

    boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere);

    List<Object[]> bulletinAnn(String codeclasse);

    List<Object[]> bulletinAnnParEleve(String codeclass, String matriculeEleve);

    List<Object[]> bulletinAnnParSerie(String codeclass, String matriculeEleve, String matriculeEleveA);

    List<Object[]> pvAnn(String codeclass);

    List<Object[]> synthAnn(String codeclass);

    List<Object[]> effG(String codeclass);

    List<Object[]> effF(String codeclass);

    List<Object[]> effG_F8_par_interval(String codeclass);

    List<Object[]> Moy_premier_Dernier(String codeclass);

    List<Object[]> rappelSeq(String codeclass);

    List<Object[]> rappelTrim(String codeclass);

    List<Object[]> rappelseqTaux(String codeclass);

    List<Object[]> rappelTimTaux(String codeclass);

    List<Object[]> synthAnn_Trim(String codeclass);

    List<Object[]> synthAnn_Trim1(String codeclass);

    List<Object[]> synthAnn_Trim2(String codeclass);

    List<Object[]> synthAnn_Trim3(String codeclass);

    List<Object[]> synthAnn_An(String codeclass);

    List<BigDecimal> moyGene_Trim_An(String codeclass);

    List<Object[]> fiches(String codeClasse);

    List<Object[]> notesSeq1DeLamatiere(String codeClasse, String codematiere);

    List<Object[]> notesSeq2DeLamatiere(String codeClasse, String codematiere);

    List<Object[]> notesSeq3DeLamatiere(String codeClasse, String codematiere);

    List<Object[]> notesSeq4DeLamatiere(String codeClasse, String codematiere);

    List<Object[]> notesSeq5DeLamatiere(String codeClasse, String codematiere);

    List<Object[]> verifierEleve_matiereSeq2(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereSeq3(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereSeq4(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereSeq5(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereSeq6(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereTrim1(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereTrim2(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereTrim3(String matricule, String codematiere);

    List<Object[]> verifierEleve_matiereAnn(String matricule, String codematiere);

    List<Object[]> noteSeq123456(String codeclass);
}
