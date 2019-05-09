/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Notesdeselevesann;
import entities.Notesdeselevesseq2;
import entities.Notesdeselevesseq5;
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
public class NotesdeselevesannFacade extends AbstractFacade<Notesdeselevesann> implements NotesdeselevesannFacadeLocal {
    
    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public NotesdeselevesannFacade() {
        super(Notesdeselevesann.class);
    }
    
    @Override
    public Integer nextId() {
        try {
            Query query = em.createNamedQuery("Notesdeselevesann.nexId");
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
    public List<Listedeseleves> listeDesNouveauxElevesAnn(String codeMatiere, String codeclasse) {
        try {
            Query query = em.createNamedQuery("Notesdeselevesann.findNouveauxElevesAnn");
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
    
    @Override
    public Notesdeselevesann upadateNoteAnuelleEleves(String codeMatiere, String matricule) {
        try {
            Query query = em.createNamedQuery("Notesdeselevesann.findNoteByMatricule");
            query.setParameter("codematiere", codeMatiere);
            query.setParameter("matriculeeleve", matricule);
            Notesdeselevesann list = (Notesdeselevesann) query.getSingleResult();
            if (list == null) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
    }
    //virifier lors de la saisie par matière si l'élève a déja sa matière

    @Override
    public boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere) {
        try {
            Notesdeselevesann n = em.createNamedQuery("Notesdeselevesann.findNoteByMatricule", Notesdeselevesann.class).setParameter("matriculeeleve", matriculeEleve).setParameter("codematiere", codeMatiere).getSingleResult();
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
    public List<Object[]> bulletinAnn(String codeclass) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns1.lanote,\n"
                    + "ann.moyenne as moyenneeleve,ann.rang as rang_classe_eleve,ann.moyg1,ann.moyg2,ann.moyg3,ann.rangg1,ann.rangg2,ann.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,\n"
                    + "co.decision,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,','),ns2.lanote as notes2,sm1.moyenne as moyenneeleve1,sm2.moyenne as moyenneeleve2,sm3.moyenne as moyenneeleve3,sm4.moyenne as moyenneeleve4,trim1.moyenne as moyenneelevetrim1,trim2.moyenne as moyenneelevetrim2,sm5.moyenne as moyenneeleve5,sm6.moyenne as moyenneeleve6,trim3.moyenne as moyenneelevetrim3,ns3.lanote as notes3,ns4.lanote as notes4,ns5.lanote as notes5,ns6.lanote as notes6\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='ANN'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq6 ns6 on ns6.matriculeeleve=l.matriculeeleve and m.codematiere=ns6.codematiere\n"
                    + " inner join notesdeselevesseq5 ns5 on ns5.matriculeeleve=l.matriculeeleve and m.codematiere=ns5.codematiere\n"
                    + " inner join notesdeselevesseq4 ns4 on ns4.matriculeeleve=l.matriculeeleve and m.codematiere=ns4.codematiere\n"
                    + " inner join notesdeselevesseq3 ns3 on ns3.matriculeeleve=l.matriculeeleve and m.codematiere=ns3.codematiere\n"
                    + " inner join notesdeselevesseq2 ns2 on ns2.matriculeeleve=l.matriculeeleve and m.codematiere=ns2.codematiere\n"
                    + " inner join notesdeselevesseq1 ns1 on ns1.matriculeeleve=l.matriculeeleve and m.codematiere=ns1.codematiere\n"
                    + " inner join notesdeselevesann n on n.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq2moyennesrangsdeseleves sm2 on sm2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq3moyennesrangsdeseleves sm3 on sm3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq4moyennesrangsdeseleves sm4 on sm4.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq5moyennesrangsdeseleves sm5 on sm5.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq6moyennesrangsdeseleves sm6 on sm6.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim1moyennesrangsdeseleves trim1 on trim1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim2moyennesrangsdeseleves trim2 on trim2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim3moyennesrangsdeseleves trim3 on trim3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join annmoyennesrangsdeseleves ann on ann.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='ANN'\n"
                    + " inner join annrangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevesann d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join conseildeclasseann co on co.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7\n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,ns2.lanote,ns3.lanote,ns4.lanote,ns5.lanote,ns6.lanote,sm3.moyenne,sm4.moyenne,sm5.moyenne,sm6.moyenne,trim1.moyenne,trim2.moyenne,\n"
                    + "trim3.moyenne,en.matriculeenseignant,ns1.lanote,ann.moyenne,ann.rang,ann.moyg1,ann.moyg2,ann.moyg3,ann.rangg1,ann.rangg2,ann.rangg3,\n"
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
    public List<Object[]> bulletinAnnParEleve(String codeclass, String matriculeEleve) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns1.lanote,\n"
                    + "ann.moyenne as moyenneeleve,ann.rang as rang_classe_eleve,ann.moyg1,ann.moyg2,ann.moyg3,ann.rangg1,ann.rangg2,ann.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,\n"
                    + "co.decision,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,','),ns2.lanote as notes2,sm1.moyenne as moyenneeleve1,sm2.moyenne as moyenneeleve2,sm3.moyenne as moyenneeleve3,sm4.moyenne as moyenneeleve4,trim1.moyenne as moyenneelevetrim1,trim2.moyenne as moyenneelevetrim2,sm5.moyenne as moyenneeleve5,sm6.moyenne as moyenneeleve6,trim3.moyenne as moyenneelevetrim3,ns3.lanote as notes3,ns4.lanote as notes4,ns5.lanote as notes5,ns6.lanote as notes6\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='ANN'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq6 ns6 on ns6.matriculeeleve=l.matriculeeleve and m.codematiere=ns6.codematiere\n"
                    + " inner join notesdeselevesseq5 ns5 on ns5.matriculeeleve=l.matriculeeleve and m.codematiere=ns5.codematiere\n"
                    + " inner join notesdeselevesseq4 ns4 on ns4.matriculeeleve=l.matriculeeleve and m.codematiere=ns4.codematiere\n"
                    + " inner join notesdeselevesseq3 ns3 on ns3.matriculeeleve=l.matriculeeleve and m.codematiere=ns3.codematiere\n"
                    + " inner join notesdeselevesseq2 ns2 on ns2.matriculeeleve=l.matriculeeleve and m.codematiere=ns2.codematiere\n"
                    + " inner join notesdeselevesseq1 ns1 on ns1.matriculeeleve=l.matriculeeleve and m.codematiere=ns1.codematiere\n"
                    + " inner join notesdeselevesann n on n.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq2moyennesrangsdeseleves sm2 on sm2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq3moyennesrangsdeseleves sm3 on sm3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq4moyennesrangsdeseleves sm4 on sm4.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq5moyennesrangsdeseleves sm5 on sm5.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq6moyennesrangsdeseleves sm6 on sm6.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim1moyennesrangsdeseleves trim1 on trim1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim2moyennesrangsdeseleves trim2 on trim2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim3moyennesrangsdeseleves trim3 on trim3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join annmoyennesrangsdeseleves ann on ann.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='ANN'\n"
                    + " inner join annrangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevesann d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join conseildeclasseann co on co.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7 and l.matriculeeleve=?8\n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,ns2.lanote,ns3.lanote,ns4.lanote,ns5.lanote,ns6.lanote,sm3.moyenne,sm4.moyenne,sm5.moyenne,sm6.moyenne,trim1.moyenne,trim2.moyenne,\n"
                    + "trim3.moyenne,en.matriculeenseignant,ns1.lanote,ann.moyenne,ann.rang,ann.moyg1,ann.moyg2,ann.moyg3,ann.rangg1,ann.rangg2,ann.rangg3,\n"
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
    public List<Object[]> bulletinAnnParSerie(String codeclass, String matriculeEleve, String matriculeEleveA) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns1.lanote,\n"
                    + "ann.moyenne as moyenneeleve,ann.rang as rang_classe_eleve,ann.moyg1,ann.moyg2,ann.moyg3,ann.rangg1,ann.rangg2,ann.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,\n"
                    + "co.decision,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,','),ns2.lanote as notes2,sm1.moyenne as moyenneeleve1,sm2.moyenne as moyenneeleve2,sm3.moyenne as moyenneeleve3,sm4.moyenne as moyenneeleve4,trim1.moyenne as moyenneelevetrim1,trim2.moyenne as moyenneelevetrim2,sm5.moyenne as moyenneeleve5,sm6.moyenne as moyenneeleve6,trim3.moyenne as moyenneelevetrim3,ns3.lanote as notes3,ns4.lanote as notes4,ns5.lanote as notes5,ns6.lanote as notes6\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='ANN'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq6 ns6 on ns6.matriculeeleve=l.matriculeeleve and m.codematiere=ns6.codematiere\n"
                    + " inner join notesdeselevesseq5 ns5 on ns5.matriculeeleve=l.matriculeeleve and m.codematiere=ns5.codematiere\n"
                    + " inner join notesdeselevesseq4 ns4 on ns4.matriculeeleve=l.matriculeeleve and m.codematiere=ns4.codematiere\n"
                    + " inner join notesdeselevesseq3 ns3 on ns3.matriculeeleve=l.matriculeeleve and m.codematiere=ns3.codematiere\n"
                    + " inner join notesdeselevesseq2 ns2 on ns2.matriculeeleve=l.matriculeeleve and m.codematiere=ns2.codematiere\n"
                    + " inner join notesdeselevesseq1 ns1 on ns1.matriculeeleve=l.matriculeeleve and m.codematiere=ns1.codematiere\n"
                    + " inner join notesdeselevesann n on n.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq2moyennesrangsdeseleves sm2 on sm2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq3moyennesrangsdeseleves sm3 on sm3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq4moyennesrangsdeseleves sm4 on sm4.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq5moyennesrangsdeseleves sm5 on sm5.matriculeeleve=l.matriculeeleve\n"
                    + " inner join seq6moyennesrangsdeseleves sm6 on sm6.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim1moyennesrangsdeseleves trim1 on trim1.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim2moyennesrangsdeseleves trim2 on trim2.matriculeeleve=l.matriculeeleve\n"
                    + " inner join trim3moyennesrangsdeseleves trim3 on trim3.matriculeeleve=l.matriculeeleve\n"
                    + " inner join annmoyennesrangsdeseleves ann on ann.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='ANN'\n"
                    + " inner join annrangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevesann d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join conseildeclasseann co on co.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7 and l.matriculeeleve between ?8 and ?9 \n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,ns2.lanote,ns3.lanote,ns4.lanote,ns5.lanote,ns6.lanote,sm3.moyenne,sm4.moyenne,sm5.moyenne,sm6.moyenne,trim1.moyenne,trim2.moyenne,\n"
                    + "trim3.moyenne,en.matriculeenseignant,ns1.lanote,ann.moyenne,ann.rang,ann.moyg1,ann.moyg2,ann.moyg3,ann.rangg1,ann.rangg2,ann.rangg3,\n"
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
    public List<Object[]> pvAnn(String codeclass) {
        try {
            Query query = em.createNativeQuery("select concat(l.nom,' ',l.prenom) as nomeleve,l.matriculeeleve,l.sexe,to_char(l.datenaiss,'YYYY'),\n"
                    + "sm1.moyenne as moyenne1,sm1.rang as rang1, sm2.moyenne as moyenne2,sm2.rang as rang2,sm3.moyenne as moyenne3,sm3.rang as rang3,ann.moyenne as moyenneann,ann.rang as rangann,ca.moyenne as moyenne_classe,ca.tauxreussite\n"
                    + "from Classes c\n"
                    + "inner join listedeseleves l on l.codeclasse=c.codeclasse\n"
                    + "inner join trim1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + "inner join trim2moyennesrangsdeseleves sm2 on sm2.matriculeeleve=l.matriculeeleve\n"
                    + "inner join trim3moyennesrangsdeseleves sm3 on sm3.matriculeeleve=l.matriculeeleve\n"
                    + "inner join annmoyennesrangsdeseleves ann on ann.matriculeeleve=l.matriculeeleve\n"
                    + "inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='ANN'\n"
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
    public List<Object[]> synthAnn(String codeclass) {
        try {
            Query query = em.createNativeQuery("select n.codematiere,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote is not null and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=n.codematiere) as effectif,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 0 and 5.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?2) and codematiere=n.codematiere) as eff0_6,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 6 and 7.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?3) and codematiere=n.codematiere) as eff6_8,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 8 and 8.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?4) and codematiere=n.codematiere) as eff8_9,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 9 and 9.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?5) and codematiere=n.codematiere) as eff9_10,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 10 and 11.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?6) and codematiere=n.codematiere) as eff10_12,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 12 and 13.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?7) and codematiere=n.codematiere) as eff12_14,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 14 and 15.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?8) and codematiere=n.codematiere) as eff14_16,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 16 and 17.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?9) and codematiere=n.codematiere) as eff16_18,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesann where lanote between 18 and 20 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?10) and codematiere=n.codematiere) as eff18_20\n"
                    + "from notesdeselevesann n\n"
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
    public List<Object[]> effF(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT count(sexe) from listedeseleves where sexe='F' and codeclasse=?1");
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
    public List<Object[]> effG(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT count(sexe) from listedeseleves where sexe='G' and codeclasse=?1");
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
    public List<Object[]> effG_F8_par_interval(String codeclass) {
        try {
            Query query = em.createNativeQuery("SELECT \n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 0 and 5.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='F')) as eff0_5,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 6 and 7.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='F')) as eff6_7,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 8 and 8.999 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='F')) as eff8_8,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 9 and 9.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='F')) as eff9_9,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 10 and 11.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='F')) as eff10_11,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 12 and 13.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='F')) as eff12_13,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 14 and 15.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?7 and sexe='F')) as eff14_15,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 16 and 17.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?8 and sexe='F')) as eff16_17,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 18 and 20 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?9 and sexe='F')) as eff18_20,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 0 and 5.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?10 and sexe='G')) as eff0_5,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 6 and 7.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=? and sexe='G')) as eff6_7,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 8 and 8.999 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?12 and sexe='G')) as eff8_8,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 9 and 9.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?13 and sexe='G')) as eff9_9,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 10 and 11.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?14 and sexe='G')) as eff10_11,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 12 and 13.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?15 and sexe='G')) as eff12_13,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 14 and 15.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?16 and sexe='G')) as eff14_15,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 16 and 17.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?17 and sexe='G')) as eff16_17,\n"
                    + "(select count(matriculeeleve) from annmoyennesrangsdeseleves where moyenne between 18 and 20 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?18 and sexe='G')) as eff18_20");
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
    public List<Object[]> rappelSeq(String codeclass) {
        try {
            Query query = em.createNativeQuery("select moyenne from calculnotesdesclasses where codeclasse=?1 and periode like 'SEQ%' order by periode");
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
    public List<Object[]> rappelseqTaux(String codeclass) {
        try {
            Query query = em.createNativeQuery("select tauxreussite from calculnotesdesclasses where codeclasse=?1 and periode like 'SEQ%' order by periode");
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
    public List<Object[]> rappelTrim(String codeclass) {
        try {
            Query query = em.createNativeQuery("select moyenne from calculnotesdesclasses where codeclasse=?1 and periode like 'TRIM%' order by periode");
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
    public List<Object[]> rappelTimTaux(String codeclass) {
        try {
            Query query = em.createNativeQuery("select tauxreussite from calculnotesdesclasses where codeclasse=?1 and periode like 'TRIM%' order by periode");
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
    public List<Object[]> Moy_premier_Dernier(String codeclass) {
        try {
            Query query = em.createNativeQuery("select moyenne,moyennepremier,moyennedernier from calculnotesdesclasses where codeclasse=?1 and periode ='ANN' order by periode");
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
    public List<Object[]> synthAnn_Trim(String codeclass) {
        try {
            Query query = em.createNativeQuery("select c.periode,c.moyenne,c.tauxreussite\n"
                    + "from calculnotesdesclasses c\n"
                    + "where c.codeclasse=?1 and c.periode like '%SEQ%'\n"
                    + "order by c.periode");
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
    public List<BigDecimal> moyGene_Trim_An(String codeclass) {
        try {
            Query query = em.createNativeQuery("select moyenne from calculnotesdesclasses\n"
                    + "where codeclasse=?1 and (periode like 'TRIM%' or periode like 'AN%')\n"
                    + "order by periode ");
            query.setParameter(1, codeclass);
            List<BigDecimal> falist = query.getResultList();
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
    public List<Object[]> synthAnn_Trim1(String codeclass) {
        try {
            Query query = em.createNativeQuery("select\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='G')) as effectifsup12G,\n"
                    + "       (select count(*)\n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='G')) as effsup10G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='G')) as eff950_999G,\n"
                    + "       (select count(*)\n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?22 and sexe='G')) as eff925_949G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='G')) as eff9_924G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='G')) as eff750_899G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='G')) as effinf899G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?7 and sexe='G')) as effinf750G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?8 and sexe='G')) as effsup14G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?9 and sexe='G')) as effsup17G,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?10 and sexe='F')) as effectifsup12F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?11 and sexe='F')) as effsup10F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?12 and sexe='F')) as eff950_999F,\n"
                    + "       (select count(*)\n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?23 and sexe='F')) as eff925_949F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?13 and sexe='F')) as eff9_924F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?14 and sexe='F')) as eff750_899F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?15 and sexe='F')) as effinf899F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?16 and sexe='F')) as effinf750F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?17 and sexe='F')) as effsup14F,\n"
                    + "       (select count(*) \n"
                    + "       from trim1moyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?18 and sexe='F')) as effsup17F,\n"
                    + "       (select max(moyenne) \n"
                    + "       from trim1moyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?19)) as maxavg,\n"
                    + "       (select min(moyenne) \n"
                    + "       from trim1moyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?20)) as minavg		 																									\n"
                    + "from trim1moyennesrangsdeseleves \n"
                    + "where matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?21) limit 1							");
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
            query.setParameter(19, codeclass);
            query.setParameter(20, codeclass);
            query.setParameter(21, codeclass);
            query.setParameter(22, codeclass);
            query.setParameter(23, codeclass);
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
    public List<Object[]> synthAnn_Trim2(String codeclass) {
        try {
            Query query = em.createNativeQuery("select\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='G')) as effectifsup12G,\n"
                    + "       (select count(*)\n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='G')) as effsup10G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='G')) as eff950_999G,\n"
                    + "       (select count(*)\n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?22 and sexe='G')) as eff925_949G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='G')) as eff9_924G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='G')) as eff750_899G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='G')) as effinf899G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?7 and sexe='G')) as effinf750G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?8 and sexe='G')) as effsup14G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?9 and sexe='G')) as effsup17G,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?10 and sexe='F')) as effectifsup12F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?11 and sexe='F')) as effsup10F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?12 and sexe='F')) as eff950_999F,\n"
                    + "       (select count(*)\n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?23 and sexe='F')) as eff925_949F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?13 and sexe='F')) as eff9_924F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?14 and sexe='F')) as eff750_899F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?15 and sexe='F')) as effinf899F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?16 and sexe='F')) as effinf750F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?17 and sexe='F')) as effsup14F,\n"
                    + "       (select count(*) \n"
                    + "       from trim2moyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?18 and sexe='F')) as effsup17F,\n"
                    + "       (select max(moyenne) \n"
                    + "       from trim2moyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?19)) as maxavg,\n"
                    + "       (select min(moyenne) \n"
                    + "       from trim2moyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?20)) as minavg		 																									\n"
                    + "from trim2moyennesrangsdeseleves \n"
                    + "where matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?21) limit 1							");
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
            query.setParameter(19, codeclass);
            query.setParameter(20, codeclass);
            query.setParameter(21, codeclass);
            query.setParameter(22, codeclass);
            query.setParameter(23, codeclass);
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
    
    public List<Object[]> synthAnn_Trim3(String codeclass) {
        try {
            Query query = em.createNativeQuery("select\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='G')) as effectifsup12G,\n"
                    + "       (select count(*)\n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='G')) as effsup10G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='G')) as eff950_999G,\n"
                    + "       (select count(*)\n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?22 and sexe='G')) as eff925_949G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='G')) as eff9_924G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='G')) as eff750_899G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='G')) as effinf899G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?7 and sexe='G')) as effinf750G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?8 and sexe='G')) as effsup14G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?9 and sexe='G')) as effsup17G,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?10 and sexe='F')) as effectifsup12F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?11 and sexe='F')) as effsup10F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?12 and sexe='F')) as eff950_999F,\n"
                    + "       (select count(*)\n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?23 and sexe='F')) as eff925_949F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?13 and sexe='F')) as eff9_924F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?14 and sexe='F')) as eff750_899F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?15 and sexe='F')) as effinf899F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?16 and sexe='F')) as effinf750F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?17 and sexe='F')) as effsup14F,\n"
                    + "       (select count(*) \n"
                    + "       from trim3moyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?18 and sexe='F')) as effsup17F,\n"
                    + "       (select max(moyenne) \n"
                    + "       from trim3moyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?19)) as maxavg,\n"
                    + "       (select min(moyenne) \n"
                    + "       from trim3moyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?20)) as minavg		 																									\n"
                    + "from trim3moyennesrangsdeseleves \n"
                    + "where matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?21) limit 1							");
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
            query.setParameter(19, codeclass);
            query.setParameter(20, codeclass);
            query.setParameter(21, codeclass);
            query.setParameter(22, codeclass);
            query.setParameter(23, codeclass);
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
    
    public List<Object[]> synthAnn_An(String codeclass) {
        try {
            Query query = em.createNativeQuery("select\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='G')) as effectifsup12G,\n"
                    + "       (select count(*)\n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='G')) as effsup10G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='G')) as eff950_999G,\n"
                    + "       (select count(*)\n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?22 and sexe='G')) as eff925_949G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='G')) as eff9_924G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='G')) as eff750_899G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='G')) as effinf899G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?7 and sexe='G')) as effinf750G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?8 and sexe='G')) as effsup14G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?9 and sexe='G')) as effsup17G,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 12 and matriculeeleve in \n"
                    + "       (select matriculeeleve from listedeseleves where codeclasse=?10 and sexe='F')) as effectifsup12F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 10 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?11 and sexe='F')) as effsup10F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 9.50 and 9.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?12 and sexe='F')) as eff950_999F,\n"
                    + "       (select count(*)\n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 9.25 and 9.49 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?23 and sexe='F')) as eff925_949F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 9 and 9.24 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?13 and sexe='F')) as eff9_924F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne between 7.50 and 8.99 and matriculeeleve \n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?14 and sexe='F')) as eff750_899F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne < 8.99 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?15 and sexe='F')) as effinf899F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne < 7.49 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?16 and sexe='F')) as effinf750F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 14 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?17 and sexe='F')) as effsup14F,\n"
                    + "       (select count(*) \n"
                    + "       from annmoyennesrangsdeseleves where moyenne >= 17 and matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?18 and sexe='F')) as effsup17F,\n"
                    + "       (select max(moyenne) \n"
                    + "       from annmoyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?19)) as maxavg,\n"
                    + "       (select min(moyenne) \n"
                    + "       from annmoyennesrangsdeseleves where matriculeeleve		\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?20)) as minavg		 																									\n"
                    + "from annmoyennesrangsdeseleves \n"
                    + "where matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?21) limit 1							");
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
            query.setParameter(19, codeclass);
            query.setParameter(20, codeclass);
            query.setParameter(21, codeclass);
            query.setParameter(22, codeclass);
            query.setParameter(23, codeclass);
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
    public List<Object[]> fiches(String codeClasse) {
        Query query = em.createNativeQuery("select matriculeeleve,nom,prenom,sexe from listedeseleves where codeclasse=?1 order by nom,prenom");
        query.setParameter(1, codeClasse);
        return query.getResultList();
    }

    //reconduire les notes d'une séquence
    @Override
    public List<Object[]> notesSeq1DeLamatiere(String codeClasse, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,lanote from notesdeselevesseq1\n"
                + "where matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> notesSeq2DeLamatiere(String codeClasse, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,lanote from notesdeselevesseq2\n"
                + "where matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> notesSeq3DeLamatiere(String codeClasse, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,lanote from notesdeselevesseq3\n"
                + "where matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> notesSeq4DeLamatiere(String codeClasse, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,lanote from notesdeselevesseq4\n"
                + "where matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> notesSeq5DeLamatiere(String codeClasse, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,lanote from notesdeselevesseq5\n"
                + "where matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }

    //verifier si la ligne eleve-matiere esixte
    @Override
    public List<Object[]> verifierEleve_matiereSeq2(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevesseq2\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereSeq3(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevesseq3\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereSeq4(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevesseq4\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereSeq5(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevesseq5\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereSeq6(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevesseq6\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereTrim1(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevestrim1\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereTrim2(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevestrim2\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereTrim3(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevestrim3\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Object[]> verifierEleve_matiereAnn(String matricule, String codematiere) {
        Query query = em.createNativeQuery("select matriculeeleve,codematiere from notesdeselevesann\n"
                + "where matriculeeleve=?1 and codematiere=?2");
        query.setParameter(1, matricule);
        query.setParameter(2, codematiere);
        if (query.getResultList() != null) {
            return query.getResultList();
        } else {
            return null;
        }
    }

    //calcul auto des notes annuelles
    @Override
    public List<Object[]> noteSeq123456(String codeclass) {
        try {
            Query query = em.createNativeQuery("select l.matriculeeleve,m.codematiere,n1.lanote as n1,n2.lanote as n2,n3.lanote as n3\n"
                    + "from listedeseleves l\n"
                    + "inner join notesdeselevestrim1 n1 on n1.matriculeeleve=l.matriculeeleve \n"
                    + "inner join notesdeselevestrim2 n2 on n2.matriculeeleve=l.matriculeeleve\n"
                    + "inner join notesdeselevestrim3 n3 on n3.matriculeeleve=l.matriculeeleve\n"
                    + "inner join matieres m on m.codematiere=n1.codematiere and m.codematiere=n2.codematiere and m.codematiere=n3.codematiere\n"
                    + "where codeclasse=?1\n"
                    + "group by l.matriculeeleve,m.codematiere,n1.lanote,n2.lanote,n3.lanote\n"
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
