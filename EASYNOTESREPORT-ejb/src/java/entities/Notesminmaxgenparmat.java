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
@Table(name = "notesminmaxgenparmat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesminmaxgenparmat.findAll", query = "SELECT n FROM Notesminmaxgenparmat n")
    // 
    ,   @NamedQuery(name = "Notesminmaxgenparmat.findSeq_Matiere_Classe", query = "SELECT n FROM Notesminmaxgenparmat n WHERE n.notesminmaxgenparmatPK.periode = :periode AND n.notesminmaxgenparmatPK.codematiere = :codematiere AND n.notesminmaxgenparmatPK.codeclasse = :codeclasse")
    // 
    , @NamedQuery(name = "Notesminmaxgenparmat.findByPeriode", query = "SELECT n FROM Notesminmaxgenparmat n WHERE n.notesminmaxgenparmatPK.periode = :periode")
    , @NamedQuery(name = "Notesminmaxgenparmat.findByCodematiere", query = "SELECT n FROM Notesminmaxgenparmat n WHERE n.notesminmaxgenparmatPK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesminmaxgenparmat.findByCodeclasse", query = "SELECT n FROM Notesminmaxgenparmat n WHERE n.notesminmaxgenparmatPK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Notesminmaxgenparmat.findByNotemin", query = "SELECT n FROM Notesminmaxgenparmat n WHERE n.notemin = :notemin")
    , @NamedQuery(name = "Notesminmaxgenparmat.findByNotemax", query = "SELECT n FROM Notesminmaxgenparmat n WHERE n.notemax = :notemax")
    , @NamedQuery(name = "Notesminmaxgenparmat.findByMoyenne", query = "SELECT n FROM Notesminmaxgenparmat n WHERE n.moyenne = :moyenne")})
public class Notesminmaxgenparmat implements Serializable {

    @Column(name = "notemin")
    private BigDecimal notemin;
    @Column(name = "notemax")
    private BigDecimal notemax;
    @Column(name = "moyenne")
    private BigDecimal moyenne;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotesminmaxgenparmatPK notesminmaxgenparmatPK;
    @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classes classes;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesminmaxgenparmat() {
    }

    public Notesminmaxgenparmat(NotesminmaxgenparmatPK notesminmaxgenparmatPK) {
        this.notesminmaxgenparmatPK = notesminmaxgenparmatPK;
    }

    public Notesminmaxgenparmat(String periode, String codematiere, String codeclasse) {
        this.notesminmaxgenparmatPK = new NotesminmaxgenparmatPK(periode, codematiere, codeclasse);
    }

    public NotesminmaxgenparmatPK getNotesminmaxgenparmatPK() {
        return notesminmaxgenparmatPK;
    }

    public void setNotesminmaxgenparmatPK(NotesminmaxgenparmatPK notesminmaxgenparmatPK) {
        this.notesminmaxgenparmatPK = notesminmaxgenparmatPK;
    }


    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
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
        hash += (notesminmaxgenparmatPK != null ? notesminmaxgenparmatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesminmaxgenparmat)) {
            return false;
        }
        Notesminmaxgenparmat other = (Notesminmaxgenparmat) object;
        if ((this.notesminmaxgenparmatPK == null && other.notesminmaxgenparmatPK != null) || (this.notesminmaxgenparmatPK != null && !this.notesminmaxgenparmatPK.equals(other.notesminmaxgenparmatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesminmaxgenparmatPK + "";
    }

    public BigDecimal getNotemin() {
        return notemin;
    }

    public void setNotemin(BigDecimal notemin) {
        this.notemin = notemin;
    }

    public BigDecimal getNotemax() {
        return notemax;
    }

    public void setNotemax(BigDecimal notemax) {
        this.notemax = notemax;
    }

    public BigDecimal getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(BigDecimal moyenne) {
        this.moyenne = moyenne;
    }

}
