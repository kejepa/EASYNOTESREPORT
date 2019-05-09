/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "notesdeselevesann")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevesann.findAll", query = "SELECT n FROM Notesdeselevesann n")
    //
    , @NamedQuery(name = "Notesdeselevesann.findNoteByMatricule", query = "SELECT n FROM Notesdeselevesann n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matriculeeleve)")
    , @NamedQuery(name = "Notesdeselevesann.findNouveauxElevesAnn", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevesann n WHERE n.listedeseleves = l AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevesann.findNoteByMatricule", query = "SELECT n FROM Notesdeselevesann n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matriculeeleve)")
    //
    , @NamedQuery(name = "Notesdeselevesann.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesann n WHERE n.notesdeselevesannPK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevesann.findByCodematiere", query = "SELECT n FROM Notesdeselevesann n WHERE n.notesdeselevesannPK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevesann.findByLanote", query = "SELECT n FROM Notesdeselevesann n WHERE n.lanote = :lanote")})
public class Notesdeselevesann implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotesdeselevesannPK notesdeselevesannPK;
    @Column(name = "lanote")
    private BigDecimal lanote;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevesann() {
    }

    public Notesdeselevesann(NotesdeselevesannPK notesdeselevesannPK) {
        this.notesdeselevesannPK = notesdeselevesannPK;
    }

    public Notesdeselevesann(String matriculeeleve, String codematiere) {
        this.notesdeselevesannPK = new NotesdeselevesannPK(matriculeeleve, codematiere);
    }

    public NotesdeselevesannPK getNotesdeselevesannPK() {
        return notesdeselevesannPK;
    }

    public void setNotesdeselevesannPK(NotesdeselevesannPK notesdeselevesannPK) {
        this.notesdeselevesannPK = notesdeselevesannPK;
    }

    public BigDecimal getLanote() {
        return lanote;
    }

    public void setLanote(BigDecimal lanote) {
        this.lanote = lanote;
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
        hash += (notesdeselevesannPK != null ? notesdeselevesannPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevesann)) {
            return false;
        }
        Notesdeselevesann other = (Notesdeselevesann) object;
        if ((this.notesdeselevesannPK == null && other.notesdeselevesannPK != null) || (this.notesdeselevesannPK != null && !this.notesdeselevesannPK.equals(other.notesdeselevesannPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Notesdeselevesann[ notesdeselevesannPK=" + notesdeselevesannPK + " ]";
    }

}
