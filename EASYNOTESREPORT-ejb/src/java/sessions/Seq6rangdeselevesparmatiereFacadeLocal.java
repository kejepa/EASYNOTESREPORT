/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seq6rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq6rangdeselevesparmatiereFacadeLocal {

    void create(Seq6rangdeselevesparmatiere seq6rangdeselevesparmatiere);

    void edit(Seq6rangdeselevesparmatiere seq6rangdeselevesparmatiere);

    void remove(Seq6rangdeselevesparmatiere seq6rangdeselevesparmatiere);

    Seq6rangdeselevesparmatiere find(Object id);

    List<Seq6rangdeselevesparmatiere> findAll();

    List<Seq6rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> seq6_matiere(String codeclasse);

    List<Object[]> seq6_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> seq6_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
