/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Profprinc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface ProfprincFacadeLocal {

    void create(Profprinc profprinc);

    void edit(Profprinc profprinc);

    void remove(Profprinc profprinc);

    Profprinc find(Object id);

    List<Profprinc> findAll();

    List<Profprinc> findRange(int[] range);

    int count();

    Integer nextId();

    List<String> listeProfClass(String codeclasse);

    List<Object[]> listeProf();

    String confirmEnseignant(String mat);

    String findNomProfPrincipalByMatricule(String matricule);

}
