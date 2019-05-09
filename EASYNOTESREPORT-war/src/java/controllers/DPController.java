/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Classes;
import entities.Donnespedagogiques;
import entities.DonnespedagogiquesPK;
import entities.Dp;
import entities.Ensgclamat;
import entities.Matieres;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
import org.primefaces.component.commandbutton.CommandButton;
import sessions.ClamatcoeffgrpeFacadeLocal;
import sessions.DonnespedagogiquesFacadeLocal;
import sessions.EnsgclamatFacadeLocal;
import sessions.EtablissementinfosFacadeLocal;
import sessions.Notesdeselevesseq1FacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "dPController")
@SessionScoped
public class DPController implements Serializable {

    @EJB
    private EtablissementinfosFacadeLocal etsFacade;
    @EJB
    private DonnespedagogiquesFacadeLocal dpFacade;
    @EJB
    private EnsgclamatFacadeLocal affecEnseigFacade;
    @EJB
    private ClamatcoeffgrpeFacadeLocal affectationFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    private List<Donnespedagogiques> dpList = new ArrayList<>();
    private Donnespedagogiques dp;
    private DonnespedagogiquesPK dpk;
    private Ensgclamat affecEnseigClass;
    private boolean TestMsgLogin = false;
    private boolean champActif = true;
    private boolean donneePexiste = false;
    private String section;
    private String operation = "add";
    public static String pwd;
    private BigDecimal effSupA10;
    private BigDecimal Mg;
    private String typeImpression;
    private boolean seq1;
    private boolean seq2;
    private boolean seq3;
    private boolean seq4;
    private boolean seq5;
    private boolean seq6;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;

    public DPController() {
    }

    public String donneesPedagogiques() {
        pouraccueil();
        return "mdp.xhtml?faces-redirect=true";
    }

    public String statDP() {
        pouraccueil();
        return "statDP.xhtml?faces-redirect=true";
    }
//retourne la liste des la liste des dp pour consultations 

    public String donneesPedagogiquesList() {
        dpList.clear();
        dpList.addAll(dpFacade.findAll());
        dp = new Donnespedagogiques();
        return "dp.xhtml?faces-redirect=true";
    }
//retourne une seule ligne dp classe_matière pour modification

