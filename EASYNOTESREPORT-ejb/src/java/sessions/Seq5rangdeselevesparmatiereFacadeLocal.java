/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seq5rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq5rangdeselevesparmatiereFacadeLocal {

    void create(Seq5rangdeselevesparmatiere seq5rangdeselevesparmatiere);

    void edit(Seq5rangdeselevesparmatiere seq5rangdeselevesparmatiere);

    void remove(Seq5rangdeselevesparmatiere seq5rangdeselevesparmatiere);

    Seq5rangdeselevesparmatiere find(Object id);

    List<Seq5rangdeselevesparmatiere> findAll();

    List<Seq5rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> seq5_matiere(String codeclasse);

    List<Object[]> seq5_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> seq5_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
