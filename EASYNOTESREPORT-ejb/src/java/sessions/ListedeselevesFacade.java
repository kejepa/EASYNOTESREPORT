/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

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
public class ListedeselevesFacade extends AbstractFacade<Listedeseleves> implements ListedeselevesFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListedeselevesFacade() {
        super(Listedeseleves.class);
    }

    @Override
    public boolean rechercheEleveParMatricule(String matriculeeleve) {
        try {
            Query query = em.createNamedQuery("Listedeseleves.findByMatriculeeleve");
            query.setParameter("matriculeeleve", matriculeeleve);
            Listedeseleves user = (Listedeseleves) query.getSingleResult();
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
//liste des élèves pour la saisie par élève

    @Override
    public List<Listedeseleves> liste_des_eleves_par_classe(String codeclasse) {
        Query query = em.createNamedQuery("Listedeseleves.findMatriculeeleve");
        query.setParameter("codeclasse", codeclasse);
        List<Listedeseleves> liste = query.getResultList();
        return liste;
    }

    @Override
    public List<Object[]> tableauh1(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim1moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 12 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableauh2(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim2moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 12 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableauh3(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim3moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 12 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableaue1(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim1moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 14 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableaue2(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim2moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 14 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableaue3(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim3moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 14 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableauf1(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim1moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 16 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableauf2(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim2moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 16 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> tableauf3(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT l.nom, l.prenom, l.codeclasse,s.moyenne\n"
                    + "from listedeseleves l\n"
                    + "inner join trim3moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1 and s.moyenne between 16 and 20");
            query.setParameter(1, codeclass);
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
    public List<Object[]> smsSimple(String codeclasse) {
        Query query;
        query = em.createNativeQuery("select concat(nom,'',prenom)as nomeleve,adresse \n"
                + "from listedeseleves\n"
                + "where codeclasse=?1 and adresse is not null");
        query.setParameter(1, codeclasse);
        List<Object[]> listeg3 = query.getResultList();
        return listeg3;
    }

    @Override
    public List<Object[]> smsSimpleuneleve(String codeclasse, String matricule) {
        Query query;
        query = em.createNativeQuery("select concat(nom,'',prenom)as nomeleve,adresse \n"
                + "from listedeseleves\n"
                + "where codeclasse=?1 and adresse is not null and matriculeeleve=?2");
        query.setParameter(1, codeclasse);
        query.setParameter(2, matricule);
        List<Object[]> listeg3 = query.getResultList();
        return listeg3;
    }

    @Override
    public List<Object[]> smsSimplesrieeleve(String codeclasse, String matricule, String matriculeA) {
        Query query;
        query = em.createNativeQuery("select concat(nom,'',prenom)as nomeleve,adresse \n"
                + "from listedeseleves\n"
                + "where codeclasse=?1 and adresse is not null and matriculeeleve between ?2 and ?3");
        query.setParameter(1, codeclasse);
        query.setParameter(2, matricule);
        query.setParameter(3, matriculeA);
        List<Object[]> listeg3 = query.getResultList();
        return listeg3;
    }
}
