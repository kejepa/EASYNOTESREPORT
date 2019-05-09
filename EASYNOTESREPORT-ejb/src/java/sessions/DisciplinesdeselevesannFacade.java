/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesann;
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
public class DisciplinesdeselevesannFacade extends AbstractFacade<Disciplinesdeselevesann> implements DisciplinesdeselevesannFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DisciplinesdeselevesannFacade() {
        super(Disciplinesdeselevesann.class);
    }

    @Override
    public List<Listedeseleves> listeDesNouveauxEleves(String codeclasse) {
        try {
            Query query = em.createNamedQuery("Disciplinesdeselevesann.findNouveauxEleves");
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
    public List<Disciplinesdeselevesann> listeElevesClasse(String codeClasse) {
        try {
            Query query = em.createNamedQuery("Disciplinesdeselevesann.findElevesParClasse");
            query.setParameter("codeclasse", codeClasse);
            List<Disciplinesdeselevesann> list = query.getResultList();
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
    public List<Object[]> liesteEleveDiscip1_a_6(String codeclasse) {
        Query query = em.createNativeQuery("select d1.matriculeeleve,d1.absb as b1,d1.absj as j1,d1.absn as n1,d1.consigne as c1,d1.exclusion as e1,\n"
                + "                d2.absb as b2,d2.absj as j2,d2.absn as n2,d2.consigne as c2,d2.exclusion as e2,\n"
                + "                d3.absb as b3,d3.absj as j3,d3.absn as n3,d3.consigne as c3,d3.exclusion as e3,\n"
                + "                d2.absb as b4,d4.absj as j4,d4.absn as n4,d4.consigne as c4,d4.exclusion as e4,\n"
                + "                d3.absb as b5,d5.absj as j5,d5.absn as n5,d5.consigne as c5,d5.exclusion as e5,\n"
                + "                d2.absb as b6,d6.absj as j6,d6.absn as n6,d6.consigne as c6,d6.exclusion as e6\n"
                + "                from disciplinesdeselevesseq1 d1\n"
                + "                inner join disciplinesdeselevesseq1 d2 on d2.matriculeeleve=d1.matriculeeleve\n"
                + "                inner join disciplinesdeselevesseq1 d3 on d3.matriculeeleve=d1.matriculeeleve\n"
                + "                inner join disciplinesdeselevesseq1 d4 on d4.matriculeeleve=d1.matriculeeleve\n"
                + "                inner join disciplinesdeselevesseq1 d5 on d5.matriculeeleve=d1.matriculeeleve\n"
                + "                inner join disciplinesdeselevesseq1 d6 on d6.matriculeeleve=d1.matriculeeleve\n"
                + "                where d1.matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse=?1)");
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
            Query query = em.createNamedQuery("Disciplinesdeselevesann.findeleve");
            query.setParameter("matricule", matricule);
            List<Disciplinesdeselevesann> list = query.getResultList();
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
