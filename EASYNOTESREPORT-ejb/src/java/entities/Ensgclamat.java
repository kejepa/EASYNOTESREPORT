/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "ensgclamat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ensgclamat.findAll", query = "SELECT e FROM Ensgclamat e")
    , @NamedQuery(name = "Ensgclamat.findByCodeclasse", query = "SELECT e FROM Ensgclamat e WHERE e.ensgclamatPK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Ensgclamat.findByMatricule", query = "SELECT e FROM Ensgclamat e WHERE e.matriculeenseignant = :matricule")
    , @NamedQuery(name = "Ensgclamat.findByCodematiere", query = "SELECT e FROM Ensgclamat e WHERE e.ensgclamatPK.codematiere = :codematiere")})
public class Ensgclamat implements Serializable {

    @Size(max = 254)
    @Column(name = "matriculeenseignant")
    private String matriculeenseignant;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnsgclamatPK ensgclamatPK;
    @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classes classes;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Ensgclamat() {
    }

    public Ensgclamat(EnsgclamatPK ensgclamatPK) {
        this.ensgclamatPK = ensgclamatPK;
    }

    public Ensgclamat(String codeclasse, String codematiere) {
        this.ensgclamatPK = new EnsgclamatPK(codeclasse, codematiere);
    }

    public EnsgclamatPK getEnsgclamatPK() {
        return ensgclamatPK;
    }

    public void setEnsgclamatPK(EnsgclamatPK ensgclamatPK) {
        this.ensgclamatPK = ensgclamatPK;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Matieres getMatieres() {
        return matieres;
    }

    public void setMatieres(Matieres matieres) {
        this.matieres = matieres;
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
        hash += (ensgclamatPK != null ? ensgclamatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ensgclamat)) {
            return false;
        }
        Ensgclamat other = (Ensgclamat) object;
        if ((this.ensgclamatPK == null && other.ensgclamatPK != null) || (this.ensgclamatPK != null && !this.ensgclamatPK.equals(other.ensgclamatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ensgclamat[ ensgclamatPK=" + ensgclamatPK + " ]";
    }

    public String getMatriculeenseignant() {
        return matriculeenseignant;
    }

    public void setMatriculeenseignant(String matriculeenseignant) {
        this.matriculeenseignant = matriculeenseignant;
    }

}
