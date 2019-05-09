/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "specialite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specialite.findAll", query = "SELECT s FROM Specialite s")
    , @NamedQuery(name = "Specialite.findByCodespecialite", query = "SELECT s FROM Specialite s WHERE s.codespecialite = :codespecialite")
    , @NamedQuery(name = "Specialite.findByNomspecialite", query = "SELECT s FROM Specialite s WHERE s.nomspecialite = :nomspecialite")})
public class Specialite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codespecialite")
    private String codespecialite;
    @Size(max = 254)
    @Column(name = "nomspecialite")
    private String nomspecialite;
    @OneToMany(mappedBy = "codespecialite")
    private Collection<Matieres> matieresCollection;

    public Specialite() {
    }

    public Specialite(String codespecialite) {
        this.codespecialite = codespecialite;
    }

    public String getCodespecialite() {
        return codespecialite;
    }

    public void setCodespecialite(String codespecialite) {
        this.codespecialite = codespecialite;
    }

    public String getNomspecialite() {
        return nomspecialite;
    }

    public void setNomspecialite(String nomspecialite) {
        this.nomspecialite = nomspecialite;
    }

    @XmlTransient
    public Collection<Matieres> getMatieresCollection() {
        return matieresCollection;
    }

    public void setMatieresCollection(Collection<Matieres> matieresCollection) {
        this.matieresCollection = matieresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codespecialite != null ? codespecialite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specialite)) {
            return false;
        }
        Specialite other = (Specialite) object;
        if ((this.codespecialite == null && other.codespecialite != null) || (this.codespecialite != null && !this.codespecialite.equals(other.codespecialite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codespecialite ; 
    }
    
}
