/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Tableau;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import sessions.ListedeselevesFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "tableauController")
@SessionScoped
public class TableauController implements Serializable {

    @EJB
    private ListedeselevesFacadeLocal eleveFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    private String typeImpression = "Inconnu";
    private String sms;
    private boolean testsms = false;
    private String seq;
    private String classe;

    public TableauController() {
    }

    public String tableau() {
        return "tableau.xhtml?faces-redirect=true";
    }

    public String verifierTableau() {
        if (seq == null) {
            sms = "Veuillez choisir le Trimestre";
            testsms = true;
            return "";
        }
        if (classe == null) {
            sms = "Veuillez choisir la Classe";
            testsms = true;
            return "";
        }
        if (typeImpression == null) {
            sms = "Veuillez choisir le Type de Tableau";
            testsms = true;
            return "";
        } else if (typeImpression != null) {
            switch (typeImpression) {
                case "HONNEUR":
                    switch (seq) {
                        case "TRIM1":
                            if (eleveFacade.tableauh1(classe) == null) {
                                sms = "Aucun Tableau d'honneur pour cette classe";
                                testsms = true;
                            }
                            return "";
                        case "TRIM2":
                            if (eleveFacade.tableauh2(classe) == null) {
                                sms = "Aucun Tableau d'honneur pour cette classe";
                                testsms = true;
                            }
                            return "";
                        case "TRIM3":
                            if (eleveFacade.tableauh3(classe) == null) {
                                sms = "Aucun Tableau d'honneur pour cette classe";
                                testsms = true;
                            }
                            return "";
                    }
                    break;
                case "ENCOURAGEMENT":
                    switch (seq) {
                        case "TRIM1":
                            if (eleveFacade.tableaue1(classe) == null) {
                                sms = "Aucun Tableau d'encouragement pour cette classe";
                                testsms = true;
                            }
                            return "";
                        case "TRIM2":
                            if (eleveFacade.tableaue2(classe) == null) {
                                sms = "Aucun Tableau d'encouragement pour cette classe";
                                testsms = true;
                            }
                            return "";
                        case "TRIM3":
                            if (eleveFacade.tableaue3(classe) == null) {
                                sms = "Aucun Tableau d'encouragement pour cette classe";
                                testsms = true;
                            }
                            return "";
                    }
                    break;
                case "FELICITATION":
                    switch (seq) {
                        case "TRIM1":
                            if (eleveFacade.tableauf1(classe) == null) {
                                sms = "Aucun Tableau de félicitation pour cette classe";
                                testsms = true;
                            }
                            return "";
                        case "TRIM2":
                            if (eleveFacade.tableauf2(classe) == null) {
                                sms = "Aucun Tableau félicitation pour cette classe";
                                testsms = true;
                            }
                            return "";
                        case "TRIM3":
                            if (eleveFacade.tableauf3(classe) == null) {
                                sms = "Aucun Tableau félicitation pour cette classe";
                                testsms = true;
                            }
                            return "";
                    }
                    break;
            }
        }
        return "";
    }

    public void deactiversms() {
        testsms = false;
    }

    public void impressionGeneral() throws IOException {
        switch (seq) {
            case "TRIM1":
                switch (typeImpression) {
                    case "HONNEUR":
                        imprimerTh1();
                        break;
                    case "ENCOURAGEMENT":
                        imprimerTe1();
                        break;
                    case "FELICITATION":
                        imprimerTf1();
                        break;
                }
                break;
            case "TRIM2":
                switch (typeImpression) {
                    case "HONNEUR":
                        imprimerTh2();
                        break;
                    case "ENCOURAGEMENT":
                        imprimerTe2();
                        break;
                    case "FELICITATION":
                        imprimerTf2();
                        break;
                }
                break;
            case "TRIM3":
                switch (typeImpression) {
                    case "HONNEUR":
                        imprimerTh3();
                        break;
                    case "ENCOURAGEMENT":
                        imprimerTe3();
                        break;
                    case "FELICITATION":
                        imprimerTf3();
                        break;
                }
                break;
        }
    }

    public String imprimerTh1() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableauh1(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TH1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
//            JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.ScriptMTBold", true);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TH_TRIM1" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTh2() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableauh2(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TH2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TH_TRIM2" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTh3() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableauh3(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TH3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TH_TRIM3" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTf1() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableauf1(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TF1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TF_TRIM1" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTf2() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableauf2(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TF2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TF_TRIM2" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTf3() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableauf3(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TF3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TF_TRIM3" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTe1() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableaue1(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TE1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TE_TRIM1" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTe2() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableaue2(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TE2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TE_TRIM2" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public String imprimerTe3() throws IOException {
        try {
            List<Tableau> tableauList = new ArrayList<>();
            List<Object[]> objetList = eleveFacade.tableaue3(classe);
            for (Object[] uneligne : objetList) {
                Tableau tb = new Tableau();
                tb.setNom((String) uneligne[0]);
                tb.setPrenom((String) uneligne[1]);
                tb.setCodeclasse((String) uneligne[2]);
                tb.setMoyenne((BigDecimal) uneligne[3]);
                tableauList.add(tb);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(tableauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/TE3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("pcodeclasse", classe);
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
            parameters.put("ANNEESCO", annee);
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=TE_TRIM3" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bulletin.xhtml?faces-redirect=true";
    }

    public ListedeselevesFacadeLocal getEleveFacade() {
        return eleveFacade;
    }

    public void setEleveFacade(ListedeselevesFacadeLocal eleveFacade) {
        this.eleveFacade = eleveFacade;
    }

    public String getTypeImpression() {
        return typeImpression;
    }

    public void setTypeImpression(String typeImpression) {
        this.typeImpression = typeImpression;
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

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public boolean isTestsms() {
        return testsms;
    }

    public void setTestsms(boolean testsms) {
        this.testsms = testsms;
    }

}
