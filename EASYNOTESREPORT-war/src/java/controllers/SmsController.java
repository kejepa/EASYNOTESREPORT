/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Operations;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import sessions.AnnmoyennesrangsdeselevesFacadeLocal;
import sessions.ListedeselevesFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;
import sessions.OperationsFacadeLocal;
import sessions.Seq1moyennesrangsdeselevesFacadeLocal;
import sessions.Seq2moyennesrangsdeselevesFacadeLocal;
import sessions.Seq3moyennesrangsdeselevesFacadeLocal;
import sessions.Seq4moyennesrangsdeselevesFacadeLocal;
import sessions.Seq5moyennesrangsdeselevesFacadeLocal;
import sessions.Seq6moyennesrangsdeselevesFacadeLocal;
import sessions.Trim1moyennesrangsdeselevesFacadeLocal;
import sessions.Trim2moyennesrangsdeselevesFacadeLocal;
import sessions.Trim3moyennesrangsdeselevesFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "smsController")
@SessionScoped
public class SmsController implements Serializable {

    @EJB
    private ListedeselevesFacadeLocal eleveFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    @EJB
    private Seq1moyennesrangsdeselevesFacadeLocal seq1Facade;
    @EJB
    private Seq2moyennesrangsdeselevesFacadeLocal seq2Facade;
    @EJB
    private Seq3moyennesrangsdeselevesFacadeLocal seq3Facade;
    @EJB
    private Seq4moyennesrangsdeselevesFacadeLocal seq4Facade;
    @EJB
    private Seq5moyennesrangsdeselevesFacadeLocal seq5Facade;
    @EJB
    private Seq6moyennesrangsdeselevesFacadeLocal seq6Facade;
    @EJB
    private Trim1moyennesrangsdeselevesFacadeLocal trim1Facade;
    @EJB
    private Trim2moyennesrangsdeselevesFacadeLocal trim2Facade;
    @EJB
    private Trim3moyennesrangsdeselevesFacadeLocal trim3Facade;
    @EJB
    private AnnmoyennesrangsdeselevesFacadeLocal annFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private boolean champEleve = false;
    private boolean labeleve = false;
    private boolean labpanel = false;
    private boolean serie = false;
    private boolean msgTest = false;
    private String nomeleve;//nom eleve de(cas de l'impression en serie) ou nom eleve (cas impression un élève)
    private String nomeleveA;//nom élève à
    private String typeEnvoi = "vide";
    private String msg;
    private String seq;
    private String classe;
    private String matricule;
    private String matriculeA;
    //
    private String msgAvn;
    private String msgAvcc;
    private String msgAvm;
    private String msgApm;
    private String msgApr;
    String message = "";
    String sender;
    String destinataires = "";
    private String user_Login = "kenfackjeanpaul@yahoo.fr";
    private String api_key = "kGxTg7tPcypgDulcyqh8uQdzVM90RIzq";

    public SmsController() {
    }

    public String pagesms() {
        return "sms.xhtml?faces-redirect=true";
    }

    public void matriculeRegex() {
        switch (typeEnvoi) {
            case "Un Elève":
                Pattern p1 = Pattern.compile("\\(.*\\)");
                String s1 = nomeleve;
                Matcher m1 = p1.matcher(s1);
                while (m1.find()) {
                    matricule = (String) m1.group().subSequence(1, m1.group().length() - 1);
                }
                break;
            case "En Série":
                Pattern p2 = Pattern.compile("\\(.*\\)");
                String s2 = nomeleve;
                Matcher m2 = p2.matcher(s2);
                while (m2.find()) {
                    matricule = (String) m2.group().subSequence(1, m2.group().length() - 1);
                }
                Pattern p3 = Pattern.compile("\\(.*\\)");
                String s3 = nomeleveA;
                Matcher m3 = p3.matcher(s3);
                while (m3.find()) {
                    matriculeA = (String) m3.group().subSequence(1, m3.group().length() - 1);
                }
                break;
        }
    }

    public void evoismsSimple() throws IOException {
        switch (typeEnvoi) {
            case "Un Elève":
                sendSimpleSmsun();
                break;
            case "Tous Les Elèves":
                sendSimpleSms();
                break;
            case "En Série":
                sendSimpleSmsserie();
                break;
        }
    }

