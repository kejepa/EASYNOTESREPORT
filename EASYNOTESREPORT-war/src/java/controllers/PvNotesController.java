/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Notesdeselevesseq1;
import entities.PvNotes;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import sessions.Notesdeselevesseq1FacadeLocal;
import sessions.Notesdeselevesseq2FacadeLocal;
import sessions.Notesdeselevesseq3FacadeLocal;
import sessions.Notesdeselevesseq4FacadeLocal;
import sessions.Notesdeselevesseq5FacadeLocal;
import sessions.Notesdeselevesseq6FacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "pvNotesController")
@SessionScoped
public class PvNotesController implements Serializable {

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
    List<Notesdeselevesseq1> noteEleveSeq1 = new ArrayList<>();
    private boolean msgTest = false;
    private String msg;
    private String seq;
    private String classe;

    public PvNotesController() {
    }

    public void faireDisparaitre_le_sms_sur_pdialog() {
        msg = "";
        msgTest = false;
    }

    public String pageuser() {
        return "pvnotes.xhtml?faces-redirect=true";
    }

    public String verification() {
        if (seq == null) {
            msg = "Veuillez Choisir la Séquence SVP!";
            msgTest = true;
            return "";
        } else {
            switch (seq) {
                case "SEQ1":
                    if (noteseq1Facade.noteElevesSeq1(classe) == null) {
                        msg = "Fiche pas encore disponible!";
                        msgTest = true;
                        return "";
                    }
                    break;
                case "SEQ2":
                    if (noteseq2Facade.noteElevesSeq2(classe) == null) {
                        msg = "Fiche pas encore disponible!";
                        msgTest = true;
                        return "";
                    }
                    break;
                case "SEQ3":
                    if (noteseq3Facade.noteElevesSeq3(classe) == null) {
                        msg = "Fiche pas encore disponible!";
                        msgTest = true;
                        return "";
                    }
                    break;
                case "SEQ4":
                    if (noteseq4Facade.noteElevesSeq4(classe) == null) {
                        msg = "Fiche pas encore disponible!";
                        msgTest = true;
                        return "";
                    }
                    break;
                case "SEQ5":
                    if (noteseq5Facade.noteElevesSeq5(classe) == null) {
                        msg = "Fiche pas encore disponible!";
                        msgTest = true;
                        return "";
                    }
                    break;
                case "SEQ6":
                    if (noteseq6Facade.noteElevesSeq6(classe) == null) {
                        msg = "Fiche pas encore disponible!";
                        msgTest = true;
                        return "";
                    }
                    break;
            }

        }
        return "";
    }

    public void imprimerPvNotes() throws IOException {
        switch (seq) {
            case "SEQ1":
                imprimerFichePvNotes1();
                break;
            case "SEQ2":
                imprimerFichePvNotes2();
                break;
            case "SEQ3":
                imprimerFichePvNotes3();
                break;
            case "SEQ4":
                imprimerFichePvNotes4();
                break;
            case "SEQ5":
                imprimerFichePvNotes5();
                break;
            case "SEQ6":
                imprimerFichePvNotes6();
                break;
        }
    }

