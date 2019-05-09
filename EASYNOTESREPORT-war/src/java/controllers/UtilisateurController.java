/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Operations;
import entities.Utilisateurs;
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
import sessions.UtilisateursFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "utilisateurController")
@SessionScoped
public class UtilisateurController implements Serializable {

    @EJB
    private UtilisateursFacadeLocal userFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Utilisateurs> userList = new ArrayList<>();
    private Utilisateurs user;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;

    public UtilisateurController() {
    }

    public String users() {
        userList.clear();
        userList.addAll(userFacade.findAll()); 
        user = new Utilisateurs();
        return "utilisateur.xhtml?faces-redirect=true";
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                user = new Utilisateurs();
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

    public String persistUsers() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (userFacade.rechercheUserParLogin(user.getLogin())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        userFacade.create(user);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Ajout");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Ajout de l'utilisateur " + user.getLogin());
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "modifier":
                    userFacade.edit(user);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Modification de l'utilisateur " + user.getLogin());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer": 
                    userFacade.remove(user);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression de l'utilisateur " + user.getLogin());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
        }
        return users();
    }

    public UtilisateursFacadeLocal getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UtilisateursFacadeLocal userFacade) {
        this.userFacade = userFacade;
    }

    public List<Utilisateurs> getUserList() {
        return userList;
    }

    public void setUserList(List<Utilisateurs> userList) {
        this.userList = userList;
    }

    public Utilisateurs getUser() {
        return user;
    }

    public void setUser(Utilisateurs user) {
        this.user = user;
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
