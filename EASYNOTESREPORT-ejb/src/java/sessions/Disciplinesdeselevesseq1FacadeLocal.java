/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesseq1;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevesseq1FacadeLocal {

    void create(Disciplinesdeselevesseq1 disciplinesdeselevesseq1);

    void edit(Disciplinesdeselevesseq1 disciplinesdeselevesseq1);

    void remove(Disciplinesdeselevesseq1 disciplinesdeselevesseq1);

    Disciplinesdeselevesseq1 find(Object id);

    List<Disciplinesdeselevesseq1> findAll();

    List<Disciplinesdeselevesseq1> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevesseq1> listeElevesClasse(String codeClasse);

    boolean findEnseignatByPwd(String password);

}
