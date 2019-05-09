/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Seq1moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq1moyennesrangsdeselevesFacadeLocal {

    void create(Seq1moyennesrangsdeseleves seq1moyennesrangsdeseleves);

    void edit(Seq1moyennesrangsdeseleves seq1moyennesrangsdeseleves);

    void remove(Seq1moyennesrangsdeseleves seq1moyennesrangsdeseleves);

    Seq1moyennesrangsdeseleves find(Object id);

    List<Seq1moyennesrangsdeseleves> findAll();

    List<Seq1moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Seq1moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangS1(String codeclasse);

    List<Object[]> smsMoyRangS1un(String codeclasse, String matricule);

    List<Object[]> smsMoyRangS1serie(String codeclasse, String matriculeDebut, String matriculeFin);
    
    List<Object[]> statparniveaus1(String section);
}
