/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annmoyennesrangsdeseleves;
import entities.Listedeseleves;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface AnnmoyennesrangsdeselevesFacadeLocal {

    void create(Annmoyennesrangsdeseleves annmoyennesrangsdeseleves);

    void edit(Annmoyennesrangsdeseleves annmoyennesrangsdeseleves);

    void remove(Annmoyennesrangsdeseleves annmoyennesrangsdeseleves);

    Annmoyennesrangsdeseleves find(Object id);

    List<Annmoyennesrangsdeseleves> findAll();

    List<Annmoyennesrangsdeseleves> findRange(int[] range);

    int count();

    List<Object[]> moyenne_de_l_eleve(String matricule);

    List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse);

    List<Annmoyennesrangsdeseleves> verifier_si_existe(String matricule);

    List<String> lite_des_groupes_matiere(String codeclasse);

    List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int goupe);

    List<Object[]> ordreDeMeriteDesEleves(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse);

    List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse);

    List<Object[]> smsMoyRangan(String codeclasse);

    List<Object[]> smsMoyRanganun(String codeclasse, String matricule);

    List<Object[]> smsMoyRanganserie(String codeclasse, String matriculeDebut, String matriculeFin);

    List<Object[]> statparniveauann(String section);

    List<Annmoyennesrangsdeseleves> liste_des_eleves_admis(String codeclasse, BigDecimal moyenne);
}
