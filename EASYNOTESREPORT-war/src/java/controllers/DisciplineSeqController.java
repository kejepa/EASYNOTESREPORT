/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Disciplinesdeselevesann;
import entities.Disciplinesdeselevesseq1;
import entities.Disciplinesdeselevesseq2;
import entities.Disciplinesdeselevesseq3;
import entities.Disciplinesdeselevesseq4;
import entities.Disciplinesdeselevesseq5;
import entities.Disciplinesdeselevesseq6;
import entities.Disciplinesdeselevestrim1;
import entities.Disciplinesdeselevestrim2;
import entities.Disciplinesdeselevestrim3;
import entities.Listedeseleves;
import entities.Operations;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import sessions.DisciplinesdeselevesannFacadeLocal;
import sessions.Disciplinesdeselevesseq1FacadeLocal;
import sessions.Disciplinesdeselevesseq2FacadeLocal;
import sessions.Disciplinesdeselevesseq3FacadeLocal;
import sessions.Disciplinesdeselevesseq4FacadeLocal;
import sessions.Disciplinesdeselevesseq5FacadeLocal;
import sessions.Disciplinesdeselevesseq6FacadeLocal;
import sessions.Disciplinesdeselevestrim1FacadeLocal;
import sessions.Disciplinesdeselevestrim2FacadeLocal;
import sessions.Disciplinesdeselevestrim3FacadeLocal;
import sessions.EtablissementinfosFacadeLocal;
import sessions.ListedeselevesFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "disciplineSeqController")
@SessionScoped
public class DisciplineSeqController implements Serializable {

    @EJB
    private EtablissementinfosFacadeLocal etsFacade;
    @EJB
    private ListedeselevesFacadeLocal eleveFacade;
    @EJB
    private Disciplinesdeselevesseq1FacadeLocal disciplineseq1Facade;
    @EJB
    private Disciplinesdeselevesseq2FacadeLocal disciplineseq2Facade;
    @EJB
    private Disciplinesdeselevesseq3FacadeLocal disciplineseq3Facade;
    @EJB
    private Disciplinesdeselevesseq4FacadeLocal disciplineseq4Facade;
    @EJB
    private Disciplinesdeselevesseq5FacadeLocal disciplineseq5Facade;
    @EJB
    private Disciplinesdeselevesseq6FacadeLocal disciplineseq6Facade;
    @EJB
    private Disciplinesdeselevestrim1FacadeLocal disciplinetrim1Facade;
    @EJB
    private Disciplinesdeselevestrim2FacadeLocal disciplinetrim2Facade;
    @EJB
    private Disciplinesdeselevestrim3FacadeLocal disciplinetrim3Facade;
    @EJB
    private DisciplinesdeselevesannFacadeLocal disciplineannFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Disciplinesdeselevesseq1> discilineseq1List = new ArrayList<>();
    private List<Disciplinesdeselevesseq2> discilineseq2List = new ArrayList<>();
    private List<Disciplinesdeselevesseq3> discilineseq3List = new ArrayList<>();
    private List<Disciplinesdeselevesseq4> discilineseq4List = new ArrayList<>();
    private List<Disciplinesdeselevesseq5> discilineseq5List = new ArrayList<>();
    private List<Disciplinesdeselevesseq6> discilineseq6List = new ArrayList<>();
    private List<Disciplinesdeselevestrim1> discilinetrim1List = new ArrayList<>();
    private List<Disciplinesdeselevestrim2> discilinetrim2List = new ArrayList<>();
    private List<Disciplinesdeselevestrim3> discilinetrim3List = new ArrayList<>();
    private List<Disciplinesdeselevesann> discilineannList = new ArrayList<>();
    List<Listedeseleves> listeEleves = new ArrayList<>();
    private Disciplinesdeselevesseq1 disciplineseq1;
    private Disciplinesdeselevesseq2 disciplineseq2;
    private Disciplinesdeselevesseq3 disciplineseq3;
    private Disciplinesdeselevesseq4 disciplineseq4;
    private Disciplinesdeselevesseq5 disciplineseq5;
    private Disciplinesdeselevesseq6 disciplineseq6;
    private Disciplinesdeselevestrim1 disciplinetrim1;
    private Disciplinesdeselevestrim2 disciplinetrim2;
    private Disciplinesdeselevestrim3 disciplinetrim3;
    private Disciplinesdeselevesann disciplineann;
    private Listedeseleves eleve;
    private String classe = null;
    private String pass;
    private String seq;
    private boolean TestMsgLogin = false;
    private boolean msgSelect = false;
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

    public DisciplineSeqController() {
    }

    public String discipline_des_eleves() {
        pouraccueil();
        return "manageddiscipline.xhtml?faces-redirect=true";
    }

