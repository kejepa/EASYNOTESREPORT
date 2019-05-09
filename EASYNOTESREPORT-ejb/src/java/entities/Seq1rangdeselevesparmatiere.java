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
@Table(name = "seq1rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seq1rangdeselevesparmatiere.findAll", query = "SELECT s FROM Seq1rangdeselevesparmatiere s")
    //
    , @NamedQuery(name = "Seq1rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT s FROM Seq1rangdeselevesparmatiere s WHERE s.seq1rangdeselevesparmatierePK.codematiere = :codematiere AND s.seq1rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND s.seq1rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Seq1rangdeselevesparmatiere.findByCodematiere", query = "SELECT s FROM Seq1rangdeselevesparmatiere s WHERE s.seq1rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Seq1rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT s FROM Seq1rangdeselevesparmatiere s WHERE s.seq1rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Seq1rangdeselevesparmatiere.findByCodeclasse", query = "SELECT s FROM Seq1rangdeselevesparmatiere s WHERE s.seq1rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Seq1rangdeselevesparmatiere.findByRang", query = "SELECT s FROM Seq1rangdeselevesparmatiere s WHERE s.rang = :rang")})
public class Seq1rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Seq1rangdeselevesparmatierePK seq1rangdeselevesparmatierePK;
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

    public Seq1rangdeselevesparmatiere() {
    }

    public Seq1rangdeselevesparmatiere(Seq1rangdeselevesparmatierePK seq1rangdeselevesparmatierePK) {
        this.seq1rangdeselevesparmatierePK = seq1rangdeselevesparmatierePK;
    }

    public Seq1rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.seq1rangdeselevesparmatierePK = new Seq1rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Seq1rangdeselevesparmatierePK getSeq1rangdeselevesparmatierePK() {
        return seq1rangdeselevesparmatierePK;
    }

    public void setSeq1rangdeselevesparmatierePK(Seq1rangdeselevesparmatierePK seq1rangdeselevesparmatierePK) {
        this.seq1rangdeselevesparmatierePK = seq1rangdeselevesparmatierePK;
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
        hash += (seq1rangdeselevesparmatierePK != null ? seq1rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seq1rangdeselevesparmatiere)) {
            return false;
        }
        Seq1rangdeselevesparmatiere other = (Seq1rangdeselevesparmatiere) object;
        if ((this.seq1rangdeselevesparmatierePK == null && other.seq1rangdeselevesparmatierePK != null) || (this.seq1rangdeselevesparmatierePK != null && !this.seq1rangdeselevesparmatierePK.equals(other.seq1rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Seq1rangdeselevesparmatiere[ seq1rangdeselevesparmatierePK=" + seq1rangdeselevesparmatierePK + " ]";
    }

}
