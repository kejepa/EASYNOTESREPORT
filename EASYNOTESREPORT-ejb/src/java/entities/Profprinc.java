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
@Table(name = "profprinc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profprinc.findAll", query = "SELECT p FROM Profprinc p")
    , @NamedQuery(name = "Profprinc.findByCodeclasse", query = "SELECT p FROM Profprinc p WHERE p.codeclasse = :codeclasse")
    , @NamedQuery(name = "Profprinc.findByMatriculeenseignant", query = "SELECT p FROM Profprinc p WHERE p.matriculeenseignant = :matriculeenseignant")})
public class Profprinc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codeclasse")
    private String codeclasse;
    @Size(max = 254)
    @Column(name = "matriculeenseignant")
    private String matriculeenseignant;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Classes classes;

    public Profprinc() {
    }

    public Profprinc(String codeclasse) {
        this.codeclasse = codeclasse;
    }

    public String getCodeclasse() {
        return codeclasse;
    }

    public void setCodeclasse(String codeclasse) {
        this.codeclasse = codeclasse;
    }

    public String getMatriculeenseignant() {
        return matriculeenseignant;
    }

    public void setMatriculeenseignant(String matriculeenseignant) {
        this.matriculeenseignant = matriculeenseignant;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeclasse != null ? codeclasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profprinc)) {
            return false;
        }
        Profprinc other = (Profprinc) object;
        if ((this.codeclasse == null && other.codeclasse != null) || (this.codeclasse != null && !this.codeclasse.equals(other.codeclasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Profprinc[ codeclasse=" + codeclasse + " ]";
    }

}
