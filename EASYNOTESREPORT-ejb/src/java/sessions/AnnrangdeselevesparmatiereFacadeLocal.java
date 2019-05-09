/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annrangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface AnnrangdeselevesparmatiereFacadeLocal {

    void create(Annrangdeselevesparmatiere annrangdeselevesparmatiere);

    void edit(Annrangdeselevesparmatiere annrangdeselevesparmatiere);

    void remove(Annrangdeselevesparmatiere annrangdeselevesparmatiere);

    Annrangdeselevesparmatiere find(Object id);

    List<Annrangdeselevesparmatiere> findAll();

    List<Annrangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> ann_matiere(String codeclasse);

    List<Object[]> ann_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> ann_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
