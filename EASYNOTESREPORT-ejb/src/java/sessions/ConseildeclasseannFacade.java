/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Conseildeclasseann;
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
public class ConseildeclasseannFacade extends AbstractFacade<Conseildeclasseann> implements ConseildeclasseannFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConseildeclasseannFacade() {
        super(Conseildeclasseann.class);
    }

    @Override
    public List<Listedeseleves> listeDesNouveauxEleves(String codeclasse) {
        try {
            Query query = em.createNamedQuery("Conseildeclasseann.findNouveauxEleves");
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
    public List<Conseildeclasseann> listeElevesClasse(String codeClasse) {
        try {
            Query query = em.createNamedQuery("Conseildeclasseann.findElevesParClasse");
            query.setParameter("codeclasse", codeClasse);
            List<Conseildeclasseann> list = query.getResultList();
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
    public List<Object[]> ficheStatAnn(String codeclass) {
        try {
            Query query = em.createNativeQuery("select (select count(matriculeeleve) from conseildeclasseann where decision like '%Admis%' and matriculeeleve in \n"
                    + "                    (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='G'))  as admisG,\n"
                    + "                    (select count(matriculeeleve) from conseildeclasseann where decision like '%Admis%' and matriculeeleve in \n"
                    + "                    (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='F'))  as admisF,\n"
                    + "                    (select count(matriculeeleve) from conseildeclasseann where decision like '%Redoubl%' and matriculeeleve in \n"
                    + "                    (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='G'))  as redouG,\n"
                    + "                    (select count(matriculeeleve) from conseildeclasseann where decision like '%Redoubl%' and matriculeeleve in \n"
                    + "                    (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='F'))  as redouF,\n"
                    + "                    (select count(matriculeeleve) from conseildeclasseann where decision like '%Exclu%' and matriculeeleve in \n"
                    + "                    (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='G'))  as excluG,\n"
                    + "                    (select count(matriculeeleve) from conseildeclasseann where decision like '%Exclu%' and matriculeeleve in \n"
                    + "                    (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='F'))  as excluF\n"
                    + "                    from conseildeclasseann limit 1");
            query.setParameter(1, codeclass);
            query.setParameter(2, codeclass);
            query.setParameter(3, codeclass);
            query.setParameter(4, codeclass);
            query.setParameter(6, codeclass);
            query.setParameter(5, codeclass);
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
    @Override
     public String pp(String codeClasse) {
        try {
            Query query = em.createNativeQuery("select concat(nom,' ',prenom) from personnels where matricule=(select matriculeenseignant from profprinc where codeclasse=?1)");
            query.setParameter(1, codeClasse);
            String nomPP = (String) query.getSingleResult();
            if (nomPP.isEmpty()) {
                return "";
            } else {
                return nomPP;
            }
        } catch (Exception e) {
            return "";
        }
    }
}
