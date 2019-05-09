/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Listepassword;
import entities.Operations;
import entities.Personnels;
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
import sessions.PersonnelsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "personnelController")
@SessionScoped
public class PersonnelController implements Serializable {

    @EJB
    private PersonnelsFacadeLocal personnelFacade;
    @EJB
    private ListepasswordFacadeLocal passwordFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private Listepassword pwdE;
    private List<Personnels> personnelList = new ArrayList<>();
    private Personnels personnel;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;

    public PersonnelController() {
    }

    public String personnels() {
        personnelList.clear();
        personnelList.addAll(personnelFacade.findAll());
        personnel = new Personnels();
        return "personnel.xhtml?faces-redirect=true";
    }

    public List<Object[]> listeMatieres() {
        List<Object[]> listM = personnelFacade.listedesMatieresParClasse();
        return listM;
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                personnel = new Personnels();
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

    public String persistPersonnels() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (personnelFacade.recherchePersonnelParMatricule(personnel.getMatricule())) {
                        TestMsgLogin = true;
                    } else {
                        pwdE = new Listepassword();
                        pwdE.setMatriculepersonne(personnel.getMatricule());
                        pwdE.setNom(personnel.getNom());
                        pwdE.setPrenom(personnel.getPrenom()); 
                        pwdE.setSpecialite(personnel.getSpecialite());
                        pwdE.setGrade(personnel.getGrade());
                        pwdE.setPassword("pwd");
                        personnelFacade.create(personnel);
                        passwordFacade.create(pwdE);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Ajout");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Ajout du personnel " + personnel.getNom() + " " + personnel.getPrenom() + "(" + personnel.getMatricule() + ")");
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        personnel = new Personnels();
                    }
                    return "";
                case "modifier":
                    pwdE = new Listepassword();
                    pwdE.setMatriculepersonne(personnel.getMatricule());
                    pwdE.setNom(personnel.getNom());
                    pwdE.setPrenom(personnel.getPrenom());
                    pwdE.setSpecialite(personnel.getSpecialite());
                    pwdE.setGrade(personnel.getGrade());
                    if (passwordFacade.findpwd(personnel.getMatricule()) != null) {
                        pwdE.setPassword(passwordFacade.findpwd(personnel.getMatricule()));
                    } else {
                        pwdE.setPassword("pwd");
                    }
                    personnelFacade.edit(personnel);
                    passwordFacade.edit(pwdE);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Modification du personnel " + personnel.getNom() + " " + personnel.getPrenom() + "(" + personnel.getMatricule() + ")");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer":
                    personnelFacade.remove(personnel);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression du personnel " + personnel.getNom() + " " + personnel.getPrenom() + "(" + personnel.getMatricule() + ")");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return personnels();
    }

    public PersonnelsFacadeLocal getPersonnelFacade() {
        return personnelFacade;
    }

    public void setPersonnelFacade(PersonnelsFacadeLocal personnelFacade) {
        this.personnelFacade = personnelFacade;
    }

    public List<Personnels> getPersonnelList() {
        return personnelList;
    }

    public void setPersonnelList(List<Personnels> personnelList) {
        this.personnelList = personnelList;
    }

    public Personnels getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnels personnel) {
        this.personnel = personnel;
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

    public ListepasswordFacadeLocal getPasswordFacade() {
        return passwordFacade;
    }

    public void setPasswordFacade(ListepasswordFacadeLocal passwordFacade) {
        this.passwordFacade = passwordFacade;
    }

    public Listepassword getPwdE() {
        return pwdE;
    }

    public void setPwdE(Listepassword pwdE) {
        this.pwdE = pwdE;
    }

}
