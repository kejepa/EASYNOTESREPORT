/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Listedeseleves;
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
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import sessions.ListepasswordFacadeLocal;
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
@Named(value = "noteSeqController")
@SessionScoped
public class NoteSeqController implements Serializable {

    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    @EJB
    private Notesdeselevesseq2FacadeLocal noteseq2Facade;
    @EJB
    private Notesdeselevesseq3FacadeLocal noteseq3Facade;
    @EJB
    private Notesdeselevesseq4FacadeLocal noteseq4Facade;
    @EJB
    private Notesdeselevesseq5FacadeLocal noteseq5Facade;
    @EJB
    private Notesdeselevesseq6FacadeLocal noteseq6Facade;
    @EJB
    private Notesdeselevestrim1FacadeLocal notetrim1Facade;
    @EJB
    private Notesdeselevestrim2FacadeLocal notetrim2Facade;
    @EJB
    private Notesdeselevestrim3FacadeLocal notetrim3Facade;
    @EJB
    private NotesdeselevesannFacadeLocal noteannFacade;
    @EJB
    private ListepasswordFacadeLocal pwdFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Object[]> noteseq1List = new ArrayList<>();
    private List<Object[]> noteseq2List = new ArrayList<>();
    private List<Object[]> noteseq3List = new ArrayList<>();
    private List<Object[]> noteseq4List = new ArrayList<>();
    private List<Object[]> noteseq5List = new ArrayList<>();
    private List<Object[]> noteseq6List = new ArrayList<>();
    private List<Notesdeselevesseq1> noteseq1Liste = new ArrayList<>();
    private List<Notesdeselevesseq2> noteseq2Liste = new ArrayList<>();
    private List<Notesdeselevesseq3> noteseq3Liste = new ArrayList<>();
    private List<Notesdeselevesseq4> noteseq4Liste = new ArrayList<>();
    private List<Notesdeselevesseq5> noteseq5Liste = new ArrayList<>();
    private List<Notesdeselevesseq6> noteseq6Liste = new ArrayList<>();
    List<Listedeseleves> listeEleves = new ArrayList<>();
    List<Notesdeselevestrim1> ntrim1 = new ArrayList<>();
    List<Notesdeselevestrim2> ntrim2 = new ArrayList<>();
    List<Notesdeselevestrim3> ntrim3 = new ArrayList<>();
    List<Notesdeselevesann> nann = new ArrayList<>();
    private Notesdeselevesseq1 noteseq1;
    private Notesdeselevesseq2 noteseq2;
    private Notesdeselevesseq3 noteseq3;
    private Notesdeselevesseq4 noteseq4;
    private Notesdeselevesseq5 noteseq5;
    private Notesdeselevesseq6 noteseq6;
    private Notesdeselevestrim1 notetrim1;
    private Notesdeselevestrim2 notetrim2;
    private Notesdeselevestrim3 notetrim3;
    private Notesdeselevesann noteann;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;
    private boolean msgSelect = false;

    public NoteSeqController() {
    }

    public void verifierSeqClasseMatiere() {
        if (ManagedSeqController.seq == null) {
            msgSelect = true;
        }
        if (ManagedSeqController.classe == null) {
            msgSelect = true;
        }
        if (ManagedSeqController.matiere == null) {
            msgSelect = true;
        }
    }

    public void desactiverMsgSelect() {
        msgSelect = false;
    }

    public void initialisation() {
        TestMsgLogin = false;
        ManagedSeqController.pwd = " ";
    }

