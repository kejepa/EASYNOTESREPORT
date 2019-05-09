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
@Table(name = "seq2rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seq2rangdeselevesparmatiere.findAll", query = "SELECT s FROM Seq2rangdeselevesparmatiere s")
    //
    , @NamedQuery(name = "Seq2rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT s FROM Seq2rangdeselevesparmatiere s WHERE s.seq2rangdeselevesparmatierePK.codematiere = :codematiere AND s.seq2rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND s.seq2rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Seq2rangdeselevesparmatiere.findByCodematiere", query = "SELECT s FROM Seq2rangdeselevesparmatiere s WHERE s.seq2rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Seq2rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT s FROM Seq2rangdeselevesparmatiere s WHERE s.seq2rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Seq2rangdeselevesparmatiere.findByCodeclasse", query = "SELECT s FROM Seq2rangdeselevesparmatiere s WHERE s.seq2rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Seq2rangdeselevesparmatiere.findByRang", query = "SELECT s FROM Seq2rangdeselevesparmatiere s WHERE s.rang = :rang")})
public class Seq2rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Seq2rangdeselevesparmatierePK seq2rangdeselevesparmatierePK;
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

    public Seq2rangdeselevesparmatiere() {
    }

    public Seq2rangdeselevesparmatiere(Seq2rangdeselevesparmatierePK seq2rangdeselevesparmatierePK) {
        this.seq2rangdeselevesparmatierePK = seq2rangdeselevesparmatierePK;
    }

    public Seq2rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.seq2rangdeselevesparmatierePK = new Seq2rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Seq2rangdeselevesparmatierePK getSeq2rangdeselevesparmatierePK() {
        return seq2rangdeselevesparmatierePK;
    }

    public void setSeq2rangdeselevesparmatierePK(Seq2rangdeselevesparmatierePK seq2rangdeselevesparmatierePK) {
        this.seq2rangdeselevesparmatierePK = seq2rangdeselevesparmatierePK;
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
        hash += (seq2rangdeselevesparmatierePK != null ? seq2rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seq2rangdeselevesparmatiere)) {
            return false;
        }
        Seq2rangdeselevesparmatiere other = (Seq2rangdeselevesparmatiere) object;
        if ((this.seq2rangdeselevesparmatierePK == null && other.seq2rangdeselevesparmatierePK != null) || (this.seq2rangdeselevesparmatierePK != null && !this.seq2rangdeselevesparmatierePK.equals(other.seq2rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Seq2rangdeselevesparmatiere[ seq2rangdeselevesparmatierePK=" + seq2rangdeselevesparmatierePK + " ]";
    }

}
