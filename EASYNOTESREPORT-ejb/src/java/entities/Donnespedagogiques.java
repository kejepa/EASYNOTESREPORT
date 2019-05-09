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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "donnespedagogiques")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Donnespedagogiques.findAll", query = "SELECT d FROM Donnespedagogiques d")
    //
    ,@NamedQuery(name = "Donnespedagogiques.findByCodematiereClasse", query = "SELECT d FROM Donnespedagogiques d WHERE d.donnespedagogiquesPK.codematiere = :codematiere and d.donnespedagogiquesPK.niveaux = :codeclasse and d.donnespedagogiquesPK.periode = :periode")
    , @NamedQuery(name = "Donnespedagogiques.findCodeclasse", query = "SELECT c.classcorresp FROM Classes c GROUP BY c.classcorresp ORDER BY c.classcorresp")
    , @NamedQuery(name = "Donnespedagogiques.findByCodeMatiere", query = "SELECT c.clamatcoeffgrpePK.codematiere FROM Clamatcoeffgrpe c WHERE c.clamatcoeffgrpePK.codeclasse IN(SELECT c.codeclasse FROM Classes c WHERE c.classcorresp = :classcorresp) GROUP BY c.clamatcoeffgrpePK.codematiere")
    , @NamedQuery(name = "Donnespedagogiques.findMatiere", query = "SELECT c.clamatcoeffgrpePK.codematiere FROM Clamatcoeffgrpe c where c.clamatcoeffgrpePK.codeclasse IN(SELECT cl.codeclasse FROM Classes cl WHERE cl.classcorresp = :classcorresp) ORDER BY c.matieres")
    , @NamedQuery(name = "Donnespedagogiques.findTypedeclasse", query = "SELECT c.typedeclasse FROM Classes c WHERE c.classcorresp = :classcorresp")
    , @NamedQuery(name = "Donnespedagogiques.findEffByNiveaux", query = "SELECT COUNT(l.matriculeeleve) FROM Listedeseleves l WHERE l.codeclasse IN(SELECT c.codeclasse FROM Classes c WHERE c.classcorresp = :classcorresp)")
//
    , @NamedQuery(name = "Donnespedagogiques.findByCodematiere", query = "SELECT d FROM Donnespedagogiques d WHERE d.donnespedagogiquesPK.codematiere = :codematiere")
    , @NamedQuery(name = "Donnespedagogiques.findByNiveaux", query = "SELECT d FROM Donnespedagogiques d WHERE d.donnespedagogiquesPK.niveaux = :niveaux")
    , @NamedQuery(name = "Donnespedagogiques.findByPeriode", query = "SELECT d FROM Donnespedagogiques d WHERE d.donnespedagogiquesPK.periode = :periode")
    , @NamedQuery(name = "Donnespedagogiques.findByLpcpt", query = "SELECT d FROM Donnespedagogiques d WHERE d.lpcpt = :lpcpt")
    , @NamedQuery(name = "Donnespedagogiques.findByLfcpt", query = "SELECT d FROM Donnespedagogiques d WHERE d.lfcpt = :lfcpt")
    , @NamedQuery(name = "Donnespedagogiques.findByLpcpp", query = "SELECT d FROM Donnespedagogiques d WHERE d.lpcpp = :lpcpp")
    , @NamedQuery(name = "Donnespedagogiques.findByLfcpp", query = "SELECT d FROM Donnespedagogiques d WHERE d.lfcpp = :lfcpp")
    , @NamedQuery(name = "Donnespedagogiques.findByHpchc", query = "SELECT d FROM Donnespedagogiques d WHERE d.hpchc = :hpchc")
    , @NamedQuery(name = "Donnespedagogiques.findByHfchc", query = "SELECT d FROM Donnespedagogiques d WHERE d.hfchc = :hfchc")
    , @NamedQuery(name = "Donnespedagogiques.findByNae", query = "SELECT d FROM Donnespedagogiques d WHERE d.nae = :nae")
    , @NamedQuery(name = "Donnespedagogiques.findByNsc", query = "SELECT d FROM Donnespedagogiques d WHERE d.nsc = :nsc")})
