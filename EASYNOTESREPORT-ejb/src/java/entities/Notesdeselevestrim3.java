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
@Table(name = "notesdeselevestrim3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevestrim3.findAll", query = "SELECT n FROM Notesdeselevestrim3 n")
         //
    , @NamedQuery(name = "Notesdeselevestrim3.findNoteEleve", query = "SELECT n FROM Notesdeselevestrim3 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevestrim3.findNouveauxElevesTrim3", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevestrim3 n WHERE n.listedeseleves = l AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevestrim3.findNoteByMatricule", query = "SELECT n FROM Notesdeselevestrim3 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matriculeeleve)")
    //
    , @NamedQuery(name = "Notesdeselevestrim3.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevestrim3 n WHERE n.notesdeselevestrim3PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevestrim3.findByCodematiere", query = "SELECT n FROM Notesdeselevestrim3 n WHERE n.notesdeselevestrim3PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevestrim3.findByLanote", query = "SELECT n FROM Notesdeselevestrim3 n WHERE n.lanote = :lanote")})
public class Notesdeselevestrim3 implements Serializable {

    @Column(name = "lanote")
    private BigDecimal lanote;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevestrim3PK notesdeselevestrim3PK;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevestrim3() {
    }

    public Notesdeselevestrim3(Notesdeselevestrim3PK notesdeselevestrim3PK) {
        this.notesdeselevestrim3PK = notesdeselevestrim3PK;
    }

    public Notesdeselevestrim3(String matriculeeleve, String codematiere) {
        this.notesdeselevestrim3PK = new Notesdeselevestrim3PK(matriculeeleve, codematiere);
    }

    public Notesdeselevestrim3PK getNotesdeselevestrim3PK() {
        return notesdeselevestrim3PK;
    }

    public void setNotesdeselevestrim3PK(Notesdeselevestrim3PK notesdeselevestrim3PK) {
        this.notesdeselevestrim3PK = notesdeselevestrim3PK;
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
        hash += (notesdeselevestrim3PK != null ? notesdeselevestrim3PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevestrim3)) {
            return false;
        }
        Notesdeselevestrim3 other = (Notesdeselevestrim3) object;
        if ((this.notesdeselevestrim3PK == null && other.notesdeselevestrim3PK != null) || (this.notesdeselevestrim3PK != null && !this.notesdeselevestrim3PK.equals(other.notesdeselevestrim3PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevestrim3PK+"";
    }

    public BigDecimal getLanote() {
        return lanote;
    }

    public void setLanote(BigDecimal lanote) {
        this.lanote = lanote;
    }
    
}
