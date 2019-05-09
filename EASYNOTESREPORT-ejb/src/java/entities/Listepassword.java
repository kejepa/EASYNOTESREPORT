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
@Table(name = "listepassword")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listepassword.findAll", query = "SELECT l FROM Listepassword l")
    //
    , @NamedQuery(name = "Listepassword.findPwdpersonne", query = "SELECT l FROM Listepassword l WHERE l.matriculepersonne = :matriculepersonne")
    , @NamedQuery(name = "Listepassword.findByPwd", query = "SELECT l FROM Listepassword l WHERE l.password = :password")
    , @NamedQuery(name = "Listepassword.findByPwd_Matiere", query = "SELECT l FROM Listepassword l WHERE l.password = :password AND l.specialite = :specialite")
    ,@NamedQuery(name = "Listepassword.findPersonnel", query = "SELECT l FROM Listepassword l WHERE l.password = :password")
//
    , @NamedQuery(name = "Listepassword.findByMatriculepersonne", query = "SELECT l FROM Listepassword l WHERE l.matriculepersonne = :matriculepersonne")
    , @NamedQuery(name = "Listepassword.findByPassword", query = "SELECT l FROM Listepassword l WHERE l.password = :password")
    , @NamedQuery(name = "Listepassword.findByNom", query = "SELECT l FROM Listepassword l WHERE l.nom = :nom")
    , @NamedQuery(name = "Listepassword.findByPrenom", query = "SELECT l FROM Listepassword l WHERE l.prenom = :prenom")
    , @NamedQuery(name = "Listepassword.findByGrade", query = "SELECT l FROM Listepassword l WHERE l.grade = :grade")
    , @NamedQuery(name = "Listepassword.findBySpecialite", query = "SELECT l FROM Listepassword l WHERE l.specialite = :specialite")})
public class Listepassword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculepersonne")
    private String matriculepersonne;
    @Size(max = 254)
    @Column(name = "password")
    private String password;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 254)
    @Column(name = "grade")
    private String grade;
    @Size(max = 254)
    @Column(name = "specialite")
    private String specialite;

    public Listepassword() {
    }

    public Listepassword(String matriculepersonne) {
        this.matriculepersonne = matriculepersonne;
    }

    public String getMatriculepersonne() {
        return matriculepersonne;
    }

    public void setMatriculepersonne(String matriculepersonne) {
        this.matriculepersonne = matriculepersonne;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculepersonne != null ? matriculepersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listepassword)) {
            return false;
        }
        Listepassword other = (Listepassword) object;
        if ((this.matriculepersonne == null && other.matriculepersonne != null) || (this.matriculepersonne != null && !this.matriculepersonne.equals(other.matriculepersonne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Listepassword[ matriculepersonne=" + matriculepersonne + " ]";
    }

}
