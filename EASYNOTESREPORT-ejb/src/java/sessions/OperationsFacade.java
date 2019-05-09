/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Operations;
import entities.Utilisateurs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KENFACK JP
 */
@Stateless
public class OperationsFacade extends AbstractFacade<Operations> implements OperationsFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationsFacade() {
        super(Operations.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createNamedQuery("Operations.nextId");
            List<Integer> list = query.getResultList();
            if (list.isEmpty()) {
                return 1;
            } else {
                return list.get(0) + 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public Utilisateurs userByString(String login) {
        try {
            Query query = em.createNamedQuery("Utilisateurs.findByLoginUser");
            query.setParameter("login", login);
            Utilisateurs user = (Utilisateurs) query.getSingleResult();
            if (user == null) {
                return null;
            } else {
                return user;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteAll() {
        Query query = em.createNativeQuery("delete from operations");
        query.executeUpdate();
    }

    @Override
    public String findRoleByLogin(String login) {
        try {
            Query query = em.createNamedQuery("Utilisateurs.findRoleByLogin");
            query.setParameter("login", login);
            String role = (String) query.getSingleResult();
            if (role == null) {
                return null;
            } else {
                return role;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