    public void evoismsMoyenne() throws IOException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = df.format(date);
        try {
            Date dateOperation = df.parse(dateString);
            Operations mouchard = new Operations();
            mouchard.setIdoperations(mouchardFacade.nextId());
            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
            mouchard.setTypeoperation("Envoi des SMS");
            mouchard.setDateoperation(dateOperation);
            mouchard.setDescription("SMS " + seq + ", " + classe + "," + typeEnvoi);
            mouchardFacade.create(mouchard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (seq) {
            case "SEQ1":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendseq1uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendseq1();
                        break;
                    case "En Série":
                        sendseq1serie();
                        break;
                }
                break;

            case "SEQ2":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendseq2uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendseq2();
                        break;
                    case "En Série":
                        sendseq2serie();
                        break;
                }
                break;

            case "SEQ3":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendseq3uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendseq3();
                        break;
                    case "En Série":
                        sendseq3serie();
                        break;
                }
                break;
            case "SEQ4":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendseq4uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendseq4();
                        break;
                    case "En Série":
                        sendseq4serie();
                        break;
                }
                break;
            case "SEQ5":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendseq5uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendseq5();
                        break;
                    case "En Série":
                        sendseq5serie();
                        break;
                }
                break;
            case "SEQ6":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendseq6uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendseq6();
                        break;
                    case "En Série":
                        sendseq6serie();
                        break;
                }
                break;
            case "TRIM1":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendtrim1uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendtrim1();
                        break;
                    case "En Série":
                        sendtrim1serie();
                        break;
                }
                break;
            case "TRIM2":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendtrim2uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendtrim2();
                        break;
                    case "En Série":
                        sendtrim2serie();
                        break;
                }
                break;
            case "TRIM3":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendtrim3uneleve();
                        break;
                    case "Tous Les Elèves":
                        sendtrim3();
                        break;
                    case "En Série":
                        sendtrim3serie();
                        break;
                }
                break;
            case "ANN":
                switch (typeEnvoi) {
                    case "Un Elève":
                        sendanuneleve();
                        break;
                    case "Tous Les Elèves":
                        sendan();
                        break;
                    case "En Série":
                        sendanserie();
                        break;
                }
                break;
        }
    }

    public void updateAjax() {
        switch (typeEnvoi) {
            case "vide":
                labeleve = false;
                labpanel = false;
                serie = false;
                champEleve = false;
                break;
            case "Tous Les Elèves":
                labeleve = false;
                labpanel = false;
                serie = false;
                champEleve = false;
                break;
            case "Un Elève":
                labeleve = true;
                labpanel = false;
                serie = false;
                champEleve = true;
                break;
            case "En Série":
                labeleve = false;
                labpanel = true;
                serie = true;
                champEleve = false;
                break;
        }
    }

    public List<String> listeEleveParClasse() {
        if (classe == null) {
            return null;
        } else {
            return noteseq1Facade.listeElevesParClasse(classe);
        }
    }

    public void faireDisparaitre_le_sms_sur_pdialog() {
        if (seq == null || classe == null || "vide".equals(typeEnvoi)) {
            msg = "Tous Les Champs sont Obligatoires!";
            msgTest = true;
        } else {
            msgTest = false;
        }
    }

    public void faireDisparaitre_le_sms_sur_pdialogsimplesms() {
        if (classe == null || "vide".equals(typeEnvoi)) {
            msg = "Tous Les Champs sont Obligatoires!";
            msgTest = true;
        } else {
            msgTest = false;
        }
    }

    public void afficheMsg() {
        message = msgAvn + " [Nom élève] " + msgAvcc + " " + classe + " " + msgAvm + " 10.00" + " " + msgApm + " 8 " + msgApr;
    }

    public void afficheMsgsimple() {
        message = msgAvn + " [Nom élève] " + msgAvcc + " " + classe + " " + msgAvm;
    }

    public void sendseq1() {
        List<Object[]> objetList = seq1Facade.smsMoyRangS1(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
            System.out.println((String) uneligne[0]);
            sendSms();
        }
    }

    public void sendseq1uneleve() {
        List<Object[]> objetList = seq1Facade.smsMoyRangS1un(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
            sendSms();
        }
    }

    public void sendseq1serie() {
        List<Object[]> objetList = seq1Facade.smsMoyRangS1serie(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq2() {
        List<Object[]> objetList = seq2Facade.smsMoyRangS2(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq2uneleve() {
        List<Object[]> objetList = seq2Facade.smsMoyRangS2un(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq2serie() {
        List<Object[]> objetList = seq2Facade.smsMoyRangS2serie(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq3() {
        List<Object[]> objetList = seq3Facade.smsMoyRangS3(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq3uneleve() {
        List<Object[]> objetList = seq3Facade.smsMoyRangS3un(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq3serie() {
        List<Object[]> objetList = seq3Facade.smsMoyRangS3serie(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq4() {
        List<Object[]> objetList = seq4Facade.smsMoyRangS4(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq4uneleve() {
        List<Object[]> objetList = seq4Facade.smsMoyRangS4un(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq4serie() {
        List<Object[]> objetList = seq4Facade.smsMoyRangS4serie(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq5() {
        List<Object[]> objetList = seq5Facade.smsMoyRangS5(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq5uneleve() {
        List<Object[]> objetList = seq5Facade.smsMoyRangS5un(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq5serie() {
        List<Object[]> objetList = seq5Facade.smsMoyRangS5serie(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq6() {
        List<Object[]> objetList = seq6Facade.smsMoyRangS6(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq6uneleve() {
        List<Object[]> objetList = seq6Facade.smsMoyRangS6un(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendseq6serie() {
        List<Object[]> objetList = seq6Facade.smsMoyRangS6serie(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim1() {
        List<Object[]> objetList = trim1Facade.smsMoyRangt1(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
            sendSms();
        }
    }

    public void sendtrim1uneleve() {
        List<Object[]> objetList = trim1Facade.smsMoyRangtun1(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim1serie() {
        List<Object[]> objetList = trim1Facade.smsMoyRangtserie1(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim2() {
        List<Object[]> objetList = trim2Facade.smsMoyRangt2(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim2uneleve() {
        List<Object[]> objetList = trim2Facade.smsMoyRangtun2(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim2serie() {
        List<Object[]> objetList = trim2Facade.smsMoyRangtserie2(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim3() {
        List<Object[]> objetList = trim3Facade.smsMoyRangt3(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim3uneleve() {
        List<Object[]> objetList = trim3Facade.smsMoyRangtun3(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendtrim3serie() {
        List<Object[]> objetList = trim3Facade.smsMoyRangtserie3(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendan() {
        List<Object[]> objetList = annFacade.smsMoyRangan(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendanuneleve() {
        List<Object[]> objetList = annFacade.smsMoyRanganun(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendanserie() {
        List<Object[]> objetList = annFacade.smsMoyRanganserie(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm + " " + (BigDecimal) uneligne[2] + " " + msgApm + " " + (String) uneligne[3] + " " + msgApr;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendSimpleSms() {
        List<Object[]> objetList = eleveFacade.smsSimple(classe);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendSimpleSmsun() {
        List<Object[]> objetList = eleveFacade.smsSimpleuneleve(classe, matricule);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public void sendSimpleSmsserie() {
        List<Object[]> objetList = eleveFacade.smsSimplesrieeleve(classe, matricule, matriculeA);
        for (Object[] uneligne : objetList) {
            message = msgAvn + " " + (String) uneligne[0] + " " + msgAvcc + " " + classe + " " + msgAvm;
            destinataires = (String) uneligne[1]; //Destinataires séparés par une virgule
        }
    }

    public String sendSms() {
        String msgStr;
        HashMap<String, String> smsData = new HashMap<>();
        smsData.put("user_login", user_Login);
        smsData.put("api_key", api_key);
        smsData.put("sms_text", message);
        smsData.put("sms_recipients", "+237" + destinataires);
        smsData.put("sms_type", "FR");
        smsData.put("sms_sender", sender);
        System.out.println(sender);
        try {
            String res = (this.myHttpRequest("https://www.octopush-dm.com", "/api/sms/", smsData)).trim();

            if (res.contains("<error_code>000</error_code>")) {
                msgStr = "The sending was successful ";
            } else {
                msgStr = "The sending was unsuccessful, please try again!!! ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msgStr = "Unable to get response from server!!! ";
        }
        System.out.println(msgStr);
        return msgStr;
    }

    public String myHttpRequest(String domain, String path, HashMap<String, String> myMap) throws Exception {
        URL myUrl;
        String strRequest = "";
        if (myMap.size() < 2) {
            return "No params";
        }

        for (String hashKey : myMap.keySet()) {
            strRequest += "&" + hashKey + "=" + URLEncoder.encode((myMap.get(hashKey) == null ? "" : myMap.get(hashKey)), "UTF-8");
        }
        strRequest = strRequest.substring(1);

        myUrl = new URL(domain + path + "?" + strRequest);
        System.out.println(myUrl);
        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        con.setReadTimeout(0);
        con.setRequestMethod("POST");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        StringBuffer response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
        }
        System.out.println(response.toString());
        return response.toString();
    }

    public String getBalance() {
        String blStr;
        HashMap<String, String> balanceData = new HashMap<>();
        balanceData.put("user_login", user_Login);
        balanceData.put("api_key", api_key);

        try {
            blStr = myHttpRequest("https://www.octopush-dm.com", "/api/balance", balanceData).trim();

            blStr = "For pro sms type the balance is: "+ blStr.substring(blStr.indexOf("type=\"FR\"") + ("type=\"FR\">").length(),
                            blStr.indexOf("</balance>  <balance type"))
                    + "\nFor standard sms type the balance is: "
                    + blStr.substring(blStr.indexOf("type=\"XXX\">") + ("type=\"XXX\">").length(),
                            blStr.indexOf("</balance></octopush>"));
        } catch (Exception e) {
            e.printStackTrace();
            blStr = "Unable to get response from server !!!";
        }
        return blStr;

    }

    public boolean isChampEleve() {
        return champEleve;
    }

    public void setChampEleve(boolean champEleve) {
        this.champEleve = champEleve;
    }

    public boolean isLabeleve() {
        return labeleve;
    }

    public void setLabeleve(boolean labeleve) {
        this.labeleve = labeleve;
    }

    public boolean isLabpanel() {
        return labpanel;
    }

    public void setLabpanel(boolean labpanel) {
        this.labpanel = labpanel;
    }

    public boolean isSerie() {
        return serie;
    }

    public void setSerie(boolean serie) {
        this.serie = serie;
    }

    public boolean isMsgTest() {
        return msgTest;
    }

    public void setMsgTest(boolean msgTest) {
        this.msgTest = msgTest;
    }

    public String getNomeleve() {
        return nomeleve;
    }

    public void setNomeleve(String nomeleve) {
        this.nomeleve = nomeleve;
    }

    public String getNomeleveA() {
        return nomeleveA;
    }

    public void setNomeleveA(String nomeleveA) {
        this.nomeleveA = nomeleveA;
    }

    public String getTypeEnvoi() {
        return typeEnvoi;
    }

    public void setTypeEnvoi(String typeEnvoi) {
        this.typeEnvoi = typeEnvoi;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMatriculeA() {
        return matriculeA;
    }

    public void setMatriculeA(String matriculeA) {
        this.matriculeA = matriculeA;
    }

    public String getMsgAvm() {
        return msgAvm;
    }

    public void setMsgAvm(String msgAvm) {
        this.msgAvm = msgAvm;
    }

    public String getMsgApm() {
        return msgApm;
    }

    public void setMsgApm(String msgApm) {
        this.msgApm = msgApm;
    }

    public String getMsgApr() {
        return msgApr;
    }

    public void setMsgApr(String msgApr) {
        this.msgApr = msgApr;
    }

    public String getMsgAvn() {
        return msgAvn;
    }

    public void setMsgAvn(String msgAvn) {
        this.msgAvn = msgAvn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgAvcc() {
        return msgAvcc;
    }

    public void setMsgAvcc(String msgAvcc) {
        this.msgAvcc = msgAvcc;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
