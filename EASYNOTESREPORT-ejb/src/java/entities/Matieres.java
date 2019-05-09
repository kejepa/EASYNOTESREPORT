/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "matieres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matieres.findAll", query = "SELECT m FROM Matieres m")
    //
    , @NamedQuery(name = "Matieres.findMatieresParClasse", query = "SELECT m FROM Matieres m where m.codematiere in (SELECT c.clamatcoeffgrpePK.codematiere FROM Clamatcoeffgrpe c WHERE c.clamatcoeffgrpePK.codeclasse = :codeclasse)")
//
    , @NamedQuery(name = "Matieres.findByCodematiere", query = "SELECT m FROM Matieres m WHERE m.codematiere = :codematiere")
    , @NamedQuery(name = "Matieres.findByNommat", query = "SELECT m FROM Matieres m WHERE m.nommat = :nommat")
    , @NamedQuery(name = "Matieres.findByType", query = "SELECT m FROM Matieres m WHERE m.type = :type")
    , @NamedQuery(name = "Matieres.findByPriorite", query = "SELECT m FROM Matieres m WHERE m.priorite = :priorite")})
public class Matieres implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesminmaxgenparmat> notesminmaxgenparmatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")

    @JoinColumn(name = "codespecialite", referencedColumnName = "codespecialite")
    @ManyToOne
    private Specialite codespecialite;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codematiere")
    private String codematiere;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "nommat")
    private String nommat;
    @Size(max = 254)
    @Column(name = "type")
    private String type;
    @Column(name = "priorite")
    private Integer priorite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Trim3rangdeselevesparmatiere> trim3rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Seq2rangdeselevesparmatiere> seq2rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Seq6rangdeselevesparmatiere> seq6rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevestrim2> notesdeselevestrim2Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevestrim1> notesdeselevestrim1Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevestrim3> notesdeselevestrim3Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Seq5rangdeselevesparmatiere> seq5rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevesseq4> notesdeselevesseq4Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevesseq5> notesdeselevesseq5Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevesseq6> notesdeselevesseq6Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Seq1rangdeselevesparmatiere> seq1rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Clamatcoeffgrpe> clamatcoeffgrpeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevesseq1> notesdeselevesseq1Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevesseq2> notesdeselevesseq2Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevesseq3> notesdeselevesseq3Collection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Trim1rangdeselevesparmatiere> trim1rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Seq4rangdeselevesparmatiere> seq4rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Notesdeselevesann> notesdeselevesannCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Trim2rangdeselevesparmatiere> trim2rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Annrangdeselevesparmatiere> annmoyennesrangsdeselevesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Seq3rangdeselevesparmatiere> seq3rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matieres")
    private Collection<Ensgclamat> ensgclamatCollection;

    public Matieres() {
    }

    public Matieres(String codematiere) {
        this.codematiere = codematiere;
    }

    public Matieres(String codematiere, String nommat) {
        this.codematiere = codematiere;
        this.nommat = nommat;
    }

    public String getCodematiere() {
        return codematiere;
    }

    public void setCodematiere(String codematiere) {
        this.codematiere = codematiere;
    }

    public String getNommat() {
        return nommat;
    }

    public void setNommat(String nommat) {
        this.nommat = nommat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPriorite() {
        return priorite;
    }

    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }

    @XmlTransient
    public Collection<Trim3rangdeselevesparmatiere> getTrim3rangdeselevesparmatiereCollection() {
        return trim3rangdeselevesparmatiereCollection;
    }

    public void setTrim3rangdeselevesparmatiereCollection(Collection<Trim3rangdeselevesparmatiere> trim3rangdeselevesparmatiereCollection) {
        this.trim3rangdeselevesparmatiereCollection = trim3rangdeselevesparmatiereCollection;
    }

    @XmlTransient
    public Collection<Seq2rangdeselevesparmatiere> getSeq2rangdeselevesparmatiereCollection() {
        return seq2rangdeselevesparmatiereCollection;
    }

    public void setSeq2rangdeselevesparmatiereCollection(Collection<Seq2rangdeselevesparmatiere> seq2rangdeselevesparmatiereCollection) {
        this.seq2rangdeselevesparmatiereCollection = seq2rangdeselevesparmatiereCollection;
    }

    @XmlTransient
    public Collection<Seq6rangdeselevesparmatiere> getSeq6rangdeselevesparmatiereCollection() {
        return seq6rangdeselevesparmatiereCollection;
    }

    public void setSeq6rangdeselevesparmatiereCollection(Collection<Seq6rangdeselevesparmatiere> seq6rangdeselevesparmatiereCollection) {
        this.seq6rangdeselevesparmatiereCollection = seq6rangdeselevesparmatiereCollection;
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
    public Collection<Clamatcoeffgrpe> getClamatcoeffgrpeCollection() {
        return clamatcoeffgrpeCollection;
    }

    public void setClamatcoeffgrpeCollection(Collection<Clamatcoeffgrpe> clamatcoeffgrpeCollection) {
        this.clamatcoeffgrpeCollection = clamatcoeffgrpeCollection;
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

    @XmlTransient
    public Collection<Notesdeselevesann> getNotesdeselevesannCollection() {
        return notesdeselevesannCollection;
    }

    public void setNotesdeselevesannCollection(Collection<Notesdeselevesann> notesdeselevesannCollection) {
        this.notesdeselevesannCollection = notesdeselevesannCollection;
    }

    @XmlTransient
    public Collection<Trim2rangdeselevesparmatiere> getTrim2rangdeselevesparmatiereCollection() {
        return trim2rangdeselevesparmatiereCollection;
    }

    public void setTrim2rangdeselevesparmatiereCollection(Collection<Trim2rangdeselevesparmatiere> trim2rangdeselevesparmatiereCollection) {
        this.trim2rangdeselevesparmatiereCollection = trim2rangdeselevesparmatiereCollection;
    }

    @XmlTransient
    public Collection<Annrangdeselevesparmatiere> getAnnmoyennesrangsdeselevesCollection() {
        return annmoyennesrangsdeselevesCollection;
    }

    public void setAnnmoyennesrangsdeselevesCollection(Collection<Annrangdeselevesparmatiere> annmoyennesrangsdeselevesCollection) {
        this.annmoyennesrangsdeselevesCollection = annmoyennesrangsdeselevesCollection;
    }

    @XmlTransient
    public Collection<Seq3rangdeselevesparmatiere> getSeq3rangdeselevesparmatiereCollection() {
        return seq3rangdeselevesparmatiereCollection;
    }

    public void setSeq3rangdeselevesparmatiereCollection(Collection<Seq3rangdeselevesparmatiere> seq3rangdeselevesparmatiereCollection) {
        this.seq3rangdeselevesparmatiereCollection = seq3rangdeselevesparmatiereCollection;
    }

    @XmlTransient
    public Collection<Ensgclamat> getEnsgclamatCollection() {
        return ensgclamatCollection;
    }

    public void setEnsgclamatCollection(Collection<Ensgclamat> ensgclamatCollection) {
        this.ensgclamatCollection = ensgclamatCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codematiere != null ? codematiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matieres)) {
            return false;
        }
        Matieres other = (Matieres) object;
        if ((this.codematiere == null && other.codematiere != null) || (this.codematiere != null && !this.codematiere.equals(other.codematiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codematiere;
    }

    public Specialite getCodespecialite() {
        return codespecialite;
    }

    public void setCodespecialite(Specialite codespecialite) {
        this.codespecialite = codespecialite;
    }

    @XmlTransient
    public Collection<Notesminmaxgenparmat> getNotesminmaxgenparmatCollection() {
        return notesminmaxgenparmatCollection;
    }

    public void setNotesminmaxgenparmatCollection(Collection<Notesminmaxgenparmat> notesminmaxgenparmatCollection) {
        this.notesminmaxgenparmatCollection = notesminmaxgenparmatCollection;
    }

}
