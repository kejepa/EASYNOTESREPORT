/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Disciplinesdeselevesseq1;
import entities.Listedeseleves;
import entities.Listepassword;
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
public class Disciplinesdeselevesseq1Facade extends AbstractFacade<Disciplinesdeselevesseq1> implements Disciplinesdeselevesseq1FacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Disciplinesdeselevesseq1Facade() {
        super(Disciplinesdeselevesseq1.class);
    }

    @Override
    public List<Listedeseleves> listeDesNouveauxEleves(String codeclasse) {
        try {
            Query query = em.createNamedQuery("Disciplinesdeselevesseq1.findNouveauxEleves");
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
    public List<Disciplinesdeselevesseq1> listeElevesClasse(String codeClasse) {
        try {
            Query query = em.createNamedQuery("Disciplinesdeselevesseq1.findElevesParClasse");
            query.setParameter("codeclasse", codeClasse);
            List<Disciplinesdeselevesseq1> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //recherche d'un enseignant par son mot de passe
    @Override
    public boolean findEnseignatByPwd(String password) {
        try {
            Listepassword u = em.createNamedQuery("Listepassword.findByPwd", Listepassword.class).setParameter("password", password).getSingleResult();
            if (u != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
