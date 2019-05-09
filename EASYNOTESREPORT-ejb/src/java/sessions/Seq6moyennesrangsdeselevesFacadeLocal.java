/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Seq6moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq6moyennesrangsdeselevesFacadeLocal {

    void create(Seq6moyennesrangsdeseleves seq6moyennesrangsdeseleves);

    void edit(Seq6moyennesrangsdeseleves seq6moyennesrangsdeseleves);

    void remove(Seq6moyennesrangsdeseleves seq6moyennesrangsdeseleves);

    Seq6moyennesrangsdeseleves find(Object id);

    List<Seq6moyennesrangsdeseleves> findAll();

    List<Seq6moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Seq6moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangS6(String codeclasse);

    List<Object[]> smsMoyRangS6un(String codeclasse, String matricule);

    List<Object[]> smsMoyRangS6serie(String codeclasse, String matriculeDebut, String matriculeFin);
    
    List<Object[]> statparniveaus6(String section);

}
