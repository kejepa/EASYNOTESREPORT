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
@Table(name = "seq5rangdeselevesparmatiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seq5rangdeselevesparmatiere.findAll", query = "SELECT s FROM Seq5rangdeselevesparmatiere s")
    //
    , @NamedQuery(name = "Seq5rangdeselevesparmatiere.rang_eleve_par_matiere", query = "SELECT s FROM Seq5rangdeselevesparmatiere s WHERE s.seq5rangdeselevesparmatierePK.codematiere = :codematiere AND s.seq5rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve AND s.seq5rangdeselevesparmatierePK.codeclasse = :codeclasse")
    //
    , @NamedQuery(name = "Seq5rangdeselevesparmatiere.findByCodematiere", query = "SELECT s FROM Seq5rangdeselevesparmatiere s WHERE s.seq5rangdeselevesparmatierePK.codematiere = :codematiere")
    , @NamedQuery(name = "Seq5rangdeselevesparmatiere.findByMatriculeeleve", query = "SELECT s FROM Seq5rangdeselevesparmatiere s WHERE s.seq5rangdeselevesparmatierePK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Seq5rangdeselevesparmatiere.findByCodeclasse", query = "SELECT s FROM Seq5rangdeselevesparmatiere s WHERE s.seq5rangdeselevesparmatierePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Seq5rangdeselevesparmatiere.findByRang", query = "SELECT s FROM Seq5rangdeselevesparmatiere s WHERE s.rang = :rang")})
public class Seq5rangdeselevesparmatiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Seq5rangdeselevesparmatierePK seq5rangdeselevesparmatierePK;
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

    public Seq5rangdeselevesparmatiere() {
    }

    public Seq5rangdeselevesparmatiere(Seq5rangdeselevesparmatierePK seq5rangdeselevesparmatierePK) {
        this.seq5rangdeselevesparmatierePK = seq5rangdeselevesparmatierePK;
    }

    public Seq5rangdeselevesparmatiere(String codematiere, String matriculeeleve, String codeclasse) {
        this.seq5rangdeselevesparmatierePK = new Seq5rangdeselevesparmatierePK(codematiere, matriculeeleve, codeclasse);
    }

    public Seq5rangdeselevesparmatierePK getSeq5rangdeselevesparmatierePK() {
        return seq5rangdeselevesparmatierePK;
    }

    public void setSeq5rangdeselevesparmatierePK(Seq5rangdeselevesparmatierePK seq5rangdeselevesparmatierePK) {
        this.seq5rangdeselevesparmatierePK = seq5rangdeselevesparmatierePK;
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
        hash += (seq5rangdeselevesparmatierePK != null ? seq5rangdeselevesparmatierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seq5rangdeselevesparmatiere)) {
            return false;
        }
        Seq5rangdeselevesparmatiere other = (Seq5rangdeselevesparmatiere) object;
        if ((this.seq5rangdeselevesparmatierePK == null && other.seq5rangdeselevesparmatierePK != null) || (this.seq5rangdeselevesparmatierePK != null && !this.seq5rangdeselevesparmatierePK.equals(other.seq5rangdeselevesparmatierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Seq5rangdeselevesparmatiere[ seq5rangdeselevesparmatierePK=" + seq5rangdeselevesparmatierePK + " ]";
    }

}
