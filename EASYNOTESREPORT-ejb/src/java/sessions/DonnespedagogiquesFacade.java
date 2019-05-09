/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Classes;
import entities.Donnespedagogiques;
import entities.Matieres;
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
public class DonnespedagogiquesFacade extends AbstractFacade<Donnespedagogiques> implements DonnespedagogiquesFacadeLocal {

    @PersistenceContext(unitName = "EASYNOTESREPORT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DonnespedagogiquesFacade() {
        super(Donnespedagogiques.class);
    }

    @Override
    public List<Matieres> listedesMatieres(String classcorresp) {
        try {
            Query query = em.createNamedQuery("Donnespedagogiques.findMatiere");
            query.setParameter("classcorresp", classcorresp);
            List<Matieres> list = query.getResultList();
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
    public List<Donnespedagogiques> listeDesDp(String niveaux, String codematiere, String periode) {
        try {
            Query query = em.createNamedQuery("Donnespedagogiques.findByCodematiereClasse");
            query.setParameter("codeclasse", niveaux);
            query.setParameter("codematiere", codematiere);
            query.setParameter("periode", periode);
            List<Donnespedagogiques> list = query.getResultList();
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
    public List<Classes> listedesNiveaux() {
        try {
            Query query = em.createNamedQuery("Donnespedagogiques.findCodeclasse");
            List<Classes> list = query.getResultList();
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
    public List<Object[]> listedesMatieresParClasse(String classcorresp) {
        try {
            Query query = em.createNamedQuery("Donnespedagogiques.findByCodeMatiere");
            query.setParameter("classcorresp", classcorresp);
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
    public String typeClasse(String classcorresp) {
        try {
            Query query = em.createNamedQuery("Donnespedagogiques.findTypedeclasse");
            query.setParameter("classcorresp", classcorresp);
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

    @Override
    public BigDecimal effectifParNiveau(String classcorresp) {
        try {
            Query query = em.createNativeQuery("SELECT COUNT(l.matriculeeleve) "
                    + "FROM Listedeseleves l "
                    + "WHERE l.codeclasse in(SELECT c.codeclasse FROM Classes c WHERE c.classcorresp = ?1)");
            query.setParameter(1, classcorresp);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public List<Object[]> ficheExamInter(String typedeclasse, String periode, String section) {
        try {
            Query query = em.createNativeQuery("select dp.codematiere,dp.periode,sum(dp.eff) as eff,\n"
                    + "sum(dp.lpcpt) as lpcpt,sum(dp.lfcpt) as lfcpt,sum(dp.lpcpp) as lpcpp,sum(dp.lfcpp) as lfcpp,sum(dp.hpchc) as hpchc,sum(dp.hfchc) as hfchc,sum(dp.nn) as nn,round(avg(dp.mg),2) as mg,sum(dp.nae) as nae,sum(dp.nsc) as nsc\n"
                    + " from donnespedagogiques dp\n"
                    + "  where niveaux in (select classcorresp from classes where typedeclasse=?1 and section=?2) and dp.periode=?3\n"
                    + "  group by dp.codematiere,dp.periode");
            query.setParameter(1, typedeclasse);
            query.setParameter(2, section);
            query.setParameter(3, periode);
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
    public List<Object[]> ficheAPall(String periode, String section) {
        try {
            Query query = em.createNativeQuery("select dp.codematiere,dp.periode,sum(dp.eff)as eff,\n"
                    + "sum(dp.lpcpt) as lpcpt,sum(dp.lfcpt) as lfcpt,sum(dp.lpcpp) as lpcpp,sum(dp.lfcpp) as lfcpp,sum(dp.hpchc) as hpchc,sum(dp.hfchc) as hfchc,sum(dp.nn) as nn,round(avg(dp.mg),2) as mg,sum(dp.nae) as nae,sum(dp.nsc) as nsc,dp.niveaux,dp.typedeclasse\n"
                    + " from donnespedagogiques dp\n"
                    + "  where dp.periode=?1 and section=?2 \n"
                    + "  group by dp.codematiere,dp.periode, dp.niveaux,dp.typedeclasse\n"
                    + " order by dp.codematiere,dp.typedeclasse desc");
            query.setParameter(1, periode);
            query.setParameter("2", section);
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
    public List<Object[]> ficheApParMatiere(String periode, String codematiere, String section) {
        try {
            Query query = em.createNativeQuery("select dp.codematiere,dp.periode,sum(dp.eff)as eff,\n"
                    + "sum(dp.lpcpt) as lpcpt,sum(dp.lfcpt) as lfcpt,sum(dp.lpcpp) as lpcpp,sum(dp.lfcpp) as lfcpp,sum(dp.hpchc) as hpchc,sum(dp.hfchc) as hfchc,sum(dp.nn) as nn,round(avg(dp.mg),2) as mg,sum(dp.nae) as nae,sum(dp.nsc) as nsc,dp.niveaux,dp.typedeclasse\n"
                    + " from donnespedagogiques dp\n"
                    + "  where dp.periode=?1 and dp.codematiere=?2 and section=?3\n"
                    + "  group by dp.codematiere,dp.periode, dp.niveaux,dp.typedeclasse\n"
                    + " order by dp.codematiere,dp.typedeclasse desc");
            query.setParameter(1, periode);
            query.setParameter(2, codematiere);
            query.setParameter(3, section);
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
    public BigDecimal moySup10(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nn \n"
                    + "from notesdeselevesseq1 n where n.lanote>=10 \n"
                    + "				and n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal mg(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select ROUND(avg(lanote),2) as mg\n"
                    + "from notesdeselevesseq1 n where n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<BigDecimal> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal effectifAbst(String codematiere, String classcorresp) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nae from notesdeselevesseq1 \n"
                    + "   where codematiere=?1 and lanote is null \n"
                    + "   and matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse in(select codeclasse from classes\n"
                    + "											where classcorresp=?2))");
            query.setParameter(1, codematiere);
            query.setParameter(2, classcorresp);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal moySup10S2(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nn \n"
                    + "from notesdeselevesseq2 n where n.lanote>=10 \n"
                    + "				and n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal mgS2(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select ROUND(avg(lanote),2) as mg\n"
                    + "from notesdeselevesseq2 n where n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<BigDecimal> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal effectifAbstS2(String codematiere, String classcorresp) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nae from notesdeselevesseq2 \n"
                    + "   where codematiere=?1 and lanote is null \n"
                    + "   and matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse in(select codeclasse from classes\n"
                    + "											where classcorresp=?2))");
            query.setParameter(1, codematiere);
            query.setParameter(2, classcorresp);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal moySup10S3(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nn \n"
                    + "from notesdeselevesseq3 n where n.lanote>=10 \n"
                    + "				and n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal mgS3(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select ROUND(avg(lanote),2) as mg\n"
                    + "from notesdeselevesseq3 n where n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<BigDecimal> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal effectifAbstS3(String codematiere, String classcorresp) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nae from notesdeselevesseq3 \n"
                    + "   where codematiere=?1 and lanote is null \n"
                    + "   and matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse in(select codeclasse from classes\n"
                    + "											where classcorresp=?2))");
            query.setParameter(1, codematiere);
            query.setParameter(2, classcorresp);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal moySup10S4(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nn \n"
                    + "from notesdeselevesseq4 n where n.lanote>=10 \n"
                    + "				and n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal mgS4(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select ROUND(avg(lanote),2) as mg\n"
                    + "from notesdeselevesseq4 n where n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<BigDecimal> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal effectifAbstS4(String codematiere, String classcorresp) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nae from notesdeselevesseq4 \n"
                    + "   where codematiere=?1 and lanote is null \n"
                    + "   and matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse in(select codeclasse from classes\n"
                    + "											where classcorresp=?2))");
            query.setParameter(1, codematiere);
            query.setParameter(2, classcorresp);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal moySup10S5(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nn \n"
                    + "from notesdeselevesseq5 n where n.lanote>=10 \n"
                    + "				and n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal mgS5(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select ROUND(avg(lanote),2) as mg\n"
                    + "from notesdeselevesseq5 n where n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<BigDecimal> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal effectifAbstS5(String codematiere, String classcorresp) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nae from notesdeselevesseq5 \n"
                    + "   where codematiere=?1 and lanote is null \n"
                    + "   and matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse in(select codeclasse from classes\n"
                    + "											where classcorresp=?2))");
            query.setParameter(1, codematiere);
            query.setParameter(2, classcorresp);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal moySup10S6(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nn \n"
                    + "from notesdeselevesseq6 n where n.lanote>=10 \n"
                    + "				and n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal mgS6(String classcorresp, String codematiere) {
        try {
            Query query = em.createNativeQuery("select ROUND(avg(lanote),2) as mg\n"
                    + "from notesdeselevesseq6 n where n.matriculeeleve in (select matriculeeleve from listedeseleves l where l.codeclasse in(select codeclasse from classes where classcorresp=?1)) \n"
                    + "				and codematiere=?2");
            query.setParameter(1, classcorresp);
            query.setParameter(2, codematiere);
            List<BigDecimal> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return list.get(0);
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal effectifAbstS6(String codematiere, String classcorresp) {
        try {
            Query query = em.createNativeQuery("select count(matriculeeleve) as nae from notesdeselevesseq6 \n"
                    + "   where codematiere=?1 and lanote is null \n"
                    + "   and matriculeeleve in(select matriculeeleve from listedeseleves where codeclasse in(select codeclasse from classes\n"
                    + "											where classcorresp=?2))");
            query.setParameter(1, codematiere);
            query.setParameter(2, classcorresp);
            List<Long> list = query.getResultList();
            if (list.isEmpty()) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(list.get(0));
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public String section(String classcorresp) {
        try {
            Query query = em.createNativeQuery("select section from classes where classcorresp=?1 limit 1");
            query.setParameter(1, classcorresp);
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
