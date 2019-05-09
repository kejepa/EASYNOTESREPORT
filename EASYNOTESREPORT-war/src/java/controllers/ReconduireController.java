/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Classes;
import entities.Listedeseleves;
import entities.Matieres;
import entities.Notesdeselevesseq2;
import entities.Notesdeselevesseq2PK;
import entities.Notesdeselevesseq3;
import entities.Notesdeselevesseq3PK;
import entities.Notesdeselevesseq4;
import entities.Notesdeselevesseq4PK;
import entities.Notesdeselevesseq5;
import entities.Notesdeselevesseq5PK;
import entities.Notesdeselevesseq6;
import entities.Notesdeselevesseq6PK;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import sessions.EnsgclamatFacadeLocal;
import sessions.ListepasswordFacadeLocal;
import sessions.NotesdeselevesannFacadeLocal;
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
@Named(value = "reconduireController")
@SessionScoped
public class ReconduireController implements Serializable {

    @EJB
    private Notesdeselevesseq1FacadeLocal noteseq1Facade;
    @EJB
    private NotesdeselevesannFacadeLocal notannFacade;
    @EJB
    private Notesdeselevesseq1FacadeLocal notseq1Facade;
    @EJB
    private Notesdeselevesseq2FacadeLocal notseq2Facade;
    @EJB
    private Notesdeselevesseq3FacadeLocal notseq3Facade;
    @EJB
    private Notesdeselevesseq4FacadeLocal notseq4Facade;
    @EJB
    private Notesdeselevesseq5FacadeLocal notseq5Facade;
    @EJB
    private Notesdeselevesseq6FacadeLocal notseq6Facade;
    @EJB
    private EnsgclamatFacadeLocal affecEnseigFacade;
    @EJB
    private ListepasswordFacadeLocal pwdFacade;
    private List<Notesdeselevesseq2> noteseq2Liste = new ArrayList<>();
    private List<Notesdeselevesseq3> noteseq3Liste = new ArrayList<>();
    private List<Notesdeselevesseq4> noteseq4Liste = new ArrayList<>();
    private List<Notesdeselevesseq5> noteseq5Liste = new ArrayList<>();
    private List<Notesdeselevesseq6> noteseq6Liste = new ArrayList<>();
    private Notesdeselevesseq2 noteseq2;
    private Notesdeselevesseq3 noteseq3;
    private Notesdeselevesseq4 noteseq4;
    private Notesdeselevesseq5 noteseq5;
    private Notesdeselevesseq6 noteseq6;
    private Listedeseleves mat;
    private String seqcible;
    private String seqdestin;
    private String classe;
    private String matiere;
    private boolean msgSelect = false;
    private boolean TestMsgLogin = false;

    public ReconduireController() {
    }

    public String reconduireNotes() {
        return "reconduirenotes.xhtml?faces-redirect=true";
    }

    public void initialisation() {
        TestMsgLogin = false;
        ManagedSeqController.pwd = ""; 
    }

    public List<Object[]> listeMatieresParClass() {
        if (classe == null) {
            return null;
        } else {
            List<Object[]> listM = notseq1Facade.listedesMatieresParClasse(classe);
            return listM;
        }
    }

    public void verifierSeqClasseMatiere() {
        if (seqcible == null) {
            msgSelect = true;
        }
        if (seqdestin == null) {
            msgSelect = true;
        }
        if (classe == null) {
            msgSelect = true;
        }
        if (matiere == null) {
            msgSelect = true;
        }
    }

    public List<Classes> listeClasses() {
        List<Classes> listC = affecEnseigFacade.listedesClasses();
        return listC;
    }

