/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesann;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface DisciplinesdeselevesannFacadeLocal {

    void create(Disciplinesdeselevesann disciplinesdeselevesann);

    void edit(Disciplinesdeselevesann disciplinesdeselevesann);

    void remove(Disciplinesdeselevesann disciplinesdeselevesann);

    Disciplinesdeselevesann find(Object id);

    List<Disciplinesdeselevesann> findAll();

    List<Disciplinesdeselevesann> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevesann> listeElevesClasse(String codeClasse);

    boolean existeEleve(String matricule);

    List<Object[]> liesteEleveDiscip1_a_6(String codeclasse);

}
