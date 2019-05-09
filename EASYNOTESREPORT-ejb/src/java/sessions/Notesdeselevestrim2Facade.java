/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevestrim2;
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
public class Notesdeselevestrim2Facade extends AbstractFacade<Notesdeselevestrim2> implements Notesdeselevestrim2FacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Notesdeselevestrim2Facade() {
        super(Notesdeselevestrim2.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createNamedQuery("Notesdeselevestrim2.nexId");
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
    public Notesdeselevestrim2 upadateNoteTrim2Eleves(String codeMatiere, String matricule) {
        try {
            Query query = em.createNamedQuery("Notesdeselevestrim2.findNoteByMatricule");
            query.setParameter("codematiere", codeMatiere);
            query.setParameter("matriculeeleve", matricule);
            Notesdeselevestrim2 list = (Notesdeselevestrim2) query.getSingleResult();
            if (list == null) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Listedeseleves> listeDesNouveauxElevesTrim2(String codeMatiere, String codeclasse) {
        try {
            Query query = em.createNamedQuery("Notesdeselevestrim2.findNouveauxElevesTrim2");
            query.setParameter("codematiere", codeMatiere);
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
    //selection de la note d'un élève pour enregistremnt de sa note annuelle

    @Override
    public BigDecimal noteEleve(String matriculeEleve, String codeMatiere) {
        Query query = em.createNamedQuery("Notesdeselevestrim2.findNoteEleve");
        query.setParameter("matricule", matriculeEleve);
        query.setParameter("codematiere", codeMatiere);
        Notesdeselevestrim2 list = (Notesdeselevestrim2) query.getSingleResult();
        if (list.getLanote() == null) {
            return BigDecimal.valueOf(0);
        } else {
            return list.getLanote();
        }
    }
    //virifier lors de la saisie par matière si l'élève a déja sa matière

    @Override
    public boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere) {
        try {
            Notesdeselevestrim2 n = em.createNamedQuery("Notesdeselevestrim2.findNoteEleve", Notesdeselevestrim2.class).setParameter("matricule", matriculeEleve).setParameter("codematiere", codeMatiere).getSingleResult();
            if (n != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    //bulletins des élèves séquence 1

    @Override
    public List<Object[]> bulletinTrim2(String codeclass) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns1.lanote,\n"
                    + "trim2.moyenne as moyenneeleve,trim2.rang as rang_classe_eleve,trim2.moyg1,trim2.moyg2,trim2.moyg3,trim2.rangg1,trim2.rangg2,trim2.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,\n"
                    + "co.decision,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,','),ns2.lanote as notes2,sm1.moyenne as moyenneeleve1,sm2.moyenne as moyenneeleve2,sm3.moyenne as moyenneeleve3,sm4.moyenne as moyenneeleve4,trim1.moyenne as moyenneelevetrim1\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='TRIM2'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq4 ns2 on ns2.matriculeeleve=l.matriculeeleve and m.codematiere=ns2.codematiere\n"
                    + " inner join notesdeselevesseq3 ns1 on ns1.matriculeeleve=l.matriculeeleve and m.codematiere=ns1.codematiere\n"
                    + " inner join notesdeselevestrim2 n on n.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq2moyennesrangsdeseleves sm2 on sm2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq3moyennesrangsdeseleves sm3 on sm3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq4moyennesrangsdeseleves sm4 on sm4.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim1moyennesrangsdeseleves trim1 on trim1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim2moyennesrangsdeseleves trim2 on trim2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='TRIM2'\n"
                    + " inner join trim2rangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevestrim2 d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join conseildeclassetrim2 co on co.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7\n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,ns2.lanote,sm3.moyenne,sm4.moyenne,trim1.moyenne,trim2.moyenne,\n"
                    + "en.matriculeenseignant,ns1.lanote,trim2.moyenne,trim2.rang,trim2.moyg1,trim2.moyg2,trim2.moyg3,trim2.rangg1,trim2.rangg2,trim2.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne,sr.rang,p.nom,p.prenom,d.absj,d.absn,d.consigne,d.exclusion,ns1.lanote,sm1.moyenne,sm2.moyenne,co.decision\n"
                    + "order by l.nom,l.matriculeeleve,cl.groupe,cl.codematiere");
            query.setParameter(1, codeclass);
            query.setParameter(2, codeclass);
            query.setParameter(3, codeclass);
            query.setParameter(4, codeclass);
            query.setParameter(5, codeclass);
            query.setParameter(6, codeclass);
            query.setParameter(7, codeclass);
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
    //bulletin pour un élève séquence 1

    @Override
    public List<Object[]> bulletinTrim2ParEleve(String codeclass, String matriculeEleve) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns1.lanote,\n"
                    + "trim2.moyenne as moyenneeleve,trim2.rang as rang_classe_eleve,trim2.moyg1,trim2.moyg2,trim2.moyg3,trim2.rangg1,trim2.rangg2,trim2.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,\n"
                    + "co.decision,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,','),ns2.lanote as notes2,sm1.moyenne as moyenneeleve1,sm2.moyenne as moyenneeleve2,sm3.moyenne as moyenneeleve3,sm4.moyenne as moyenneeleve4,trim1.moyenne as moyenneelevetrim1\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='TRIM2'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq4 ns2 on ns2.matriculeeleve=l.matriculeeleve and m.codematiere=ns2.codematiere\n"
                    + " inner join notesdeselevesseq3 ns1 on ns1.matriculeeleve=l.matriculeeleve and m.codematiere=ns1.codematiere\n"
                    + " inner join notesdeselevestrim2 n on n.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq2moyennesrangsdeseleves sm2 on sm2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq3moyennesrangsdeseleves sm3 on sm3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq4moyennesrangsdeseleves sm4 on sm4.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim1moyennesrangsdeseleves trim1 on trim1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim2moyennesrangsdeseleves trim2 on trim2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='TRIM2'\n"
                    + " inner join trim2rangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevestrim2 d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join conseildeclassetrim2 co on co.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7 and l.matriculeeleve=?8\n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,ns2.lanote,sm3.moyenne,sm4.moyenne,trim1.moyenne,trim2.moyenne,\n"
                    + "en.matriculeenseignant,ns1.lanote,trim2.moyenne,trim2.rang,trim2.moyg1,trim2.moyg2,trim2.moyg3,trim2.rangg1,trim2.rangg2,trim2.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne,sr.rang,p.nom,p.prenom,d.absj,d.absn,d.consigne,d.exclusion,ns1.lanote,sm1.moyenne,sm2.moyenne,co.decision\n"
                    + "order by l.nom,l.matriculeeleve,cl.groupe,cl.codematiere");
            query.setParameter(1, codeclass);
            query.setParameter(2, codeclass);
            query.setParameter(3, codeclass);
            query.setParameter(4, codeclass);
            query.setParameter(5, codeclass);
            query.setParameter(6, codeclass);
            query.setParameter(7, codeclass);
            query.setParameter(8, matriculeEleve);
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
//bulletin pour un élève séquence 1

    @Override
    public List<Object[]> bulletinTrim2ParSerie(String codeclass, String matriculeEleve, String matriculeEleveA) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns1.lanote,\n"
                    + "trim2.moyenne as moyenneeleve,trim2.rang as rang_classe_eleve,trim2.moyg1,trim2.moyg2,trim2.moyg3,trim2.rangg1,trim2.rangg2,trim2.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,\n"
                    + "co.decision,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,','),ns2.lanote as notes2,sm1.moyenne as moyenneeleve1,sm2.moyenne as moyenneeleve2,sm3.moyenne as moyenneeleve3,sm4.moyenne as moyenneeleve4,trim1.moyenne as moyenneelevetrim1\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='TRIM2'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq4 ns2 on ns2.matriculeeleve=l.matriculeeleve and m.codematiere=ns2.codematiere\n"
                    + " inner join notesdeselevesseq3 ns1 on ns1.matriculeeleve=l.matriculeeleve and m.codematiere=ns1.codematiere\n"
                    + " inner join notesdeselevestrim2 n on n.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq2moyennesrangsdeseleves sm2 on sm2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq3moyennesrangsdeseleves sm3 on sm3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq4moyennesrangsdeseleves sm4 on sm4.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim1moyennesrangsdeseleves trim1 on trim1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim2moyennesrangsdeseleves trim2 on trim2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='TRIM2'\n"
                    + " inner join trim2rangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevestrim2 d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join conseildeclassetrim2 co on co.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7 and l.matriculeeleve between ?8 and ?9 \n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,ns2.lanote,sm3.moyenne,sm4.moyenne,trim1.moyenne,trim2.moyenne,\n"
                    + "en.matriculeenseignant,ns1.lanote,trim2.moyenne,trim2.rang,trim2.moyg1,trim2.moyg2,trim2.moyg3,trim2.rangg1,trim2.rangg2,trim2.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne,sr.rang,p.nom,p.prenom,d.absj,d.absn,d.consigne,d.exclusion,ns1.lanote,sm1.moyenne,sm2.moyenne,co.decision\n"
                    + "order by l.nom,l.matriculeeleve,cl.groupe,cl.codematiere");
            query.setParameter(1, codeclass);
            query.setParameter(2, codeclass);
            query.setParameter(3, codeclass);
            query.setParameter(4, codeclass);
            query.setParameter(5, codeclass);
            query.setParameter(6, codeclass);
            query.setParameter(7, codeclass);
            query.setParameter(8, matriculeEleve);
            query.setParameter(9, matriculeEleveA);
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
    public List<Object[]> pvTrim2(String codeclass) {
        try {
            Query query = em.createNativeQuery("select concat(l.nom,' ',l.prenom) as nomeleve,l.matriculeeleve,l.sexe,to_char(l.datenaiss,'YYYY'),\n"
                    + "sm1.moyenne as moyenne1,sm1.rang,ca.moyenne as moyenne_classe,ca.tauxreussite\n"
                    + "from Classes c\n"
                    + "inner join listedeseleves l on l.codeclasse=c.codeclasse\n"
                    + "inner join trim2moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + "inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='TRIM2'\n"
                    + "where c.codeclasse=?1\n"
                    + "order by l.nom");
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
    public List<Object[]> synthTrim2(String codeclass) {
        try {
            Query query = em.createNativeQuery("select n.codematiere,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote is not null and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=n.codematiere) as effectif,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 0 and 5.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?2) and codematiere=n.codematiere) as eff0_6,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 6 and 7.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?3) and codematiere=n.codematiere) as eff6_8,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 8 and 8.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?4) and codematiere=n.codematiere) as eff8_9,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 9 and 9.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?5) and codematiere=n.codematiere) as eff9_10,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 10 and 11.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?6) and codematiere=n.codematiere) as eff10_12,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 12 and 13.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?7) and codematiere=n.codematiere) as eff12_14,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 14 and 15.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?8) and codematiere=n.codematiere) as eff14_16,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 16 and 17.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?9) and codematiere=n.codematiere) as eff16_18,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevestrim2 where lanote between 18 and 20 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?10) and codematiere=n.codematiere) as eff18_20\n"
                    + "from notesdeselevestrim2 n\n"
                    + "where matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?11)\n"
                    + "group by n.codematiere\n"
                    + "order by n.codematiere");
            query.setParameter(1, codeclass);
            query.setParameter(2, codeclass);
            query.setParameter(3, codeclass);
            query.setParameter(4, codeclass);
            query.setParameter(5, codeclass);
            query.setParameter(6, codeclass);
            query.setParameter(7, codeclass);
            query.setParameter(8, codeclass);
            query.setParameter(9, codeclass);
            query.setParameter(10, codeclass);
            query.setParameter(11, codeclass);
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
    public List<Object[]> effG_F8_par_interval(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT \n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 0 and 5.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='F')) as eff0_5,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 6 and 7.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='F')) as eff6_7,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 8 and 8.999 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='F')) as eff8_8,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 9 and 9.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='F')) as eff9_9,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 10 and 11.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='F')) as eff10_11,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 12 and 13.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='F')) as eff12_13,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 14 and 15.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?7 and sexe='F')) as eff14_15,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 16 and 17.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?8 and sexe='F')) as eff16_17,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 18 and 20 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?9 and sexe='F')) as eff18_20,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 0 and 5.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?10 and sexe='G')) as eff0_5,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 6 and 7.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=? and sexe='G')) as eff6_7,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 8 and 8.999 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?12 and sexe='G')) as eff8_8,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 9 and 9.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?13 and sexe='G')) as eff9_9,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 10 and 11.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?14 and sexe='G')) as eff10_11,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 12 and 13.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?15 and sexe='G')) as eff12_13,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 14 and 15.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?16 and sexe='G')) as eff14_15,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 16 and 17.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?17 and sexe='G')) as eff16_17,\n"
                    + "(select count(matriculeeleve) from trim2moyennesrangsdeseleves where moyenne between 18 and 20 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?18 and sexe='G')) as eff18_20");
            query.setParameter(1, codeclass);
            query.setParameter(2, codeclass);
            query.setParameter(3, codeclass);
            query.setParameter(4, codeclass);
            query.setParameter(5, codeclass);
            query.setParameter(6, codeclass);
            query.setParameter(7, codeclass);
            query.setParameter(8, codeclass);
            query.setParameter(9, codeclass);
            query.setParameter(10, codeclass);
            query.setParameter(11, codeclass);
            query.setParameter(12, codeclass);
            query.setParameter(13, codeclass);
            query.setParameter(14, codeclass);
            query.setParameter(15, codeclass);
            query.setParameter(16, codeclass);
            query.setParameter(17, codeclass);
            query.setParameter(18, codeclass);
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
    public List<Object[]> Moy_premier_Dernier(String codeclass) {
        try {
            Query query = em.createNativeQuery("select moyenne,moyennepremier,moyennedernier from calculnotesdesclasses where codeclasse=?1 and periode ='TRIM2' order by periode");
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
//calcul auto des notes trimestrielles

    @Override
    public List<Object[]> noteSeq3_4(String codeclass) {
        try {
            Query query = em.createNativeQuery("select l.matriculeeleve,m.codematiere,n1.lanote as n1,n2.lanote as n2\n"
                    + "from listedeseleves l\n"
                    + "inner join notesdeselevesseq3 n1 on n1.matriculeeleve=l.matriculeeleve \n"
                    + "inner join notesdeselevesseq4 n2 on n2.matriculeeleve=l.matriculeeleve\n"
                    + "inner join matieres m on m.codematiere=n1.codematiere and m.codematiere=n2.codematiere\n"
                    + "where codeclasse=?1\n"
                    + "group by l.matriculeeleve,m.codematiere,n1.lanote,n2.lanote\n"
                    + "order by m.codematiere,l.matriculeeleve");
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
}