    public String teacher() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return "";
        } else {
            String teach = pwdFacade.Enseignat_de_la_Matiere(ManagedSeqController.pwd, matiere);
            return teach;
        }
    }

    public int composer2() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq2Facade.eleveComposé(matiere, classe);
            return eff;
        }
    }

    public int noncomposer2() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq2Facade.eleveAbsent(matiere, classe);
            return eff;
        }
    }

    public BigDecimal moyenne2() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal moy = notseq2Facade.findMoyenne(matiere, classe);
            return moy;
        }
    }

    public BigDecimal tauxReussite2() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal taux = notseq2Facade.findTauxreussite(matiere, classe);
            return taux;
        }
    }

    public BigDecimal pludGrandeNote2() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pg = notseq2Facade.findGandeMoyenne(matiere, classe);
            return pg;
        }
    }

    public BigDecimal pludPetiteNote2() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pp = notseq2Facade.findPetiteMoyenne(matiere, classe);
            return pp;
        }
    }

    public int composer3() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq3Facade.eleveComposé(matiere, classe);
            return eff;
        }
    }

    public int noncomposer3() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq3Facade.eleveAbsent(matiere, classe);
            return eff;
        }
    }

    public BigDecimal moyenne3() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal moy = notseq3Facade.findMoyenne(matiere, classe);
            return moy;
        }
    }

    public BigDecimal tauxReussite3() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal taux = notseq3Facade.findTauxreussite(matiere, classe);
            return taux;
        }
    }

    public BigDecimal pludGrandeNote3() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pg = notseq3Facade.findGandeMoyenne(matiere, classe);
            return pg;
        }
    }

    public BigDecimal pludPetiteNote3() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pp = notseq3Facade.findPetiteMoyenne(matiere, classe);
            return pp;
        }
    }

    public int composer4() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq4Facade.eleveComposé(matiere, classe);
            return eff;
        }
    }

    public int noncomposer4() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq4Facade.eleveAbsent(matiere, classe);
            return eff;
        }
    }

    public BigDecimal moyenne4() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal moy = notseq4Facade.findMoyenne(matiere, classe);
            return moy;
        }
    }

    public BigDecimal tauxReussite4() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal taux = notseq4Facade.findTauxreussite(matiere, classe);
            return taux;
        }
    }

    public BigDecimal pludGrandeNote4() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pg = notseq4Facade.findGandeMoyenne(matiere, classe);
            return pg;
        }
    }

    public BigDecimal pludPetiteNote4() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pp = notseq4Facade.findPetiteMoyenne(matiere, classe);
            return pp;
        }
    }

    public int composer5() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq5Facade.eleveComposé(matiere, classe);
            return eff;
        }
    }

    public int noncomposer5() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq5Facade.eleveAbsent(matiere, classe);
            return eff;
        }
    }

    public BigDecimal moyenne5() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal moy = notseq5Facade.findMoyenne(matiere, classe);
            return moy;
        }
    }

    public BigDecimal tauxReussite5() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal taux = notseq5Facade.findTauxreussite(matiere, classe);
            return taux;
        }
    }

    public BigDecimal pludGrandeNote5() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pg = notseq5Facade.findGandeMoyenne(matiere, classe);
            return pg;
        }
    }

    public BigDecimal pludPetiteNote5() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pp = notseq5Facade.findPetiteMoyenne(matiere, classe);
            return pp;
        }
    }

    public int composer6() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq6Facade.eleveComposé(matiere, classe);
            return eff;
        }
    }

    public int noncomposer6() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return 0;
        } else {
            int eff = notseq6Facade.eleveAbsent(matiere, classe);
            return eff;
        }
    }

    public BigDecimal moyenne6() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal moy = notseq6Facade.findMoyenne(matiere, classe);
            return moy;
        }
    }

    public BigDecimal tauxReussite6() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal taux = notseq6Facade.findTauxreussite(matiere, classe);
            return taux;
        }
    }

    public BigDecimal pludGrandeNote6() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pg = notseq6Facade.findGandeMoyenne(matiere, classe);
            return pg;
        }
    }

    public BigDecimal pludPetiteNote6() {
        if (ManagedSeqController.pwd == null || matiere == null) {
            return null;
        } else {
            BigDecimal pp = notseq6Facade.findPetiteMoyenne(matiere, classe);
            return pp;
        }
    }
