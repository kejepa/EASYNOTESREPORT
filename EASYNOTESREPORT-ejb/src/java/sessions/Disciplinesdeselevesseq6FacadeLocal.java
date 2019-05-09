/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesseq6;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevesseq6FacadeLocal {

    void create(Disciplinesdeselevesseq6 disciplinesdeselevesseq6);

    void edit(Disciplinesdeselevesseq6 disciplinesdeselevesseq6);

    void remove(Disciplinesdeselevesseq6 disciplinesdeselevesseq6);

    Disciplinesdeselevesseq6 find(Object id);

    List<Disciplinesdeselevesseq6> findAll();

    List<Disciplinesdeselevesseq6> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevesseq6> listeElevesClasse(String codeClasse);

}
