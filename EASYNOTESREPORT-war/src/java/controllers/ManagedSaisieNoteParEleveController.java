/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Annmoyennesrangsdeseleves;
import entities.Listedeseleves;
import entities.Matieres;
import entities.Notesdeselevesann;
import entities.NotesdeselevesannPK;
import entities.Notesdeselevesseq1;
import entities.Notesdeselevesseq1PK;
import entities.Notesdeselevesseq2;
import entities.Notesdeselevesseq2PK;
import entities.Notesdeselevesseq3;
import entities.Notesdeselevesseq3PK;
import entities.Notesdeselevesseq4;
import entities.Notesdeselevesseq4PK;
import entities.Notesdeselevesseq5;
import entities.Notesdeselevesseq5PK;
import entities.Notesdeselevesseq6;
import entities.Notesdeselevesseq6PK;
import entities.Notesdeselevestrim1;
import entities.Notesdeselevestrim1PK;
import entities.Notesdeselevestrim2;
import entities.Notesdeselevestrim2PK;
import entities.Notesdeselevestrim3;
import entities.Notesdeselevestrim3PK;
import entities.Operations;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import sessions.AnnmoyennesrangsdeselevesFacadeLocal;
import sessions.EtablissementinfosFacadeLocal;
import sessions.ListedeselevesFacadeLocal;
import sessions.MatieresFacadeLocal;
import sessions.NotesdeselevesannFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;
import sessions.Notesdeselevesseq2FacadeLocal;
import sessions.Notesdeselevesseq3FacadeLocal;
import sessions.Notesdeselevesseq4FacadeLocal;
import sessions.Notesdeselevesseq5FacadeLocal;
import sessions.Notesdeselevesseq6FacadeLocal;
import sessions.Notesdeselevestrim1FacadeLocal;
import sessions.Notesdeselevestrim2FacadeLocal;
import sessions.Notesdeselevestrim3FacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "managedSaisieNoteParEleveController")
@SessionScoped
public class ManagedSaisieNoteParEleveController implements Serializable {

    @EJB
    private EtablissementinfosFacadeLocal etsFavcade;
    @EJB
    private AnnmoyennesrangsdeselevesFacadeLocal anmoyeleveFavcade;
    @EJB
    private ListedeselevesFacadeLocal eleveFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal noteSeq1Facade;
    @EJB
    private Notesdeselevesseq2FacadeLocal noteSeq2Facade;
    @EJB
    private Notesdeselevesseq3FacadeLocal noteSeq3Facade;
    @EJB
    private Notesdeselevesseq4FacadeLocal noteSeq4Facade;
    @EJB
    private Notesdeselevesseq5FacadeLocal noteSeq5Facade;
    @EJB
    private Notesdeselevesseq6FacadeLocal noteSeq6Facade;
    @EJB
    private Notesdeselevestrim1FacadeLocal notetrim1Facade;
    @EJB
    private Notesdeselevestrim2FacadeLocal notetrim2Facade;
    @EJB
    private Notesdeselevestrim3FacadeLocal notetrim3Facade;
    @EJB
    private NotesdeselevesannFacadeLocal noteannFacade;
    @EJB
    private MatieresFacadeLocal matiereFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Listedeseleves> eleveList = new ArrayList<>();
    private List<Notesdeselevesseq1> eleveListSeq1 = new ArrayList<>();
    private List<Notesdeselevesseq2> eleveListSeq2 = new ArrayList<>();
    private List<Notesdeselevesseq3> eleveListSeq3 = new ArrayList<>();
    private List<Notesdeselevesseq4> eleveListSeq4 = new ArrayList<>();
    private List<Notesdeselevesseq5> eleveListSeq5 = new ArrayList<>();
    private List<Notesdeselevesseq6> eleveListSeq6 = new ArrayList<>();
    private List<Annmoyennesrangsdeseleves> anListAdmis = new ArrayList<>();
    private Notesdeselevesseq1 matiere_eleveseq1;
    private Notesdeselevesseq2 matiere_eleveseq2;
    private Notesdeselevesseq3 matiere_eleveseq3;
    private Notesdeselevesseq4 matiere_eleveseq4;
    private Notesdeselevesseq5 matiere_eleveseq5;
    private Notesdeselevesseq6 matiere_eleveseq6;
    private Notesdeselevestrim1 matiere_elevetrim1;
    private Notesdeselevestrim2 matiere_elevetrim2;
    private Notesdeselevestrim3 matiere_elevetrim3;
    private Notesdeselevesann matiere_eleveann;
    private Annmoyennesrangsdeseleves anMoyEleves;

    private Listedeseleves eleve;
    private String classe = null;
    private BigDecimal moyenne = BigDecimal.TEN;
    private String seq;
    private boolean seq1;
    private boolean seq2;
    private boolean seq3;
    private boolean seq4;
    private boolean seq5;
    private boolean seq6;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;

    public ManagedSaisieNoteParEleveController() {

    }

    public String saisiepareleve() {
        pouraccueil();
        eleveList.clear();
        eleveList.addAll(eleveFacade.liste_des_eleves_par_classe(classe));
        eleve = new Listedeseleves();
        return "managedsaisiepareleve.xhtml?faces-redirect=true";
    }

