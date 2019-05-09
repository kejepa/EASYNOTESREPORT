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
@Table(name = "notesdeselevesseq5")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevesseq5.findAll", query = "SELECT n FROM Notesdeselevesseq5 n")
    //
    , @NamedQuery(name = "Notesdeselevesseq5.findAll1", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NULL")
    , @NamedQuery(name = "Notesdeselevesseq5.findAll2", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NOT NULL")
    , @NamedQuery(name = "Notesdeselevesseq5.findMoyenneMax", query = "SELECT MAX(n.lanote) FROM Notesdeselevesseq5 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq5.findMoyenneMin", query = "SELECT MIN(n.lanote) FROM Notesdeselevesseq5 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse=(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq5.findTauxReussite", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.lanote >= :lanote AND n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq5.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevesseq5 n WHERE n.listedeseleves = l AND n.notesdeselevesseq5PK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevesseq5.findMatiereClasse", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse))")
    , @NamedQuery(name = "Notesdeselevesseq5.findNoteEleve", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.notesdeselevesseq5PK.matriculeeleve = (SELECT l.matriculeeleve FROM Listedeseleves l WHERE l.matriculeeleve = :matricule) AND n.notesdeselevesseq5PK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq5.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.notesdeselevesseq5PK.matriculeeleve = :matriculeeleve")
    //
    , @NamedQuery(name = "Notesdeselevesseq5.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.notesdeselevesseq5PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevesseq5.findByCodematiere", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.notesdeselevesseq5PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevesseq5.findByLanote", query = "SELECT n FROM Notesdeselevesseq5 n WHERE n.lanote = :lanote")})
public class Notesdeselevesseq5 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevesseq5PK notesdeselevesseq5PK;
    @Column(name = "lanote")
    private BigDecimal lanote;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevesseq5() {
    }

    public Notesdeselevesseq5(Notesdeselevesseq5PK notesdeselevesseq5PK) {
        this.notesdeselevesseq5PK = notesdeselevesseq5PK;
    }

    public Notesdeselevesseq5(String matriculeeleve, String codematiere) {
        this.notesdeselevesseq5PK = new Notesdeselevesseq5PK(matriculeeleve, codematiere);
    }

    public Notesdeselevesseq5PK getNotesdeselevesseq5PK() {
        return notesdeselevesseq5PK;
    }

    public void setNotesdeselevesseq5PK(Notesdeselevesseq5PK notesdeselevesseq5PK) {
        this.notesdeselevesseq5PK = notesdeselevesseq5PK;
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
        hash += (notesdeselevesseq5PK != null ? notesdeselevesseq5PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevesseq5)) {
            return false;
        }
        Notesdeselevesseq5 other = (Notesdeselevesseq5) object;
        if ((this.notesdeselevesseq5PK == null && other.notesdeselevesseq5PK != null) || (this.notesdeselevesseq5PK != null && !this.notesdeselevesseq5PK.equals(other.notesdeselevesseq5PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevesseq5PK + "";
    }

}
