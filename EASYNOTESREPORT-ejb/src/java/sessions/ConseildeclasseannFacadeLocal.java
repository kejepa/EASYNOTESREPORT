/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conseildeclasseann;
import entities.Listedeseleves;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface ConseildeclasseannFacadeLocal {

    void create(Conseildeclasseann conseildeclasseann);

    void edit(Conseildeclasseann conseildeclasseann);

    void remove(Conseildeclasseann conseildeclasseann);

    Conseildeclasseann find(Object id);

    List<Conseildeclasseann> findAll();

    List<Conseildeclasseann> findRange(int[] range);

    int count();

    List<Listedeseleves> listeDesNouveauxEleves(String codeclasse);

    List<Conseildeclasseann> listeElevesClasse(String codeClasse);

    List<Object[]> ficheStatAnn(String codeclass);

    String pp(String codeClasse);
}
