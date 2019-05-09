/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seq4rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq4rangdeselevesparmatiereFacadeLocal {

    void create(Seq4rangdeselevesparmatiere seq4rangdeselevesparmatiere);

    void edit(Seq4rangdeselevesparmatiere seq4rangdeselevesparmatiere);

    void remove(Seq4rangdeselevesparmatiere seq4rangdeselevesparmatiere);

    Seq4rangdeselevesparmatiere find(Object id);

    List<Seq4rangdeselevesparmatiere> findAll();

    List<Seq4rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> seq4_matiere(String codeclasse);

    List<Object[]> seq4_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> seq4_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
