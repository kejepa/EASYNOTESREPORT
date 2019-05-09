/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Classes;
import entities.Ensgclamat;
import entities.EnsgclamatPK;
import entities.Matieres;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import sessions.EnsgclamatFacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "affectationEnseigClassController")
@SessionScoped
public class AffectationEnseigClassController implements Serializable {

    @EJB
    private EnsgclamatFacadeLocal affecEnseigFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Ensgclamat> affectEnseigList = new ArrayList<>();
    private Ensgclamat affecEnseigClass;
    private EnsgclamatPK enspk;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;
    private String matricule;

    public AffectationEnseigClassController() {
    }

    public String affectationEnseiClasses() {
        affectEnseigList.clear();
        affectEnseigList.addAll(affecEnseigFacade.findAll());
        affecEnseigClass = new Ensgclamat();
        return "affectationEnseigClasse.xhtml?faces-redirect=true";
    }

    public List<Matieres> listeMatieres() {
        try {
            if (affecEnseigFacade.listedesMatieres(affecEnseigClass.getClasses().toString()) != null) {
                return affecEnseigFacade.listedesMatieres(affecEnseigClass.getClasses().toString());
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<Classes> listeClasses() {
        List<Classes> listC = affecEnseigFacade.listedesClasses();
        return listC;
    }

    public void matriculeRegex() {
        Pattern p1 = Pattern.compile("\\(.*\\)");
        String s1 = affecEnseigClass.getMatriculeenseignant();
        Matcher m1 = p1.matcher(s1);
        while (m1.find()) {
            matricule = (String) m1.group().subSequence(1, m1.group().length() - 1);
        }
    }

    public List<String> listePersonnels() {
        try {
            if (affecEnseigFacade.listedesPersonnels(affecEnseigClass.getMatieres().toString()) != null) {
                return affecEnseigFacade.listedesPersonnels(affecEnseigClass.getMatieres().toString());
            } else {
                return null;
            }
        } catch (Exception e) {
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
                affecEnseigClass = new Ensgclamat();
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

    public String persistEnseiAffectClasse() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (affecEnseigFacade.matiereEnseignatClasseExixteDeja(affecEnseigClass.getClasses().toString(), affecEnseigClass.getMatieres().toString())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        enspk = new EnsgclamatPK(affecEnseigClass.getClasses().toString(), affecEnseigClass.getMatieres().toString());
                        affecEnseigClass.setEnsgclamatPK(enspk);
                        //
                        Pattern p1 = Pattern.compile("\\(.*\\)");
                        String s1 = affecEnseigClass.getMatriculeenseignant();
                        Matcher m1 = p1.matcher(s1);
                        while (m1.find()) {
                            matricule = (String) m1.group().subSequence(1, m1.group().length() - 1);
                        }
                        String[] decoupage = s1.split("\\(");
                        affecEnseigClass.setNom(decoupage[0]);
                        affecEnseigClass.setMatriculeenseignant(matricule);
                        //
                        affecEnseigFacade.create(affecEnseigClass);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Affectation");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Affectation de l'enseignat " + affecEnseigFacade.rechercheEnseignantParMatricule(matricule) + " en " + affecEnseigClass.getEnsgclamatPK().getCodeclasse());
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        affecEnseigClass = new Ensgclamat();
                    }
                    return "";
                case "modifier":
                    if (affecEnseigFacade.matiereEnseignatClasseExixteDeja(affecEnseigClass.getClasses().toString(), affecEnseigClass.getMatieres().toString())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        enspk = new EnsgclamatPK(affecEnseigClass.getClasses().toString(), affecEnseigClass.getMatieres().toString());
                        affecEnseigClass.setEnsgclamatPK(enspk);
                        //
                        Pattern p1 = Pattern.compile("\\(.*\\)");
                        String s1 = affecEnseigClass.getMatriculeenseignant();
                        Matcher m1 = p1.matcher(s1);
                        while (m1.find()) {
                            matricule = (String) m1.group().subSequence(1, m1.group().length() - 1);
                        }
                        //
                        String[] decoupage = s1.split("\\("); 
                        affecEnseigClass.setNom(decoupage[0]);
                        affecEnseigClass.setMatriculeenseignant(matricule);
                        affecEnseigFacade.edit(affecEnseigClass);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Modification");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Affectation de l'enseignat " + affecEnseigFacade.rechercheEnseignantParMatricule(matricule) + " en " + affecEnseigClass.getEnsgclamatPK().getCodeclasse());
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "supprimer":
                    affecEnseigFacade.remove(affecEnseigClass);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression de l'affectation de " + affecEnseigFacade.rechercheEnseignantParMatricule(matricule) + " affecté en " + affecEnseigClass.getEnsgclamatPK().getCodeclasse());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
        }
        return affectationEnseiClasses();
    }

    public EnsgclamatFacadeLocal getAffecEnseigFacade() {
        return affecEnseigFacade;
    }

    public void setAffecEnseigFacade(EnsgclamatFacadeLocal affecEnseigFacade) {
        this.affecEnseigFacade = affecEnseigFacade;
    }

    public List<Ensgclamat> getAffectEnseigList() {
        return affectEnseigList;
    }

    public void setAffectEnseigList(List<Ensgclamat> affectEnseigList) {
        this.affectEnseigList = affectEnseigList;
    }

    public Ensgclamat getAffecEnseigClass() {
        return affecEnseigClass;
    }

    public void setAffecEnseigClass(Ensgclamat affecEnseigClass) {
        this.affecEnseigClass = affecEnseigClass;
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
