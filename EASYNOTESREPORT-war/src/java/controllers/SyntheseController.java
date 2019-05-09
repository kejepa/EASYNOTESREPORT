/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.SynthAnn;
import entities.SynthTrimAn;
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
import sessions.ConseildeclasseannFacadeLocal;
import sessions.Conseildeclassetrim1FacadeLocal;
import sessions.Conseildeclassetrim2FacadeLocal;
import sessions.Conseildeclassetrim3FacadeLocal;
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
@Named(value = "syntheseController")
@SessionScoped
public class SyntheseController implements Serializable {

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
    private Conseildeclassetrim1FacadeLocal conseiltrim1Facade;
    @EJB
    private Conseildeclassetrim2FacadeLocal conseiltrim2Facade;
    @EJB
    private Conseildeclassetrim3FacadeLocal conseiltrim3Facade;
    @EJB
    private ConseildeclasseannFacadeLocal conseilannFacade;
    private boolean msgTest = false;
    private String msg;
    private String seq;
    private String classe;

    public SyntheseController() {
    }

    public String espaceFicheSynth() {
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String espaceFicheStat() {
        return "ficheStat.xhtml?faces-redirect=true";
    }

    public void faireDisparaitre_le_sms_sur_pdialog() {
        msg = "";
        msgTest = true;
    }

    public String imprimerFicheSynth() throws IOException {
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
                imprimerFicheSynthTrim1();
                break;
            case "TRIM2":
                imprimerFicheSynthTrim2();
                break;
            case "TRIM3":
                imprimerFicheSynthTrim3();
                break;
            case "ANN":
                imprimerFicheSynthAnn();
                break;
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq1() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteseq1Facade.synthSeq1(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHSEQ1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = noteseq1Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = noteseq1Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_SEQ1" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq2() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteseq2Facade.synthSeq2(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHSEQ2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = noteseq2Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = noteseq2Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_SEQ2" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq3() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteseq3Facade.synthSeq3(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHSEQ3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = noteseq3Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = noteseq3Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("MGS3", noteannFacade.rappelSeq(classe).get(2));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("TR3", noteannFacade.rappelseqTaux(classe).get(2));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_SEQ3" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq4() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteseq4Facade.synthSeq4(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHSEQ4.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = noteseq4Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = noteseq4Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("MGS3", noteannFacade.rappelSeq(classe).get(2));
            parameters.put("MGS4", noteannFacade.rappelSeq(classe).get(3));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("TR3", noteannFacade.rappelseqTaux(classe).get(2));
            parameters.put("TR4", noteannFacade.rappelseqTaux(classe).get(3));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("MGT2", noteannFacade.rappelTrim(classe).get(1));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));
            parameters.put("TRT2", noteannFacade.rappelTimTaux(classe).get(1));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_SEQ4" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq5() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteseq5Facade.synthSeq5(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHSEQ5.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = noteseq5Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = noteseq5Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("MGS3", noteannFacade.rappelSeq(classe).get(2));
            parameters.put("MGS4", noteannFacade.rappelSeq(classe).get(3));
            parameters.put("MGS5", noteannFacade.rappelSeq(classe).get(4));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("TR3", noteannFacade.rappelseqTaux(classe).get(2));
            parameters.put("TR4", noteannFacade.rappelseqTaux(classe).get(3));
            parameters.put("TR5", noteannFacade.rappelseqTaux(classe).get(4));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("MGT2", noteannFacade.rappelTrim(classe).get(1));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));
            parameters.put("TRT2", noteannFacade.rappelTimTaux(classe).get(1));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_SEQ5" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthSeq6() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteseq6Facade.synthSeq6(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHSEQ6.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = noteseq6Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = noteseq6Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("MGS3", noteannFacade.rappelSeq(classe).get(2));
            parameters.put("MGS4", noteannFacade.rappelSeq(classe).get(3));
            parameters.put("MGS5", noteannFacade.rappelSeq(classe).get(4));
            parameters.put("MGS6", noteannFacade.rappelSeq(classe).get(5));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("TR3", noteannFacade.rappelseqTaux(classe).get(2));
            parameters.put("TR4", noteannFacade.rappelseqTaux(classe).get(3));
            parameters.put("TR5", noteannFacade.rappelseqTaux(classe).get(4));
            parameters.put("TR6", noteannFacade.rappelseqTaux(classe).get(5));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("MGT2", noteannFacade.rappelTrim(classe).get(1));
            parameters.put("MGT3", noteannFacade.rappelTrim(classe).get(2));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));
            parameters.put("TRT2", noteannFacade.rappelTimTaux(classe).get(1));
            parameters.put("TRT3", noteannFacade.rappelTimTaux(classe).get(2));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_SEQ6" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthTrim1() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = notetrim1Facade.synthTrim1(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHTRIM1.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = notetrim1Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = notetrim1Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_TRIM1" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthTrim2() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = notetrim2Facade.synthTrim2(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHTRIM2.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = notetrim2Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = notetrim2Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("MGS3", noteannFacade.rappelSeq(classe).get(2));
            parameters.put("MGS4", noteannFacade.rappelSeq(classe).get(3));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("TR3", noteannFacade.rappelseqTaux(classe).get(2));
            parameters.put("TR4", noteannFacade.rappelseqTaux(classe).get(3));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("MGT2", noteannFacade.rappelTrim(classe).get(1));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));
            parameters.put("TRT2", noteannFacade.rappelTimTaux(classe).get(1));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_TRIM2" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthTrim3() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = notetrim3Facade.synthTrim3(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHTRIM3.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = notetrim3Facade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = notetrim3Facade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("MGS3", noteannFacade.rappelSeq(classe).get(2));
            parameters.put("MGS4", noteannFacade.rappelSeq(classe).get(3));
            parameters.put("MGS5", noteannFacade.rappelSeq(classe).get(4));
            parameters.put("MGS6", noteannFacade.rappelSeq(classe).get(5));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("TR3", noteannFacade.rappelseqTaux(classe).get(2));
            parameters.put("TR4", noteannFacade.rappelseqTaux(classe).get(3));
            parameters.put("TR5", noteannFacade.rappelseqTaux(classe).get(4));
            parameters.put("TR6", noteannFacade.rappelseqTaux(classe).get(5));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("MGT2", noteannFacade.rappelTrim(classe).get(1));
            parameters.put("MGT3", noteannFacade.rappelTrim(classe).get(2));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));
            parameters.put("TRT2", noteannFacade.rappelTimTaux(classe).get(1));
            parameters.put("TRT3", noteannFacade.rappelTimTaux(classe).get(2));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_TRIM3" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheSynthAnn() throws IOException {
        try {
            List<SynthAnn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteannFacade.synthAnn(classe);
            for (Object[] uneligne : objetList) {
                SynthAnn synth = new SynthAnn();
                synth.setCodematiere((String) uneligne[0]);
                synth.setEffectif(((Long) uneligne[1]).intValue());
                synth.setEff0_6(((Long) uneligne[2]).intValue());
                synth.setEff6_8(((Long) uneligne[3]).intValue());
                synth.setEff8_9(((Long) uneligne[4]).intValue());
                synth.setEff9_10(((Long) uneligne[5]).intValue());
                synth.setEff10_12(((Long) uneligne[6]).intValue());
                synth.setEff12_14(((Long) uneligne[7]).intValue());
                synth.setEff14_16(((Long) uneligne[8]).intValue());
                synth.setEff16_18(((Long) uneligne[9]).intValue());
                synth.setEff18_20(((Long) uneligne[10]).intValue());
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/SYNTHANN.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            List<Object[]> objetListInterval = noteannFacade.effG_F8_par_interval(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("G6", ligne[9]);
                parameters.put("F6", ligne[0]);
                parameters.put("G8", ligne[10]);
                parameters.put("F8", ligne[1]);
                parameters.put("G9", ligne[11]);
                parameters.put("F9", ligne[2]);
                parameters.put("G10", ligne[12]);
                parameters.put("F10", ligne[3]);
                parameters.put("G12", ligne[13]);
                parameters.put("F12", ligne[4]);
                parameters.put("G14", ligne[14]);
                parameters.put("F14", ligne[5]);
                parameters.put("G16", ligne[15]);
                parameters.put("F16", ligne[6]);
                parameters.put("G18", ligne[16]);
                parameters.put("F18", ligne[7]);
                parameters.put("G20", ligne[17]);
                parameters.put("F20", ligne[8]);
            }
            List<Object[]> objetListMoyenne = noteannFacade.Moy_premier_Dernier(classe);
            for (Object[] lignemoy : objetListMoyenne) {
                parameters.put("MG", lignemoy[0]);
                parameters.put("Mpremier", lignemoy[1]);
                parameters.put("Mdernier", lignemoy[2]);
            }
            parameters.put("effectif_Garcon", noteannFacade.effG(classe).get(0));
            parameters.put("effectis_Fille", noteannFacade.effF(classe).get(0));
            parameters.put("MGS1", noteannFacade.rappelSeq(classe).get(0));
            parameters.put("MGS2", noteannFacade.rappelSeq(classe).get(1));
            parameters.put("MGS3", noteannFacade.rappelSeq(classe).get(2));
            parameters.put("MGS4", noteannFacade.rappelSeq(classe).get(3));
            parameters.put("MGS5", noteannFacade.rappelSeq(classe).get(4));
            parameters.put("MGS6", noteannFacade.rappelSeq(classe).get(5));
            parameters.put("TR1", noteannFacade.rappelseqTaux(classe).get(0));
            parameters.put("TR2", noteannFacade.rappelseqTaux(classe).get(1));
            parameters.put("TR3", noteannFacade.rappelseqTaux(classe).get(2));
            parameters.put("TR4", noteannFacade.rappelseqTaux(classe).get(3));
            parameters.put("TR5", noteannFacade.rappelseqTaux(classe).get(4));
            parameters.put("TR6", noteannFacade.rappelseqTaux(classe).get(5));
            parameters.put("MGT1", noteannFacade.rappelTrim(classe).get(0));
            parameters.put("MGT2", noteannFacade.rappelTrim(classe).get(1));
            parameters.put("MGT3", noteannFacade.rappelTrim(classe).get(2));
            parameters.put("TRT1", noteannFacade.rappelTimTaux(classe).get(0));
            parameters.put("TRT2", noteannFacade.rappelTimTaux(classe).get(1));
            parameters.put("TRT3", noteannFacade.rappelTimTaux(classe).get(2));

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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_SYNTH_ANN" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
    }

    public String imprimerFicheStat() throws IOException {
        try {
            List<SynthTrimAn> synthannList = new ArrayList<>();
            List<Object[]> objetList = noteannFacade.synthAnn_Trim(classe);
            for (int i = 1; i <= 6; i++) {
                SynthTrimAn synth = new SynthTrimAn();
                Object[] next = objetList.get(i - 1);
                synth.setPeriode((String) next[0]);
                synth.setMoyenne((BigDecimal) next[1]);
                synth.setTauxreussite((String) next[2]);
                synthannList.add(synth);
            }
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(synthannList, false);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/Stat_Synth/STATan_trim.jrxml");
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("Pcodeclasse", classe);
            parameters.put("pp", conseilannFacade.pp(classe));
            List<Object[]> objetListPresentAdmisT1 = conseiltrim1Facade.ficheStatT1(classe);
            for (Object[] ligne : objetListPresentAdmisT1) {
                parameters.put("effGP1", ligne[0]);
                parameters.put("effFP1", ligne[1]);
                parameters.put("effGA1", ligne[2]);
                parameters.put("effFA1", ligne[3]);
            }
            List<Object[]> objetListPresentAdmisT2 = conseiltrim2Facade.ficheStatT2(classe);
            for (Object[] ligne : objetListPresentAdmisT2) {
                parameters.put("effGP2", ligne[0]);
                parameters.put("effFP2", ligne[1]);
                parameters.put("effGA2", ligne[2]);
                parameters.put("effFA2", ligne[3]);
            }
            List<Object[]> objetListPresentAdmisT3 = conseiltrim3Facade.ficheStatT3(classe);
            for (Object[] ligne : objetListPresentAdmisT3) {
                parameters.put("effGP3", ligne[0]);
                parameters.put("effFP3", ligne[1]);
                parameters.put("effGA3", ligne[2]);
                parameters.put("effFA3", ligne[3]);
            }
            List<Object[]> objetListPresentAdmisA = conseilannFacade.ficheStatAnn(classe);
            for (Object[] ligne : objetListPresentAdmisA) {
                parameters.put("AAG", ligne[0]);
                parameters.put("AAF", ligne[1]);
                parameters.put("RAG", ligne[2]);
                parameters.put("RAF", ligne[3]);
                parameters.put("EAG", ligne[4]);
                parameters.put("EAF", ligne[5]);
            }
            List<Object[]> objetListInterval = noteannFacade.synthAnn_Trim1(classe);
            for (Object[] ligne : objetListInterval) {
                parameters.put("T1Gsup12", ligne[0]);
                parameters.put("T1Gsup10", ligne[1]);
                parameters.put("T1G950_999", ligne[2]);
                parameters.put("T1G925_949", ligne[3]);
                parameters.put("T1G9_924", ligne[4]);
                parameters.put("T1G750_899", ligne[5]);
                parameters.put("T1Ginf899", ligne[6]);
                parameters.put("T1Ginf749", ligne[7]);
                parameters.put("T1THEG", ligne[8]);
                parameters.put("T1THFG", ligne[9]);
                parameters.put("T1Fsup12", ligne[10]);
                parameters.put("T1Fsup10", ligne[11]);
                parameters.put("T1F950_999", ligne[12]);
                parameters.put("T1F925_949", ligne[13]);
                parameters.put("T1F9_924", ligne[14]);
                parameters.put("T1F750_899", ligne[15]);
                parameters.put("T1Finf899", ligne[16]);
                parameters.put("T1Finf749", ligne[17]);
                parameters.put("T1THEF", ligne[18]);
                parameters.put("T1THFF", ligne[19]);
                parameters.put("T1Mpremier", ligne[20]);
                parameters.put("T1Mdernier", ligne[21]);
                parameters.put("T1MG", noteannFacade.moyGene_Trim_An(classe).get(1));
            }
            List<Object[]> objetListInterval2 = noteannFacade.synthAnn_Trim2(classe);
            for (Object[] ligne : objetListInterval2) {
                parameters.put("T2Gsup12", ligne[0]);
                parameters.put("T2Gsup10", ligne[1]);
                parameters.put("T2G950_999", ligne[2]);
                parameters.put("T2G925_949", ligne[3]);
                parameters.put("T2G9_924", ligne[4]);
                parameters.put("T2G750_899", ligne[5]);
                parameters.put("T2Ginf899", ligne[6]);
                parameters.put("T2Ginf749", ligne[7]);
                parameters.put("T2THEG", ligne[8]);
                parameters.put("T2THFG", ligne[9]);
                parameters.put("T2Fsup12", ligne[10]);
                parameters.put("T2Fsup10", ligne[11]);
                parameters.put("T2F950_999", ligne[12]);
                parameters.put("T2F925_949", ligne[13]);
                parameters.put("T2F9_924", ligne[14]);
                parameters.put("T2F750_899", ligne[15]);
                parameters.put("T2Finf899", ligne[16]);
                parameters.put("T2Finf749", ligne[17]);
                parameters.put("T2THEF", ligne[18]);
                parameters.put("T2THFF", ligne[19]);
                parameters.put("T2Mpremier", ligne[20]);
                parameters.put("T2Mdernier", ligne[21]);
                parameters.put("T2MG", noteannFacade.moyGene_Trim_An(classe).get(2));
            }
            List<Object[]> objetListInterval3 = noteannFacade.synthAnn_Trim3(classe);
            for (Object[] ligne : objetListInterval3) {
                parameters.put("T3Gsup12", ligne[0]);
                parameters.put("T3Gsup10", ligne[1]);
                parameters.put("T3G950_999", ligne[2]);
                parameters.put("T3G925_949", ligne[3]);
                parameters.put("T3G9_924", ligne[4]);
                parameters.put("T3G750_899", ligne[5]);
                parameters.put("T3Ginf899", ligne[6]);
                parameters.put("T3Ginf749", ligne[7]);
                parameters.put("T3THEG", ligne[8]);
                parameters.put("T3THFG", ligne[9]);
                parameters.put("T3Fsup12", ligne[10]);
                parameters.put("T3Fsup10", ligne[11]);
                parameters.put("T3F950_999", ligne[12]);
                parameters.put("T3F925_949", ligne[13]);
                parameters.put("T3F9_924", ligne[14]);
                parameters.put("T3F750_899", ligne[15]);
                parameters.put("T3Finf899", ligne[16]);
                parameters.put("T3Finf749", ligne[17]);
                parameters.put("T3THEF", ligne[18]);
                parameters.put("T3THFF", ligne[19]);
                parameters.put("T3Mpremier", ligne[20]);
                parameters.put("T3Mdernier", ligne[21]);
                parameters.put("T3MG", noteannFacade.moyGene_Trim_An(classe).get(3));
            }
            List<Object[]> objetListIntervalan = noteannFacade.synthAnn_An(classe);
            for (Object[] ligne : objetListIntervalan) {
                parameters.put("AGsup12", ligne[0]);
                parameters.put("AGsup10", ligne[1]);
                parameters.put("AG950_999", ligne[2]);
                parameters.put("AG925_949", ligne[3]);
                parameters.put("AG9_924", ligne[4]);
                parameters.put("AG750_899", ligne[5]);
                parameters.put("AGinf899", ligne[6]);
                parameters.put("AGinf749", ligne[7]);
                parameters.put("ATHEG", ligne[8]);
                parameters.put("ATHFG", ligne[9]);
                parameters.put("AFsup12", ligne[10]);
                parameters.put("AFsup10", ligne[11]);
                parameters.put("AF950_999", ligne[12]);
                parameters.put("AF925_949", ligne[13]);
                parameters.put("AF9_924", ligne[14]);
                parameters.put("AF750_899", ligne[15]);
                parameters.put("AFinf899", ligne[16]);
                parameters.put("AFinf749", ligne[17]);
                parameters.put("ATHEF", ligne[18]);
                parameters.put("ATHFF", ligne[19]);
                parameters.put("AMpremier", ligne[20]);
                parameters.put("AMdernier", ligne[21]);
                parameters.put("AMG", noteannFacade.moyGene_Trim_An(classe).get(0));
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=FICHE_STAT_ANN_TRIM" + "_" + classe + "_" + "pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ficheSynth.xhtml?faces-redirect=true";
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
