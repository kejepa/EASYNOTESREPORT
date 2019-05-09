/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevestrim2;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Disciplinesdeselevestrim2FacadeLocal {

    void create(Disciplinesdeselevestrim2 disciplinesdeselevestrim2);

    void edit(Disciplinesdeselevestrim2 disciplinesdeselevestrim2);

    void remove(Disciplinesdeselevestrim2 disciplinesdeselevestrim2);

    Disciplinesdeselevestrim2 find(Object id);

    List<Disciplinesdeselevestrim2> findAll();

    List<Disciplinesdeselevestrim2> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Disciplinesdeselevestrim2> listeElevesClasse(String codeClasse);
    
    boolean existeEleve(String matricule);

    List<Object[]> liesteEleveDiscip3_4(String codeclasse);

}
