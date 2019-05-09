/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Notesminmaxgenparmat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface NotesminmaxgenparmatFacadeLocal {

    void create(Notesminmaxgenparmat notesminmaxgenparmat);

    void edit(Notesminmaxgenparmat notesminmaxgenparmat);

    void remove(Notesminmaxgenparmat notesminmaxgenparmat);

    Notesminmaxgenparmat find(Object id);

    List<Notesminmaxgenparmat> findAll();

    List<Notesminmaxgenparmat> findRange(int[] range);

    int count();

    List<Object[]> note_min_max_moy_par_matiereSeq1(String codeclasse);
    
    boolean existe_classe_et_sequence_et_matiere(String codeclasse, String sequence, String matiere);
    
    List<Object[]> note_min_max_moy_par_matiereSeq2(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereSeq3(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereSeq4(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereSeq5(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereSeq6(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereTrim1(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereTrim2(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereTrim3(String codeclasse);
    
    List<Object[]> note_min_max_moy_par_matiereAnn(String codeclasse);

}
