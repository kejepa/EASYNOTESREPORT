/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KENFACK JP
 */
@Entity
@Table(name = "operations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operations.findAll", query = "SELECT o FROM Operations o")
    , @NamedQuery(name = "Operations.nextId", query = "SELECT MAX(o.idoperations) FROM Operations o")
    , @NamedQuery(name = "Operations.findByIdoperations", query = "SELECT o FROM Operations o WHERE o.idoperations = :idoperations")
    , @NamedQuery(name = "Operations.findByTypeoperation", query = "SELECT o FROM Operations o WHERE o.typeoperation = :typeoperation")
    , @NamedQuery(name = "Operations.findByDateoperation", query = "SELECT o FROM Operations o WHERE o.dateoperation = :dateoperation")
    , @NamedQuery(name = "Operations.findByDescription", query = "SELECT o FROM Operations o WHERE o.description = :description")})
public class Operations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idoperations")
    private Integer idoperations;
    @Size(max = 254)
    @Column(name = "typeoperation")
    private String typeoperation;
    @Column(name = "dateoperation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateoperation;
    @Size(max = 254)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne
    private Utilisateurs login; 

    public Operations() {
    }

    public Operations(Integer idoperations) {
        this.idoperations = idoperations;
    }

    public Integer getIdoperations() {
        return idoperations;
    }

    public void setIdoperations(Integer idoperations) {
        this.idoperations = idoperations;
    }

    public String getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(String typeoperation) {
        this.typeoperation = typeoperation;
    }

    public Date getDateoperation() {
        return dateoperation;
    }

    public void setDateoperation(Date dateoperation) {
        this.dateoperation = dateoperation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utilisateurs getLogin() {
        return login;
    }

    public void setLogin(Utilisateurs login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoperations != null ? idoperations.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operations)) {
            return false;
        }
        Operations other = (Operations) object;
        if ((this.idoperations == null && other.idoperations != null) || (this.idoperations != null && !this.idoperations.equals(other.idoperations))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Operations[ idoperations=" + idoperations + " ]";
    }

}
