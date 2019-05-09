/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Trim2rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Trim2rangdeselevesparmatiereFacadeLocal {

    void create(Trim2rangdeselevesparmatiere trim2rangdeselevesparmatiere);

    void edit(Trim2rangdeselevesparmatiere trim2rangdeselevesparmatiere);

    void remove(Trim2rangdeselevesparmatiere trim2rangdeselevesparmatiere);

    Trim2rangdeselevesparmatiere find(Object id);

    List<Trim2rangdeselevesparmatiere> findAll();

    List<Trim2rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> trim2_matiere(String codeclasse);

    List<Object[]> trim2_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> trim2_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
