/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Listepassword;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface ListedeselevesFacadeLocal {

    void create(Listedeseleves listedeseleves);

    void edit(Listedeseleves listedeseleves);

    void remove(Listedeseleves listedeseleves);

    Listedeseleves find(Object id);

    List<Listedeseleves> findAll();

    List<Listedeseleves> findRange(int[] range);

    int count();

    boolean rechercheEleveParMatricule(String matriculeeleve);

    List<Listedeseleves> liste_des_eleves_par_classe(String codeclasse);

    List<Object[]> tableauh1(String codeclass);

    List<Object[]> tableauh2(String codeclass);

    List<Object[]> tableauh3(String codeclass);

    List<Object[]> tableaue1(String codeclass);

    List<Object[]> tableaue2(String codeclass);

    List<Object[]> tableaue3(String codeclass);

    List<Object[]> tableauf1(String codeclass);

    List<Object[]> tableauf2(String codeclass);

    List<Object[]> tableauf3(String codeclass);

    List<Object[]> smsSimple(String codeclasse);

    List<Object[]> smsSimpleuneleve(String codeclasse, String matricule);

    List<Object[]> smsSimplesrieeleve(String codeclasse, String matricule, String matriculeA);

}
