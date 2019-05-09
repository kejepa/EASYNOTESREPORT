/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Etablissementinfos;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import sessions.EtablissementinfosFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "etsInfoController")
@SessionScoped
public class EtsInfoController implements Serializable {

    @EJB
    private EtablissementinfosFacadeLocal etsFavcade;
    private Etablissementinfos ets;
    //atributs pour aciver_désactiver
    private boolean seq1;
    private boolean seq2;
    private boolean seq3;
    private boolean seq4;
    private boolean seq5;
    private boolean seq6;
    private boolean modifierSeq = true;
    private boolean btnModifier = true;
    private boolean btnEnregistrer = false;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    //attributs pour fixation des bornes
    private BigDecimal avertissementTravail;
    private BigDecimal blameTravail;
    private BigDecimal avertissementDiscipline;
    private BigDecimal blameDiscipline;
    private boolean modifierSeqTravail = true;
    private boolean btnModifierTravail = true;
    private boolean btnEnregistrerTravail = false;

    public EtsInfoController() {
    }
//recupreration des valeurs à afficher dans le

    public String valeursinfos() {
        List<Etablissementinfos> etslist = etsFavcade.valeursinfos();
        Iterator i = etslist.iterator();
        Etablissementinfos et;
        List<BigDecimal> iList = new ArrayList<>();
        while (i.hasNext()) {
            et = (Etablissementinfos) i.next();
            iList.add(et.getValeurinfos());
        }
        avertissementTravail = iList.get(0);
        blameTravail = iList.get(1);
        avertissementDiscipline = iList.get(2);
        blameDiscipline = iList.get(3);
        return "activersequence.xhtml?faces-redirect=true";
    }

    public String enrefistrerFixation() {
        int i = 2;
        for (i = 2; i < 6; i++) {
            ets = new Etablissementinfos();
            ets.setId(i);
            switch (i) {
                case 2:
                    ets.setCleinfos("BORNES_AVERT_TRAVAIL");
                    ets.setValeurinfos(avertissementTravail);
                    break;
                case 3:
                    ets.setCleinfos("BORNES_BLAME_TRAVAIL");
                    ets.setValeurinfos(blameTravail);
                    break;
                case 4:
                    ets.setCleinfos("BORNES_AVERT_DISCIPLINE");
                    ets.setValeurinfos(avertissementDiscipline);
                    break;
                case 5:
                    ets.setCleinfos("BORNES_BLAME_DISCIPLINE");
                    ets.setValeurinfos(blameDiscipline);
                    break;
            }
            etsFavcade.edit(ets);
        }
        modifierSeqTravail = true;
        btnModifierTravail = true;
        btnEnregistrerTravail = false;

        return "activersequence.xhtml?faces-redirect=true";
    }

    public String modifierBorneFixation() {
        modifierSeqTravail = false;
        btnModifierTravail = false;
        btnEnregistrerTravail = true;
        return valeursinfos();
    }

    public void pouraccueil() {  
        BigDecimal valeurBigDecimal = etsFavcade.acti_deative_seq();
        String valeur = valeurBigDecimal.toString();
        s1 = valeur.substring(0, 1);
        s2 = valeur.substring(1, 2);
        s3 = valeur.substring(2, 3);
        s4 = valeur.substring(3, 4);
        s5 = valeur.substring(4, 5);
        s6 = valeur.substring(5, 6);
        switch (s1) {
            case "1":
                seq1 = false;
                break;
            case "2":
                seq1 = true;
        }
        switch (s2) {
            case "1":
                seq2 = false;
                break;
            case "2":
                seq2 = true;
        }
        switch (s3) {
            case "1":
                seq3 = false;
                break;
            case "2":
                seq3 = true;
        }
        switch (s4) {
            case "1":
                seq4 = false;
                break;
            case "2":
                seq4 = true;
        }
        switch (s5) {
            case "1":
                seq5 = false;
                break;
            case "2":
                seq5 = true;
        }
        switch (s6) {
            case "1":
                seq6 = false;
                break;
            case "2":
                seq6 = true;
        }
    }

    public String modifiersequence() {
        modifierSeq = false;
        btnEnregistrer = true;
        btnModifier = false;
        BigDecimal valeurBigDecimal = etsFavcade.acti_deative_seq();
        String valeur = valeurBigDecimal.toString();
        s1 = valeur.substring(0, 1);
        s2 = valeur.substring(1, 2);
        s3 = valeur.substring(2, 3);
        s4 = valeur.substring(3, 4);
        s5 = valeur.substring(4, 5);
        s6 = valeur.substring(5, 6);
        switch (s1) {
            case "1":
                seq1 = false; 
                break;
            case "2":
                seq1 = true;
        }
        switch (s2) {
            case "1":
                seq2 = false;
                break;
            case "2":
                seq2 = true;
        }
        switch (s3) {
            case "1":
                seq3 = false;
                break;
            case "2":
                seq3 = true;
        }
        switch (s4) {
            case "1":
                seq4 = false;
                break;
            case "2":
                seq4 = true;
        }
        switch (s5) {
            case "1":
                seq5 = false;
                break;
            case "2":
                seq5 = true;
        }
        switch (s6) {
            case "1":
                seq6 = false;
                break;
            case "2":
                seq6 = true; 
        }
        return "activersequence.xhtml?faces-redirect=true";
    }
// click sur fixer les bornes ou activer séquences

