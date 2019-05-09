/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conseildeclassetrim1;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Conseildeclassetrim1FacadeLocal {

    void create(Conseildeclassetrim1 conseildeclassetrim1);

    void edit(Conseildeclassetrim1 conseildeclassetrim1);

    void remove(Conseildeclassetrim1 conseildeclassetrim1);

    Conseildeclassetrim1 find(Object id);

    List<Conseildeclassetrim1> findAll();

    List<Conseildeclassetrim1> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);
    
    List<Conseildeclassetrim1> listeElevesClasse(String codeClasse);
    
    List<Object[]> ficheStatT1(String codeclass);
}
