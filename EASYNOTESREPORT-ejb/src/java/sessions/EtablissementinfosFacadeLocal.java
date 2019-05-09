/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etablissementinfos;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface EtablissementinfosFacadeLocal {

    void create(Etablissementinfos etablissementinfos);

    void edit(Etablissementinfos etablissementinfos);

    void remove(Etablissementinfos etablissementinfos);

    Etablissementinfos find(Object id);

    List<Etablissementinfos> findAll();

    List<Etablissementinfos> findRange(int[] range);

    int count();

    BigDecimal acti_deative_seq();

    List<Etablissementinfos> valeursinfos();

    BigDecimal borneAvertissement();

    BigDecimal borneBlame();

}
