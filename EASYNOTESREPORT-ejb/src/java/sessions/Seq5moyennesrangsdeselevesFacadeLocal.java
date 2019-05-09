/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Seq5moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq5moyennesrangsdeselevesFacadeLocal {

    void create(Seq5moyennesrangsdeseleves seq5moyennesrangsdeseleves);

    void edit(Seq5moyennesrangsdeseleves seq5moyennesrangsdeseleves);

    void remove(Seq5moyennesrangsdeseleves seq5moyennesrangsdeseleves);

    Seq5moyennesrangsdeseleves find(Object id);

    List<Seq5moyennesrangsdeseleves> findAll();

    List<Seq5moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Seq5moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangS5(String codeclasse);

    List<Object[]> smsMoyRangS5un(String codeclasse, String matricule);

    List<Object[]> smsMoyRangS5serie(String codeclasse, String matriculeDebut, String matriculeFin);
    
    List<Object[]> statparniveaus5(String section);

}