    public String etsinfos() {
        modifierSeqTravail = true;
        btnModifierTravail = true;
        btnEnregistrerTravail = false;
        modifiersequence();
        modifierSeq = true;
        btnEnregistrer = false;
        btnModifier = true;
        return valeursinfos();
    }

    public String acti_deative_seq() {
        if (seq1 == true) {
            s1 = "2";
        } else {
            s1 = "1";
        }
        if (seq2 == true) {
            s2 = "2";
        } else {
            s2 = "1";
        }
        if (seq3 == true) {
            s3 = "2";
        } else {
            s3 = "1";
        }
        if (seq4 == true) {
            s4 = "2";
        } else {
            s4 = "1";
        }
        if (seq5 == true) {
            s5 = "2";
        } else {
            s5 = "1";
        }
        if (seq6 == true) {
            s6 = "2";
        } else {
            s6 = "1";
        }
        String valeur = s1 + "" + s2 + "" + s3 + "" + s4 + "" + s5 + "" + s6;
        ets = new Etablissementinfos();
        ets.setId(1);
        ets.setCleinfos("SEQUENCES_ACTIVES");
        ets.setValeurinfos(new BigDecimal(valeur));
        etsFavcade.edit(ets);
        modifierSeq = true;
        btnEnregistrer = false;
        btnModifier = true;
        return "activersequence.xhtml?faces-redirect=true";
    }

    public boolean isSeq1() {
        return seq1;
    }

    public void setSeq1(boolean seq1) {
        this.seq1 = seq1;
    }

    public boolean isSeq2() {
        return seq2;
    }

    public void setSeq2(boolean seq2) {
        this.seq2 = seq2;
    }

    public boolean isSeq3() {
        return seq3;
    }

    public void setSeq3(boolean seq3) {
        this.seq3 = seq3;
    }

    public boolean isSeq4() {
        return seq4;
    }

    public void setSeq4(boolean seq4) {
        this.seq4 = seq4;
    }

    public boolean isSeq5() {
        return seq5;
    }

    public void setSeq5(boolean seq5) {
        this.seq5 = seq5;
    }

    public boolean isSeq6() {
        return seq6;
    }

    public void setSeq6(boolean seq6) {
        this.seq6 = seq6;
    }

    public boolean isModifierSeq() {
        return modifierSeq;
    }

    public void setModifierSeq(boolean modifierSeq) {
        this.modifierSeq = modifierSeq;
    }

    public EtablissementinfosFacadeLocal getEtsFavcade() {
        return etsFavcade;
    }

    public void setEtsFavcade(EtablissementinfosFacadeLocal etsFavcade) {
        this.etsFavcade = etsFavcade;
    }

    public Etablissementinfos getEts() {
        return ets;
    }

    public void setEts(Etablissementinfos ets) {
        this.ets = ets;
    }

    public boolean isBtnModifier() {
        return btnModifier;
    }

    public void setBtnModifier(boolean btnModifier) {
        this.btnModifier = btnModifier;
    }

    public boolean isBtnEnregistrer() {
        return btnEnregistrer;
    }

    public void setBtnEnregistrer(boolean btnEnregistrer) {
        this.btnEnregistrer = btnEnregistrer;
    }

    public BigDecimal getAvertissementTravail() {
        return avertissementTravail;
    }

    public void setAvertissementTravail(BigDecimal avertissementTravail) {
        this.avertissementTravail = avertissementTravail;
    }

    public BigDecimal getBlameTravail() {
        return blameTravail;
    }

    public void setBlameTravail(BigDecimal blameTravail) {
        this.blameTravail = blameTravail;
    }

    public BigDecimal getAvertissementDiscipline() {
        return avertissementDiscipline;
    }

    public void setAvertissementDiscipline(BigDecimal avertissementDiscipline) {
        this.avertissementDiscipline = avertissementDiscipline;
    }

    public BigDecimal getBlameDiscipline() {
        return blameDiscipline;
    }

    public void setBlameDiscipline(BigDecimal blameDiscipline) {
        this.blameDiscipline = blameDiscipline;
    }

    public boolean isBtnModifierTravail() {
        return btnModifierTravail;
    }

    public void setBtnModifierTravail(boolean btnModifierTravail) {
        this.btnModifierTravail = btnModifierTravail;
    }

    public boolean isBtnEnregistrerTravail() {
        return btnEnregistrerTravail;
    }

    public void setBtnEnregistrerTravail(boolean btnEnregistrerTravail) {
        this.btnEnregistrerTravail = btnEnregistrerTravail;
    }

    public boolean isModifierSeqTravail() {
        return modifierSeqTravail;
    }

    public void setModifierSeqTravail(boolean modifierSeqTravail) {
        this.modifierSeqTravail = modifierSeqTravail;
    }

}
