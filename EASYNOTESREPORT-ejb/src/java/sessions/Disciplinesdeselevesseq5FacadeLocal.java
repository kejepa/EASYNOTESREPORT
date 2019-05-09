/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesseq5;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevesseq5FacadeLocal {

    void create(Disciplinesdeselevesseq5 disciplinesdeselevesseq5);

    void edit(Disciplinesdeselevesseq5 disciplinesdeselevesseq5);

    void remove(Disciplinesdeselevesseq5 disciplinesdeselevesseq5);

    Disciplinesdeselevesseq5 find(Object id);

    List<Disciplinesdeselevesseq5> findAll();

    List<Disciplinesdeselevesseq5> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevesseq5> listeElevesClasse(String codeClasse);

}
