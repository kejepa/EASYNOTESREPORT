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
public class PvAn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeleve;
    private String matriculeeleve;
    private String sexe;
    private String to_char;
    private BigDecimal moyenne1;
    private String rang1;
     private BigDecimal moyenne2;
    private String rang2;
     private BigDecimal moyenne3;
    private String rang3;
     private BigDecimal moyenneann;
    private String rangann;
    private BigDecimal moyenne_classe;
    private String tauxreussite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeleve() {
        return nomeleve;
    }

    public void setNomeleve(String nomeleve) {
        this.nomeleve = nomeleve;
    }

    public String getMatriculeeleve() {
        return matriculeeleve;
    }

    public void setMatriculeeleve(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTo_char() {
        return to_char;
    }

    public void setTo_char(String to_char) {
        this.to_char = to_char;
    }

    public BigDecimal getMoyenne1() {
        return moyenne1;
    }

    public void setMoyenne1(BigDecimal moyenne1) {
        this.moyenne1 = moyenne1;
    }

    public String getRang1() {
        return rang1;
    }

    public void setRang1(String rang1) {
        this.rang1 = rang1;
    }

    public BigDecimal getMoyenne2() {
        return moyenne2;
    }

    public void setMoyenne2(BigDecimal moyenne2) {
        this.moyenne2 = moyenne2;
    }

    public String getRang2() {
        return rang2;
    }

    public void setRang2(String rang2) {
        this.rang2 = rang2;
    }

    public BigDecimal getMoyenne3() {
        return moyenne3;
    }

    public void setMoyenne3(BigDecimal moyenne3) {
        this.moyenne3 = moyenne3;
    }

    public String getRang3() {
        return rang3;
    }

    public void setRang3(String rang3) {
        this.rang3 = rang3;
    }

    public BigDecimal getMoyenneann() {
        return moyenneann;
    }

    public void setMoyenneann(BigDecimal moyenneann) {
        this.moyenneann = moyenneann;
    }

    public String getRangann() {
        return rangann;
    }

    public void setRangann(String rangann) {
        this.rangann = rangann;
    }

    public BigDecimal getMoyenne_classe() {
        return moyenne_classe;
    }

    public void setMoyenne_classe(BigDecimal moyenne_classe) {
        this.moyenne_classe = moyenne_classe;
    }

    public String getTauxreussite() {
        return tauxreussite;
    }

    public void setTauxreussite(String tauxreussite) {
        this.tauxreussite = tauxreussite;
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
        if (!(object instanceof PvAn)) {
            return false;
        }
        PvAn other = (PvAn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PvAn[ id=" + id + " ]";
    }
    
}
