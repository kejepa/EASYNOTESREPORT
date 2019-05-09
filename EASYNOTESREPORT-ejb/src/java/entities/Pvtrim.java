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
public class Pvtrim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeleve;
    private String matriculeeleve;
    private String sexe;
    private String to_char;
    private BigDecimal moyenne1;
    private String rang;
    private BigDecimal moyenne_classe;
    private String tauxreussite;
    private String redoublant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Pvtrim)) {
            return false;
        }
        Pvtrim other = (Pvtrim) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pvtrim[ id=" + id + " ]";
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

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
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

    public String getRedoublant() {
        return redoublant;
    }

    public void setRedoublant(String redoublant) {
        this.redoublant = redoublant;
    }

}
