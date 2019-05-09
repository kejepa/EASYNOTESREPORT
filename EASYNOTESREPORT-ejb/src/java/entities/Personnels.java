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
@Table(name = "personnels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnels.findAll", query = "SELECT p FROM Personnels p")
    //
//    , @NamedQuery(name = "Personnels.findMatNomPreGradSpecia", query = "SELECT p FROM Personnels p WHERE p.matricule = :matricule")
    // 
    , @NamedQuery(name = "Personnels.findByMatricule", query = "SELECT p FROM Personnels p WHERE p.matricule = :matricule")
    , @NamedQuery(name = "Personnels.findByNom", query = "SELECT p FROM Personnels p WHERE p.nom = :nom")
    , @NamedQuery(name = "Personnels.findByPrenom", query = "SELECT p FROM Personnels p WHERE p.prenom = :prenom")
    , @NamedQuery(name = "Personnels.findBySexe", query = "SELECT p FROM Personnels p WHERE p.sexe = :sexe")
    , @NamedQuery(name = "Personnels.findByDatenaiss", query = "SELECT p FROM Personnels p WHERE p.datenaiss = :datenaiss")
    , @NamedQuery(name = "Personnels.findByLieunaiss", query = "SELECT p FROM Personnels p WHERE p.lieunaiss = :lieunaiss")
    , @NamedQuery(name = "Personnels.findByAdresse", query = "SELECT p FROM Personnels p WHERE p.adresse = :adresse")
    , @NamedQuery(name = "Personnels.findBySpecialite", query = "SELECT p FROM Personnels p WHERE p.specialite = :specialite")
    , @NamedQuery(name = "Personnels.findByGrade", query = "SELECT p FROM Personnels p WHERE p.grade = :grade")
    , @NamedQuery(name = "Personnels.findByFonction", query = "SELECT p FROM Personnels p WHERE p.fonction = :fonction")
    , @NamedQuery(name = "Personnels.findByPhoto", query = "SELECT p FROM Personnels p WHERE p.photo = :photo")})
public class Personnels implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matricule")
    private String matricule;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 254)
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "datenaiss")
    @Temporal(TemporalType.DATE)
    private Date datenaiss;
    @Size(max = 254)
    @Column(name = "lieunaiss")
    private String lieunaiss;
    @Size(max = 254)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 254)
    @Column(name = "specialite")
    private String specialite;
    @Size(max = 254)
    @Column(name = "grade")
    private String grade;
    @Size(max = 254)
    @Column(name = "fonction")
    private String fonction;
    @Size(max = 254)
    @Column(name = "photo")
    private String photo;

    public Personnels() {
    }

    public Personnels(String matricule) {
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getLieunaiss() {
        return lieunaiss;
    }

    public void setLieunaiss(String lieunaiss) {
        this.lieunaiss = lieunaiss;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnels)) {
            return false;
        }
        Personnels other = (Personnels) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return matricule;
    }

}