//    public void actualisationmoyenne_tauxdereussite_pdm_ppm() {
//        moyenne();
//        tauxReussite();
//        pludGrandeNote();
//        pludPetiteNote();
//    }

    public String reconduire() throws IOException {
        if (noteseq1Facade.findEnseignatByPwd(ManagedSeqController.pwd, matiere)) {
            switch (seqcible) {
                case "1":
                    List<Object[]> objetList = notannFacade.notesSeq1DeLamatiere(classe, matiere);
                    if (objetList != null) {
                        switch (seqdestin) {
                            case "2":
                                for (Object[] uneligne : objetList) {
                                    Notesdeselevesseq2 nseq2 = new Notesdeselevesseq2();
                                    Notesdeselevesseq2PK nseqPK2 = new Notesdeselevesseq2PK((String) uneligne[0], matiere);
                                    nseq2.setNotesdeselevesseq2PK(nseqPK2);
                                    nseq2.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq2.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq2((String) uneligne[0], matiere) == null) {
                                        notseq2Facade.create(nseq2);
                                    } else {
                                        notseq2Facade.edit(nseq2);
                                    }
                                }
                                noteseq2Liste.clear();
                                noteseq2Liste.addAll(notseq2Facade.listeMatiereClasse(matiere, classe));
                                noteseq2 = new Notesdeselevesseq2();
                                return "notesreconduites2.xhtml?faces-redirect=true";
                            case "3":
                                for (Object[] uneligne : objetList) {
                                    Notesdeselevesseq3 nseq3 = new Notesdeselevesseq3();
                                    Notesdeselevesseq3PK nseqPK3 = new Notesdeselevesseq3PK((String) uneligne[0], matiere);
                                    nseq3.setNotesdeselevesseq3PK(nseqPK3);
                                    nseq3.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq3.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq3((String) uneligne[0], matiere) == null) {
                                        notseq3Facade.create(nseq3);
                                    } else {
                                        notseq3Facade.edit(nseq3);
                                    }
                                }
                                noteseq3Liste.clear();
                                noteseq3Liste.addAll(notseq3Facade.listeMatiereClasse(matiere, classe));
                                noteseq3 = new Notesdeselevesseq3();
                                return "notesreconduites3.xhtml?faces-redirect=true";
                            case "4":
                                for (Object[] uneligne : objetList) {
                                    Notesdeselevesseq4 nseq4 = new Notesdeselevesseq4();
                                    Notesdeselevesseq4PK nseqPK4 = new Notesdeselevesseq4PK((String) uneligne[0], matiere);
                                    nseq4.setNotesdeselevesseq4PK(nseqPK4);
                                    mat = new Listedeseleves((String) uneligne[0]);
                                    nseq4.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    if (notannFacade.verifierEleve_matiereSeq4((String) uneligne[0], matiere) == null) {
                                        notseq4Facade.create(nseq4);
                                    } else {
                                        notseq4Facade.edit(nseq4);
                                    }
                                }
                                noteseq4Liste.clear();
                                noteseq4Liste.addAll(notseq4Facade.listeMatiereClasse(matiere, classe));
                                noteseq4 = new Notesdeselevesseq4();
                                return "notesreconduites4.xhtml?faces-redirect=true";
                            case "5":
                                for (Object[] uneligne : objetList) {
                                    Notesdeselevesseq5 nseq5 = new Notesdeselevesseq5();
                                    Notesdeselevesseq5PK nseqPK5 = new Notesdeselevesseq5PK((String) uneligne[0], matiere);
                                    nseq5.setNotesdeselevesseq5PK(nseqPK5);
                                    nseq5.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq5.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq5((String) uneligne[0], matiere) == null) {
                                        notseq5Facade.create(nseq5);
                                    } else {
                                        notseq5Facade.edit(nseq5);
                                    }
                                }
                                noteseq5Liste.clear();
                                noteseq5Liste.addAll(notseq5Facade.listeMatiereClasse(matiere, classe));
                                noteseq5 = new Notesdeselevesseq5();
                                return "notesreconduites5.xhtml?faces-redirect=true";
                            case "6":
                                for (Object[] uneligne : objetList) {
                                    Notesdeselevesseq6 nseq6 = new Notesdeselevesseq6();
                                    Notesdeselevesseq6PK nseqPK6 = new Notesdeselevesseq6PK((String) uneligne[0], matiere);
                                    nseq6.setNotesdeselevesseq6PK(nseqPK6);
                                    nseq6.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq6.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq6((String) uneligne[0], matiere) == null) {
                                        notseq6Facade.create(nseq6);
                                    } else {
                                        notseq6Facade.edit(nseq6);
                                    }
                                }
                                noteseq6Liste.clear();
                                noteseq6Liste.addAll(notseq6Facade.listeMatiereClasse(matiere, classe));
                                noteseq6 = new Notesdeselevesseq6();
                                return "notesreconduites6.xhtml?faces-redirect=true";
                        }
                        break;
                    }
                case "2":
                    List<Object[]> objetList2 = notannFacade.notesSeq2DeLamatiere(classe, matiere);
                    if (objetList2 != null) {
                        switch (seqdestin) {
                            case "3":
                                for (Object[] uneligne : objetList2) {
                                    Notesdeselevesseq3 nseq3 = new Notesdeselevesseq3();
                                    Notesdeselevesseq3PK nseqPK3 = new Notesdeselevesseq3PK((String) uneligne[0], matiere);
                                    nseq3.setNotesdeselevesseq3PK(nseqPK3);
                                    nseq3.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq3.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq3((String) uneligne[0], matiere) == null) {
                                        notseq3Facade.create(nseq3);
                                    } else {
                                        notseq3Facade.edit(nseq3);
                                    }
                                }
                                noteseq3Liste.clear();
                                noteseq3Liste.addAll(notseq3Facade.listeMatiereClasse(matiere, classe));
                                noteseq3 = new Notesdeselevesseq3();
                                return "notesreconduites3.xhtml?faces-redirect=true";
                            case "4":
                                for (Object[] uneligne : objetList2) {
                                    Notesdeselevesseq4 nseq4 = new Notesdeselevesseq4();
                                    Notesdeselevesseq4PK nseqPK4 = new Notesdeselevesseq4PK((String) uneligne[0], matiere);
                                    nseq4.setNotesdeselevesseq4PK(nseqPK4);
                                    mat = new Listedeseleves((String) uneligne[0]);
                                    nseq4.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    if (notannFacade.verifierEleve_matiereSeq4((String) uneligne[0], matiere) == null) {
                                        notseq4Facade.create(nseq4);
                                    } else {
                                        notseq4Facade.edit(nseq4);
                                    }
                                }
                                noteseq4Liste.clear();
                                noteseq4Liste.addAll(notseq4Facade.listeMatiereClasse(matiere, classe));
                                noteseq4 = new Notesdeselevesseq4();
                                return "notesreconduites4.xhtml?faces-redirect=true";
                            case "5":
                                for (Object[] uneligne : objetList2) {
                                    Notesdeselevesseq5 nseq5 = new Notesdeselevesseq5();
                                    Notesdeselevesseq5PK nseqPK5 = new Notesdeselevesseq5PK((String) uneligne[0], matiere);
                                    nseq5.setNotesdeselevesseq5PK(nseqPK5);
                                    nseq5.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq5.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq5((String) uneligne[0], matiere) == null) {
                                        notseq5Facade.create(nseq5);
                                    } else {
                                        notseq5Facade.edit(nseq5);
                                    }
                                }
                                noteseq5Liste.clear();
                                noteseq5Liste.addAll(notseq5Facade.listeMatiereClasse(matiere, classe));
                                noteseq5 = new Notesdeselevesseq5();
                                return "notesreconduites5.xhtml?faces-redirect=true";
                            case "6":
                                for (Object[] uneligne : objetList2) {
                                    Notesdeselevesseq6 nseq6 = new Notesdeselevesseq6();
                                    Notesdeselevesseq6PK nseqPK6 = new Notesdeselevesseq6PK((String) uneligne[0], matiere);
                                    nseq6.setNotesdeselevesseq6PK(nseqPK6);
                                    nseq6.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq6.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq6((String) uneligne[0], matiere) == null) {
                                        notseq6Facade.create(nseq6);
                                    } else {
                                        notseq6Facade.edit(nseq6);
                                    }
                                }
                                noteseq6Liste.clear();
                                noteseq6Liste.addAll(notseq6Facade.listeMatiereClasse(matiere, classe));
                                noteseq6 = new Notesdeselevesseq6();
                                return "notesreconduites6.xhtml?faces-redirect=true";
                        }
                        break;
                    }

                case "3":
                    List<Object[]> objetList3 = notannFacade.notesSeq3DeLamatiere(classe, matiere);
                    if (objetList3
                            != null) {
                        switch (seqdestin) {
                            case "4":
                                for (Object[] uneligne : objetList3) {
                                    Notesdeselevesseq4 nseq4 = new Notesdeselevesseq4();
                                    Notesdeselevesseq4PK nseqPK4 = new Notesdeselevesseq4PK((String) uneligne[0], matiere);
                                    nseq4.setNotesdeselevesseq4PK(nseqPK4);
                                    nseq4.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq4.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq4((String) uneligne[0], matiere) == null) {
                                        notseq4Facade.create(nseq4);
                                    } else {
                                        notseq4Facade.edit(nseq4);
                                    }
                                }
                                noteseq4Liste.clear();
                                noteseq4Liste.addAll(notseq4Facade.listeMatiereClasse(matiere, classe));
                                noteseq4 = new Notesdeselevesseq4();
                                return "notesreconduites4.xhtml?faces-redirect=true";
                            case "5":
                                for (Object[] uneligne : objetList3) {
                                    Notesdeselevesseq5 nseq5 = new Notesdeselevesseq5();
                                    Notesdeselevesseq5PK nseqPK5 = new Notesdeselevesseq5PK((String) uneligne[0], matiere);
                                    nseq5.setNotesdeselevesseq5PK(nseqPK5);
                                    nseq5.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq5.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq5((String) uneligne[0], matiere) == null) {
                                        notseq5Facade.create(nseq5);
                                    } else {
                                        notseq5Facade.edit(nseq5);
                                    }
                                }
                                noteseq5Liste.clear();
                                noteseq5Liste.addAll(notseq5Facade.listeMatiereClasse(matiere, classe));
                                noteseq5 = new Notesdeselevesseq5();
                                return "notesreconduites5.xhtml?faces-redirect=true";
                            case "6":
                                for (Object[] uneligne : objetList3) {
                                    Notesdeselevesseq6 nseq6 = new Notesdeselevesseq6();
                                    Notesdeselevesseq6PK nseqPK6 = new Notesdeselevesseq6PK((String) uneligne[0], matiere);
                                    nseq6.setNotesdeselevesseq6PK(nseqPK6);
                                    nseq6.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq6.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq6((String) uneligne[0], matiere) == null) {
                                        notseq6Facade.create(nseq6);
                                    } else {
                                        notseq6Facade.edit(nseq6);
                                    }
                                }
                                noteseq6Liste.clear();
                                noteseq6Liste.addAll(notseq6Facade.listeMatiereClasse(matiere, classe));
                                noteseq6 = new Notesdeselevesseq6();
                                return "notesreconduites6.xhtml?faces-redirect=true";
                        }
                        break;
                    }
                case "4":
                    List<Object[]> objetList4 = notannFacade.notesSeq4DeLamatiere(classe, matiere);
                    if (objetList4
                            != null) {
                        switch (seqdestin) {
                            case "5":
                                for (Object[] uneligne : objetList4) {
                                    Notesdeselevesseq5 nseq5 = new Notesdeselevesseq5();
                                    Notesdeselevesseq5PK nseqPK5 = new Notesdeselevesseq5PK((String) uneligne[0], matiere);
                                    nseq5.setNotesdeselevesseq5PK(nseqPK5);
                                    nseq5.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq5.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq5((String) uneligne[0], matiere) == null) {
                                        notseq5Facade.create(nseq5);
                                    } else {
                                        notseq5Facade.edit(nseq5);
                                    }
                                }
                                noteseq5Liste.clear();
                                noteseq5Liste.addAll(notseq5Facade.listeMatiereClasse(matiere, classe));
                                noteseq5 = new Notesdeselevesseq5();
                                return "notesreconduites5.xhtml?faces-redirect=true";
                            case "6":
                                for (Object[] uneligne : objetList4) {
                                    Notesdeselevesseq6 nseq6 = new Notesdeselevesseq6();
                                    Notesdeselevesseq6PK nseqPK6 = new Notesdeselevesseq6PK((String) uneligne[0], matiere);
                                    nseq6.setNotesdeselevesseq6PK(nseqPK6);
                                    nseq6.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq6.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq6((String) uneligne[0], matiere) == null) {
                                        notseq6Facade.create(nseq6);
                                    } else {
                                        notseq6Facade.edit(nseq6);
                                    }
                                }
                                noteseq6Liste.clear();
                                noteseq6Liste.addAll(notseq6Facade.listeMatiereClasse(matiere, classe));
                                noteseq6 = new Notesdeselevesseq6();
                                return "notesreconduites6.xhtml?faces-redirect=true";
                        }
                        break;
                    }
                case "5":
                    List<Object[]> objetList5 = notannFacade.notesSeq5DeLamatiere(classe, matiere);
                    if (objetList5
                            != null) {
                        switch (seqdestin) {
                            case "6":
                                for (Object[] uneligne : objetList5) {
                                    Notesdeselevesseq6 nseq6 = new Notesdeselevesseq6();
                                    Notesdeselevesseq6PK nseqPK6 = new Notesdeselevesseq6PK((String) uneligne[0], matiere);
                                    nseq6.setNotesdeselevesseq6PK(nseqPK6);
                                    nseq6.setListedeseleves(new Listedeseleves((String) uneligne[0]));
                                    nseq6.setLanote((BigDecimal) uneligne[1]);
                                    if (notannFacade.verifierEleve_matiereSeq6((String) uneligne[0], matiere) == null) {
                                        notseq6Facade.create(nseq6);
                                    } else {
                                        notseq6Facade.edit(nseq6);
                                    }
                                }
                                noteseq6Liste.clear();
                                noteseq6Liste.addAll(notseq6Facade.listeMatiereClasse(matiere, classe));
                                noteseq6 = new Notesdeselevesseq6();
                                return "notesreconduites6.xhtml?faces-redirect=true";
                        }
                        break;
                    }

                    break;
            }
        } else {
            TestMsgLogin = true;
            return "";
        }
        return "";
    }

    public ListepasswordFacadeLocal getPwdFacade() {
        return pwdFacade;
    }

    public void setPwdFacade(ListepasswordFacadeLocal pwdFacade) {
        this.pwdFacade = pwdFacade;
    }

    public Notesdeselevesseq2 getNoteseq2() {
        return noteseq2;
    }

    public void setNoteseq2(Notesdeselevesseq2 noteseq2) {
        this.noteseq2 = noteseq2;
    }

    public Notesdeselevesseq3 getNoteseq3() {
        return noteseq3;
    }

    public void setNoteseq3(Notesdeselevesseq3 noteseq3) {
        this.noteseq3 = noteseq3;
    }

    public Notesdeselevesseq4 getNoteseq4() {
        return noteseq4;
    }

    public void setNoteseq4(Notesdeselevesseq4 noteseq4) {
        this.noteseq4 = noteseq4;
    }

    public Notesdeselevesseq5 getNoteseq5() {
        return noteseq5;
    }

    public void setNoteseq5(Notesdeselevesseq5 noteseq5) {
        this.noteseq5 = noteseq5;
    }

    public Notesdeselevesseq6 getNoteseq6() {
        return noteseq6;
    }

    public void setNoteseq6(Notesdeselevesseq6 noteseq6) {
        this.noteseq6 = noteseq6;
    }

    public String getSeqcible() {
        return seqcible;
    }

    public void setSeqcible(String seqcible) {
        this.seqcible = seqcible;
    }

    public String getSeqdestin() {
        return seqdestin;
    }

    public void setSeqdestin(String seqdestin) {
        this.seqdestin = seqdestin;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public boolean isMsgSelect() {
        return msgSelect;
    }

    public void setMsgSelect(boolean msgSelect) {
        this.msgSelect = msgSelect;
    }

    public List<Notesdeselevesseq2> getNoteseq2Liste() {
        return noteseq2Liste;
    }

    public void setNoteseq2Liste(List<Notesdeselevesseq2> noteseq2Liste) {
        this.noteseq2Liste = noteseq2Liste;
    }

    public List<Notesdeselevesseq3> getNoteseq3Liste() {
        return noteseq3Liste;
    }

    public void setNoteseq3Liste(List<Notesdeselevesseq3> noteseq3Liste) {
        this.noteseq3Liste = noteseq3Liste;
    }

    public List<Notesdeselevesseq4> getNoteseq4Liste() {
        return noteseq4Liste;
    }

    public void setNoteseq4Liste(List<Notesdeselevesseq4> noteseq4Liste) {
        this.noteseq4Liste = noteseq4Liste;
    }

    public List<Notesdeselevesseq5> getNoteseq5Liste() {
        return noteseq5Liste;
    }

    public void setNoteseq5Liste(List<Notesdeselevesseq5> noteseq5Liste) {
        this.noteseq5Liste = noteseq5Liste;
    }

    public List<Notesdeselevesseq6> getNoteseq6Liste() {
        return noteseq6Liste;
    }

    public void setNoteseq6Liste(List<Notesdeselevesseq6> noteseq6Liste) {
        this.noteseq6Liste = noteseq6Liste;
    }

    public boolean isTestMsgLogin() {
        return TestMsgLogin;
    }

    public void setTestMsgLogin(boolean TestMsgLogin) {
        this.TestMsgLogin = TestMsgLogin;
    }

}
