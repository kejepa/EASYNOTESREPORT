/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Trim1rangdeselevesparmatiere;
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
public class Trim1rangdeselevesparmatiereFacade extends AbstractFacade<Trim1rangdeselevesparmatiere> implements Trim1rangdeselevesparmatiereFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Trim1rangdeselevesparmatiereFacade() {
        super(Trim1rangdeselevesparmatiere.class);
    }

    @Override
    public List<String> trim1_matiere(String codeclasse) {
        Query query = em.createNativeQuery("select distinct n.codematiere \n"
                + "from notesdeselevestrim1 n \n"
                + "inner join listedeseleves l on l.matriculeeleve=n.matriculeeleve\n"
                + "where l.codeclasse = ?1");
        query.setParameter(1, codeclasse);
        List<String> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public List<Object[]> trim1_rang_eleve_par_matiere(String codeclasse, String codematiere) {
        Query query = em.createNativeQuery("select n1.codematiere,n1.matriculeeleve,n1.codeclasse, count(n2.lanote) as rang,n1.lanote\n"
                + "from (select n.matriculeeleve,l.codeclasse, n.codematiere, n.lanote\n"
                + "      from notesdeselevestrim1 n\n"
                + "      inner join listedeseleves l on l.matriculeeleve=n.matriculeeleve\n"
                + "      where l.codeclasse=?1 and n.codematiere=?2 and n.lanote is not null) n1,\n"
                + "      (select n.matriculeeleve,l.codeclasse, n.codematiere, n.lanote\n"
                + "      from notesdeselevestrim1 n\n"
                + "      inner join listedeseleves l on l.matriculeeleve=n.matriculeeleve\n"
                + "      where l.codeclasse=?3 and n.codematiere=?4 and n.lanote is not null) n2\n"
                + "where n1.lanote<n2.lanote or (n1.lanote=n2.lanote and n1.matriculeeleve=n2.matriculeeleve and n1.codematiere=n2.codematiere)\n"
                + "group by  n1.codeclasse,n1.codematiere,n1.matriculeeleve,n1.lanote\n"
                + "order by n1.lanote,n1.matriculeeleve,n1.codematiere");
        query.setParameter(1, codeclasse);
        query.setParameter(2, codematiere);
        query.setParameter(3, codeclasse);
        query.setParameter(4, codematiere);
        List<Object[]> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
//eleve par matiere avec note  null

    @Override
    public List<Object[]> trim1_rang_eleve_par_matiere_avec_note_nulle(String codeclasse, String codematiere) {
        Query query = em.createNativeQuery("select n1.codematiere,n1.matriculeeleve,n1.codeclasse\n"
                + "                from (select n.matriculeeleve,l.codeclasse, n.codematiere, n.lanote\n"
                + "                     from notesdeselevestrim1 n\n" 
                + "                     inner join listedeseleves l on l.matriculeeleve=n.matriculeeleve\n"
                + "                     where l.codeclasse=?1 and n.codematiere=?2 and n.lanote is null) n1\n"
                + "group by  n1.codeclasse,n1.codematiere,n1.matriculeeleve,n1.lanote\n"
                + "order by n1.lanote,n1.matriculeeleve,n1.codematiere");
        query.setParameter(1, codeclasse);
        query.setParameter(2, codematiere);
        List<Object[]> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public boolean existe_classe_et_eleve_et_matiere(String codematiere, String matricule, String codeclasse) {
        try {
            Trim1rangdeselevesparmatiere c = em.createNamedQuery("Trim1rangdeselevesparmatiere.rang_eleve_par_matiere", Trim1rangdeselevesparmatiere.class).setParameter("codematiere", codematiere).setParameter("matriculeeleve", matricule).setParameter("codeclasse", codeclasse).getSingleResult();
            if (c != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
