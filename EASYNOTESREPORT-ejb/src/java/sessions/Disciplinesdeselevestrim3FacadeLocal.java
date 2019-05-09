/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevestrim3;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevestrim3FacadeLocal {

    void create(Disciplinesdeselevestrim3 disciplinesdeselevestrim3);

    void edit(Disciplinesdeselevestrim3 disciplinesdeselevestrim3);

    void remove(Disciplinesdeselevestrim3 disciplinesdeselevestrim3);

    Disciplinesdeselevestrim3 find(Object id);

    List<Disciplinesdeselevestrim3> findAll();

    List<Disciplinesdeselevestrim3> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevestrim3> listeElevesClasse(String codeClasse);
    
    boolean existeEleve(String matricule);

    List<Object[]> liesteEleveDiscip5_6(String codeclasse);

}
