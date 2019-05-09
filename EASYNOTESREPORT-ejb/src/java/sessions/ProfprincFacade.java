/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personnels;
import entities.Profprinc;
import java.util.Iterator;
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
public class ProfprincFacade extends AbstractFacade<Profprinc> implements ProfprincFacadeLocal {
    
    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ProfprincFacade() {
        super(Profprinc.class);
    }
    
    @Override
    public Integer nextId() {
        try {
            Query query = em.createNamedQuery("Profprinc.nextId");
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
    public List<Object[]> listeProf() {
        try {
            Query query = em.createNamedQuery("Ensgclamat.findMatEn");
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
    public List<String> listeProfClass(String codeclasse) {
        try {
            Query query = em.createNativeQuery("select concat(nom,' ',prenom,' \"',grade,' ',specialite,'\"','(',matricule,')') as nomeleve \n"
                    + "from personnels where matricule in(select matriculeenseignant from ensgclamat where codeclasse = ?1)\n"
                    + "order by nom"); 
            query.setParameter(1, codeclasse);
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
    
    @Override
    public String confirmEnseignant(String mat) {
        Query query = em.createNamedQuery("Personnels.findMatNomPreGradSpecia");
        query.setParameter("matricule", mat);
        List<Personnels> plist = query.getResultList();
        Iterator i = plist.iterator();
        Personnels p;
        String m = "";
        String n = "";
        String pr = "";
        String g = "";
        String s = "";
        
        while (i.hasNext()) {
            p = (Personnels) i.next();
            m = (p.getMatricule());
            n = (p.getNom());
            pr = (p.getPrenom());
            g = (p.getGrade());
            s = (p.getSpecialite());
        }
        return m + ": " + n + " " + pr + " " + g + " " + s;
    }

    @Override
    public String findNomProfPrincipalByMatricule(String matricule) {
        try {
            Query query = em.createNativeQuery("select concat(nom,' ',prenom,'(',matricule,')') as nomepp from personnels where matricule=?1");
            query.setParameter(1, matricule);
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
