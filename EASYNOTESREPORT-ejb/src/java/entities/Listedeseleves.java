/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "listedeseleves")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listedeseleves.findAll", query = "SELECT l FROM Listedeseleves l")
    //
    ,@NamedQuery(name = "Listedeseleves.findMatriculeeleve", query = "SELECT l FROM Listedeseleves l WHERE l.codeclasse = (SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse) ORDER BY l.nom")
    //
    , @NamedQuery(name = "Listedeseleves.findByMatriculeeleve", query = "SELECT l FROM Listedeseleves l WHERE l.matriculeeleve = :matriculeeleve")
    , @NamedQuery(name = "Listedeseleves.findByNom", query = "SELECT l FROM Listedeseleves l WHERE l.nom = :nom")
    , @NamedQuery(name = "Listedeseleves.findByPrenom", query = "SELECT l FROM Listedeseleves l WHERE l.prenom = :prenom")
    , @NamedQuery(name = "Listedeseleves.findBySexe", query = "SELECT l FROM Listedeseleves l WHERE l.sexe = :sexe")
    , @NamedQuery(name = "Listedeseleves.findByDatenaiss", query = "SELECT l FROM Listedeseleves l WHERE l.datenaiss = :datenaiss")
    , @NamedQuery(name = "Listedeseleves.findByLieunaiss", query = "SELECT l FROM Listedeseleves l WHERE l.lieunaiss = :lieunaiss")
    , @NamedQuery(name = "Listedeseleves.findByAdresse", query = "SELECT l FROM Listedeseleves l WHERE l.adresse = :adresse")
    , @NamedQuery(name = "Listedeseleves.findByRedoublant", query = "SELECT l FROM Listedeseleves l WHERE l.redoublant = :redoublant")
    , @NamedQuery(name = "Listedeseleves.findByInscrit", query = "SELECT l FROM Listedeseleves l WHERE l.inscrit = :inscrit")
    , @NamedQuery(name = "Listedeseleves.findByPhoto", query = "SELECT l FROM Listedeseleves l WHERE l.photo = :photo")

})

