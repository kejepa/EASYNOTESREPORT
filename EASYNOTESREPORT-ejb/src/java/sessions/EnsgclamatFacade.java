/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Classes;
import entities.Ensgclamat;
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
public class EnsgclamatFacade extends AbstractFacade<Ensgclamat> implements EnsgclamatFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnsgclamatFacade() {
        super(Ensgclamat.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createNamedQuery("Ensgclamat.nextId");
            List<Integer> list = query.getResultList();
            if (list.isEmpty()) {
                return 1;
            } else {
                return list.get(0) + 1;
            }
        } catch (Exception e) {
            return 1;
        }
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
    public List<Matieres> listedesMatieres(String codeclasse) {
        try {
            Query query = em.createNamedQuery("Clamatcoeffgrpe.findMatiere");
            query.setParameter("codeclasse", codeclasse);
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

    @Override
    public List<String> listedesPersonnels(String codematiere) {
        try {
            Query query = em.createNativeQuery("SELECT concat(nom,' ',prenom,'(',matricule,')') from personnels where specialite = (select codespecialite from matieres where codematiere= ?1)");
            query.setParameter(1, codematiere);
            List<String> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }

//    @Override
//    public String confirmEnseignant(String mat) {
//        Query query = em.createNamedQuery("Personnels.findMatNomPreGradSpecia");
//        query.setParameter("matricule", mat);
//        List<Personnels> plist = query.getResultList();
//        Iterator i = plist.iterator();
//        Personnels p;
//        String m = "";
//        String n = "";
//        String pr = "";
//        String g = "";
//        String s = "";
//
//        while (i.hasNext()) {
//            p = (Personnels) i.next();
//            m = (p.getMatricule());
//            n = (p.getNom());
//            pr = (p.getPrenom());
//            g = (p.getGrade());
//            s = (p.getSpecialite());
//        }
//        return m + ": " + n + " " + pr + " " + g + " " + s;
//    }
    //verifier si une matière est attribuée à un enseignant dans d'une classe
    @Override
    public boolean matiereEnseignatClasseExixteDeja(String classe, String codematiere) {
        try {
            Query query = em.createNamedQuery("Ensgclamat.findMatiere_Enseignant");
            query.setParameter("codeclasse", classe);
            query.setParameter("codemartiere", codematiere);
            Ensgclamat enseig = (Ensgclamat) query.getSingleResult();
            if (enseig != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String rechercheEnseignantParMatricule(String mat) {
        try {
            Query query = em.createNativeQuery("SELECT concat(nom,' ',prenom,'(',matricule,')') from personnels where matricule=?1");
            query.setParameter(1, mat);
            List<String> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
