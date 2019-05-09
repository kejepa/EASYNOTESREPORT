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
@Table(name = "notesdeselevesseq6")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevesseq6.findAll", query = "SELECT n FROM Notesdeselevesseq6 n")
    //
    , @NamedQuery(name = "Notesdeselevesseq6.findAll1", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NULL")
    , @NamedQuery(name = "Notesdeselevesseq6.findAll2", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NOT NULL")
    , @NamedQuery(name = "Notesdeselevesseq6.findMoyenneMax", query = "SELECT MAX(n.lanote) FROM Notesdeselevesseq6 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq6.findMoyenneMin", query = "SELECT MIN(n.lanote) FROM Notesdeselevesseq6 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse=(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq6.findTauxReussite", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.lanote >= :lanote AND n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq6.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevesseq6 n WHERE n.listedeseleves = l AND n.notesdeselevesseq6PK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevesseq6.findMatiereClasse", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse))")
    , @NamedQuery(name = "Notesdeselevesseq6.findNoteEleve", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule)AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq6.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.notesdeselevesseq6PK.matriculeeleve = :matriculeeleve")
    //
    , @NamedQuery(name = "Notesdeselevesseq6.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.notesdeselevesseq6PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevesseq6.findByCodematiere", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.notesdeselevesseq6PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevesseq6.findByLanote", query = "SELECT n FROM Notesdeselevesseq6 n WHERE n.lanote = :lanote")})
public class Notesdeselevesseq6 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevesseq6PK notesdeselevesseq6PK;
    @Column(name = "lanote")
    private BigDecimal lanote;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevesseq6() {
    }

    public Notesdeselevesseq6(Notesdeselevesseq6PK notesdeselevesseq6PK) {
        this.notesdeselevesseq6PK = notesdeselevesseq6PK;
    }

    public Notesdeselevesseq6(String matriculeeleve, String codematiere) {
        this.notesdeselevesseq6PK = new Notesdeselevesseq6PK(matriculeeleve, codematiere);
    }

    public Notesdeselevesseq6PK getNotesdeselevesseq6PK() {
        return notesdeselevesseq6PK;
    }

    public void setNotesdeselevesseq6PK(Notesdeselevesseq6PK notesdeselevesseq6PK) {
        this.notesdeselevesseq6PK = notesdeselevesseq6PK;
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
        hash += (notesdeselevesseq6PK != null ? notesdeselevesseq6PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevesseq6)) {
            return false;
        }
        Notesdeselevesseq6 other = (Notesdeselevesseq6) object;
        if ((this.notesdeselevesseq6PK == null && other.notesdeselevesseq6PK != null) || (this.notesdeselevesseq6PK != null && !this.notesdeselevesseq6PK.equals(other.notesdeselevesseq6PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevesseq6PK + "";
    }

}