public class Donnespedagogiques implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DonnespedagogiquesPK donnespedagogiquesPK;
    @Column(name = "lpcpt")
    private BigDecimal lpcpt;
    @Column(name = "lfcpt")
    private BigDecimal lfcpt;
    @Column(name = "lpcpp")
    private BigDecimal lpcpp;
    @Column(name = "lfcpp")
    private BigDecimal lfcpp;
    @Column(name = "hpchc")
    private BigDecimal hpchc;
    @Column(name = "hfchc")
    private BigDecimal hfchc;
    @Column(name = "nn")
    private BigDecimal nn;
    @Column(name = "mg")
    private BigDecimal mg;
    @Column(name = "nae")
    private BigDecimal nae;
    @Column(name = "nsc")
    private BigDecimal nsc;
    @Size(max = 254)
    @Column(name = "typedeclasse")
    private String typedeclasse;
    @Column(name = "eff")
    private BigDecimal eff;
    @Size(max = 254)
    @Column(name = "section")
    private String section;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Donnespedagogiques() {
    }

    public Donnespedagogiques(DonnespedagogiquesPK donnespedagogiquesPK) {
        this.donnespedagogiquesPK = donnespedagogiquesPK;
    }

    public Donnespedagogiques(String codematiere, String niveaux, String periode) {
        this.donnespedagogiquesPK = new DonnespedagogiquesPK(codematiere, niveaux, periode);
    }

    public DonnespedagogiquesPK getDonnespedagogiquesPK() {
        return donnespedagogiquesPK;
    }

    public void setDonnespedagogiquesPK(DonnespedagogiquesPK donnespedagogiquesPK) {
        this.donnespedagogiquesPK = donnespedagogiquesPK;
    }

    public BigDecimal getLpcpt() {
        return lpcpt;
    }

    public void setLpcpt(BigDecimal lpcpt) {
        this.lpcpt = lpcpt;
    }

    public BigDecimal getLfcpt() {
        return lfcpt;
    }

    public void setLfcpt(BigDecimal lfcpt) {
        this.lfcpt = lfcpt;
    }

    public BigDecimal getLpcpp() {
        return lpcpp;
    }

    public void setLpcpp(BigDecimal lpcpp) {
        this.lpcpp = lpcpp;
    }

    public BigDecimal getLfcpp() {
        return lfcpp;
    }

    public void setLfcpp(BigDecimal lfcpp) {
        this.lfcpp = lfcpp;
    }

    public BigDecimal getHpchc() {
        return hpchc;
    }

    public void setHpchc(BigDecimal hpchc) {
        this.hpchc = hpchc;
    }

    public BigDecimal getHfchc() {
        return hfchc;
    }

    public void setHfchc(BigDecimal hfchc) {
        this.hfchc = hfchc;
    }

    public BigDecimal getNn() {
        return nn;
    }

    public void setNn(BigDecimal nn) {
        this.nn = nn;
    }

    public BigDecimal getMg() {
        return mg;
    }

    public void setMg(BigDecimal mg) {
        this.mg = mg;
    }

    public BigDecimal getNae() {
        return nae;
    }

    public void setNae(BigDecimal nae) {
        this.nae = nae;
    }

    public BigDecimal getNsc() {
        return nsc;
    }

    public void setNsc(BigDecimal nsc) {
        this.nsc = nsc;
    }

    public String getTypedeclasse() {
        return typedeclasse;
    }

    public void setTypedeclasse(String typedeclasse) {
        this.typedeclasse = typedeclasse;
    }

    public BigDecimal getEff() {
        return eff;
    }

    public void setEff(BigDecimal eff) {
        this.eff = eff;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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
        hash += (donnespedagogiquesPK != null ? donnespedagogiquesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donnespedagogiques)) {
            return false;
        }
        Donnespedagogiques other = (Donnespedagogiques) object;
        if ((this.donnespedagogiquesPK == null && other.donnespedagogiquesPK != null) || (this.donnespedagogiquesPK != null && !this.donnespedagogiquesPK.equals(other.donnespedagogiquesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Donnespedagogiques[ donnespedagogiquesPK=" + donnespedagogiquesPK + " ]";
    }

}
