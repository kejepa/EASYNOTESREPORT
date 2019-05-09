/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevestrim2;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Notesdeselevestrim2FacadeLocal {

    void create(Notesdeselevestrim2 notesdeselevestrim2);

    void edit(Notesdeselevestrim2 notesdeselevestrim2);

    void remove(Notesdeselevestrim2 notesdeselevestrim2);

    Notesdeselevestrim2 find(Object id);

    List<Notesdeselevestrim2> findAll();

    List<Notesdeselevestrim2> findRange(int[] range);

    int count();

    Integer nextId();

    Notesdeselevestrim2 upadateNoteTrim2Eleves(String codeMatiere, String matricule);

    List<Listedeseleves> listeDesNouveauxElevesTrim2(String codeMatiere, String codeclasse);

    BigDecimal noteEleve(String matriculeEleve, String codeMatiere);

    boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere);

    List<Object[]> bulletinTrim2(String codeclasse);

    List<Object[]> bulletinTrim2ParEleve(String codeclass, String matriculeEleve);

    List<Object[]> bulletinTrim2ParSerie(String codeclass, String matriculeEleve, String matriculeEleveA);

    List<Object[]> pvTrim2(String codeclass);

    List<Object[]> synthTrim2(String codeclass);

    List<Object[]> effG_F8_par_interval(String codeclass);

    List<Object[]> Moy_premier_Dernier(String codeclass);

    List<Object[]> noteSeq3_4(String codeclass);
}
