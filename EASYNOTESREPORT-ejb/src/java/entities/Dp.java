/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author KENFACK JP
 */
@Entity
public class Dp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codematiere;
    private String periode;
    private BigDecimal eff;
    private BigDecimal lpcpt;
    private BigDecimal lfcpt;
    private BigDecimal lpcpp;
    private BigDecimal lfcpp;
    private BigDecimal hpchc;
    private BigDecimal hfchc;
    private BigDecimal nn;
    private BigDecimal mg;
    private BigDecimal nae;
    private BigDecimal nsc;
    private String typedeclasse;
    private String niveaux;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodematiere() {
        return codematiere;
    }

    public void setCodematiere(String codematiere) {
        this.codematiere = codematiere;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public BigDecimal getEff() {
        return eff;
    }

    public void setEff(BigDecimal eff) {
        this.eff = eff;
    }

    public BigDecimal getLpcpt() {
        return lpcpt;
    }

    public void setLpcpt(BigDecimal lpcpt) {
        this.lpcpt = lpcpt;
    }

    public BigDecimal getLfcpt() {
        return lfcpt;
    }

    public void setLfcpt(BigDecimal lfcpt) {
        this.lfcpt = lfcpt;
    }

    public BigDecimal getLpcpp() {
        return lpcpp;
    }

    public void setLpcpp(BigDecimal lpcpp) {
        this.lpcpp = lpcpp;
    }

    public BigDecimal getLfcpp() {
        return lfcpp;
    }

    public void setLfcpp(BigDecimal lfcpp) {
        this.lfcpp = lfcpp;
    }

    public BigDecimal getHpchc() {
        return hpchc;
    }

    public void setHpchc(BigDecimal hpchc) {
        this.hpchc = hpchc;
    }

    public BigDecimal getHfchc() {
        return hfchc;
    }

    public void setHfchc(BigDecimal hfchc) {
        this.hfchc = hfchc;
    }

    public BigDecimal getNn() {
        return nn;
    }

    public void setNn(BigDecimal nn) {
        this.nn = nn;
    }

    public BigDecimal getMg() {
        return mg;
    }

    public void setMg(BigDecimal mg) {
        this.mg = mg;
    }

    public BigDecimal getNae() {
        return nae;
    }

    public void setNae(BigDecimal nae) {
        this.nae = nae;
    }

    public BigDecimal getNsc() {
        return nsc;
    }

    public void setNsc(BigDecimal nsc) {
        this.nsc = nsc;
    }

    public String getTypedeclasse() {
        return typedeclasse;
    }

    public void setTypedeclasse(String typedeclasse) {
        this.typedeclasse = typedeclasse;
    }

    public String getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(String niveaux) {
        this.niveaux = niveaux;
    }

}
