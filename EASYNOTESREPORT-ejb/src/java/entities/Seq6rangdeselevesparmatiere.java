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
@Table(name = "seq6rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seq6rangdeselevesparmatiere.findAll", query = "SELECT s FROM Seq6rangdeselevesparmatiere s")
    //
    , @NamedQuery(name = "Seq6rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT s FROM Seq6rangdeselevesparmatiere s WHERE s.seq6rangdeselevesparmatierePK.codematiere = :codematiere AND s.seq6rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND s.seq6rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Seq6rangdeselevesparmatiere.findByCodematiere", query = "SELECT s FROM Seq6rangdeselevesparmatiere s WHERE s.seq6rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Seq6rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT s FROM Seq6rangdeselevesparmatiere s WHERE s.seq6rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Seq6rangdeselevesparmatiere.findByCodeclasse", query = "SELECT s FROM Seq6rangdeselevesparmatiere s WHERE s.seq6rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Seq6rangdeselevesparmatiere.findByRang", query = "SELECT s FROM Seq6rangdeselevesparmatiere s WHERE s.rang = :rang")})
public class Seq6rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Seq6rangdeselevesparmatierePK seq6rangdeselevesparmatierePK;
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

    public Seq6rangdeselevesparmatiere() {
    }

    public Seq6rangdeselevesparmatiere(Seq6rangdeselevesparmatierePK seq6rangdeselevesparmatierePK) {
        this.seq6rangdeselevesparmatierePK = seq6rangdeselevesparmatierePK;
    }

    public Seq6rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.seq6rangdeselevesparmatierePK = new Seq6rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Seq6rangdeselevesparmatierePK getSeq6rangdeselevesparmatierePK() {
        return seq6rangdeselevesparmatierePK;
    }

    public void setSeq6rangdeselevesparmatierePK(Seq6rangdeselevesparmatierePK seq6rangdeselevesparmatierePK) {
        this.seq6rangdeselevesparmatierePK = seq6rangdeselevesparmatierePK;
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
        hash += (seq6rangdeselevesparmatierePK != null ? seq6rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seq6rangdeselevesparmatiere)) {
            return false;
        }
        Seq6rangdeselevesparmatiere other = (Seq6rangdeselevesparmatiere) object;
        if ((this.seq6rangdeselevesparmatierePK == null && other.seq6rangdeselevesparmatierePK != null) || (this.seq6rangdeselevesparmatierePK != null && !this.seq6rangdeselevesparmatierePK.equals(other.seq6rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Seq6rangdeselevesparmatiere[ seq6rangdeselevesparmatierePK=" + seq6rangdeselevesparmatierePK + " ]";
    }

}
