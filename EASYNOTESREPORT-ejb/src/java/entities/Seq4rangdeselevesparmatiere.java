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
@Table(name = "seq4rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seq4rangdeselevesparmatiere.findAll", query = "SELECT s FROM Seq4rangdeselevesparmatiere s")
    //
    , @NamedQuery(name = "Seq4rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT s FROM Seq4rangdeselevesparmatiere s WHERE s.seq4rangdeselevesparmatierePK.codematiere = :codematiere AND s.seq4rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND s.seq4rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Seq4rangdeselevesparmatiere.findByCodematiere", query = "SELECT s FROM Seq4rangdeselevesparmatiere s WHERE s.seq4rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Seq4rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT s FROM Seq4rangdeselevesparmatiere s WHERE s.seq4rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Seq4rangdeselevesparmatiere.findByCodeclasse", query = "SELECT s FROM Seq4rangdeselevesparmatiere s WHERE s.seq4rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Seq4rangdeselevesparmatiere.findByRang", query = "SELECT s FROM Seq4rangdeselevesparmatiere s WHERE s.rang = :rang")})
public class Seq4rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Seq4rangdeselevesparmatierePK seq4rangdeselevesparmatierePK;
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

    public Seq4rangdeselevesparmatiere() {
    }

    public Seq4rangdeselevesparmatiere(Seq4rangdeselevesparmatierePK seq4rangdeselevesparmatierePK) {
        this.seq4rangdeselevesparmatierePK = seq4rangdeselevesparmatierePK;
    }

    public Seq4rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.seq4rangdeselevesparmatierePK = new Seq4rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Seq4rangdeselevesparmatierePK getSeq4rangdeselevesparmatierePK() {
        return seq4rangdeselevesparmatierePK;
    }

    public void setSeq4rangdeselevesparmatierePK(Seq4rangdeselevesparmatierePK seq4rangdeselevesparmatierePK) {
        this.seq4rangdeselevesparmatierePK = seq4rangdeselevesparmatierePK;
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
        hash += (seq4rangdeselevesparmatierePK != null ? seq4rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seq4rangdeselevesparmatiere)) {
            return false;
        }
        Seq4rangdeselevesparmatiere other = (Seq4rangdeselevesparmatiere) object;
        if ((this.seq4rangdeselevesparmatierePK == null && other.seq4rangdeselevesparmatierePK != null) || (this.seq4rangdeselevesparmatierePK != null && !this.seq4rangdeselevesparmatierePK.equals(other.seq4rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Seq4rangdeselevesparmatiere[ seq4rangdeselevesparmatierePK=" + seq4rangdeselevesparmatierePK + " ]";
    }

}
