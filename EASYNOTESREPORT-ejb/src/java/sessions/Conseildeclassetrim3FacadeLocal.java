/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conseildeclassetrim3;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface Conseildeclassetrim3FacadeLocal {

    void create(Conseildeclassetrim3 conseildeclassetrim3);

    void edit(Conseildeclassetrim3 conseildeclassetrim3);

    void remove(Conseildeclassetrim3 conseildeclassetrim3);

    Conseildeclassetrim3 find(Object id);

    List<Conseildeclassetrim3> findAll();

    List<Conseildeclassetrim3> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Conseildeclassetrim3> listeElevesClasse(String codeClasse);

    List<Object[]> ficheStatT3(String codeclass);

}