    public String teacher() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return "";
        } else {
            String teach = pwdFacade.Enseignat_de_la_Matiere(ManagedSeqController.pwd, ManagedSeqController.matiere.toString());
            return teach;
        }
    }

    public int composer() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq1Facade.eleveComposé(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public int noncomposer() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq1Facade.eleveAbsent(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public BigDecimal moyenne() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal moy = noteseq1Facade.findMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return moy;
        }
    }

    public BigDecimal tauxReussite() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal taux = noteseq1Facade.findTauxreussite(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return taux;
        }
    }

    public BigDecimal pludGrandeNote() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pg = noteseq1Facade.findGandeMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pg;
        }
    }

    public BigDecimal pludPetiteNote() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pp = noteseq1Facade.findPetiteMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pp;
        }
    }

    public int composer2() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq2Facade.eleveComposé(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public int noncomposer2() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq2Facade.eleveAbsent(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public BigDecimal moyenne2() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal moy = noteseq2Facade.findMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return moy;
        }
    }

    public BigDecimal tauxReussite2() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal taux = noteseq2Facade.findTauxreussite(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return taux;
        }
    }

    public BigDecimal pludGrandeNote2() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pg = noteseq2Facade.findGandeMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pg;
        }
    }

    public BigDecimal pludPetiteNote2() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pp = noteseq2Facade.findPetiteMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pp;
        }
    }

    public int composer3() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq3Facade.eleveComposé(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public int noncomposer3() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq3Facade.eleveAbsent(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public BigDecimal moyenne3() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal moy = noteseq3Facade.findMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return moy;
        }
    }

    public BigDecimal tauxReussite3() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal taux = noteseq3Facade.findTauxreussite(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return taux;
        }
    }

    public BigDecimal pludGrandeNote3() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pg = noteseq3Facade.findGandeMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pg;
        }
    }

    public BigDecimal pludPetiteNote3() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pp = noteseq3Facade.findPetiteMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pp;
        }
    }

    public int composer4() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq4Facade.eleveComposé(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public int noncomposer4() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq4Facade.eleveAbsent(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public BigDecimal moyenne4() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal moy = noteseq4Facade.findMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return moy;
        }
    }

    public BigDecimal tauxReussite4() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal taux = noteseq4Facade.findTauxreussite(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return taux;
        }
    }

    public BigDecimal pludGrandeNote4() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pg = noteseq4Facade.findGandeMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pg;
        }
    }

    public BigDecimal pludPetiteNote4() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pp = noteseq4Facade.findPetiteMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pp;
        }
    }

    public int composer5() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq5Facade.eleveComposé(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public int noncomposer5() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq5Facade.eleveAbsent(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public BigDecimal moyenne5() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal moy = noteseq5Facade.findMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return moy;
        }
    }

    public BigDecimal tauxReussite5() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal taux = noteseq5Facade.findTauxreussite(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return taux;
        }
    }

    public BigDecimal pludGrandeNote5() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pg = noteseq5Facade.findGandeMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pg;
        }
    }

    public BigDecimal pludPetiteNote5() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pp = noteseq5Facade.findPetiteMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pp;
        }
    }

    public int composer6() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq6Facade.eleveComposé(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public int noncomposer6() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return 0;
        } else {
            int eff = noteseq6Facade.eleveAbsent(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return eff;
        }
    }

    public BigDecimal moyenne6() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal moy = noteseq6Facade.findMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return moy;
        }
    }

    public BigDecimal tauxReussite6() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal taux = noteseq6Facade.findTauxreussite(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return taux;
        }
    }

    public BigDecimal pludGrandeNote6() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pg = noteseq6Facade.findGandeMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pg;
        }
    }

    public BigDecimal pludPetiteNote6() {
        if (ManagedSeqController.pwd == null || ManagedSeqController.matiere.toString() == null) {
            return null;
        } else {
            BigDecimal pp = noteseq6Facade.findPetiteMoyenne(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
            return pp;
        }
    }

    public void actualisationmoyenne_tauxdereussite_pdm_ppm() {
        moyenne();
        tauxReussite();
        pludGrandeNote();
        pludPetiteNote();
    }

    public String notesseq() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = df.format(date);
        try {
            Date dateOperation = df.parse(dateString);
            Operations mouchard = new Operations();
            mouchard.setIdoperations(mouchardFacade.nextId());
            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
            mouchard.setTypeoperation("Saisie Des Notes par matière");
            mouchard.setDateoperation(dateOperation);
            mouchard.setDescription("Saisie Des Notes par matière de SEQ" + ManagedSeqController.seq + ", " + ManagedSeqController.classe.toString() + ", " + ManagedSeqController.matiere.toString());
            mouchardFacade.create(mouchard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (ManagedSeqController.seq) {
            case "1":
                return notesseq1();

            case "2":
                return notesseq2();
            case "3":
                return notesseq3();
            case "4":
                return notesseq4();
            case "5":
                return notesseq5();
            case "6":
                return notesseq6();
        }
        return "";
    }

    public String notesseq1() {
        if (noteseq1Facade.findEnseignatByPwd(ManagedSeqController.pwd, ManagedSeqController.matiere.toString())) {
            if (ManagedSeqController.seq != null && ManagedSeqController.classe != null && ManagedSeqController.matiere != null) {
                if ((noteseq1Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteseq1Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteseq1 = new Notesdeselevesseq1();
                        Notesdeselevesseq1PK notseq1 = new Notesdeselevesseq1PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteseq1.setNotesdeselevesseq1PK(notseq1);
                        noteseq1.setListedeseleves(l);
                        noteseq1.setMatieres(ManagedSeqController.matiere);
                        noteseq1Facade.create(noteseq1);
                    }
                }
                if ((notetrim1Facade.listeDesNouveauxElevesTrim1(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = notetrim1Facade.listeDesNouveauxElevesTrim1(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        notetrim1 = new Notesdeselevestrim1();
                        Notesdeselevestrim1PK nottrim1 = new Notesdeselevestrim1PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        notetrim1.setNotesdeselevestrim1PK(nottrim1);
                        notetrim1.setListedeseleves(l);
                        notetrim1.setMatieres(ManagedSeqController.matiere);
                        notetrim1Facade.create(notetrim1);
                    }
                }
                if ((noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteann = new Notesdeselevesann();
                        NotesdeselevesannPK notann = new NotesdeselevesannPK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteann.setNotesdeselevesannPK(notann);
                        noteann.setListedeseleves(l);
                        noteann.setMatieres(ManagedSeqController.matiere);
                        noteannFacade.create(noteann);
                    }
                }
                noteseq1Liste.clear();
                if (noteseq1Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()) == null) {
                    return "noteseq1.xhtml?faces-redirect=true";
                } else {
                    noteseq1Liste.addAll(noteseq1Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                    noteseq1 = new Notesdeselevesseq1();
                    return "noteseq1.xhtml?faces-redirect=true";
                }

            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String notesseq2() {
        if (noteseq2Facade.findEnseignatByPwd(ManagedSeqController.pwd, ManagedSeqController.matiere.toString())) {
            if (ManagedSeqController.seq != null && ManagedSeqController.classe != null && ManagedSeqController.matiere != null) {
                if ((noteseq2Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteseq2Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteseq2 = new Notesdeselevesseq2();
                        Notesdeselevesseq2PK notseq2 = new Notesdeselevesseq2PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteseq2.setNotesdeselevesseq2PK(notseq2);
                        noteseq2.setListedeseleves(l);
                        noteseq2.setMatieres(ManagedSeqController.matiere);
                        noteseq2Facade.create(noteseq2);
                    }
                }
                if ((notetrim1Facade.listeDesNouveauxElevesTrim1(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = notetrim1Facade.listeDesNouveauxElevesTrim1(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        notetrim1 = new Notesdeselevestrim1();
                        Notesdeselevestrim1PK nottrim1 = new Notesdeselevestrim1PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        notetrim1.setNotesdeselevestrim1PK(nottrim1);
                        notetrim1.setListedeseleves(l);
                        notetrim1.setMatieres(ManagedSeqController.matiere);
                        notetrim1Facade.create(notetrim1);
                    }
                }
                if ((noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteann = new Notesdeselevesann();
                        NotesdeselevesannPK notann = new NotesdeselevesannPK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteann.setNotesdeselevesannPK(notann);
                        noteann.setListedeseleves(l);
                        noteann.setMatieres(ManagedSeqController.matiere);
                        noteannFacade.create(noteann);
                    }
                }
                noteseq2Liste.clear();
                if (noteseq2Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()) == null) {
                    return "noteseq2.xhtml?faces-redirect=true";
                } else {
                    noteseq2Liste.addAll(noteseq2Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                    noteseq2 = new Notesdeselevesseq2();
                    return "noteseq2.xhtml?faces-redirect=true";
                }

            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String notesseq3() {
        if (noteseq3Facade.findEnseignatByPwd(ManagedSeqController.pwd, ManagedSeqController.matiere.toString())) {
            if (ManagedSeqController.seq != null && ManagedSeqController.classe != null && ManagedSeqController.matiere != null) {
                if ((noteseq3Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteseq3Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteseq3 = new Notesdeselevesseq3();
                        Notesdeselevesseq3PK notseq3 = new Notesdeselevesseq3PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteseq3.setNotesdeselevesseq3PK(notseq3);
                        noteseq3.setListedeseleves(l);
                        noteseq3.setMatieres(ManagedSeqController.matiere);
                        noteseq3Facade.create(noteseq3);
                    }
                }
                if ((notetrim2Facade.listeDesNouveauxElevesTrim2(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = notetrim2Facade.listeDesNouveauxElevesTrim2(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        notetrim2 = new Notesdeselevestrim2();
                        Notesdeselevestrim2PK nottrim2 = new Notesdeselevestrim2PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        notetrim2.setNotesdeselevestrim2PK(nottrim2);
                        notetrim2.setListedeseleves(l);
                        notetrim2.setMatieres(ManagedSeqController.matiere);
                        notetrim2Facade.create(notetrim2);
                    }
                }
                if ((noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteann = new Notesdeselevesann();
                        NotesdeselevesannPK notann = new NotesdeselevesannPK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteann.setNotesdeselevesannPK(notann);
                        noteann.setListedeseleves(l);
                        noteann.setMatieres(ManagedSeqController.matiere);
                        noteannFacade.create(noteann);
                    }
                }
                noteseq3Liste.clear();
                if (noteseq3Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()) == null) {
                    return "noteseq3.xhtml?faces-redirect=true";
                } else {
                    noteseq3Liste.addAll(noteseq3Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                    noteseq3 = new Notesdeselevesseq3();
                    return "noteseq3.xhtml?faces-redirect=true";
                }
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String notesseq4() {
        if (noteseq4Facade.findEnseignatByPwd(ManagedSeqController.pwd, ManagedSeqController.matiere.toString())) {
            if (ManagedSeqController.seq != null && ManagedSeqController.classe != null && ManagedSeqController.matiere != null) {
                if ((noteseq4Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteseq4Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteseq4 = new Notesdeselevesseq4();
                        Notesdeselevesseq4PK notseq4 = new Notesdeselevesseq4PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteseq4.setNotesdeselevesseq4PK(notseq4);
                        noteseq4.setListedeseleves(l);
                        noteseq4.setMatieres(ManagedSeqController.matiere);
                        noteseq4Facade.create(noteseq4);
                    }
                }
                if ((notetrim2Facade.listeDesNouveauxElevesTrim2(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = notetrim2Facade.listeDesNouveauxElevesTrim2(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        notetrim2 = new Notesdeselevestrim2();
                        Notesdeselevestrim2PK nottrim2 = new Notesdeselevestrim2PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        notetrim2.setNotesdeselevestrim2PK(nottrim2);
                        notetrim2.setListedeseleves(l);
                        notetrim2.setMatieres(ManagedSeqController.matiere);
                        notetrim2Facade.create(notetrim2);
                    }
                }
                if ((noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteann = new Notesdeselevesann();
                        NotesdeselevesannPK notann = new NotesdeselevesannPK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteann.setNotesdeselevesannPK(notann);
                        noteann.setListedeseleves(l);
                        noteann.setMatieres(ManagedSeqController.matiere);
                        noteannFacade.create(noteann);
                    }
                }
                noteseq4Liste.clear();
                if (noteseq4Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()) == null) {
                    return "noteseq4.xhtml?faces-redirect=true";
                } else {
                    noteseq4Liste.addAll(noteseq4Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                    noteseq4 = new Notesdeselevesseq4();
                    return "noteseq4.xhtml?faces-redirect=true";
                }
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String notesseq5() {
        if (noteseq5Facade.findEnseignatByPwd(ManagedSeqController.pwd, ManagedSeqController.matiere.toString())) {
            if (ManagedSeqController.seq != null && ManagedSeqController.classe != null && ManagedSeqController.matiere != null) {
                if ((noteseq5Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteseq5Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteseq5 = new Notesdeselevesseq5();
                        Notesdeselevesseq5PK notseq5 = new Notesdeselevesseq5PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteseq5.setNotesdeselevesseq5PK(notseq5);
                        noteseq5.setListedeseleves(l);
                        noteseq5.setMatieres(ManagedSeqController.matiere);
                        noteseq5Facade.create(noteseq5);
                    }
                }
                if ((notetrim3Facade.listeDesNouveauxElevesTrim3(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = notetrim3Facade.listeDesNouveauxElevesTrim3(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        notetrim3 = new Notesdeselevestrim3();
                        Notesdeselevestrim3PK nottrim3 = new Notesdeselevestrim3PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        notetrim3.setNotesdeselevestrim3PK(nottrim3);
                        notetrim3.setListedeseleves(l);
                        notetrim3.setMatieres(ManagedSeqController.matiere);
                        notetrim3Facade.create(notetrim3);
                    }
                }
                if ((noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteann = new Notesdeselevesann();
                        NotesdeselevesannPK notann = new NotesdeselevesannPK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteann.setNotesdeselevesannPK(notann);
                        noteann.setListedeseleves(l);
                        noteann.setMatieres(ManagedSeqController.matiere);
                        noteannFacade.create(noteann);
                    }
                }
                noteseq5Liste.clear();
                if (noteseq5Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()) == null) {
                    return "noteseq5.xhtml?faces-redirect=true";
                } else {
                    noteseq5Liste.addAll(noteseq5Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                    noteseq5 = new Notesdeselevesseq5();
                    return "noteseq5.xhtml?faces-redirect=true";
                }
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String notesseq6() {
        if (noteseq6Facade.findEnseignatByPwd(ManagedSeqController.pwd, ManagedSeqController.matiere.toString())) {
            if (ManagedSeqController.seq != null && ManagedSeqController.classe != null && ManagedSeqController.matiere != null) {
                if ((noteseq6Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteseq6Facade.listeDesNouveauxEleves(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteseq6 = new Notesdeselevesseq6();
                        Notesdeselevesseq6PK notseq6 = new Notesdeselevesseq6PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteseq6.setNotesdeselevesseq6PK(notseq6);
                        noteseq6.setListedeseleves(l);
                        noteseq6.setMatieres(ManagedSeqController.matiere);
                        noteseq6Facade.create(noteseq6);
                    }
                }
                if ((notetrim3Facade.listeDesNouveauxElevesTrim3(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = notetrim3Facade.listeDesNouveauxElevesTrim3(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        notetrim3 = new Notesdeselevestrim3();
                        Notesdeselevestrim3PK nottrim3 = new Notesdeselevestrim3PK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        notetrim3.setNotesdeselevestrim3PK(nottrim3);
                        notetrim3.setListedeseleves(l);
                        notetrim3.setMatieres(ManagedSeqController.matiere);
                        notetrim3Facade.create(notetrim3);
                    }
                }
                if ((noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString())) != null) {
                    listeEleves = noteannFacade.listeDesNouveauxElevesAnn(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString());
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        noteann = new Notesdeselevesann();
                        NotesdeselevesannPK notann = new NotesdeselevesannPK(l.getMatriculeeleve(), ManagedSeqController.matiere.toString());
                        noteann.setNotesdeselevesannPK(notann);
                        noteann.setListedeseleves(l);
                        noteann.setMatieres(ManagedSeqController.matiere);
                        noteannFacade.create(noteann);
                    }
                }
                noteseq6Liste.clear();
                if (noteseq6Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()) == null) {
                    return "noteseq6.xhtml?faces-redirect=true";
                } else {
                    noteseq6Liste.addAll(noteseq6Facade.listeMatiereClasse(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                    noteseq6 = new Notesdeselevesseq6();
                    return "noteseq6.xhtml?faces-redirect=true";
                }
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public void onCellEdit1(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq1 uneeleve = (Notesdeselevesseq1) table.getRowData();
            noteseq1Facade.edit(uneeleve);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == oldValue) {
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq1 uneeleve = (Notesdeselevesseq1) table.getRowData();
            noteseq1 = new Notesdeselevesseq1();
            Notesdeselevesseq1PK notseq1 = new Notesdeselevesseq1PK(uneeleve.getListedeseleves().getMatriculeeleve(), ManagedSeqController.matiere.toString());
            noteseq1.setNotesdeselevesseq1PK(notseq1);
            noteseq1.setListedeseleves(uneeleve.getListedeseleves());
            noteseq1.setMatieres(uneeleve.getMatieres());
            noteseq1.setLanote(null);
            noteseq1Facade.edit(noteseq1);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit2(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq2 uneeleve = (Notesdeselevesseq2) table.getRowData();
            noteseq2Facade.edit(uneeleve);
            BigDecimal note1ereseq = noteseq1Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            BigDecimal note2eseq = noteseq2Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            Notesdeselevestrim1 elevetrim1 = notetrim1Facade.upadateNoteTrim1Eleves(uneeleve.getNotesdeselevesseq2PK().getCodematiere(), uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve());
            notetrim1 = new Notesdeselevestrim1();
            Notesdeselevestrim1PK nottrim1 = new Notesdeselevestrim1PK(elevetrim1.getListedeseleves().getMatriculeeleve(), elevetrim1.getMatieres().toString());
            notetrim1.setListedeseleves(elevetrim1.getListedeseleves());
            notetrim1.setMatieres(elevetrim1.getMatieres());
            notetrim1.setNotesdeselevestrim1PK(nottrim1);
            if (note1ereseq != null && note2eseq != null) {
                notetrim1.setLanote((note1ereseq.add(note2eseq)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            } else if (note1ereseq == null && note2eseq == null) {
                notetrim1.setLanote(null);
            } else if (note1ereseq == null && note2eseq != null) {
                notetrim1.setLanote((note2eseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            } else if (note1ereseq != null && note2eseq == null) {
                notetrim1.setLanote((note1ereseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            }
            notetrim1Facade.edit(notetrim1);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == oldValue) {

        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq2 uneeleve = (Notesdeselevesseq2) table.getRowData();
            noteseq2 = new Notesdeselevesseq2();
            Notesdeselevesseq2PK notseq2 = new Notesdeselevesseq2PK(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            noteseq2.setNotesdeselevesseq2PK(notseq2);
            noteseq2.setListedeseleves(uneeleve.getListedeseleves());
            noteseq2.setMatieres(uneeleve.getMatieres());
            noteseq2.setLanote(null);
            noteseq2Facade.edit(noteseq2);

            BigDecimal note1ereseq = noteseq1Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            BigDecimal note2eseq = noteseq2Facade.noteEleve(uneeleve.getNotesdeselevesseq2PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq2PK().getCodematiere());
            Notesdeselevestrim1 elevetrim1 = notetrim1Facade.upadateNoteTrim1Eleves(uneeleve.getNotesdeselevesseq2PK().getCodematiere(), uneeleve.getListedeseleves().toString());
            notetrim1 = new Notesdeselevestrim1();
            Notesdeselevestrim1PK nottrim1 = new Notesdeselevestrim1PK(elevetrim1.getListedeseleves().getMatriculeeleve(), elevetrim1.getMatieres().toString());
            notetrim1.setListedeseleves(elevetrim1.getListedeseleves());
            notetrim1.setMatieres(elevetrim1.getMatieres());
            notetrim1.setNotesdeselevestrim1PK(nottrim1);
            if (note1ereseq == null && note2eseq == null) {
                notetrim1.setLanote(null);
            } else if (note1ereseq != null && note2eseq == null) {
                notetrim1.setLanote((note1ereseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            }
            notetrim1Facade.edit(notetrim1);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit3(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq3 uneeleve = (Notesdeselevesseq3) table.getRowData();
            noteseq3Facade.edit(uneeleve);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == oldValue) {
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq3 uneeleve = (Notesdeselevesseq3) table.getRowData();
            noteseq3 = new Notesdeselevesseq3();
            Notesdeselevesseq3PK notseq3 = new Notesdeselevesseq3PK(uneeleve.getListedeseleves().getMatriculeeleve(), ManagedSeqController.matiere.toString());
            noteseq3.setNotesdeselevesseq3PK(notseq3);
            noteseq3.setListedeseleves(uneeleve.getListedeseleves());
            noteseq3.setMatieres(uneeleve.getMatieres());
            noteseq3.setLanote(null);
            noteseq3Facade.edit(noteseq3);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit4(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq4 uneeleve = (Notesdeselevesseq4) table.getRowData();
            noteseq4Facade.edit(uneeleve);
            BigDecimal note3ereseq = noteseq3Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            BigDecimal note4eseq = noteseq4Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            Notesdeselevestrim2 elevetrim2 = notetrim2Facade.upadateNoteTrim2Eleves(uneeleve.getNotesdeselevesseq4PK().getCodematiere(), uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve());
            notetrim2 = new Notesdeselevestrim2();
            Notesdeselevestrim2PK nottrim2 = new Notesdeselevestrim2PK(elevetrim2.getListedeseleves().getMatriculeeleve(), elevetrim2.getMatieres().toString());
            notetrim2.setListedeseleves(elevetrim2.getListedeseleves());
            notetrim2.setMatieres(elevetrim2.getMatieres());
            notetrim2.setNotesdeselevestrim2PK(nottrim2);
            if (note3ereseq != null && note4eseq != null) {
                notetrim2.setLanote((note3ereseq.add(note4eseq)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            } else if (note3ereseq == null && note4eseq == null) {
                notetrim2.setLanote(null);
            } else if (note3ereseq == null && note4eseq != null) {
                notetrim2.setLanote((note4eseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            } else if (note3ereseq != null && note4eseq == null) {
                notetrim2.setLanote((note3ereseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            }
            notetrim2Facade.edit(notetrim2);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == oldValue) {

        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq4 uneeleve = (Notesdeselevesseq4) table.getRowData();
            noteseq4 = new Notesdeselevesseq4();
            Notesdeselevesseq4PK notseq4 = new Notesdeselevesseq4PK(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            noteseq4.setNotesdeselevesseq4PK(notseq4);
            noteseq4.setListedeseleves(uneeleve.getListedeseleves());
            noteseq4.setMatieres(uneeleve.getMatieres());
            noteseq4.setLanote(null);
            noteseq4Facade.edit(noteseq4);

            BigDecimal note3ereseq = noteseq3Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            BigDecimal note4eseq = noteseq4Facade.noteEleve(uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq4PK().getCodematiere());
            Notesdeselevestrim2 elevetrim2 = notetrim2Facade.upadateNoteTrim2Eleves(uneeleve.getNotesdeselevesseq4PK().getCodematiere(), uneeleve.getNotesdeselevesseq4PK().getMatriculeeleve());
            notetrim2 = new Notesdeselevestrim2();
            Notesdeselevestrim2PK nottrim2 = new Notesdeselevestrim2PK(elevetrim2.getListedeseleves().getMatriculeeleve(), elevetrim2.getMatieres().toString());
            notetrim2.setListedeseleves(elevetrim2.getListedeseleves());
            notetrim2.setMatieres(elevetrim2.getMatieres());
            notetrim2.setNotesdeselevestrim2PK(nottrim2);
            if (note3ereseq == null && note4eseq == null) {
                notetrim2.setLanote(null);
            } else if (note3ereseq != null && note4eseq == null) {
                notetrim2.setLanote((note3ereseq.add(BigDecimal.ZERO)).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
            }
            notetrim2Facade.edit(notetrim2);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit5(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq5 uneeleve = (Notesdeselevesseq5) table.getRowData();
            noteseq5Facade.edit(uneeleve);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == oldValue) {
        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq5 uneeleve = (Notesdeselevesseq5) table.getRowData();
            noteseq5 = new Notesdeselevesseq5();
            Notesdeselevesseq5PK notseq5 = new Notesdeselevesseq5PK(uneeleve.getListedeseleves().getMatriculeeleve(), ManagedSeqController.matiere.toString());
            noteseq5.setNotesdeselevesseq5PK(notseq5);
            noteseq5.setListedeseleves(uneeleve.getListedeseleves());
            noteseq5.setMatieres(uneeleve.getMatieres());
            noteseq5.setLanote(null);
            noteseq5Facade.edit(noteseq5);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit6(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq6 uneeleve = (Notesdeselevesseq6) table.getRowData();
            noteseq6Facade.edit(uneeleve);
            BigDecimal note5ereseq = noteseq5Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note6eseq = noteseq6Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            Notesdeselevestrim3 elevetrim3 = notetrim3Facade.upadateNoteTrim3Eleves(uneeleve.getNotesdeselevesseq6PK().getCodematiere(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            notetrim3 = new Notesdeselevestrim3();
            Notesdeselevestrim3PK nottrim3 = new Notesdeselevestrim3PK(elevetrim3.getListedeseleves().getMatriculeeleve(), elevetrim3.getMatieres().toString());
            notetrim3.setListedeseleves(elevetrim3.getListedeseleves());
            notetrim3.setMatieres(elevetrim3.getMatieres());
            notetrim3.setNotesdeselevestrim3PK(nottrim3);
            if (note5ereseq != null && note6eseq != null) {
                notetrim3.setLanote((note5ereseq.add(note6eseq)).divide(BigDecimal.valueOf(2)));
            } else if (note5ereseq == null && note6eseq == null) {
                notetrim3.setLanote(null);
            } else if (note5ereseq == null && note6eseq != null) {
                notetrim3.setLanote((note6eseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            } else if (note5ereseq != null && note6eseq == null) {
                notetrim3.setLanote((note5ereseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            }
            notetrim3Facade.edit(notetrim3);
            //enregistrement de la note annuelle
            BigDecimal note1ertri = notetrim1Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note2etri = notetrim2Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note3etri = notetrim3Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            Notesdeselevesann elevean = noteannFacade.upadateNoteAnuelleEleves(uneeleve.getNotesdeselevesseq6PK().getCodematiere(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            noteann = new Notesdeselevesann();
            NotesdeselevesannPK notannuel = new NotesdeselevesannPK(elevean.getListedeseleves().getMatriculeeleve(), elevean.getMatieres().toString());
            noteann.setListedeseleves(elevean.getListedeseleves());
            noteann.setMatieres(elevean.getMatieres());
            noteann.setNotesdeselevesannPK(notannuel);
            noteann.setLanote(note1ertri.add(note2etri).add(note3etri));
            noteannFacade.edit(noteann);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (newValue == oldValue) {

        } else if (newValue == null) {
            DataTable table = (DataTable) event.getSource();
            Notesdeselevesseq6 uneeleve = (Notesdeselevesseq6) table.getRowData();
            noteseq6 = new Notesdeselevesseq6();
            Notesdeselevesseq6PK notseq6 = new Notesdeselevesseq6PK(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            noteseq6.setNotesdeselevesseq6PK(notseq6);
            noteseq6.setListedeseleves(uneeleve.getListedeseleves());
            noteseq6.setMatieres(uneeleve.getMatieres());
            noteseq6.setLanote(null);
            noteseq6Facade.edit(noteseq6);

            BigDecimal note5ereseq = noteseq5Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note6eseq = noteseq6Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            Notesdeselevestrim3 elevetrim3 = notetrim3Facade.upadateNoteTrim3Eleves(uneeleve.getNotesdeselevesseq6PK().getCodematiere(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            notetrim3 = new Notesdeselevestrim3();
            Notesdeselevestrim3PK nottrim3 = new Notesdeselevestrim3PK(elevetrim3.getListedeseleves().getMatriculeeleve(), elevetrim3.getMatieres().toString());
            notetrim3.setListedeseleves(elevetrim3.getListedeseleves());
            notetrim3.setMatieres(elevetrim3.getMatieres());
            notetrim3.setNotesdeselevestrim3PK(nottrim3);
            if (note5ereseq == null && note6eseq == null) {
                notetrim3.setLanote(null);
            } else if (note5ereseq != null && note6eseq == null) {
                notetrim3.setLanote((note5ereseq.add(BigDecimal.valueOf(0))).divide(BigDecimal.valueOf(2)));
            }
            notetrim3Facade.edit(notetrim3);
            //enregistrement de la note annuelle
            BigDecimal note1ertri = notetrim1Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note2etri = notetrim2Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            BigDecimal note3etri = notetrim3Facade.noteEleve(uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve(), uneeleve.getNotesdeselevesseq6PK().getCodematiere());
            Notesdeselevesann elevean = noteannFacade.upadateNoteAnuelleEleves(uneeleve.getNotesdeselevesseq6PK().getCodematiere(), uneeleve.getNotesdeselevesseq6PK().getMatriculeeleve());
            noteann = new Notesdeselevesann();
            NotesdeselevesannPK notannuel = new NotesdeselevesannPK(elevean.getListedeseleves().getMatriculeeleve(), elevean.getMatieres().toString());
            noteann.setListedeseleves(elevean.getListedeseleves());
            noteann.setMatieres(elevean.getMatieres());
            noteann.setNotesdeselevesannPK(notannuel);
            noteann.setLanote(note1ertri.add(note2etri).add(note3etri));
            noteannFacade.edit(noteann);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Object[]> listeMatieresParClass() {
        if (ManagedSeqController.classe == null) {
            return null;
        } else {
            List<Object[]> listM = noteseq1Facade.listedesMatieresParClasse(ManagedSeqController.classe.toString());
            return listM;
        }
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                noteseq1 = new Notesdeselevesseq1();
                break;
            case "modifier":
                TestMsgLogin = false;
                champActif = true;
                break;
            case "supprimer":
                TestMsgLogin = false;
                champActif = true;
                break;
            case "consulter":
                TestMsgLogin = false;
                champActif = false;
                break;

        }
    }

    public Notesdeselevesseq1FacadeLocal getNoteseq1Facade() {
        return noteseq1Facade;
    }

    public void setNoteseq1Facade(Notesdeselevesseq1FacadeLocal noteseq1Facade) {
        this.noteseq1Facade = noteseq1Facade;
    }

    public List<Object[]> getNoteseq1List() {
        return noteseq1List;
    }

    public void setNoteseq1List(List<Object[]> noteseq1List) {
        this.noteseq1List = noteseq1List;
    }

    public Notesdeselevesseq1 getNoteseq1() {
        return noteseq1;
    }

    public void setNoteseq1(Notesdeselevesseq1 noteseq1) {
        this.noteseq1 = noteseq1;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isChampActif() {
        return champActif;
    }

    public void setChampActif(boolean champActif) {
        this.champActif = champActif;
    }

    public boolean isTestMsgLogin() {
        return TestMsgLogin;
    }

    public void setTestMsgLogin(boolean TestMsgLogin) {
        this.TestMsgLogin = TestMsgLogin;
    }

    public List<Notesdeselevesseq1> getNoteseq1Liste() {
        return noteseq1Liste;
    }

    public void setNoteseq1Liste(List<Notesdeselevesseq1> noteseq1Liste) {
        this.noteseq1Liste = noteseq1Liste;
    }

    public List<Listedeseleves> getListeEleves() {
        return listeEleves;
    }

    public void setListeEleves(List<Listedeseleves> listeEleves) {
        this.listeEleves = listeEleves;
    }

    public Notesdeselevestrim1FacadeLocal getNotetrim1Facade() {
        return notetrim1Facade;
    }

    public void setNotetrim1Facade(Notesdeselevestrim1FacadeLocal notetrim1Facade) {
        this.notetrim1Facade = notetrim1Facade;
    }

    public NotesdeselevesannFacadeLocal getNoteannFacade() {
        return noteannFacade;
    }

    public void setNoteannFacade(NotesdeselevesannFacadeLocal noteannFacade) {
        this.noteannFacade = noteannFacade;
    }

    public Notesdeselevestrim1 getNotetrim1() {
        return notetrim1;
    }

    public void setNotetrim1(Notesdeselevestrim1 notetrim1) {
        this.notetrim1 = notetrim1;
    }

    public Notesdeselevesann getNoteann() {
        return noteann;
    }

    public void setNoteann(Notesdeselevesann noteann) {
        this.noteann = noteann;
    }

    public boolean isMsgSelect() {
        return msgSelect;
    }

    public void setMsgSelect(boolean msgSelect) {
        this.msgSelect = msgSelect;
    }

    public List<Notesdeselevestrim1> getNtrim1() {
        return ntrim1;
    }

    public void setNtrim1(List<Notesdeselevestrim1> ntrim1) {
        this.ntrim1 = ntrim1;
    }

    public List<Notesdeselevesann> getNann() {
        return nann;
    }

    public void setNann(List<Notesdeselevesann> nann) {
        this.nann = nann;
    }

    public ListepasswordFacadeLocal getPwdFacade() {
        return pwdFacade;
    }

    public void setPwdFacade(ListepasswordFacadeLocal pwdFacade) {
        this.pwdFacade = pwdFacade;
    }

    public Notesdeselevesseq2FacadeLocal getNoteseq2Facade() {
        return noteseq2Facade;
    }

    public void setNoteseq2Facade(Notesdeselevesseq2FacadeLocal noteseq2Facade) {
        this.noteseq2Facade = noteseq2Facade;
    }

    public Notesdeselevesseq3FacadeLocal getNoteseq3Facade() {
        return noteseq3Facade;
    }

    public void setNoteseq3Facade(Notesdeselevesseq3FacadeLocal noteseq3Facade) {
        this.noteseq3Facade = noteseq3Facade;
    }

    public Notesdeselevesseq4FacadeLocal getNoteseq4Facade() {
        return noteseq4Facade;
    }

    public void setNoteseq4Facade(Notesdeselevesseq4FacadeLocal noteseq4Facade) {
        this.noteseq4Facade = noteseq4Facade;
    }

    public Notesdeselevesseq5FacadeLocal getNoteseq5Facade() {
        return noteseq5Facade;
    }

    public void setNoteseq5Facade(Notesdeselevesseq5FacadeLocal noteseq5Facade) {
        this.noteseq5Facade = noteseq5Facade;
    }

    public Notesdeselevesseq6FacadeLocal getNoteseq6Facade() {
        return noteseq6Facade;
    }

    public void setNoteseq6Facade(Notesdeselevesseq6FacadeLocal noteseq6Facade) {
        this.noteseq6Facade = noteseq6Facade;
    }

    public Notesdeselevestrim2FacadeLocal getNotetrim2Facade() {
        return notetrim2Facade;
    }

    public void setNotetrim2Facade(Notesdeselevestrim2FacadeLocal notetrim2Facade) {
        this.notetrim2Facade = notetrim2Facade;
    }

    public Notesdeselevestrim3FacadeLocal getNotetrim3Facade() {
        return notetrim3Facade;
    }

    public void setNotetrim3Facade(Notesdeselevestrim3FacadeLocal notetrim3Facade) {
        this.notetrim3Facade = notetrim3Facade;
    }

    public List<Object[]> getNoteseq2List() {
        return noteseq2List;
    }

    public void setNoteseq2List(List<Object[]> noteseq2List) {
        this.noteseq2List = noteseq2List;
    }

    public List<Object[]> getNoteseq3List() {
        return noteseq3List;
    }

    public void setNoteseq3List(List<Object[]> noteseq3List) {
        this.noteseq3List = noteseq3List;
    }

    public List<Object[]> getNoteseq4List() {
        return noteseq4List;
    }

    public void setNoteseq4List(List<Object[]> noteseq4List) {
        this.noteseq4List = noteseq4List;
    }

    public List<Object[]> getNoteseq5List() {
        return noteseq5List;
    }

    public void setNoteseq5List(List<Object[]> noteseq5List) {
        this.noteseq5List = noteseq5List;
    }

    public List<Object[]> getNoteseq6List() {
        return noteseq6List;
    }

    public void setNoteseq6List(List<Object[]> noteseq6List) {
        this.noteseq6List = noteseq6List;
    }

    public List<Notesdeselevesseq2> getNoteseq2Liste() {
        return noteseq2Liste;
    }

    public void setNoteseq2Liste(List<Notesdeselevesseq2> noteseq2Liste) {
        this.noteseq2Liste = noteseq2Liste;
    }

    public List<Notesdeselevesseq3> getNoteseq3Liste() {
        return noteseq3Liste;
    }

    public void setNoteseq3Liste(List<Notesdeselevesseq3> noteseq3Liste) {
        this.noteseq3Liste = noteseq3Liste;
    }

    public List<Notesdeselevesseq4> getNoteseq4Liste() {
        return noteseq4Liste;
    }

    public void setNoteseq4Liste(List<Notesdeselevesseq4> noteseq4Liste) {
        this.noteseq4Liste = noteseq4Liste;
    }

    public List<Notesdeselevesseq5> getNoteseq5Liste() {
        return noteseq5Liste;
    }

    public void setNoteseq5Liste(List<Notesdeselevesseq5> noteseq5Liste) {
        this.noteseq5Liste = noteseq5Liste;
    }

    public List<Notesdeselevesseq6> getNoteseq6Liste() {
        return noteseq6Liste;
    }

    public void setNoteseq6Liste(List<Notesdeselevesseq6> noteseq6Liste) {
        this.noteseq6Liste = noteseq6Liste;
    }

    public List<Notesdeselevestrim2> getNtrim2() {
        return ntrim2;
    }

    public void setNtrim2(List<Notesdeselevestrim2> ntrim2) {
        this.ntrim2 = ntrim2;
    }

    public List<Notesdeselevestrim3> getNtrim3() {
        return ntrim3;
    }

    public void setNtrim3(List<Notesdeselevestrim3> ntrim3) {
        this.ntrim3 = ntrim3;
    }

    public Notesdeselevesseq2 getNoteseq2() {
        return noteseq2;
    }

    public void setNoteseq2(Notesdeselevesseq2 noteseq2) {
        this.noteseq2 = noteseq2;
    }

    public Notesdeselevesseq3 getNoteseq3() {
        return noteseq3;
    }

    public void setNoteseq3(Notesdeselevesseq3 noteseq3) {
        this.noteseq3 = noteseq3;
    }

    public Notesdeselevesseq4 getNoteseq4() {
        return noteseq4;
    }

    public void setNoteseq4(Notesdeselevesseq4 noteseq4) {
        this.noteseq4 = noteseq4;
    }

    public Notesdeselevesseq5 getNoteseq5() {
        return noteseq5;
    }

    public void setNoteseq5(Notesdeselevesseq5 noteseq5) {
        this.noteseq5 = noteseq5;
    }

    public Notesdeselevesseq6 getNoteseq6() {
        return noteseq6;
    }

    public void setNoteseq6(Notesdeselevesseq6 noteseq6) {
        this.noteseq6 = noteseq6;
    }

    public Notesdeselevestrim2 getNotetrim2() {
        return notetrim2;
    }

    public void setNotetrim2(Notesdeselevestrim2 notetrim2) {
        this.notetrim2 = notetrim2;
    }

    public Notesdeselevestrim3 getNotetrim3() {
        return notetrim3;
    }

    public void setNotetrim3(Notesdeselevestrim3 notetrim3) {
        this.notetrim3 = notetrim3;
    }

}
