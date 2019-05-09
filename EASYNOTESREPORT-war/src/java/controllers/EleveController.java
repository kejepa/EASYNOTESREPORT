/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Classes;
import entities.Listedeseleves;
import entities.Operations;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import sessions.ListedeselevesFacadeLocal;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "eleveController")
@SessionScoped
public class EleveController implements Serializable {

    @EJB
    private ListedeselevesFacadeLocal eleveFacade;
    @EJB
    private OperationsFacadeLocal mouchardFacade;
    private List<Listedeseleves> eleveList = new ArrayList<>();
    private Listedeseleves eleve;
    private String operation = "add";
    private boolean champActif = true;
    private boolean TestMsgLogin = false;
    private boolean imgConfirm = false;
    private String excelFile = "";
    private String sms = "";
    private boolean affiche_sms1 = false;
    private boolean affiche_sms2 = false;
    private Part part;
    private Classes classe;
    private boolean celluleConfirm = false;
    private boolean bonexcelConfirm = false;
    private boolean fauxexcelConfirm = false;
    int lastligne = 0;
    int lastCol = 0;

    public EleveController() {
    }

    public String eleves() {
        eleveList.clear();
        eleveList.addAll(eleveFacade.findAll());
        eleve = new Listedeseleves();
//        affiche_sms = false;
        return "eleve.xhtml?faces-redirect=true";
    }

