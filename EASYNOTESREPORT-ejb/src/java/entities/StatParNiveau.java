/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author KENFACK JP
 */
@Entity
public class StatParNiveau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String classcorresp;
    private Long inscrits;
    private Long presents;
    private Long admis;
    private String typedeclasse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClasscorresp() {
        return classcorresp;
    }

    public void setClasscorresp(String classcorresp) {
        this.classcorresp = classcorresp;
    }

    public Long getInscrits() {
        return inscrits;
    }

    public void setInscrits(Long inscrits) {
        this.inscrits = inscrits;
    }

    public Long getPresents() {
        return presents;
    }

    public void setPresents(Long presents) {
        this.presents = presents;
    }

    public Long getAdmis() {
        return admis;
    }

    public void setAdmis(Long admis) {
        this.admis = admis;
    }

    public String getTypedeclasse() {
        return typedeclasse;
    }

    public void setTypedeclasse(String typedeclasse) {
        this.typedeclasse = typedeclasse;
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
        if (!(object instanceof StatParNiveau)) {
            return false;
        }
        StatParNiveau other = (StatParNiveau) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StatParNiveau[ id=" + id + " ]";
    }

}
