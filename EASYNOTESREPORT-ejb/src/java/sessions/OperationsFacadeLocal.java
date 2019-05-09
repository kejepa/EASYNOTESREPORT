/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Operations;
import entities.Utilisateurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface OperationsFacadeLocal {

    void create(Operations operations);

    void edit(Operations operations);

    void remove(Operations operations);

    Operations find(Object id);

    List<Operations> findAll();

    List<Operations> findRange(int[] range);

    int count();

    Integer nextId();

    Utilisateurs userByString(String login);

    void deleteAll();

    String findRoleByLogin(String login);
}
