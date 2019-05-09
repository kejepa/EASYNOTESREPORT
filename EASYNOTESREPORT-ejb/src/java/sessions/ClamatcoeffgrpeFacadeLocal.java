/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Clamatcoeffgrpe;
import entities.Classes;
import entities.Matieres;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface ClamatcoeffgrpeFacadeLocal {

    void create(Clamatcoeffgrpe clamatcoeffgrpe);

    void edit(Clamatcoeffgrpe clamatcoeffgrpe);

    void remove(Clamatcoeffgrpe clamatcoeffgrpe);

    Clamatcoeffgrpe find(Object id);

    List<Clamatcoeffgrpe> findAll();

    List<Clamatcoeffgrpe> findRange(int[] range);

    int count();

    List<Classes> listedesClasses();

    List<Matieres> listedesMatieres();

    boolean matiereMatiereClasseExixteDeja(String classe, String codematiere);

    List<Classes> listedesClasses_clone();

    List<Object[]> classeMatiere_clone(String codeclasse);

    List<Object[]> liteaffactationParClasse(String codeclasse);
}
