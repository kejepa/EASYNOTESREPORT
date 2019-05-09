/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author KENFACK JP
 */
@Embeddable
public class DonnespedagogiquesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codematiere")
    private String codematiere;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "niveaux")
    private String niveaux;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "periode")
    private String periode;

    public DonnespedagogiquesPK() {
    }

    public DonnespedagogiquesPK(String codematiere, String niveaux, String periode) {
        this.codematiere = codematiere;
        this.niveaux = niveaux;
        this.periode = periode;
    }

    public String getCodematiere() {
        return codematiere;
    }

    public void setCodematiere(String codematiere) {
        this.codematiere = codematiere;
    }

    public String getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(String niveaux) {
        this.niveaux = niveaux;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codematiere != null ? codematiere.hashCode() : 0);
        hash += (niveaux != null ? niveaux.hashCode() : 0);
        hash += (periode != null ? periode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DonnespedagogiquesPK)) {
            return false;
        }
        DonnespedagogiquesPK other = (DonnespedagogiquesPK) object;
        if ((this.codematiere == null && other.codematiere != null) || (this.codematiere != null && !this.codematiere.equals(other.codematiere))) {
            return false;
        }
        if ((this.niveaux == null && other.niveaux != null) || (this.niveaux != null && !this.niveaux.equals(other.niveaux))) {
            return false;
        }
        if ((this.periode == null && other.periode != null) || (this.periode != null && !this.periode.equals(other.periode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DonnespedagogiquesPK[ codematiere=" + codematiere + ", niveaux=" + niveaux + ", periode=" + periode + " ]";
    }
    
}
