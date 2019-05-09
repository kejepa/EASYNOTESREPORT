/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Seq2rangdeselevesparmatiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Seq2rangdeselevesparmatiereFacadeLocal {

    void create(Seq2rangdeselevesparmatiere seq2rangdeselevesparmatiere);

    void edit(Seq2rangdeselevesparmatiere seq2rangdeselevesparmatiere);

    void remove(Seq2rangdeselevesparmatiere seq2rangdeselevesparmatiere);

    Seq2rangdeselevesparmatiere find(Object id);

    List<Seq2rangdeselevesparmatiere> findAll();

    List<Seq2rangdeselevesparmatiere> findRange(int[] range);

    int count();

    List<String> seq2_matiere(String codeclasse);

    List<Object[]> seq2_rang_eleve_par_matiere(String codeclasse, String codematiere);

    List<Object[]> seq2_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere);

    boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse);

}
