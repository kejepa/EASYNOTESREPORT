/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Classes;
import entities.Ensgclamat;
import entities.Matieres;
import entities.Personnels;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface EnsgclamatFacadeLocal {

    void create(Ensgclamat ensgclamat);

    void edit(Ensgclamat ensgclamat);

    void remove(Ensgclamat ensgclamat);

    Ensgclamat find(Object id);

    List<Ensgclamat> findAll();

    List<Ensgclamat> findRange(int[] range);

    int count();

    Integer nextId();

    List<Classes> listedesClasses();

    List<Matieres> listedesMatieres(String codeclasse);

    List<String> listedesPersonnels(String codematiere);

    boolean matiereEnseignatClasseExixteDeja(String classe, String codematiere);

    String rechercheEnseignantParMatricule(String mat);

}
