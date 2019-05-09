/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "trim3moyennesrangsdeseleves")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trim3moyennesrangsdeseleves.findAll", query = "SELECT t FROM Trim3moyennesrangsdeseleves t")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByMatriculeeleve", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByMoyenne", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.moyenne = :moyenne")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByRang", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.rang = :rang")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByMoyg1", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.moyg1 = :moyg1")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByMoyg2", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.moyg2 = :moyg2")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByMoyg3", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.moyg3 = :moyg3")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByRangg1", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.rangg1 = :rangg1")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByRangg2", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.rangg2 = :rangg2")
    , @NamedQuery(name = "Trim3moyennesrangsdeseleves.findByRangg3", query = "SELECT t FROM Trim3moyennesrangsdeseleves t WHERE t.rangg3 = :rangg3")})
public class Trim3moyennesrangsdeseleves implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculeeleve")
    private String matriculeeleve;
    @Column(name = "moyenne")
    private BigDecimal moyenne;
    @Column(name = "rang")
    private String rang;
    @Column(name = "moyg1")
    private BigDecimal moyg1;
    @Column(name = "moyg2")
    private BigDecimal moyg2;
    @Column(name = "moyg3")
    private BigDecimal moyg3;
    @Column(name = "rangg1")
    private String rangg1;
    @Column(name = "rangg2")
    private String rangg2;
    @Column(name = "rangg3")
    private String rangg3;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Listedeseleves listedeseleves;

    public Trim3moyennesrangsdeseleves() {
    }

    public Trim3moyennesrangsdeseleves(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public String getMatriculeeleve() {
        return matriculeeleve;
    }

    public void setMatriculeeleve(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public BigDecimal getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(BigDecimal moyenne) {
        this.moyenne = moyenne;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public BigDecimal getMoyg1() {
        return moyg1;
    }

    public void setMoyg1(BigDecimal moyg1) {
        this.moyg1 = moyg1;
    }

    public BigDecimal getMoyg2() {
        return moyg2;
    }

    public void setMoyg2(BigDecimal moyg2) {
        this.moyg2 = moyg2;
    }

    public BigDecimal getMoyg3() {
        return moyg3;
    }

    public void setMoyg3(BigDecimal moyg3) {
        this.moyg3 = moyg3;
    }

    public String getRangg1() {
        return rangg1;
    }

    public void setRangg1(String rangg1) {
        this.rangg1 = rangg1;
    }

    public String getRangg2() {
        return rangg2;
    }

    public void setRangg2(String rangg2) {
        this.rangg2 = rangg2;
    }

    public String getRangg3() {
        return rangg3;
    }

    public void setRangg3(String rangg3) {
        this.rangg3 = rangg3;
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
        if (!(object instanceof Trim3moyennesrangsdeseleves)) {
            return false;
        }
        Trim3moyennesrangsdeseleves other = (Trim3moyennesrangsdeseleves) object;
        if ((this.matriculeeleve == null && other.matriculeeleve != null) || (this.matriculeeleve != null && !this.matriculeeleve.equals(other.matriculeeleve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return matriculeeleve;
    }
    
}
