/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listedeseleves;
import entities.Listepassword;
import entities.Notesdeselevesseq1;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class Notesdeselevesseq1Facade extends AbstractFacade<Notesdeselevesseq1> implements Notesdeselevesseq1FacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Notesdeselevesseq1Facade() {
        super(Notesdeselevesseq1.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createNamedQuery("Notesdeselevesseq1.nexId");
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
//recherche des élèves de d'une classe à la 1ère séquence pour remplir les notes

    @Override
    public List<Notesdeselevesseq1> listeMatiereClasse(String codeMatiere, String codeClasse) {
        try {
            Query query = em.createNamedQuery("Notesdeselevesseq1.findMatiereClasse");
            query.setParameter("codematiere", codeMatiere);
            query.setParameter("codeclasse", codeClasse);
            List<Notesdeselevesseq1> list = query.getResultList();
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
    public List<Listedeseleves> listeDesNouveauxEleves(String codeMatiere, String codeclasse) {
        try {
            Query query = em.createNamedQuery("Notesdeselevesseq1.findNouveauxEleves");
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
//approvisionner le selectOneListbox en Matières de la classe

    @Override
    public List<Object[]> listedesMatieresParClasse(String codeClas) {
        try {
            Query query = em.createNamedQuery("Clamatcoeffgrpe.findByCodeMatiere");
            query.setParameter("codeclasse", codeClas);
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

    //recherche d'un enseignant par son mot de passe
    @Override
    public boolean findEnseignatByPwd(String password, String codematiere) {
        try {
            Query query = em.createNativeQuery("SELECT l FROM Listepassword l WHERE l.password = ?1 and specialite=(select codespecialite from matieres where codematiere=?2)");
            query.setParameter(1, password);
            query.setParameter(2, codematiere);
            List<Listepassword> u = query.getResultList();
            if (!u.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
//calcul moyenne de la classe

    @Override
    public BigDecimal findMoyenne(String codeMatiere, String codeclasse) {
        try {
            Query query;
            query = em.createNamedQuery("Notesdeselevesseq1.findAll2");
            query.setParameter("codeclasse", codeclasse);
            query.setParameter("codematiere", codeMatiere);
            List<Notesdeselevesseq1> eleves_de_la_classe = query.getResultList();
            Iterator i = eleves_de_la_classe.iterator();
            Notesdeselevesseq1 el;
            BigDecimal sum = BigDecimal.valueOf(0);
            while (i.hasNext()) {
                el = (Notesdeselevesseq1) i.next();
                sum = sum.add(el.getLanote());
            }
            BigDecimal denomin = new BigDecimal(eleves_de_la_classe.size());
            BigDecimal moy = sum.divide(denomin, 2, RoundingMode.CEILING);
            if (moy == null) {
                return null;
            } else {
                return moy;
            }
        } catch (Exception e) {
            return null;
        }
    }
    //recherhe grande Note

    @Override
    public BigDecimal findGandeMoyenne(String codeMatiere, String codeclasse) {
        Query query;
        query = em.createNamedQuery("Notesdeselevesseq1.findMoyenneMax");
        query.setParameter("codeclasse", codeclasse);
        query.setParameter("codematiere", codeMatiere);
        BigDecimal moyMax = (BigDecimal) query.getSingleResult();
        if (moyMax == null) {
            return null;
        } else {
            return moyMax;
        }
    }
    //recherhe Petite Notee

    @Override
    public BigDecimal findPetiteMoyenne(String codeMatiere, String codeclasse) {
        Query query;
        query = em.createNamedQuery("Notesdeselevesseq1.findMoyenneMin");
        query.setParameter("codeclasse", codeclasse);
        query.setParameter("codematiere", codeMatiere);
        BigDecimal moyMin = (BigDecimal) query.getSingleResult();
        if (moyMin == null) {
            return null;
        } else {
            return moyMin;
        }
    }
    //calcul Taux de reussite de la classe

    @Override
    public BigDecimal findTauxreussite(String codeMatiere, String codeclasse) {
        try {
            Query query;
            Query query1;
            query = em.createNamedQuery("Notesdeselevesseq1.findTauxReussite");
            query.setParameter("codeclasse", codeclasse);
            query.setParameter("codematiere", codeMatiere);
            query.setParameter("lanote", BigDecimal.valueOf(10));
            List<Notesdeselevesseq1> effectif_ayant_la_moyenne = query.getResultList();
            query1 = em.createNamedQuery("Notesdeselevesseq1.findAll2");
            query1.setParameter("codeclasse", codeclasse);
            query1.setParameter("codematiere", codeMatiere);
            List<Notesdeselevesseq1> effectif_tottal = query1.getResultList();
            BigDecimal num = new BigDecimal(effectif_ayant_la_moyenne.size()).multiply(BigDecimal.valueOf(100));
            BigDecimal denomin = new BigDecimal(effectif_tottal.size());
            BigDecimal taux = num.divide(denomin, 2, RoundingMode.CEILING);
            return taux;
        } catch (Exception e) {
            return null;
        }
    }

    //selection de la note d'un élève pour modificationtrimestrielle et annuelle
    @Override
    public BigDecimal noteEleve(String matriculeEleve, String codeMatiere) {
        Query query = em.createNamedQuery("Notesdeselevesseq1.findNoteEleve");
        query.setParameter("matricule", matriculeEleve);
        query.setParameter("codematiere", codeMatiere);
        Notesdeselevesseq1 list = (Notesdeselevesseq1) query.getSingleResult();
        if (list.getLanote() == null) {
            return null;
        } else {
            return list.getLanote();
        }
    }

    //effectif n'ayant pas composé
    @Override
    public int eleveAbsent(String codeMatiere, String codeclasse) {
        Query query = em.createNamedQuery("Notesdeselevesseq1.findAll1");
        query.setParameter("codeclasse", codeclasse);
        query.setParameter("codematiere", codeMatiere);
        List<Notesdeselevesseq1> list = query.getResultList();
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    //effectif n'ayant pas composé
    @Override
    public int eleveComposé(String codeMatiere, String codeclasse) {
        Query query = em.createNamedQuery("Notesdeselevesseq1.findAll2");
        query.setParameter("codeclasse", codeclasse);
        query.setParameter("codematiere", codeMatiere);
        List<Notesdeselevesseq1> list = query.getResultList();
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }
    //deuxième partie concernant la saisie Par Elève

    //selection des matières et leur code pour un élève donné
    @Override
    public List<Object[]> listedesMatieresParClasseDeEleve(String codeClas) {
        try {
            Query query = em.createNativeQuery("select m.codematiere, m.nommat\n"
                    + "from matieres m\n"
                    + "inner join ClaMatCoeffGrpe c on c.codematiere=m.codematiere\n"
                    + "where c.codeclasse=?1");
            query.setParameter(1, codeClas);
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

    //virifier lors de la saisie par matière si l'élève a déja sa matière
    @Override
    public boolean existEleve_et_sa_matiere(String matriculeEleve, String codeMatiere) {
        try {
            Notesdeselevesseq1 n = em.createNamedQuery("Notesdeselevesseq1.findNoteEleve", Notesdeselevesseq1.class).setParameter("matricule", matriculeEleve).setParameter("codematiere", codeMatiere).getSingleResult();
            if (n != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //charger la liste des matières d'un élève
    @Override
    public List<Notesdeselevesseq1> Eleve_et_sa_matiere(String matriculeEleve) {
        Query query = em.createNamedQuery("Notesdeselevesseq1.findByMatriculeeleve");
        query.setParameter("matriculeeleve", matriculeEleve);
        List<Notesdeselevesseq1> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    //liste des élèves par classe pour la saisie des notes par matière
    @Override
    public List<Notesdeselevesseq1> liste_des_eleves_par_classe(String codeclasse) {
        Query query = em.createNamedQuery("Notesdeselevesseq1.findAll");
        query.setParameter("codeclasse", codeclasse);
        List<Notesdeselevesseq1> liste = query.getResultList();
        return liste;
    }

    //bulletins des élèves séquence 1
    @Override
    public List<Object[]> bulletinSeq1(String codeclass) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns.lanote,\n"
                    + "sm.moyenne as moyenneeleve,sm.rang as rang_classe_eleve,sm.moyg1,sm.moyg2,sm.moyg3,sm.rangg1,sm.rangg2,sm.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,',')\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='SEQ1'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq1 ns on ns.matriculeeleve=l.matriculeeleve and m.codematiere=ns.codematiere\n"
                    + " inner join seq1moyennesrangsdeseleves sm on sm.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='SEQ1'\n"
                    + " inner join seq1rangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevesseq1 d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join notesdeselevesseq1 n on n.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7\n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "en.matriculeenseignant,ns.lanote,sm.moyenne,sm.rang,sm.moyg1,sm.moyg2,sm.moyg3,sm.rangg1,sm.rangg2,sm.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne,sr.rang,p.nom,p.prenom,d.absj,d.absn,d.consigne,d.exclusion\n"
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
    public List<Object[]> bulletinSeq1ParEleve(String codeclass, String matriculeEleve) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns.lanote,\n"
                    + "sm.moyenne as moyenneeleve,sm.rang as rang_classe_eleve,sm.moyg1,sm.moyg2,sm.moyg3,sm.rangg1,sm.rangg2,sm.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,',')\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='SEQ1'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq1 ns on ns.matriculeeleve=l.matriculeeleve and m.codematiere=ns.codematiere\n"
                    + " inner join seq1moyennesrangsdeseleves sm on sm.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='SEQ1'\n"
                    + " inner join seq1rangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevesseq1 d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join notesdeselevesseq1 n on n.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7 and l.matriculeeleve=?8\n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "en.matriculeenseignant,ns.lanote,sm.moyenne,sm.rang,sm.moyg1,sm.moyg2,sm.moyg3,sm.rangg1,sm.rangg2,sm.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne,sr.rang,p.nom,p.prenom,d.absj,d.absn,d.consigne,d.exclusion\n"
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
    public List<Object[]> bulletinSeq1ParSerie(String codeclass, String matriculeEleve, String matriculeEleveA) {
        try {
            Query query = em.createNativeQuery("select c.codeclasse,\n"
                    + "l.matriculeeleve,concat(l.nom,' ',l.prenom) as nomeleve ,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,\n"
                    + "cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne as moyenneclasse,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "concat(p.nom,' ' ,p.prenom) as nomprof,\n"
                    + "ns.lanote,\n"
                    + "sm.moyenne as moyenneeleve,sm.rang as rang_classe_eleve,sm.moyg1,sm.moyg2,sm.moyg3,sm.rangg1,sm.rangg2,sm.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne as moyenne_note,\n"
                    + "sr.rang as rang_eleve,\n"
                    + "d.absj,d.absn,d.consigne,d.exclusion,string_agg(distinct case when n.lanote<10 then n.codematiere else null end,',')\n"
                    + "from classes c\n"
                    + " inner join listedeseleves l on c.codeclasse=l.codeclasse\n"
                    + " inner join clamatcoeffgrpe cl on c.codeclasse=cl.codeclasse\n"
                    + " inner join matieres m on m.codematiere=cl.codematiere\n"
                    + " inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='SEQ1'\n"
                    + " inner join ensgclamat en on en.codeclasse=c.codeclasse and en.codematiere=m.codematiere\n"
                    + " inner join notesdeselevesseq1 ns on ns.matriculeeleve=l.matriculeeleve and m.codematiere=ns.codematiere\n"
                    + " inner join seq1moyennesrangsdeseleves sm on sm.matriculeeleve=l.matriculeeleve\n"
                    + " inner join personnels p on p.matricule=en.matriculeenseignant\n"
                    + " inner join notesminmaxgenparmat nm on nm.codeclasse=c.codeclasse and  m.codematiere=nm.codematiere and nm.periode='SEQ1'\n"
                    + " inner join seq1rangdeselevesparmatiere sr on sr.matriculeeleve=l.matriculeeleve and sr.codeclasse=c.codeclasse and  m.codematiere=sr.codematiere\n"
                    + " inner join disciplinesdeselevesseq1 d on d.matriculeeleve=l.matriculeeleve\n"
                    + " inner join notesdeselevesseq1 n on n.matriculeeleve=l.matriculeeleve\n"
                    + "where c.codeclasse = ?1 and l.codeclasse = ?2 and cl.codeclasse = ?3 and ca.codeclasse = ?4 and en.codeclasse = ?5 and nm.codeclasse = ?6 and sr.codeclasse = ?7 and l.matriculeeleve between ?8 and ?9 \n"
                    + "group by l.matriculeeleve,c.codeclasse,l.nom,l.prenom,l.sexe,l.datenaiss,l.lieunaiss,l.adresse,l.redoublant,l.photo,cl.codematiere,cl.coefficient,cl.groupe,\n"
                    + "ca.periode,ca.moyenne,ca.moyennepremier,ca.moyennedernier,ca.tauxreussite,\n"
                    + "en.matriculeenseignant,ns.lanote,sm.moyenne,sm.rang,sm.moyg1,sm.moyg2,sm.moyg3,sm.rangg1,sm.rangg2,sm.rangg3,\n"
                    + "nm.notemin,nm.notemax,nm.moyenne,sr.rang,p.nom,p.prenom,d.absj,d.absn,d.consigne,d.exclusion\n"
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

    public List<BigDecimal> borneTravail() {
        Query query = em.createNativeQuery("select valeurinfos from etablissementinfos order by id");
        List<BigDecimal> list = query.getResultList();
        return list;
    }

    @Override
    public Long effectifclasse(String codeclasse) {
        Query query = em.createNativeQuery("select count(matriculeeleve) as eff from listedeseleves where codeclasse= ?1");
        query.setParameter(1, codeclasse);
        Long eff = (Long) query.getSingleResult();
        return eff;
    }

    @Override
    public String pp(String codeclasse) {
        try {
            Query query = em.createNativeQuery("select concat(p.nom,' ',p.prenom) as nompp\n"
                    + "from personnels p\n"
                    + "where p.matricule=(select matriculeenseignant from profprinc where codeclasse = ?1)");
            query.setParameter(1, codeclasse);
            String pp = (String) query.getSingleResult();
            if (pp != null) {
                return pp;
            } else {
                return " ";
            }
        } catch (Exception e) {
            return " ";
        }
    }

    @Override
    public List<Object[]> ppEts() {
        Query query = em.createNativeQuery("select * from proprietesets");
        List<Object[]> ppets = query.getResultList();
        return ppets;
    }

    @Override
    public List<String> listeElevesParClasse(String codeclasse) {
        Query query = em.createNativeQuery("select concat(nom,' ',prenom,'(',matriculeeleve,')') as nomeleve from listedeseleves where codeclasse=?1 order by nom");
        query.setParameter(1, codeclasse);
        List<String> nomPrenom = query.getResultList();
        return nomPrenom;
    }

    @Override
    public List<Object[]> pvSeq1(String codeclass) {
        try {
            Query query = em.createNativeQuery("select concat(l.nom,' ',l.prenom) as nomeleve,l.matriculeeleve,l.sexe,to_char(l.datenaiss,'YYYY'),\n"
                    + "sm1.moyenne as moyenne1,sm1.rang,ca.moyenne as moyenne_classe,ca.tauxreussite\n"
                    + "from Classes c\n"
                    + "inner join listedeseleves l on l.codeclasse=c.codeclasse\n"
                    + "inner join seq1moyennesrangsdeseleves sm1 on sm1.matriculeeleve=l.matriculeeleve\n"
                    + "inner join calculnotesdesclasses ca on ca.codeclasse=c.codeclasse and ca.periode='SEQ1'\n"
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
    public List<Object[]> synthSeq1(String codeclass) {
        try {
            Query query = em.createNativeQuery("select n.codematiere,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote is not null and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?1) and codematiere=n.codematiere) as effectif,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 0 and 5.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?2) and codematiere=n.codematiere) as eff0_6,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 6 and 7.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?3) and codematiere=n.codematiere) as eff6_8,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 8 and 8.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?4) and codematiere=n.codematiere) as eff8_9,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 9 and 9.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?5) and codematiere=n.codematiere) as eff9_10,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 10 and 11.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?6) and codematiere=n.codematiere) as eff10_12,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 12 and 13.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?7) and codematiere=n.codematiere) as eff12_14,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 14 and 15.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?8) and codematiere=n.codematiere) as eff14_16,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 16 and 17.99 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?9) and codematiere=n.codematiere) as eff16_18,\n"
                    + "       (select count(*)\n"
                    + "       from notesdeselevesseq1 where lanote between 18 and 20 and matriculeeleve\n"
                    + "       in (select matriculeeleve from listedeseleves where codeclasse=?10) and codematiere=n.codematiere) as eff18_20\n"
                    + "from notesdeselevesseq1 n\n"
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
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 0 and 5.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?1 and sexe='F')) as eff0_5,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 6 and 7.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?2 and sexe='F')) as eff6_7,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 8 and 8.999 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?3 and sexe='F')) as eff8_8,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 9 and 9.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?4 and sexe='F')) as eff9_9,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 10 and 11.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?5 and sexe='F')) as eff10_11,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 12 and 13.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?6 and sexe='F')) as eff12_13,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 14 and 15.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?7 and sexe='F')) as eff14_15,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 16 and 17.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?8 and sexe='F')) as eff16_17,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 18 and 20 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?9 and sexe='F')) as eff18_20,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 0 and 5.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?10 and sexe='G')) as eff0_5,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 6 and 7.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=? and sexe='G')) as eff6_7,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 8 and 8.999 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?12 and sexe='G')) as eff8_8,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 9 and 9.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?13 and sexe='G')) as eff9_9,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 10 and 11.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?14 and sexe='G')) as eff10_11,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 12 and 13.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?15 and sexe='G')) as eff12_13,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 14 and 15.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?16 and sexe='G')) as eff14_15,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 16 and 17.99 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?17 and sexe='G')) as eff16_17,\n"
                    + "(select count(matriculeeleve) from seq1moyennesrangsdeseleves where moyenne between 18 and 20 and matriculeeleve in (select matriculeeleve from listedeseleves where codeclasse=?18 and sexe='G')) as eff18_20");
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
            Query query = em.createNativeQuery("select moyenne,moyennepremier,moyennedernier from calculnotesdesclasses where codeclasse=?1 and periode ='SEQ1' order by periode");
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
    public List<Object[]> noteElevesSeq1(String codeclass) {
        try {
            Query query = em.createNativeQuery("select l.matriculeeleve,s.moyenne,n.codematiere,n.lanote\n"
                    + "from listedeseleves l\n"
                    + "inner join notesdeselevesseq1 n on n.matriculeeleve=l.matriculeeleve\n"
                    + "inner join seq1moyennesrangsdeseleves s on s.matriculeeleve=l.matriculeeleve\n"
                    + "where l.codeclasse=?1\n"
                    + "order by s.moyenne desc");
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