    public String didciplines_sequences() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = df.format(date);
        try {
            Date dateOperation = df.parse(dateString);
            Operations mouchard = new Operations();
            mouchard.setIdoperations(mouchardFacade.nextId());
            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
            mouchard.setTypeoperation("Saisie Discipline");
            mouchard.setDateoperation(dateOperation);
            mouchard.setDescription("Saisie Discipline de " + seq + ", " + classe);
            mouchardFacade.create(mouchard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (seq) {
            case "SEQ1":
                return disciplinesequ1();
            case "SEQ2":
                return disciplinesequ2();
            case "SEQ3":
                return disciplinesequ3();
            case "SEQ4":
                return disciplinesequ4();
            case "SEQ5":
                return disciplinesequ5();
            case "SEQ6":
                return disciplinesequ6();
        }
        return "";
    }

    public String vueConsultationDiscipline() {
        pouraccueil();
        return "consulterDisciplineseq.xhtml?faces-redirect=true";
    }

    public String updateDatatableWithAjax() {
        switch (seq) {
            case "SEQ1":
                disciplinesequ1();
                discilineseq1List.clear();
                discilineseq1List.addAll(disciplineseq1Facade.listeElevesClasse(classe));
                disciplineseq1 = new Disciplinesdeselevesseq1();
                return "consulterDisciplineseq1.xhtml?faces-redirect=true";
            case "SEQ2":
                disciplinesequ1();
                disciplinesequ2();
                discilineseq2List.clear();
                discilineseq2List.addAll(disciplineseq2Facade.listeElevesClasse(classe));
                disciplineseq2 = new Disciplinesdeselevesseq2();
                return "consulterDisciplineseq2.xhtml?faces-redirect=true";
            case "SEQ3":
                disciplinesequ3();
                discilineseq3List.clear();
                discilineseq3List.addAll(disciplineseq3Facade.listeElevesClasse(classe));
                disciplineseq3 = new Disciplinesdeselevesseq3();
                return "consulterDisciplineseq3.xhtml?faces-redirect=true";
            case "SEQ4":
                disciplinesequ3();
                disciplinesequ4();
                discilineseq4List.clear();
                discilineseq4List.addAll(disciplineseq4Facade.listeElevesClasse(classe));
                disciplineseq4 = new Disciplinesdeselevesseq4();
                return "consulterDisciplineseq4.xhtml?faces-redirect=true";
            case "SEQ5":
                disciplinesequ5();
                discilineseq5List.clear();
                discilineseq5List.addAll(disciplineseq5Facade.listeElevesClasse(classe));
                disciplineseq5 = new Disciplinesdeselevesseq5();
                return "consulterDisciplineseq5.xhtml?faces-redirect=true";
            case "SEQ6":
                disciplinesequ5();
                disciplinesequ6();
                discilineseq6List.clear();
                discilineseq6List.addAll(disciplineseq6Facade.listeElevesClasse(classe));
                disciplineseq6 = new Disciplinesdeselevesseq6();
                return "consulterDisciplineseq6.xhtml?faces-redirect=true";
            case "TRIM1":
                disciplinesequ1();
                disciplinesequ2();
                for (Object[] uneligne : disciplinetrim1Facade.liesteEleveDiscip1_2(classe)) {
                    int ab = (int) uneligne[1] + (int) uneligne[6];
                    int aj = (int) uneligne[2] + (int) uneligne[7];
                    int an = (int) uneligne[3] + (int) uneligne[8];
                    boolean av = false;
                    boolean bl = false;
                    int co = (int) uneligne[4] + (int) uneligne[9];
                    int ex = (int) uneligne[5] + (int) uneligne[10];
                    BigDecimal aver = etsFacade.borneAvertissement();
                    BigDecimal blam = etsFacade.borneBlame();
                    if (new BigDecimal(an).compareTo(aver) >= 0) {
                        av = true;
                    } else {
                        av = false;
                    }
                    if (new BigDecimal(an).compareTo(blam) >= 0) {
                        bl = true;
                    } else {
                        bl = false;
                    }
                    disciplinetrim1 = new Disciplinesdeselevestrim1();
                    disciplinetrim1.setMatriculeeleve((String) uneligne[0]);
                    disciplinetrim1.setAbsb(ab);
                    disciplinetrim1.setAbsj(aj);
                    disciplinetrim1.setAbsn(an);
                    disciplinetrim1.setAv(av);
                    disciplinetrim1.setBl(bl);
                    disciplinetrim1.setConsigne(co);
                    disciplinetrim1.setExclusion(ex);
                    if (disciplinetrim1Facade.existeEleve((String) uneligne[0])) {
                        disciplinetrim1Facade.edit(disciplinetrim1);
                    } else {
                        disciplinetrim1Facade.create(disciplinetrim1);
                    }
                }
                discilinetrim1List.clear();
                discilinetrim1List.addAll(disciplinetrim1Facade.listeElevesClasse(classe));
                disciplinetrim1 = new Disciplinesdeselevestrim1();
                return "consulterDisciplinetrim1.xhtml?faces-redirect=true";
            case "TRIM2":
                disciplinesequ3();
                disciplinesequ4();
                for (Object[] uneligne : disciplinetrim2Facade.liesteEleveDiscip3_4(classe)) {
                    int ab = (int) uneligne[1] + (int) uneligne[6];
                    int aj = (int) uneligne[2] + (int) uneligne[7];
                    int an = (int) uneligne[3] + (int) uneligne[8];
                    boolean av = false;
                    boolean bl = false;
                    int co = (int) uneligne[4] + (int) uneligne[9];
                    int ex = (int) uneligne[5] + (int) uneligne[10];
                    BigDecimal aver = etsFacade.borneAvertissement();
                    BigDecimal blam = etsFacade.borneBlame();
                    if (new BigDecimal(an).compareTo(aver) >= 0) {
                        av = true;
                    } else {
                        av = false;
                    }
                    if (new BigDecimal(an).compareTo(blam) >= 0) {
                        bl = true;
                    } else {
                        bl = false;
                    }
                    disciplinetrim2 = new Disciplinesdeselevestrim2();
                    disciplinetrim2.setMatriculeeleve((String) uneligne[0]);
                    disciplinetrim2.setAbsb(ab);
                    disciplinetrim2.setAbsj(aj);
                    disciplinetrim2.setAbsn(an);
                    disciplinetrim2.setAv(av);
                    disciplinetrim2.setBl(bl);
                    disciplinetrim2.setConsigne(co);
                    disciplinetrim2.setExclusion(ex);
                    if (disciplinetrim2Facade.existeEleve((String) uneligne[0])) {
                        disciplinetrim2Facade.edit(disciplinetrim2);
                    } else {
                        disciplinetrim2Facade.create(disciplinetrim2);
                    }
                }
                discilinetrim2List.clear();
                discilinetrim2List.addAll(disciplinetrim2Facade.listeElevesClasse(classe));
                disciplinetrim2 = new Disciplinesdeselevestrim2();
                return "consulterDisciplinetrim2.xhtml?faces-redirect=true";
            case "TRIM3":
                disciplinesequ5();
                disciplinesequ6();
                for (Object[] uneligne : disciplinetrim3Facade.liesteEleveDiscip5_6(classe)) {
                    int ab = (int) uneligne[1] + (int) uneligne[6];
                    int aj = (int) uneligne[2] + (int) uneligne[7];
                    int an = (int) uneligne[3] + (int) uneligne[8];
                    boolean av = false;
                    boolean bl = false;
                    int co = (int) uneligne[4] + (int) uneligne[9];
                    int ex = (int) uneligne[5] + (int) uneligne[10];
                    BigDecimal aver = etsFacade.borneAvertissement();
                    BigDecimal blam = etsFacade.borneBlame();
                    if (new BigDecimal(an).compareTo(aver) >= 0) {
                        av = true;
                    } else {
                        av = false;
                    }
                    if (new BigDecimal(an).compareTo(blam) >= 0) {
                        bl = true;
                    } else {
                        bl = false;
                    }
                    disciplinetrim3 = new Disciplinesdeselevestrim3();
                    disciplinetrim3.setMatriculeeleve((String) uneligne[0]);
                    disciplinetrim3.setAbsb(ab);
                    disciplinetrim3.setAbsj(aj);
                    disciplinetrim3.setAbsn(an);
                    disciplinetrim3.setAv(av);
                    disciplinetrim3.setBl(bl);
                    disciplinetrim3.setConsigne(co);
                    disciplinetrim3.setExclusion(ex);
                    if (disciplinetrim3Facade.existeEleve((String) uneligne[0])) {
                        disciplinetrim3Facade.edit(disciplinetrim3);
                    } else {
                        disciplinetrim3Facade.create(disciplinetrim3);
                    }
                }
                discilinetrim3List.clear();
                discilinetrim3List.addAll(disciplinetrim3Facade.listeElevesClasse(classe));
                disciplinetrim3 = new Disciplinesdeselevestrim3();
                return "consulterDisciplinetrim3.xhtml?faces-redirect=true";
            case "ANN":
                disciplinesequ1();
                disciplinesequ2();
                disciplinesequ3();
                disciplinesequ4();
                disciplinesequ5();
                disciplinesequ6();
                for (Object[] uneligne : disciplineannFacade.liesteEleveDiscip1_a_6(classe)) {
                    int ab = (int) uneligne[1] + (int) uneligne[6] + (int) uneligne[11] + (int) uneligne[16] + (int) uneligne[21] + (int) uneligne[26];
                    int aj = (int) uneligne[2] + (int) uneligne[7] + (int) uneligne[12] + (int) uneligne[17] + (int) uneligne[22] + (int) uneligne[27];
                    int an = (int) uneligne[3] + (int) uneligne[8] + (int) uneligne[13] + (int) uneligne[18] + (int) uneligne[23] + (int) uneligne[28];
                    boolean av = false;
                    boolean bl = false;
                    int co = (int) uneligne[4] + (int) uneligne[9] + (int) uneligne[14] + (int) uneligne[19] + (int) uneligne[24] + (int) uneligne[29];
                    int ex = (int) uneligne[5] + (int) uneligne[10] + (int) uneligne[15] + (int) uneligne[20] + (int) uneligne[25] + (int) uneligne[30];
                    BigDecimal aver = etsFacade.borneAvertissement();
                    BigDecimal blam = etsFacade.borneBlame();
                    if (new BigDecimal(an).compareTo(aver) >= 0) {
                        av = true;
                    } else {
                        av = false;
                    }
                    if (new BigDecimal(an).compareTo(blam) >= 0) {
                        bl = true;
                    } else {
                        bl = false;
                    }
                    disciplineann = new Disciplinesdeselevesann();
                    disciplineann.setMatriculeeleve((String) uneligne[0]);
                    disciplineann.setAbsb(ab);
                    disciplineann.setAbsj(aj);
                    disciplineann.setAbsn(an);
                    disciplineann.setAv(av);
                    disciplineann.setBl(bl);
                    disciplineann.setConsigne(co);
                    disciplineann.setExclusion(ex);
                    if (disciplineannFacade.existeEleve((String) uneligne[0])) {
                        disciplineannFacade.edit(disciplineann);
                    } else {
                        disciplineannFacade.create(disciplineann);
                    }
                }
                discilineannList.clear();
                discilineannList.addAll(disciplineannFacade.listeElevesClasse(classe));
                disciplineann = new Disciplinesdeselevesann();
                return "consulterDisciplineann.xhtml?faces-redirect=true";
        }

        return "";
    }

    public String disciplinesequ1() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((disciplineseq1Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineseq1Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineseq1 = new Disciplinesdeselevesseq1();
                        disciplineseq1.setListedeseleves(l);
                        disciplineseq1.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineseq1.setAbsb(0);
                        disciplineseq1.setAbsj(0);
                        disciplineseq1.setAbsn(0);
                        disciplineseq1.setAv(false);
                        disciplineseq1.setBl(false);
                        disciplineseq1.setConsigne(0);
                        disciplineseq1.setExclusion(0);
                        disciplineseq1Facade.create(disciplineseq1);
                    }
                }
                if ((disciplinetrim1Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplinetrim1Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplinetrim1 = new Disciplinesdeselevestrim1();
                        disciplinetrim1.setListedeseleves(l);
                        disciplinetrim1.setMatriculeeleve(l.getMatriculeeleve());
                        disciplinetrim1.setAbsb(0);
                        disciplinetrim1.setAbsj(0);
                        disciplinetrim1.setAbsn(0);
                        disciplinetrim1.setAv(false);
                        disciplinetrim1.setBl(false);
                        disciplinetrim1.setConsigne(0);
                        disciplinetrim1.setExclusion(0);
                        disciplinetrim1Facade.create(disciplinetrim1);
                    }
                }
                if ((disciplineannFacade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineannFacade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineann = new Disciplinesdeselevesann();
                        disciplineann.setListedeseleves(l);
                        disciplineann.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineann.setAbsb(0);
                        disciplineann.setAbsj(0);
                        disciplineann.setAbsn(0);
                        disciplineann.setAv(false);
                        disciplineann.setBl(false);
                        disciplineann.setConsigne(0);
                        disciplineann.setExclusion(0);
                        disciplineannFacade.create(disciplineann);
                    }
                }
                discilineseq1List.clear();
                discilineseq1List.addAll(disciplineseq1Facade.listeElevesClasse(classe));
                disciplineseq1 = new Disciplinesdeselevesseq1();
                return "disciplineseq1.xhtml?faces-redirect=true";
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String disciplinesequ2() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((disciplineseq2Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineseq2Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineseq2 = new Disciplinesdeselevesseq2();
                        disciplineseq2.setListedeseleves(l);
                        disciplineseq2.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineseq2.setAbsb(0);
                        disciplineseq2.setAbsj(0);
                        disciplineseq2.setAbsn(0);
                        disciplineseq2.setAv(false);
                        disciplineseq2.setBl(false);
                        disciplineseq2.setConsigne(0);
                        disciplineseq2.setExclusion(0);
                        disciplineseq2Facade.create(disciplineseq2);
                    }
                }
                if ((disciplinetrim1Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplinetrim1Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplinetrim1 = new Disciplinesdeselevestrim1();
                        disciplinetrim1.setListedeseleves(l);
                        disciplinetrim1.setMatriculeeleve(l.getMatriculeeleve());
                        disciplinetrim1.setAbsb(0);
                        disciplinetrim1.setAbsj(0);
                        disciplinetrim1.setAbsn(0);
                        disciplinetrim1.setAv(false);
                        disciplinetrim1.setBl(false);
                        disciplinetrim1.setConsigne(0);
                        disciplinetrim1.setExclusion(0);
                        disciplinetrim1Facade.create(disciplinetrim1);
                    }
                }
                if ((disciplineannFacade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineannFacade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineann = new Disciplinesdeselevesann();
                        disciplineann.setListedeseleves(l);
                        disciplineann.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineann.setAbsb(0);
                        disciplineann.setAbsj(0);
                        disciplineann.setAbsn(0);
                        disciplineann.setAv(false);
                        disciplineann.setBl(false);
                        disciplineann.setConsigne(0);
                        disciplineann.setExclusion(0);
                        disciplineannFacade.create(disciplineann);
                    }
                }
                discilineseq2List.clear();
                discilineseq2List.addAll(disciplineseq2Facade.listeElevesClasse(classe));
                disciplineseq2 = new Disciplinesdeselevesseq2();
                return "disciplineseq2.xhtml?faces-redirect=true";
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String disciplinesequ3() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((disciplineseq3Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineseq3Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineseq3 = new Disciplinesdeselevesseq3();
                        disciplineseq3.setListedeseleves(l);
                        disciplineseq3.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineseq3.setAbsb(0);
                        disciplineseq3.setAbsj(0);
                        disciplineseq3.setAbsn(0);
                        disciplineseq3.setAv(false);
                        disciplineseq3.setBl(false);
                        disciplineseq3.setConsigne(0);
                        disciplineseq3.setExclusion(0);
                        disciplineseq3Facade.create(disciplineseq3);
                    }
                }
                if ((disciplinetrim2Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplinetrim2Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplinetrim2 = new Disciplinesdeselevestrim2();
                        disciplinetrim2.setListedeseleves(l);
                        disciplinetrim2.setMatriculeeleve(l.getMatriculeeleve());
                        disciplinetrim2.setAbsb(0);
                        disciplinetrim2.setAbsj(0);
                        disciplinetrim2.setAbsn(0);
                        disciplinetrim2.setAv(false);
                        disciplinetrim2.setBl(false);
                        disciplinetrim2.setConsigne(0);
                        disciplinetrim2.setExclusion(0);
                        disciplinetrim2Facade.create(disciplinetrim2);
                    }
                }
                if ((disciplineannFacade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineannFacade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineann = new Disciplinesdeselevesann();
                        disciplineann.setListedeseleves(l);
                        disciplineann.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineann.setAbsb(0);
                        disciplineann.setAbsj(0);
                        disciplineann.setAbsn(0);
                        disciplineann.setAv(false);
                        disciplineann.setBl(false);
                        disciplineann.setConsigne(0);
                        disciplineann.setExclusion(0);
                        disciplineannFacade.create(disciplineann);
                    }
                }
                discilineseq3List.clear();
                discilineseq3List.addAll(disciplineseq3Facade.listeElevesClasse(classe));
                disciplineseq3 = new Disciplinesdeselevesseq3();
                return "disciplineseq3.xhtml?faces-redirect=true";
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String disciplinesequ4() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((disciplineseq4Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineseq4Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineseq4 = new Disciplinesdeselevesseq4();
                        disciplineseq4.setListedeseleves(l);
                        disciplineseq4.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineseq4.setAbsb(0);
                        disciplineseq4.setAbsj(0);
                        disciplineseq4.setAbsn(0);
                        disciplineseq4.setAv(false);
                        disciplineseq4.setBl(false);
                        disciplineseq4.setConsigne(0);
                        disciplineseq4.setExclusion(0);
                        disciplineseq4Facade.create(disciplineseq4);
                    }
                }
                if ((disciplinetrim2Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplinetrim2Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplinetrim2 = new Disciplinesdeselevestrim2();
                        disciplinetrim2.setListedeseleves(l);
                        disciplinetrim2.setMatriculeeleve(l.getMatriculeeleve());
                        disciplinetrim2.setAbsb(0);
                        disciplinetrim2.setAbsj(0);
                        disciplinetrim2.setAbsn(0);
                        disciplinetrim2.setAv(false);
                        disciplinetrim2.setBl(false);
                        disciplinetrim2.setConsigne(0);
                        disciplinetrim2.setExclusion(0);
                        disciplinetrim2Facade.create(disciplinetrim2);
                    }
                }
                if ((disciplineannFacade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineannFacade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineann = new Disciplinesdeselevesann();
                        disciplineann.setListedeseleves(l);
                        disciplineann.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineann.setAbsb(0);
                        disciplineann.setAbsj(0);
                        disciplineann.setAbsn(0);
                        disciplineann.setAv(false);
                        disciplineann.setBl(false);
                        disciplineann.setConsigne(0);
                        disciplineann.setExclusion(0);
                        disciplineannFacade.create(disciplineann);
                    }
                }
                discilineseq4List.clear();
                discilineseq4List.addAll(disciplineseq4Facade.listeElevesClasse(classe));
                disciplineseq4 = new Disciplinesdeselevesseq4();
                return "disciplineseq4.xhtml?faces-redirect=true";
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String disciplinesequ5() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((disciplineseq5Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineseq5Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineseq5 = new Disciplinesdeselevesseq5();
                        disciplineseq5.setListedeseleves(l);
                        disciplineseq5.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineseq5.setAbsb(0);
                        disciplineseq5.setAbsj(0);
                        disciplineseq5.setAbsn(0);
                        disciplineseq5.setAv(false);
                        disciplineseq5.setBl(false);
                        disciplineseq5.setConsigne(0);
                        disciplineseq5.setExclusion(0);
                        disciplineseq5Facade.create(disciplineseq5);
                    }
                }
                if ((disciplinetrim3Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplinetrim3Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplinetrim3 = new Disciplinesdeselevestrim3();
                        disciplinetrim3.setListedeseleves(l);
                        disciplinetrim3.setMatriculeeleve(l.getMatriculeeleve());
                        disciplinetrim3.setAbsb(0);
                        disciplinetrim3.setAbsj(0);
                        disciplinetrim3.setAbsn(0);
                        disciplinetrim3.setAv(false);
                        disciplinetrim3.setBl(false);
                        disciplinetrim3.setConsigne(0);
                        disciplinetrim3.setExclusion(0);
                        disciplinetrim3Facade.create(disciplinetrim3);
                    }
                }
                if ((disciplineannFacade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineannFacade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineann = new Disciplinesdeselevesann();
                        disciplineann.setListedeseleves(l);
                        disciplineann.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineann.setAbsb(0);
                        disciplineann.setAbsj(0);
                        disciplineann.setAbsn(0);
                        disciplineann.setAv(false);
                        disciplineann.setBl(false);
                        disciplineann.setConsigne(0);
                        disciplineann.setExclusion(0);
                        disciplineannFacade.create(disciplineann);
                    }
                }
                discilineseq5List.clear();
                discilineseq5List.addAll(disciplineseq5Facade.listeElevesClasse(classe));
                disciplineseq5 = new Disciplinesdeselevesseq5();
                return "disciplineseq5.xhtml?faces-redirect=true";
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
    }

