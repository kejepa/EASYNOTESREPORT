/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Annmoyennesrangsdeseleves;
import entities.Annrangdeselevesparmatiere;
import entities.AnnrangdeselevesparmatierePK;
import entities.Calculnotesdesclasses;
import entities.CalculnotesdesclassesPK;
import entities.Conseildeclasseann;
import entities.Conseildeclassetrim1;
import entities.Conseildeclassetrim2;
import entities.Conseildeclassetrim3;
import entities.Disciplinesdeselevesann;
import entities.Disciplinesdeselevesseq1;
import entities.Disciplinesdeselevesseq2;
import entities.Disciplinesdeselevesseq3;
import entities.Disciplinesdeselevesseq4;
import entities.Disciplinesdeselevesseq5;
import entities.Disciplinesdeselevesseq6;
import entities.Disciplinesdeselevestrim1;
import entities.Disciplinesdeselevestrim2;
import entities.Disciplinesdeselevestrim3;
import entities.Listedeseleves;
import entities.Notesdeselevesann;
import entities.NotesdeselevesannPK;
import entities.Notesdeselevestrim1;
import entities.Notesdeselevestrim1PK;
import entities.Notesdeselevestrim2;
import entities.Notesdeselevestrim2PK;
import entities.Notesdeselevestrim3;
import entities.Notesdeselevestrim3PK;
import entities.Notesminmaxgenparmat;
import entities.NotesminmaxgenparmatPK;
import entities.Operations;
import entities.Seq1moyennesrangsdeseleves;
import entities.Seq1rangdeselevesparmatiere;
import entities.Seq1rangdeselevesparmatierePK;
import entities.Seq2moyennesrangsdeseleves;
import entities.Seq2rangdeselevesparmatiere;
import entities.Seq2rangdeselevesparmatierePK;
import entities.Seq3moyennesrangsdeseleves;
import entities.Seq3rangdeselevesparmatiere;
import entities.Seq3rangdeselevesparmatierePK;
import entities.Seq4moyennesrangsdeseleves;
import entities.Seq4rangdeselevesparmatiere;
import entities.Seq4rangdeselevesparmatierePK;
import entities.Seq5moyennesrangsdeseleves;
import entities.Seq5rangdeselevesparmatiere;
import entities.Seq5rangdeselevesparmatierePK;
import entities.Seq6moyennesrangsdeseleves;
import entities.Seq6rangdeselevesparmatiere;
import entities.Seq6rangdeselevesparmatierePK;
import entities.Trim1moyennesrangsdeseleves;
import entities.Trim1rangdeselevesparmatiere;
import entities.Trim1rangdeselevesparmatierePK;
import entities.Trim2moyennesrangsdeseleves;
import entities.Trim2rangdeselevesparmatiere;
import entities.Trim2rangdeselevesparmatierePK;
import entities.Trim3moyennesrangsdeseleves;
import entities.Trim3rangdeselevesparmatiere;
import entities.Trim3rangdeselevesparmatierePK;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import sessions.AnnmoyennesrangsdeselevesFacadeLocal;
import sessions.AnnrangdeselevesparmatiereFacadeLocal;
import sessions.CalculnotesdesclassesFacadeLocal;
import sessions.ConseildeclasseannFacadeLocal;
import sessions.Conseildeclassetrim1FacadeLocal;
import sessions.Conseildeclassetrim2FacadeLocal;
import sessions.Conseildeclassetrim3FacadeLocal;
import sessions.DisciplinesdeselevesannFacadeLocal;
import sessions.Disciplinesdeselevesseq1FacadeLocal;
import sessions.Disciplinesdeselevesseq2FacadeLocal;
import sessions.Disciplinesdeselevesseq3FacadeLocal;
import sessions.Disciplinesdeselevesseq4FacadeLocal;
import sessions.Disciplinesdeselevesseq5FacadeLocal;
import sessions.Disciplinesdeselevesseq6FacadeLocal;
import sessions.Disciplinesdeselevestrim1FacadeLocal;
import sessions.Disciplinesdeselevestrim2FacadeLocal;
import sessions.Disciplinesdeselevestrim3FacadeLocal;
import sessions.EtablissementinfosFacadeLocal;
import sessions.NotesdeselevesannFacadeLocal;
import sessions.Notesdeselevestrim1FacadeLocal;
import sessions.Notesdeselevestrim2FacadeLocal;
import sessions.Notesdeselevestrim3FacadeLocal;
import sessions.NotesminmaxgenparmatFacadeLocal;
import sessions.OperationsFacadeLocal;
import sessions.Seq1moyennesrangsdeselevesFacadeLocal;
import sessions.Seq1rangdeselevesparmatiereFacadeLocal;
import sessions.Seq2moyennesrangsdeselevesFacadeLocal;
import sessions.Seq2rangdeselevesparmatiereFacadeLocal;
import sessions.Seq3moyennesrangsdeselevesFacadeLocal;
import sessions.Seq3rangdeselevesparmatiereFacadeLocal;
import sessions.Seq4moyennesrangsdeselevesFacadeLocal;
import sessions.Seq4rangdeselevesparmatiereFacadeLocal;
import sessions.Seq5moyennesrangsdeselevesFacadeLocal;
import sessions.Seq5rangdeselevesparmatiereFacadeLocal;
import sessions.Seq6moyennesrangsdeselevesFacadeLocal;
import sessions.Seq6rangdeselevesparmatiereFacadeLocal;
import sessions.Trim1moyennesrangsdeselevesFacadeLocal;
import sessions.Trim1rangdeselevesparmatiereFacadeLocal;
import sessions.Trim2moyennesrangsdeselevesFacadeLocal;
import sessions.Trim2rangdeselevesparmatiereFacadeLocal;
import sessions.Trim3moyennesrangsdeselevesFacadeLocal;
import sessions.Trim3rangdeselevesparmatiereFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "calculController")
@SessionScoped
public class CalculController implements Serializable {

    @EJB
    private EtablissementinfosFacadeLocal etsFacade;
    @EJB
    private Seq1moyennesrangsdeselevesFacadeLocal seq1MoyenneFacade;
    @EJB
    private Seq2moyennesrangsdeselevesFacadeLocal seq2MoyenneFacade;
    @EJB
    private Seq3moyennesrangsdeselevesFacadeLocal seq3MoyenneFacade;
    @EJB
    private Seq4moyennesrangsdeselevesFacadeLocal seq4MoyenneFacade;
    @EJB
    private Seq5moyennesrangsdeselevesFacadeLocal seq5MoyenneFacade;
    @EJB
    private Seq6moyennesrangsdeselevesFacadeLocal seq6MoyenneFacade;
    @EJB
    private Trim1moyennesrangsdeselevesFacadeLocal trim1MoyenneFacade;
    @EJB
    private Trim2moyennesrangsdeselevesFacadeLocal trim2MoyenneFacade;
    @EJB
    private Trim3moyennesrangsdeselevesFacadeLocal trim3MoyenneFacade;
    @EJB
    private AnnmoyennesrangsdeselevesFacadeLocal annMoyenneFacade;
    @EJB
    private CalculnotesdesclassesFacadeLocal calculnoteclasseFacade;
    @EJB
    private NotesminmaxgenparmatFacadeLocal notesminmaxgenparmatFacade;
    @EJB
    private Seq1rangdeselevesparmatiereFacadeLocal seq1rangeleveFacade;
    @EJB
    private Seq2rangdeselevesparmatiereFacadeLocal seq2rangeleveFacade;
    @EJB
    private Seq3rangdeselevesparmatiereFacadeLocal seq3rangeleveFacade;
    @EJB
    private Seq4rangdeselevesparmatiereFacadeLocal seq4rangeleveFacade;
    @EJB
    private Seq5rangdeselevesparmatiereFacadeLocal seq5rangeleveFacade;
    @EJB
    private Seq6rangdeselevesparmatiereFacadeLocal seq6rangeleveFacade;
    @EJB
    private Trim1rangdeselevesparmatiereFacadeLocal trim1rangeleveFacade;
    @EJB
    private Trim2rangdeselevesparmatiereFacadeLocal trim2rangeleveFacade;
    @EJB
    private Trim3rangdeselevesparmatiereFacadeLocal trim3rangeleveFacade;
    @EJB
    private AnnrangdeselevesparmatiereFacadeLocal annrangeleveFacade;

    @EJB
    private Disciplinesdeselevesseq1FacadeLocal disciplineseq1Facade;
    @EJB
    private Disciplinesdeselevesseq2FacadeLocal disciplineseq2Facade;
    @EJB
    private Disciplinesdeselevesseq3FacadeLocal disciplineseq3Facade;
    @EJB
    private Disciplinesdeselevesseq4FacadeLocal disciplineseq4Facade;
    @EJB
    private Disciplinesdeselevesseq5FacadeLocal disciplineseq5Facade;
    @EJB
    private Disciplinesdeselevesseq6FacadeLocal disciplineseq6Facade;
    @EJB
    private Disciplinesdeselevestrim1FacadeLocal disciplinetrim1Facade;
    @EJB
    private Disciplinesdeselevestrim2FacadeLocal disciplinetrim2Facade;
    @EJB
    private Disciplinesdeselevestrim3FacadeLocal disciplinetrim3Facade;
    @EJB
    private DisciplinesdeselevesannFacadeLocal disciplineannFacade;
    @EJB
    private Conseildeclassetrim1FacadeLocal conseiltrim1Facade;
    @EJB
    private Conseildeclassetrim2FacadeLocal conseiltrim2Facade;
    @EJB
    private Conseildeclassetrim3FacadeLocal conseiltrim3Facade;
    @EJB
    private ConseildeclasseannFacadeLocal conseilannFacade;
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
    List<Listedeseleves> listeEleves = new ArrayList<>();
    private Disciplinesdeselevesseq1 disciplineseq1;
    private Disciplinesdeselevesseq2 disciplineseq2;
    private Disciplinesdeselevesseq3 disciplineseq3;
    private Disciplinesdeselevesseq4 disciplineseq4;
    private Disciplinesdeselevesseq5 disciplineseq5;
    private Disciplinesdeselevesseq6 disciplineseq6;
    private Disciplinesdeselevestrim1 disciplinetrim1;
    private Disciplinesdeselevestrim2 disciplinetrim2;
    private Disciplinesdeselevestrim3 disciplinetrim3;
    private Disciplinesdeselevesann disciplineann;
    private Seq1moyennesrangsdeseleves seq1moyenne;
    private Seq2moyennesrangsdeseleves seq2moyenne;
    private Seq3moyennesrangsdeseleves seq3moyenne;
    private Seq4moyennesrangsdeseleves seq4moyenne;
    private Seq5moyennesrangsdeseleves seq5moyenne;
    private Seq6moyennesrangsdeseleves seq6moyenne;
    private Trim1moyennesrangsdeseleves trim1moyenne;
    private Trim2moyennesrangsdeseleves trim2moyenne;
    private Trim3moyennesrangsdeseleves trim3moyenne;
    private Annmoyennesrangsdeseleves annmoyenne;
    private Calculnotesdesclasses calculnoteClasse;
    private Conseildeclassetrim1 conseiltrim1;
    private Conseildeclassetrim2 conseiltrim2;
    private Conseildeclassetrim3 conseiltrim3;
    private Conseildeclasseann conseilann;
    private String Msg = "";
    private boolean MsgTest = false;
    private boolean imageConirm = false;

    public CalculController() {
    }

