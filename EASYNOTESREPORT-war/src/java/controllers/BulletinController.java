/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.BulletinAnn;
import entities.BulletinTrim;
import entities.BulletinSeq;
import entities.BulletinTrim2;
import entities.BulletinTrim3;
import entities.Operations;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sessions.NotesdeselevesannFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;
import sessions.Notesdeselevesseq2FacadeLocal;
import sessions.Notesdeselevesseq3FacadeLocal;
import sessions.Notesdeselevesseq4FacadeLocal;
import sessions.Notesdeselevesseq5FacadeLocal;
import sessions.Notesdeselevesseq6FacadeLocal;
import sessions.Notesdeselevestrim1FacadeLocal;
import sessions.Notesdeselevestrim2FacadeLocal;
import sessions.Notesdeselevestrim3FacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "bulletinController")
@SessionScoped
public class BulletinController implements Serializable {

    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    @EJB
    private Notesdeselevesseq2FacadeLocal noteseq2Facade;
    @EJB
    private Notesdeselevesseq3FacadeLocal noteseq3Facade;
    @EJB
    private Notesdeselevesseq4FacadeLocal noteseq4Facade;
    @EJB
    private Notesdeselevesseq5FacadeLocal noteseq5Facade;
    @EJB
    private Notesdeselevesseq6FacadeLocal noteseq6Facade;
    @EJB
    private Notesdeselevestrim1FacadeLocal notetrim1Facade;
    @EJB
    private Notesdeselevestrim2FacadeLocal notetrim2Facade;
    @EJB
    private Notesdeselevestrim3FacadeLocal notetrim3Facade;
    @EJB
    private NotesdeselevesannFacadeLocal noteannFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private boolean champEleve = false;
    private boolean labeleve = false;
    private boolean labpanel = false;
    private boolean serie = false;
    private boolean msgTest = false;
    private String nomeleve;//nom eleve de(cas de l'impression en serie) ou nom eleve (cas impression un élève)
    private String nomeleveA;//nom élève à
    private String typeImpression = "vide";
    private String msg;
    private String seq;
    private String classe;
    private String matricule;
    private String matriculeA;

    public BulletinController() {
    }

    public String espaceBulletin() {
        return "bulletin.xhtml?faces-redirect=true";
    }

