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
@Table(name = "trim2rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trim2rangdeselevesparmatiere.findAll", query = "SELECT t FROM Trim2rangdeselevesparmatiere t")
    //
    , @NamedQuery(name = "Trim2rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT t FROM Trim2rangdeselevesparmatiere t WHERE t.trim2rangdeselevesparmatierePK.codematiere = :codematiere AND t.trim2rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND t.trim2rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Trim2rangdeselevesparmatiere.findByCodematiere", query = "SELECT t FROM Trim2rangdeselevesparmatiere t WHERE t.trim2rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Trim2rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT t FROM Trim2rangdeselevesparmatiere t WHERE t.trim2rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Trim2rangdeselevesparmatiere.findByCodeclasse", query = "SELECT t FROM Trim2rangdeselevesparmatiere t WHERE t.trim2rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Trim2rangdeselevesparmatiere.findByRang", query = "SELECT t FROM Trim2rangdeselevesparmatiere t WHERE t.rang = :rang")})
public class Trim2rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Trim2rangdeselevesparmatierePK trim2rangdeselevesparmatierePK;
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

    public Trim2rangdeselevesparmatiere() {
    }

    public Trim2rangdeselevesparmatiere(Trim2rangdeselevesparmatierePK trim2rangdeselevesparmatierePK) {
        this.trim2rangdeselevesparmatierePK = trim2rangdeselevesparmatierePK;
    }

    public Trim2rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.trim2rangdeselevesparmatierePK = new Trim2rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Trim2rangdeselevesparmatierePK getTrim2rangdeselevesparmatierePK() {
        return trim2rangdeselevesparmatierePK;
    }

    public void setTrim2rangdeselevesparmatierePK(Trim2rangdeselevesparmatierePK trim2rangdeselevesparmatierePK) {
        this.trim2rangdeselevesparmatierePK = trim2rangdeselevesparmatierePK;
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
        hash += (trim2rangdeselevesparmatierePK != null ? trim2rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trim2rangdeselevesparmatiere)) {
            return false;
        }
        Trim2rangdeselevesparmatiere other = (Trim2rangdeselevesparmatiere) object;
        if ((this.trim2rangdeselevesparmatierePK == null && other.trim2rangdeselevesparmatierePK != null) || (this.trim2rangdeselevesparmatierePK != null && !this.trim2rangdeselevesparmatierePK.equals(other.trim2rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trim2rangdeselevesparmatiere[ trim2rangdeselevesparmatierePK=" + trim2rangdeselevesparmatierePK + " ]";
    }

}
