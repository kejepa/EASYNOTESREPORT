/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Specialite;
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
public class SpecialiteFacade extends AbstractFacade<Specialite> implements SpecialiteFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpecialiteFacade() {
        super(Specialite.class);
    }

    @Override
    public boolean rechercheSpecialiteParCode(String code) {
        try {
            Query query = em.createNamedQuery("Specialite.findByCodespecialite");
            query.setParameter("codespecialite", code);
            Specialite sp = (Specialite) query.getSingleResult();
            if (sp != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> listeDesSpecialites() {
        try {
            Query query = em.createNativeQuery("select codespecialite from Specialite");
            if (query.getResultList() != null) {
                return query.getResultList();
            } else {
                return null; 
            }
        } catch (Exception e) {
            return null;
        }
    }
}
