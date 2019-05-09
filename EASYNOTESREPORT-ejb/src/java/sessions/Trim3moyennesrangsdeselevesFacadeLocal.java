/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Trim3moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Trim3moyennesrangsdeselevesFacadeLocal {

    void create(Trim3moyennesrangsdeseleves trim3moyennesrangsdeseleves);

    void edit(Trim3moyennesrangsdeseleves trim3moyennesrangsdeseleves);

    void remove(Trim3moyennesrangsdeseleves trim3moyennesrangsdeseleves);

    Trim3moyennesrangsdeseleves find(Object id);

    List<Trim3moyennesrangsdeseleves> findAll();

    List<Trim3moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Trim3moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangt3(String codeclasse);

    List<Object[]> smsMoyRangtun3(String codeclasse, String matricule);

    List<Object[]> smsMoyRangtserie3(String codeclasse, String matriculeDebut, String matriculeFin);

    List<Object[]> statparniveaut3(String section);

}
