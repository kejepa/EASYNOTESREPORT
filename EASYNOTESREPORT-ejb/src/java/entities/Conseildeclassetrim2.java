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
@Table(name = "conseildeclassetrim2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conseildeclassetrim2.findAll", query = "SELECT c FROM Conseildeclassetrim2 c")
    //
    , @NamedQuery(name = "Conseildeclassetrim2.findElevesParClasse", query = "SELECT d FROM Conseildeclassetrim2 d WHERE d.listedeseleves IN (SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse))")
    , @NamedQuery(name = "Conseildeclassetrim2.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT d FROM Conseildeclassetrim2 d WHERE d.listedeseleves = l)")
    //
    , @NamedQuery(name = "Conseildeclassetrim2.findByMatriculeeleve", query = "SELECT c FROM Conseildeclassetrim2 c WHERE c.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Conseildeclassetrim2.findByDecision", query = "SELECT c FROM Conseildeclassetrim2 c WHERE c.decision = :decision")})
public class Conseildeclassetrim2 implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "decision")
    private String decision;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculeeleve")
    private String matriculeeleve;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Listedeseleves listedeseleves;

    public Conseildeclassetrim2() {
    }

    public Conseildeclassetrim2(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public String getMatriculeeleve() {
        return matriculeeleve;
    }

    public void setMatriculeeleve(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
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
        if (!(object instanceof Conseildeclassetrim2)) {
            return false;
        }
        Conseildeclassetrim2 other = (Conseildeclassetrim2) object;
        if ((this.matriculeeleve == null && other.matriculeeleve != null) || (this.matriculeeleve != null && !this.matriculeeleve.equals(other.matriculeeleve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Conseildeclassetrim2[ matriculeeleve=" + matriculeeleve + " ]";
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

}
