/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seq3rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq3rangdeselevesparmatiereFacadeLocal {

    void create(Seq3rangdeselevesparmatiere seq3rangdeselevesparmatiere);

    void edit(Seq3rangdeselevesparmatiere seq3rangdeselevesparmatiere);

    void remove(Seq3rangdeselevesparmatiere seq3rangdeselevesparmatiere);

    Seq3rangdeselevesparmatiere find(Object id);

    List<Seq3rangdeselevesparmatiere> findAll();

    List<Seq3rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> seq3_matiere(String codeclasse);

    List<Object[]> seq3_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> seq3_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
