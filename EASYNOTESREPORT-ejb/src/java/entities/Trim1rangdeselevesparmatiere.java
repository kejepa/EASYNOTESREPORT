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
@Table(name = "trim1rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trim1rangdeselevesparmatiere.findAll", query = "SELECT t FROM Trim1rangdeselevesparmatiere t")
    //
    , @NamedQuery(name = "Trim1rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT t FROM Trim1rangdeselevesparmatiere t WHERE t.trim1rangdeselevesparmatierePK.codematiere = :codematiere AND t.trim1rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND t.trim1rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Trim1rangdeselevesparmatiere.findByCodematiere", query = "SELECT t FROM Trim1rangdeselevesparmatiere t WHERE t.trim1rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Trim1rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT t FROM Trim1rangdeselevesparmatiere t WHERE t.trim1rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Trim1rangdeselevesparmatiere.findByCodeclasse", query = "SELECT t FROM Trim1rangdeselevesparmatiere t WHERE t.trim1rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Trim1rangdeselevesparmatiere.findByRang", query = "SELECT t FROM Trim1rangdeselevesparmatiere t WHERE t.rang = :rang")})
public class Trim1rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Trim1rangdeselevesparmatierePK trim1rangdeselevesparmatierePK;
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

    public Trim1rangdeselevesparmatiere() {
    }

    public Trim1rangdeselevesparmatiere(Trim1rangdeselevesparmatierePK trim1rangdeselevesparmatierePK) {
        this.trim1rangdeselevesparmatierePK = trim1rangdeselevesparmatierePK;
    }

    public Trim1rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.trim1rangdeselevesparmatierePK = new Trim1rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Trim1rangdeselevesparmatierePK getTrim1rangdeselevesparmatierePK() {
        return trim1rangdeselevesparmatierePK;
    }

    public void setTrim1rangdeselevesparmatierePK(Trim1rangdeselevesparmatierePK trim1rangdeselevesparmatierePK) {
        this.trim1rangdeselevesparmatierePK = trim1rangdeselevesparmatierePK;
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
        hash += (trim1rangdeselevesparmatierePK != null ? trim1rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trim1rangdeselevesparmatiere)) {
            return false;
        }
        Trim1rangdeselevesparmatiere other = (Trim1rangdeselevesparmatiere) object;
        if ((this.trim1rangdeselevesparmatierePK == null && other.trim1rangdeselevesparmatierePK != null) || (this.trim1rangdeselevesparmatierePK != null && !this.trim1rangdeselevesparmatierePK.equals(other.trim1rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trim1rangdeselevesparmatiere[ trim1rangdeselevesparmatierePK=" + trim1rangdeselevesparmatierePK + " ]";
    }

}
