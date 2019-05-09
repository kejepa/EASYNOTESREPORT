/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seq1rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq1rangdeselevesparmatiereFacadeLocal {

    void create(Seq1rangdeselevesparmatiere seq1rangdeselevesparmatiere);

    void edit(Seq1rangdeselevesparmatiere seq1rangdeselevesparmatiere);

    void remove(Seq1rangdeselevesparmatiere seq1rangdeselevesparmatiere);

    Seq1rangdeselevesparmatiere find(Object id);

    List<Seq1rangdeselevesparmatiere> findAll();

    List<Seq1rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<Object[]> seq1_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> seq1_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    List<String> seq1_matiere(String codeclasse);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
