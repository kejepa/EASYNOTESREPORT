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
@Table(name = "notesdeselevestrim1")
@XmlRootElement
@NamedQueries({ 
    @NamedQuery(name = "Notesdeselevestrim1.findAll", query = "SELECT n FROM Notesdeselevestrim1 n")
    //
    , @NamedQuery(name = "Notesdeselevestrim1.findNoteEleve", query = "SELECT n FROM Notesdeselevestrim1 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevestrim1.findNouveauxElevesTrim1", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevestrim1 n WHERE n.listedeseleves = l AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevestrim1.findNoteByMatricule", query = "SELECT n FROM Notesdeselevestrim1 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matriculeeleve)")
    //
    , @NamedQuery(name = "Notesdeselevestrim1.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevestrim1 n WHERE n.notesdeselevestrim1PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevestrim1.findByCodematiere", query = "SELECT n FROM Notesdeselevestrim1 n WHERE n.notesdeselevestrim1PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevestrim1.findByLanote", query = "SELECT n FROM Notesdeselevestrim1 n WHERE n.lanote = :lanote")})
public class Notesdeselevestrim1 implements Serializable {

    @Column(name = "lanote")
    private BigDecimal lanote;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevestrim1PK notesdeselevestrim1PK;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevestrim1() {
    }

    public Notesdeselevestrim1(Notesdeselevestrim1PK notesdeselevestrim1PK) {
        this.notesdeselevestrim1PK = notesdeselevestrim1PK;
    }

    public Notesdeselevestrim1(String matriculeeleve, String codematiere) {
        this.notesdeselevestrim1PK = new Notesdeselevestrim1PK(matriculeeleve, codematiere);
    }

    public Notesdeselevestrim1PK getNotesdeselevestrim1PK() {
        return notesdeselevestrim1PK;
    }

    public void setNotesdeselevestrim1PK(Notesdeselevestrim1PK notesdeselevestrim1PK) {
        this.notesdeselevestrim1PK = notesdeselevestrim1PK;
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
        hash += (notesdeselevestrim1PK != null ? notesdeselevestrim1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevestrim1)) {
            return false;
        }
        Notesdeselevestrim1 other = (Notesdeselevestrim1) object;
        if ((this.notesdeselevestrim1PK == null && other.notesdeselevestrim1PK != null) || (this.notesdeselevestrim1PK != null && !this.notesdeselevestrim1PK.equals(other.notesdeselevestrim1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevestrim1PK + "";
    }

    public BigDecimal getLanote() {
        return lanote;
    }

    public void setLanote(BigDecimal lanote) {
        this.lanote = lanote;
    }

}
