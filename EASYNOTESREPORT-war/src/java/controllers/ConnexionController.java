/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Operations;
import entities.Utilisateurs;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.OperationsFacadeLocal;
import sessions.UtilisateursFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@ManagedBean(name = "connexionController")
@SessionScoped
public class ConnexionController implements Serializable {

    @EJB
    private UtilisateursFacadeLocal utilisateurFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Utilisateurs> userList = new ArrayList<>();
    private Utilisateurs user = new Utilisateurs();
    private String msg = "";
    private boolean islogged = false;
    private boolean testConnection = false;
    private String matricule = "Inconnu";
    public static String nom = "Inconnu";
    private List<String> matriculeList;
    private String currentUser;
    private boolean typerUser = true;
    private boolean resp = true;
    private boolean resd = true;
    private boolean prof = true;
//    private boolean User = true;

    public ConnexionController() {
    }

    public String saisienotes() {
        return "saisienotes.xhtml?faces-redirect=true";
    }

    public String connexion() {
        if (utilisateurFacade.findByLoginPwd(user.getLogin(), user.getPassword())) {
            this.islogged = true;
            testConnection = false;
            nom = utilisateurFacade.findNomByLoginPwd(user.getLogin(), user.getPassword());
            String roleUser = mouchardFacade.findRoleByLogin(user.getLogin());
            switch (roleUser) {
                case "resp":
                    resp = true;
                    resd = false;
                    typerUser = false;
                    break;
                case "resd":
                    resd = true;
                    typerUser = false;
                    break;
                case "prof":
                    typerUser = false;
                    resd = false;
                    resp = false;
                    break;
            }
            user = new Utilisateurs();

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            try {
                Date dateOperation = df.parse(dateString);
                Operations mouchard = new Operations();
                mouchard.setIdoperations(mouchardFacade.nextId());
                mouchard.setLogin(mouchardFacade.userByString(nom));
                mouchard.setTypeoperation("Connexion");
                mouchard.setDateoperation(dateOperation);
                mouchard.setDescription("Connexion de l'utilisateur " + nom);
                mouchardFacade.create(mouchard);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "accueil.xhtml?faces-redirect=true";
        } else {
//            testConnection = !(user.getLoginuser() == null || user.getLoginuser().equals(""));
//            testConnection = !(user.getPassworduser() == null || user.getPassworduser().equals(""));
        }
//        user=new Utilisateur();
//        RequestContext.getCurrentInstance().update("growl");
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Login ou Mot de Passe Incorrect!!"));
        return "index.xhtml?faces-redirect=true";
    }

    public String deconnexion() {
        nom = "Inconnu";
        typerUser = true;
        resd = true;
        resp = true;
        prof = true;
        return "index.xhtml?faces-redirect=true";
    }

    public UtilisateursFacadeLocal getUtilisateurFacade() {
        return utilisateurFacade;
    }

    public void setUtilisateurFacade(UtilisateursFacadeLocal utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isTestConnection() {
        return testConnection;
    }

    public void setTestConnection(boolean testConnection) {
        this.testConnection = testConnection;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getMatriculeList() {
        return matriculeList;
    }

    public void setMatriculeList(List<String> matriculeList) {
        this.matriculeList = matriculeList;
    }

    public OperationsFacadeLocal getMouchardFacade() {
        return mouchardFacade;
    }

    public void setMouchardFacade(OperationsFacadeLocal mouchardFacade) {
        this.mouchardFacade = mouchardFacade;
    }

    public boolean isTyperUser() {
        return typerUser;
    }

    public void setTyperUser(boolean typerUser) {
        this.typerUser = typerUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isIslogged() {
        return islogged;
    }

    public void setIslogged(boolean islogged) {
        this.islogged = islogged;
    }

    public boolean isResp() {
        return resp;
    }

    public void setResp(boolean resp) {
        this.resp = resp;
    }

    public boolean isResd() {
        return resd;
    }

    public void setResd(boolean resd) {
        this.resd = resd;
    }

    public boolean isProf() {
        return prof;
    }

    public void setProf(boolean prof) {
        this.prof = prof;
    }
}
