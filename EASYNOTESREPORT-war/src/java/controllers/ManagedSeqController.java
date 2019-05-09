/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Classes;
import entities.Matieres;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import sessions.Notesdeselevesseq1FacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "managedSeqController")
@SessionScoped
public class ManagedSeqController implements Serializable {

    @EJB
    private Notesdeselevesseq1FacadeLocal noteseqFacde;
    public static String seq = "";
    public static Classes classe = null;
    public static Matieres matiere = null;
    public static String pwd;
    public static String enseignant = null;

    public ManagedSeqController() {
    }

    public Notesdeselevesseq1FacadeLocal getNoteseqFacde() {
        return noteseqFacde;
    }

    public void setNoteseqFacde(Notesdeselevesseq1FacadeLocal noteseqFacde) {
        this.noteseqFacde = noteseqFacde;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    public Matieres getMatiere() {
        return matiere;
    }

    public void setMatiere(Matieres matiere) {
        this.matiere = matiere;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        ManagedSeqController.pwd = pwd;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        ManagedSeqController.enseignant = enseignant;
    }

}