    public String disciplinesequ6() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((disciplineseq6Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineseq6Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineseq6 = new Disciplinesdeselevesseq6();
                        disciplineseq6.setListedeseleves(l);
                        disciplineseq6.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineseq6.setAbsb(0);
                        disciplineseq6.setAbsj(0);
                        disciplineseq6.setAbsn(0);
                        disciplineseq6.setAv(false);
                        disciplineseq6.setBl(false);
                        disciplineseq6.setConsigne(0);
                        disciplineseq6.setExclusion(0);
                        disciplineseq6Facade.create(disciplineseq6);
                    }
                }
                if ((disciplinetrim3Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplinetrim3Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplinetrim3 = new Disciplinesdeselevestrim3();
                        disciplinetrim3.setListedeseleves(l);
                        disciplinetrim3.setMatriculeeleve(l.getMatriculeeleve());
                        disciplinetrim3.setAbsb(0);
                        disciplinetrim3.setAbsj(0);
                        disciplinetrim3.setAbsn(0);
                        disciplinetrim3.setAv(false);
                        disciplinetrim3.setBl(false);
                        disciplinetrim3.setConsigne(0);
                        disciplinetrim3.setExclusion(0);
                        disciplinetrim3Facade.create(disciplinetrim3);
                    }
                }
                if ((disciplineannFacade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = disciplineannFacade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        disciplineann = new Disciplinesdeselevesann();
                        disciplineann.setListedeseleves(l);
                        disciplineann.setMatriculeeleve(l.getMatriculeeleve());
                        disciplineann.setAbsb(0);
                        disciplineann.setAbsj(0);
                        disciplineann.setAbsn(0);
                        disciplineann.setAv(false);
                        disciplineann.setBl(false);
                        disciplineann.setConsigne(0);
                        disciplineann.setExclusion(0);
                        disciplineannFacade.create(disciplineann);
                    }
                }
                discilineseq6List.clear();
                discilineseq6List.addAll(disciplineseq6Facade.listeElevesClasse(classe));
                disciplineseq6 = new Disciplinesdeselevesseq6();
                return "disciplineseq6.xhtml?faces-redirect=true";
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
            Disciplinesdeselevesseq1 uneeleve = (Disciplinesdeselevesseq1) table.getRowData();
            disciplineseq1 = new Disciplinesdeselevesseq1();
            disciplineseq1.setAbsb(uneeleve.getAbsb());
            disciplineseq1.setAbsj(uneeleve.getAbsj());
            disciplineseq1.setAbsn(uneeleve.getAbsb() - uneeleve.getAbsj());
            if (uneeleve.getAbsb() == 0) {
                disciplineseq1.setAbsb(0);
                disciplineseq1.setAbsj(0);
                disciplineseq1.setAbsn(0);
            }
            disciplineseq1.setConsigne(uneeleve.getConsigne());
            disciplineseq1.setExclusion(uneeleve.getExclusion());
            disciplineseq1.setMatriculeeleve(uneeleve.getMatriculeeleve());
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if ((new BigDecimal(disciplineseq1.getAbsn())).compareTo(aver) >= 0) {
                disciplineseq1.setAv(true);
            } else {
                disciplineseq1.setAv(false);
            }
            if ((new BigDecimal(disciplineseq1.getAbsn())).compareTo(blam) >= 0) {
                disciplineseq1.setBl(true);
            } else {
                disciplineseq1.setBl(false);
            }
            disciplineseq1Facade.edit(disciplineseq1);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit2(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Disciplinesdeselevesseq2 uneeleve = (Disciplinesdeselevesseq2) table.getRowData();
            disciplineseq2 = new Disciplinesdeselevesseq2();
            disciplineseq2.setAbsb(uneeleve.getAbsb());
            disciplineseq2.setAbsj(uneeleve.getAbsj());
            disciplineseq2.setAbsn(uneeleve.getAbsb() - uneeleve.getAbsj());
            if (uneeleve.getAbsb() == 0) {
                disciplineseq2.setAbsb(0);
                disciplineseq2.setAbsj(0);
                disciplineseq2.setAbsn(0);
            }
            disciplineseq2.setConsigne(uneeleve.getConsigne());
            disciplineseq2.setExclusion(uneeleve.getExclusion());
            disciplineseq2.setMatriculeeleve(uneeleve.getMatriculeeleve());
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if ((new BigDecimal(disciplineseq2.getAbsn())).compareTo(aver) >= 0) {
                disciplineseq2.setAv(true);
            } else {
                disciplineseq2.setAv(false);
            }
            if ((new BigDecimal(disciplineseq2.getAbsn())).compareTo(blam) >= 0) {
                disciplineseq2.setBl(true);
            } else {
                disciplineseq2.setBl(false);
            }
            disciplineseq2Facade.edit(disciplineseq2);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit3(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Disciplinesdeselevesseq3 uneeleve = (Disciplinesdeselevesseq3) table.getRowData();
            disciplineseq3 = new Disciplinesdeselevesseq3();
            disciplineseq3.setAbsb(uneeleve.getAbsb());
            disciplineseq3.setAbsj(uneeleve.getAbsj());
            disciplineseq3.setAbsn(uneeleve.getAbsb() - uneeleve.getAbsj());
            if (uneeleve.getAbsb() == 0) {
                disciplineseq3.setAbsb(0);
                disciplineseq3.setAbsj(0);
                disciplineseq3.setAbsn(0);
            }
            disciplineseq3.setConsigne(uneeleve.getConsigne());
            disciplineseq3.setExclusion(uneeleve.getExclusion());
            disciplineseq3.setMatriculeeleve(uneeleve.getMatriculeeleve());
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if ((new BigDecimal(disciplineseq3.getAbsn())).compareTo(aver) >= 0) {
                disciplineseq3.setAv(true);
            } else {
                disciplineseq3.setAv(false);
            }
            if ((new BigDecimal(disciplineseq3.getAbsn())).compareTo(blam) >= 0) {
                disciplineseq3.setBl(true);
            } else {
                disciplineseq3.setBl(false);
            }
            disciplineseq3Facade.edit(disciplineseq3);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit4(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Disciplinesdeselevesseq4 uneeleve = (Disciplinesdeselevesseq4) table.getRowData();
            disciplineseq4 = new Disciplinesdeselevesseq4();
            disciplineseq4.setAbsb(uneeleve.getAbsb());
            disciplineseq4.setAbsj(uneeleve.getAbsj());
            disciplineseq4.setAbsn(uneeleve.getAbsb() - uneeleve.getAbsj());
            if (uneeleve.getAbsb() == 0) {
                disciplineseq4.setAbsb(0);
                disciplineseq4.setAbsj(0);
                disciplineseq4.setAbsn(0);
            }
            disciplineseq4.setConsigne(uneeleve.getConsigne());
            disciplineseq4.setExclusion(uneeleve.getExclusion());
            disciplineseq4.setMatriculeeleve(uneeleve.getMatriculeeleve());
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if ((new BigDecimal(disciplineseq4.getAbsn())).compareTo(aver) >= 0) {
                disciplineseq4.setAv(true);
            } else {
                disciplineseq4.setAv(false);
            }
            if ((new BigDecimal(disciplineseq4.getAbsn())).compareTo(blam) >= 0) {
                disciplineseq4.setBl(true);
            } else {
                disciplineseq4.setBl(false);
            }
            disciplineseq4Facade.edit(disciplineseq4);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit5(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Disciplinesdeselevesseq5 uneeleve = (Disciplinesdeselevesseq5) table.getRowData();
            disciplineseq5 = new Disciplinesdeselevesseq5();
            disciplineseq5.setAbsb(uneeleve.getAbsb());
            disciplineseq5.setAbsj(uneeleve.getAbsj());
            disciplineseq5.setAbsn(uneeleve.getAbsb() - uneeleve.getAbsj());
            if (uneeleve.getAbsb() == 0) {
                disciplineseq5.setAbsb(0);
                disciplineseq5.setAbsj(0);
                disciplineseq5.setAbsn(0);
            }
            disciplineseq5.setConsigne(uneeleve.getConsigne());
            disciplineseq5.setExclusion(uneeleve.getExclusion());
            disciplineseq5.setMatriculeeleve(uneeleve.getMatriculeeleve());
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if ((new BigDecimal(disciplineseq5.getAbsn())).compareTo(aver) >= 0) {
                disciplineseq5.setAv(true);
            } else {
                disciplineseq5.setAv(false);
            }
            if ((new BigDecimal(disciplineseq5.getAbsn())).compareTo(blam) >= 0) {
                disciplineseq5.setBl(true);
            } else {
                disciplineseq5.setBl(false);
            }
            disciplineseq5Facade.edit(disciplineseq5);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit6(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Disciplinesdeselevesseq6 uneeleve = (Disciplinesdeselevesseq6) table.getRowData();
            disciplineseq6 = new Disciplinesdeselevesseq6();
            disciplineseq6.setAbsb(uneeleve.getAbsb());
            disciplineseq6.setAbsj(uneeleve.getAbsj());
            disciplineseq6.setAbsn(uneeleve.getAbsb() - uneeleve.getAbsj());
            if (uneeleve.getAbsb() == 0) {
                disciplineseq6.setAbsb(0);
                disciplineseq6.setAbsj(0);
                disciplineseq6.setAbsn(0);
            }
            disciplineseq6.setConsigne(uneeleve.getConsigne());
            disciplineseq6.setExclusion(uneeleve.getExclusion());
            disciplineseq6.setMatriculeeleve(uneeleve.getMatriculeeleve());
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if ((new BigDecimal(disciplineseq6.getAbsn())).compareTo(aver) >= 0) {
                disciplineseq6.setAv(true);
            } else {
                disciplineseq6.setAv(false);
            }
            if ((new BigDecimal(disciplineseq6.getAbsn())).compareTo(blam) >= 0) {
                disciplineseq6.setBl(true);
            } else {
                disciplineseq6.setBl(false);
            }
            disciplineseq6Facade.edit(disciplineseq6);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void pouraccueil() {
        BigDecimal valeurBigDecimal = etsFacade.acti_deative_seq();
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

    public List<Disciplinesdeselevesseq1> getDiscilineseq1List() {
        return discilineseq1List;
    }

    public void setDiscilineseq1List(List<Disciplinesdeselevesseq1> discilineseq1List) {
        this.discilineseq1List = discilineseq1List;
    }

    public List<Disciplinesdeselevesseq2> getDiscilineseq2List() {
        return discilineseq2List;
    }

    public void setDiscilineseq2List(List<Disciplinesdeselevesseq2> discilineseq2List) {
        this.discilineseq2List = discilineseq2List;
    }

    public List<Disciplinesdeselevesseq3> getDiscilineseq3List() {
        return discilineseq3List;
    }

    public void setDiscilineseq3List(List<Disciplinesdeselevesseq3> discilineseq3List) {
        this.discilineseq3List = discilineseq3List;
    }

    public List<Disciplinesdeselevesseq4> getDiscilineseq4List() {
        return discilineseq4List;
    }

    public void setDiscilineseq4List(List<Disciplinesdeselevesseq4> discilineseq4List) {
        this.discilineseq4List = discilineseq4List;
    }

    public List<Disciplinesdeselevesseq5> getDiscilineseq5List() {
        return discilineseq5List;
    }

    public void setDiscilineseq5List(List<Disciplinesdeselevesseq5> discilineseq5List) {
        this.discilineseq5List = discilineseq5List;
    }

    public List<Disciplinesdeselevesseq6> getDiscilineseq6List() {
        return discilineseq6List;
    }

    public void setDiscilineseq6List(List<Disciplinesdeselevesseq6> discilineseq6List) {
        this.discilineseq6List = discilineseq6List;
    }

    public List<Disciplinesdeselevestrim1> getDiscilinetrim1List() {
        return discilinetrim1List;
    }

    public void setDiscilinetrim1List(List<Disciplinesdeselevestrim1> discilinetrim1List) {
        this.discilinetrim1List = discilinetrim1List;
    }

    public List<Disciplinesdeselevestrim2> getDiscilinetrim2List() {
        return discilinetrim2List;
    }

    public void setDiscilinetrim2List(List<Disciplinesdeselevestrim2> discilinetrim2List) {
        this.discilinetrim2List = discilinetrim2List;
    }

    public List<Disciplinesdeselevestrim3> getDiscilinetrim3List() {
        return discilinetrim3List;
    }

    public void setDiscilinetrim3List(List<Disciplinesdeselevestrim3> discilinetrim3List) {
        this.discilinetrim3List = discilinetrim3List;
    }

    public List<Disciplinesdeselevesann> getDiscilineannList() {
        return discilineannList;
    }

    public void setDiscilineannList(List<Disciplinesdeselevesann> discilineannList) {
        this.discilineannList = discilineannList;
    }

    public Disciplinesdeselevesann getDisciplineann() {
        return disciplineann;
    }

    public void setDisciplineann(Disciplinesdeselevesann disciplineann) {
        this.disciplineann = disciplineann;
    }

    public Disciplinesdeselevesseq1 getDisciplineseq1() {
        return disciplineseq1;
    }

    public void setDisciplineseq1(Disciplinesdeselevesseq1 disciplineseq1) {
        this.disciplineseq1 = disciplineseq1;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Listedeseleves> getListeEleves() {
        return listeEleves;
    }

    public void setListeEleves(List<Listedeseleves> listeEleves) {
        this.listeEleves = listeEleves;
    }

    public boolean isTestMsgLogin() {
        return TestMsgLogin;
    }

    public void setTestMsgLogin(boolean TestMsgLogin) {
        this.TestMsgLogin = TestMsgLogin;
    }

    public boolean isMsgSelect() {
        return msgSelect;
    }

    public void setMsgSelect(boolean msgSelect) {
        this.msgSelect = msgSelect;
    }

    public Disciplinesdeselevesseq2 getDisciplineseq2() {
        return disciplineseq2;
    }

    public void setDisciplineseq2(Disciplinesdeselevesseq2 disciplineseq2) {
        this.disciplineseq2 = disciplineseq2;
    }

    public Disciplinesdeselevesseq3 getDisciplineseq3() {
        return disciplineseq3;
    }

    public void setDisciplineseq3(Disciplinesdeselevesseq3 disciplineseq3) {
        this.disciplineseq3 = disciplineseq3;
    }

    public Disciplinesdeselevesseq4 getDisciplineseq4() {
        return disciplineseq4;
    }

    public void setDisciplineseq4(Disciplinesdeselevesseq4 disciplineseq4) {
        this.disciplineseq4 = disciplineseq4;
    }

    public Disciplinesdeselevesseq5 getDisciplineseq5() {
        return disciplineseq5;
    }

    public void setDisciplineseq5(Disciplinesdeselevesseq5 disciplineseq5) {
        this.disciplineseq5 = disciplineseq5;
    }

    public Disciplinesdeselevesseq6 getDisciplineseq6() {
        return disciplineseq6;
    }

    public void setDisciplineseq6(Disciplinesdeselevesseq6 disciplineseq6) {
        this.disciplineseq6 = disciplineseq6;
    }

    public Disciplinesdeselevestrim1 getDisciplinetrim1() {
        return disciplinetrim1;
    }

    public void setDisciplinetrim1(Disciplinesdeselevestrim1 disciplinetrim1) {
        this.disciplinetrim1 = disciplinetrim1;
    }

    public Disciplinesdeselevestrim2 getDisciplinetrim2() {
        return disciplinetrim2;
    }

    public void setDisciplinetrim2(Disciplinesdeselevestrim2 disciplinetrim2) {
        this.disciplinetrim2 = disciplinetrim2;
    }

    public Disciplinesdeselevestrim3 getDisciplinetrim3() {
        return disciplinetrim3;
    }

    public void setDisciplinetrim3(Disciplinesdeselevestrim3 disciplinetrim3) {
        this.disciplinetrim3 = disciplinetrim3;
    }

}