    public String calcule_des_moyennes() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateString = df.format(date);
        try {
            Date dateOperation = df.parse(dateString);
            Operations mouchard = new Operations();
            mouchard.setIdoperations(mouchardFacade.nextId());
            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
            mouchard.setTypeoperation("Calcul Automatique des Notes");
            mouchard.setDateoperation(dateOperation);
            mouchard.setDescription("Calcul Automatique des Notes de " + ManagedNoteSeqController.seq + ", " + ManagedNoteSeqController.classe.toString());
            mouchardFacade.create(mouchard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (ManagedNoteSeqController.seq) {
            case "SEQ1":
                return calcule_des_moyennesSeq1();
            case "SEQ2":
                return calcule_des_moyennesSeq2();
            case "SEQ3":
                return calcule_des_moyennesSeq3();
            case "SEQ4":
                return calcule_des_moyennesSeq4();
            case "SEQ5":
                return calcule_des_moyennesSeq5();
            case "SEQ6":
                return calcule_des_moyennesSeq6();
            case "TRIM1":
                return calcule_des_moyennesTrim1();
            case "TRIM2":
                return calcule_des_moyennesTrim2();
            case "TRIM3":
                return calcule_des_moyennesTrim3();
            case "ANN":
                return calcule_des_moyennesAnn();
        }
        return "";
    }

    public void faireDisparaitre_le_sms_sur_pdialog() {
        if (ManagedNoteSeqController.seq == null || ManagedNoteSeqController.classe == null || ManagedNoteSeqController.seuil == null) {
            Msg = "Tous Les Champs sont Obligatoires!";
            MsgTest = true;
        } else {
            MsgTest = false;
        }
    }

    public String calcule_des_moyennesSeq1() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = seq1MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = seq1MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Seq1moyennesrangsdeseleves> eleve = seq1MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq1moyenne = new Seq1moyennesrangsdeseleves();
                        seq1moyenne.setMatriculeeleve(matricule);
                        seq1moyenne.setMoyenne(moy);
                        seq1MoyenneFacade.edit(seq1moyenne);
                    } else {
                        seq1moyenne = new Seq1moyennesrangsdeseleves();
                        seq1moyenne.setMatriculeeleve(matricule);
                        seq1moyenne.setMoyenne(moy);
                        seq1MoyenneFacade.create(seq1moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Seq1moyennesrangsdeseleves> eleve = seq1MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq1moyenne = new Seq1moyennesrangsdeseleves();
                        seq1moyenne.setMatriculeeleve(matricule);
                        seq1moyenne.setMoyenne(null);
                        seq1MoyenneFacade.edit(seq1moyenne);
                    } else {
                        seq1moyenne = new Seq1moyennesrangsdeseleves();
                        seq1moyenne.setMatriculeeleve(matricule);
                        seq1moyenne.setMoyenne(null);
                        seq1MoyenneFacade.create(seq1moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;
//            List<String> liste_groupe = seq1MoyenneFacade.lite_des_groupes_matiere("6M1");
                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = seq1MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = seq1MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = seq1MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Seq1moyennesrangsdeseleves> eleveMoyG = seq1MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Seq1moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Seq1moyennesrangsdeseleves) k.next();
                    seq1moyenne = new Seq1moyennesrangsdeseleves();
                    seq1moyenne.setMatriculeeleve(matricule);
                    seq1moyenne.setMoyenne(elevG.getMoyenne());
                    seq1moyenne.setMoyg1(moyG1);
                    seq1moyenne.setMoyg2(moyG2);
                    seq1moyenne.setMoyg3(moyG3);
                    seq1MoyenneFacade.edit(seq1moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = seq1MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                seq1moyenne = new Seq1moyennesrangsdeseleves();
                seq1moyenne.setMatriculeeleve((String) object[0]);
                seq1moyenne.setMoyenne((BigDecimal) object[1]);
                seq1moyenne.setMoyg1((BigDecimal) object[2]);
                seq1moyenne.setMoyg2((BigDecimal) object[3]);
                seq1moyenne.setMoyg3((BigDecimal) object[4]);
                seq1moyenne.setRang(object[5].toString());
                seq1MoyenneFacade.edit(seq1moyenne);
            }
            List<Object[]> eleveRangG1 = seq1MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                seq1moyenne = new Seq1moyennesrangsdeseleves();
                seq1moyenne.setMatriculeeleve((String) object[0]);
                seq1moyenne.setMoyenne((BigDecimal) object[1]);
                seq1moyenne.setMoyg1((BigDecimal) object[2]);
                seq1moyenne.setMoyg2((BigDecimal) object[3]);
                seq1moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq1moyenne.setRang(object[5].toString());
                } else {
                    seq1moyenne.setRang(null);
                }
                seq1moyenne.setRangg1(object[6].toString());
                seq1MoyenneFacade.edit(seq1moyenne);
            }
            List<Object[]> eleveRangG2 = seq1MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                seq1moyenne = new Seq1moyennesrangsdeseleves();
                seq1moyenne.setMatriculeeleve((String) object[0]);
                seq1moyenne.setMoyenne((BigDecimal) object[1]);
                seq1moyenne.setMoyg1((BigDecimal) object[2]);
                seq1moyenne.setMoyg2((BigDecimal) object[3]);
                seq1moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    seq1moyenne.setRang(object[5].toString());
                } else {
                    seq1moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq1moyenne.setRangg1(object[6].toString());
                } else {
                    seq1moyenne.setRangg1(null);
                }
                seq1moyenne.setRangg2(object[7].toString());
                seq1MoyenneFacade.edit(seq1moyenne);
            }
            List<Object[]> eleveRangG3 = seq1MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                seq1moyenne = new Seq1moyennesrangsdeseleves();
                seq1moyenne.setMatriculeeleve((String) object[0]);
                seq1moyenne.setMoyenne((BigDecimal) object[1]);
                seq1moyenne.setMoyg1((BigDecimal) object[2]);
                seq1moyenne.setMoyg2((BigDecimal) object[3]);
                seq1moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq1moyenne.setRang(object[5].toString());
                } else {
                    seq1moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq1moyenne.setRangg1(object[6].toString());
                } else {
                    seq1moyenne.setRangg1(null);
                }
                seq1moyenne.setRangg2(object[7].toString());
                seq1moyenne.setRangg3(object[8].toString());
                seq1MoyenneFacade.edit(seq1moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("SEQ1", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classe(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classe(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classe(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classe(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "SEQ1")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereSeq1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereSeq1(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("SEQ1", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "SEQ1", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière 
            List<String> listeMatieresDeLaClasse = seq1rangeleveFacade.seq1_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = seq1rangeleveFacade.seq1_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Seq1rangdeselevesparmatiere seq1rangeleve = new Seq1rangdeselevesparmatiere();
                        Seq1rangdeselevesparmatierePK seq1rangelevepk = new Seq1rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq1rangeleve.setSeq1rangdeselevesparmatierePK(seq1rangelevepk);
                        seq1rangeleve.setRang(uneligne[3].toString());
                        if (seq1rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq1rangeleveFacade.edit(seq1rangeleve);
                        } else {
                            seq1rangeleveFacade.create(seq1rangeleve);
                        }
                    }
                }
                //rang pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = seq1rangeleveFacade.seq1_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Seq1rangdeselevesparmatiere seq1rangeleve = new Seq1rangdeselevesparmatiere();
                        Seq1rangdeselevesparmatierePK seq1rangelevepk = new Seq1rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq1rangeleve.setSeq1rangdeselevesparmatierePK(seq1rangelevepk);
                        seq1rangeleve.setRang(null);
                        if (seq1rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq1rangeleveFacade.edit(seq1rangeleve);
                        } else {
                            seq1rangeleveFacade.create(seq1rangeleve);
                        }
                    }
                }
            }
            //charger la liste des éleves pour la Discipline si cela n'a pas été fait 
            disciplineseq1();
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesSeq2() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = seq2MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = seq2MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Seq2moyennesrangsdeseleves> eleve = seq2MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq2moyenne = new Seq2moyennesrangsdeseleves();
                        seq2moyenne.setMatriculeeleve(matricule);
                        seq2moyenne.setMoyenne(moy);
                        seq2MoyenneFacade.edit(seq2moyenne);
                    } else {
                        seq2moyenne = new Seq2moyennesrangsdeseleves();
                        seq2moyenne.setMatriculeeleve(matricule);
                        seq2moyenne.setMoyenne(moy);
                        seq2MoyenneFacade.create(seq2moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Seq2moyennesrangsdeseleves> eleve = seq2MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq2moyenne = new Seq2moyennesrangsdeseleves();
                        seq2moyenne.setMatriculeeleve(matricule);
                        seq2moyenne.setMoyenne(null);
                        seq2MoyenneFacade.edit(seq2moyenne);
                    } else {
                        seq2moyenne = new Seq2moyennesrangsdeseleves();
                        seq2moyenne.setMatriculeeleve(matricule);
                        seq2moyenne.setMoyenne(null);
                        seq2MoyenneFacade.create(seq2moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;
//            List<String> liste_groupe = seq1MoyenneFacade.lite_des_groupes_matiere("6M1");
                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = seq2MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = seq2MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = seq2MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Seq2moyennesrangsdeseleves> eleveMoyG = seq2MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Seq2moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Seq2moyennesrangsdeseleves) k.next();
                    seq2moyenne = new Seq2moyennesrangsdeseleves();
                    seq2moyenne.setMatriculeeleve(matricule);
                    seq2moyenne.setMoyenne(elevG.getMoyenne());
                    seq2moyenne.setMoyg1(moyG1);
                    seq2moyenne.setMoyg2(moyG2);
                    seq2moyenne.setMoyg3(moyG3);
                    seq2MoyenneFacade.edit(seq2moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = seq2MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                seq2moyenne = new Seq2moyennesrangsdeseleves();
                seq2moyenne.setMatriculeeleve((String) object[0]);
                seq2moyenne.setMoyenne((BigDecimal) object[1]);
                seq2moyenne.setMoyg1((BigDecimal) object[2]);
                seq2moyenne.setMoyg2((BigDecimal) object[3]);
                seq2moyenne.setMoyg3((BigDecimal) object[4]);
                seq2moyenne.setRang(object[5].toString());
                seq2MoyenneFacade.edit(seq2moyenne);
            }
            List<Object[]> eleveRangG1 = seq2MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                seq2moyenne = new Seq2moyennesrangsdeseleves();
                seq2moyenne.setMatriculeeleve((String) object[0]);
                seq2moyenne.setMoyenne((BigDecimal) object[1]);
                seq2moyenne.setMoyg1((BigDecimal) object[2]);
                seq2moyenne.setMoyg2((BigDecimal) object[3]);
                seq2moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq2moyenne.setRang(object[5].toString());
                } else {
                    seq2moyenne.setRang(null);
                }
                seq2moyenne.setRangg1(object[6].toString());
                seq2MoyenneFacade.edit(seq2moyenne);
            }
            List<Object[]> eleveRangG2 = seq2MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                seq2moyenne = new Seq2moyennesrangsdeseleves();
                seq2moyenne.setMatriculeeleve((String) object[0]);
                seq2moyenne.setMoyenne((BigDecimal) object[1]);
                seq2moyenne.setMoyg1((BigDecimal) object[2]);
                seq2moyenne.setMoyg2((BigDecimal) object[3]);
                seq2moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    seq2moyenne.setRang(object[5].toString());
                } else {
                    seq2moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq2moyenne.setRangg1(object[6].toString());
                } else {
                    seq2moyenne.setRangg1(null);
                }
                seq2moyenne.setRangg2(object[7].toString());
                seq2MoyenneFacade.edit(seq2moyenne);
            }
            List<Object[]> eleveRangG3 = seq2MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                seq2moyenne = new Seq2moyennesrangsdeseleves();
                seq2moyenne.setMatriculeeleve((String) object[0]);
                seq2moyenne.setMoyenne((BigDecimal) object[1]);
                seq2moyenne.setMoyg1((BigDecimal) object[2]);
                seq2moyenne.setMoyg2((BigDecimal) object[3]);
                seq2moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq2moyenne.setRang(object[5].toString());
                } else {
                    seq2moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq2moyenne.setRangg1(object[6].toString());
                } else {
                    seq2moyenne.setRangg1(null);
                }
                seq2moyenne.setRangg2(object[7].toString());
                seq2moyenne.setRangg3(object[8].toString());
                seq2MoyenneFacade.edit(seq2moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("SEQ2", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeSeq2(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeSeq2(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeSeq2(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeSeq2(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "SEQ2")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereSeq1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereSeq2(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("SEQ2", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "SEQ2", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = seq2rangeleveFacade.seq2_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = seq2rangeleveFacade.seq2_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Seq2rangdeselevesparmatiere seq2rangeleve = new Seq2rangdeselevesparmatiere();
                        Seq2rangdeselevesparmatierePK seq2rangelevepk = new Seq2rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq2rangeleve.setSeq2rangdeselevesparmatierePK(seq2rangelevepk);
                        seq2rangeleve.setRang(uneligne[3].toString());
                        if (seq2rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq2rangeleveFacade.edit(seq2rangeleve);
                        } else {
                            seq2rangeleveFacade.create(seq2rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = seq2rangeleveFacade.seq2_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Seq2rangdeselevesparmatiere seq2rangeleve = new Seq2rangdeselevesparmatiere();
                        Seq2rangdeselevesparmatierePK seq2rangelevepk = new Seq2rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq2rangeleve.setSeq2rangdeselevesparmatierePK(seq2rangelevepk);
                        seq2rangeleve.setRang(null);
                        if (seq2rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq2rangeleveFacade.edit(seq2rangeleve);
                        } else {
                            seq2rangeleveFacade.create(seq2rangeleve);
                        }
                    }
                }
            }
            //charger la liste des éleves pour la Discipline si cela n'a pas été fait 
            disciplineseq2();
            MsgTest = true;
            Msg = "Opération effectuée avec Succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesSeq3() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = seq3MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = seq3MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Seq3moyennesrangsdeseleves> eleve = seq3MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq3moyenne = new Seq3moyennesrangsdeseleves();
                        seq3moyenne.setMatriculeeleve(matricule);
                        seq3moyenne.setMoyenne(moy);
                        seq3MoyenneFacade.edit(seq3moyenne);
                    } else {
                        seq3moyenne = new Seq3moyennesrangsdeseleves();
                        seq3moyenne.setMatriculeeleve(matricule);
                        seq3moyenne.setMoyenne(moy);
                        seq3MoyenneFacade.create(seq3moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Seq3moyennesrangsdeseleves> eleve = seq3MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq3moyenne = new Seq3moyennesrangsdeseleves();
                        seq3moyenne.setMatriculeeleve(matricule);
                        seq3moyenne.setMoyenne(null);
                        seq3MoyenneFacade.edit(seq3moyenne);
                    } else {
                        seq3moyenne = new Seq3moyennesrangsdeseleves();
                        seq3moyenne.setMatriculeeleve(matricule);
                        seq3moyenne.setMoyenne(null);
                        seq3MoyenneFacade.create(seq3moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;
//            List<String> liste_groupe = seq1MoyenneFacade.lite_des_groupes_matiere("6M1");
                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = seq3MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = seq3MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = seq3MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Seq3moyennesrangsdeseleves> eleveMoyG = seq3MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Seq3moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Seq3moyennesrangsdeseleves) k.next();
                    seq3moyenne = new Seq3moyennesrangsdeseleves();
                    seq3moyenne.setMatriculeeleve(matricule);
                    seq3moyenne.setMoyenne(elevG.getMoyenne());
                    seq3moyenne.setMoyg1(moyG1);
                    seq3moyenne.setMoyg2(moyG2);
                    seq3moyenne.setMoyg3(moyG3);
                    seq3MoyenneFacade.edit(seq3moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = seq3MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                seq3moyenne = new Seq3moyennesrangsdeseleves();
                seq3moyenne.setMatriculeeleve((String) object[0]);
                seq3moyenne.setMoyenne((BigDecimal) object[1]);
                seq3moyenne.setMoyg1((BigDecimal) object[2]);
                seq3moyenne.setMoyg2((BigDecimal) object[3]);
                seq3moyenne.setMoyg3((BigDecimal) object[4]);
                seq3moyenne.setRang(object[5].toString());
                seq3MoyenneFacade.edit(seq3moyenne);
            }
            List<Object[]> eleveRangG1 = seq3MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                seq3moyenne = new Seq3moyennesrangsdeseleves();
                seq3moyenne.setMatriculeeleve((String) object[0]);
                seq3moyenne.setMoyenne((BigDecimal) object[1]);
                seq3moyenne.setMoyg1((BigDecimal) object[2]);
                seq3moyenne.setMoyg2((BigDecimal) object[3]);
                seq3moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq3moyenne.setRang(object[5].toString());
                } else {
                    seq3moyenne.setRang(null);
                }
                seq3moyenne.setRangg1(object[6].toString());
                seq3MoyenneFacade.edit(seq3moyenne);
            }
            List<Object[]> eleveRangG2 = seq3MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                seq3moyenne = new Seq3moyennesrangsdeseleves();
                seq3moyenne.setMatriculeeleve((String) object[0]);
                seq3moyenne.setMoyenne((BigDecimal) object[1]);
                seq3moyenne.setMoyg1((BigDecimal) object[2]);
                seq3moyenne.setMoyg2((BigDecimal) object[3]);
                seq3moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    seq3moyenne.setRang(object[5].toString());
                } else {
                    seq3moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq3moyenne.setRangg1(object[6].toString());
                } else {
                    seq3moyenne.setRangg1(null);
                }
                seq3moyenne.setRangg2(object[7].toString());
                seq3MoyenneFacade.edit(seq3moyenne);
            }
            List<Object[]> eleveRangG3 = seq3MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                seq3moyenne = new Seq3moyennesrangsdeseleves();
                seq3moyenne.setMatriculeeleve((String) object[0]);
                seq3moyenne.setMoyenne((BigDecimal) object[1]);
                seq3moyenne.setMoyg1((BigDecimal) object[2]);
                seq3moyenne.setMoyg2((BigDecimal) object[3]);
                seq3moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq3moyenne.setRang(object[5].toString());
                } else {
                    seq3moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq3moyenne.setRangg1(object[6].toString());
                } else {
                    seq3moyenne.setRangg1(null);
                }
                seq3moyenne.setRangg2(object[7].toString());
                seq3moyenne.setRangg3(object[8].toString());
                seq3MoyenneFacade.edit(seq3moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("SEQ3", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeSeq3(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeSeq3(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeSeq3(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeSeq3(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "SEQ3")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereSeq1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereSeq3(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("SEQ3", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "SEQ3", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = seq3rangeleveFacade.seq3_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = seq3rangeleveFacade.seq3_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Seq3rangdeselevesparmatiere seq3rangeleve = new Seq3rangdeselevesparmatiere();
                        Seq3rangdeselevesparmatierePK seq3rangelevepk = new Seq3rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq3rangeleve.setSeq3rangdeselevesparmatierePK(seq3rangelevepk);
                        seq3rangeleve.setRang(uneligne[3].toString());
                        if (seq3rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq3rangeleveFacade.edit(seq3rangeleve);
                        } else {
                            seq3rangeleveFacade.create(seq3rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = seq3rangeleveFacade.seq3_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Seq3rangdeselevesparmatiere seq3rangeleve = new Seq3rangdeselevesparmatiere();
                        Seq3rangdeselevesparmatierePK seq3rangelevepk = new Seq3rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq3rangeleve.setSeq3rangdeselevesparmatierePK(seq3rangelevepk);
                        seq3rangeleve.setRang(null);
                        if (seq3rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq3rangeleveFacade.edit(seq3rangeleve);
                        } else {
                            seq3rangeleveFacade.create(seq3rangeleve);
                        }
                    }
                }
            }
            //charger la liste des éleves pour la Discipline si cela n'a pas été fait
            disciplineseq3();
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesSeq4() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = seq4MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = seq4MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Seq4moyennesrangsdeseleves> eleve = seq4MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq4moyenne = new Seq4moyennesrangsdeseleves();
                        seq4moyenne.setMatriculeeleve(matricule);
                        seq4moyenne.setMoyenne(moy);
                        seq4MoyenneFacade.edit(seq4moyenne);
                    } else {
                        seq4moyenne = new Seq4moyennesrangsdeseleves();
                        seq4moyenne.setMatriculeeleve(matricule);
                        seq4moyenne.setMoyenne(moy);
                        seq4MoyenneFacade.create(seq4moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Seq4moyennesrangsdeseleves> eleve = seq4MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq4moyenne = new Seq4moyennesrangsdeseleves();
                        seq4moyenne.setMatriculeeleve(matricule);
                        seq4moyenne.setMoyenne(null);
                        seq4MoyenneFacade.edit(seq4moyenne);
                    } else {
                        seq4moyenne = new Seq4moyennesrangsdeseleves();
                        seq4moyenne.setMatriculeeleve(matricule);
                        seq4moyenne.setMoyenne(null);
                        seq4MoyenneFacade.create(seq4moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;
//            List<String> liste_groupe = seq1MoyenneFacade.lite_des_groupes_matiere("6M1");
                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = seq4MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = seq4MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = seq4MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Seq4moyennesrangsdeseleves> eleveMoyG = seq4MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Seq4moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Seq4moyennesrangsdeseleves) k.next();
                    seq4moyenne = new Seq4moyennesrangsdeseleves();
                    seq4moyenne.setMatriculeeleve(matricule);
                    seq4moyenne.setMoyenne(elevG.getMoyenne());
                    seq4moyenne.setMoyg1(moyG1);
                    seq4moyenne.setMoyg2(moyG2);
                    seq4moyenne.setMoyg3(moyG3);
                    seq4MoyenneFacade.edit(seq4moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = seq4MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                seq4moyenne = new Seq4moyennesrangsdeseleves();
                seq4moyenne.setMatriculeeleve((String) object[0]);
                seq4moyenne.setMoyenne((BigDecimal) object[1]);
                seq4moyenne.setMoyg1((BigDecimal) object[2]);
                seq4moyenne.setMoyg2((BigDecimal) object[3]);
                seq4moyenne.setMoyg3((BigDecimal) object[4]);
                seq4moyenne.setRang(object[5].toString());
                seq4MoyenneFacade.edit(seq4moyenne);
            }
            List<Object[]> eleveRangG1 = seq4MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                seq4moyenne = new Seq4moyennesrangsdeseleves();
                seq4moyenne.setMatriculeeleve((String) object[0]);
                seq4moyenne.setMoyenne((BigDecimal) object[1]);
                seq4moyenne.setMoyg1((BigDecimal) object[2]);
                seq4moyenne.setMoyg2((BigDecimal) object[3]);
                seq4moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq4moyenne.setRang(object[5].toString());
                } else {
                    seq4moyenne.setRang(null);
                }
                seq4moyenne.setRangg1(object[6].toString());
                seq4MoyenneFacade.edit(seq4moyenne);
            }
            List<Object[]> eleveRangG2 = seq4MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                seq4moyenne = new Seq4moyennesrangsdeseleves();
                seq4moyenne.setMatriculeeleve((String) object[0]);
                seq4moyenne.setMoyenne((BigDecimal) object[1]);
                seq4moyenne.setMoyg1((BigDecimal) object[2]);
                seq4moyenne.setMoyg2((BigDecimal) object[3]);
                seq4moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    seq4moyenne.setRang(object[5].toString());
                } else {
                    seq4moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq4moyenne.setRangg1(object[6].toString());
                } else {
                    seq4moyenne.setRangg1(null);
                }
                seq4moyenne.setRangg2(object[7].toString());
                seq4MoyenneFacade.edit(seq4moyenne);
            }
            List<Object[]> eleveRangG3 = seq4MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                seq4moyenne = new Seq4moyennesrangsdeseleves();
                seq4moyenne.setMatriculeeleve((String) object[0]);
                seq4moyenne.setMoyenne((BigDecimal) object[1]);
                seq4moyenne.setMoyg1((BigDecimal) object[2]);
                seq4moyenne.setMoyg2((BigDecimal) object[3]);
                seq4moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq4moyenne.setRang(object[5].toString());
                } else {
                    seq4moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq4moyenne.setRangg1(object[6].toString());
                } else {
                    seq4moyenne.setRangg1(null);
                }
                seq4moyenne.setRangg2(object[7].toString());
                seq4moyenne.setRangg3(object[8].toString());
                seq4MoyenneFacade.edit(seq4moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("SEQ4", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeSeq4(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeSeq4(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeSeq4(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeSeq4(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "SEQ4")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereSeq1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereSeq4(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("SEQ4", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "SEQ4", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = seq4rangeleveFacade.seq4_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = seq4rangeleveFacade.seq4_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Seq4rangdeselevesparmatiere seq4rangeleve = new Seq4rangdeselevesparmatiere();
                        Seq4rangdeselevesparmatierePK seq4rangelevepk = new Seq4rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq4rangeleve.setSeq4rangdeselevesparmatierePK(seq4rangelevepk);
                        seq4rangeleve.setRang(uneligne[3].toString());
                        if (seq4rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq4rangeleveFacade.edit(seq4rangeleve);
                        } else {
                            seq4rangeleveFacade.create(seq4rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = seq4rangeleveFacade.seq4_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Seq4rangdeselevesparmatiere seq4rangeleve = new Seq4rangdeselevesparmatiere();
                        Seq4rangdeselevesparmatierePK seq4rangelevepk = new Seq4rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq4rangeleve.setSeq4rangdeselevesparmatierePK(seq4rangelevepk);
                        seq4rangeleve.setRang(null);
                        if (seq4rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq4rangeleveFacade.edit(seq4rangeleve);
                        } else {
                            seq4rangeleveFacade.create(seq4rangeleve);
                        }
                    }
                }
            }
            //charger la liste des éleves pour la Discipline si cela n'a pas été fait 
            disciplineseq4();
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesSeq5() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = seq5MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = seq5MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Seq5moyennesrangsdeseleves> eleve = seq5MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq5moyenne = new Seq5moyennesrangsdeseleves();
                        seq5moyenne.setMatriculeeleve(matricule);
                        seq5moyenne.setMoyenne(moy);
                        seq5MoyenneFacade.edit(seq5moyenne);
                    } else {
                        seq5moyenne = new Seq5moyennesrangsdeseleves();
                        seq5moyenne.setMatriculeeleve(matricule);
                        seq5moyenne.setMoyenne(moy);
                        seq5MoyenneFacade.create(seq5moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Seq5moyennesrangsdeseleves> eleve = seq5MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq5moyenne = new Seq5moyennesrangsdeseleves();
                        seq5moyenne.setMatriculeeleve(matricule);
                        seq5moyenne.setMoyenne(null);
                        seq5MoyenneFacade.edit(seq5moyenne);
                    } else {
                        seq5moyenne = new Seq5moyennesrangsdeseleves();
                        seq5moyenne.setMatriculeeleve(matricule);
                        seq5moyenne.setMoyenne(null);
                        seq5MoyenneFacade.create(seq5moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;
//            List<String> liste_groupe = seq1MoyenneFacade.lite_des_groupes_matiere("6M1");
                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = seq5MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = seq5MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = seq5MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Seq5moyennesrangsdeseleves> eleveMoyG = seq5MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Seq5moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Seq5moyennesrangsdeseleves) k.next();
                    seq5moyenne = new Seq5moyennesrangsdeseleves();
                    seq5moyenne.setMatriculeeleve(matricule);
                    seq5moyenne.setMoyenne(elevG.getMoyenne());
                    seq5moyenne.setMoyg1(moyG1);
                    seq5moyenne.setMoyg2(moyG2);
                    seq5moyenne.setMoyg3(moyG3);
                    seq5MoyenneFacade.edit(seq5moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = seq5MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                seq5moyenne = new Seq5moyennesrangsdeseleves();
                seq5moyenne.setMatriculeeleve((String) object[0]);
                seq5moyenne.setMoyenne((BigDecimal) object[1]);
                seq5moyenne.setMoyg1((BigDecimal) object[2]);
                seq5moyenne.setMoyg2((BigDecimal) object[3]);
                seq5moyenne.setMoyg3((BigDecimal) object[4]);
                seq5moyenne.setRang(object[5].toString());
                seq5MoyenneFacade.edit(seq5moyenne);
            }
            List<Object[]> eleveRangG1 = seq5MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                seq5moyenne = new Seq5moyennesrangsdeseleves();
                seq5moyenne.setMatriculeeleve((String) object[0]);
                seq5moyenne.setMoyenne((BigDecimal) object[1]);
                seq5moyenne.setMoyg1((BigDecimal) object[2]);
                seq5moyenne.setMoyg2((BigDecimal) object[3]);
                seq5moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq5moyenne.setRang(object[5].toString());
                } else {
                    seq5moyenne.setRang(null);
                }
                seq5moyenne.setRangg1(object[6].toString());
                seq5MoyenneFacade.edit(seq5moyenne);
            }
            List<Object[]> eleveRangG2 = seq5MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                seq5moyenne = new Seq5moyennesrangsdeseleves();
                seq5moyenne.setMatriculeeleve((String) object[0]);
                seq5moyenne.setMoyenne((BigDecimal) object[1]);
                seq5moyenne.setMoyg1((BigDecimal) object[2]);
                seq5moyenne.setMoyg2((BigDecimal) object[3]);
                seq5moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    seq5moyenne.setRang(object[5].toString());
                } else {
                    seq5moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq5moyenne.setRangg1(object[6].toString());
                } else {
                    seq5moyenne.setRangg1(null);
                }
                seq5moyenne.setRangg2(object[7].toString());
                seq5MoyenneFacade.edit(seq5moyenne);
            }
            List<Object[]> eleveRangG3 = seq5MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                seq5moyenne = new Seq5moyennesrangsdeseleves();
                seq5moyenne.setMatriculeeleve((String) object[0]);
                seq5moyenne.setMoyenne((BigDecimal) object[1]);
                seq5moyenne.setMoyg1((BigDecimal) object[2]);
                seq5moyenne.setMoyg2((BigDecimal) object[3]);
                seq5moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq5moyenne.setRang(object[5].toString());
                } else {
                    seq5moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq5moyenne.setRangg1(object[6].toString());
                } else {
                    seq5moyenne.setRangg1(null);
                }
                seq5moyenne.setRangg2(object[7].toString());
                seq5moyenne.setRangg3(object[8].toString());
                seq5MoyenneFacade.edit(seq5moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("SEQ5", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeSeq5(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeSeq5(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeSeq5(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeSeq5(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "SEQ5")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereSeq1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereSeq5(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("SEQ5", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "SEQ5", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = seq5rangeleveFacade.seq5_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = seq5rangeleveFacade.seq5_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Seq5rangdeselevesparmatiere seq5rangeleve = new Seq5rangdeselevesparmatiere();
                        Seq5rangdeselevesparmatierePK seq5rangelevepk = new Seq5rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq5rangeleve.setSeq5rangdeselevesparmatierePK(seq5rangelevepk);
                        seq5rangeleve.setRang(uneligne[3].toString());
                        if (seq5rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq5rangeleveFacade.edit(seq5rangeleve);
                        } else {
                            seq5rangeleveFacade.create(seq5rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = seq5rangeleveFacade.seq5_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Seq5rangdeselevesparmatiere seq5rangeleve = new Seq5rangdeselevesparmatiere();
                        Seq5rangdeselevesparmatierePK seq5rangelevepk = new Seq5rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq5rangeleve.setSeq5rangdeselevesparmatierePK(seq5rangelevepk);
                        seq5rangeleve.setRang(null);
                        if (seq5rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq5rangeleveFacade.edit(seq5rangeleve);
                        } else {
                            seq5rangeleveFacade.create(seq5rangeleve);
                        }
                    }
                }
            }
            //charger la liste des éleves pour la Discipline si cela n'a pas été fait 
            disciplineseq5();
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesSeq6() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = seq6MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = seq6MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Seq6moyennesrangsdeseleves> eleve = seq6MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq6moyenne = new Seq6moyennesrangsdeseleves();
                        seq6moyenne.setMatriculeeleve(matricule);
                        seq6moyenne.setMoyenne(moy);
                        seq6MoyenneFacade.edit(seq6moyenne);
                    } else {
                        seq6moyenne = new Seq6moyennesrangsdeseleves();
                        seq6moyenne.setMatriculeeleve(matricule);
                        seq6moyenne.setMoyenne(moy);
                        seq6MoyenneFacade.create(seq6moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Seq6moyennesrangsdeseleves> eleve = seq6MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        seq6moyenne = new Seq6moyennesrangsdeseleves();
                        seq6moyenne.setMatriculeeleve(matricule);
                        seq6moyenne.setMoyenne(null);
                        seq6MoyenneFacade.edit(seq6moyenne);
                    } else {
                        seq6moyenne = new Seq6moyennesrangsdeseleves();
                        seq6moyenne.setMatriculeeleve(matricule);
                        seq6moyenne.setMoyenne(null);
                        seq6MoyenneFacade.create(seq6moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;
//            List<String> liste_groupe = seq1MoyenneFacade.lite_des_groupes_matiere("6M1");
                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = seq6MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = seq6MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = seq6MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Seq6moyennesrangsdeseleves> eleveMoyG = seq6MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Seq6moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Seq6moyennesrangsdeseleves) k.next();
                    seq6moyenne = new Seq6moyennesrangsdeseleves();
                    seq6moyenne.setMatriculeeleve(matricule);
                    seq6moyenne.setMoyenne(elevG.getMoyenne());
                    seq6moyenne.setMoyg1(moyG1);
                    seq6moyenne.setMoyg2(moyG2);
                    seq6moyenne.setMoyg3(moyG3);
                    seq6MoyenneFacade.edit(seq6moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = seq6MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                seq6moyenne = new Seq6moyennesrangsdeseleves();
                seq6moyenne.setMatriculeeleve((String) object[0]);
                seq6moyenne.setMoyenne((BigDecimal) object[1]);
                seq6moyenne.setMoyg1((BigDecimal) object[2]);
                seq6moyenne.setMoyg2((BigDecimal) object[3]);
                seq6moyenne.setMoyg3((BigDecimal) object[4]);
                seq6moyenne.setRang(object[5].toString());
                seq6MoyenneFacade.edit(seq6moyenne);
            }
            List<Object[]> eleveRangG1 = seq6MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                seq6moyenne = new Seq6moyennesrangsdeseleves();
                seq6moyenne.setMatriculeeleve((String) object[0]);
                seq6moyenne.setMoyenne((BigDecimal) object[1]);
                seq6moyenne.setMoyg1((BigDecimal) object[2]);
                seq6moyenne.setMoyg2((BigDecimal) object[3]);
                seq6moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq6moyenne.setRang(object[5].toString());
                } else {
                    seq6moyenne.setRang(null);
                }
                seq6moyenne.setRangg1(object[6].toString());
                seq6MoyenneFacade.edit(seq6moyenne);
            }
            List<Object[]> eleveRangG2 = seq6MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                seq6moyenne = new Seq6moyennesrangsdeseleves();
                seq6moyenne.setMatriculeeleve((String) object[0]);
                seq6moyenne.setMoyenne((BigDecimal) object[1]);
                seq6moyenne.setMoyg1((BigDecimal) object[2]);
                seq6moyenne.setMoyg2((BigDecimal) object[3]);
                seq6moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    seq6moyenne.setRang(object[5].toString());
                } else {
                    seq6moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq6moyenne.setRangg1(object[6].toString());
                } else {
                    seq6moyenne.setRangg1(null);
                }
                seq6moyenne.setRangg2(object[7].toString());
                seq6MoyenneFacade.edit(seq6moyenne);
            }
            List<Object[]> eleveRangG3 = seq6MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                seq6moyenne = new Seq6moyennesrangsdeseleves();
                seq6moyenne.setMatriculeeleve((String) object[0]);
                seq6moyenne.setMoyenne((BigDecimal) object[1]);
                seq6moyenne.setMoyg1((BigDecimal) object[2]);
                seq6moyenne.setMoyg2((BigDecimal) object[3]);
                seq6moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    seq6moyenne.setRang(object[5].toString());
                } else {
                    seq6moyenne.setRang(null);
                }
                if (object[6] != null) {
                    seq6moyenne.setRangg1(object[6].toString());
                } else {
                    seq6moyenne.setRangg1(null);
                }
                seq6moyenne.setRangg2(object[7].toString());
                seq6moyenne.setRangg3(object[8].toString());
                seq6MoyenneFacade.edit(seq6moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("SEQ6", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeSeq6(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeSeq6(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeSeq6(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeSeq6(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "SEQ6")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereSeq6  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereSeq6(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("SEQ6", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "SEQ6", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = seq6rangeleveFacade.seq6_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = seq6rangeleveFacade.seq6_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Seq6rangdeselevesparmatiere seq6rangeleve = new Seq6rangdeselevesparmatiere();
                        Seq6rangdeselevesparmatierePK seq6rangelevepk = new Seq6rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq6rangeleve.setSeq6rangdeselevesparmatierePK(seq6rangelevepk);
                        seq6rangeleve.setRang(uneligne[3].toString());
                        if (seq6rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq6rangeleveFacade.edit(seq6rangeleve);
                        } else {
                            seq6rangeleveFacade.create(seq6rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = seq6rangeleveFacade.seq6_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Seq6rangdeselevesparmatiere seq6rangeleve = new Seq6rangdeselevesparmatiere();
                        Seq6rangdeselevesparmatierePK seq6rangelevepk = new Seq6rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        seq6rangeleve.setSeq6rangdeselevesparmatierePK(seq6rangelevepk);
                        seq6rangeleve.setRang(null);
                        if (seq6rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            seq6rangeleveFacade.edit(seq6rangeleve);
                        } else {
                            seq6rangeleveFacade.create(seq6rangeleve);
                        }
                    }
                }
            }
            //charger la liste des éleves pour la Discipline si cela n'a pas été fait 
            disciplineseq6();
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesTrim1() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //calcul auto des notes trimestrielles((s1+s2)/2)
            if (notetrim1Facade.noteSeq1_2(ManagedNoteSeqController.classe.toString()) != null) {
                List<Object[]> noteSeq1_2 = notetrim1Facade.noteSeq1_2(ManagedNoteSeqController.classe.toString());
                for (Object[] uneligne : noteSeq1_2) {
                    Notesdeselevestrim1 ntrim1 = new Notesdeselevestrim1();
                    Notesdeselevestrim1PK ntrim1PK = new Notesdeselevestrim1PK((String) uneligne[0], (String) uneligne[1]);
                    ntrim1.setNotesdeselevestrim1PK(ntrim1PK);
                    if ((noteannFacade.verifierEleve_matiereTrim1((String) uneligne[0], (String) uneligne[1])) != null) {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null) {
                            ntrim1.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            notetrim1Facade.edit(ntrim1);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null) {
                            ntrim1.setLanote((BigDecimal) uneligne[3]);
                            notetrim1Facade.edit(ntrim1);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null) {
                            ntrim1.setLanote((BigDecimal) uneligne[2]);
                            notetrim1Facade.edit(ntrim1);
                        } else {
                            ntrim1.setLanote(null);
                            notetrim1Facade.edit(ntrim1);
                        }
                    } else {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null) {
                            ntrim1.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            notetrim1Facade.create(ntrim1);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null) {
                            ntrim1.setLanote((BigDecimal) uneligne[3]);
                            notetrim1Facade.create(ntrim1);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null) {
                            ntrim1.setLanote((BigDecimal) uneligne[2]);
                            notetrim1Facade.create(ntrim1);
                        } else {
                            ntrim1.setLanote(null);
                            notetrim1Facade.create(ntrim1);
                        }
                    }
                }
            }
            //clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = trim1MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = trim1MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Trim1moyennesrangsdeseleves> eleve = trim1MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        trim1moyenne = new Trim1moyennesrangsdeseleves();
                        trim1moyenne.setMatriculeeleve(matricule);
                        trim1moyenne.setMoyenne(moy);
                        trim1MoyenneFacade.edit(trim1moyenne);
                    } else {
                        trim1moyenne = new Trim1moyennesrangsdeseleves();
                        trim1moyenne.setMatriculeeleve(matricule);
                        trim1moyenne.setMoyenne(moy);
                        trim1MoyenneFacade.create(trim1moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Trim1moyennesrangsdeseleves> eleve = trim1MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        trim1moyenne = new Trim1moyennesrangsdeseleves();
                        trim1moyenne.setMatriculeeleve(matricule);
                        trim1moyenne.setMoyenne(null);
                        trim1MoyenneFacade.edit(trim1moyenne);
                    } else {
                        trim1moyenne = new Trim1moyennesrangsdeseleves();
                        trim1moyenne.setMatriculeeleve(matricule);
                        trim1moyenne.setMoyenne(null);
                        trim1MoyenneFacade.create(trim1moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;

                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = trim1MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = trim1MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = trim1MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Trim1moyennesrangsdeseleves> eleveMoyG = trim1MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Trim1moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Trim1moyennesrangsdeseleves) k.next();
                    trim1moyenne = new Trim1moyennesrangsdeseleves();
                    trim1moyenne.setMatriculeeleve(matricule);
                    trim1moyenne.setMoyenne(elevG.getMoyenne());
                    trim1moyenne.setMoyg1(moyG1);
                    trim1moyenne.setMoyg2(moyG2);
                    trim1moyenne.setMoyg3(moyG3);
                    trim1MoyenneFacade.edit(trim1moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = trim1MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                trim1moyenne = new Trim1moyennesrangsdeseleves();
                trim1moyenne.setMatriculeeleve((String) object[0]);
                trim1moyenne.setMoyenne((BigDecimal) object[1]);
                trim1moyenne.setMoyg1((BigDecimal) object[2]);
                trim1moyenne.setMoyg2((BigDecimal) object[3]);
                trim1moyenne.setMoyg3((BigDecimal) object[4]);
                trim1moyenne.setRang(object[5].toString());
                trim1MoyenneFacade.edit(trim1moyenne);
            }
            List<Object[]> eleveRangG1 = trim1MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                trim1moyenne = new Trim1moyennesrangsdeseleves();
                trim1moyenne.setMatriculeeleve((String) object[0]);
                trim1moyenne.setMoyenne((BigDecimal) object[1]);
                trim1moyenne.setMoyg1((BigDecimal) object[2]);
                trim1moyenne.setMoyg2((BigDecimal) object[3]);
                trim1moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    trim1moyenne.setRang(object[5].toString());
                } else {
                    trim1moyenne.setRang(null);
                }
                trim1moyenne.setRangg1(object[6].toString());
                trim1MoyenneFacade.edit(trim1moyenne);
            }
            List<Object[]> eleveRangG2 = trim1MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                trim1moyenne = new Trim1moyennesrangsdeseleves();
                trim1moyenne.setMatriculeeleve((String) object[0]);
                trim1moyenne.setMoyenne((BigDecimal) object[1]);
                trim1moyenne.setMoyg1((BigDecimal) object[2]);
                trim1moyenne.setMoyg2((BigDecimal) object[3]);
                trim1moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    trim1moyenne.setRang(object[5].toString());
                } else {
                    trim1moyenne.setRang(null);
                }
                if (object[6] != null) {
                    trim1moyenne.setRangg1(object[6].toString());
                } else {
                    trim1moyenne.setRangg1(null);
                }
                trim1moyenne.setRangg2(object[7].toString());
                trim1MoyenneFacade.edit(trim1moyenne);
            }
            List<Object[]> eleveRangG3 = trim1MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                trim1moyenne = new Trim1moyennesrangsdeseleves();
                trim1moyenne.setMatriculeeleve((String) object[0]);
                trim1moyenne.setMoyenne((BigDecimal) object[1]);
                trim1moyenne.setMoyg1((BigDecimal) object[2]);
                trim1moyenne.setMoyg2((BigDecimal) object[3]);
                trim1moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    trim1moyenne.setRang(object[5].toString());
                } else {
                    trim1moyenne.setRang(null);
                }
                if (object[6] != null) {
                    trim1moyenne.setRangg1(object[6].toString());
                } else {
                    trim1moyenne.setRangg1(null);
                }
                trim1moyenne.setRangg2(object[7].toString());
                trim1moyenne.setRangg3(object[8].toString());
                trim1MoyenneFacade.edit(trim1moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("TRIM1", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeTrim1(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeTrim1(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeTrim1(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeTrim1(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "TRIM1")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereTrim1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereTrim1(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("TRIM1", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "TRIM1", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = trim1rangeleveFacade.trim1_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = trim1rangeleveFacade.trim1_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Trim1rangdeselevesparmatiere trim1rangeleve = new Trim1rangdeselevesparmatiere();
                        Trim1rangdeselevesparmatierePK trim1rangelevepk = new Trim1rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        trim1rangeleve.setTrim1rangdeselevesparmatierePK(trim1rangelevepk);
                        trim1rangeleve.setRang(uneligne[3].toString());
                        if (trim1rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            trim1rangeleveFacade.edit(trim1rangeleve);
                        } else {
                            trim1rangeleveFacade.create(trim1rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = trim1rangeleveFacade.trim1_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Trim1rangdeselevesparmatiere trim1rangeleve = new Trim1rangdeselevesparmatiere();
                        Trim1rangdeselevesparmatierePK trim1rangelevepk = new Trim1rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        trim1rangeleve.setTrim1rangdeselevesparmatierePK(trim1rangelevepk);
                        trim1rangeleve.setRang(null);
                        if (trim1rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            trim1rangeleveFacade.edit(trim1rangeleve);
                        } else {
                            trim1rangeleveFacade.create(trim1rangeleve);
                        }
                    }
                }
            }
            // Charger la discipline des élèves si c'est pas déjà fait
            disciplineseq1();
            disciplineseq2();
            disciplinetrim1();
            // Charger la liste des élèves pour le conseil de classe si c'est pas déjà fait
            if ((conseiltrim1Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = conseiltrim1Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator i2 = listeEleves.iterator();
                Listedeseleves l;
                while (i2.hasNext()) {
                    l = (Listedeseleves) i2.next();
                    conseiltrim1 = new Conseildeclassetrim1();
                    conseiltrim1.setListedeseleves(l);
                    conseiltrim1.setMatriculeeleve(l.getMatriculeeleve());
                    conseiltrim1Facade.create(conseiltrim1);
                }
            }
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesTrim2() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //calcul auto des notes trimestrielles((s3+s4)/2)
            if (notetrim2Facade.noteSeq3_4(ManagedNoteSeqController.classe.toString()) != null) {
                List<Object[]> noteSeq2_3 = notetrim2Facade.noteSeq3_4(ManagedNoteSeqController.classe.toString());
                for (Object[] uneligne : noteSeq2_3) {
                    Notesdeselevestrim2 ntrim2 = new Notesdeselevestrim2();
                    Notesdeselevestrim2PK ntrim2PK = new Notesdeselevestrim2PK((String) uneligne[0], (String) uneligne[1]);
                    ntrim2.setNotesdeselevestrim2PK(ntrim2PK);
                    if ((noteannFacade.verifierEleve_matiereTrim2((String) uneligne[0], (String) uneligne[1])) != null) {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null) {
                            ntrim2.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            notetrim2Facade.edit(ntrim2);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null) {
                            ntrim2.setLanote((BigDecimal) uneligne[3]);
                            notetrim2Facade.edit(ntrim2);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null) {
                            ntrim2.setLanote((BigDecimal) uneligne[2]);
                            notetrim2Facade.edit(ntrim2);
                        } else {
                            ntrim2.setLanote(null);
                            notetrim2Facade.edit(ntrim2);
                        }
                    } else {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null) {
                            ntrim2.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            notetrim2Facade.create(ntrim2);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null) {
                            ntrim2.setLanote((BigDecimal) uneligne[3]);
                            notetrim2Facade.create(ntrim2);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null) {
                            ntrim2.setLanote((BigDecimal) uneligne[2]);
                            notetrim2Facade.create(ntrim2);
                        } else {
                            ntrim2.setLanote(null);
                            notetrim2Facade.create(ntrim2);
                        }
                    }
                }
            }
//clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = trim2MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = trim2MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Trim2moyennesrangsdeseleves> eleve = trim2MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        trim2moyenne = new Trim2moyennesrangsdeseleves();
                        trim2moyenne.setMatriculeeleve(matricule);
                        trim2moyenne.setMoyenne(moy);
                        trim2MoyenneFacade.edit(trim2moyenne);
                    } else {
                        trim2moyenne = new Trim2moyennesrangsdeseleves();
                        trim2moyenne.setMatriculeeleve(matricule);
                        trim2moyenne.setMoyenne(moy);
                        trim2MoyenneFacade.create(trim2moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Trim2moyennesrangsdeseleves> eleve = trim2MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        trim2moyenne = new Trim2moyennesrangsdeseleves();
                        trim2moyenne.setMatriculeeleve(matricule);
                        trim2moyenne.setMoyenne(null);
                        trim2MoyenneFacade.edit(trim2moyenne);
                    } else {
                        trim2moyenne = new Trim2moyennesrangsdeseleves();
                        trim2moyenne.setMatriculeeleve(matricule);
                        trim2moyenne.setMoyenne(null);
                        trim2MoyenneFacade.create(trim2moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;

                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = trim2MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = trim2MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = trim2MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Trim2moyennesrangsdeseleves> eleveMoyG = trim2MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Trim2moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Trim2moyennesrangsdeseleves) k.next();
                    trim2moyenne = new Trim2moyennesrangsdeseleves();
                    trim2moyenne.setMatriculeeleve(matricule);
                    trim2moyenne.setMoyenne(elevG.getMoyenne());
                    trim2moyenne.setMoyg1(moyG1);
                    trim2moyenne.setMoyg2(moyG2);
                    trim2moyenne.setMoyg3(moyG3);
                    trim2MoyenneFacade.edit(trim2moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = trim2MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                trim2moyenne = new Trim2moyennesrangsdeseleves();
                trim2moyenne.setMatriculeeleve((String) object[0]);
                trim2moyenne.setMoyenne((BigDecimal) object[1]);
                trim2moyenne.setMoyg1((BigDecimal) object[2]);
                trim2moyenne.setMoyg2((BigDecimal) object[3]);
                trim2moyenne.setMoyg3((BigDecimal) object[4]);
                trim2moyenne.setRang(object[5].toString());
                trim2MoyenneFacade.edit(trim2moyenne);
            }
            List<Object[]> eleveRangG1 = trim2MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                trim2moyenne = new Trim2moyennesrangsdeseleves();
                trim2moyenne.setMatriculeeleve((String) object[0]);
                trim2moyenne.setMoyenne((BigDecimal) object[1]);
                trim2moyenne.setMoyg1((BigDecimal) object[2]);
                trim2moyenne.setMoyg2((BigDecimal) object[3]);
                trim2moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    trim2moyenne.setRang(object[5].toString());
                } else {
                    trim2moyenne.setRang(null);
                }
                trim2moyenne.setRangg1(object[6].toString());
                trim2MoyenneFacade.edit(trim2moyenne);
            }
            List<Object[]> eleveRangG2 = trim2MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                trim2moyenne = new Trim2moyennesrangsdeseleves();
                trim2moyenne.setMatriculeeleve((String) object[0]);
                trim2moyenne.setMoyenne((BigDecimal) object[1]);
                trim2moyenne.setMoyg1((BigDecimal) object[2]);
                trim2moyenne.setMoyg2((BigDecimal) object[3]);
                trim2moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    trim2moyenne.setRang(object[5].toString());
                } else {
                    trim2moyenne.setRang(null);
                }
                if (object[6] != null) {
                    trim2moyenne.setRangg1(object[6].toString());
                } else {
                    trim2moyenne.setRangg1(null);
                }
                trim2moyenne.setRangg2(object[7].toString());
                trim2MoyenneFacade.edit(trim2moyenne);
            }
            List<Object[]> eleveRangG3 = trim2MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                trim2moyenne = new Trim2moyennesrangsdeseleves();
                trim2moyenne.setMatriculeeleve((String) object[0]);
                trim2moyenne.setMoyenne((BigDecimal) object[1]);
                trim2moyenne.setMoyg1((BigDecimal) object[2]);
                trim2moyenne.setMoyg2((BigDecimal) object[3]);
                trim2moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    trim2moyenne.setRang(object[5].toString());
                } else {
                    trim2moyenne.setRang(null);
                }
                if (object[6] != null) {
                    trim2moyenne.setRangg1(object[6].toString());
                } else {
                    trim2moyenne.setRangg1(null);
                }
                trim2moyenne.setRangg2(object[7].toString());
                trim2moyenne.setRangg3(object[8].toString());
                trim2MoyenneFacade.edit(trim2moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("TRIM2", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeTrim2(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeTrim2(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeTrim2(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeTrim2(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "TRIM2")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereTrim2  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereTrim2(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("TRIM2", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "TRIM2", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = trim2rangeleveFacade.trim2_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = trim2rangeleveFacade.trim2_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Trim2rangdeselevesparmatiere trim2rangeleve = new Trim2rangdeselevesparmatiere();
                        Trim2rangdeselevesparmatierePK trim2rangelevepk = new Trim2rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        trim2rangeleve.setTrim2rangdeselevesparmatierePK(trim2rangelevepk);
                        trim2rangeleve.setRang(uneligne[3].toString());
                        if (trim2rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            trim2rangeleveFacade.edit(trim2rangeleve);
                        } else {
                            trim2rangeleveFacade.create(trim2rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = trim2rangeleveFacade.trim2_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Trim2rangdeselevesparmatiere trim2rangeleve = new Trim2rangdeselevesparmatiere();
                        Trim2rangdeselevesparmatierePK trim2rangelevepk = new Trim2rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        trim2rangeleve.setTrim2rangdeselevesparmatierePK(trim2rangelevepk);
                        trim2rangeleve.setRang(null);
                        if (trim2rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            trim2rangeleveFacade.edit(trim2rangeleve);
                        } else {
                            trim2rangeleveFacade.create(trim2rangeleve);
                        }
                    }
                }
            }
            disciplineseq3();
            disciplineseq4();
            disciplinetrim2();
            // Charger la liste des élèves pour le conseil de classe si c'est pas déjà fait
            if ((conseiltrim2Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = conseiltrim2Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator i2 = listeEleves.iterator();
                Listedeseleves l;
                while (i2.hasNext()) {
                    l = (Listedeseleves) i2.next();
                    conseiltrim2 = new Conseildeclassetrim2();
                    conseiltrim2.setListedeseleves(l);
                    conseiltrim2.setMatriculeeleve(l.getMatriculeeleve());
                    conseiltrim2Facade.create(conseiltrim2);
                }
            }
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesTrim3() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //calcul auto des notes trimestrielles((s6+s5)/2)
            if (notetrim3Facade.noteSeq5_6(ManagedNoteSeqController.classe.toString()) != null) {
                List<Object[]> noteSeq5_6 = notetrim3Facade.noteSeq5_6(ManagedNoteSeqController.classe.toString());
                for (Object[] uneligne : noteSeq5_6) {
                    Notesdeselevestrim3 ntrim3 = new Notesdeselevestrim3();
                    Notesdeselevestrim3PK ntrim3PK = new Notesdeselevestrim3PK((String) uneligne[0], (String) uneligne[1]);
                    ntrim3.setNotesdeselevestrim3PK(ntrim3PK);
                    if ((noteannFacade.verifierEleve_matiereTrim3((String) uneligne[0], (String) uneligne[1])) != null) {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null) {
                            ntrim3.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            notetrim3Facade.edit(ntrim3);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null) {
                            ntrim3.setLanote((BigDecimal) uneligne[3]);
                            notetrim3Facade.edit(ntrim3);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null) {
                            ntrim3.setLanote((BigDecimal) uneligne[2]);
                            notetrim3Facade.edit(ntrim3);
                        } else {
                            ntrim3.setLanote(null);
                            notetrim3Facade.edit(ntrim3);
                        }
                    } else {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null) {
                            ntrim3.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            notetrim3Facade.create(ntrim3);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null) {
                            ntrim3.setLanote((BigDecimal) uneligne[3]);
                            notetrim3Facade.create(ntrim3);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null) {
                            ntrim3.setLanote((BigDecimal) uneligne[2]);
                            notetrim3Facade.create(ntrim3);
                        } else {
                            ntrim3.setLanote(null);
                            notetrim3Facade.create(ntrim3);
                        }
                    }
                }
            }
//clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = trim3MoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = trim3MoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Trim3moyennesrangsdeseleves> eleve = trim3MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        trim3moyenne = new Trim3moyennesrangsdeseleves();
                        trim3moyenne.setMatriculeeleve(matricule);
                        trim3moyenne.setMoyenne(moy);
                        trim3MoyenneFacade.edit(trim3moyenne);
                    } else {
                        trim3moyenne = new Trim3moyennesrangsdeseleves();
                        trim3moyenne.setMatriculeeleve(matricule);
                        trim3moyenne.setMoyenne(moy);
                        trim3MoyenneFacade.create(trim3moyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Trim3moyennesrangsdeseleves> eleve = trim3MoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        trim3moyenne = new Trim3moyennesrangsdeseleves();
                        trim3moyenne.setMatriculeeleve(matricule);
                        trim3moyenne.setMoyenne(null);
                        trim3MoyenneFacade.edit(trim3moyenne);
                    } else {
                        trim3moyenne = new Trim3moyennesrangsdeseleves();
                        trim3moyenne.setMatriculeeleve(matricule);
                        trim3moyenne.setMoyenne(null);
                        trim3MoyenneFacade.create(trim3moyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;

                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = trim3MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = trim3MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = trim3MoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Trim3moyennesrangsdeseleves> eleveMoyG = trim3MoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Trim3moyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Trim3moyennesrangsdeseleves) k.next();
                    trim3moyenne = new Trim3moyennesrangsdeseleves();
                    trim3moyenne.setMatriculeeleve(matricule);
                    trim3moyenne.setMoyenne(elevG.getMoyenne());
                    trim3moyenne.setMoyg1(moyG1);
                    trim3moyenne.setMoyg2(moyG2);
                    trim3moyenne.setMoyg3(moyG3);
                    trim3MoyenneFacade.edit(trim3moyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = trim3MoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                trim3moyenne = new Trim3moyennesrangsdeseleves();
                trim3moyenne.setMatriculeeleve((String) object[0]);
                trim3moyenne.setMoyenne((BigDecimal) object[1]);
                trim3moyenne.setMoyg1((BigDecimal) object[2]);
                trim3moyenne.setMoyg2((BigDecimal) object[3]);
                trim3moyenne.setMoyg3((BigDecimal) object[4]);
                trim3moyenne.setRang(object[5].toString());
                trim3MoyenneFacade.edit(trim3moyenne);
            }
            List<Object[]> eleveRangG1 = trim3MoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                trim3moyenne = new Trim3moyennesrangsdeseleves();
                trim3moyenne.setMatriculeeleve((String) object[0]);
                trim3moyenne.setMoyenne((BigDecimal) object[1]);
                trim3moyenne.setMoyg1((BigDecimal) object[2]);
                trim3moyenne.setMoyg2((BigDecimal) object[3]);
                trim3moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    trim3moyenne.setRang(object[5].toString());
                } else {
                    trim3moyenne.setRang(null);
                }
                trim3moyenne.setRangg1(object[6].toString());
                trim3MoyenneFacade.edit(trim3moyenne);
            }
            List<Object[]> eleveRangG2 = trim3MoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                trim3moyenne = new Trim3moyennesrangsdeseleves();
                trim3moyenne.setMatriculeeleve((String) object[0]);
                trim3moyenne.setMoyenne((BigDecimal) object[1]);
                trim3moyenne.setMoyg1((BigDecimal) object[2]);
                trim3moyenne.setMoyg2((BigDecimal) object[3]);
                trim3moyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    trim3moyenne.setRang(object[5].toString());
                } else {
                    trim3moyenne.setRang(null);
                }
                if (object[6] != null) {
                    trim3moyenne.setRangg1(object[6].toString());
                } else {
                    trim3moyenne.setRangg1(null);
                }
                trim3moyenne.setRangg2(object[7].toString());
                trim3MoyenneFacade.edit(trim3moyenne);
            }
            List<Object[]> eleveRangG3 = trim3MoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                trim3moyenne = new Trim3moyennesrangsdeseleves();
                trim3moyenne.setMatriculeeleve((String) object[0]);
                trim3moyenne.setMoyenne((BigDecimal) object[1]);
                trim3moyenne.setMoyg1((BigDecimal) object[2]);
                trim3moyenne.setMoyg2((BigDecimal) object[3]);
                trim3moyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    trim3moyenne.setRang(object[5].toString());
                } else {
                    trim3moyenne.setRang(null);
                }
                if (object[6] != null) {
                    trim3moyenne.setRangg1(object[6].toString());
                } else {
                    trim3moyenne.setRangg1(null);
                }
                trim3moyenne.setRangg2(object[7].toString());
                trim3moyenne.setRangg3(object[8].toString());
                trim3MoyenneFacade.edit(trim3moyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("TRIM3", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeTrim3(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeTrim3(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeTrim3(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeTrim3(ManagedNoteSeqController.classe.toString())).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "TRIM3")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereTrim1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereTrim3(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("TRIM3", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "TRIM3", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = trim3rangeleveFacade.trim3_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = trim3rangeleveFacade.trim3_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Trim3rangdeselevesparmatiere trim3rangeleve = new Trim3rangdeselevesparmatiere();
                        Trim3rangdeselevesparmatierePK trim3rangelevepk = new Trim3rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        trim3rangeleve.setTrim3rangdeselevesparmatierePK(trim3rangelevepk);
                        trim3rangeleve.setRang(uneligne[3].toString());
                        if (trim3rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            trim3rangeleveFacade.edit(trim3rangeleve);
                        } else {
                            trim3rangeleveFacade.create(trim3rangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = trim3rangeleveFacade.trim3_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Trim3rangdeselevesparmatiere trim3rangeleve = new Trim3rangdeselevesparmatiere();
                        Trim3rangdeselevesparmatierePK trim3rangelevepk = new Trim3rangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        trim3rangeleve.setTrim3rangdeselevesparmatierePK(trim3rangelevepk);
                        trim3rangeleve.setRang(null);
                        if (trim3rangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            trim3rangeleveFacade.edit(trim3rangeleve);
                        } else {
                            trim3rangeleveFacade.create(trim3rangeleve);
                        }
                    }
                }
            }
            disciplineseq5();
            disciplineseq6();
            disciplinetrim3();
            if ((conseiltrim3Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = conseiltrim3Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator i2 = listeEleves.iterator();
                Listedeseleves l;
                while (i2.hasNext()) {
                    l = (Listedeseleves) i2.next();
                    conseiltrim3 = new Conseildeclassetrim3();
                    conseiltrim3.setListedeseleves(l);
                    conseiltrim3.setMatriculeeleve(l.getMatriculeeleve());
                    conseiltrim3Facade.create(conseiltrim3);
                }
            }
            // Charger la liste des élèves pour le conseil de classe si c'est pas déjà fait
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public String calcule_des_moyennesAnn() {
        //verification si la séquence et la classe et le seuil ont été bien choisi
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe.toString() != null && ManagedNoteSeqController.seuil != null) {
            //calcul auto des notes trimestrielles((s3+s4)/2)
            if (noteannFacade.noteSeq123456(ManagedNoteSeqController.classe.toString()) != null) {
                List<Object[]> noteSeq123456 = noteannFacade.noteSeq123456(ManagedNoteSeqController.classe.toString());
                for (Object[] uneligne : noteSeq123456) {
                    Notesdeselevesann nann = new Notesdeselevesann();
                    NotesdeselevesannPK nannPK = new NotesdeselevesannPK((String) uneligne[0], (String) uneligne[1]);
                    nann.setNotesdeselevesannPK(nannPK);
                    if ((noteannFacade.verifierEleve_matiereAnn((String) uneligne[0], (String) uneligne[1])) != null) {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote(((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).add((BigDecimal) uneligne[4])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.edit(nann);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote((((BigDecimal) uneligne[3]).add((BigDecimal) uneligne[4])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.edit(nann);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote((BigDecimal) uneligne[4]);
                            noteannFacade.edit(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[4])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.edit(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] == null) {
                            nann.setLanote((BigDecimal) uneligne[2]);
                            noteannFacade.edit(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null && (BigDecimal) uneligne[4] == null) {
                            nann.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.edit(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] == null) {
                            nann.setLanote((BigDecimal) uneligne[2]);
                            noteannFacade.edit(nann);
                        } else {
                            nann.setLanote(null);
                            noteannFacade.edit(nann);
                        }
                    } else {
                        if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote(((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).add((BigDecimal) uneligne[4])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.create(nann);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] != null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote((((BigDecimal) uneligne[3]).add((BigDecimal) uneligne[4])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.create(nann);
                        } else if ((BigDecimal) uneligne[2] == null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote((BigDecimal) uneligne[4]);
                            noteannFacade.create(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] != null) {
                            nann.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[4])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.create(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] == null) {
                            nann.setLanote((BigDecimal) uneligne[2]);
                            noteannFacade.create(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] != null && (BigDecimal) uneligne[4] == null) {
                            nann.setLanote((((BigDecimal) uneligne[2]).add((BigDecimal) uneligne[3])).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN));
                            noteannFacade.create(nann);
                        } else if ((BigDecimal) uneligne[2] != null && (BigDecimal) uneligne[3] == null && (BigDecimal) uneligne[4] == null) {
                            nann.setLanote((BigDecimal) uneligne[2]);
                            noteannFacade.create(nann);
                        } else {
                            nann.setLanote(null);
                            noteannFacade.create(nann);
                        }
                    }
                }
            }
//clacul des moyennes des elseves//
            //recherche des matricules de tous les élèves par Classes
            List<Listedeseleves> listMatricule = annMoyenneFacade.findMatriculePourCalculMoyenne(ManagedNoteSeqController.classe.toString());
            Iterator i = listMatricule.iterator();
            Listedeseleves el;
            String matricule;
            while (i.hasNext()) {
                el = (Listedeseleves) i.next();
                matricule = el.getMatriculeeleve();
                List<Object[]> listeeleve = annMoyenneFacade.moyenne_de_l_eleve(matricule);
                BigDecimal sumNote = BigDecimal.ZERO;
                BigDecimal sumCoef = BigDecimal.ZERO;
                BigDecimal moy = null;
                for (Object[] uneleve : listeeleve) {
                    if ((BigDecimal) uneleve[1] == null) {
                        sumNote = sumNote.add(BigDecimal.ZERO);
                        sumCoef = sumCoef.add(BigDecimal.ZERO);
                    } else {
                        sumNote = sumNote.add((BigDecimal) uneleve[1]);
                        sumCoef = sumCoef.add((BigDecimal) uneleve[0]);
                    }

                }
                if (sumCoef.compareTo(BigDecimal.ZERO) > 0) {
                    moy = sumNote.divide(sumCoef, 2, RoundingMode.HALF_EVEN);
                }
                //création ou mise à jour de la table en créant ou modifiant 
                if (sumCoef.compareTo(ManagedNoteSeqController.seuil) >= 0) {  //somme des coeefficients d'un eleve > au seuil 
                    List<Annmoyennesrangsdeseleves> eleve = annMoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        annmoyenne = new Annmoyennesrangsdeseleves();
                        annmoyenne.setMatriculeeleve(matricule);
                        annmoyenne.setMoyenne(moy);
                        annMoyenneFacade.edit(annmoyenne);
                    } else {
                        annmoyenne = new Annmoyennesrangsdeseleves();
                        annmoyenne.setMatriculeeleve(matricule);
                        annmoyenne.setMoyenne(moy);
                        annMoyenneFacade.create(annmoyenne);
                    }
                } else {//si la somme des coefficients d'un élève est < au seuil, il est non classé et sa moy=null
                    List<Annmoyennesrangsdeseleves> eleve = annMoyenneFacade.verifier_si_existe(matricule);
                    if (eleve != null) {
                        annmoyenne = new Annmoyennesrangsdeseleves();
                        annmoyenne.setMatriculeeleve(matricule);
                        annmoyenne.setMoyenne(null);
                        annMoyenneFacade.edit(annmoyenne);
                    } else {
                        annmoyenne = new Annmoyennesrangsdeseleves();
                        annmoyenne.setMatriculeeleve(matricule);
                        annmoyenne.setMoyenne(null);
                        annMoyenneFacade.create(annmoyenne);
                    }
                }
                BigDecimal moyG1 = null;
                BigDecimal moyG2 = null;
                BigDecimal moyG3 = null;

                //calcule de la moyennes du groupe1
                List<Object[]> eleveParGroup1 = annMoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 1);
                if (eleveParGroup1 != null) {
                    BigDecimal sumNoteGroupe1 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe1 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG1 : eleveParGroup1) {
                        if ((BigDecimal) uneleveG1[1] == null) {
                            sumNoteGroupe1 = sumNoteGroupe1.add(BigDecimal.ZERO);
                            sumCoefGroupe1 = sumCoefGroupe1.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe1 = sumNoteGroupe1.add((BigDecimal) uneleveG1[1]);
                            sumCoefGroupe1 = sumCoefGroupe1.add((BigDecimal) uneleveG1[0]);
                        }

                    }
                    if (sumCoefGroupe1.compareTo(BigDecimal.ZERO) > 0) {
                        moyG1 = sumNoteGroupe1.divide(sumCoefGroupe1, 2, RoundingMode.HALF_EVEN);
                    }
                }
                //calcule de la moyennes du groupe2
                List<Object[]> eleveParGroup2 = annMoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 2);
                if (eleveParGroup2 != null) {
                    BigDecimal sumNoteGroupe2 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe2 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG2 : eleveParGroup2) {
                        if ((BigDecimal) uneleveG2[1] == null) {
                            sumNoteGroupe2 = sumNoteGroupe2.add(BigDecimal.ZERO);
                            sumCoefGroupe2 = sumCoefGroupe2.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe2 = sumNoteGroupe2.add((BigDecimal) uneleveG2[1]);
                            sumCoefGroupe2 = sumCoefGroupe2.add((BigDecimal) uneleveG2[0]);
                        }
                    }
                    if (sumCoefGroupe2.compareTo(BigDecimal.ZERO) > 0) {
                        moyG2 = sumNoteGroupe2.divide(sumCoefGroupe2, 2, RoundingMode.HALF_EVEN);
                    }

                }
                //calcule de la moyennes du groupe3
                List<Object[]> eleveParGroup3 = annMoyenneFacade.findMatriculePourCalculMoyenneParGroupe(matricule, 3);
                if (eleveParGroup3 != null) {
                    BigDecimal sumNoteGroupe3 = BigDecimal.valueOf(0);
                    BigDecimal sumCoefGroupe3 = BigDecimal.valueOf(0);
                    for (Object[] uneleveG3 : eleveParGroup3) {
                        if ((BigDecimal) uneleveG3[1] == null) {
                            sumNoteGroupe3 = sumNoteGroupe3.add(BigDecimal.ZERO);
                            sumCoefGroupe3 = sumCoefGroupe3.add(BigDecimal.ZERO);
                        } else {
                            sumNoteGroupe3 = sumNoteGroupe3.add((BigDecimal) uneleveG3[1]);
                            sumCoefGroupe3 = sumCoefGroupe3.add((BigDecimal) uneleveG3[0]);
                        }
                    }
                    if (sumCoefGroupe3.compareTo(BigDecimal.ZERO) > 0) {
                        moyG3 = sumNoteGroupe3.divide(sumCoefGroupe3, 2, RoundingMode.HALF_EVEN);
                    }

                }
                // mise à jour de la table en complétant les moyennes par groupe
                List<Annmoyennesrangsdeseleves> eleveMoyG = annMoyenneFacade.verifier_si_existe(matricule);
                Iterator k = eleveMoyG.iterator();
                Annmoyennesrangsdeseleves elevG;
                while (k.hasNext()) {
                    elevG = (Annmoyennesrangsdeseleves) k.next();
                    annmoyenne = new Annmoyennesrangsdeseleves();
                    annmoyenne.setMatriculeeleve(matricule);
                    annmoyenne.setMoyenne(elevG.getMoyenne());
                    annmoyenne.setMoyg1(moyG1);
                    annmoyenne.setMoyg2(moyG2);
                    annmoyenne.setMoyg3(moyG3);
                    annMoyenneFacade.edit(annmoyenne);

                }
            }
            // mise à jour de la table en complétant les rangs par groupe
            List<Object[]> eleveRang = annMoyenneFacade.ordreDeMeriteDesEleves(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRang) {
                annmoyenne = new Annmoyennesrangsdeseleves();
                annmoyenne.setMatriculeeleve((String) object[0]);
                annmoyenne.setMoyenne((BigDecimal) object[1]);
                annmoyenne.setMoyg1((BigDecimal) object[2]);
                annmoyenne.setMoyg2((BigDecimal) object[3]);
                annmoyenne.setMoyg3((BigDecimal) object[4]);
                annmoyenne.setRang(object[5].toString());
                annMoyenneFacade.edit(annmoyenne);
            }
            List<Object[]> eleveRangG1 = annMoyenneFacade.ordreDeMeriteDesElevesG1(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG1) {
                annmoyenne = new Annmoyennesrangsdeseleves();
                annmoyenne.setMatriculeeleve((String) object[0]);
                annmoyenne.setMoyenne((BigDecimal) object[1]);
                annmoyenne.setMoyg1((BigDecimal) object[2]);
                annmoyenne.setMoyg2((BigDecimal) object[3]);
                annmoyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    annmoyenne.setRang(object[5].toString());
                } else {
                    annmoyenne.setRang(null);
                }
                annmoyenne.setRangg1(object[6].toString());
                annMoyenneFacade.edit(annmoyenne);
            }
            List<Object[]> eleveRangG2 = annMoyenneFacade.ordreDeMeriteDesElevesG2(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG2) {
                annmoyenne = new Annmoyennesrangsdeseleves();
                annmoyenne.setMatriculeeleve((String) object[0]);
                annmoyenne.setMoyenne((BigDecimal) object[1]);
                annmoyenne.setMoyg1((BigDecimal) object[2]);
                annmoyenne.setMoyg2((BigDecimal) object[3]);
                annmoyenne.setMoyg3((BigDecimal) object[4]);
                //au cas ou le rang d'un élève est null
                if (object[5] != null) {
                    annmoyenne.setRang(object[5].toString());
                } else {
                    annmoyenne.setRang(null);
                }
                if (object[6] != null) {
                    annmoyenne.setRangg1(object[6].toString());
                } else {
                    annmoyenne.setRangg1(null);
                }
                annmoyenne.setRangg2(object[7].toString());
                annMoyenneFacade.edit(annmoyenne);
            }
            List<Object[]> eleveRangG3 = annMoyenneFacade.ordreDeMeriteDesElevesG3(ManagedNoteSeqController.classe.toString());
            for (Object[] object : eleveRangG3) {
                annmoyenne = new Annmoyennesrangsdeseleves();
                annmoyenne.setMatriculeeleve((String) object[0]);
                annmoyenne.setMoyenne((BigDecimal) object[1]);
                annmoyenne.setMoyg1((BigDecimal) object[2]);
                annmoyenne.setMoyg2((BigDecimal) object[3]);
                annmoyenne.setMoyg3((BigDecimal) object[4]);
                if (object[5] != null) {
                    annmoyenne.setRang(object[5].toString());
                } else {
                    annmoyenne.setRang(null);
                }
                if (object[6] != null) {
                    annmoyenne.setRangg1(object[6].toString());
                } else {
                    annmoyenne.setRangg1(null);
                }
                annmoyenne.setRangg2(object[7].toString());
                annmoyenne.setRangg3(object[8].toString());
                annMoyenneFacade.edit(annmoyenne);
            }
//calcul des moyenne de la classe, moyennePremier, moyenne dernier et taux de reussite
            calculnoteClasse = new Calculnotesdesclasses();
            CalculnotesdesclassesPK calculnotePK = new CalculnotesdesclassesPK("ANN", ManagedNoteSeqController.classe.toString());
            calculnoteClasse.setCalculnotesdesclassesPK(calculnotePK);
            calculnoteClasse.setMoyenne(calculnoteclasseFacade.moyenne_de_la_classeAnn(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennepremier(calculnoteclasseFacade.moyenne_premier_de_la_classeAnn(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setMoyennedernier(calculnoteclasseFacade.moyenne_dernier_de_la_classeAnn(ManagedNoteSeqController.classe.toString()));
            calculnoteClasse.setTauxreussite((calculnoteclasseFacade.taux_de_reussite_de_la_classeAnn(ManagedNoteSeqController.classe.toString(), ManagedNoteSeqController.moyPourPasser)).toString() + "%");
            if (calculnoteclasseFacade.existe_classe_et_sequence(ManagedNoteSeqController.classe.toString(), "ANN")) {
                calculnoteclasseFacade.edit(calculnoteClasse);
            } else {
                calculnoteclasseFacade.create(calculnoteClasse);
            }
            //calcul note_min_max_moy_par_matiereTrim1  
            List<Object[]> listnotesminmaxgenparmat = notesminmaxgenparmatFacade.note_min_max_moy_par_matiereTrim3(ManagedNoteSeqController.classe.toString());
            for (Object[] uneligne : listnotesminmaxgenparmat) {
                Notesminmaxgenparmat notesminmaxgenparmat = new Notesminmaxgenparmat();
                NotesminmaxgenparmatPK notesminmaxgenparmatPK = new NotesminmaxgenparmatPK("ANN", uneligne[0].toString(), ManagedNoteSeqController.classe.toString());
                notesminmaxgenparmat.setNotemax((BigDecimal) uneligne[1]);
                notesminmaxgenparmat.setNotemin((BigDecimal) uneligne[2]);
                notesminmaxgenparmat.setMoyenne(((BigDecimal) uneligne[3]).setScale(2, RoundingMode.HALF_EVEN));
                notesminmaxgenparmat.setNotesminmaxgenparmatPK(notesminmaxgenparmatPK);
                if (notesminmaxgenparmatFacade.existe_classe_et_sequence_et_matiere(ManagedNoteSeqController.classe.toString(), "ANN", uneligne[0].toString())) {
                    notesminmaxgenparmatFacade.edit(notesminmaxgenparmat);
                } else {
                    notesminmaxgenparmatFacade.create(notesminmaxgenparmat);
                }
            }
            //rangs des eleves par matière
            List<String> listeMatieresDeLaClasse = annrangeleveFacade.ann_matiere(ManagedNoteSeqController.classe.toString());
            for (String unematiere : listeMatieresDeLaClasse) {
                List<Object[]> rangParmatiere = annrangeleveFacade.ann_rang_eleve_par_matiere(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere != null) {
                    for (Object[] uneligne : rangParmatiere) {
                        Annrangdeselevesparmatiere annrangeleve = new Annrangdeselevesparmatiere();
                        AnnrangdeselevesparmatierePK annrangelevepk = new AnnrangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        annrangeleve.setAnnrangdeselevesparmatierePK(annrangelevepk);
                        annrangeleve.setRang(uneligne[3].toString());
                        if (annrangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            annrangeleveFacade.edit(annrangeleve);
                        } else {
                            annrangeleveFacade.create(annrangeleve);
                        }
                    }
                }
                //pour les eleves dont lanote est nulle
                List<Object[]> rangParmatiere_note_nulle = annrangeleveFacade.ann_rang_eleve_par_matiere_avec_note_nulle(ManagedNoteSeqController.classe.toString(), unematiere);
                if (rangParmatiere_note_nulle != null) {
                    for (Object[] uneligne : rangParmatiere_note_nulle) {
                        Annrangdeselevesparmatiere annrangeleve = new Annrangdeselevesparmatiere();
                        AnnrangdeselevesparmatierePK annrangelevepk = new AnnrangdeselevesparmatierePK(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString());
                        annrangeleve.setAnnrangdeselevesparmatierePK(annrangelevepk);
                        annrangeleve.setRang(null);
                        if (annrangeleveFacade.existe_classe_et_eleve_et_matiere(uneligne[0].toString(), uneligne[1].toString(), uneligne[2].toString())) {
                            annrangeleveFacade.edit(annrangeleve);
                        } else {
                            annrangeleveFacade.create(annrangeleve);
                        }
                    }
                }
            }
            disciplineseq1();
            disciplineseq2();
            disciplineseq3();
            disciplineseq4();
            disciplineseq5();
            disciplineseq6();
            disciplineann();
            if ((conseilannFacade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = conseilannFacade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator i2 = listeEleves.iterator();
                Listedeseleves l;
                while (i2.hasNext()) {
                    l = (Listedeseleves) i2.next();
                    conseilann = new Conseildeclasseann();
                    conseilann.setListedeseleves(l);
                    conseilann.setMatriculeeleve(l.getMatriculeeleve());
                    conseilannFacade.create(conseilann);
                }
            }
            MsgTest = true;
            Msg = "Opérattion effectuée avec succès";
            return "calculnote.xhtml?faces-redirect=true";
        } else {
            MsgTest = true;
            Msg = "Les trois choix sont obligatoires";
            return "";
        }
    }

    public void disciplineseq1() {
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe != null) {
            if ((disciplineseq1Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = disciplineseq1Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator ii = listeEleves.iterator();
                Listedeseleves l;
                while (ii.hasNext()) {
                    l = (Listedeseleves) ii.next();
                    disciplineseq1 = new Disciplinesdeselevesseq1();
                    disciplineseq1.setListedeseleves(l);
                    disciplineseq1.setMatriculeeleve(l.getMatriculeeleve());
                    disciplineseq1.setAbsb(0);
                    disciplineseq1.setAbsj(0);
                    disciplineseq1.setAbsn(0);
                    disciplineseq1.setAv(false);
                    disciplineseq1.setBl(false);
                    disciplineseq1.setConsigne(0);
                    disciplineseq1.setExclusion(0);
                    disciplineseq1Facade.create(disciplineseq1);
                }
            }
        }
    }

    public void disciplineseq2() {
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe != null) {
            if ((disciplineseq2Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = disciplineseq2Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator ii = listeEleves.iterator();
                Listedeseleves l;
                while (ii.hasNext()) {
                    l = (Listedeseleves) ii.next();
                    disciplineseq2 = new Disciplinesdeselevesseq2();
                    disciplineseq2.setListedeseleves(l);
                    disciplineseq2.setMatriculeeleve(l.getMatriculeeleve());
                    disciplineseq2.setAbsb(0);
                    disciplineseq2.setAbsj(0);
                    disciplineseq2.setAbsn(0);
                    disciplineseq2.setAv(false);
                    disciplineseq2.setBl(false);
                    disciplineseq2.setConsigne(0);
                    disciplineseq2.setExclusion(0);
                    disciplineseq2Facade.create(disciplineseq2);
                }
            }
        }
    }

    public void disciplineseq3() {
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe != null) {
            if ((disciplineseq3Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = disciplineseq3Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator ii = listeEleves.iterator();
                Listedeseleves l;
                while (ii.hasNext()) {
                    l = (Listedeseleves) ii.next();
                    disciplineseq3 = new Disciplinesdeselevesseq3();
                    disciplineseq3.setListedeseleves(l);
                    disciplineseq3.setMatriculeeleve(l.getMatriculeeleve());
                    disciplineseq3.setAbsb(0);
                    disciplineseq3.setAbsj(0);
                    disciplineseq3.setAbsn(0);
                    disciplineseq3.setAv(false);
                    disciplineseq3.setBl(false);
                    disciplineseq3.setConsigne(0);
                    disciplineseq3.setExclusion(0);
                    disciplineseq3Facade.create(disciplineseq3);
                }
            }
        }
    }

    public void disciplineseq4() {
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe != null) {
            if ((disciplineseq4Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = disciplineseq4Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator ii = listeEleves.iterator();
                Listedeseleves l;
                while (ii.hasNext()) {
                    l = (Listedeseleves) ii.next();
                    disciplineseq4 = new Disciplinesdeselevesseq4();
                    disciplineseq4.setListedeseleves(l);
                    disciplineseq4.setMatriculeeleve(l.getMatriculeeleve());
                    disciplineseq4.setAbsb(0);
                    disciplineseq4.setAbsj(0);
                    disciplineseq4.setAbsn(0);
                    disciplineseq4.setAv(false);
                    disciplineseq4.setBl(false);
                    disciplineseq4.setConsigne(0);
                    disciplineseq4.setExclusion(0);
                    disciplineseq4Facade.create(disciplineseq4);
                }
            }
        }
    }

    public void disciplineseq5() {

        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe != null) {
            if ((disciplineseq5Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = disciplineseq5Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator ii = listeEleves.iterator();
                Listedeseleves l;
                while (ii.hasNext()) {
                    l = (Listedeseleves) ii.next();
                    disciplineseq5 = new Disciplinesdeselevesseq5();
                    disciplineseq5.setListedeseleves(l);
                    disciplineseq5.setMatriculeeleve(l.getMatriculeeleve());
                    disciplineseq5.setAbsb(0);
                    disciplineseq5.setAbsj(0);
                    disciplineseq5.setAbsn(0);
                    disciplineseq5.setAv(false);
                    disciplineseq5.setBl(false);
                    disciplineseq5.setConsigne(0);
                    disciplineseq5.setExclusion(0);
                    disciplineseq5Facade.create(disciplineseq5);
                }
            }
        }
    }

    public void disciplineseq6() {
        if (ManagedNoteSeqController.seq != null && ManagedNoteSeqController.classe != null) {
            if ((disciplineseq6Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString())) != null) {
                listeEleves = disciplineseq6Facade.listeDesNouveauxEleves(ManagedNoteSeqController.classe.toString());
                Iterator ii = listeEleves.iterator();
                Listedeseleves l;
                while (ii.hasNext()) {
                    l = (Listedeseleves) ii.next();
                    disciplineseq6 = new Disciplinesdeselevesseq6();
                    disciplineseq6.setListedeseleves(l);
                    disciplineseq6.setMatriculeeleve(l.getMatriculeeleve());
                    disciplineseq6.setAbsb(0);
                    disciplineseq6.setAbsj(0);
                    disciplineseq6.setAbsn(0);
                    disciplineseq6.setAv(false);
                    disciplineseq6.setBl(false);
                    disciplineseq6.setConsigne(0);
                    disciplineseq6.setExclusion(0);
                    disciplineseq6Facade.create(disciplineseq6);
                }
            }
        }
    }

    public void disciplinetrim1() {
        for (Object[] uneligne : disciplinetrim1Facade.liesteEleveDiscip1_2(ManagedNoteSeqController.classe.toString())) {
            int ab = (int) uneligne[1] + (int) uneligne[6];
            int aj = (int) uneligne[2] + (int) uneligne[7];
            int an = (int) uneligne[3] + (int) uneligne[8];
            boolean av = false;
            boolean bl = false;
            int co = (int) uneligne[4] + (int) uneligne[9];
            int ex = (int) uneligne[5] + (int) uneligne[10];
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if (new BigDecimal(an).compareTo(aver) >= 0) {
                av = true;
            } else {
                av = false;
            }
            if (new BigDecimal(an).compareTo(blam) >= 0) {
                bl = true;
            } else {
                bl = false;
            }
            disciplinetrim1 = new Disciplinesdeselevestrim1();
            disciplinetrim1.setMatriculeeleve((String) uneligne[0]);
            disciplinetrim1.setAbsb(ab);
            disciplinetrim1.setAbsj(aj);
            disciplinetrim1.setAbsn(an);
            disciplinetrim1.setAv(av);
            disciplinetrim1.setBl(bl);
            disciplinetrim1.setConsigne(co);
            disciplinetrim1.setExclusion(ex);
            if (disciplinetrim1Facade.existeEleve((String) uneligne[0])) {
                disciplinetrim1Facade.edit(disciplinetrim1);
            } else {
                disciplinetrim1Facade.create(disciplinetrim1);
            }
        }
    }

    public void disciplinetrim2() {
        for (Object[] uneligne : disciplinetrim2Facade.liesteEleveDiscip3_4(ManagedNoteSeqController.classe.toString())) {
            int ab = (int) uneligne[1] + (int) uneligne[6];
            int aj = (int) uneligne[2] + (int) uneligne[7];
            int an = (int) uneligne[3] + (int) uneligne[8];
            boolean av = false;
            boolean bl = false;
            int co = (int) uneligne[4] + (int) uneligne[9];
            int ex = (int) uneligne[5] + (int) uneligne[10];
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if (new BigDecimal(an).compareTo(aver) >= 0) {
                av = true;
            } else {
                av = false;
            }
            if (new BigDecimal(an).compareTo(blam) >= 0) {
                bl = true;
            } else {
                bl = false;
            }
            disciplinetrim2 = new Disciplinesdeselevestrim2();
            disciplinetrim2.setMatriculeeleve((String) uneligne[0]);
            disciplinetrim2.setAbsb(ab);
            disciplinetrim2.setAbsj(aj);
            disciplinetrim2.setAbsn(an);
            disciplinetrim2.setAv(av);
            disciplinetrim2.setBl(bl);
            disciplinetrim2.setConsigne(co);
            disciplinetrim2.setExclusion(ex);
            if (disciplinetrim2Facade.existeEleve((String) uneligne[0])) {
                disciplinetrim2Facade.edit(disciplinetrim2);
            } else {
                disciplinetrim2Facade.create(disciplinetrim2);
            }
        }
    }

    public void disciplinetrim3() {
        for (Object[] uneligne : disciplinetrim3Facade.liesteEleveDiscip5_6(ManagedNoteSeqController.classe.toString())) {
            int ab = (int) uneligne[1] + (int) uneligne[6];
            int aj = (int) uneligne[2] + (int) uneligne[7];
            int an = (int) uneligne[3] + (int) uneligne[8];
            boolean av = false;
            boolean bl = false;
            int co = (int) uneligne[4] + (int) uneligne[9];
            int ex = (int) uneligne[5] + (int) uneligne[10];
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if (new BigDecimal(an).compareTo(aver) >= 0) {
                av = true;
            } else {
                av = false;
            }
            if (new BigDecimal(an).compareTo(blam) >= 0) {
                bl = true;
            } else {
                bl = false;
            }
            disciplinetrim3 = new Disciplinesdeselevestrim3();
            disciplinetrim3.setMatriculeeleve((String) uneligne[0]);
            disciplinetrim3.setAbsb(ab);
            disciplinetrim3.setAbsj(aj);
            disciplinetrim3.setAbsn(an);
            disciplinetrim3.setAv(av);
            disciplinetrim3.setBl(bl);
            disciplinetrim3.setConsigne(co);
            disciplinetrim3.setExclusion(ex);
            if (disciplinetrim3Facade.existeEleve((String) uneligne[0])) {
                disciplinetrim3Facade.edit(disciplinetrim3);
            } else {
                disciplinetrim3Facade.create(disciplinetrim3);
            }
        }
    }

    public void disciplineann() {
        for (Object[] uneligne : disciplineannFacade.liesteEleveDiscip1_a_6(ManagedNoteSeqController.classe.toString())) {
            int ab = (int) uneligne[1] + (int) uneligne[6] + (int) uneligne[11] + (int) uneligne[16] + (int) uneligne[21] + (int) uneligne[26];
            int aj = (int) uneligne[2] + (int) uneligne[7] + (int) uneligne[12] + (int) uneligne[17] + (int) uneligne[22] + (int) uneligne[27];
            int an = (int) uneligne[3] + (int) uneligne[8] + (int) uneligne[13] + (int) uneligne[18] + (int) uneligne[23] + (int) uneligne[28];
            boolean av = false;
            boolean bl = false;
            int co = (int) uneligne[4] + (int) uneligne[9] + (int) uneligne[14] + (int) uneligne[19] + (int) uneligne[24] + (int) uneligne[29];
            int ex = (int) uneligne[5] + (int) uneligne[10] + (int) uneligne[15] + (int) uneligne[20] + (int) uneligne[25] + (int) uneligne[30];
            BigDecimal aver = etsFacade.borneAvertissement();
            BigDecimal blam = etsFacade.borneBlame();
            if (new BigDecimal(an).compareTo(aver) >= 0) {
                av = true;
            } else {
                av = false;
            }
            if (new BigDecimal(an).compareTo(blam) >= 0) {
                bl = true;
            } else {
                bl = false;
            }
            disciplineann = new Disciplinesdeselevesann();
            disciplineann.setMatriculeeleve((String) uneligne[0]);
            disciplineann.setAbsb(ab);
            disciplineann.setAbsj(aj);
            disciplineann.setAbsn(an);
            disciplineann.setAv(av);
            disciplineann.setBl(bl);
            disciplineann.setConsigne(co);
            disciplineann.setExclusion(ex);
            if (disciplineannFacade.existeEleve((String) uneligne[0])) {
                disciplineannFacade.edit(disciplineann);
            } else {
                disciplineannFacade.create(disciplineann);
            }
        }
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public boolean isMsgTest() {
        return MsgTest;
    }

    public void setMsgTest(boolean MsgTest) {
        this.MsgTest = MsgTest;
    }

    public boolean isImageConirm() {
        return imageConirm;
    }

    public void setImageConirm(boolean imageConirm) {
        this.imageConirm = imageConirm;
    }

}
