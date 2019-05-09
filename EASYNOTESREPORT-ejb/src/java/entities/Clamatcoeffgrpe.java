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
@Table(name = "clamatcoeffgrpe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clamatcoeffgrpe.findAll", query = "SELECT c FROM Clamatcoeffgrpe c")
    // 
    , @NamedQuery(name = "Clamatcoeffgrpe.findMatiere_Classe", query = "SELECT c FROM Clamatcoeffgrpe c WHERE c.clamatcoeffgrpePK.codeclasse = (SELECT c.codeclasse FROM Classes c WHERE c.codeclasse = :codeclasse) AND c.clamatcoeffgrpePK.codematiere = (SELECT m.codematiere FROM Matieres m WHERE m.codematiere = :codemartiere)")
    , @NamedQuery(name = "Clamatcoeffgrpe.findByCodeMatiere", query = "SELECT c.clamatcoeffgrpePK.codematiere FROM Clamatcoeffgrpe c WHERE c.clamatcoeffgrpePK.codeclasse=(SELECT c.codeclasse FROM Classes c WHERE c.codeclasse = :codeclasse)ORDER BY c.clamatcoeffgrpePK.codematiere ASC")
    , @NamedQuery(name = "Clamatcoeffgrpe.findByMatieres", query = "SELECT DISTINCT c.clamatcoeffgrpePK.codematiere FROM Clamatcoeffgrpe c")
    , @NamedQuery(name = "Clamatcoeffgrpe.findGroupe", query = "SELECT DISTINCT c.groupe FROM Clamatcoeffgrpe c WHERE c.classes=(SELECT c FROM Classes c WHERE c.codeclasse = :codeclasse)")
    , @NamedQuery(name = "Clamatcoeffgrpe.findClasse_clone", query = "SELECT DISTINCT c.clamatcoeffgrpePK.codeclasse FROM Clamatcoeffgrpe c")
    , @NamedQuery(name = "Clamatcoeffgrpe.cloneclasse", query = "SELECT c.clamatcoeffgrpePK.codematiere,c.coefficient,c.groupe FROM Clamatcoeffgrpe c WHERE c.clamatcoeffgrpePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Clamatcoeffgrpe.findMatiere", query = "SELECT c.clamatcoeffgrpePK.codematiere FROM Clamatcoeffgrpe c where c.clamatcoeffgrpePK.codeclasse = :codeclasse ORDER BY c.matieres")
//
    , @NamedQuery(name = "Clamatcoeffgrpe.findByCodematiere", query = "SELECT c FROM Clamatcoeffgrpe c WHERE c.clamatcoeffgrpePK.codematiere = :codematiere")
    , @NamedQuery(name = "Clamatcoeffgrpe.findByCodeclasse", query = "SELECT c FROM Clamatcoeffgrpe c WHERE c.clamatcoeffgrpePK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Clamatcoeffgrpe.findByCoefficient", query = "SELECT c FROM Clamatcoeffgrpe c WHERE c.coefficient = :coefficient")
    , @NamedQuery(name = "Clamatcoeffgrpe.findByGroupe", query = "SELECT c FROM Clamatcoeffgrpe c WHERE c.groupe = :groupe")})
public class Clamatcoeffgrpe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClamatcoeffgrpePK clamatcoeffgrpePK;
    @Column(name = "coefficient")
    private BigDecimal coefficient;
    @Column(name = "groupe")
    private Integer groupe;
    @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classes classes;
    @JoinColumn(name = "codematiere", referencedColumnName = "codematiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matieres matieres;

    public Clamatcoeffgrpe() {
    }

    public Clamatcoeffgrpe(ClamatcoeffgrpePK clamatcoeffgrpePK) {
        this.clamatcoeffgrpePK = clamatcoeffgrpePK;
    }

    public Clamatcoeffgrpe(String codematiere, String codeclasse) {
        this.clamatcoeffgrpePK = new ClamatcoeffgrpePK(codematiere, codeclasse);
    }

    public ClamatcoeffgrpePK getClamatcoeffgrpePK() {
        return clamatcoeffgrpePK;
    }

    public void setClamatcoeffgrpePK(ClamatcoeffgrpePK clamatcoeffgrpePK) {
        this.clamatcoeffgrpePK = clamatcoeffgrpePK;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getGroupe() {
        return groupe;
    }

    public void setGroupe(Integer groupe) {
        this.groupe = groupe;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
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
        hash += (clamatcoeffgrpePK != null ? clamatcoeffgrpePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clamatcoeffgrpe)) {
            return false;
        }
        Clamatcoeffgrpe other = (Clamatcoeffgrpe) object;
        if ((this.clamatcoeffgrpePK == null && other.clamatcoeffgrpePK != null) || (this.clamatcoeffgrpePK != null && !this.clamatcoeffgrpePK.equals(other.clamatcoeffgrpePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return clamatcoeffgrpePK + "";
    }

}
