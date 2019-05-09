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
@Table(name = "seq3rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seq3rangdeselevesparmatiere.findAll", query = "SELECT s FROM Seq3rangdeselevesparmatiere s")
    //
    , @NamedQuery(name = "Seq3rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT s FROM Seq3rangdeselevesparmatiere s WHERE s.seq3rangdeselevesparmatierePK.codematiere = :codematiere AND s.seq3rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND s.seq3rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Seq3rangdeselevesparmatiere.findByCodematiere", query = "SELECT s FROM Seq3rangdeselevesparmatiere s WHERE s.seq3rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Seq3rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT s FROM Seq3rangdeselevesparmatiere s WHERE s.seq3rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Seq3rangdeselevesparmatiere.findByCodeclasse", query = "SELECT s FROM Seq3rangdeselevesparmatiere s WHERE s.seq3rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Seq3rangdeselevesparmatiere.findByRang", query = "SELECT s FROM Seq3rangdeselevesparmatiere s WHERE s.rang = :rang")})
public class Seq3rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Seq3rangdeselevesparmatierePK seq3rangdeselevesparmatierePK;
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

    public Seq3rangdeselevesparmatiere() {
    }

    public Seq3rangdeselevesparmatiere(Seq3rangdeselevesparmatierePK seq3rangdeselevesparmatierePK) {
        this.seq3rangdeselevesparmatierePK = seq3rangdeselevesparmatierePK;
    }

    public Seq3rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.seq3rangdeselevesparmatierePK = new Seq3rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Seq3rangdeselevesparmatierePK getSeq3rangdeselevesparmatierePK() {
        return seq3rangdeselevesparmatierePK;
    }

    public void setSeq3rangdeselevesparmatierePK(Seq3rangdeselevesparmatierePK seq3rangdeselevesparmatierePK) {
        this.seq3rangdeselevesparmatierePK = seq3rangdeselevesparmatierePK;
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
        hash += (seq3rangdeselevesparmatierePK != null ? seq3rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seq3rangdeselevesparmatiere)) {
            return false;
        }
        Seq3rangdeselevesparmatiere other = (Seq3rangdeselevesparmatiere) object;
        if ((this.seq3rangdeselevesparmatierePK == null && other.seq3rangdeselevesparmatierePK != null) || (this.seq3rangdeselevesparmatierePK != null && !this.seq3rangdeselevesparmatierePK.equals(other.seq3rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Seq3rangdeselevesparmatiere[ seq3rangdeselevesparmatierePK=" + seq3rangdeselevesparmatierePK + " ]";
    }

}
