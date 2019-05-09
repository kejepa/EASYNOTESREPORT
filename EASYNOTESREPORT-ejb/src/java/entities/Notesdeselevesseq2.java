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
@Table(name = "notesdeselevesseq2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notesdeselevesseq2.findAll", query = "SELECT n FROM Notesdeselevesseq2 n")
    //
    , @NamedQuery(name = "Notesdeselevesseq2.findAll1", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NULL")
    , @NamedQuery(name = "Notesdeselevesseq2.findAll2", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.lanote IS NOT NULL")
    , @NamedQuery(name = "Notesdeselevesseq2.findMoyenneMax", query = "SELECT MAX(n.lanote) FROM Notesdeselevesseq2 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq2.findMoyenneMin", query = "SELECT MIN(n.lanote) FROM Notesdeselevesseq2 n WHERE n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse=(SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq2.findTauxReussite", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.lanote >= :lanote AND n.listedeseleves =(SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse= :codeclasse)) AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq2.findNouveauxEleves", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) AND NOT EXISTS (SELECT n FROM Notesdeselevesseq2 n WHERE n.listedeseleves = l AND n.notesdeselevesseq2PK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codematiere))")
    , @NamedQuery(name = "Notesdeselevesseq2.findMatiereClasse", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere) AND n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.codeclasse =(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse))")
    , @NamedQuery(name = "Notesdeselevesseq2.findNoteEleve", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.listedeseleves = (SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matricule)AND n.matieres = (SELECT m FROM Matieres m WHERE m.codematiere = :codematiere)")
    , @NamedQuery(name = "Notesdeselevesseq2.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.notesdeselevesseq2PK.matriculeeleve = :matriculeeleve")
    //
    , @NamedQuery(name = "Notesdeselevesseq2.findByMatriculeeleve", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.notesdeselevesseq2PK.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Notesdeselevesseq2.findByCodematiere", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.notesdeselevesseq2PK.codematiere = :codematiere")
    , @NamedQuery(name = "Notesdeselevesseq2.findByLanote", query = "SELECT n FROM Notesdeselevesseq2 n WHERE n.lanote = :lanote")})
public class Notesdeselevesseq2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Notesdeselevesseq2PK notesdeselevesseq2PK;
    @Column(name = "lanote")
    private BigDecimal lanote;
    @JoinColumn(name = "matriculeeleve", referencedColumnName = "matriculeeleve", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Listedeseleves listedeseleves;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Notesdeselevesseq2() {
    }

    public Notesdeselevesseq2(Notesdeselevesseq2PK notesdeselevesseq2PK) {
        this.notesdeselevesseq2PK = notesdeselevesseq2PK;
    }

    public Notesdeselevesseq2(String matriculeeleve, String codematiere) {
        this.notesdeselevesseq2PK = new Notesdeselevesseq2PK(matriculeeleve, codematiere);
    }

    public Notesdeselevesseq2PK getNotesdeselevesseq2PK() {
        return notesdeselevesseq2PK;
    }

    public void setNotesdeselevesseq2PK(Notesdeselevesseq2PK notesdeselevesseq2PK) {
        this.notesdeselevesseq2PK = notesdeselevesseq2PK;
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
        hash += (notesdeselevesseq2PK != null ? notesdeselevesseq2PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notesdeselevesseq2)) {
            return false;
        }
        Notesdeselevesseq2 other = (Notesdeselevesseq2) object;
        if ((this.notesdeselevesseq2PK == null && other.notesdeselevesseq2PK != null) || (this.notesdeselevesseq2PK != null && !this.notesdeselevesseq2PK.equals(other.notesdeselevesseq2PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return notesdeselevesseq2PK + "";
    }

}
