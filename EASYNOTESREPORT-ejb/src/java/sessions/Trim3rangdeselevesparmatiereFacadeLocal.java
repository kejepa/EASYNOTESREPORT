/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Trim3rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Trim3rangdeselevesparmatiereFacadeLocal {

    void create(Trim3rangdeselevesparmatiere trim3rangdeselevesparmatiere);

    void edit(Trim3rangdeselevesparmatiere trim3rangdeselevesparmatiere);

    void remove(Trim3rangdeselevesparmatiere trim3rangdeselevesparmatiere);

    Trim3rangdeselevesparmatiere find(Object id);

    List<Trim3rangdeselevesparmatiere> findAll();

    List<Trim3rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> trim3_matiere(String codeclasse);

    List<Object[]> trim3_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> trim3_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
