/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Notesminmaxgenparmat;
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
public class NotesminmaxgenparmatFacade extends AbstractFacade<Notesminmaxgenparmat> implements NotesminmaxgenparmatFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotesminmaxgenparmatFacade() {
        super(Notesminmaxgenparmat.class);
    }

    @Override
    public List<Object[]> note_min_max_moy_par_matiereSeq1(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevesseq1 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    @Override
    public boolean existe_classe_et_sequence_et_matiere(String codeclasse, String sequence, String matiere) {
        try {
            Notesminmaxgenparmat c = em.createNamedQuery("Notesminmaxgenparmat.findSeq_Matiere_Classe", Notesminmaxgenparmat.class).setParameter("periode", sequence).setParameter("codeclasse", codeclasse).setParameter("codematiere", matiere).getSingleResult();
            if (c != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    //seq2
    @Override
    public List<Object[]> note_min_max_moy_par_matiereSeq2(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevesseq2 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    //seq3
    @Override
    public List<Object[]> note_min_max_moy_par_matiereSeq3(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevesseq3 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    // seq4
    @Override
    public List<Object[]> note_min_max_moy_par_matiereSeq4(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevesseq4 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    //SEQ5
    @Override
    public List<Object[]> note_min_max_moy_par_matiereSeq5(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevesseq5 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    //SEQ6
    @Override
    public List<Object[]> note_min_max_moy_par_matiereSeq6(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevesseq6 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    //TRIM1
    @Override
    public List<Object[]> note_min_max_moy_par_matiereTrim1(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevestrim1 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    //TRIM2
    @Override
    public List<Object[]> note_min_max_moy_par_matiereTrim2(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevestrim2 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    //TRIM3
    @Override
    public List<Object[]> note_min_max_moy_par_matiereTrim3(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevestrim3 n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }

    //ANN
    @Override
    public List<Object[]> note_min_max_moy_par_matiereAnn(String codeclasse) {
        Query query = em.createNativeQuery("select n.codematiere, max(n.lanote),min(n.lanote),avg(n.lanote)\n"
                + "from notesdeselevesann n where n.lanote is not null and n.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)\n"
                + "group by n.codematiere");
        query.setParameter(1, codeclasse);
        List<Object[]> note_min_max_moy_par_matiere = query.getResultList();
        if (note_min_max_moy_par_matiere != null) {
            return note_min_max_moy_par_matiere;
        } else {
            return null;
        }
    }
}