    public String vérifiersaisiedesnotes() {
        pouraccueil();
        eleveList.clear();
        eleveList.addAll(eleveFacade.liste_des_eleves_par_classe(classe));
        eleve = new Listedeseleves();
        return "managedverifienoteseleve.xhtml?faces-redirect=true";
    }

    //pour la liste des élèves admis en classe sup
    public String liteelevesAdmisEnClasseSup() {
        anListAdmis.clear();
        anListAdmis.addAll(anmoyeleveFavcade.liste_des_eleves_admis(classe, moyenne));
        anMoyEleves = new Annmoyennesrangsdeseleves();
        return "eleveAdmis.xhtml?faces-redirect=true";
    }

//pour le menu liste des élèves par classe du menu accueil
    public String liteelevesparclasse() {
        eleveList.clear();
        eleveList.addAll(eleveFacade.liste_des_eleves_par_classe(classe));
        eleve = new Listedeseleves();
        return "eleveParClasse.xhtml?faces-redirect=true";
    }

    public void pouraccueil() {
        BigDecimal valeurBigDecimal = etsFavcade.acti_deative_seq();
        String valeur = valeurBigDecimal.toString();
        s1 = valeur.substring(0, 1);
        s2 = valeur.substring(1, 2);
        s3 = valeur.substring(2, 3);
        s4 = valeur.substring(3, 4);
        s5 = valeur.substring(4, 5);
        s6 = valeur.substring(5, 6);
        switch (s1) {
            case "1":
                seq1 = false;
                break;
            case "2":
                seq1 = true;
        }
        switch (s2) {
            case "1":
                seq2 = false;
                break;
            case "2":
                seq2 = true;
        }
        switch (s3) {
            case "1":
                seq3 = false;
                break;
            case "2":
                seq3 = true;
        }
        switch (s4) {
            case "1":
                seq4 = false;
                break;
            case "2":
                seq4 = true;
        }
        switch (s5) {
            case "1":
                seq5 = false;
                break;
            case "2":
                seq5 = true;
        }
        switch (s6) {
            case "1":
                seq6 = false;
                break;
            case "2":
                seq6 = true;
        }
    }

    //methode pour verifier les notes déja saisie en fonction de la séquence choisie
    public String verifieNotedesEleve() {
        switch (seq) {
            case "1":
                return verifiernoteseleveseq1();
            case "2":
                return verifiernoteseleveseq2();

            case "3":
                return verifiernoteseleveseq3();

            case "4":
                return verifiernoteseleveseq4();

            case "5":
                return verifiernoteseleveseq5();

            case "6": 
                return verifiernoteseleveseq6();

        }
        return "";
    }

