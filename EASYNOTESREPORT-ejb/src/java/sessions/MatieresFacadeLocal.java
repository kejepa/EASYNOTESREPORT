/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Matieres;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface MatieresFacadeLocal {

    void create(Matieres matieres);

    void edit(Matieres matieres);

    void remove(Matieres matieres);

    Matieres find(Object id);

    List<Matieres> findAll();

    List<Matieres> findRange(int[] range);

    int count();

    boolean rechercheMatiereParCode(String code);

    List<Matieres> listeMatieresParClasse(String codeclasse);

}
