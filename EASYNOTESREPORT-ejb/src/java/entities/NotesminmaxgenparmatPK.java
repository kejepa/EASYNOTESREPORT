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
public class NotesminmaxgenparmatPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "periode")
    private String periode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codematiere")
    private String codematiere;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codeclasse")
    private String codeclasse;

    public NotesminmaxgenparmatPK() {
    }

    public NotesminmaxgenparmatPK(String periode, String codematiere, String codeclasse) {
        this.periode = periode;
        this.codematiere = codematiere;
        this.codeclasse = codeclasse;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getCodematiere() {
        return codematiere;
    }

    public void setCodematiere(String codematiere) {
        this.codematiere = codematiere;
    }

    public String getCodeclasse() {
        return codeclasse;
    }

    public void setCodeclasse(String codeclasse) {
        this.codeclasse = codeclasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periode != null ? periode.hashCode() : 0);
        hash += (codematiere != null ? codematiere.hashCode() : 0);
        hash += (codeclasse != null ? codeclasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotesminmaxgenparmatPK)) {
            return false;
        }
        NotesminmaxgenparmatPK other = (NotesminmaxgenparmatPK) object;
        if ((this.periode == null && other.periode != null) || (this.periode != null && !this.periode.equals(other.periode))) {
            return false;
        }
        if ((this.codematiere == null && other.codematiere != null) || (this.codematiere != null && !this.codematiere.equals(other.codematiere))) {
            return false;
        }
        if ((this.codeclasse == null && other.codeclasse != null) || (this.codeclasse != null && !this.codeclasse.equals(other.codeclasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NotesminmaxgenparmatPK[ periode=" + periode + ", codematiere=" + codematiere + ", codeclasse=" + codeclasse + " ]";
    }
    
}
