/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Conseildeclasseann;
import entities.Conseildeclassetrim1;
import entities.Conseildeclassetrim2;
import entities.Conseildeclassetrim3;
import entities.Listedeseleves;
import entities.Operations;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import sessions.ConseildeclasseannFacadeLocal;
import sessions.Conseildeclassetrim1FacadeLocal;
import sessions.Conseildeclassetrim2FacadeLocal;
import sessions.Conseildeclassetrim3FacadeLocal;
import sessions.Disciplinesdeselevesseq1FacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "conseilController")
@SessionScoped
public class ConseilController implements Serializable {

    @EJB
    private Disciplinesdeselevesseq1FacadeLocal disciplineseq1Facade;
    @EJB
    private Conseildeclassetrim1FacadeLocal conseiltrim1Facade;
    @EJB
    private Conseildeclassetrim2FacadeLocal conseiltrim2Facade;
    @EJB
    private Conseildeclassetrim3FacadeLocal conseiltrim3Facade;
    @EJB
    private ConseildeclasseannFacadeLocal conseilannFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Conseildeclassetrim1> conseiltrim1List = new ArrayList<>();
    private List<Conseildeclassetrim2> conseiltrim2List = new ArrayList<>();
    private List<Conseildeclassetrim3> conseiltrim3List = new ArrayList<>();
    private List<Conseildeclasseann> conseilannList = new ArrayList<>();
    List<Listedeseleves> listeEleves = new ArrayList<>();
    private Conseildeclassetrim1 conseiltrim1;
    private Conseildeclassetrim2 conseiltrim2;
    private Conseildeclassetrim3 conseiltrim3;
    private Conseildeclasseann conseilann;
    private String seq;
    private String classe;
    private String operation = "add";
    private String pass;
    private boolean TestMsgLogin = false;
    private boolean msgSelect = false;
    private boolean champActif = false;

    public ConseilController() {
    }

    public String managedConseil() {
        return "managedConseil.xhtml?faces-redirect=true";
    }

    public String conseil_didcipline() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = df.format(date);
        try {
            Date dateOperation = df.parse(dateString);
            Operations mouchard = new Operations();
            mouchard.setIdoperations(mouchardFacade.nextId());
            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
            mouchard.setTypeoperation("Saisie Conseil");
            mouchard.setDateoperation(dateOperation);
            mouchard.setDescription("Conseil de classe " + seq + ", " + classe);
            mouchardFacade.create(mouchard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (seq) {
            case "TRIM1":
                return conseiltrime1();
            case "TRIM2":
                return conseiltrime2();
            case "TRIM3":
                return conseiltrime3();
            case "ANN":
                return conseilanne();
        }
        return "";
    }

    public String conseiltrime1() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((conseiltrim1Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = conseiltrim1Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        conseiltrim1 = new Conseildeclassetrim1();
                        conseiltrim1.setListedeseleves(l);
                        conseiltrim1.setMatriculeeleve(l.getMatriculeeleve());
                        conseiltrim1Facade.create(conseiltrim1);
                    }
                }
                conseiltrim1List.clear();
                conseiltrim1List.addAll(conseiltrim1Facade.listeElevesClasse(classe));
                conseiltrim1 = new Conseildeclassetrim1();
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
        return "conseiltrim1.xhtml?faces-redirect=true";
    }