public class Listedeseleves implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculeeleve")
    private String matriculeeleve;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "datenaiss")
    @Temporal(TemporalType.DATE)
    private Date datenaiss;
    @Size(max = 254) 
    @Column(name = "lieunaiss")
    private String lieunaiss;
    @Size(max = 254)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 254)
    @Column(name = "redoublant")
    private String redoublant;
    @Size(max = 254)
    @Column(name = "inscrit")
    private String inscrit;
    @Size(max = 254)
    @Column(name = "photo")
    private String photo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Seq3moyennesrangsdeseleves seq3moyennesrangsdeseleves;
    @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse")
    @ManyToOne
    private Classes codeclasse;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevesseq1 disciplinesdeselevesseq1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Annrangdeselevesparmatiere> annrangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevesseq5 disciplinesdeselevesseq5;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevesseq4 disciplinesdeselevesseq4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Trim3rangdeselevesparmatiere> trim3rangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevesseq3 disciplinesdeselevesseq3;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevesseq2 disciplinesdeselevesseq2;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevesseq6 disciplinesdeselevesseq6;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Seq4moyennesrangsdeseleves seq4moyennesrangsdeseleves;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevestrim2 disciplinesdeselevestrim2;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevestrim1 disciplinesdeselevestrim1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Seq2rangdeselevesparmatiere> seq2rangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevestrim3 disciplinesdeselevestrim3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Seq6rangdeselevesparmatiere> seq6rangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Seq5moyennesrangsdeseleves seq5moyennesrangsdeseleves;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevestrim2> notesdeselevestrim2Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevestrim1> notesdeselevestrim1Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevestrim3> notesdeselevestrim3Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Seq5rangdeselevesparmatiere> seq5rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevesseq4> notesdeselevesseq4Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevesseq5> notesdeselevesseq5Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevesseq6> notesdeselevesseq6Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Seq1rangdeselevesparmatiere> seq1rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevesseq1> notesdeselevesseq1Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevesseq2> notesdeselevesseq2Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevesseq3> notesdeselevesseq3Collection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Trim3moyennesrangsdeseleves trim3moyennesrangsdeseleves;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Trim1rangdeselevesparmatiere> trim1rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Seq4rangdeselevesparmatiere> seq4rangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Seq6moyennesrangsdeseleves seq6moyennesrangsdeseleves;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Notesdeselevesann> notesdeselevesannCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Conseildeclasseann conseildeclasseann;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Seq1moyennesrangsdeseleves seq1moyennesrangsdeseleves;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Disciplinesdeselevesann disciplinesdeselevesann;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Trim2rangdeselevesparmatiere> trim2rangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Annmoyennesrangsdeseleves annmoyennesrangsdeseleves;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Trim1moyennesrangsdeseleves trim1moyennesrangsdeseleves;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Seq2moyennesrangsdeseleves seq2moyennesrangsdeseleves;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Trim2moyennesrangsdeseleves trim2moyennesrangsdeseleves;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Collection<Seq3rangdeselevesparmatiere> seq3rangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Conseildeclassetrim3 conseildeclassetrim3;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Conseildeclassetrim2 conseildeclassetrim2;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "listedeseleves")
    private Conseildeclassetrim1 conseildeclassetrim1;

    public Listedeseleves() {
    }

    public Listedeseleves(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public String getMatriculeeleve() {
        return matriculeeleve;
    }

    public void setMatriculeeleve(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getLieunaiss() {
        return lieunaiss;
    }

    public void setLieunaiss(String lieunaiss) {
        this.lieunaiss = lieunaiss;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRedoublant() {
        return redoublant;
    }

    public void setRedoublant(String redoublant) {
        this.redoublant = redoublant;
    }

    public String getInscrit() {
        return inscrit;
    }

    public void setInscrit(String inscrit) {
        this.inscrit = inscrit;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Seq3moyennesrangsdeseleves getSeq3moyennesrangsdeseleves() {
        return seq3moyennesrangsdeseleves;
    }

    public void setSeq3moyennesrangsdeseleves(Seq3moyennesrangsdeseleves seq3moyennesrangsdeseleves) {
        this.seq3moyennesrangsdeseleves = seq3moyennesrangsdeseleves;
    }

    public Classes getCodeclasse() {
        return codeclasse;
    }

    public void setCodeclasse(Classes codeclasse) {
        this.codeclasse = codeclasse;
    }

    public Disciplinesdeselevesseq1 getDisciplinesdeselevesseq1() {
        return disciplinesdeselevesseq1;
    }

    public void setDisciplinesdeselevesseq1(Disciplinesdeselevesseq1 disciplinesdeselevesseq1) {
        this.disciplinesdeselevesseq1 = disciplinesdeselevesseq1;
    }

    @XmlTransient
    public Collection<Annrangdeselevesparmatiere> getAnnrangdeselevesparmatiereCollection() {
        return annrangdeselevesparmatiereCollection;
    }

    public void setAnnrangdeselevesparmatiereCollection(Collection<Annrangdeselevesparmatiere> annrangdeselevesparmatiereCollection) {
        this.annrangdeselevesparmatiereCollection = annrangdeselevesparmatiereCollection;
    }

    public Disciplinesdeselevesseq5 getDisciplinesdeselevesseq5() {
        return disciplinesdeselevesseq5;
    }

    public void setDisciplinesdeselevesseq5(Disciplinesdeselevesseq5 disciplinesdeselevesseq5) {
        this.disciplinesdeselevesseq5 = disciplinesdeselevesseq5;
    }

    public Disciplinesdeselevesseq4 getDisciplinesdeselevesseq4() {
        return disciplinesdeselevesseq4;
    }

    public void setDisciplinesdeselevesseq4(Disciplinesdeselevesseq4 disciplinesdeselevesseq4) {
        this.disciplinesdeselevesseq4 = disciplinesdeselevesseq4;
    }

    @XmlTransient
    public Collection<Trim3rangdeselevesparmatiere> getTrim3rangdeselevesparmatiereCollection() {
        return trim3rangdeselevesparmatiereCollection;
    }

    public void setTrim3rangdeselevesparmatiereCollection(Collection<Trim3rangdeselevesparmatiere> trim3rangdeselevesparmatiereCollection) {
        this.trim3rangdeselevesparmatiereCollection = trim3rangdeselevesparmatiereCollection;
    }

    public Disciplinesdeselevesseq3 getDisciplinesdeselevesseq3() {
        return disciplinesdeselevesseq3;
    }

    public void setDisciplinesdeselevesseq3(Disciplinesdeselevesseq3 disciplinesdeselevesseq3) {
        this.disciplinesdeselevesseq3 = disciplinesdeselevesseq3;
    }

    public Disciplinesdeselevesseq2 getDisciplinesdeselevesseq2() {
        return disciplinesdeselevesseq2;
    }

    public void setDisciplinesdeselevesseq2(Disciplinesdeselevesseq2 disciplinesdeselevesseq2) {
        this.disciplinesdeselevesseq2 = disciplinesdeselevesseq2;
    }

    public Disciplinesdeselevesseq6 getDisciplinesdeselevesseq6() {
        return disciplinesdeselevesseq6;
    }

    public void setDisciplinesdeselevesseq6(Disciplinesdeselevesseq6 disciplinesdeselevesseq6) {
        this.disciplinesdeselevesseq6 = disciplinesdeselevesseq6;
    }

    public Seq4moyennesrangsdeseleves getSeq4moyennesrangsdeseleves() {
        return seq4moyennesrangsdeseleves;
    }

    public void setSeq4moyennesrangsdeseleves(Seq4moyennesrangsdeseleves seq4moyennesrangsdeseleves) {
        this.seq4moyennesrangsdeseleves = seq4moyennesrangsdeseleves;
    }

    public Disciplinesdeselevestrim2 getDisciplinesdeselevestrim2() {
        return disciplinesdeselevestrim2;
    }

    public void setDisciplinesdeselevestrim2(Disciplinesdeselevestrim2 disciplinesdeselevestrim2) {
        this.disciplinesdeselevestrim2 = disciplinesdeselevestrim2;
    }

    public Disciplinesdeselevestrim1 getDisciplinesdeselevestrim1() {
        return disciplinesdeselevestrim1;
    }

    public void setDisciplinesdeselevestrim1(Disciplinesdeselevestrim1 disciplinesdeselevestrim1) {
        this.disciplinesdeselevestrim1 = disciplinesdeselevestrim1;
    }

    @XmlTransient
    public Collection<Seq2rangdeselevesparmatiere> getSeq2rangdeselevesparmatiereCollection() {
        return seq2rangdeselevesparmatiereCollection;
    }

    public void setSeq2rangdeselevesparmatiereCollection(Collection<Seq2rangdeselevesparmatiere> seq2rangdeselevesparmatiereCollection) {
        this.seq2rangdeselevesparmatiereCollection = seq2rangdeselevesparmatiereCollection;
    }

    public Disciplinesdeselevestrim3 getDisciplinesdeselevestrim3() {
        return disciplinesdeselevestrim3;
    }

    public void setDisciplinesdeselevestrim3(Disciplinesdeselevestrim3 disciplinesdeselevestrim3) {
        this.disciplinesdeselevestrim3 = disciplinesdeselevestrim3;
    }

    @XmlTransient
    public Collection<Seq6rangdeselevesparmatiere> getSeq6rangdeselevesparmatiereCollection() {
        return seq6rangdeselevesparmatiereCollection;
    }

    public void setSeq6rangdeselevesparmatiereCollection(Collection<Seq6rangdeselevesparmatiere> seq6rangdeselevesparmatiereCollection) {
        this.seq6rangdeselevesparmatiereCollection = seq6rangdeselevesparmatiereCollection;
    }

    public Seq5moyennesrangsdeseleves getSeq5moyennesrangsdeseleves() {
        return seq5moyennesrangsdeseleves;
    }

    public void setSeq5moyennesrangsdeseleves(Seq5moyennesrangsdeseleves seq5moyennesrangsdeseleves) {
        this.seq5moyennesrangsdeseleves = seq5moyennesrangsdeseleves;
    }

    @XmlTransient
    public Collection<Notesdeselevestrim2> getNotesdeselevestrim2Collection() {
        return notesdeselevestrim2Collection;
    }

    public void setNotesdeselevestrim2Collection(Collection<Notesdeselevestrim2> notesdeselevestrim2Collection) {
        this.notesdeselevestrim2Collection = notesdeselevestrim2Collection;
    }

    @XmlTransient
    public Collection<Notesdeselevestrim1> getNotesdeselevestrim1Collection() {
        return notesdeselevestrim1Collection;
    }

    public void setNotesdeselevestrim1Collection(Collection<Notesdeselevestrim1> notesdeselevestrim1Collection) {
        this.notesdeselevestrim1Collection = notesdeselevestrim1Collection;
    }

    @XmlTransient
    public Collection<Notesdeselevestrim3> getNotesdeselevestrim3Collection() {
        return notesdeselevestrim3Collection;
    }

    public void setNotesdeselevestrim3Collection(Collection<Notesdeselevestrim3> notesdeselevestrim3Collection) {
        this.notesdeselevestrim3Collection = notesdeselevestrim3Collection;
    }

    @XmlTransient
    public Collection<Seq5rangdeselevesparmatiere> getSeq5rangdeselevesparmatiereCollection() {
        return seq5rangdeselevesparmatiereCollection;
    }

    public void setSeq5rangdeselevesparmatiereCollection(Collection<Seq5rangdeselevesparmatiere> seq5rangdeselevesparmatiereCollection) {
        this.seq5rangdeselevesparmatiereCollection = seq5rangdeselevesparmatiereCollection;
    }

    @XmlTransient
    public Collection<Notesdeselevesseq4> getNotesdeselevesseq4Collection() {
        return notesdeselevesseq4Collection;
    }

    public void setNotesdeselevesseq4Collection(Collection<Notesdeselevesseq4> notesdeselevesseq4Collection) {
        this.notesdeselevesseq4Collection = notesdeselevesseq4Collection;
    }

    @XmlTransient
    public Collection<Notesdeselevesseq5> getNotesdeselevesseq5Collection() {
        return notesdeselevesseq5Collection;
    }

    public void setNotesdeselevesseq5Collection(Collection<Notesdeselevesseq5> notesdeselevesseq5Collection) {
        this.notesdeselevesseq5Collection = notesdeselevesseq5Collection;
    }

    @XmlTransient
    public Collection<Notesdeselevesseq6> getNotesdeselevesseq6Collection() {
        return notesdeselevesseq6Collection;
    }

    public void setNotesdeselevesseq6Collection(Collection<Notesdeselevesseq6> notesdeselevesseq6Collection) {
        this.notesdeselevesseq6Collection = notesdeselevesseq6Collection;
    }

    @XmlTransient
    public Collection<Seq1rangdeselevesparmatiere> getSeq1rangdeselevesparmatiereCollection() {
        return seq1rangdeselevesparmatiereCollection;
    }

    public void setSeq1rangdeselevesparmatiereCollection(Collection<Seq1rangdeselevesparmatiere> seq1rangdeselevesparmatiereCollection) {
        this.seq1rangdeselevesparmatiereCollection = seq1rangdeselevesparmatiereCollection;
    }

    @XmlTransient
    public Collection<Notesdeselevesseq1> getNotesdeselevesseq1Collection() {
        return notesdeselevesseq1Collection;
    }

    public void setNotesdeselevesseq1Collection(Collection<Notesdeselevesseq1> notesdeselevesseq1Collection) {
        this.notesdeselevesseq1Collection = notesdeselevesseq1Collection;
    }

    @XmlTransient
    public Collection<Notesdeselevesseq2> getNotesdeselevesseq2Collection() {
        return notesdeselevesseq2Collection;
    }

    public void setNotesdeselevesseq2Collection(Collection<Notesdeselevesseq2> notesdeselevesseq2Collection) {
        this.notesdeselevesseq2Collection = notesdeselevesseq2Collection;
    }

    @XmlTransient
    public Collection<Notesdeselevesseq3> getNotesdeselevesseq3Collection() {
        return notesdeselevesseq3Collection;
    }

    public void setNotesdeselevesseq3Collection(Collection<Notesdeselevesseq3> notesdeselevesseq3Collection) {
        this.notesdeselevesseq3Collection = notesdeselevesseq3Collection;
    }

    public Trim3moyennesrangsdeseleves getTrim3moyennesrangsdeseleves() {
        return trim3moyennesrangsdeseleves;
    }

    public void setTrim3moyennesrangsdeseleves(Trim3moyennesrangsdeseleves trim3moyennesrangsdeseleves) {
        this.trim3moyennesrangsdeseleves = trim3moyennesrangsdeseleves;
    }

    @XmlTransient
    public Collection<Trim1rangdeselevesparmatiere> getTrim1rangdeselevesparmatiereCollection() {
        return trim1rangdeselevesparmatiereCollection;
    }

    public void setTrim1rangdeselevesparmatiereCollection(Collection<Trim1rangdeselevesparmatiere> trim1rangdeselevesparmatiereCollection) {
        this.trim1rangdeselevesparmatiereCollection = trim1rangdeselevesparmatiereCollection;
    }

    @XmlTransient
    public Collection<Seq4rangdeselevesparmatiere> getSeq4rangdeselevesparmatiereCollection() {
        return seq4rangdeselevesparmatiereCollection;
    }

    public void setSeq4rangdeselevesparmatiereCollection(Collection<Seq4rangdeselevesparmatiere> seq4rangdeselevesparmatiereCollection) {
        this.seq4rangdeselevesparmatiereCollection = seq4rangdeselevesparmatiereCollection;
    }

    public Seq6moyennesrangsdeseleves getSeq6moyennesrangsdeseleves() {
        return seq6moyennesrangsdeseleves;
    }

    public void setSeq6moyennesrangsdeseleves(Seq6moyennesrangsdeseleves seq6moyennesrangsdeseleves) {
        this.seq6moyennesrangsdeseleves = seq6moyennesrangsdeseleves;
    }

    @XmlTransient
    public Collection<Notesdeselevesann> getNotesdeselevesannCollection() {
        return notesdeselevesannCollection;
    }

    public void setNotesdeselevesannCollection(Collection<Notesdeselevesann> notesdeselevesannCollection) {
        this.notesdeselevesannCollection = notesdeselevesannCollection;
    }

    public Conseildeclasseann getConseildeclasseann() {
        return conseildeclasseann;
    }

    public void setConseildeclasseann(Conseildeclasseann conseildeclasseann) {
        this.conseildeclasseann = conseildeclasseann;
    }

    public Seq1moyennesrangsdeseleves getSeq1moyennesrangsdeseleves() {
        return seq1moyennesrangsdeseleves;
    }

    public void setSeq1moyennesrangsdeseleves(Seq1moyennesrangsdeseleves seq1moyennesrangsdeseleves) {
        this.seq1moyennesrangsdeseleves = seq1moyennesrangsdeseleves;
    }

    public Disciplinesdeselevesann getDisciplinesdeselevesann() {
        return disciplinesdeselevesann;
    }

    public void setDisciplinesdeselevesann(Disciplinesdeselevesann disciplinesdeselevesann) {
        this.disciplinesdeselevesann = disciplinesdeselevesann;
    }

    @XmlTransient
    public Collection<Trim2rangdeselevesparmatiere> getTrim2rangdeselevesparmatiereCollection() {
        return trim2rangdeselevesparmatiereCollection;
    }

    public void setTrim2rangdeselevesparmatiereCollection(Collection<Trim2rangdeselevesparmatiere> trim2rangdeselevesparmatiereCollection) {
        this.trim2rangdeselevesparmatiereCollection = trim2rangdeselevesparmatiereCollection;
    }

    public Annmoyennesrangsdeseleves getAnnmoyennesrangsdeseleves() {
        return annmoyennesrangsdeseleves;
    }

    public void setAnnmoyennesrangsdeseleves(Annmoyennesrangsdeseleves annmoyennesrangsdeseleves) {
        this.annmoyennesrangsdeseleves = annmoyennesrangsdeseleves;
    }

    public Trim1moyennesrangsdeseleves getTrim1moyennesrangsdeseleves() {
        return trim1moyennesrangsdeseleves;
    }

    public void setTrim1moyennesrangsdeseleves(Trim1moyennesrangsdeseleves trim1moyennesrangsdeseleves) {
        this.trim1moyennesrangsdeseleves = trim1moyennesrangsdeseleves;
    }

    public Seq2moyennesrangsdeseleves getSeq2moyennesrangsdeseleves() {
        return seq2moyennesrangsdeseleves;
    }

    public void setSeq2moyennesrangsdeseleves(Seq2moyennesrangsdeseleves seq2moyennesrangsdeseleves) {
        this.seq2moyennesrangsdeseleves = seq2moyennesrangsdeseleves;
    }

    public Trim2moyennesrangsdeseleves getTrim2moyennesrangsdeseleves() {
        return trim2moyennesrangsdeseleves;
    }

    public void setTrim2moyennesrangsdeseleves(Trim2moyennesrangsdeseleves trim2moyennesrangsdeseleves) {
        this.trim2moyennesrangsdeseleves = trim2moyennesrangsdeseleves;
    }

    @XmlTransient
    public Collection<Seq3rangdeselevesparmatiere> getSeq3rangdeselevesparmatiereCollection() {
        return seq3rangdeselevesparmatiereCollection;
    }

    public void setSeq3rangdeselevesparmatiereCollection(Collection<Seq3rangdeselevesparmatiere> seq3rangdeselevesparmatiereCollection) {
        this.seq3rangdeselevesparmatiereCollection = seq3rangdeselevesparmatiereCollection;
    }

    public Conseildeclassetrim3 getConseildeclassetrim3() {
        return conseildeclassetrim3;
    }

    public void setConseildeclassetrim3(Conseildeclassetrim3 conseildeclassetrim3) {
        this.conseildeclassetrim3 = conseildeclassetrim3;
    }

    public Conseildeclassetrim2 getConseildeclassetrim2() {
        return conseildeclassetrim2;
    }

    public void setConseildeclassetrim2(Conseildeclassetrim2 conseildeclassetrim2) {
        this.conseildeclassetrim2 = conseildeclassetrim2;
    }

    public Conseildeclassetrim1 getConseildeclassetrim1() {
        return conseildeclassetrim1;
    }

    public void setConseildeclassetrim1(Conseildeclassetrim1 conseildeclassetrim1) {
        this.conseildeclassetrim1 = conseildeclassetrim1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculeeleve != null ? matriculeeleve.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listedeseleves)) {
            return false;
        }
        Listedeseleves other = (Listedeseleves) object;
        if ((this.matriculeeleve == null && other.matriculeeleve != null) || (this.matriculeeleve != null && !this.matriculeeleve.equals(other.matriculeeleve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return matriculeeleve; 
    }

}
