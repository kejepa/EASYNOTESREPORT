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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "trim3rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trim3rangdeselevesparmatiere.findAll", query = "SELECT t FROM Trim3rangdeselevesparmatiere t")
    //
    , @NamedQuery(name = "Trim3rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT t FROM Trim3rangdeselevesparmatiere t WHERE t.trim3rangdeselevesparmatierePK.codematiere = :codematiere AND t.trim3rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND t.trim3rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Trim3rangdeselevesparmatiere.findByCodematiere", query = "SELECT t FROM Trim3rangdeselevesparmatiere t WHERE t.trim3rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Trim3rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT t FROM Trim3rangdeselevesparmatiere t WHERE t.trim3rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Trim3rangdeselevesparmatiere.findByCodeclasse", query = "SELECT t FROM Trim3rangdeselevesparmatiere t WHERE t.trim3rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Trim3rangdeselevesparmatiere.findByRang", query = "SELECT t FROM Trim3rangdeselevesparmatiere t WHERE t.rang = :rang")})
public class Trim3rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Trim3rangdeselevesparmatierePK trim3rangdeselevesparmatierePK;
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

    public Trim3rangdeselevesparmatiere() {
    }

    public Trim3rangdeselevesparmatiere(Trim3rangdeselevesparmatierePK trim3rangdeselevesparmatierePK) {
        this.trim3rangdeselevesparmatierePK = trim3rangdeselevesparmatierePK;
    }

    public Trim3rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.trim3rangdeselevesparmatierePK = new Trim3rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Trim3rangdeselevesparmatierePK getTrim3rangdeselevesparmatierePK() {
        return trim3rangdeselevesparmatierePK;
    }

    public void setTrim3rangdeselevesparmatierePK(Trim3rangdeselevesparmatierePK trim3rangdeselevesparmatierePK) {
        this.trim3rangdeselevesparmatierePK = trim3rangdeselevesparmatierePK;
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
        hash += (trim3rangdeselevesparmatierePK != null ? trim3rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trim3rangdeselevesparmatiere)) {
            return false;
        }
        Trim3rangdeselevesparmatiere other = (Trim3rangdeselevesparmatiere) object;
        if ((this.trim3rangdeselevesparmatierePK == null && other.trim3rangdeselevesparmatierePK != null) || (this.trim3rangdeselevesparmatierePK != null && !this.trim3rangdeselevesparmatierePK.equals(other.trim3rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trim3rangdeselevesparmatiere[ trim3rangdeselevesparmatierePK=" + trim3rangdeselevesparmatierePK + " ]";
    }

}
