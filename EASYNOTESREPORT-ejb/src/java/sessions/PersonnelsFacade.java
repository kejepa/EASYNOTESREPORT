/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personnels;
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
public class PersonnelsFacade extends AbstractFacade<Personnels> implements PersonnelsFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelsFacade() {
        super(Personnels.class);
    }

    @Override
    public boolean recherchePersonnelParMatricule(String matriculepersonnel) {
        try {
            Query query = em.createNamedQuery("Personnels.findByMatricule");
            query.setParameter("matricule", matriculepersonnel);
            Personnels person = (Personnels) query.getSingleResult();
            if (person != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //approvisionner le selectOneMenu de spécialité d'un personnel Matières de la classe
    @Override
    public List<Object[]> listedesMatieresParClasse() {
        try {
            Query query = em.createNamedQuery("Clamatcoeffgrpe.findByMatieres");
            List<Object[]> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
