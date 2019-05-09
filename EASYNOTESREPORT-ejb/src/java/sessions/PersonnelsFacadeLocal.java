/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personnels;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface PersonnelsFacadeLocal {

    void create(Personnels personnels);

    void edit(Personnels personnels);

    void remove(Personnels personnels);

    Personnels find(Object id);

    List<Personnels> findAll();

    List<Personnels> findRange(int[] range);

    int count();
    
    boolean recherchePersonnelParMatricule(String matriculepersonnel);
    
    List<Object[]> listedesMatieresParClasse();
    
}
