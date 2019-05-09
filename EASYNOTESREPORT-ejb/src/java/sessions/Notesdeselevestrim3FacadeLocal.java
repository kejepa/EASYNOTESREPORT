/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevestrim3;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Notesdeselevestrim3FacadeLocal {

    void create(Notesdeselevestrim3 notesdeselevestrim3);

    void edit(Notesdeselevestrim3 notesdeselevestrim3);

    void remove(Notesdeselevestrim3 notesdeselevestrim3);

    Notesdeselevestrim3 find(Object id);

    List<Notesdeselevestrim3> findAll();

    List<Notesdeselevestrim3> findRange(int[] range);

    int count();

    Integer nextId();

    Notesdeselevestrim3 upadateNoteTrim3Eleves(String codeMatiere, String matricule);

    List<Listedeseleves> listeDesNouveauxElevesTrim3(String codeMatiere, String codeclasse);

    BigDecimal noteEleve(String matriculeEleve, String codeMatiere);

    boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere);

    List<Object[]> bulletinTrim3(String codeclasse);

    List<Object[]> bulletinTrim3ParEleve(String codeclass, String matriculeEleve);

    List<Object[]> bulletinTrim3ParSerie(String codeclass, String matriculeEleve, String matriculeEleveA);

    List<Object[]> pvTrim3(String codeclass);

    List<Object[]> synthTrim3(String codeclass);

    List<Object[]> effG_F8_par_interval(String codeclass);

    List<Object[]> Moy_premier_Dernier(String codeclass);

    List<Object[]> noteSeq5_6(String codeclass);
}
