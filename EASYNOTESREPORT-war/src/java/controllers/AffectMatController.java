/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Clamatcoeffgrpe;
import entities.ClamatcoeffgrpePK;
import entities.Classes;
import entities.Matieres;
import entities.Operations;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import sessions.ClamatcoeffgrpeFacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "affectMatController")
@SessionScoped
public class AffectMatController implements Serializable {

    @EJB
    private ClamatcoeffgrpeFacadeLocal affectationFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Clamatcoeffgrpe> affectationMatList = new ArrayList<>();
    private List<Object[]> affectationMatListParClasse = new ArrayList<>();
    private Object object;
    private Clamatcoeffgrpe affectationMat;
    private ClamatcoeffgrpePK affectationMatPk;
    private String operation = "add";
    private boolean champActif = true;
    private String classe;
    private String classeP;
    private boolean TestMsgLogin = false;
    private boolean reussi = false;

    public AffectMatController() {
    }

    public String affectationMatieres() {
        affectationMatList.clear();
        affectationMatList.addAll(affectationFacade.findAll());
        affectationMat = new Clamatcoeffgrpe();
        return "affectationMatiere.xhtml?faces-redirect=true";
    }

    public void affectationMatieresParClasse() {
        affectationMatListParClasse.clear();
        affectationMatListParClasse.addAll(affectationFacade.liteaffactationParClasse(classe));
        object = new Object();
    }

    public void offImage() {
        reussi = false;
    }

    public List<Matieres> listeMatieres() {
        try {
            if (affectationFacade.listedesMatieres() != null) {
                return affectationFacade.listedesMatieres();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<Classes> listeClasses() {
        List<Classes> listC = affectationFacade.listedesClasses();
        return listC;
    }

    public List<Classes> listeClasses_clone() {
        List<Classes> listC = affectationFacade.listedesClasses_clone();
        return listC;
    }

    public String cloneclasse() {
        if (affectationFacade.classeMatiere_clone(classe) != null) {
            for (Object[] uneligne : affectationFacade.classeMatiere_clone(classe)) {
                affectationMat = new Clamatcoeffgrpe();
                ClamatcoeffgrpePK clpk = new ClamatcoeffgrpePK((String) uneligne[0], classeP);
                affectationMat.setClamatcoeffgrpePK(clpk);
                affectationMat.setCoefficient((BigDecimal) uneligne[1]);
                affectationMat.setGroupe((Integer) uneligne[2]);
                affectationFacade.create(affectationMat);
            }
        }
        return affectationMatieres();
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                reussi = false;
                affectationMat = new Clamatcoeffgrpe();
                break;
            case "modifier":
                reussi = false;
                TestMsgLogin = false;
                champActif = true;
                break;
            case "supprimer":
                reussi = false;
                TestMsgLogin = false;
                champActif = true;
                break;
            case "consulter":
                reussi = false;
                TestMsgLogin = false;
                champActif = false;
                break;

        }
    }

    public String persistAffectMat() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (affectationFacade.matiereMatiereClasseExixteDeja(affectationMat.getClasses().toString(), affectationMat.getMatieres().toString())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        affectationMatPk = new ClamatcoeffgrpePK(affectationMat.getMatieres().toString(), affectationMat.getClasses().toString());
                        affectationMat.setClamatcoeffgrpePK(affectationMatPk);
                        affectationFacade.create(affectationMat);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Affectation d'une matière à une classe");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Affectation de " + affectationMat.getClamatcoeffgrpePK().getCodematiere() + " en " + affectationMat.getClamatcoeffgrpePK().getCodeclasse());
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        affectationMat = new Clamatcoeffgrpe();
                    }
                    return "";
                case "modifier":
                    affectationFacade.edit(affectationMat);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Affectation de " + affectationMat.getClamatcoeffgrpePK().getCodematiere() + " en " + affectationMat.getClamatcoeffgrpePK().getCodeclasse());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer":
                    affectationFacade.remove(affectationMat);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression de " + affectationMat.getClamatcoeffgrpePK().getCodematiere() + " affectée en " + affectationMat.getClamatcoeffgrpePK().getCodeclasse());
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
        }
        reussi = true;
        return "";

//                affectationMatieres();
    }

    public ClamatcoeffgrpeFacadeLocal getAffectationFacade() {
        return affectationFacade;
    }

    public void setAffectationFacade(ClamatcoeffgrpeFacadeLocal affectationFacade) {
        this.affectationFacade = affectationFacade;
    }

    public List<Clamatcoeffgrpe> getAffectationMatList() {
        return affectationMatList;
    }

    public void setAffectationMatList(List<Clamatcoeffgrpe> affectationMatList) {
        this.affectationMatList = affectationMatList;
    }

    public Clamatcoeffgrpe getAffectationMat() {
        return affectationMat;
    }

    public void setAffectationMat(Clamatcoeffgrpe affectationMat) {
        this.affectationMat = affectationMat;
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getClasseP() {
        return classeP;
    }

    public void setClasseP(String classeP) {
        this.classeP = classeP;
    }

    public List<Object[]> getAffectationMatListParClasse() {
        return affectationMatListParClasse;
    }

    public void setAffectationMatListParClasse(List<Object[]> affectationMatListParClasse) {
        this.affectationMatListParClasse = affectationMatListParClasse;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isReussi() {
        return reussi;
    }

    public void setReussi(boolean reussi) {
        this.reussi = reussi;
    }

}
