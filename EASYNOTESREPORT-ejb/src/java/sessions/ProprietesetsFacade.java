/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Proprietesets;
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
public class ProprietesetsFacade extends AbstractFacade<Proprietesets> implements ProprietesetsFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProprietesetsFacade() {
        super(Proprietesets.class);
    }

    @Override
    public List<Object []> infosEts() {
        Query query = em.createNativeQuery("SELECT * from proprietesets");
        return query.getResultList();
    }
}
