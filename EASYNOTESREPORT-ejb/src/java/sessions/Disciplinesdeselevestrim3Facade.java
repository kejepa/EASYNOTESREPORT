/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevestrim3;
import entities.Listedeseleves;
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
public class Disciplinesdeselevestrim3Facade extends AbstractFacade<Disciplinesdeselevestrim3> implements Disciplinesdeselevestrim3FacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Disciplinesdeselevestrim3Facade() {
        super(Disciplinesdeselevestrim3.class);
    }

    @Override
    public List<Listedeseleves> listeDesNouveauxEleves(String codeclasse) {
        try {
            Query query = em.createNamedQuery("Disciplinesdeselevestrim3.findNouveauxEleves");
            query.setParameter("codeclasse", codeclasse);
            List<Listedeseleves> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Disciplinesdeselevestrim3> listeElevesClasse(String codeClasse) {
        try {
            Query query = em.createNamedQuery("Disciplinesdeselevestrim3.findElevesParClasse");
            query.setParameter("codeclasse", codeClasse);
            List<Disciplinesdeselevestrim3> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object[]> liesteEleveDiscip5_6(String codeclasse) {
        Query query = em.createNativeQuery("select d1.matriculeeleve,d1.absb as b1,d1.absj as j1,d1.absn as n1,d1.consigne as c1,d1.exclusion as e1,\n"
                + "                d2.absb as b2,d2.absj as j2,d2.absn as n2,d2.consigne as c2,d2.exclusion as e2\n"
                + "                from disciplinesdeselevesseq5 d1\n"
                + "                inner join disciplinesdeselevesseq6 d2 on d2.matriculeeleve=d1.matriculeeleve\n"
                + "                where d1.matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse = ?1)");
        query.setParameter(1, codeclasse);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }

   @Override
    public boolean existeEleve(String matricule) {
        try {
            Query query = em.createNamedQuery("Disciplinesdeselevestrim3.findeleve");
            query.setParameter("matricule", matricule);
            List<Disciplinesdeselevestrim3> list = query.getResultList();
            if (list.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
