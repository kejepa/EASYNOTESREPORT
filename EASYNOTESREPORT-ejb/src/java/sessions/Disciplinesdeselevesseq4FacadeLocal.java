/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesseq4;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevesseq4FacadeLocal {

    void create(Disciplinesdeselevesseq4 disciplinesdeselevesseq4);

    void edit(Disciplinesdeselevesseq4 disciplinesdeselevesseq4);

    void remove(Disciplinesdeselevesseq4 disciplinesdeselevesseq4);

    Disciplinesdeselevesseq4 find(Object id);

    List<Disciplinesdeselevesseq4> findAll();

    List<Disciplinesdeselevesseq4> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevesseq4> listeElevesClasse(String codeClasse);

}
