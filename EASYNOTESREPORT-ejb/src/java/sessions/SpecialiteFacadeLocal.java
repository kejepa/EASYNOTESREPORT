/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Specialite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface SpecialiteFacadeLocal {

    void create(Specialite specialite);

    void edit(Specialite specialite);

    void remove(Specialite specialite);

    Specialite find(Object id);

    List<Specialite> findAll();

    List<Specialite> findRange(int[] range);

    int count();

    boolean rechercheSpecialiteParCode(String code);

    List<String> listeDesSpecialites();
}
