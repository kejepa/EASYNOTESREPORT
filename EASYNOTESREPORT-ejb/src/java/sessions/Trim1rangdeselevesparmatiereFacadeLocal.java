/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Trim1rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Trim1rangdeselevesparmatiereFacadeLocal {

    void create(Trim1rangdeselevesparmatiere trim1rangdeselevesparmatiere);

    void edit(Trim1rangdeselevesparmatiere trim1rangdeselevesparmatiere);

    void remove(Trim1rangdeselevesparmatiere trim1rangdeselevesparmatiere);

    Trim1rangdeselevesparmatiere find(Object id);

    List<Trim1rangdeselevesparmatiere> findAll();

    List<Trim1rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> trim1_matiere(String codeclasse);

    List<Object[]> trim1_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> trim1_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
