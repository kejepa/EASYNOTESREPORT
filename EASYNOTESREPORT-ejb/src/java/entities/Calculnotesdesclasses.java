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
@Table(name = "calculnotesdesclasses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calculnotesdesclasses.findAll", query = "SELECT c FROM Calculnotesdesclasses c")
    //
    ,@NamedQuery(name = "Calculnotesdesclasses.existe_Classe_Seq", query = "SELECT c FROM Calculnotesdesclasses c WHERE c.calculnotesdesclassesPK.periode = :periode AND c.calculnotesdesclassesPK.codeclasse = (SELECT c.codeclasse FROM Classes c WHERE c.codeclasse = :codeclasse)")
    //
    , @NamedQuery(name = "Calculnotesdesclasses.findByPeriode", query = "SELECT c FROM Calculnotesdesclasses c WHERE c.calculnotesdesclassesPK.periode = :periode")
    , @NamedQuery(name = "Calculnotesdesclasses.findByCodeclasse", query = "SELECT c FROM Calculnotesdesclasses c WHERE c.calculnotesdesclassesPK.codeclasse = :codeclasse")
    , @NamedQuery(name = "Calculnotesdesclasses.findByMoyenne", query = "SELECT c FROM Calculnotesdesclasses c WHERE c.moyenne = :moyenne")
    , @NamedQuery(name = "Calculnotesdesclasses.findByMoyennepremier", query = "SELECT c FROM Calculnotesdesclasses c WHERE c.moyennepremier = :moyennepremier")
    , @NamedQuery(name = "Calculnotesdesclasses.findByMoyennedernier", query = "SELECT c FROM Calculnotesdesclasses c WHERE c.moyennedernier = :moyennedernier")
    , @NamedQuery(name = "Calculnotesdesclasses.findByTauxreussite", query = "SELECT c FROM Calculnotesdesclasses c WHERE c.tauxreussite = :tauxreussite")})
public class Calculnotesdesclasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CalculnotesdesclassesPK calculnotesdesclassesPK;
    @Column(name = "moyenne")
    private BigDecimal moyenne;
    @Column(name = "moyennepremier")
    private BigDecimal moyennepremier;
    @Column(name = "moyennedernier")
    private BigDecimal moyennedernier;
    @Size(max = 254)
    @Column(name = "tauxreussite")
    private String tauxreussite;
    @JoinColumn(name = "codeclasse", referencedColumnName = "codeclasse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classes classes;

    public Calculnotesdesclasses() {
    }

    public Calculnotesdesclasses(CalculnotesdesclassesPK calculnotesdesclassesPK) {
        this.calculnotesdesclassesPK = calculnotesdesclassesPK;
    }

    public Calculnotesdesclasses(String periode, String codeclasse) {
        this.calculnotesdesclassesPK = new CalculnotesdesclassesPK(periode, codeclasse);
    }

    public CalculnotesdesclassesPK getCalculnotesdesclassesPK() {
        return calculnotesdesclassesPK;
    }

    public void setCalculnotesdesclassesPK(CalculnotesdesclassesPK calculnotesdesclassesPK) {
        this.calculnotesdesclassesPK = calculnotesdesclassesPK;
    }

    public BigDecimal getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(BigDecimal moyenne) {
        this.moyenne = moyenne;
    }

    public BigDecimal getMoyennepremier() {
        return moyennepremier;
    }

    public void setMoyennepremier(BigDecimal moyennepremier) {
        this.moyennepremier = moyennepremier;
    }

    public BigDecimal getMoyennedernier() {
        return moyennedernier;
    }

    public void setMoyennedernier(BigDecimal moyennedernier) {
        this.moyennedernier = moyennedernier;
    }

    public String getTauxreussite() {
        return tauxreussite;
    }

    public void setTauxreussite(String tauxreussite) {
        this.tauxreussite = tauxreussite;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calculnotesdesclassesPK != null ? calculnotesdesclassesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calculnotesdesclasses)) {
            return false;
        }
        Calculnotesdesclasses other = (Calculnotesdesclasses) object;
        if ((this.calculnotesdesclassesPK == null && other.calculnotesdesclassesPK != null) || (this.calculnotesdesclassesPK != null && !this.calculnotesdesclassesPK.equals(other.calculnotesdesclassesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return calculnotesdesclassesPK + "";
    }

}