    public String ajoutParListe() {
        return "ajouteleveparliste.xhtml?faces-redirect=true";
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "ajouter":
                TestMsgLogin = false;
                champActif = true;
                eleve = new Listedeseleves();
                break;
            case "modifier":
                TestMsgLogin = false;
                champActif = true;
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

    public String uploadfiles() throws IOException {
        try {
            Path mypath = Paths.get(new File("/EASYNOTESREPORT/fichierspourcorrection/" + part.getSubmittedFileName()).toString());
            Files.deleteIfExists(mypath);
            InputStream in = part.getInputStream();
            File f = new File("/EASYNOTESREPORT/fichierspourcorrection/" + part.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            String extension = (new File("/EASYNOTESREPORT/fichierspourcorrection/" + part.getSubmittedFileName()).toString().substring(f.toString().length() - 4, f.toString().length()));
            if (extension.equals("xlsx")) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
                InputStream input = new FileInputStream(f.toString());
                XSSFWorkbook wb = new XSSFWorkbook(input);
                XSSFSheet sheet = wb.getSheetAt(0);
                Row row;
                lastligne = sheet.getLastRowNum() + 1;
                lastCol = sheet.getDefaultColumnWidth();
                affiche_sms1 = true;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    celluleConfirm = false;
                    row = (Row) sheet.getRow(i);
                    int k = i + 1;
                    try {
                        if (!CellType.BLANK.equals(row.getCell(0).getCellTypeEnum())) {
                            if (row.getCell(0).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule A" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(1).getCellTypeEnum())) {
                            if (row.getCell(1).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule B" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(2).getCellTypeEnum())) {
                            if (row.getCell(2).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule C" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(3).getCellTypeEnum())) {
                            if (row.getCell(3).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule D" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(4).getCellTypeEnum())) {
                            if (row.getCell(4).getCellTypeEnum() == CellType.STRING || row.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
                                sms = "Erreur à la cellule E" + k + " la cellule doit avoir une date";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(5).getCellTypeEnum())) {
                            if (row.getCell(5).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule F" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(6).getCellTypeEnum())) {
                            if (row.getCell(6).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule G" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(7).getCellTypeEnum())) {
                            if (row.getCell(7).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule H" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(8).getCellTypeEnum())) {
                            if (row.getCell(8).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule I" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (!CellType.BLANK.equals(row.getCell(9).getCellTypeEnum())) {
                            if (row.getCell(9).getCellTypeEnum() != CellType.STRING) {
                                sms = "Erreur à la cellule J" + k + " la cellule doit avoir un texte";
                                bonexcelConfirm = false;
                                fauxexcelConfirm = true;
                                celluleConfirm = true;
                                return "";
                            }
                        }
                    } catch (Exception e) {

                    }
                }
                bonexcelConfirm = true;
                fauxexcelConfirm = false;
                affiche_sms1 = false;
                if (classe != null) {
                    celluleConfirm = false;
                }
                processUpload();
            } else {
                sms = "Le Fichier excel doit avoir l'extention << xlsx >> i.e enregistré comme Classeur Excel";
                celluleConfirm = true;
                bonexcelConfirm = false;
                fauxexcelConfirm = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            Listedeseleves student = (Listedeseleves) table.getRowData();
            eleveFacade.edit(student);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String processUpload() throws IOException {
        if (classe != null) {
            try {
                Path mypath = Paths.get(new File("/EASYNOTESREPORT/fichiers/" + part.getSubmittedFileName()).toString());
                Files.deleteIfExists(mypath);
                InputStream in = part.getInputStream();
                File f = new File("/EASYNOTESREPORT/fichiers/" + part.getSubmittedFileName());
                f.createNewFile();
                FileOutputStream out = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                out.close();
                in.close();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
                InputStream input = new FileInputStream(f.toString());
                XSSFWorkbook wb = new XSSFWorkbook(input);
                XSSFSheet sheet = wb.getSheetAt(0);
                Row row;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    row = (Row) sheet.getRow(i);

                    String matriculeeleve;
                    if (row.getCell(0) != null) {
                        matriculeeleve = row.getCell(0).toString();
                    } else {
                        matriculeeleve = null;
                    }
                    String nom;
                    if (row.getCell(1) != null) {
                        nom = row.getCell(1).toString();
                    } else {
                        nom = null;
                    }
                    String prenom;
                    if (row.getCell(2) != null) {
                        prenom = row.getCell(2).toString();
                    } else {
                        prenom = null;
                    }
                    String sexe;
                    if (row.getCell(3) != null) {
                        sexe = row.getCell(3).toString();
                    } else {
                        sexe = null;
                    }
                    Date date;
                    if (row.getCell(4) != null) {
                        date = row.getCell(4).getDateCellValue();
                    } else {
                        date = null;
                    }
                    String lieu;
                    if (row.getCell(5) != null) {
                        lieu = row.getCell(5).toString();
                    } else {
                        lieu = null;
                    }
                    String adresse;
                    if (row.getCell(6) != null) {
                        adresse = row.getCell(6).toString();
                    } else {
                        adresse = null;
                    }
                    String red;
                    if (row.getCell(7) != null) {
                        red = row.getCell(7).toString();
                    } else {
                        red = null;
                    }
                    String inscrit;
                    if (row.getCell(8) != null) {
                        inscrit = row.getCell(8).toString();
                    } else {
                        inscrit = null;
                    }
                    String photo;
                    if (row.getCell(9) != null) {
                        photo = row.getCell(9).toString();
                    } else {
                        photo = null;
                    }
                    eleve = new Listedeseleves();
                    eleve.setMatriculeeleve(matriculeeleve);
                    eleve.setCodeclasse(classe);
                    eleve.setNom(nom);
                    eleve.setPrenom(prenom);
                    eleve.setSexe(sexe);
                    eleve.setDatenaiss(date);
                    eleve.setLieunaiss(lieu);
                    eleve.setAdresse(adresse);
                    eleve.setRedoublant(red);
                    eleve.setInscrit(inscrit);
                    eleve.setPhoto(photo);
                    if (eleveFacade.rechercheEleveParMatricule(eleve.getMatriculeeleve())) {
                        eleveFacade.edit(eleve);  
                    } else {
                        eleveFacade.create(eleve);
                    }
                }
                sms = "Bravo les élèves de la " + classe + " ont été enregistrés avec succès";
                celluleConfirm = true;
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String dateString = df.format(date);
                try {
                    Date dateOperation = df.parse(dateString);
                    Operations mouchard = new Operations();
                    mouchard.setIdoperations(mouchardFacade.nextId());
                    mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                    mouchard.setTypeoperation("Chargement du fichier Excel");
                    mouchard.setDateoperation(dateOperation);
                    mouchard.setDescription("Chargement du fichier Excel des élèves de la Classe " + classe);
                    mouchardFacade.create(mouchard);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {

            }
            Path path = Paths.get(new File("/EASYNOTESREPORT/fichiers/" + part.getSubmittedFileName()).toString());
            Files.deleteIfExists(path);
        } else {
            sms = "Veuillez Choisir la classe svp!";
            celluleConfirm = true;
            return "";
        }
        return ajoutParListe();
    }

    public void desactiverimage() {
        imgConfirm = false;
    }

    public String persistEleves() throws InterruptedException {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dateString = df.format(date);
            switch (operation) {
                case "ajouter":
                    if (eleveFacade.rechercheEleveParMatricule(eleve.getMatriculeeleve())) {
                        TestMsgLogin = true;
                        return "";
                    } else {
                        eleveFacade.create(eleve);
                        try {
                            Date dateOperation = df.parse(dateString);
                            Operations mouchard = new Operations();
                            mouchard.setIdoperations(mouchardFacade.nextId());
                            mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                            mouchard.setTypeoperation("Ajout");
                            mouchard.setDateoperation(dateOperation);
                            mouchard.setDescription("Ajout de l'élève " + eleve.getNom() + " " + eleve.getPrenom() + "(" + eleve.getMatriculeeleve() + ")");
                            mouchardFacade.create(mouchard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        eleve = new Listedeseleves();

                    }
                    imgConfirm = true;
                    return "";
                case "modifier":
                    eleveFacade.edit(eleve);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Modification");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Modification de l'élève " + eleve.getNom() + " " + eleve.getPrenom() + "(" + eleve.getMatriculeeleve() + ")");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "supprimer":
                    eleveFacade.remove(eleve);
                    try {
                        Date dateOperation = df.parse(dateString);
                        Operations mouchard = new Operations();
                        mouchard.setIdoperations(mouchardFacade.nextId());
                        mouchard.setLogin(mouchardFacade.userByString(ConnexionController.nom));
                        mouchard.setTypeoperation("Suppression");
                        mouchard.setDateoperation(dateOperation);
                        mouchard.setDescription("Suppression de l'élève " + eleve.getNom() + " " + eleve.getPrenom() + "(" + eleve.getMatriculeeleve() + ")");
                        mouchardFacade.create(mouchard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } catch (Exception e) {
        }
        return eleves();
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isChampActif() {
        return champActif;
    }

    public void setChampActif(boolean champActif) {
        this.champActif = champActif;
    }

    public boolean isTestMsgLogin() {
        return TestMsgLogin;
    }

    public void setTestMsgLogin(boolean TestMsgLogin) {
        this.TestMsgLogin = TestMsgLogin;
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean isImgConfirm() {
        return imgConfirm;
    }

    public void setImgConfirm(boolean imgConfirm) {
        this.imgConfirm = imgConfirm;
    }

    public String getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(String excelFile) {
        this.excelFile = excelFile;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    public boolean isCelluleConfirm() {
        return celluleConfirm;
    }

    public void setCelluleConfirm(boolean celluleConfirm) {
        this.celluleConfirm = celluleConfirm;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public boolean isAffiche_sms1() {
        return affiche_sms1;
    }

    public void setAffiche_sms1(boolean affiche_sms1) {
        this.affiche_sms1 = affiche_sms1;
    }

    public boolean isAffiche_sms2() {
        return affiche_sms2;
    }

    public void setAffiche_sms2(boolean affiche_sms2) {
        this.affiche_sms2 = affiche_sms2;
    }

    public int getLastligne() {
        return lastligne;
    }

    public void setLastligne(int lastligne) {
        this.lastligne = lastligne;
    }

    public int getLastCol() {
        return lastCol;
    }

    public void setLastCol(int lastCol) {
        this.lastCol = lastCol;
    }

    public boolean isBonexcelConfirm() {
        return bonexcelConfirm;
    }

    public void setBonexcelConfirm(boolean bonexcelConfirm) {
        this.bonexcelConfirm = bonexcelConfirm;
    }

    public boolean isFauxexcelConfirm() {
        return fauxexcelConfirm;
    }

    public void setFauxexcelConfirm(boolean fauxexcelConfirm) {
        this.fauxexcelConfirm = fauxexcelConfirm;
    }

}