    public String conseiltrime2() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((conseiltrim2Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = conseiltrim2Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        conseiltrim2 = new Conseildeclassetrim2();
                        conseiltrim2.setListedeseleves(l);
                        conseiltrim2.setMatriculeeleve(l.getMatriculeeleve());
                        conseiltrim2Facade.create(conseiltrim2);
                    }
                }
                conseiltrim2List.clear();
                conseiltrim2List.addAll(conseiltrim2Facade.listeElevesClasse(classe));
                conseiltrim2 = new Conseildeclassetrim2();
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
        return "conseiltrim2.xhtml?faces-redirect=true";
    }

    public String conseiltrime3() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((conseiltrim3Facade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = conseiltrim3Facade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        conseiltrim3 = new Conseildeclassetrim3();
                        conseiltrim3.setListedeseleves(l);
                        conseiltrim3.setMatriculeeleve(l.getMatriculeeleve());
                        conseiltrim3Facade.create(conseiltrim3);
                    }
                }
                conseiltrim3List.clear();
                conseiltrim3List.addAll(conseiltrim3Facade.listeElevesClasse(classe));
                conseiltrim3 = new Conseildeclassetrim3();
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
        return "conseiltrim3.xhtml?faces-redirect=true";
    }

    public String conseilanne() {
        if (disciplineseq1Facade.findEnseignatByPwd(pass)) {
            pass = "";
            if (seq != null && classe != null) {
                if ((conseilannFacade.listeDesNouveauxEleves(classe)) != null) {
                    listeEleves = conseilannFacade.listeDesNouveauxEleves(classe);
                    Iterator i = listeEleves.iterator();
                    Listedeseleves l;
                    while (i.hasNext()) {
                        l = (Listedeseleves) i.next();
                        conseilann = new Conseildeclasseann();
                        conseilann.setListedeseleves(l);
                        conseilann.setMatriculeeleve(l.getMatriculeeleve());
                        conseilannFacade.create(conseilann);
                    }
                }
                conseilannList.clear();
                conseilannList.addAll(conseilannFacade.listeElevesClasse(classe));
                conseilann = new Conseildeclasseann();
            } else {
                msgSelect = true;
                return "";
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
        return "conseilann.xhtml?faces-redirect=true";
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "modifier":
                champActif = true;
                break;
            case "consulter":
                champActif = false;
                break;

        }
    }

    public String persistConseiltrim1() {
        try {
            switch (operation) {
                case "modifier":
                    conseiltrim1Facade.edit(conseiltrim1);
                    break;
            }
        } catch (Exception e) {
        }
        return "conseiltrim1.xhtml?faces-redirect=true";
    }

    public String persistConseiltrim2() {
        try {
            switch (operation) {
                case "modifier":
                    conseiltrim2Facade.edit(conseiltrim2);
                    break;
            }
        } catch (Exception e) {
        }
        return "conseiltrim2.xhtml?faces-redirect=true";
    }

    public String persistConseiltrim3() {
        try {
            switch (operation) {
                case "modifier":
                    conseiltrim3Facade.edit(conseiltrim3);
                    break;
            }
        } catch (Exception e) {
        }
        return "conseiltrim3.xhtml?faces-redirect=true";
    }

    public String persistConseilann() {
        try {
            switch (operation) {
                case "modifier":
                    conseilannFacade.edit(conseilann);
                    break;
            }
        } catch (Exception e) {
        }
        return "conseilann.xhtml?faces-redirect=true";
    }

    public Conseildeclassetrim1FacadeLocal getConseiltrim1Facade() {
        return conseiltrim1Facade;
    }

    public void setConseiltrim1Facade(Conseildeclassetrim1FacadeLocal conseiltrim1Facade) {
        this.conseiltrim1Facade = conseiltrim1Facade;
    }

    public List<Conseildeclassetrim1> getConseiltrim1List() {
        return conseiltrim1List;
    }

    public void setConseiltrim1List(List<Conseildeclassetrim1> conseiltrim1List) {
        this.conseiltrim1List = conseiltrim1List;
    }

    public Conseildeclassetrim1 getConseiltrim1() {
        return conseiltrim1;
    }

    public void setConseiltrim1(Conseildeclassetrim1 conseiltrim1) {
        this.conseiltrim1 = conseiltrim1;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public Conseildeclassetrim2FacadeLocal getConseiltrim2Facade() {
        return conseiltrim2Facade;
    }

    public void setConseiltrim2Facade(Conseildeclassetrim2FacadeLocal conseiltrim2Facade) {
        this.conseiltrim2Facade = conseiltrim2Facade;
    }

    public Conseildeclassetrim3FacadeLocal getConseiltrim3Facade() {
        return conseiltrim3Facade;
    }

    public void setConseiltrim3Facade(Conseildeclassetrim3FacadeLocal conseiltrim3Facade) {
        this.conseiltrim3Facade = conseiltrim3Facade;
    }

    public ConseildeclasseannFacadeLocal getConseilannFacade() {
        return conseilannFacade;
    }

    public void setConseilannFacade(ConseildeclasseannFacadeLocal conseilannFacade) {
        this.conseilannFacade = conseilannFacade;
    }

    public Notesdeselevesseq1FacadeLocal getNoteseq1Facade() {
        return noteseq1Facade;
    }

    public void setNoteseq1Facade(Notesdeselevesseq1FacadeLocal noteseq1Facade) {
        this.noteseq1Facade = noteseq1Facade;
    }

    public List<Conseildeclassetrim2> getConseiltrim2List() {
        return conseiltrim2List;
    }

    public void setConseiltrim2List(List<Conseildeclassetrim2> conseiltrim2List) {
        this.conseiltrim2List = conseiltrim2List;
    }

    public List<Conseildeclassetrim3> getConseiltrim3List() {
        return conseiltrim3List;
    }

    public void setConseiltrim3List(List<Conseildeclassetrim3> conseiltrim3List) {
        this.conseiltrim3List = conseiltrim3List;
    }

    public List<Conseildeclasseann> getConseilannList() {
        return conseilannList;
    }

    public void setConseilannList(List<Conseildeclasseann> conseilannList) {
        this.conseilannList = conseilannList;
    }

    public List<Listedeseleves> getListeEleves() {
        return listeEleves;
    }

    public void setListeEleves(List<Listedeseleves> listeEleves) {
        this.listeEleves = listeEleves;
    }

    public Conseildeclassetrim2 getConseiltrim2() {
        return conseiltrim2;
    }

    public void setConseiltrim2(Conseildeclassetrim2 conseiltrim2) {
        this.conseiltrim2 = conseiltrim2;
    }

    public Conseildeclassetrim3 getConseiltrim3() {
        return conseiltrim3;
    }

    public void setConseiltrim3(Conseildeclassetrim3 conseiltrim3) {
        this.conseiltrim3 = conseiltrim3;
    }

    public Conseildeclasseann getConseilann() {
        return conseilann;
    }

    public void setConseilann(Conseildeclasseann conseilann) {
        this.conseilann = conseilann;
    }

    public boolean isChampActif() {
        return champActif;
    }

    public void setChampActif(boolean champActif) {
        this.champActif = champActif;
    }

}