    public String imprimerFichePvNotes1() throws IOException {
        try {
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
            List<PvNotes> pvnoteList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.noteElevesSeq1(classe);
            for (Object[] uneligne : objetList) {
                PvNotes pvn = new PvNotes();
                pvn.setMatriculeeleve((String) uneligne[0]);
                pvn.setMoyenne((BigDecimal) uneligne[1]);
                pvn.setCodematiere((String) uneligne[2]);
                pvn.setLanote((BigDecimal) uneligne[3]);
                pvnoteList.add(pvn);
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvnoteList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/etatnotes1.jrxml");
            Map parameters = new HashMap();
            parameters.put("ANNEESCO", annee);
            parameters.put("Pcodeclasse", classe);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_notes_Seq1" + "_" + classe + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            msg = "Opération effectuée avec succès";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pvnotes.xhtml?faces-redirect=true";
    }

    public String imprimerFichePvNotes2() throws IOException {
        try {
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
            List<PvNotes> pvnoteList = new ArrayList<>();
            List<Object[]> objetList = noteseq2Facade.noteElevesSeq2(classe);
            for (Object[] uneligne : objetList) {
                PvNotes pvn = new PvNotes();
                pvn.setMatriculeeleve((String) uneligne[0]);
                pvn.setMoyenne((BigDecimal) uneligne[1]);
                pvn.setCodematiere((String) uneligne[2]);
                pvn.setLanote((BigDecimal) uneligne[3]);
                pvnoteList.add(pvn);
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvnoteList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/etatnotes2.jrxml");
            Map parameters = new HashMap();
            parameters.put("ANNEESCO", annee);
            parameters.put("Pcodeclasse", classe);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_notes_Seq2" + "_" + classe + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            msg = "Opération effectuée avec succès";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pvnotes.xhtml?faces-redirect=true";
    }

    public String imprimerFichePvNotes3() throws IOException {
        try {
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
            List<PvNotes> pvnoteList = new ArrayList<>();
            List<Object[]> objetList = noteseq3Facade.noteElevesSeq3(classe);
            for (Object[] uneligne : objetList) {
                PvNotes pvn = new PvNotes();
                pvn.setMatriculeeleve((String) uneligne[0]);
                pvn.setMoyenne((BigDecimal) uneligne[1]);
                pvn.setCodematiere((String) uneligne[2]);
                pvn.setLanote((BigDecimal) uneligne[3]);
                pvnoteList.add(pvn);
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvnoteList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/etatnotes3.jrxml");
            Map parameters = new HashMap();
            parameters.put("ANNEESCO", annee);
            parameters.put("Pcodeclasse", classe);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_notes_Seq3" + "_" + classe + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            msg = "Opération effectuée avec succès";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pvnotes.xhtml?faces-redirect=true";
    }

    public String imprimerFichePvNotes4() throws IOException {
        try {
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
            List<PvNotes> pvnoteList = new ArrayList<>();
            List<Object[]> objetList = noteseq4Facade.noteElevesSeq4(classe);
            for (Object[] uneligne : objetList) {
                PvNotes pvn = new PvNotes();
                pvn.setMatriculeeleve((String) uneligne[0]);
                pvn.setMoyenne((BigDecimal) uneligne[1]);
                pvn.setCodematiere((String) uneligne[2]);
                pvn.setLanote((BigDecimal) uneligne[3]);
                pvnoteList.add(pvn);
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvnoteList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/etatnotes4.jrxml");
            Map parameters = new HashMap();
            parameters.put("ANNEESCO", annee);
            parameters.put("Pcodeclasse", classe);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_notes_Seq4" + "_" + classe + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            msg = "Opération effectuée avec succès";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pvnotes.xhtml?faces-redirect=true";
    }

    public String imprimerFichePvNotes5() throws IOException {
        try {
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
            List<PvNotes> pvnoteList = new ArrayList<>();
            List<Object[]> objetList = noteseq5Facade.noteElevesSeq5(classe);
            for (Object[] uneligne : objetList) {
                PvNotes pvn = new PvNotes();
                pvn.setMatriculeeleve((String) uneligne[0]);
                pvn.setMoyenne((BigDecimal) uneligne[1]);
                pvn.setCodematiere((String) uneligne[2]);
                pvn.setLanote((BigDecimal) uneligne[3]);
                pvnoteList.add(pvn);
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvnoteList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/etatnotes5.jrxml");
            Map parameters = new HashMap();
            parameters.put("ANNEESCO", annee);
            parameters.put("Pcodeclasse", classe);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_notes_Seq5" + "_" + classe + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            msg = "Opération effectuée avec succès";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pvnotes.xhtml?faces-redirect=true";
    }

    public String imprimerFichePvNotes6() throws IOException {
        try {
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
            List<PvNotes> pvnoteList = new ArrayList<>();
            List<Object[]> objetList = noteseq6Facade.noteElevesSeq6(classe);
            for (Object[] uneligne : objetList) {
                PvNotes pvn = new PvNotes();
                pvn.setMatriculeeleve((String) uneligne[0]);
                pvn.setMoyenne((BigDecimal) uneligne[1]);
                pvn.setCodematiere((String) uneligne[2]);
                pvn.setLanote((BigDecimal) uneligne[3]);
                pvnoteList.add(pvn);
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvnoteList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/PV/etatnotes6.jrxml");
            Map parameters = new HashMap();
            parameters.put("ANNEESCO", annee);
            parameters.put("Pcodeclasse", classe);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=pv_notes_Seq6" + "_" + classe + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            msg = "Opération effectuée avec succès";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pvnotes.xhtml?faces-redirect=true";
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

}
