/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Listepassword;
import entities.Operations;
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
import sessions.ListepasswordFacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "passwordController")
@SessionScoped
public class PasswordController implements Serializable {

    @EJB
    private ListepasswordFacadeLocal pwdFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Listepassword> pwdList = new ArrayList<>();
    private List<Listepassword> pwdListChange = new ArrayList<>();
    private Listepassword pwd;
    private String operation = "add";
    private boolean pwdExisteDeja = false;
    private boolean champActif = true;
    private String matricule;
    private String motDePasse;
    private String motDePasseAchanger;

    public PasswordController() {
    }

    public String passwords() {
        pwdList.clear();
        pwdList.addAll(pwdFacade.findAll());
        pwd = new Listepassword();
        return "password.xhtml?faces-redirect=true";
    }

    public String passwordsChange() {
        pwdListChange.clear();
        pwdListChange.addAll(pwdFacade.findAll());
        pwd = new Listepassword();
        return "changePassword.xhtml?faces-redirect=true";
    }

    public String passwordsChange1() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "modifier":
                    if (pwdFacade.selfpwdExisteDeja(motDePasse,matricule) != null) {
                        pwd.setPassword(motDePasseAchanger);
                        pwdFacade.edit(pwd);
                    } else {
                        pwdExisteDeja = true;
                        return "";
                    }
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Modification du password de " + pwd.getNom() + "(" + pwd.getMatriculepersonne() + ")");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
            pwdList.clear();
            pwdList.addAll(pwdFacade.changerMotDePasse(motDePasseAchanger)); 
            pwd = new Listepassword();
        } catch (Exception e) {
        }
        return "password.xhtml?faces-redirect=true";
    }

    public void desactiverSmsPwdExisteDeje() {
        pwdExisteDeja = false;
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "modifier":
                champActif = true;
                break;
            case "retirer":
                champActif = true;
                break;
            case "visualiser":
                champActif = true;
                break;
        }
    }

    public String persistPassword() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "modifier":
                    if (pwdFacade.pwdExisteDeja(pwd.getPassword()) == null) {
                        pwdFacade.edit(pwd);
                    } else {
                        pwdExisteDeja = true;
                        return "";
                    }
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Modification du password de " + pwd.getNom() + "(" + pwd.getMatriculepersonne() + ")");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "retirer":
                    champActif = true;
                    String mat = pwd.getMatriculepersonne();
                    String specialite = pwd.getSpecialite();
                    String grade = pwd.getGrade();
                    pwd = new Listepassword();
                    pwd.setMatriculepersonne(mat);
                    pwd.setSpecialite(specialite);
                    pwd.setGrade(grade);
                    pwd.setPassword("");
                    pwdFacade.edit(pwd);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression du password de " + pwd.getNom() + "(" + pwd.getMatriculepersonne() + ")");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "visualiser":
                    champActif = true;
                    break;
            }

        } catch (Exception e) {
        }
        return passwords();
    }

    public ListepasswordFacadeLocal getPwdFacade() {
        return pwdFacade;
    }

    public void setPwdFacade(ListepasswordFacadeLocal pwdFacade) {
        this.pwdFacade = pwdFacade;
    }

    public List<Listepassword> getPwdList() {
        return pwdList;
    }

    public void setPwdList(List<Listepassword> pwdList) {
        this.pwdList = pwdList;
    }

    public Listepassword getPwd() {
        return pwd;
    }

    public void setPwd(Listepassword pwd) {
        this.pwd = pwd;
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

    public boolean getPwdExisteDeja() {
        return pwdExisteDeja;
    }

    public void setPwdExisteDeja(boolean pwdExisteDeja) {
        this.pwdExisteDeja = pwdExisteDeja;
    }

    public List<Listepassword> getPwdListChange() {
        return pwdListChange;
    }

    public void setPwdListChange(List<Listepassword> pwdListChange) {
        this.pwdListChange = pwdListChange;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMotDePasseAchanger() {
        return motDePasseAchanger;
    }

    public void setMotDePasseAchanger(String motDePasseAchanger) {
        this.motDePasseAchanger = motDePasseAchanger;
    }

}
