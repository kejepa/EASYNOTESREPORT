/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conseildeclassetrim3;
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
public class Conseildeclassetrim3Facade extends AbstractFacade<Conseildeclassetrim3> implements Conseildeclassetrim3FacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Conseildeclassetrim3Facade() {
        super(Conseildeclassetrim3.class);
    }

    @Override
    public List<Listedeseleves> listeDesNouveauxEleves(String codeclasse) {
        try {
            Query query = em.createNamedQuery("Conseildeclassetrim3.findNouveauxEleves");
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
    public List<Conseildeclassetrim3> listeElevesClasse(String codeClasse) {
        try {
            Query query = em.createNamedQuery("Conseildeclassetrim3.findElevesParClasse");
            query.setParameter("codeclasse", codeClasse);
            List<Conseildeclassetrim3> list = query.getResultList();
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
    public List<Object[]> ficheStatT3(String codeclass) {
        try {
            Query query = em.createNativeQuery("select (select count(matriculeeleve) from conseildeclassetrim3 where matriculeeleve in \n"
                    + "(select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='G'))  as presentG,\n"
                    + "(select count(matriculeeleve) from conseildeclassetrim3 where matriculeeleve in \n"
                    + "(select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='F'))  as presentF,\n"
                    + "(select count(matriculeeleve) from conseildeclassetrim3 where decision like '%Admis%' and matriculeeleve in \n"
                    + "(select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='G'))  as admisG,\n"
                    + "(select count(matriculeeleve) from conseildeclassetrim3 where decision like '%Admis%' and matriculeeleve in \n"
                    + "(select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='F'))  as admisF\n"
                    + "from conseildeclassetrim3 limit 1");
            query.setParameter(1, codeclass);
            query.setParameter(2, codeclass);
            query.setParameter(3, codeclass);
            query.setParameter(4, codeclass);
            List<Object[]> falist = query.getResultList();
            if (falist.isEmpty()) {
                return null;
            } else {
                return falist;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
