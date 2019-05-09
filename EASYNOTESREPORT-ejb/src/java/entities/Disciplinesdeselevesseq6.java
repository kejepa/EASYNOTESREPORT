/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "disciplinesdeselevesseq6")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplinesdeselevesseq6.findAll", query = "SELECT d FROM Disciplinesdeselevesseq6 d")
    // 
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.listedeseleves = l)")
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findElevesParClasse", query = "SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.listedeseleves IN(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse)) ORDER BY d.listedeseleves.nom,d.listedeseleves.prenom")
//
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findByMatriculeeleve", query = "SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findByAbsb", query = "SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.absb = :absb")
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findByAbsj", query = "SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.absj = :absj")
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findByAbsn", query = "SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.absn = :absn")
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findByConsigne", query = "SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.consigne = :consigne")
    , @NamedQuery(name = "Disciplinesdeselevesseq6.findByExclusion", query = "SELECT d FROM Disciplinesdeselevesseq6 d WHERE d.exclusion = :exclusion")})
public class Disciplinesdeselevesseq6 implements Serializable {

    @Column(name = "av")
    private Boolean av;
    @Column(name = "bl")
    private Boolean bl;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculeeleve")
    private String matriculeeleve;
    @Column(name = "absb")
    private Integer absb;
    @Column(name = "absj")
    private Integer absj;
    @Column(name = "absn")
    private Integer absn;
    @Column(name = "consigne")
    private Integer consigne;
    @Column(name = "exclusion")
    private Integer exclusion;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Listedeseleves listedeseleves;

    public Disciplinesdeselevesseq6() {
    }

    public Disciplinesdeselevesseq6(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public String getMatriculeeleve() {
        return matriculeeleve;
    }

    public void setMatriculeeleve(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public Integer getAbsb() {
        return absb;
    }

    public void setAbsb(Integer absb) {
        this.absb = absb;
    }

    public Integer getAbsj() {
        return absj;
    }

    public void setAbsj(Integer absj) {
        this.absj = absj;
    }

    public Integer getAbsn() {
        return absn;
    }

    public void setAbsn(Integer absn) {
        this.absn = absn;
    }

    public Integer getConsigne() {
        return consigne;
    }

    public void setConsigne(Integer consigne) {
        this.consigne = consigne;
    }

    public Integer getExclusion() {
        return exclusion;
    }

    public void setExclusion(Integer exclusion) {
        this.exclusion = exclusion;
    }

    public Listedeseleves getListedeseleves() {
        return listedeseleves;
    }

    public void setListedeseleves(Listedeseleves listedeseleves) {
        this.listedeseleves = listedeseleves;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculeeleve != null ? matriculeeleve.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplinesdeselevesseq6)) {
            return false;
        }
        Disciplinesdeselevesseq6 other = (Disciplinesdeselevesseq6) object;
        if ((this.matriculeeleve == null && other.matriculeeleve != null) || (this.matriculeeleve != null && !this.matriculeeleve.equals(other.matriculeeleve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Disciplinesdeselevesseq6[ matriculeeleve=" + matriculeeleve + " ]";
    }

    public Boolean getAv() {
        return av;
    }

    public void setAv(Boolean av) {
        this.av = av;
    }

    public Boolean getBl() {
        return bl;
    }

    public void setBl(Boolean bl) {
        this.bl = bl;
    }

}
