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
@Table(name = "notesdeselevestrim2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevestrim2.findAll", query = "SELECT n FROM Notesdeselevestrim2 n")
    //
    , @NamedQuery(name = "Notesdeselevestrim2.findNoteEleve", query = "SELECT n FROM Notesdeselevestrim2 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevestrim2.findNouveauxElevesTrim2", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevestrim2 n WHERE n.listedeseleves = l AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevestrim2.findNoteByMatricule", query = "SELECT n FROM Notesdeselevestrim2 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matriculeeleve)")
    //
    , @NamedQuery(name = "Notesdeselevestrim2.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevestrim2 n WHERE n.notesdeselevestrim2PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevestrim2.findByCodematiere", query = "SELECT n FROM Notesdeselevestrim2 n WHERE n.notesdeselevestrim2PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevestrim2.findByLanote", query = "SELECT n FROM Notesdeselevestrim2 n WHERE n.lanote = :lanote")})
public class Notesdeselevestrim2 implements Serializable {

    @Column(name = "lanote")
    private BigDecimal lanote;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevestrim2PK notesdeselevestrim2PK;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevestrim2() {
    }

    public Notesdeselevestrim2(Notesdeselevestrim2PK notesdeselevestrim2PK) {
        this.notesdeselevestrim2PK = notesdeselevestrim2PK;
    }

    public Notesdeselevestrim2(String matriculeeleve, String codematiere) {
        this.notesdeselevestrim2PK = new Notesdeselevestrim2PK(matriculeeleve, codematiere);
    }

    public Notesdeselevestrim2PK getNotesdeselevestrim2PK() {
        return notesdeselevestrim2PK;
    }

    public void setNotesdeselevestrim2PK(Notesdeselevestrim2PK notesdeselevestrim2PK) {
        this.notesdeselevestrim2PK = notesdeselevestrim2PK;
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
        hash += (notesdeselevestrim2PK != null ? notesdeselevestrim2PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevestrim2)) {
            return false;
        }
        Notesdeselevestrim2 other = (Notesdeselevestrim2) object;
        if ((this.notesdeselevestrim2PK == null && other.notesdeselevestrim2PK != null) || (this.notesdeselevestrim2PK != null && !this.notesdeselevestrim2PK.equals(other.notesdeselevestrim2PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevestrim2PK + "";
    }

    public BigDecimal getLanote() {
        return lanote;
    }

    public void setLanote(BigDecimal lanote) {
        this.lanote = lanote;
    }

}
