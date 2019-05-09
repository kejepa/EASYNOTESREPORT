/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesseq3;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevesseq3FacadeLocal {

    void create(Disciplinesdeselevesseq3 disciplinesdeselevesseq3);

    void edit(Disciplinesdeselevesseq3 disciplinesdeselevesseq3);

    void remove(Disciplinesdeselevesseq3 disciplinesdeselevesseq3);

    Disciplinesdeselevesseq3 find(Object id);

    List<Disciplinesdeselevesseq3> findAll();

    List<Disciplinesdeselevesseq3> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevesseq3> listeElevesClasse(String codeClasse);

}
