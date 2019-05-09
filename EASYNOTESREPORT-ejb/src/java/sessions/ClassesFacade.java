/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Classes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KENFACK JP
 */
@Stateless
public class ClassesFacade extends AbstractFacade<Classes> implements ClassesFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassesFacade() {
        super(Classes.class);
    }
    @Override
    public boolean rechercheClasseParCode(String code) {
        try {
            Query query = em.createNamedQuery("Classes.findByCodeclasse");
            query.setParameter("codeclasse", code);
            Classes user = (Classes) query.getSingleResult();
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    } 
}