    public String donneesPedagogiquesParMatiereDeLaClasse() {
        if (noteseq1Facade.findEnseignatByPwd(pwd, ManagedSeqController.matiere.toString())) {
            dpList.clear();
            if (dpFacade.listeDesDp(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString(), ManagedSeqController.seq) != null) {
                dpList.addAll(dpFacade.listeDesDp(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString(), ManagedSeqController.seq));
            }
            dp = new Donnespedagogiques();
            return "saisiedp.xhtml?faces-redirect=true";
        } else {
            TestMsgLogin = true;
            return "";
        }

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

    public List<Matieres> listeMatieresparClasse() {
        try {
            if (dpFacade.listedesMatieres(dp.getDonnespedagogiquesPK().getNiveaux()) != null) {
                return affecEnseigFacade.listedesMatieres(dp.getDonnespedagogiquesPK().getNiveaux());
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

    public void initialisation() {
        TestMsgLogin = false;
        pwd = "";
    }

    public List<Classes> listedesNiveaux() {
        List<Classes> listC = dpFacade.listedesNiveaux();
        return listC;
    }

    public List<Object[]> listeMatieresParClass() {
        if (ManagedSeqController.classe == null) {
            return null;
        } else {
            List<Object[]> listM = dpFacade.listedesMatieresParClasse(ManagedSeqController.classe.toString());
            return listM;
        }
    }

    public void effMoySupA10() {
        effSupA10 = dpFacade.moySup10(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
        Mg = dpFacade.mg(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
    }

    public void effMoySupA10S2() {
        effSupA10 = dpFacade.moySup10S2(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
        Mg = dpFacade.mgS2(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
    }

    public void effMoySupA10S3() {
        effSupA10 = dpFacade.moySup10S3(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
        Mg = dpFacade.mgS3(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
    }

    public void effMoySupA10S4() {
        effSupA10 = dpFacade.moySup10S4(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
        Mg = dpFacade.mgS4(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
    }

    public void effMoySupA10S5() {
        effSupA10 = dpFacade.moySup10S5(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
        Mg = dpFacade.mgS5(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
    }

    public void effMoySupA10S6() {
        effSupA10 = dpFacade.moySup10S6(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
        Mg = dpFacade.mgS6(ManagedSeqController.classe.toString(), ManagedSeqController.matiere.toString());
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                dp = new Donnespedagogiques();
                switch (ManagedSeqController.seq) {
                    case "1":
                        effMoySupA10();
                        dp.setNn(effSupA10);
                        dp.setMg(Mg);
                        dp.setSection(dpFacade.section(ManagedSeqController.classe.toString()));
                        dp.setTypedeclasse(dpFacade.typeClasse(ManagedSeqController.classe.toString()));
                        dp.setEff(dpFacade.effectifParNiveau(ManagedSeqController.classe.toString()));
                        dp.setNae(dpFacade.effectifAbst(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                        break;
                    case "2":
                        effMoySupA10S2();
                        dp.setNn(effSupA10);
                        dp.setMg(Mg);
                        dp.setSection(dpFacade.section(ManagedSeqController.classe.toString()));
                        dp.setTypedeclasse(dpFacade.typeClasse(ManagedSeqController.classe.toString()));
                        dp.setEff(dpFacade.effectifParNiveau(ManagedSeqController.classe.toString()));
                        dp.setNae(dpFacade.effectifAbstS2(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                        break;
                    case "3":
                        effMoySupA10S3();
                        dp.setNn(effSupA10);
                        dp.setMg(Mg);
                        dp.setSection(dpFacade.section(ManagedSeqController.classe.toString()));
                        dp.setTypedeclasse(dpFacade.typeClasse(ManagedSeqController.classe.toString()));
                        dp.setEff(dpFacade.effectifParNiveau(ManagedSeqController.classe.toString()));
                        dp.setNae(dpFacade.effectifAbstS3(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                        break;
                    case "4":
                        effMoySupA10S4();
                        dp.setNn(effSupA10);
                        dp.setMg(Mg);
                        dp.setSection(dpFacade.section(ManagedSeqController.classe.toString()));
                        dp.setTypedeclasse(dpFacade.typeClasse(ManagedSeqController.classe.toString()));
                        dp.setEff(dpFacade.effectifParNiveau(ManagedSeqController.classe.toString()));
                        dp.setNae(dpFacade.effectifAbstS4(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                        break;
                    case "5":
                        effMoySupA10S5();
                        dp.setNn(effSupA10);
                        dp.setMg(Mg);
                        dp.setSection(dpFacade.section(ManagedSeqController.classe.toString()));
                        dp.setTypedeclasse(dpFacade.typeClasse(ManagedSeqController.classe.toString()));
                        dp.setEff(dpFacade.effectifParNiveau(ManagedSeqController.classe.toString()));
                        dp.setNae(dpFacade.effectifAbstS5(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                        break;
                    case "6":
                        effMoySupA10S6();
                        dp.setNn(effSupA10);
                        dp.setMg(Mg);
                        dp.setSection(dpFacade.section(ManagedSeqController.classe.toString()));
                        dp.setTypedeclasse(dpFacade.typeClasse(ManagedSeqController.classe.toString()));
                        dp.setEff(dpFacade.effectifParNiveau(ManagedSeqController.classe.toString()));
                        dp.setNae(dpFacade.effectifAbstS6(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
                        break;
                }
                break;
            case "modifier":
                TestMsgLogin = false;
                champActif = true;
                dp = new Donnespedagogiques();
                effMoySupA10();
                dp.setNn(effSupA10);
                dp.setMg(Mg);
                dp.setSection(dpFacade.section(ManagedSeqController.classe.toString()));
                dp.setTypedeclasse(dpFacade.typeClasse(ManagedSeqController.classe.toString()));
                dp.setEff(dpFacade.effectifParNiveau(ManagedSeqController.classe.toString()));
                dp.setNae(dpFacade.effectifAbst(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString()));
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

    public String persistEleves() throws InterruptedException {
        switch (operation) {
            case "ajouter":
                dpk = new DonnespedagogiquesPK(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString(), ManagedSeqController.seq);
                dp.setDonnespedagogiquesPK(dpk);
                dpFacade.create(dp);
                break;
            case "modifier":
                dpk = new DonnespedagogiquesPK(ManagedSeqController.matiere.toString(), ManagedSeqController.classe.toString(), ManagedSeqController.seq);
                dp.setDonnespedagogiquesPK(dpk);
                dpFacade.edit(dp);
                break;
            case "supprimer":
                dpFacade.remove(dp);
                break;
        }
        return donneesPedagogiquesParMatiereDeLaClasse();
    }

    public String imprimerDP() {
        try {
            switch (typeImpression) {
                case "Intermédiaire":
                    imprimExamInter();
                    donneePexiste = true;
                    break;
                case "Examen":
                    imprimExamInter();
                    break;
                case "Toutes les matières":
                    imprimFAPTout();
                    break;
                default:
                    imprimFAPparMatiere();
                    break;
            }
        } catch (Exception e) {
        }
        return "";
    }

    public void imprimExamInter() {
        try {
            List<Dp> donneesList = new ArrayList<>();
            List<Object[]> objetList = dpFacade.ficheExamInter(typeImpression, ManagedSeqController.seq, section);
            for (Object[] uneligne : objetList) {
                Dp donneeP = new Dp();
                donneeP.setCodematiere((String) uneligne[0]);
                donneeP.setPeriode((String) uneligne[1]);
                donneeP.setEff((BigDecimal) uneligne[2]);
                donneeP.setLpcpt((BigDecimal) uneligne[3]);
                donneeP.setLfcpt((BigDecimal) uneligne[4]);
                donneeP.setLpcpp((BigDecimal) uneligne[5]);
                donneeP.setLfcpp((BigDecimal) uneligne[6]);
                donneeP.setHpchc((BigDecimal) uneligne[7]);
                donneeP.setHfchc((BigDecimal) uneligne[8]);
                donneeP.setNn((BigDecimal) uneligne[9]);
                donneeP.setMg((BigDecimal) uneligne[10]);
                donneeP.setNae((BigDecimal) uneligne[11]);
                donneeP.setNsc((BigDecimal) uneligne[12]);
                donneesList.add(donneeP);
            }
            String reportPath = "";
            switch (typeImpression) {
                case "Intermédiaire":
                    reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/donneesPedagogiques/dpIntermediaire.jrxml");
                    break;
                case "Examen":
                    reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/donneesPedagogiques/dpIExamen.jrxml");
                    break;
            }

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(donneesList, false);
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("section", section);
            parameters.put("periode", ManagedSeqController.seq);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=Fiche" + "_" + typeImpression + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {

        }
    }

    public void imprimFAPTout() {
        try {
            List<Dp> donneesList = new ArrayList<>();
            List<Object[]> objetList = dpFacade.ficheAPall(ManagedSeqController.seq, section);
            for (Object[] uneligne : objetList) {
                Dp donneeP = new Dp();
                donneeP.setCodematiere((String) uneligne[0]);
                donneeP.setPeriode((String) uneligne[1]);
                donneeP.setEff((BigDecimal) uneligne[2]);
                donneeP.setLpcpt((BigDecimal) uneligne[3]);
                donneeP.setLfcpt((BigDecimal) uneligne[4]);
                donneeP.setLpcpp((BigDecimal) uneligne[5]);
                donneeP.setLfcpp((BigDecimal) uneligne[6]);
                donneeP.setHpchc((BigDecimal) uneligne[7]);
                donneeP.setHfchc((BigDecimal) uneligne[8]);
                donneeP.setNn((BigDecimal) uneligne[9]);
                donneeP.setMg((BigDecimal) uneligne[10]);
                donneeP.setNae((BigDecimal) uneligne[11]);
                donneeP.setNsc((BigDecimal) uneligne[12]);
                donneeP.setNiveaux((String) uneligne[13]);
                donneeP.setTypedeclasse((String) uneligne[14]);
                donneesList.add(donneeP);
            }
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/donneesPedagogiques/dpConseil.jrxml");
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(donneesList, false);
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("section", section);
            parameters.put("periode", ManagedSeqController.seq);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=Fiche" + "_" + typeImpression + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {

        }
    }

    public void imprimFAPparMatiere() {
        try {
            List<Dp> donneesList = new ArrayList<>();
            List<Object[]> objetList = dpFacade.ficheApParMatiere(ManagedSeqController.seq, typeImpression, section);
            for (Object[] uneligne : objetList) {
                Dp donneeP = new Dp();
                donneeP.setCodematiere((String) uneligne[0]);
                donneeP.setPeriode((String) uneligne[1]);
                donneeP.setEff((BigDecimal) uneligne[2]);
                donneeP.setLpcpt((BigDecimal) uneligne[3]);
                donneeP.setLfcpt((BigDecimal) uneligne[4]);
                donneeP.setLpcpp((BigDecimal) uneligne[5]);
                donneeP.setLfcpp((BigDecimal) uneligne[6]);
                donneeP.setHpchc((BigDecimal) uneligne[7]);
                donneeP.setHfchc((BigDecimal) uneligne[8]);
                donneeP.setNn((BigDecimal) uneligne[9]);
                donneeP.setMg((BigDecimal) uneligne[10]);
                donneeP.setNae((BigDecimal) uneligne[11]);
                donneeP.setNsc((BigDecimal) uneligne[12]);
                donneeP.setNiveaux((String) uneligne[13]);
                donneeP.setTypedeclasse((String) uneligne[14]);
                donneesList.add(donneeP);
            }
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/donneesPedagogiques/dpConseilParMatiere.jrxml");
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(donneesList, false);
            HashMap parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            parameters.put("periode", ManagedSeqController.seq);
            parameters.put("section", section);
            parameters.put("matiere", typeImpression);
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
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=Fiche" + "_" + typeImpression + "_pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {

        }
    }

    public void pouraccueil() {
        BigDecimal valeurBigDecimal = etsFacade.acti_deative_seq();
        String valeur = valeurBigDecimal.toString();
        s1 = valeur.substring(0, 1);
        s2 = valeur.substring(1, 2);
        s3 = valeur.substring(2, 3);
        s4 = valeur.substring(3, 4);
        s5 = valeur.substring(4, 5);
        s6 = valeur.substring(5, 6);
        switch (s1) {
            case "1":
                seq1 = false;
                break;
            case "2":
                seq1 = true;
        }
        switch (s2) {
            case "1":
                seq2 = false;
                break;
            case "2":
                seq2 = true;
        }
        switch (s3) {
            case "1":
                seq3 = false;
                break;
            case "2":
                seq3 = true;
        }
        switch (s4) {
            case "1":
                seq4 = false;
                break;
            case "2":
                seq4 = true;
        }
        switch (s5) {
            case "1":
                seq5 = false;
                break;
            case "2":
                seq5 = true;
        }
        switch (s6) {
            case "1":
                seq6 = false;
                break;
            case "2":
                seq6 = true;
        }
    }

    public Donnespedagogiques getDp() {
        return dp;
    }

    public void setDp(Donnespedagogiques dp) {
        this.dp = dp;
    }

    public List<Donnespedagogiques> getDpList() {
        return dpList;
    }

    public void setDpList(List<Donnespedagogiques> dpList) {
        this.dpList = dpList;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        DPController.pwd = pwd;
    }

    public String getTypeImpression() {
        return typeImpression;
    }

    public void setTypeImpression(String typeImpression) {
        this.typeImpression = typeImpression;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public boolean isSeq1() {
        return seq1;
    }

    public void setSeq1(boolean seq1) {
        this.seq1 = seq1;
    }

    public boolean isSeq2() {
        return seq2;
    }

    public void setSeq2(boolean seq2) {
        this.seq2 = seq2;
    }

    public boolean isSeq3() {
        return seq3;
    }

    public void setSeq3(boolean seq3) {
        this.seq3 = seq3;
    }

    public boolean isSeq4() {
        return seq4;
    }

    public void setSeq4(boolean seq4) {
        this.seq4 = seq4;
    }

    public boolean isSeq5() {
        return seq5;
    }

    public void setSeq5(boolean seq5) {
        this.seq5 = seq5;
    }

    public boolean isSeq6() {
        return seq6;
    }

    public void setSeq6(boolean seq6) {
        this.seq6 = seq6;
    }

    public boolean isDonneePexiste() {
        return donneePexiste;
    }

    public void setDonneePexiste(boolean donneePexiste) {
        this.donneePexiste = donneePexiste;
    }

    public boolean isTestMsgLogin() {
        return TestMsgLogin;
    }

    public void setTestMsgLogin(boolean TestMsgLogin) {
        this.TestMsgLogin = TestMsgLogin;
    }

    public boolean isChampActif() {
        return champActif;
    }

    public void setChampActif(boolean champActif) {
        this.champActif = champActif;
    }

}
