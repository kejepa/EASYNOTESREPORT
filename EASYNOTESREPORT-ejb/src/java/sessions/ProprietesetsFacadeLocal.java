/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Proprietesets;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface ProprietesetsFacadeLocal {

    void create(Proprietesets proprietesets);

    void edit(Proprietesets proprietesets);

    void remove(Proprietesets proprietesets);

    Proprietesets find(Object id);

    List<Proprietesets> findAll();

    List<Proprietesets> findRange(int[] range);

    int count();

    List<Object []> infosEts();

}
