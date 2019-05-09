/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Classes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import sessions.CalculnotesdesclassesFacadeLocal;
import sessions.Seq1moyennesrangsdeselevesFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "managedNoteSeqController")
@SessionScoped
public class ManagedNoteSeqController implements Serializable {

    @EJB
    private Seq1moyennesrangsdeselevesFacadeLocal seq1moyennesrangFacde;
    @EJB
    private CalculnotesdesclassesFacadeLocal calculNoteFacade;
    public static String seq = "";
    public static Classes classe = null;
    public static String pwd;
    public static BigDecimal seuil = null;
    public static BigDecimal moyPourPasser = BigDecimal.TEN;
    private boolean testSeq = false;
    public static BigDecimal coef = null;

    public ManagedNoteSeqController() {
    }

    public String calcul() {
        return "calculnote.xhtml?faces-redirect=true";
    }

    public BigDecimal sumcoefficient() {
        try {
            BigDecimal totalCoef = calculNoteFacade.sommeCoef(classe.toString());
            return totalCoef;
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public void calculTauxAnnuel() {
        if ("ANN".equals(seq)) {
            testSeq = true;
        } else {
            testSeq = false;
        }
    }

    public Seq1moyennesrangsdeselevesFacadeLocal getSeq1moyennesrangFacde() {
        return seq1moyennesrangFacde;
    }

    public void setSeq1moyennesrangFacde(Seq1moyennesrangsdeselevesFacadeLocal seq1moyennesrangFacde) {
        this.seq1moyennesrangFacde = seq1moyennesrangFacde;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        ManagedNoteSeqController.seq = seq;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        ManagedNoteSeqController.classe = classe;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        ManagedNoteSeqController.pwd = pwd;
    }

    public BigDecimal getSeuil() {
        return seuil;
    }

    public void setSeuil(BigDecimal seuil) {
        ManagedNoteSeqController.seuil = seuil;
    }

    public BigDecimal getCoef() {
        return coef;
    }

    public void setCoef(BigDecimal coef) {
        ManagedNoteSeqController.coef = coef;
    }

    public BigDecimal getMoyPourPasser() {
        return moyPourPasser;
    } 

    public void setMoyPourPasser(BigDecimal moyPourPasser) {
        this.moyPourPasser = moyPourPasser;
    }

    public boolean isTestSeq() {
        return testSeq;
    }

    public void setTestSeq(boolean testSeq) {
        this.testSeq = testSeq;
    }

}
