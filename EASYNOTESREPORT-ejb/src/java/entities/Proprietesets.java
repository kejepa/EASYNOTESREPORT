/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "proprietesets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proprietesets.findAll", query = "SELECT p FROM Proprietesets p")
    , @NamedQuery(name = "Proprietesets.findByNomdelets", query = "SELECT p FROM Proprietesets p WHERE p.nomdelets = :nomdelets")
    , @NamedQuery(name = "Proprietesets.findByNomdeletsan", query = "SELECT p FROM Proprietesets p WHERE p.nomdeletsan = :nomdeletsan")
    , @NamedQuery(name = "Proprietesets.findByDelegationregfr", query = "SELECT p FROM Proprietesets p WHERE p.delegationregfr = :delegationregfr")
    , @NamedQuery(name = "Proprietesets.findByDelegationregan", query = "SELECT p FROM Proprietesets p WHERE p.delegationregan = :delegationregan")
    , @NamedQuery(name = "Proprietesets.findByDelegationdepfr", query = "SELECT p FROM Proprietesets p WHERE p.delegationdepfr = :delegationdepfr")
    , @NamedQuery(name = "Proprietesets.findByDelegationdepan", query = "SELECT p FROM Proprietesets p WHERE p.delegationdepan = :delegationdepan")
    , @NamedQuery(name = "Proprietesets.findByTelephone", query = "SELECT p FROM Proprietesets p WHERE p.telephone = :telephone")
    , @NamedQuery(name = "Proprietesets.findByBoitepostale", query = "SELECT p FROM Proprietesets p WHERE p.boitepostale = :boitepostale")
    , @NamedQuery(name = "Proprietesets.findByEmail", query = "SELECT p FROM Proprietesets p WHERE p.email = :email")
    , @NamedQuery(name = "Proprietesets.findByCheminlogo", query = "SELECT p FROM Proprietesets p WHERE p.cheminlogo = :cheminlogo")})
public class Proprietesets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "nomdelets")
    private String nomdelets = "LYCEE ou CES ou CETIC DE ......";
    @Size(max = 254)
    @Column(name = "nomdeletsan")
    private String nomdeletsan;
    @Size(max = 254)
    @Column(name = "delegationregfr")
    private String delegationregfr;
    @Size(max = 254)
    @Column(name = "delegationregan")
    private String delegationregan;
    @Size(max = 254)
    @Column(name = "delegationdepfr")
    private String delegationdepfr;
    @Size(max = 254)
    @Column(name = "delegationdepan")
    private String delegationdepan;
    @Size(max = 254)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 254)
    @Column(name = "boitepostale")
    private String boitepostale;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Size(max = 254)
    @Column(name = "cheminlogo")
    private String cheminlogo;
    @Size(max = 254)
    @Column(name = "situation")
    private String situation;
    @Size(max = 254)
    @Column(name = "principal")
    private String principal;
    @Size(max = 254)
    @Column(name = "annee")
    private String annee;

    public Proprietesets() {
    }

    public Proprietesets(String nomdelets) {
        this.nomdelets = nomdelets;
    }

    public String getNomdelets() {
        return nomdelets;
    }

    public void setNomdelets(String nomdelets) {
        this.nomdelets = nomdelets;
    }

    public String getNomdeletsan() {
        return nomdeletsan;
    }

    public void setNomdeletsan(String nomdeletsan) {
        this.nomdeletsan = nomdeletsan;
    }

    public String getDelegationregfr() {
        return delegationregfr;
    }

    public void setDelegationregfr(String delegationregfr) {
        this.delegationregfr = delegationregfr;
    }

    public String getDelegationregan() {
        return delegationregan;
    }

    public void setDelegationregan(String delegationregan) {
        this.delegationregan = delegationregan;
    }

    public String getDelegationdepfr() {
        return delegationdepfr;
    }

    public void setDelegationdepfr(String delegationdepfr) {
        this.delegationdepfr = delegationdepfr;
    }

    public String getDelegationdepan() {
        return delegationdepan;
    }

    public void setDelegationdepan(String delegationdepan) {
        this.delegationdepan = delegationdepan;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBoitepostale() {
        return boitepostale;
    }

    public void setBoitepostale(String boitepostale) {
        this.boitepostale = boitepostale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheminlogo() {
        return cheminlogo;
    }

    public void setCheminlogo(String cheminlogo) {
        this.cheminlogo = cheminlogo;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomdelets != null ? nomdelets.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proprietesets)) {
            return false;
        }
        Proprietesets other = (Proprietesets) object;
        if ((this.nomdelets == null && other.nomdelets != null) || (this.nomdelets != null && !this.nomdelets.equals(other.nomdelets))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomdelets + " ";
    }

}
