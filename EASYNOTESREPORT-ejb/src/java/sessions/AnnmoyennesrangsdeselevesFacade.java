/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annmoyennesrangsdeseleves;
import entities.Listedeseleves;
import java.math.BigDecimal;
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
public class AnnmoyennesrangsdeselevesFacade extends AbstractFacade<Annmoyennesrangsdeseleves> implements AnnmoyennesrangsdeselevesFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnnmoyennesrangsdeselevesFacade() {
        super(Annmoyennesrangsdeseleves.class);
    }

    @Override
    public List<Listedeseleves> findMatriculePourCalculMoyenne(String codeClasse) {
        Query query;
        query = em.createNamedQuery("Listedeseleves.findMatriculeeleve");
        query.setParameter("codeclasse", codeClasse);
        List<Listedeseleves> eleves_de_la_classe = query.getResultList();
        if (eleves_de_la_classe == null) {
            return null;
        } else {
            return eleves_de_la_classe;
        }
    }

    @Override
    public List<Object[]> findMatriculePourCalculMoyenneParGroupe(String matricule, int groupe) {
        try {
            Query query;
            String sql = "select cl.coefficient,(n.lanote*cl.coefficient) as notecoef \n"
                    + "from classes c \n"
                    + "inner join listedeseleves l on c.codeclasse = l.codeclasse \n"
                    + "inner join notesdeselevesann n on l.matriculeeleve = n.matriculeeleve \n"
                    + "inner join matieres m on m.codematiere = n.codematiere \n"
                    + "inner join ClaMatCoeffGrpe cl on cl.codeclasse = c.codeclasse and cl.codematiere = m.codematiere \n"
                    + "where l.matriculeeleve = ?1 and cl.groupe= ?2 \n"
                    + "group by c.codeclasse,l.matriculeeleve,m.codematiere,n.lanote,cl.coefficient";
            query = em.createNativeQuery(sql);
            query.setParameter(1, matricule);
            query.setParameter(2, groupe);
            List<Object[]> liste = query.getResultList();
            if (liste == null) {
                return null;
            } else {
                return liste;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object[]> moyenne_de_l_eleve(String matricule) {
        try {
            Query query;
            String sql = "select cl.coefficient,(n.lanote*cl.coefficient) as notecoef"
                    + " from classes c "
                    + "inner join listedeseleves l on c.codeclasse = l.codeclasse "
                    + "inner join notesdeselevesann n on l.matriculeeleve = n.matriculeeleve "
                    + "inner join matieres m on m.codematiere = n.codematiere "
                    + "inner join ClaMatCoeffGrpe cl on cl.codeclasse = c.codeclasse and cl.codematiere = m.codematiere "
                    + "where l.matriculeeleve = ?1 "
                    + "group by c.codeclasse,l.matriculeeleve,m.codematiere,n.lanote,cl.coefficient";
            query = em.createNativeQuery(sql);
            query.setParameter(1, matricule);
            List<Object[]> liste = query.getResultList();
            if (liste == null) {
                return null;
            } else {
                return liste;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Annmoyennesrangsdeseleves> verifier_si_existe(String matricule) {
        Query query;
        query = em.createNamedQuery("Annmoyennesrangsdeseleves.findMatriculeeleve");
        query.setParameter("matriculeeleve", matricule);
        List<Annmoyennesrangsdeseleves> eleves_de_la_classe = query.getResultList();
        if (eleves_de_la_classe == null) {
            return null;
        } else {
            return eleves_de_la_classe;
        }
    }

    @Override
    public List<String> lite_des_groupes_matiere(String codeclasse) {
        try {
            Query query;
            query = em.createNamedQuery("Clamatcoeffgrpe.findGroupe");
            query.setParameter("codeclasse", codeclasse);
            List<String> liste = query.getResultList();
            if (liste == null) {
                return null;
            } else {
                return liste;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object[]> ordreDeMeriteDesEleves(String codeclasse) {
        Query query;
        query = em.createNativeQuery("select s1.matriculeeleve,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3,COUNT(s2.moyenne) rang\n"
                + "from (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?1) and s.moyenne is not null) s1,\n"
                + "     (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?2)and s.moyenne is not null) s2\n"
                + "where s1.moyenne<s2.moyenne or (s1.moyenne=s2.moyenne and s1.matriculeeleve=s2.matriculeeleve)\n"
                + "group by s1.matriculeeleve,s1.moyenne,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3\n"
                + "order by s1.matriculeeleve desc,s1.moyenne desc");
        query.setParameter(1, codeclasse);
        query.setParameter(2, codeclasse);
        List<Object[]> liste = query.getResultList();
        return liste;
    }

    @Override
    public List<Object[]> ordreDeMeriteDesElevesG1(String codeclasse) {
        Query query;
        query = em.createNativeQuery("select s1.matriculeeleve,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3,s1.rang,COUNT(s2.moyg1) rang\n"
                + "from (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?1) and s.moyg1 is not null) s1,\n"
                + "     (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?2)and s.moyg1 is not null) s2\n"
                + "where s1.moyg1<s2.moyg1 or (s1.moyg1=s2.moyg1 and s1.matriculeeleve=s2.matriculeeleve)\n"
                + "group by s1.matriculeeleve,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3,s1.rang\n"
                + "order by s1.matriculeeleve desc,s1.moyg1 desc");
        query.setParameter(1, codeclasse);
        query.setParameter(2, codeclasse);
        List<Object[]> listeg1 = query.getResultList();
        return listeg1;
    }

    @Override
    public List<Object[]> ordreDeMeriteDesElevesG2(String codeclasse) {
        Query query;
        query = em.createNativeQuery("select s1.matriculeeleve,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3,s1.rang,s1.rangg1,COUNT(s2.moyg2) rang\n"
                + "from (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?1) and s.moyg2 is not null) s1,\n"
                + "     (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?2) and s.moyg2 is not null) s2\n"
                + "where s1.moyg2<s2.moyg2 or (s1.moyg2=s2.moyg2 and s1.matriculeeleve=s2.matriculeeleve)\n"
                + "group by s1.matriculeeleve,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3,s1.rang,s1.rangg1\n"
                + "order by s1.matriculeeleve desc,s1.moyg2 desc");
        query.setParameter(1, codeclasse);
        query.setParameter(2, codeclasse);
        List<Object[]> listeg2 = query.getResultList();
        return listeg2;
    }

    @Override
    public List<Object[]> ordreDeMeriteDesElevesG3(String codeclasse) {
        Query query;
        query = em.createNativeQuery("select s1.matriculeeleve,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3,s1.rang,s1.rangg1,s1.rangg2,COUNT(s2.moyg3) rang\n"
                + "from (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?1) and s.moyg3 is not null) s1,\n"
                + "     (select s.matriculeeleve,s.moyenne,s.rang,s.moyg1,s.moyg2,s.moyg3,s.rangg1,s.rangg2,s.rangg3\n"
                + "      from annmoyennesrangsdeseleves s \n"
                + "      where s.matriculeeleve \n"
                + "      in (select l.matriculeeleve \n"
                + "      from listedeseleves l \n"
                + "      where l.codeclasse= ?2) and s.moyg3 is not null) s2\n"
                + "where s1.moyg3<s2.moyg3 or (s1.moyg3=s2.moyg3 and s1.matriculeeleve=s2.matriculeeleve)\n"
                + "group by s1.matriculeeleve,s1.moyenne,s1.moyg1,s1.moyg2,s1.moyg3,s1.rang,s1.rangg1,s1.rangg2\n"
                + "order by s1.matriculeeleve desc,s1.moyg3 desc");
        query.setParameter(1, codeclasse);
        query.setParameter(2, codeclasse);
        List<Object[]> listeg3 = query.getResultList();
        return listeg3;
    }

    @Override
    public List<Object[]> smsMoyRangan(String codeclasse) {
        Query query;
        query = em.createNativeQuery("select concat(l.nom,'',l.prenom)as nomeleve,l.adresse,s.moyenne,s.rang \n"
                + "from listedeseleves l\n"
                + "inner join annmoyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                + "where codeclasse=?1 and l.adresse is not null");
        query.setParameter(1, codeclasse);
        List<Object[]> listeg3 = query.getResultList();
        return listeg3;
    }

    @Override
    public List<Object[]> smsMoyRanganun(String codeclasse, String matricule) {
        Query query;
        query = em.createNativeQuery("select concat(l.nom,'',l.prenom)as nomeleve,l.adresse,s.moyenne,s.rang \n"
                + "from listedeseleves l\n"
                + "inner join annmoyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                + "where codeclasse=?1 and l.adresse is not null and l.matriculeeleve=?2");
        query.setParameter(1, codeclasse);
        query.setParameter(2, matricule);
        List<Object[]> listeg3 = query.getResultList();
        return listeg3;
    }

    @Override
    public List<Object[]> smsMoyRanganserie(String codeclasse, String matriculeDebut, String matriculeFin) {
        Query query;
        query = em.createNativeQuery("select concat(l.nom,' ',l.prenom)as nomeleve,l.adresse,s.moyenne,s.rang \n"
                + "from listedeseleves l\n"
                + "inner join annmoyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                + "where codeclasse=?1 and l.adresse is not null and l.matriculeeleve between ?2 and ?3");
        query.setParameter(1, codeclasse);
        query.setParameter(2, matriculeDebut);
        query.setParameter(3, matriculeFin);
        List<Object[]> listeg3 = query.getResultList();
        return listeg3;
    }

    @Override
    public List<Object[]> statparniveauann(String section) {
        Query query;
        query = em.createNativeQuery("select c.classcorresp,count(l.matriculeeleve) as inscrits,count(s.moyenne) as presents,count(case when s.moyenne >=10 then 1 end) as admis,c.typedeclasse\n"
                + "from classes c\n"
                + "inner join listedeseleves l on l.codeclasse=c.codeclasse\n"
                + "inner join annmoyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                + "where c.section=?1\n"
                + "group by c.classcorresp,c.typedeclasse\n"
                + "order by c.classcorresp asc");
        query.setParameter(1, section);
        try {
            List<Object[]> fiche = query.getResultList();
            if (!fiche.isEmpty()) {
                return fiche;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //liste des élèves Admis
    @Override
    public List<Annmoyennesrangsdeseleves> liste_des_eleves_admis(String codeclasse, BigDecimal moyenne) {
        Query query = em.createNamedQuery("Annmoyennesrangsdeseleves.findEleveAdmis");
        query.setParameter("codeclasse", codeclasse);
        query.setParameter("moyenne", moyenne); 
        List<Annmoyennesrangsdeseleves> liste = query.getResultList();
        return liste;
    }
}
