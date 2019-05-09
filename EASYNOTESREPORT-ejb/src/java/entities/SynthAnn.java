/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author KENFACK JP
 */
@Entity
public class SynthAnn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codematiere;
    private Integer effectif;
    private Integer eff0_6;
    private Integer eff6_8;
    private Integer eff8_9;
    private Integer eff9_10;
    private Integer eff10_12;
    private Integer eff12_14;
    private Integer eff14_16;
    private Integer eff16_18;
    private Integer eff18_20;

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

    public Integer getEffectif() {
        return effectif;
    }

    public void setEffectif(Integer effectif) {
        this.effectif = effectif;
    }

    public Integer getEff0_6() {
        return eff0_6;
    }

    public void setEff0_6(Integer eff0_6) {
        this.eff0_6 = eff0_6;
    }

    public Integer getEff6_8() {
        return eff6_8;
    }

    public void setEff6_8(Integer eff6_8) {
        this.eff6_8 = eff6_8;
    }

    public Integer getEff8_9() {
        return eff8_9;
    }

    public void setEff8_9(Integer eff8_9) {
        this.eff8_9 = eff8_9;
    }

    public Integer getEff9_10() {
        return eff9_10;
    }

    public void setEff9_10(Integer eff9_10) {
        this.eff9_10 = eff9_10;
    }

    public Integer getEff10_12() {
        return eff10_12;
    }

    public void setEff10_12(Integer eff10_12) {
        this.eff10_12 = eff10_12;
    }

    public Integer getEff12_14() {
        return eff12_14;
    }

    public void setEff12_14(Integer eff12_14) {
        this.eff12_14 = eff12_14;
    }

    public Integer getEff14_16() {
        return eff14_16;
    }

    public void setEff14_16(Integer eff14_16) {
        this.eff14_16 = eff14_16;
    }

    public Integer getEff16_18() {
        return eff16_18;
    }

    public void setEff16_18(Integer eff16_18) {
        this.eff16_18 = eff16_18;
    }

    public Integer getEff18_20() {
        return eff18_20;
    }

    public void setEff18_20(Integer eff18_20) {
        this.eff18_20 = eff18_20;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SynthAnn)) {
            return false;
        }
        SynthAnn other = (SynthAnn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SynthAnn[ id=" + id + " ]";
    }

}
