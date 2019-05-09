/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Seq3moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq3moyennesrangsdeselevesFacadeLocal {

    void create(Seq3moyennesrangsdeseleves seq3moyennesrangsdeseleves);

    void edit(Seq3moyennesrangsdeseleves seq3moyennesrangsdeseleves);

    void remove(Seq3moyennesrangsdeseleves seq3moyennesrangsdeseleves);

    Seq3moyennesrangsdeseleves find(Object id);

    List<Seq3moyennesrangsdeseleves> findAll();

    List<Seq3moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Seq3moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangS3(String codeclasse);

    List<Object[]> smsMoyRangS3un(String codeclasse, String matricule);

    List<Object[]> smsMoyRangS3serie(String codeclasse, String matriculeDebut, String matriculeFin);

    List<Object[]> statparniveaus3(String section);

}
