/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.FicheConseil;
import entities.FixhesNotes;
import entities.Listedeseleves;
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
import sessions.NotesdeselevesannFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;
import sessions.Notesdeselevestrim1FacadeLocal;
import sessions.Notesdeselevestrim2FacadeLocal;
import sessions.Notesdeselevestrim3FacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "fichesController")
@SessionScoped
public class FichesController implements Serializable {

    @EJB
    private ListedeselevesFacadeLocal eleveFacade;
    @EJB
    private NotesdeselevesannFacadeLocal notesFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    @EJB
    private Notesdeselevestrim1FacadeLocal notetrim1Facade;
    @EJB
    private Notesdeselevestrim2FacadeLocal notetrim2Facade;
    @EJB
    private Notesdeselevestrim3FacadeLocal notetrim3Facade;
    @EJB
    private NotesdeselevesannFacadeLocal noteannFacade;
    private List<Listedeseleves> eleveList = new ArrayList<>();
    private Listedeseleves eleve;
    private boolean msgTest = false;
    private String classe;
    private String msg;
    private String seq;

    public FichesController() {
    }

    public String fichesdenontes() {
        return "fichedesnotes.xhtml?faces-redirect=true";
    }

    public String fichesdediscipline() {
        return "fichedediscipline.xhtml?faces-redirect=true";
    }

    public String fichesdeconseil() {
        return "ficheconseil.xhtml?faces-redirect=true";
    }

    public void faireDisparaitre_le_sms_sur_pdialog() {
        msg = "";
        msgTest = false;

    }

    public void verifierFiche() {
        switch (seq) {
            case "TRIM1":
                if (notetrim1Facade.ficheConseilTrim1(classe) == null) {
                    msg = "Fiche pas encore prête. Contactez L'Admin";
                    msgTest = true;
                }
                break;
            case "TRIM2":
                if (notetrim1Facade.ficheConseilTrim2(classe) == null) {
                    msg = "Fiche pas encore prête. Contactez L'Admin";
                    msgTest = true;
                }
                break;
            case "TRIM3":
                if (notetrim1Facade.ficheConseilTrim3(classe) == null) {
                    msg = "Fiche pas encore prête. Contactez L'Admin";
                    msgTest = true;
                }
                break;
            case "ANN":
                if (notetrim1Facade.ficheConseilAn(classe) == null) {
                    msg = "Fiche pas encore prête. Contactez L'Admin";
                    msgTest = true;
                }
                break;
        }
    }

    public String impressionGeneral() throws IOException {
        switch (seq) {
            case "TRIM1":
                if (notetrim1Facade.ficheConseilTrim1(classe) != null) {
                    imprimerFicheConseilTrim1();
                }
                break;
            case "TRIM2":
                if (notetrim1Facade.ficheConseilTrim2(classe) != null) {
                    imprimerFicheConseilTrim2();
                }
                break;
            case "TRIM3":
                if (notetrim1Facade.ficheConseilTrim3(classe) != null) {
                    imprimerFicheConseilTrim3();
                }
                break;
            case "ANN":
                if (notetrim1Facade.ficheConseilAn(classe) != null) {
                    imprimerFicheConseilAn();
                }
                break;
        }
        return "ficheconseil.xhtml?faces-redirect=true";
    }

