/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Trim1moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Trim1moyennesrangsdeselevesFacadeLocal {

    void create(Trim1moyennesrangsdeseleves trim1moyennesrangsdeseleves);

    void edit(Trim1moyennesrangsdeseleves trim1moyennesrangsdeseleves);

    void remove(Trim1moyennesrangsdeseleves trim1moyennesrangsdeseleves);

    Trim1moyennesrangsdeseleves find(Object id);

    List<Trim1moyennesrangsdeseleves> findAll();

    List<Trim1moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Trim1moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangt1(String codeclasse);

    List<Object[]> smsMoyRangtun1(String codeclasse, String matricule);

    List<Object[]> smsMoyRangtserie1(String codeclasse, String matriculeDebut, String matriculeFin);

    List<Object[]> statparniveaut1(String section);

}