    public void matriculeRegex() {
        switch (typeImpression) {
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

    public void impressionGeneral() throws IOException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = df.format(date);
        try {
            Date dateOperation = df.parse(dateString);
            Operations mouchard = new Operations();
            mouchard.setIdoperations(mouchardFacade.nextId());
            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
            mouchard.setTypeoperation("Impression bulletins");
            mouchard.setDateoperation(dateOperation);
            mouchard.setDescription("Impression bulletins " + seq + ", " + classe + "," + typeImpression);
            mouchardFacade.create(mouchard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (seq) {
            case "SEQ1":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnEleveseq1();
                        break;
                    case "Tous Les Elèves":
                        imprimerToutseq1();
                        break;
                    case "En Série":
                        imprimerEnSerieseq1();
                        break;
                }
                break;

            case "SEQ2":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnEleveseq2();
                        break;
                    case "Tous Les Elèves":
                        imprimerToutseq2();
                        break;
                    case "En Série":
                        imprimerEnSerieseq2();
                        break;
                }
                break;

            case "SEQ3":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnEleveseq3();
                        break;
                    case "Tous Les Elèves":
                        imprimerToutseq3();
                        break;
                    case "En Série":
                        imprimerEnSerieseq3();
                        break;
                }
                break;
            case "SEQ4":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnEleveseq4();
                        break;
                    case "Tous Les Elèves":
                        imprimerToutseq4();
                        break;
                    case "En Série":
                        imprimerEnSerieseq4();
                        break;
                }
                break;
            case "SEQ5":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnEleveseq5();
                        break;
                    case "Tous Les Elèves":
                        imprimerToutseq5();
                        break;
                    case "En Série":
                        imprimerEnSerieseq5();
                        break;
                }
                break;
            case "SEQ6":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnEleveseq6();
                        break;
                    case "Tous Les Elèves":
                        imprimerToutseq6();
                        break;
                    case "En Série":
                        imprimerEnSerieseq6();
                        break;
                }
                break;
            case "TRIM1":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnElevetrim1();
                        break;
                    case "Tous Les Elèves":
                        imprimerTouttrim1();
                        break;
                    case "En Série":
                        imprimerEnSerietrim1();
                        break;
                }
                break; 
            case "TRIM2":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnElevetrim2();
                        break;
                    case "Tous Les Elèves":
                        imprimerTouttrim2();
                        break;
                    case "En Série":
                        imprimerEnSerietrim2();
                        break;
                }
                break;
            case "TRIM3":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnElevetrim3();
                        break;
                    case "Tous Les Elèves":
                        imprimerTouttrim3();
                        break;
                    case "En Série":
                        imprimerEnSerietrim3();
                        break;
                }
                break;
            case "ANN":
                switch (typeImpression) {
                    case "Un Elève":
                        imprimerUnEleveann();
                        break;
                    case "Tous Les Elèves":
                        imprimerToutann();
                        break;
                    case "En Série":
                        imprimerEnSerieann();
                        break;
                }
                break;
        }
    }

    public void updateAjax() {
        switch (typeImpression) {
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
        if (seq == null || classe == null || "vide".equals(typeImpression)) {
            msg = "Tous Les Champs sont Obligatoires!";
            msgTest = true;
        } else { 
            msgTest = false;
        }
    }
//impression des bulletins

    public String imprimerToutseq1() throws IOException {
        try {
            List<BulletinSeq> bulletinseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.bulletinSeq1(classe);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinseqList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinseqList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq1" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnEleveseq1() throws IOException {
        try {
            List<BulletinSeq> bulletinseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.bulletinSeq1ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinseqList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinseqList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq1Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq1" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerieseq1() throws IOException {
        try {
            List<BulletinSeq> bulletinseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.bulletinSeq1ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinseqList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinseqList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq1serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq1" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerToutseq2() throws IOException {
        try {
            List<BulletinSeq> bulletinseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq2Facade.bulletinSeq2(classe);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinseqList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinseqList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq2" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnEleveseq2() throws IOException {
        try {
            List<BulletinSeq> bulletinseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq2Facade.bulletinSeq2ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinseqList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinseqList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq2Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq2" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerieseq2() throws IOException {
        try {
            List<BulletinSeq> bulletinseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq2Facade.bulletinSeq2ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinseqList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinseqList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq2serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq2" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerTouttrim1() throws IOException {
        try {
            List<BulletinTrim> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.bulletinTrim1(classe);
            for (Object[] uneligne : objetList) {
                BulletinTrim bt = new BulletinTrim();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim1" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnElevetrim1() throws IOException {
        try {
            List<BulletinTrim> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.bulletinTrim1ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinTrim bt = new BulletinTrim();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim1Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim1" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerietrim1() throws IOException {
        try {
            List<BulletinTrim> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.bulletinTrim1ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinTrim bt = new BulletinTrim();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim1serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim1" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerToutseq3() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq3Facade.bulletinSeq3(classe);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq3" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnEleveseq3() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.bulletinSeq1ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq3Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq3" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerieseq3() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq3Facade.bulletinSeq3ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq3serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq3" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerToutseq4() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq4Facade.bulletinSeq4(classe);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq4.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq4" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnEleveseq4() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq4Facade.bulletinSeq4ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq4Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq4" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerieseq4() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq4Facade.bulletinSeq4ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq4serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq4" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerTouttrim2() throws IOException {
        try {
            List<BulletinTrim2> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim2Facade.bulletinTrim2(classe);
            for (Object[] uneligne : objetList) {
                BulletinTrim2 bt = new BulletinTrim2();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim2" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnElevetrim2() throws IOException {
        try {
            List<BulletinTrim2> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim2Facade.bulletinTrim2ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinTrim2 bt = new BulletinTrim2();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim2Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim2" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerietrim2() throws IOException {
        try {
            List<BulletinTrim2> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim2Facade.bulletinTrim2ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinTrim2 bt = new BulletinTrim2();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim2serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim2" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerToutseq5() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq5Facade.bulletinSeq5(classe);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq5.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq5" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnEleveseq5() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq5Facade.bulletinSeq5ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq5Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq5" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerieseq5() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq5Facade.bulletinSeq5ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq5serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq5" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerToutseq6() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq6Facade.bulletinSeq6(classe);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq6.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq6" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnEleveseq6() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq6Facade.bulletinSeq6ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq6Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq6" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerieseq6() throws IOException {
        try {
            List<BulletinSeq> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq6Facade.bulletinSeq6ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinSeq bt = new BulletinSeq();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setString_agg((String) uneligne[35]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsSeq6serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Seq6" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerTouttrim3() throws IOException {
        try {
            List<BulletinTrim3> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim3Facade.bulletinTrim3(classe);
            for (Object[] uneligne : objetList) {
                BulletinTrim3 bt = new BulletinTrim3();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bt.setMoyenneelevetrim2((BigDecimal) uneligne[43]);
                bt.setMoyenneeleve5((BigDecimal) uneligne[44]);
                bt.setMoyenneeleve6((BigDecimal) uneligne[45]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim3" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnElevetrim3() throws IOException {
        try {
            List<BulletinTrim3> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.bulletinSeq1ParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinTrim3 bt = new BulletinTrim3();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bt.setMoyenneelevetrim2((BigDecimal) uneligne[43]);
                bt.setMoyenneeleve5((BigDecimal) uneligne[44]);
                bt.setMoyenneeleve6((BigDecimal) uneligne[45]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim3Pareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim3" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerietrim3() throws IOException {
        try {
            List<BulletinTrim3> bulletinList = new ArrayList<>();
            List<Object[]> objetList = notetrim3Facade.bulletinTrim3ParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinTrim3 bt = new BulletinTrim3();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bt.setMoyenneelevetrim2((BigDecimal) uneligne[43]);
                bt.setMoyenneeleve5((BigDecimal) uneligne[44]);
                bt.setMoyenneeleve6((BigDecimal) uneligne[45]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsTrim3serie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Trim3" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    //impression des bulletins
    public String imprimerToutann() throws IOException {
        try {
            List<BulletinAnn> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteannFacade.bulletinAnn(classe);
            for (Object[] uneligne : objetList) {
                BulletinAnn bt = new BulletinAnn();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bt.setMoyenneelevetrim2((BigDecimal) uneligne[43]);
                bt.setMoyenneeleve5((BigDecimal) uneligne[44]);
                bt.setMoyenneeleve6((BigDecimal) uneligne[45]);
                bt.setMoyenneelevetrim3((BigDecimal) uneligne[46]);
                bt.setNotes3((BigDecimal) uneligne[47]);
                bt.setNotes4((BigDecimal) uneligne[48]);
                bt.setNotes5((BigDecimal) uneligne[49]);
                bt.setNotes6((BigDecimal) uneligne[50]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsAnn.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3).intValueExact());
            parameters.put("bld", noteseq1Facade.borneTravail().get(4).intValueExact());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Ann" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerUnEleveann() throws IOException {
        try {
            List<BulletinAnn> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteannFacade.bulletinAnnParEleve(classe, matricule);
            for (Object[] uneligne : objetList) {
                BulletinAnn bt = new BulletinAnn();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bt.setMoyenneelevetrim2((BigDecimal) uneligne[43]);
                bt.setMoyenneeleve5((BigDecimal) uneligne[44]);
                bt.setMoyenneeleve6((BigDecimal) uneligne[45]);
                bt.setMoyenneelevetrim3((BigDecimal) uneligne[46]);
                bt.setNotes3((BigDecimal) uneligne[47]);
                bt.setNotes4((BigDecimal) uneligne[48]);
                bt.setNotes5((BigDecimal) uneligne[49]);
                bt.setNotes6((BigDecimal) uneligne[50]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsAnnPareleve.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Ann" + "_" + classe + "_" + nomeleve + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerEnSerieann() throws IOException {
        try {
            List<BulletinAnn> bulletinList = new ArrayList<>();
            List<Object[]> objetList = noteannFacade.bulletinAnnParSerie(classe, matricule, matriculeA);
            for (Object[] uneligne : objetList) {
                BulletinAnn bt = new BulletinAnn();
                bt.setCodeclasse((String) uneligne[0]);
                bt.setMatriculeeleve((String) uneligne[1]);
                bt.setNomeleve((String) uneligne[2]);
                bt.setSexe((String) uneligne[3]);
                bt.setDatenaiss((Date) uneligne[4]);
                bt.setLieunaiss((String) uneligne[5]);
                bt.setAdresse((String) uneligne[6]);
                bt.setRedoublant((String) uneligne[7]);
                bt.setPhoto((String) uneligne[8]);
                bt.setCodematiere((String) uneligne[9]);
                bt.setCoefficient((BigDecimal) uneligne[10]);
                bt.setGroupe((int) uneligne[11]);
                bt.setPeriode((String) uneligne[12]);
                bt.setMoyenneclasse((BigDecimal) uneligne[13]);
                bt.setMoyennepremier((BigDecimal) uneligne[14]);
                bt.setMoyennedernier((BigDecimal) uneligne[15]);
                bt.setTauxreussite((String) uneligne[16]);
                bt.setNomprof((String) uneligne[17]);
                bt.setLanote((BigDecimal) uneligne[18]);
                bt.setMoyenneeleve((BigDecimal) uneligne[19]);
                bt.setRang_classe_eleve((String) uneligne[20]);
                bt.setMoyg1((BigDecimal) uneligne[21]);
                bt.setMoyg2((BigDecimal) uneligne[22]);
                bt.setMoyg3((BigDecimal) uneligne[23]);
                bt.setRangg1((String) uneligne[24]);
                bt.setRangg2((String) uneligne[25]);
                bt.setRangg3((String) uneligne[26]);
                bt.setNotemin((BigDecimal) uneligne[27]);
                bt.setNotemax((BigDecimal) uneligne[28]);
                bt.setMoyenne_note((BigDecimal) uneligne[29]);
                bt.setRang_eleve((String) uneligne[30]);
                if (uneligne[31] != null) {
                    bt.setAbsj((int) uneligne[31]);
                }
                if (uneligne[32] != null) {
                    bt.setAbsn((int) uneligne[32]);
                }
                if (uneligne[33] != null) {
                    bt.setConsigne((int) uneligne[33]);
                }
                if (uneligne[34] != null) {
                    bt.setExclusion((int) uneligne[34]);
                }
                bt.setDecision((String) uneligne[35]);
                bt.setString_agg((String) uneligne[36]);
                bt.setNotes2((BigDecimal) uneligne[37]);
                bt.setMoyenneeleve1((BigDecimal) uneligne[38]);
                bt.setMoyenneeleve2((BigDecimal) uneligne[39]);
                bt.setMoyenneeleve3((BigDecimal) uneligne[40]);
                bt.setMoyenneeleve4((BigDecimal) uneligne[41]);
                bt.setMoyenneelevetrim1((BigDecimal) uneligne[42]);
                bt.setMoyenneelevetrim2((BigDecimal) uneligne[43]);
                bt.setMoyenneeleve5((BigDecimal) uneligne[44]);
                bt.setMoyenneeleve6((BigDecimal) uneligne[45]);
                bt.setMoyenneelevetrim3((BigDecimal) uneligne[46]);
                bt.setNotes3((BigDecimal) uneligne[47]);
                bt.setNotes4((BigDecimal) uneligne[48]);
                bt.setNotes5((BigDecimal) uneligne[49]);
                bt.setNotes6((BigDecimal) uneligne[50]);
                bulletinList.add(bt);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bulletinList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Bulletins/bulletinsAnnserie.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("parampp", noteseq1Facade.pp(classe));
            String etsfr = "";
            String etsan = "";
            String regfr = "";
            String regan = "";
            String depfr = "";
            String depan = "";
            String tel = "";
            String bp = "";
            String email = "";
            String chemin = "";
            String situation = "";
            String principal = "";
            String annee = "";
            for (Object[] uniqueligne : noteseq1Facade.ppEts()) {
                etsfr = (String) uniqueligne[0];
                etsan = (String) uniqueligne[1];
                regfr = (String) uniqueligne[2];
                regan = (String) uniqueligne[3];
                depfr = (String) uniqueligne[4];
                depan = (String) uniqueligne[5];
                tel = (String) uniqueligne[6];
                bp = (String) uniqueligne[7];
                email = (String) uniqueligne[8];
                chemin = (String) uniqueligne[9];
                situation = (String) uniqueligne[10];
                principal = (String) uniqueligne[11];
                annee = (String) uniqueligne[12];
            }
            parameters.put("ANNEESCO", annee);
            parameters.put("etsfr", etsfr);
            parameters.put("etsan", etsan);
            parameters.put("regfr", regfr);
            parameters.put("regan", regan);
            parameters.put("depfr", depfr);
            parameters.put("depan", depan);
            parameters.put("tel", tel);
            parameters.put("bp", bp);
            parameters.put("email", email);
            parameters.put("chemin", chemin);
            parameters.put("situation", situation);
            parameters.put("principal", principal);
            parameters.put("pmatricule", matricule);
            parameters.put("pmatriculeA", matriculeA);
            parameters.put("avt", noteseq1Facade.borneTravail().get(1));
            parameters.put("blt", noteseq1Facade.borneTravail().get(2));
            parameters.put("avd", noteseq1Facade.borneTravail().get(3));
            parameters.put("bld", noteseq1Facade.borneTravail().get(4));
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=bulletins_Ann" + "_" + classe + "_" + "Serie" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public boolean isChampEleve() {
        return champEleve;
    }

    public void setChampEleve(boolean champEleve) {
        this.champEleve = champEleve;
    }

    public String getTypeImpression() {
        return typeImpression;
    }

    public void setTypeImpression(String typeImpression) {
        this.typeImpression = typeImpression;
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

    public boolean isMsgTest() {
        return msgTest;
    }

    public void setMsgTest(boolean msgTest) {
        this.msgTest = msgTest;
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

}
