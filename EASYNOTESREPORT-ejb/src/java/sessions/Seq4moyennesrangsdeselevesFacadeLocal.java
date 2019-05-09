/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Seq4moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq4moyennesrangsdeselevesFacadeLocal {

    void create(Seq4moyennesrangsdeseleves seq4moyennesrangsdeseleves);

    void edit(Seq4moyennesrangsdeseleves seq4moyennesrangsdeseleves);

    void remove(Seq4moyennesrangsdeseleves seq4moyennesrangsdeseleves);

    Seq4moyennesrangsdeseleves find(Object id);

    List<Seq4moyennesrangsdeseleves> findAll();

    List<Seq4moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Seq4moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangS4(String codeclasse);

    List<Object[]> smsMoyRangS4un(String codeclasse, String matricule);

    List<Object[]> smsMoyRangS4serie(String codeclasse, String matriculeDebut, String matriculeFin);

    List<Object[]> statparniveaus4(String section);

}
