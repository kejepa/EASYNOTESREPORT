/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conseildeclassetrim2;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Conseildeclassetrim2FacadeLocal {

    void create(Conseildeclassetrim2 conseildeclassetrim2);

    void edit(Conseildeclassetrim2 conseildeclassetrim2);

    void remove(Conseildeclassetrim2 conseildeclassetrim2);

    Conseildeclassetrim2 find(Object id);

    List<Conseildeclassetrim2> findAll();

    List<Conseildeclassetrim2> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);
    
    List<Conseildeclassetrim2> listeElevesClasse(String codeClasse);
    
    List<Object[]> ficheStatT2(String codeclass);

}
