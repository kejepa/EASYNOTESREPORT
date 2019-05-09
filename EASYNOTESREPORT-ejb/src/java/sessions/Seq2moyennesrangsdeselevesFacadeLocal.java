/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Seq2moyennesrangsdeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq2moyennesrangsdeselevesFacadeLocal {

    void create(Seq2moyennesrangsdeseleves seq2moyennesrangsdeseleves);

    void edit(Seq2moyennesrangsdeseleves seq2moyennesrangsdeseleves);

    void remove(Seq2moyennesrangsdeseleves seq2moyennesrangsdeseleves);

    Seq2moyennesrangsdeseleves find(Object id);

    List<Seq2moyennesrangsdeseleves> findAll();

    List<Seq2moyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Seq2moyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangS2(String codeclasse);

    List<Object[]> smsMoyRangS2un(String codeclasse, String matricule);

    List<Object[]> smsMoyRangS2serie(String codeclasse, String matriculeDebut, String matriculeFin);

    List<Object[]> statparniveaus2(String section);
}
