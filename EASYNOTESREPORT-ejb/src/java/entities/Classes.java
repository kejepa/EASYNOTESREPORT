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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "classes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classes.findAll", query = "SELECT c FROM Classes c")
    //
    , @NamedQuery(name = "Classes.findCodeclasse", query = "SELECT c FROM Classes c")
    //
    , @NamedQuery(name = "Classes.findByCodeclasse", query = "SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse")
    , @NamedQuery(name = "Classes.findByNomclasse", query = "SELECT c FROM Classes c WHERE c.nomclasse = :nomclasse")
    , @NamedQuery(name = "Classes.findByNiveau", query = "SELECT c FROM Classes c WHERE c.niveau = :niveau")
    , @NamedQuery(name = "Classes.findByClasscorresp", query = "SELECT c FROM Classes c WHERE c.classcorresp = :classcorresp")})
public class Classes implements Serializable {

    @JoinTable(name = "profprinc", joinColumns = {
        @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse")}, inverseJoinColumns = {
        @JoinColumn(name = "matricule", referencedColumnName = "matricule")})
    @ManyToMany
    private Collection<Personnels> personnelsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Calculnotesdesclasses> calculnotesdesclassesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codeclasse")
    private String codeclasse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "nomclasse")
    private String nomclasse;
    @Size(max = 254)
    @Column(name = "section")
    private String section;
    @Size(max = 254)
    @Column(name = "niveau")
    private String niveau;
    @Size(max = 254)
    @Column(name = "typedeclasse")
    private String typedeclasse;
    @Size(max = 254)
    @Column(name = "classcorresp")
    private String classcorresp;
    @OneToMany(mappedBy = "codeclasse")
    private Collection<Listedeseleves> listedeselevesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Trim3rangdeselevesparmatiere> trim3rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Seq2rangdeselevesparmatiere> seq2rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Seq6rangdeselevesparmatiere> seq6rangdeselevesparmatiereCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "classes")
    private Profprinc profprinc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Seq5rangdeselevesparmatiere> seq5rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Seq1rangdeselevesparmatiere> seq1rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Clamatcoeffgrpe> clamatcoeffgrpeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Trim1rangdeselevesparmatiere> trim1rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Seq4rangdeselevesparmatiere> seq4rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Trim2rangdeselevesparmatiere> trim2rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Annrangdeselevesparmatiere> annmoyennesrangsdeselevesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Seq3rangdeselevesparmatiere> seq3rangdeselevesparmatiereCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes")
    private Collection<Ensgclamat> ensgclamatCollection;

    public Classes() {
    }

    public Classes(String codeclasse) {
        this.codeclasse = codeclasse;
    }

    public Classes(String codeclasse, String nomclasse) {
        this.codeclasse = codeclasse;
        this.nomclasse = nomclasse;
    }

    public String getCodeclasse() {
        return codeclasse;
    }

    public void setCodeclasse(String codeclasse) {
        this.codeclasse = codeclasse;
    }

    public String getNomclasse() {
        return nomclasse;
    }

    public void setNomclasse(String nomclasse) {
        this.nomclasse = nomclasse;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getTypedeclasse() {
        return typedeclasse;
    }

    public void setTypedeclasse(String typedeclasse) {
        this.typedeclasse = typedeclasse;
    }

    public String getClasscorresp() {
        return classcorresp;
    }

    public void setClasscorresp(String classcorresp) {
        this.classcorresp = classcorresp;
    }

    @XmlTransient
    public Collection<Listedeseleves> getListedeselevesCollection() {
        return listedeselevesCollection;
    }

    public void setListedeselevesCollection(Collection<Listedeseleves> listedeselevesCollection) {
        this.listedeselevesCollection = listedeselevesCollection;
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

    public Profprinc getProfprinc() {
        return profprinc;
    }

    public void setProfprinc(Profprinc profprinc) {
        this.profprinc = profprinc;
    }

    @XmlTransient
    public Collection<Seq5rangdeselevesparmatiere> getSeq5rangdeselevesparmatiereCollection() {
        return seq5rangdeselevesparmatiereCollection;
    }

    public void setSeq5rangdeselevesparmatiereCollection(Collection<Seq5rangdeselevesparmatiere> seq5rangdeselevesparmatiereCollection) {
        this.seq5rangdeselevesparmatiereCollection = seq5rangdeselevesparmatiereCollection;
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
    public Collection<Calculnotesdesclasses> getCalculnotesdesclassesCollection() {
        return calculnotesdesclassesCollection;
    }

    public void setCalculnotesdesclassesCollection(Collection<Calculnotesdesclasses> calculnotesdesclassesCollection) {
        this.calculnotesdesclassesCollection = calculnotesdesclassesCollection;
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
        hash += (codeclasse != null ? codeclasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classes)) {
            return false;
        }
        Classes other = (Classes) object;
        if ((this.codeclasse == null && other.codeclasse != null) || (this.codeclasse != null && !this.codeclasse.equals(other.codeclasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codeclasse;
    }

    @XmlTransient
    public Collection<Personnels> getPersonnelsCollection() {
        return personnelsCollection;
    }

    public void setPersonnelsCollection(Collection<Personnels> personnelsCollection) {
        this.personnelsCollection = personnelsCollection;
    }
}
