/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Trim2moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Trim2moyennesrangsdeselevesFacadeLocal {

    void create(Trim2moyennesrangsdeseleves trim2moyennesrangsdeseleves);

    void edit(Trim2moyennesrangsdeseleves trim2moyennesrangsdeseleves);

    void remove(Trim2moyennesrangsdeseleves trim2moyennesrangsdeseleves);

    Trim2moyennesrangsdeseleves find(Object id);

    List<Trim2moyennesrangsdeseleves> findAll();

    List<Trim2moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Trim2moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangt2(String codeclasse);

    List<Object[]> smsMoyRangtun2(String codeclasse, String matricule);

    List<Object[]> smsMoyRangtserie2(String codeclasse, String matriculeDebut, String matriculeFin);

    List<Object[]> statparniveaut2(String section);

}
