/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "etablissementinfos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etablissementinfos.findAll", query = "SELECT e FROM Etablissementinfos e")
    // 
    , @NamedQuery(name = "Etablissementinfos.findValeursInfos", query = "SELECT e FROM Etablissementinfos e WHERE e.id > :id ORDER BY e.id")
    , @NamedQuery(name = "Etablissementinfos.findBycleseq", query = "SELECT e FROM Etablissementinfos e WHERE e.cleinfos = :seq")
    , @NamedQuery(name = "Etablissementinfos.findBorneAV", query = "SELECT e.valeurinfos FROM Etablissementinfos e WHERE e.id = :id1")
    , @NamedQuery(name = "Etablissementinfos.findBorneBL", query = "SELECT e.valeurinfos FROM Etablissementinfos e WHERE e.id = :id2")
    //
    , @NamedQuery(name = "Etablissementinfos.findById", query = "SELECT e FROM Etablissementinfos e WHERE e.id = :id")
    , @NamedQuery(name = "Etablissementinfos.findByCleinfos", query = "SELECT e FROM Etablissementinfos e WHERE e.cleinfos = :cleinfos")
    , @NamedQuery(name = "Etablissementinfos.findByValeurinfos", query = "SELECT e FROM Etablissementinfos e WHERE e.valeurinfos = :valeurinfos")})
public class Etablissementinfos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "cleinfos")
    private String cleinfos;
    @Column(name = "valeurinfos")
    private BigDecimal valeurinfos;

    public Etablissementinfos() {
    }

    public Etablissementinfos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCleinfos() {
        return cleinfos;
    }

    public void setCleinfos(String cleinfos) {
        this.cleinfos = cleinfos;
    }

    public BigDecimal getValeurinfos() {
        return valeurinfos;
    }

    public void setValeurinfos(BigDecimal valeurinfos) {
        this.valeurinfos = valeurinfos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etablissementinfos)) {
            return false;
        }
        Etablissementinfos other = (Etablissementinfos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Etablissementinfos[ id=" + id + " ]";
    }

}
