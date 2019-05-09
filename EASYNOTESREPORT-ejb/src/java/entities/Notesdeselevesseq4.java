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
@Table(name = "notesdeselevesseq4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevesseq4.findAll", query = "SELECT n FROM Notesdeselevesseq4 n")
    //
    , @NamedQuery(name = "Notesdeselevesseq4.findAll1", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NULL")
    , @NamedQuery(name = "Notesdeselevesseq4.findAll2", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NOT NULL")
    , @NamedQuery(name = "Notesdeselevesseq4.findMoyenneMax", query = "SELECT MAX(n.lanote) FROM Notesdeselevesseq4 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq4.findMoyenneMin", query = "SELECT MIN(n.lanote) FROM Notesdeselevesseq4 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse=(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq4.findTauxReussite", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.lanote >= :lanote AND n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq4.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevesseq4 n WHERE n.listedeseleves = l AND n.notesdeselevesseq4PK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevesseq4.findMatiereClasse", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse))")
    , @NamedQuery(name = "Notesdeselevesseq4.findNoteEleve", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule)AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq4.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.notesdeselevesseq4PK.matriculeeleve = :matriculeeleve")
    //
    , @NamedQuery(name = "Notesdeselevesseq4.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.notesdeselevesseq4PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevesseq4.findByCodematiere", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.notesdeselevesseq4PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevesseq4.findByLanote", query = "SELECT n FROM Notesdeselevesseq4 n WHERE n.lanote = :lanote")})
public class Notesdeselevesseq4 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevesseq4PK notesdeselevesseq4PK;
    @Column(name = "lanote")
    private BigDecimal lanote;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevesseq4() {
    }

    public Notesdeselevesseq4(Notesdeselevesseq4PK notesdeselevesseq4PK) {
        this.notesdeselevesseq4PK = notesdeselevesseq4PK;
    }

    public Notesdeselevesseq4(String matriculeeleve, String codematiere) {
        this.notesdeselevesseq4PK = new Notesdeselevesseq4PK(matriculeeleve, codematiere);
    }

    public Notesdeselevesseq4PK getNotesdeselevesseq4PK() {
        return notesdeselevesseq4PK;
    }

    public void setNotesdeselevesseq4PK(Notesdeselevesseq4PK notesdeselevesseq4PK) {
        this.notesdeselevesseq4PK = notesdeselevesseq4PK;
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
        hash += (notesdeselevesseq4PK != null ? notesdeselevesseq4PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevesseq4)) {
            return false;
        }
        Notesdeselevesseq4 other = (Notesdeselevesseq4) object;
        if ((this.notesdeselevesseq4PK == null && other.notesdeselevesseq4PK != null) || (this.notesdeselevesseq4PK != null && !this.notesdeselevesseq4PK.equals(other.notesdeselevesseq4PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevesseq4PK + "";
    }

}
