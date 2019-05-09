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
@Table(name = "annrangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Annrangdeselevesparmatiere.findAll", query = "SELECT a FROM Annrangdeselevesparmatiere a")
    //
    , @NamedQuery(name = "Annrangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT a FROM Annrangdeselevesparmatiere a WHERE a.annrangdeselevesparmatierePK.codematiere = :codematiere AND a.annrangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND a.annrangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Annrangdeselevesparmatiere.findByCodematiere", query = "SELECT a FROM Annrangdeselevesparmatiere a WHERE a.annrangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Annrangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT a FROM Annrangdeselevesparmatiere a WHERE a.annrangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Annrangdeselevesparmatiere.findByCodeclasse", query = "SELECT a FROM Annrangdeselevesparmatiere a WHERE a.annrangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Annrangdeselevesparmatiere.findByRang", query = "SELECT a FROM Annrangdeselevesparmatiere a WHERE a.rang = :rang")})
public class Annrangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AnnrangdeselevesparmatierePK annrangdeselevesparmatierePK;
    @Size(max = 2147483647)
    @Column(name = "rang")
    private String rang;
    @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classes classes;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Annrangdeselevesparmatiere() {
    }

    public Annrangdeselevesparmatiere(AnnrangdeselevesparmatierePK annrangdeselevesparmatierePK) {
        this.annrangdeselevesparmatierePK = annrangdeselevesparmatierePK;
    }

    public Annrangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.annrangdeselevesparmatierePK = new AnnrangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public AnnrangdeselevesparmatierePK getAnnrangdeselevesparmatierePK() {
        return annrangdeselevesparmatierePK;
    }

    public void setAnnrangdeselevesparmatierePK(AnnrangdeselevesparmatierePK annrangdeselevesparmatierePK) {
        this.annrangdeselevesparmatierePK = annrangdeselevesparmatierePK;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Listedeseleves getListedeseleves() {
        return listedeseleves;
    }

    public void setListedeseleves(Listedeseleves listedeseleves) {
        this.listedeseleves = listedeseleves;
    }

    public Matieres getMatieres() {
        return matieres;
    }

    public void setMatieres(Matieres matieres) {
        this.matieres = matieres;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (annrangdeselevesparmatierePK != null ? annrangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annrangdeselevesparmatiere)) {
            return false;
        }
        Annrangdeselevesparmatiere other = (Annrangdeselevesparmatiere) object;
        if ((this.annrangdeselevesparmatierePK == null && other.annrangdeselevesparmatierePK != null) || (this.annrangdeselevesparmatierePK != null && !this.annrangdeselevesparmatierePK.equals(other.annrangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return annrangdeselevesparmatierePK + "";
    }

}
