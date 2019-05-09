/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.PvAn;
import entities.Pvseq;
import entities.Pvtrim;
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

/**
 *
 * @author KENFACK JP
 */
@Named(value = "pvController")
@SessionScoped
public class PvController implements Serializable {

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
    private boolean msgTest = false;
    private String msg;
    private String seq;
    private String classe;
    private BigDecimal seuil = BigDecimal.TEN;

    public PvController() {
    }

    public String espacePV() {
        return "pv.xhtml?faces-redirect=true";
    }

    public void verifierSeqClasseMatiere() {
        if (seq == null) {
            msg = "Veuillez Choisir la séquence SVP!";
            msgTest = true;
        }
        if (classe == null) {
            msgTest = true;
        }
    }

    public void faireDisparaitre_le_sms_sur_pdialog() {
        msg = ""; 
        msgTest = false;

    }

    public String impressionGeneral() throws IOException {
        switch (seq) {
            case "SEQ1":
                imprimerpvseq1();
                break;
            case "SEQ2":
                imprimerpvseq2();
                break;
            case "SEQ3":
                imprimerpvseq3();
                break;
            case "SEQ4":
                imprimerpvseq4();
                break;
            case "SEQ5":
                imprimerpvseq5();
                break;
            case "SEQ6":
                imprimerpvseq6();
                break;
            case "TRIM1":
                imprimerpvtrim1();
                break;
            case "TRIM2":
                imprimerpvtrim2();
                break;
            case "TRIM3":
                imprimerpvtrim3();
                break;
            case "ANN":
                imprimerpvann();
                break;
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvseq1() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.pvSeq1(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQ1.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_Seq1" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvseq2() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq2Facade.pvSeq2(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQ2.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_Seq2" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvseq3() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq3Facade.pvSeq3(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQ3.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_Seq3" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvseq4() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq4Facade.pvSeq4(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQ4.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_Seq4" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvseq5() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq5Facade.pvSeq5(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQ5.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_Seq5" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvseq6() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = noteseq6Facade.pvSeq6(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQ6.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_Seq6" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvtrim1() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.pvTrim1(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQTRIM1.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_TRIM1" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvtrim2() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = notetrim2Facade.pvTrim2(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQTRIM2.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_TRIM2" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvtrim3() throws IOException {
        try {
            List<Pvseq> pvseqList = new ArrayList<>();
            List<Object[]> objetList = notetrim3Facade.pvTrim3(classe);
            for (Object[] uneligne : objetList) {
                Pvseq pv = new Pvseq();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQTRIM3.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_TRIM3" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
    }

    public String imprimerpvann() throws IOException {
        try {
            List<PvAn> pvannList = new ArrayList<>();
            List<Object[]> objetList = noteannFacade.pvAnn(classe);
            for (Object[] uneligne : objetList) {
                PvAn pv = new PvAn();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang1((String) uneligne[5]);
                pv.setMoyenne2((BigDecimal) uneligne[6]);
                pv.setRang2((String) uneligne[7]);
                pv.setMoyenne3((BigDecimal) uneligne[8]);
                pv.setRang3((String) uneligne[9]);
                pv.setMoyenneann((BigDecimal) uneligne[10]);
                pv.setRangann((String) uneligne[11]);
                pv.setMoyenne_classe((BigDecimal) uneligne[12]);
                pv.setTauxreussite((String) uneligne[13]);
                pvannList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvannList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/PVSEQANN.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
                parameters.put("seuil", seuil);
                String etsfr = "";
                String etsan = "";
                String regfr = "";
                String regan = "";
                String depfr = "";
                String depan = "";
                String tel = "";
                String bp = "";
                String email = "";
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
                JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_ANN" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pv.xhtml?faces-redirect=true";
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

    public BigDecimal getSeuil() {
        return seuil;
    }

    public void setSeuil(BigDecimal seuil) {
        this.seuil = seuil;
    }

}
