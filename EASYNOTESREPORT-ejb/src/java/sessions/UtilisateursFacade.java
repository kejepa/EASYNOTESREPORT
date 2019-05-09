/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

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
public class UtilisateursFacade extends AbstractFacade<Utilisateurs> implements UtilisateursFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateursFacade() {
        super(Utilisateurs.class);
    }

    @Override
    public boolean findByLoginPwd(String login, String password) {
        try {
//            Utilisateurs u = em.createNamedQuery("Utilisateurs.findByLoginPwd", Utilisateurs.class).setParameter("login", login).setParameter("password", password).getSingleResult();
//            if (u != null) {
//                return true;
//            }
//            return false;
//        } catch (Exception e) { 
//            return false;
//        }  
            Query query = em.createNativeQuery("SELECT * FROM utilisateurs WHERE password = ?1 and login = ?2");
            query.setParameter(1, password);
            query.setParameter(2, login);
            List<Utilisateurs> u = query.getResultList();
            if (!u.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean rechercheUserParLogin(String login) {
        try {
            Query query = em.createNamedQuery("Utilisateurs.findByLogin");
            query.setParameter("login", login);
            Utilisateurs user = (Utilisateurs) query.getSingleResult();
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String findNomByLoginPwd(String login, String password) {
        Query query;
        query = em.createNativeQuery("select login from utilisateurs where login=?1 and password=?2");
        query.setParameter(1, login);
        query.setParameter(2, password);
        List<String> nomList;
        nomList = query.getResultList();
        if (nomList.isEmpty()) {
            return null;
        } else {
            return nomList.get(0);
        }
    }
}
