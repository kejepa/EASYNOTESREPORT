/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface UtilisateursFacadeLocal {

    void create(Utilisateurs utilisateurs);

    void edit(Utilisateurs utilisateurs);

    void remove(Utilisateurs utilisateurs);

    Utilisateurs find(Object id);

    List<Utilisateurs> findAll();

    List<Utilisateurs> findRange(int[] range);

    int count();

    boolean findByLoginPwd(String login, String password);

    boolean rechercheUserParLogin(String login);

    String findNomByLoginPwd(String login, String password);

}
