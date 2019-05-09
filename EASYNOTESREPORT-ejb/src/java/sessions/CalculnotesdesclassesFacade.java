/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Calculnotesdesclasses;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class CalculnotesdesclassesFacade extends AbstractFacade<Calculnotesdesclasses> implements CalculnotesdesclassesFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalculnotesdesclassesFacade() {
        super(Calculnotesdesclasses.class);
    }

    @Override
    public BigDecimal sommeCoef(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select sum(c.coefficient) as sumcoef from claMatCoeffGrpe c where c.codeclasse = ?1");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }
//moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classe(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from seq1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classe(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from seq1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classe(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from seq1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classe(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from seq1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from seq1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }

    @Override
    public boolean existe_classe_et_sequence(String codeclasse, String sequence) {
        try {
            Calculnotesdesclasses c = em.createNamedQuery("Calculnotesdesclasses.existe_Classe_Seq", Calculnotesdesclasses.class).setParameter("periode", sequence).setParameter("codeclasse", codeclasse).getSingleResult();
            if (c != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    //deuxième séquence

//moyenne d'une classe donnée
    @Override
    public BigDecimal moyenne_de_la_classeSeq2(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from seq2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeSeq2(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from seq2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeSeq2(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from seq2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeSeq2(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from seq2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from seq2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
//trosième séquence
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeSeq3(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from seq3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeSeq3(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from seq3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeSeq3(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from seq3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeSeq3(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from seq3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from seq3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
    //quatrième séquence
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeSeq4(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from seq4moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeSeq4(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from seq4moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeSeq4(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from seq4moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeSeq4(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from seq4moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from seq4moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
    //cinquième séquence
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeSeq5(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from seq5moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeSeq5(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from seq5moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeSeq5(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from seq5moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeSeq5(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from seq5moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from seq5moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
    //sixième séquence
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeSeq6(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from seq6moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeSeq6(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from seq6moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeSeq6(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from seq6moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeSeq6(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from seq6moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from seq6moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
    //premier trimestre
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeTrim1(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from trim1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeTrim1(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from trim1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeTrim1(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from trim1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeTrim1(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from trim1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from trim1moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
    //dexième trimestre
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeTrim2(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from trim2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeTrim2(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from trim2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeTrim2(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from trim2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeTrim2(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from trim2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from trim2moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
    //troisième trimestre
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeTrim3(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from trim3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeTrim3(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from trim3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeTrim3(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from trim3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeTrim3(String codeClasse) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from trim3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, 10);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from trim3moyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
    //annuel
    //moyenne d'une classe donnée

    @Override
    public BigDecimal moyenne_de_la_classeAnn(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select avg(s.moyenne) as moyclasse from annmoyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    @Override
    public BigDecimal moyenne_premier_de_la_classeAnn(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select max(s.moyenne) as moyPr from annmoyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal moyenne_dernier_de_la_classeAnn(String codeClasse) {
        Query query;
        query = em.createNativeQuery("select min(s.moyenne) as moyPr from annmoyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1)");
        query.setParameter(1, codeClasse);
        List<BigDecimal> list = query.getResultList();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return list.get(0);
        }
    }

    @Override
    public BigDecimal taux_de_reussite_de_la_classeAnn(String codeClasse, BigDecimal moySupA) {
        Query query = em.createNativeQuery("select s.moyenne as moyPr from annmoyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne >= ?2");
        query.setParameter(1, codeClasse);
        query.setParameter(2, moySupA);
        List<Object> list = query.getResultList();
        Query query1 = em.createNativeQuery("select s.moyenne as effectifclasse from annmoyennesrangsdeseleves s where s.matriculeeleve in (select l.matriculeeleve from listedeseleves l where l.codeclasse=?1) and s.moyenne is not null");
        query1.setParameter(1, codeClasse);
        List<Object> effectif = query1.getResultList();
        if (effectif.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal taux = new BigDecimal(list.size()).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(effectif.size()), 2, RoundingMode.HALF_EVEN);
            return taux;
        }
    }
}
