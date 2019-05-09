/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevestrim1;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevestrim1FacadeLocal {

    void create(Disciplinesdeselevestrim1 disciplinesdeselevestrim1);

    void edit(Disciplinesdeselevestrim1 disciplinesdeselevestrim1);

    void remove(Disciplinesdeselevestrim1 disciplinesdeselevestrim1);

    Disciplinesdeselevestrim1 find(Object id);

    List<Disciplinesdeselevestrim1> findAll();

    List<Disciplinesdeselevestrim1> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevestrim1> listeElevesClasse(String codeClasse);

    boolean existeEleve(String matricule);

    List<Object[]> liesteEleveDiscip1_2(String codeclasse);

}