    //methode pour la vue en fonction de la séquence choisie
    public String saisieNoteParEleve() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = df.format(date);
        try {
            Date dateOperation = df.parse(dateString);
            Operations mouchard = new Operations();
            mouchard.setIdoperations(mouchardFacade.nextId());
            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
            mouchard.setTypeoperation("Saisie Des Notes par élève");
            mouchard.setDateoperation(dateOperation);
            mouchard.setDescription("Saisie Des Notes par élève de SEQ" + seq + ", " + classe + ",élève " + eleve.getNom() + " " + eleve.getPrenom() + "(" + eleve.getMatriculeeleve() + ")");
            mouchardFacade.create(mouchard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (seq) {
            case "1":
                return saisiepareleveseq1();
            case "2":
                return saisiepareleveseq2();

            case "3":
                return saisiepareleveseq3();

            case "4":
                return saisiepareleveseq4();

            case "5":
                return saisiepareleveseq5();

            case "6":
                return saisiepareleveseq6();

        }
        return "";
    }

    //verifier notes eleve seq1
    public String verifiernoteseleveseq1() {
        if ("1".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq1Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq1Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq1 = new Notesdeselevesseq1();
                            Notesdeselevesseq1PK notseq1 = new Notesdeselevesseq1PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq1.setNotesdeselevesseq1PK(notseq1);
                            matiere_eleveseq1.setMatieres(m);
                            noteSeq1Facade.create(matiere_eleveseq1);
                        }
                        if (!notetrim1Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim1 = new Notesdeselevestrim1();
                            Notesdeselevestrim1PK notetrim1 = new Notesdeselevestrim1PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim1.setNotesdeselevestrim1PK(notetrim1);
                            matiere_elevetrim1.setMatieres(m);
                            notetrim1Facade.create(matiere_elevetrim1);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq1.clear();
                    eleveListSeq1.addAll(noteSeq1Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
//                    matiere_eleveseq1 = new Notesdeselevesseq1();
                }
                return "verifienotepareleveseq1.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
//verifier notes eleve seq2

    public String verifiernoteseleveseq2() {
        if ("2".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq2Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq2Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq2 = new Notesdeselevesseq2();
                            Notesdeselevesseq2PK notseq2 = new Notesdeselevesseq2PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq2.setNotesdeselevesseq2PK(notseq2);
                            matiere_eleveseq2.setMatieres(m);
                            noteSeq2Facade.create(matiere_eleveseq2);
                        }
                        if (!notetrim1Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim1 = new Notesdeselevestrim1();
                            Notesdeselevestrim1PK notetrim1 = new Notesdeselevestrim1PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim1.setNotesdeselevestrim1PK(notetrim1);
                            matiere_elevetrim1.setMatieres(m);
                            notetrim1Facade.create(matiere_elevetrim1);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq2.clear();
                    eleveListSeq2.addAll(noteSeq2Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq2 = new Notesdeselevesseq2();
                }
                return "verifienotepareleveseq2.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    //verifier notes eleve seq3
    public String verifiernoteseleveseq3() {
        if ("3".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq3Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq3Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq3 = new Notesdeselevesseq3();
                            Notesdeselevesseq3PK notseq3 = new Notesdeselevesseq3PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq3.setNotesdeselevesseq3PK(notseq3);
                            matiere_eleveseq3.setMatieres(m);
                            noteSeq3Facade.create(matiere_eleveseq3);
                        }
                        if (!notetrim2Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim2 = new Notesdeselevestrim2();
                            Notesdeselevestrim2PK notetrim2 = new Notesdeselevestrim2PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim2.setNotesdeselevestrim2PK(notetrim2);
                            matiere_elevetrim2.setMatieres(m);
                            notetrim2Facade.create(matiere_elevetrim2);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq3.clear();
                    eleveListSeq3.addAll(noteSeq3Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq3 = new Notesdeselevesseq3();
                }
                return "verifienotepareleveseq3.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    //verifier notes eleve seq4
    public String verifiernoteseleveseq4() {
        if ("4".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq4Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq4Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq4 = new Notesdeselevesseq4();
                            Notesdeselevesseq4PK notseq4 = new Notesdeselevesseq4PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq4.setNotesdeselevesseq4PK(notseq4);
                            matiere_eleveseq4.setMatieres(m);
                            noteSeq4Facade.create(matiere_eleveseq4);
                        }
                        if (!notetrim2Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim2 = new Notesdeselevestrim2();
                            Notesdeselevestrim2PK notetrim2 = new Notesdeselevestrim2PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim2.setNotesdeselevestrim2PK(notetrim2);
                            matiere_elevetrim2.setMatieres(m);
                            notetrim2Facade.create(matiere_elevetrim2);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq4.clear();
                    eleveListSeq4.addAll(noteSeq4Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq4 = new Notesdeselevesseq4();
                }
                return "verifienotepareleveseq4.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    //verifier notes eleve seq5
    public String verifiernoteseleveseq5() {
        if ("5".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq5Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq5Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq5 = new Notesdeselevesseq5();
                            Notesdeselevesseq5PK notseq5 = new Notesdeselevesseq5PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq5.setNotesdeselevesseq5PK(notseq5);
                            matiere_eleveseq5.setMatieres(m);
                            noteSeq5Facade.create(matiere_eleveseq5);
                        }
                        if (!notetrim3Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim3 = new Notesdeselevestrim3();
                            Notesdeselevestrim3PK notetrim3 = new Notesdeselevestrim3PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim3.setNotesdeselevestrim3PK(notetrim3);
                            matiere_elevetrim3.setMatieres(m);
                            notetrim3Facade.create(matiere_elevetrim3);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq5.clear();
                    eleveListSeq5.addAll(noteSeq5Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq5 = new Notesdeselevesseq5();
                }
                return "verifienotepareleveseq5.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    //verifier notes eleve seq6
    public String verifiernoteseleveseq6() {
        if ("6".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq6Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq6Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq6 = new Notesdeselevesseq6();
                            Notesdeselevesseq6PK notseq6 = new Notesdeselevesseq6PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq6.setNotesdeselevesseq6PK(notseq6);
                            matiere_eleveseq6.setMatieres(m);
                            noteSeq6Facade.create(matiere_eleveseq6);
                        }
                        if (!notetrim3Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim3 = new Notesdeselevestrim3();
                            Notesdeselevestrim3PK notetrim3 = new Notesdeselevestrim3PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim3.setNotesdeselevestrim3PK(notetrim3);
                            matiere_elevetrim3.setMatieres(m);
                            notetrim3Facade.create(matiere_elevetrim3);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq6.clear();
                    eleveListSeq6.addAll(noteSeq6Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq6 = new Notesdeselevesseq6();
                }
                return "verifienotepareleveseq6.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
    //saisie par eleve seq1

    public String saisiepareleveseq1() {
        if ("1".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq1Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq1Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq1 = new Notesdeselevesseq1();
                            Notesdeselevesseq1PK notseq1 = new Notesdeselevesseq1PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq1.setNotesdeselevesseq1PK(notseq1);
                            matiere_eleveseq1.setMatieres(m);
                            noteSeq1Facade.create(matiere_eleveseq1);
                        }
                        if (!notetrim1Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim1 = new Notesdeselevestrim1();
                            Notesdeselevestrim1PK notetrim1 = new Notesdeselevestrim1PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim1.setNotesdeselevestrim1PK(notetrim1);
                            matiere_elevetrim1.setMatieres(m);
                            notetrim1Facade.create(matiere_elevetrim1);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq1.clear();
                    eleveListSeq1.addAll(noteSeq1Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
//                    matiere_eleveseq1 = new Notesdeselevesseq1();
                }
                return "saisienotepareleveseq1.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void onCellEdit1(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq1 uneeleve = (Notesdeselevesseq1) table.getRowData();
            noteSeq1Facade.edit(uneeleve);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq1 uneeleve = (Notesdeselevesseq1) table.getRowData();
            matiere_eleveseq1 = new Notesdeselevesseq1();
            Notesdeselevesseq1PK notseq1 = new Notesdeselevesseq1PK(uneeleve.getListedeseleves().getMatriculeeleve(), uneeleve.getMatieres().toString());
            matiere_eleveseq1.setNotesdeselevesseq1PK(notseq1);
            matiere_eleveseq1.setListedeseleves(uneeleve.getListedeseleves());
            matiere_eleveseq1.setMatieres(uneeleve.getMatieres());
            matiere_eleveseq1.setLanote(null);
            noteSeq1Facade.edit(matiere_eleveseq1);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
//saisie par eleve seq2

    public String saisiepareleveseq2() {
        if ("2".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq2Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq2Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq2 = new Notesdeselevesseq2();
                            Notesdeselevesseq2PK notseq2 = new Notesdeselevesseq2PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq2.setNotesdeselevesseq2PK(notseq2);
                            matiere_eleveseq2.setMatieres(m);
                            noteSeq2Facade.create(matiere_eleveseq2);
                        }
                        if (!notetrim1Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim1 = new Notesdeselevestrim1();
                            Notesdeselevestrim1PK notetrim1 = new Notesdeselevestrim1PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim1.setNotesdeselevestrim1PK(notetrim1);
                            matiere_elevetrim1.setMatieres(m);
                            notetrim1Facade.create(matiere_elevetrim1);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq2.clear();
                    eleveListSeq2.addAll(noteSeq2Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq2 = new Notesdeselevesseq2();
                }
                return "saisienotepareleveseq2.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void onCellEdit2(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq2 uneeleve = (Notesdeselevesseq2) table.getRowData();
            noteSeq2Facade.edit(uneeleve);
            BigDecimal note1ereseq = noteSeq1Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            BigDecimal note2eseq = noteSeq2Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            Notesdeselevestrim1 elevetrim1 = notetrim1Facade.upadateNoteTrim1Eleves(uneeleve.getNotesdeselevesseq2PK().getCodematiere(), uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve());
            matiere_elevetrim1 = new Notesdeselevestrim1();
            Notesdeselevestrim1PK nottrim1 = new Notesdeselevestrim1PK(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            matiere_elevetrim1.setListedeseleves(elevetrim1.getListedeseleves());
            matiere_elevetrim1.setMatieres(elevetrim1.getMatieres());
            matiere_elevetrim1.setNotesdeselevestrim1PK(nottrim1);
            if (note1ereseq != null && note2eseq != null) {
                matiere_elevetrim1.setLanote((note1ereseq.add(note2eseq)).divide(BigDecimal.valueOf(2)));
            } else if (note1ereseq == null && note2eseq == null) {
                matiere_elevetrim1.setLanote(null);
            } else if (note1ereseq == null && note2eseq != null) {
                matiere_elevetrim1.setLanote((note2eseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            } else if (note1ereseq != null && note2eseq == null) {
                matiere_elevetrim1.setLanote((note1ereseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            }
            notetrim1Facade.edit(matiere_elevetrim1);
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq2 uneeleve = (Notesdeselevesseq2) table.getRowData();
            matiere_eleveseq2 = new Notesdeselevesseq2();
            Notesdeselevesseq2PK notseq2 = new Notesdeselevesseq2PK(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            matiere_eleveseq2.setNotesdeselevesseq2PK(notseq2);
            matiere_eleveseq2.setListedeseleves(uneeleve.getListedeseleves());
            matiere_eleveseq2.setMatieres(uneeleve.getMatieres());
            matiere_eleveseq2.setLanote(null);
            noteSeq2Facade.edit(matiere_eleveseq2);

            BigDecimal note1ereseq = noteSeq1Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            BigDecimal note2eseq = noteSeq2Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            Notesdeselevestrim1 elevetrim1 = notetrim1Facade.upadateNoteTrim1Eleves(uneeleve.getNotesdeselevesseq2PK().getCodematiere(), uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve());
            matiere_elevetrim1 = new Notesdeselevestrim1();
            Notesdeselevestrim1PK nottrim1 = new Notesdeselevestrim1PK(elevetrim1.getListedeseleves().getMatriculeeleve(), elevetrim1.getMatieres().toString());
            matiere_elevetrim1.setListedeseleves(elevetrim1.getListedeseleves());
            matiere_elevetrim1.setMatieres(elevetrim1.getMatieres());
            matiere_elevetrim1.setNotesdeselevestrim1PK(nottrim1);
            if (note1ereseq == null && note2eseq == null) {
                matiere_elevetrim1.setLanote(null);
            } else if (note1ereseq != null && note2eseq == null) {
                matiere_elevetrim1.setLanote((note1ereseq.add(BigDecimal.ZERO)).divide(BigDecimal.ZERO));
            }
            notetrim1Facade.edit(matiere_elevetrim1);
        } else {

        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //saisie par eleve seq3
    public String saisiepareleveseq3() {
        if ("3".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq3Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq3Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq3 = new Notesdeselevesseq3();
                            Notesdeselevesseq3PK notseq3 = new Notesdeselevesseq3PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq3.setNotesdeselevesseq3PK(notseq3);
                            matiere_eleveseq3.setMatieres(m);
                            noteSeq3Facade.create(matiere_eleveseq3);
                        }
                        if (!notetrim2Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim2 = new Notesdeselevestrim2();
                            Notesdeselevestrim2PK notetrim2 = new Notesdeselevestrim2PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim2.setNotesdeselevestrim2PK(notetrim2);
                            matiere_elevetrim2.setMatieres(m);
                            notetrim2Facade.create(matiere_elevetrim2);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq3.clear();
                    eleveListSeq3.addAll(noteSeq3Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq3 = new Notesdeselevesseq3();
                }
                return "saisienotepareleveseq3.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void onCellEdit3(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq3 uneeleve = (Notesdeselevesseq3) table.getRowData();
            noteSeq3Facade.edit(uneeleve);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq3 uneeleve = (Notesdeselevesseq3) table.getRowData();
            matiere_eleveseq3 = new Notesdeselevesseq3();
            Notesdeselevesseq3PK notseq3 = new Notesdeselevesseq3PK(uneeleve.getListedeseleves().getMatriculeeleve(), uneeleve.getMatieres().toString());
            matiere_eleveseq3.setNotesdeselevesseq3PK(notseq3);
            matiere_eleveseq3.setListedeseleves(uneeleve.getListedeseleves());
            matiere_eleveseq3.setMatieres(uneeleve.getMatieres());
            matiere_eleveseq3.setLanote(null);
            noteSeq3Facade.edit(matiere_eleveseq3);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //saisie par eleve seq4
    public String saisiepareleveseq4() {
        if ("4".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq4Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq4Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq4 = new Notesdeselevesseq4();
                            Notesdeselevesseq4PK notseq4 = new Notesdeselevesseq4PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq4.setNotesdeselevesseq4PK(notseq4);
                            matiere_eleveseq4.setMatieres(m);
                            noteSeq4Facade.create(matiere_eleveseq4);
                        }
                        if (!notetrim2Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim2 = new Notesdeselevestrim2();
                            Notesdeselevestrim2PK notetrim2 = new Notesdeselevestrim2PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim2.setNotesdeselevestrim2PK(notetrim2);
                            matiere_elevetrim2.setMatieres(m);
                            notetrim2Facade.create(matiere_elevetrim2);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq4.clear();
                    eleveListSeq4.addAll(noteSeq4Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq4 = new Notesdeselevesseq4();
                }
                return "saisienotepareleveseq4.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void onCellEdit4(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq4 uneeleve = (Notesdeselevesseq4) table.getRowData();
            noteSeq4Facade.edit(uneeleve);
            BigDecimal note3ereseq = noteSeq3Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            BigDecimal note4eseq = noteSeq4Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            Notesdeselevestrim2 elevetrim2 = notetrim2Facade.upadateNoteTrim2Eleves(uneeleve.getNotesdeselevesseq4PK().getCodematiere(), uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve());
            matiere_elevetrim2 = new Notesdeselevestrim2();
            Notesdeselevestrim2PK nottrim2 = new Notesdeselevestrim2PK(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            matiere_elevetrim2.setListedeseleves(elevetrim2.getListedeseleves());
            matiere_elevetrim2.setMatieres(elevetrim2.getMatieres());
            matiere_elevetrim2.setNotesdeselevestrim2PK(nottrim2);
            if (note3ereseq != null && note4eseq != null) {
                matiere_elevetrim2.setLanote((note3ereseq.add(note4eseq)).divide(BigDecimal.valueOf(2)));
            } else if (note3ereseq == null && note4eseq == null) {
                matiere_elevetrim2.setLanote(null);
            } else if (note3ereseq == null && note4eseq != null) {
                matiere_elevetrim2.setLanote((note4eseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            } else if (note3ereseq != null && note4eseq == null) {
                matiere_elevetrim2.setLanote((note3ereseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            }
            notetrim2Facade.edit(matiere_elevetrim2);
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq4 uneeleve = (Notesdeselevesseq4) table.getRowData();
            matiere_eleveseq4 = new Notesdeselevesseq4();
            Notesdeselevesseq4PK notseq4 = new Notesdeselevesseq4PK(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            matiere_eleveseq4.setNotesdeselevesseq4PK(notseq4);
            matiere_eleveseq4.setListedeseleves(uneeleve.getListedeseleves());
            matiere_eleveseq4.setMatieres(uneeleve.getMatieres());
            matiere_eleveseq4.setLanote(null);
            noteSeq4Facade.edit(matiere_eleveseq4);

            BigDecimal note3ereseq = noteSeq3Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            BigDecimal note4eseq = noteSeq4Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            Notesdeselevestrim2 elevetrim2 = notetrim2Facade.upadateNoteTrim2Eleves(uneeleve.getNotesdeselevesseq4PK().getCodematiere(), uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve());
            matiere_elevetrim2 = new Notesdeselevestrim2();
            Notesdeselevestrim2PK nottrim2 = new Notesdeselevestrim2PK(elevetrim2.getListedeseleves().getMatriculeeleve(), elevetrim2.getMatieres().toString());
            matiere_elevetrim2.setListedeseleves(elevetrim2.getListedeseleves());
            matiere_elevetrim2.setMatieres(elevetrim2.getMatieres());
            matiere_elevetrim2.setNotesdeselevestrim2PK(nottrim2);
            if (note3ereseq == null && note4eseq == null) {
                matiere_elevetrim2.setLanote(null);
            } else if (note3ereseq != null && note4eseq == null) {
                matiere_elevetrim2.setLanote((note3ereseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            }
            notetrim2Facade.edit(matiere_elevetrim2);
        } else {

        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //saisie par eleve seq5
    public String saisiepareleveseq5() {
        if ("5".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq5Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq5Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq5 = new Notesdeselevesseq5();
                            Notesdeselevesseq5PK notseq5 = new Notesdeselevesseq5PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq5.setNotesdeselevesseq5PK(notseq5);
                            matiere_eleveseq5.setMatieres(m);
                            noteSeq5Facade.create(matiere_eleveseq5);
                        }
                        if (!notetrim3Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim3 = new Notesdeselevestrim3();
                            Notesdeselevestrim3PK notetrim3 = new Notesdeselevestrim3PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim3.setNotesdeselevestrim3PK(notetrim3);
                            matiere_elevetrim3.setMatieres(m);
                            notetrim3Facade.create(matiere_elevetrim3);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq5.clear();
                    eleveListSeq5.addAll(noteSeq5Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq5 = new Notesdeselevesseq5();
                }
                return "saisienotepareleveseq5.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void onCellEdit5(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq5 uneeleve = (Notesdeselevesseq5) table.getRowData();
            noteSeq5Facade.edit(uneeleve);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq5 uneeleve = (Notesdeselevesseq5) table.getRowData();
            matiere_eleveseq5 = new Notesdeselevesseq5();
            Notesdeselevesseq5PK notseq5 = new Notesdeselevesseq5PK(uneeleve.getListedeseleves().getMatriculeeleve(), uneeleve.getMatieres().toString());
            matiere_eleveseq5.setNotesdeselevesseq5PK(notseq5);
            matiere_eleveseq5.setListedeseleves(uneeleve.getListedeseleves());
            matiere_eleveseq5.setMatieres(uneeleve.getMatieres());
            matiere_eleveseq5.setLanote(null);
            noteSeq5Facade.edit(matiere_eleveseq5);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //saisie par eleve seq6
    public String saisiepareleveseq6() {
        if ("6".equals(seq)) {
            if (seq != null && classe != null) {
                if ((noteSeq6Facade.listedesMatieresParClasseDeEleve(classe)) != null) {
                    List<Matieres> matiereList = matiereFacade.listeMatieresParClasse(classe);
                    Iterator i = matiereList.iterator();
                    Matieres m;
                    while (i.hasNext()) {
                        m = (Matieres) i.next();
                        if (!noteSeq6Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveseq6 = new Notesdeselevesseq6();
                            Notesdeselevesseq6PK notseq6 = new Notesdeselevesseq6PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveseq6.setNotesdeselevesseq6PK(notseq6);
                            matiere_eleveseq6.setMatieres(m);
                            noteSeq6Facade.create(matiere_eleveseq6);
                        }
                        if (!notetrim3Facade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_elevetrim3 = new Notesdeselevestrim3();
                            Notesdeselevestrim3PK notetrim3 = new Notesdeselevestrim3PK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_elevetrim3.setNotesdeselevestrim3PK(notetrim3);
                            matiere_elevetrim3.setMatieres(m);
                            notetrim3Facade.create(matiere_elevetrim3);
                        }
                        if (!noteannFacade.existEleve_et_sa_matiere(eleve.getMatriculeeleve(), m.getCodematiere())) {
                            matiere_eleveann = new Notesdeselevesann();
                            NotesdeselevesannPK notann = new NotesdeselevesannPK(eleve.getMatriculeeleve(), m.getCodematiere());
                            matiere_eleveann.setNotesdeselevesannPK(notann);
                            matiere_eleveann.setMatieres(m);
                            noteannFacade.create(matiere_eleveann);
                        }
                    }
                    eleveListSeq6.clear();
                    eleveListSeq6.addAll(noteSeq6Facade.Eleve_et_sa_matiere(eleve.getMatriculeeleve()));
                    matiere_eleveseq6 = new Notesdeselevesseq6();
                }
                return "saisienotepareleveseq6.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void onCellEdit6(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq6 uneeleve = (Notesdeselevesseq6) table.getRowData();
            noteSeq6Facade.edit(uneeleve);
            BigDecimal note5ereseq = noteSeq5Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note6eseq = noteSeq6Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            Notesdeselevestrim3 elevetrim3 = notetrim3Facade.upadateNoteTrim3Eleves(uneeleve.getNotesdeselevesseq6PK().getCodematiere(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            matiere_elevetrim3 = new Notesdeselevestrim3();
            Notesdeselevestrim3PK nottrim3 = new Notesdeselevestrim3PK(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            matiere_elevetrim3.setListedeseleves(elevetrim3.getListedeseleves());
            matiere_elevetrim3.setMatieres(elevetrim3.getMatieres());
            matiere_elevetrim3.setNotesdeselevestrim3PK(nottrim3);
            if (note5ereseq != null && note6eseq != null) {
                matiere_elevetrim3.setLanote((note5ereseq.add(note6eseq)).divide(BigDecimal.valueOf(2)));
            } else if (note5ereseq == null && note6eseq == null) {
                matiere_elevetrim3.setLanote(null);
            } else if (note5ereseq == null && note6eseq != null) {
                matiere_elevetrim3.setLanote((note6eseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2)));
            } else if (note5ereseq != null && note6eseq == null) {
                matiere_elevetrim3.setLanote((note5ereseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2)));
            }
            notetrim3Facade.edit(matiere_elevetrim3);
            //enregistrement de la note annuelle
            BigDecimal note1ertri = notetrim1Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note2etri = notetrim2Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note3etri = notetrim3Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            Notesdeselevesann elevean = noteannFacade.upadateNoteAnuelleEleves(uneeleve.getNotesdeselevesseq6PK().getCodematiere(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            matiere_eleveann = new Notesdeselevesann();
            NotesdeselevesannPK notannuel = new NotesdeselevesannPK(elevean.getNotesdeselevesannPK().getMatriculeeleve(), elevean.getNotesdeselevesannPK().getCodematiere());
            matiere_eleveann.setListedeseleves(elevean.getListedeseleves());
            matiere_eleveann.setMatieres(elevean.getMatieres());
            matiere_eleveann.setNotesdeselevesannPK(notannuel);
            matiere_eleveann.setLanote(note1ertri.add(note2etri).add(note3etri).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_EVEN));
            noteannFacade.edit(matiere_eleveann);
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq6 uneeleve = (Notesdeselevesseq6) table.getRowData();
            matiere_eleveseq6 = new Notesdeselevesseq6();
            Notesdeselevesseq6PK notseq6 = new Notesdeselevesseq6PK(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            matiere_eleveseq6.setNotesdeselevesseq6PK(notseq6);
            matiere_eleveseq6.setListedeseleves(uneeleve.getListedeseleves());
            matiere_eleveseq6.setMatieres(uneeleve.getMatieres());
            matiere_eleveseq6.setLanote(null);
            noteSeq6Facade.edit(matiere_eleveseq6);

            BigDecimal note5ereseq = noteSeq5Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getMatieres().toString());
            BigDecimal note6eseq = noteSeq6Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getMatieres().toString());
            Notesdeselevestrim3 elevetrim3 = notetrim3Facade.upadateNoteTrim3Eleves(uneeleve.getMatieres().toString(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            matiere_elevetrim3 = new Notesdeselevestrim3();
            Notesdeselevestrim3PK nottrim3 = new Notesdeselevestrim3PK(elevetrim3.getListedeseleves().getMatriculeeleve(), elevetrim3.getMatieres().toString());
            matiere_elevetrim3.setListedeseleves(elevetrim3.getListedeseleves());
            matiere_elevetrim3.setMatieres(elevetrim3.getMatieres());
            matiere_elevetrim3.setNotesdeselevestrim3PK(nottrim3);
            if (note5ereseq == null && note6eseq == null) {
                matiere_elevetrim3.setLanote(null);
            } else if (note5ereseq != null && note6eseq == null) {
                matiere_elevetrim3.setLanote((note5ereseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2)));
            }
            notetrim3Facade.edit(matiere_elevetrim3);
            //enregistrement de la note annuelle
            BigDecimal note1ertri = notetrim1Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note2etri = notetrim2Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note3etri = notetrim3Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            Notesdeselevesann elevean = noteannFacade.upadateNoteAnuelleEleves(uneeleve.getNotesdeselevesseq6PK().getCodematiere(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            matiere_eleveann = new Notesdeselevesann();
            NotesdeselevesannPK notannuel = new NotesdeselevesannPK(elevean.getNotesdeselevesannPK().getMatriculeeleve(), elevean.getNotesdeselevesannPK().getCodematiere());
            matiere_eleveann.setListedeseleves(elevean.getListedeseleves());
            matiere_eleveann.setMatieres(elevean.getMatieres());
            matiere_eleveann.setNotesdeselevesannPK(notannuel);
            matiere_eleveann.setLanote(note1ertri.add(note2etri).add(note3etri).divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_EVEN));
            noteannFacade.edit(matiere_eleveann);
        } else {

        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Listedeseleves> getEleveList() {
        return eleveList;
    }

    public void setEleveList(List<Listedeseleves> eleveList) {
        this.eleveList = eleveList;
    }

    public ListedeselevesFacadeLocal getEleveFacade() {
        return eleveFacade;
    }

    public void setEleveFacade(ListedeselevesFacadeLocal eleveFacade) {
        this.eleveFacade = eleveFacade;
    }

    public Listedeseleves getEleve() {
        return eleve;
    }

    public void setEleve(Listedeseleves eleve) {
        this.eleve = eleve;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public boolean isSeq1() {
        return seq1;
    }

    public void setSeq1(boolean seq1) {
        this.seq1 = seq1;
    }

    public boolean isSeq2() {
        return seq2;
    }

    public void setSeq2(boolean seq2) {
        this.seq2 = seq2;
    }

    public boolean isSeq3() {
        return seq3;
    }

    public void setSeq3(boolean seq3) {
        this.seq3 = seq3;
    }

    public boolean isSeq4() {
        return seq4;
    }

    public void setSeq4(boolean seq4) {
        this.seq4 = seq4;
    }

    public boolean isSeq5() {
        return seq5;
    }

    public void setSeq5(boolean seq5) {
        this.seq5 = seq5;
    }

    public boolean isSeq6() {
        return seq6;
    }

    public void setSeq6(boolean seq6) {
        this.seq6 = seq6;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public List<Notesdeselevesseq1> getEleveListSeq1() {
        return eleveListSeq1;
    }

    public void setEleveListSeq1(List<Notesdeselevesseq1> eleveListSeq1) {
        this.eleveListSeq1 = eleveListSeq1;
    }

    public List<Notesdeselevesseq2> getEleveListSeq2() {
        return eleveListSeq2;
    }

    public void setEleveListSeq2(List<Notesdeselevesseq2> eleveListSeq2) {
        this.eleveListSeq2 = eleveListSeq2;
    }

    public List<Notesdeselevesseq3> getEleveListSeq3() {
        return eleveListSeq3;
    }

    public void setEleveListSeq3(List<Notesdeselevesseq3> eleveListSeq3) {
        this.eleveListSeq3 = eleveListSeq3;
    }

    public List<Notesdeselevesseq4> getEleveListSeq4() {
        return eleveListSeq4;
    }

    public void setEleveListSeq4(List<Notesdeselevesseq4> eleveListSeq4) {
        this.eleveListSeq4 = eleveListSeq4;
    }

    public List<Notesdeselevesseq5> getEleveListSeq5() {
        return eleveListSeq5;
    }

    public void setEleveListSeq5(List<Notesdeselevesseq5> eleveListSeq5) {
        this.eleveListSeq5 = eleveListSeq5;
    }

    public List<Notesdeselevesseq6> getEleveListSeq6() {
        return eleveListSeq6;
    }

    public void setEleveListSeq6(List<Notesdeselevesseq6> eleveListSeq6) {
        this.eleveListSeq6 = eleveListSeq6;
    }

    public Notesdeselevesseq2 getMatiere_eleveseq2() {
        return matiere_eleveseq2;
    }

    public void setMatiere_eleveseq2(Notesdeselevesseq2 matiere_eleveseq2) {
        this.matiere_eleveseq2 = matiere_eleveseq2;
    }

    public Notesdeselevesseq3 getMatiere_eleveseq3() {
        return matiere_eleveseq3;
    }

    public void setMatiere_eleveseq3(Notesdeselevesseq3 matiere_eleveseq3) {
        this.matiere_eleveseq3 = matiere_eleveseq3;
    }

    public Notesdeselevesseq4 getMatiere_eleveseq4() {
        return matiere_eleveseq4;
    }

    public void setMatiere_eleveseq4(Notesdeselevesseq4 matiere_eleveseq4) {
        this.matiere_eleveseq4 = matiere_eleveseq4;
    }

    public Notesdeselevesseq5 getMatiere_eleveseq5() {
        return matiere_eleveseq5;
    }

    public void setMatiere_eleveseq5(Notesdeselevesseq5 matiere_eleveseq5) {
        this.matiere_eleveseq5 = matiere_eleveseq5;
    }

    public Notesdeselevesseq6 getMatiere_eleveseq6() {
        return matiere_eleveseq6;
    }

    public void setMatiere_eleveseq6(Notesdeselevesseq6 matiere_eleveseq6) {
        this.matiere_eleveseq6 = matiere_eleveseq6;
    }

    public Notesdeselevestrim2 getMatiere_elevetrim2() {
        return matiere_elevetrim2;
    }

    public void setMatiere_elevetrim2(Notesdeselevestrim2 matiere_elevetrim2) {
        this.matiere_elevetrim2 = matiere_elevetrim2;
    }

    public Notesdeselevestrim3 getMatiere_elevetrim3() {
        return matiere_elevetrim3;
    }

    public void setMatiere_elevetrim3(Notesdeselevestrim3 matiere_elevetrim3) {
        this.matiere_elevetrim3 = matiere_elevetrim3;
    }

    public Notesdeselevesseq1 getMatiere_eleveseq1() {
        return matiere_eleveseq1;
    }

    public void setMatiere_eleveseq1(Notesdeselevesseq1 matiere_eleveseq1) {
        this.matiere_eleveseq1 = matiere_eleveseq1;
    }

    public Notesdeselevestrim1 getMatiere_elevetrim1() {
        return matiere_elevetrim1;
    }

    public void setMatiere_elevetrim1(Notesdeselevestrim1 matiere_elevetrim1) {
        this.matiere_elevetrim1 = matiere_elevetrim1;
    }

    public Notesdeselevesann getMatiere_eleveann() {
        return matiere_eleveann;
    }

    public void setMatiere_eleveann(Notesdeselevesann matiere_eleveann) {
        this.matiere_eleveann = matiere_eleveann;
    }

    public List<Annmoyennesrangsdeseleves> getAnListAdmis() {
        return anListAdmis;
    }

    public void setAnListAdmis(List<Annmoyennesrangsdeseleves> anListAdmis) {
        this.anListAdmis = anListAdmis;
    }

    public Annmoyennesrangsdeseleves getAnMoyEleves() {
        return anMoyEleves;
    }

    public void setAnMoyEleves(Annmoyennesrangsdeseleves anMoyEleves) {
        this.anMoyEleves = anMoyEleves;
    }

    public BigDecimal getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(BigDecimal moyenne) {
        this.moyenne = moyenne;
    }

}
