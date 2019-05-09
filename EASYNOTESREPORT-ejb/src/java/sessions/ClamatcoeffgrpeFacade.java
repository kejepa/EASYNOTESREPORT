/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Clamatcoeffgrpe;
import entities.Classes;
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
public class ClamatcoeffgrpeFacade extends AbstractFacade<Clamatcoeffgrpe> implements ClamatcoeffgrpeFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClamatcoeffgrpeFacade() {
        super(Clamatcoeffgrpe.class);
    }

    @Override
    public List<Classes> listedesClasses() {  
        try {
            Query query = em.createNamedQuery("Classes.findCodeclasse");
            List<Classes> list = query.getResultList();
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
    public List<Classes> listedesClasses_clone() {
        try {
            Query query = em.createNamedQuery("Clamatcoeffgrpe.findClasse_clone");
            List<Classes> list = query.getResultList();
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
    public List<Object[]> classeMatiere_clone(String codeclasse) {
        try {
            Query query = em.createNamedQuery("Clamatcoeffgrpe.cloneclasse");
            query.setParameter("codeclasse", codeclasse);
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

    @Override
    public List<Matieres> listedesMatieres() {
        try {
            Query query = em.createNativeQuery("SELECT codematiere FROM matieres  ORDER BY codematiere");
            List<Matieres> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //verifier si une matière est attribuée à une classe
    @Override
    public boolean matiereMatiereClasseExixteDeja(String classe, String codematiere) {
        try {
            Query query = em.createNamedQuery("Clamatcoeffgrpe.findMatiere_Classe");
            query.setParameter("codeclasse", classe);
            query.setParameter("codemartiere", codematiere);
            Clamatcoeffgrpe matClasse = (Clamatcoeffgrpe) query.getSingleResult();
            if (matClasse != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Object[]> liteaffactationParClasse(String codeclasse) {
        try {
            Query query = em.createNativeQuery("select codematiere,coefficient,groupe from clamatcoeffgrpe where codeclasse=?1\n"
                    + "order by groupe,codematiere");
            query.setParameter(1, codeclasse);
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
