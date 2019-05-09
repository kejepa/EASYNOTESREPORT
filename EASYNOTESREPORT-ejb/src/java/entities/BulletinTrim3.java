/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

/**
 *
 * @author KENFACK JP
 */
@Entity
public class BulletinTrim3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeclasse;
    private String matriculeeleve;
    private String nomeleve;
    private String sexe;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datenaiss;
    private String lieunaiss;
    private String adresse;
    private String redoublant;
    private String photo;
    private String codematiere;
    private BigDecimal coefficient;
    private int groupe;
    private String periode;
    private BigDecimal moyenneclasse;
    private BigDecimal moyennepremier;
    private BigDecimal moyennedernier;
    private String tauxreussite;
    private String nomprof;
    private BigDecimal lanote;
    private BigDecimal moyenneeleve;
    private String rang_classe_eleve;
    private BigDecimal moyg1;
    private BigDecimal moyg2;
    private BigDecimal moyg3;
    private String rangg1;
    private String rangg2;
    private String rangg3;
    private BigDecimal notemin;
    private BigDecimal notemax;
    private BigDecimal moyenne_note;
    private String rang_eleve;
    private String profprincipal;
    private int absj;
    private int absn;
    private int consigne;
    private int exclusion;
    private String decision;
    @Size(max = 2147483647)
    @Column(name = "string_agg")
    private String string_agg;
    private BigDecimal notes2;
    private BigDecimal moyenneeleve1;
    private BigDecimal moyenneeleve2;
    private BigDecimal moyenneeleve3;
    private BigDecimal moyenneeleve4;
    private BigDecimal moyenneelevetrim1;
    private BigDecimal moyenneelevetrim2;
    private BigDecimal moyenneeleve5;
    private BigDecimal moyenneeleve6;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeclasse() {
        return codeclasse;
    }

    public void setCodeclasse(String codeclasse) {
        this.codeclasse = codeclasse;
    }

    public String getMatriculeeleve() {
        return matriculeeleve;
    }

    public void setMatriculeeleve(String matriculeeleve) {
        this.matriculeeleve = matriculeeleve;
    }

    public String getNomeleve() {
        return nomeleve;
    }

    public void setNomeleve(String nomeleve) {
        this.nomeleve = nomeleve;
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

    public String getRedoublant() {
        return redoublant;
    }

    public void setRedoublant(String redoublant) {
        this.redoublant = redoublant;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCodematiere() {
        return codematiere;
    }

    public void setCodematiere(String codematiere) {
        this.codematiere = codematiere;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public int getGroupe() {
        return groupe;
    }

    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public BigDecimal getMoyenneclasse() {
        return moyenneclasse;
    }

    public void setMoyenneclasse(BigDecimal moyenneclasse) {
        this.moyenneclasse = moyenneclasse;
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

    public String getNomprof() {
        return nomprof;
    }

    public void setNomprof(String nomprof) {
        this.nomprof = nomprof;
    }

    public BigDecimal getLanote() {
        return lanote;
    }

    public void setLanote(BigDecimal lanote) {
        this.lanote = lanote;
    }

    public BigDecimal getMoyenneeleve() {
        return moyenneeleve;
    }

    public void setMoyenneeleve(BigDecimal moyenneeleve) {
        this.moyenneeleve = moyenneeleve;
    }

    public String getRang_classe_eleve() {
        return rang_classe_eleve;
    }

    public void setRang_classe_eleve(String rang_classe_eleve) {
        this.rang_classe_eleve = rang_classe_eleve;
    }

    public BigDecimal getMoyg1() {
        return moyg1;
    }

    public void setMoyg1(BigDecimal moyg1) {
        this.moyg1 = moyg1;
    }

    public BigDecimal getMoyg2() {
        return moyg2;
    }

    public void setMoyg2(BigDecimal moyg2) {
        this.moyg2 = moyg2;
    }

    public BigDecimal getMoyg3() {
        return moyg3;
    }

    public void setMoyg3(BigDecimal moyg3) {
        this.moyg3 = moyg3;
    }

    public String getRangg1() {
        return rangg1;
    }

    public void setRangg1(String rangg1) {
        this.rangg1 = rangg1;
    }

    public String getRangg2() {
        return rangg2;
    }

    public void setRangg2(String rangg2) {
        this.rangg2 = rangg2;
    }

    public String getRangg3() {
        return rangg3;
    }

    public void setRangg3(String rangg3) {
        this.rangg3 = rangg3;
    }

    public BigDecimal getNotemin() {
        return notemin;
    }

    public void setNotemin(BigDecimal notemin) {
        this.notemin = notemin;
    }

    public BigDecimal getNotemax() {
        return notemax;
    }

    public void setNotemax(BigDecimal notemax) {
        this.notemax = notemax;
    }

    public BigDecimal getMoyenne_note() {
        return moyenne_note;
    }

    public void setMoyenne_note(BigDecimal moyenne_note) {
        this.moyenne_note = moyenne_note;
    }

    public String getRang_eleve() {
        return rang_eleve;
    }

    public void setRang_eleve(String rang_eleve) {
        this.rang_eleve = rang_eleve;
    }

    public String getProfprincipal() {
        return profprincipal;
    }

    public void setProfprincipal(String profprincipal) {
        this.profprincipal = profprincipal;
    }

    public int getAbsj() {
        return absj;
    }

    public void setAbsj(int absj) {
        this.absj = absj;
    }

    public int getAbsn() {
        return absn;
    }

    public void setAbsn(int absn) {
        this.absn = absn;
    }

    public int getConsigne() {
        return consigne;
    }

    public void setConsigne(int consigne) {
        this.consigne = consigne;
    }

    public int getExclusion() {
        return exclusion;
    }

    public void setExclusion(int exclusion) {
        this.exclusion = exclusion;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getString_agg() {
        return string_agg;
    }

    public void setString_agg(String string_agg) {
        this.string_agg = string_agg;
    }

    public BigDecimal getNotes2() {
        return notes2;
    }

    public void setNotes2(BigDecimal notes2) {
        this.notes2 = notes2;
    }

    public BigDecimal getMoyenneeleve1() {
        return moyenneeleve1;
    }

    public void setMoyenneeleve1(BigDecimal moyenneeleve1) {
        this.moyenneeleve1 = moyenneeleve1;
    }

    public BigDecimal getMoyenneeleve2() {
        return moyenneeleve2;
    }

    public void setMoyenneeleve2(BigDecimal moyenneeleve2) {
        this.moyenneeleve2 = moyenneeleve2;
    }

    public BigDecimal getMoyenneeleve3() {
        return moyenneeleve3;
    }

    public void setMoyenneeleve3(BigDecimal moyenneeleve3) {
        this.moyenneeleve3 = moyenneeleve3;
    }

    public BigDecimal getMoyenneeleve4() {
        return moyenneeleve4;
    }

    public void setMoyenneeleve4(BigDecimal moyenneeleve4) {
        this.moyenneeleve4 = moyenneeleve4;
    }

    public BigDecimal getMoyenneelevetrim1() {
        return moyenneelevetrim1;
    }

    public void setMoyenneelevetrim1(BigDecimal moyenneelevetrim1) {
        this.moyenneelevetrim1 = moyenneelevetrim1;
    }

    public BigDecimal getMoyenneeleve5() {
        return moyenneeleve5;
    }

    public void setMoyenneeleve5(BigDecimal moyenneeleve5) {
        this.moyenneeleve5 = moyenneeleve5;
    }

    public BigDecimal getMoyenneeleve6() {
        return moyenneeleve6;
    }

    public void setMoyenneeleve6(BigDecimal moyenneeleve6) {
        this.moyenneeleve6 = moyenneeleve6;
    }

    public BigDecimal getMoyenneelevetrim2() {
        return moyenneelevetrim2;
    }

    public void setMoyenneelevetrim2(BigDecimal moyenneelevetrim2) {
        this.moyenneelevetrim2 = moyenneelevetrim2;
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
        if (!(object instanceof BulletinTrim3)) {
            return false;
        }
        BulletinTrim3 other = (BulletinTrim3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + "";
    }

}
