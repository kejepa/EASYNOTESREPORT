/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Matieres;
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
public class MatieresFacade extends AbstractFacade<Matieres> implements MatieresFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatieresFacade() {
        super(Matieres.class);
    }

    @Override
    public boolean rechercheMatiereParCode(String code) {
        try {
            Query query = em.createNamedQuery("Matieres.findByCodematiere");
            query.setParameter("codematiere", code);
            Matieres matiere = (Matieres) query.getSingleResult();
            if (matiere != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //recherche des matieres d'une classe pour la saisie des notes par élève
    @Override
    public List<Matieres> listeMatieresParClasse(String codeclasse) {
        Query query = em.createNamedQuery("Matieres.findMatieresParClasse");
        query.setParameter("codeclasse", codeclasse);
        List<Matieres> listeMatieres = query.getResultList();
        if (listeMatieres == null) {
            return null;
        } else {
            return listeMatieres;
        }
    }
}
