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
@Table(name = "notesdeselevesseq3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevesseq3.findAll", query = "SELECT n FROM Notesdeselevesseq3 n")
    //
    , @NamedQuery(name = "Notesdeselevesseq3.findAll1", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NULL")
    , @NamedQuery(name = "Notesdeselevesseq3.findAll2", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NOT NULL")
    , @NamedQuery(name = "Notesdeselevesseq3.findMoyenneMax", query = "SELECT MAX(n.lanote) FROM Notesdeselevesseq3 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq3.findMoyenneMin", query = "SELECT MIN(n.lanote) FROM Notesdeselevesseq3 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse=(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq3.findTauxReussite", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.lanote >= :lanote AND n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq3.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevesseq3 n WHERE n.listedeseleves = l AND n.notesdeselevesseq3PK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevesseq3.findMatiereClasse", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse))")
    , @NamedQuery(name = "Notesdeselevesseq3.findNoteEleve", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule)AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq3.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.notesdeselevesseq3PK.matriculeeleve = :matriculeeleve")
    //
    , @NamedQuery(name = "Notesdeselevesseq3.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.notesdeselevesseq3PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevesseq3.findByCodematiere", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.notesdeselevesseq3PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevesseq3.findByLanote", query = "SELECT n FROM Notesdeselevesseq3 n WHERE n.lanote = :lanote")})
public class Notesdeselevesseq3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevesseq3PK notesdeselevesseq3PK;
    @Column(name = "lanote")
    private BigDecimal lanote;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevesseq3() {
    }

    public Notesdeselevesseq3(Notesdeselevesseq3PK notesdeselevesseq3PK) {
        this.notesdeselevesseq3PK = notesdeselevesseq3PK;
    }

    public Notesdeselevesseq3(String matriculeeleve, String codematiere) {
        this.notesdeselevesseq3PK = new Notesdeselevesseq3PK(matriculeeleve, codematiere);
    }

    public Notesdeselevesseq3PK getNotesdeselevesseq3PK() {
        return notesdeselevesseq3PK;
    }

    public void setNotesdeselevesseq3PK(Notesdeselevesseq3PK notesdeselevesseq3PK) {
        this.notesdeselevesseq3PK = notesdeselevesseq3PK;
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
        hash += (notesdeselevesseq3PK != null ? notesdeselevesseq3PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevesseq3)) {
            return false;
        }
        Notesdeselevesseq3 other = (Notesdeselevesseq3) object;
        if ((this.notesdeselevesseq3PK == null && other.notesdeselevesseq3PK != null) || (this.notesdeselevesseq3PK != null && !this.notesdeselevesseq3PK.equals(other.notesdeselevesseq3PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevesseq3PK + "";
    }

}
