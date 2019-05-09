/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.StatParNiveau;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sessions.AnnmoyennesrangsdeselevesFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;
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
@Named(value = "statParNiveauController")
@SessionScoped
public class StatParNiveauController implements Serializable {

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
    private String seq;
    private String section;
    private boolean msgTest = false;
    private String msg;

    public StatParNiveauController() {
    }

    public String statparniv() {
        return "statparniveau.xhtml?faces-redirect=true";
    }

    public void faireDisparaitre_le_sms_sur_pdialog() {
        msg = "";
        msgTest = false;
    }

    public void verifiDisponibilite_statParNiveau() {
        switch (seq) {
            case "SEQ1":
                if (seq1Facade.statparniveaus1(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "SEQ2":
                if (seq2Facade.statparniveaus2(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "SEQ3":
                if (seq3Facade.statparniveaus3(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "SEQ4":
                if (seq4Facade.statparniveaus4(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "SEQ5":
                if (seq5Facade.statparniveaus5(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "SEQ6":
                if (seq6Facade.statparniveaus6(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "TRIM1":
                if (trim1Facade.statparniveaut1(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "TRIM2":
                if (trim2Facade.statparniveaut2(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "TRIM3":
                if (trim3Facade.statparniveaut3(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
            case "ANN":
                if (annFacade.statparniveauann(section) == null) {
                    msg = "Pas encore Prêt";
                    msgTest = true;
                }
                break;
        }
    }

    public void imprimerstatParNiveau() throws IOException {
        switch (seq) {
            case "SEQ1":
                imprimerFicheSynthSeq1();
                break;
            case "SEQ2":
                imprimerFicheSynthSeq2();
                break;
            case "SEQ3":
                imprimerFicheSynthSeq3();
                break;
            case "SEQ4":
                imprimerFicheSynthSeq4();
                break;
            case "SEQ5":
                imprimerFicheSynthSeq5();
                break;
            case "SEQ6":
                imprimerFicheSynthSeq6();
                break;
            case "TRIM1":
                imprimerFicheSynthT1();
                break;
            case "TRIM2":
                imprimerFicheSynthT2();
                break;
            case "TRIM3":
                imprimerFicheSynthTrim3();
                break;
            case "ANN":
                imprimerFicheSynthAn();
                break;
        }
    }

    public String imprimerFicheSynthSeq1() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = seq1Facade.statparniveaus1(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHSEQ1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", ""); 
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_SEQ1" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq2() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = seq2Facade.statparniveaus2(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHSEQ2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_SEQ2" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq3() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = seq3Facade.statparniveaus3(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHSEQ3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_SEQ3" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq4() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = seq4Facade.statparniveaus4(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHSEQ4.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_SEQ4" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq5() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = seq5Facade.statparniveaus5(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHSEQ5.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_SEQ5" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq6() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = seq6Facade.statparniveaus6(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHSEQ6.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_SEQ6" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthT1() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = trim1Facade.statparniveaut1(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHTRIM1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_TRIM1" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthT2() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = trim2Facade.statparniveaut2(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHTRIM2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_TRIM2" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthTrim3() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = trim3Facade.statparniveaut3(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHTRIM3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_TRIM3" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthAn() throws IOException {
        try {
            List<StatParNiveau> StatParNiveauList = new ArrayList<>();
            List<Object[]> objetList = annFacade.statparniveauann(section);
            objetList.stream().map((uneligne) -> {
                StatParNiveau stat = new StatParNiveau();
                stat.setClasscorresp((String) uneligne[0]);
                stat.setInscrits((Long) uneligne[1]);
                stat.setPresents((Long) uneligne[2]);
                stat.setAdmis((Long) uneligne[3]);
                stat.setTypedeclasse((String) uneligne[4]);
                return stat;
            }).forEachOrdered((stat) -> {
                StatParNiveauList.add(stat);
            });
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(StatParNiveauList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_SynthNiv/SYNTHANN.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
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
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=STAT_PAR_NIVEAU_FR_AN" + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException e) {
        }

        return "statparniveau.xhtml?faces-redirect=true";
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

}
