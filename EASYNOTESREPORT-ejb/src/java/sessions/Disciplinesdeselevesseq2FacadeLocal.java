/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesseq2;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevesseq2FacadeLocal {

    void create(Disciplinesdeselevesseq2 disciplinesdeselevesseq2);

    void edit(Disciplinesdeselevesseq2 disciplinesdeselevesseq2);

    void remove(Disciplinesdeselevesseq2 disciplinesdeselevesseq2);

    Disciplinesdeselevesseq2 find(Object id);

    List<Disciplinesdeselevesseq2> findAll();

    List<Disciplinesdeselevesseq2> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevesseq2> listeElevesClasse(String codeClasse);

}
