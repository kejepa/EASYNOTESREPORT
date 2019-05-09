/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Operations;
import entities.Specialite;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import sessions.OperationsFacadeLocal;
import sessions.SpecialiteFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "specialiteController")
@SessionScoped
public class SpecialiteController implements Serializable {

    @EJB
    private SpecialiteFacadeLocal specialiteFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Specialite> specialiteList = new ArrayList<>();
    private Specialite specialite;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;

    public SpecialiteController() {
    }

    public String specialites() {
        specialiteList.clear();
        specialiteList.addAll(specialiteFacade.findAll());
        specialite = new Specialite();
        return "specialite.xhtml?faces-redirect=true";
    }

    public List<String> listespecialites() {
        if (specialiteFacade.listeDesSpecialites() != null) {
            return specialiteFacade.listeDesSpecialites();
        } else {
            return null;
        }
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                specialite = new Specialite();
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

    public String persistSpecialite() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (specialiteFacade.rechercheSpecialiteParCode(specialite.getCodespecialite())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        specialiteFacade.create(specialite);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Ajout");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Ajoute de la spécialité " + specialite.getCodespecialite());
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                case "modifier":
                    specialiteFacade.edit(specialite);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("suppretion de la spécialité " + specialite.getCodespecialite());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer":
                    specialiteFacade.remove(specialite);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression de la spécialité " + specialite.getCodespecialite());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialites();
    }

    public SpecialiteFacadeLocal getSpecialiteFacade() {
        return specialiteFacade;
    }

    public void setSpecialiteFacade(SpecialiteFacadeLocal specialiteFacade) {
        this.specialiteFacade = specialiteFacade;
    }

    public List<Specialite> getSpecialiteList() {
        return specialiteList;
    }

    public void setSpecialiteList(List<Specialite> specialiteList) {
        this.specialiteList = specialiteList;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
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