    public String imprimerFicheNote() throws IOException {
        try {
            List<FixhesNotes> fichesList = new ArrayList<>();
            List<Object[]> objetList = notesFacade.fiches(classe);
            for (Object[] uneligne : objetList) {
                FixhesNotes fich = new FixhesNotes();
                fich.setMatriculeeleve((String) uneligne[0]);
                fich.setNom((String) uneligne[1]);
                fich.setPrenom((String) uneligne[2]);
                fich.setSexe((String) uneligne[3]);
                fichesList.add(fich);
            }
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
            HashMap parameters = new HashMap();
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("pcodeclasse", classe);
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
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(fichesList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/fiches/fichedesnotes.jrxml");
            parameters.put("REPORT_TITLE", "");
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=Fiche_Notes" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String imprimerFicheDiscipline() throws IOException {
        try {
            List<FixhesNotes> fichesList = new ArrayList<>();
            List<Object[]> objetList = notesFacade.fiches(classe);
            for (Object[] uneligne : objetList) {
                FixhesNotes fich = new FixhesNotes();
                fich.setMatriculeeleve((String) uneligne[0]);
                fich.setNom((String) uneligne[1]);
                fich.setPrenom((String) uneligne[2]);
                fich.setSexe((String) uneligne[3]);
                fichesList.add(fich);
            }
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
            HashMap parameters = new HashMap();
            parameters.put("effectif", noteseq1Facade.effectifclasse(classe));
            parameters.put("pcodeclasse", classe);
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
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(fichesList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/fiches/fichedediscipline.jrxml");
            parameters.put("REPORT_TITLE", "");
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=Fiche_de_discipline" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String imprimerFicheConseilTrim1() throws IOException {
        try {
            List<FicheConseil> pvseqList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.ficheConseilTrim1(classe);
            for (Object[] uneligne : objetList) {
                FicheConseil pv = new FicheConseil();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pv.setAbsn((Integer) uneligne[8]);
                pv.setConsigne((Integer) uneligne[9]);
                pv.setExclusion((Integer) uneligne[10]);
                pv.setRedoublant((String) uneligne[11]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/fiches/CONSEILTRIM1.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
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
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_CONSEIL_TRIM1" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheconseil.xhtml?faces-redirect=true";
    }

    public String imprimerFicheConseilTrim2() throws IOException {
        try {
            List<FicheConseil> pvseqList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.ficheConseilTrim1(classe);
            for (Object[] uneligne : objetList) {
                FicheConseil pv = new FicheConseil();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pv.setAbsn((Integer) uneligne[8]);
                pv.setConsigne((Integer) uneligne[9]);
                pv.setExclusion((Integer) uneligne[10]);
                pv.setRedoublant((String) uneligne[11]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/fiches/CONSEILTRIM2.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
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
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_CONSEIL_TRIM2" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheconseil.xhtml?faces-redirect=true";
    }

    public String imprimerFicheConseilTrim3() throws IOException {
        try {
            List<FicheConseil> pvseqList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.ficheConseilTrim1(classe);
            for (Object[] uneligne : objetList) {
                FicheConseil pv = new FicheConseil();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pv.setAbsn((Integer) uneligne[8]);
                pv.setConsigne((Integer) uneligne[9]);
                pv.setExclusion((Integer) uneligne[10]);
                pv.setRedoublant((String) uneligne[11]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/fiches/CONSEILTRIM3.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
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
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_CONSEIL_TRIM3" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheconseil.xhtml?faces-redirect=true";
    }

    public String imprimerFicheConseilAn() throws IOException {
        try {
            List<FicheConseil> pvseqList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.ficheConseilTrim1(classe);
            for (Object[] uneligne : objetList) {
                FicheConseil pv = new FicheConseil();
                pv.setNomeleve((String) uneligne[0]);
                pv.setMatriculeeleve((String) uneligne[1]);
                pv.setSexe((String) uneligne[2]);
                pv.setTo_char((String) uneligne[3]);
                pv.setMoyenne1((BigDecimal) uneligne[4]);
                pv.setRang((String) uneligne[5]);
                pv.setMoyenne_classe((BigDecimal) uneligne[6]);
                pv.setTauxreussite((String) uneligne[7]);
                pv.setAbsn((Integer) uneligne[8]);
                pv.setConsigne((Integer) uneligne[9]);
                pv.setExclusion((Integer) uneligne[10]);
                pv.setRedoublant((String) uneligne[11]);
                pvseqList.add(pv);
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pvseqList, false);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/fiches/CONSEILAN.jrxml");
                HashMap parameters = new HashMap();
                parameters.put("REPORT_TITLE", "");
                parameters.put("Pcodeclasse", classe);
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
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_CONSEIL_AN" + "_" + classe + "_" + "pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheconseil.xhtml?faces-redirect=true";
    }

    public ListedeselevesFacadeLocal getEleveFacade() {
        return eleveFacade;
    }

    public void setEleveFacade(ListedeselevesFacadeLocal eleveFacade) {
        this.eleveFacade = eleveFacade;
    }

    public List<Listedeseleves> getEleveList() {
        return eleveList;
    }

    public void setEleveList(List<Listedeseleves> eleveList) {
        this.eleveList = eleveList;
    }

    public Listedeseleves getEleve() {
        return eleve;
    }

    public void setEleve(Listedeseleves eleve) {
        this.eleve = eleve;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
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

    public boolean isMsgTest() {
        return msgTest;
    }

    public void setMsgTest(boolean msgTest) {
        this.msgTest = msgTest;
    }

}
