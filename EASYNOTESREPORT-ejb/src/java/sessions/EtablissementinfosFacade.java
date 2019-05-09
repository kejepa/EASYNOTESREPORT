/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Etablissementinfos;
import java.math.BigDecimal;
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
public class EtablissementinfosFacade extends AbstractFacade<Etablissementinfos> implements EtablissementinfosFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtablissementinfosFacade() {
        super(Etablissementinfos.class);
    }
//activation et désactivation des séquences

    @Override
    public BigDecimal acti_deative_seq() {
        Query query = em.createNamedQuery("Etablissementinfos.findBycleseq");
        query.setParameter("seq", "SEQUENCES_ACTIVES");
        List<Etablissementinfos> etslist = query.getResultList();
        Iterator i = etslist.iterator();
        Etablissementinfos ets;
        BigDecimal m = null;
        while (i.hasNext()) {
            ets = (Etablissementinfos) i.next();
            m = (ets.getValeurinfos());
        }
        return m;
    }

    //recuperer les valeurs de bornes de travail et discipline pour affichage
    @Override
    public List<Etablissementinfos> valeursinfos() {
        try {
            Query query = em.createNamedQuery("Etablissementinfos.findValeursInfos");
            query.setParameter("id", 1);
            List<Etablissementinfos> etslist = query.getResultList();
            if (etslist.isEmpty()) {
                return null;
            } else {
                return etslist;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public BigDecimal borneAvertissement() {
        Query query = em.createNamedQuery("Etablissementinfos.findBorneAV");
        query.setParameter("id1", 4);
        BigDecimal av = (BigDecimal) query.getSingleResult();
        return av;
    }

    @Override
    public BigDecimal borneBlame() {
        Query query = em.createNamedQuery("Etablissementinfos.findBorneBL");
        query.setParameter("id2", 5);
        BigDecimal bl = (BigDecimal) query.getSingleResult();
        return bl;
    }
}
