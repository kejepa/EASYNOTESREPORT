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
@Table(name = "notesdeselevesseq1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevesseq1.findAll", query = "SELECT n FROM Notesdeselevesseq1 n")
    //
    , @NamedQuery(name = "Notesdeselevesseq1.findAll1", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NULL")
    , @NamedQuery(name = "Notesdeselevesseq1.findAll2", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NOT NULL")
    , @NamedQuery(name = "Notesdeselevesseq1.findMoyenneMax", query = "SELECT MAX(n.lanote) FROM Notesdeselevesseq1 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq1.findMoyenneMin", query = "SELECT MIN(n.lanote) FROM Notesdeselevesseq1 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse=(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq1.findTauxReussite", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.lanote >= :lanote AND n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq1.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevesseq1 n WHERE n.listedeseleves = l AND n.notesdeselevesseq1PK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevesseq1.findMatiereClasse", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse))")
    , @NamedQuery(name = "Notesdeselevesseq1.findNoteEleve", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule)AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq1.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.notesdeselevesseq1PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevesseq1.findEleveParClasse", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse)")
//
    , @NamedQuery(name = "Notesdeselevesseq1.findByCodematiere", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.notesdeselevesseq1PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevesseq1.findByLanote", query = "SELECT n FROM Notesdeselevesseq1 n WHERE n.lanote = :lanote")})
public class Notesdeselevesseq1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevesseq1PK notesdeselevesseq1PK;
    @Column(name = "lanote")
    private BigDecimal lanote;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevesseq1() {
    }

    public Notesdeselevesseq1(Notesdeselevesseq1PK notesdeselevesseq1PK) {
        this.notesdeselevesseq1PK = notesdeselevesseq1PK;
    }

    public Notesdeselevesseq1(String matriculeeleve, String codematiere) {
        this.notesdeselevesseq1PK = new Notesdeselevesseq1PK(matriculeeleve, codematiere);
    }

    public Notesdeselevesseq1PK getNotesdeselevesseq1PK() {
        return notesdeselevesseq1PK;
    }

    public void setNotesdeselevesseq1PK(Notesdeselevesseq1PK notesdeselevesseq1PK) {
        this.notesdeselevesseq1PK = notesdeselevesseq1PK;
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
        hash += (notesdeselevesseq1PK != null ? notesdeselevesseq1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevesseq1)) {
            return false;
        }
        Notesdeselevesseq1 other = (Notesdeselevesseq1) object;
        if ((this.notesdeselevesseq1PK == null && other.notesdeselevesseq1PK != null) || (this.notesdeselevesseq1PK != null && !this.notesdeselevesseq1PK.equals(other.notesdeselevesseq1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevesseq1PK + "";
    }

}
