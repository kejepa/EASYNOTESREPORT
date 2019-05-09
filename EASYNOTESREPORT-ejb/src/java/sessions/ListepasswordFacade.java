/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Listepassword;
import java.util.Iterator;
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
public class ListepasswordFacade extends AbstractFacade<Listepassword> implements ListepasswordFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListepasswordFacade() {
        super(Listepassword.class);
    }

    @Override
    public String Enseignat_de_la_Matiere(String pwd, String specialite) {
        try {
            Query query = em.createNamedQuery("Listepassword.findByPwd_Matiere");
            query.setParameter("password", pwd);
            query.setParameter("specialite", specialite);
            List<Listepassword> ens = query.getResultList();
            Iterator i = ens.iterator();
            Listepassword l;
            String enseignat = "";
            if (ens == null || ens.isEmpty()) {
                return "";
            } else {
                while (i.hasNext()) {
                    l = (Listepassword) i.next();
                    enseignat = l.getMatriculepersonne() + " / " + l.getNom() + " " + l.getPrenom() + " / " + l.getGrade() + " / " + l.getSpecialite();
                }
                return enseignat;
            }
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String pwdExisteDeja(String pwd) {
        Query query = em.createNativeQuery("select matriculepersonne from listepassword  where password=?1");
        query.setParameter(1, pwd);
        try {
            String list = (String) query.getSingleResult();
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Listepassword> changerMotDePasse(String pwd) {
        Query query = em.createNamedQuery("Listepassword.findByPwd");
        query.setParameter("password", pwd);
        List<Listepassword> liste = query.getResultList();
        if (liste != null) {
            return liste;
        } else {
            return null;
        }
    }
    //modifier son propre mot de passe

    @Override
    public String selfpwdExisteDeja(String pwd, String matri) {
        Query query = em.createNativeQuery("select matriculepersonne from listepassword  where password=?1 and matriculepersonne=?2");
        query.setParameter(1, pwd);
        query.setParameter(2, matri);
        try {
            String list = (String) query.getSingleResult();
            if (list != null) {
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String findpwd(String matri) {
        Query query = em.createNativeQuery("select password from listepassword  where matriculepersonne=?1");
        query.setParameter(1, matri);
        try {
            String pwd = (String) query.getSingleResult();
            if (pwd != null) {
                return pwd;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }
}
