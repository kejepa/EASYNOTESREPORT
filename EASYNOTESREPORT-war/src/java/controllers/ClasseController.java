/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Classes;
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
import sessions.ClassesFacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "classeController")
@SessionScoped
public class ClasseController implements Serializable {

    @EJB
    private ClassesFacadeLocal classeFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Classes> classeList = new ArrayList<>();
    private Classes classe;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;
    private String classeCorrespondante = "";

    public ClasseController() {
    }

    public String classes() {
        classeList.clear();
        classeList.addAll(classeFacade.findAll());
        classe = new Classes();
        return "classe.xhtml?faces-redirect=true";
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                classe = new Classes();
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

    public void choixClasseCorrespondante() {
        String section = classe.getSection();
        String num = classe.getNiveau();
        switch (section) {
            case "FR":
                switch (num) {
                    case "1":
                        classeCorrespondante = "1ère année/6ème";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "2":
                        classeCorrespondante = "2ème année/5ème";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "3":
                        classeCorrespondante = "3ème année/4ème";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "4":
                        classeCorrespondante = "4ème année/3ème";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "5":
                        classeCorrespondante = "5ème année/2nde";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "6":
                        classeCorrespondante = "6ème année/1ère";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "7":
                        classeCorrespondante = "7ème année/Tle";
                        classe.setClasscorresp(classeCorrespondante);
                        break;

                }
                break;
            case "EN":
                switch (num) {
                    case "1":
                        classeCorrespondante = "Form 1";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "2":
                        classeCorrespondante = "Form 2";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "3":
                        classeCorrespondante = "Form 3";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "4":
                        classeCorrespondante = "Form 4";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "5":
                        classeCorrespondante = "Form 5";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "6":
                        classeCorrespondante = "Lower sith";
                        classe.setClasscorresp(classeCorrespondante);
                        break;
                    case "7":
                        classeCorrespondante = "Upper Sith";
                        classe.setClasscorresp(classeCorrespondante);
                        break;

                }
                break;
        }
    }

    public String persistClasses() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (classeFacade.rechercheClasseParCode(classe.getCodeclasse())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        classeFacade.create(classe);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Ajout");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Ajout de " + classe.getCodeclasse());
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "modifier":
                    classeFacade.edit(classe);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Modification des propriétés de " + classe.getCodeclasse());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer":
                    classeFacade.remove(classe);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression de " + classe.getCodeclasse());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
        }
        return classes();
    }

    public ClassesFacadeLocal getClasseFacade() {
        return classeFacade;
    }

    public void setClasseFacade(ClassesFacadeLocal classeFacade) {
        this.classeFacade = classeFacade;
    }

    public List<Classes> getClasseList() {
        return classeList;
    }

    public void setClasseList(List<Classes> classeList) {
        this.classeList = classeList;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
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

    public String getClasseCorrespondante() {
        return classeCorrespondante;
    }

    public void setClasseCorrespondante(String classeCorrespondante) {
        this.classeCorrespondante = classeCorrespondante;
    }

}
