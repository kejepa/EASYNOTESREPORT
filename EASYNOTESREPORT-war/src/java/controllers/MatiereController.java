/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Matieres;
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
import sessions.MatieresFacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "matiereController")
@SessionScoped
public class MatiereController implements Serializable {

    @EJB
    private MatieresFacadeLocal matiereFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Matieres> matiereList = new ArrayList<>();
    private Matieres matiere;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;

    public MatieresFacadeLocal getMatiereFacade() {
        return matiereFacade;
    }

    public String matieres() {
        matiereList.clear();
        matiereList.addAll(matiereFacade.findAll());
        matiere = new Matieres();
        return "matiere.xhtml?faces-redirect=true";
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                matiere = new Matieres();
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

    public String persistMatieres() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (matiereFacade.rechercheMatiereParCode(matiere.getCodematiere())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        matiereFacade.create(matiere);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Ajout");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Ajout de " + matiere.getCodematiere());
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "modifier":
                    matiereFacade.edit(matiere);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Modification de " + matiere.getCodematiere() + " récenment crée");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer":
                    matiereFacade.remove(matiere);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression de " + matiere.getCodematiere() + " récenment crée");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
        }
        return matieres();
    }

    public void setMatiereFacade(MatieresFacadeLocal matiereFacade) {
        this.matiereFacade = matiereFacade;
    }

    public List<Matieres> getMatiereList() {
        return matiereList;
    }

    public void setMatiereList(List<Matieres> matiereList) {
        this.matiereList = matiereList;
    }

    public Matieres getMatiere() {
        return matiere;
    }

    public void setMatiere(Matieres matiere) {
        this.matiere = matiere;
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

    public MatiereController() {
    }

}
