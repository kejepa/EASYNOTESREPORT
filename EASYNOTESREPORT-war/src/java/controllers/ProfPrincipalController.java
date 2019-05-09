/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Operations;
import entities.Profprinc;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import sessions.OperationsFacadeLocal;
import sessions.ProfprincFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "profPrincipalController")
@SessionScoped
public class ProfPrincipalController implements Serializable {

    @EJB
    private ProfprincFacadeLocal ppFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Profprinc> ppList = new ArrayList<>();
    private Profprinc pp;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;

    public ProfPrincipalController() {
    }

    public String pps() {
        ppList.clear();
        ppList.addAll(ppFacade.findAll());
        pp = new Profprinc();
        return "pp.xhtml?faces-redirect=true";
    }

    public List<String> listeProfParClasse() {
        List<String> profL = ppFacade.listeProfClass(pp.getCodeclasse());
        return profL;
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                pp = new Profprinc();
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

    public String persistPps() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    String profprincipal = "";
                    String codeclasse = pp.getCodeclasse();
                    Pattern p1 = Pattern.compile("\\(.*\\)");
                    String s1 = pp.getMatriculeenseignant();
                    Matcher m1 = p1.matcher(s1);
                    while (m1.find()) {
                        profprincipal = (String) m1.group().subSequence(1, m1.group().length() - 1);
                    }
                    String[] decoupage = s1.split("\"");
                    pp = new Profprinc();
                    pp.setMatriculeenseignant(profprincipal);
                    pp.setCodeclasse(codeclasse);
                    pp.setNom(decoupage[0]);
                    ppFacade.create(pp);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Attribution Professeur Principal");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Attribution de la classe " + pp.getCodeclasse() + " à l'Enseignat " + ppFacade.findNomProfPrincipalByMatricule(pp.getMatriculeenseignant()));
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "";
                case "modifier":
                    String ppp = "";
                    String codeclass = pp.getCodeclasse();
                    Pattern p11 = Pattern.compile("\\(.*\\)");
                    String s11 = pp.getMatriculeenseignant();
                    Matcher m11 = p11.matcher(s11);
                    while (m11.find()) {
                        ppp = (String) m11.group().subSequence(1, m11.group().length() - 1);
                    }
                    String[] decoupage2 = s11.split("\"");
                    pp = new Profprinc();
                    pp.setMatriculeenseignant(ppp);
                    pp.setCodeclasse(codeclass);
                    pp.setNom(decoupage2[0]);
                    ppFacade.edit(pp);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Attribution de la classe " + pp.getCodeclasse() + " à l'Enseignat " + ppFacade.findNomProfPrincipalByMatricule(ppp));
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer":
                    ppFacade.remove(pp);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression du titulaire la classe " + pp.getCodeclasse());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
        }
        return pps();
    }

    public ProfprincFacadeLocal getPpFacade() {
        return ppFacade;
    }

    public void setPpFacade(ProfprincFacadeLocal ppFacade) {
        this.ppFacade = ppFacade;
    }

    public List<Profprinc> getPpList() {
        return ppList;
    }

    public void setPpList(List<Profprinc> ppList) {
        this.ppList = ppList;
    }

    public Profprinc getPp() {
        return pp;
    }

    public void setPp(Profprinc pp) {
        this.pp = pp;
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
}
