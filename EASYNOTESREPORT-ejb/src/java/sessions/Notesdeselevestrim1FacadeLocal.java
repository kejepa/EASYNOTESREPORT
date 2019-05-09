/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevestrim1;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Notesdeselevestrim1FacadeLocal {

    void create(Notesdeselevestrim1 notesdeselevestrim1);

    void edit(Notesdeselevestrim1 notesdeselevestrim1);

    void remove(Notesdeselevestrim1 notesdeselevestrim1);

    Notesdeselevestrim1 find(Object id);

    List<Notesdeselevestrim1> findAll();

    List<Notesdeselevestrim1> findRange(int[] range);

    int count();

    Integer nextId();

    Notesdeselevestrim1 upadateNoteTrim1Eleves(String codeMatiere, String matricule);

    List<Listedeseleves> listeDesNouveauxElevesTrim1(String codeMatiere, String codeclasse);

    BigDecimal noteEleve(String matriculeEleve, String codeMatiere);

    boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere);

    List<Object[]> bulletinTrim1(String codeclasse);

    List<Object[]> bulletinTrim1ParEleve(String codeclass, String matriculeEleve);

    List<Object[]> bulletinTrim1ParSerie(String codeclass, String matriculeEleve, String matriculeEleveA);

    List<Object[]> pvTrim1(String codeclass);

    List<Object[]> synthTrim1(String codeclass);

    List<Object[]> effG_F8_par_interval(String codeclass);

    List<Object[]> Moy_premier_Dernier(String codeclass);

    List<Object[]> ficheConseilAn(String codeclass);

    List<Object[]> ficheConseilTrim1(String codeclass);

    List<Object[]> ficheConseilTrim2(String codeclass);

    List<Object[]> ficheConseilTrim3(String codeclass);
    
    List<Object[]> noteSeq1_2(String codeclass);

}
