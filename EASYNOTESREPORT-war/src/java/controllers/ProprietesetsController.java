/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Proprietesets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.event.FlowEvent;
import sessions.ProprietesetsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "proprietesetsController")
@SessionScoped
public class ProprietesetsController implements Serializable {

    @EJB
    private ProprietesetsFacadeLocal pptFacade;
    private List<Proprietesets> pptList = new ArrayList<>();
    private Proprietesets pptets = new Proprietesets();
    private Part image;
    private boolean upladed;
    private boolean Modifierpptets = true;
    private boolean Enregistrerpptets = false;
    private boolean activerPanel = true;
    private String cheminLogo;
    private boolean skip;

    public ProprietesetsController() {
    }

    public String Modifier_pptEts() {
        activerPanel = false;
        Enregistrerpptets = true;
        Modifierpptets = false;
        for (Object[] uniqueligne : pptFacade.infosEts()) {
            pptets = new Proprietesets();
            pptets.setNomdelets((String) uniqueligne[0]);
            pptets.setNomdeletsan((String) uniqueligne[1]);
            pptets.setDelegationregfr((String) uniqueligne[2]);
            pptets.setDelegationregan((String) uniqueligne[3]);
            pptets.setDelegationdepfr((String) uniqueligne[4]);
            pptets.setDelegationdepan((String) uniqueligne[5]);
            pptets.setTelephone((String) uniqueligne[6]);
            pptets.setBoitepostale((String) uniqueligne[7]);
            pptets.setEmail((String) uniqueligne[8]);
            pptets.setCheminlogo((String) uniqueligne[9]);
            pptets.setSituation((String) uniqueligne[10]);
            pptets.setPrincipal((String) uniqueligne[11]);
        }
        return "proprietesets.xhtml?faces-redirect=true";
    }

    public String Enregistrer_pptEts() {
        String etsfr = pptets.getNomdelets();
        String etsan = pptets.getNomdeletsan();
        String regfr = pptets.getDelegationregfr();
        String regan = pptets.getDelegationregan();
        String depfr = pptets.getDelegationdepfr();
        String depan = pptets.getDelegationdepan();
        String tel = pptets.getTelephone();
        String bp = pptets.getBoitepostale();
        String email = pptets.getEmail();
        String chemin = pptets.getCheminlogo();
        String situation = pptets.getSituation();
        String principal = pptets.getPrincipal();
        for (Object[] uniqueligne : pptFacade.infosEts()) {
            pptets = new Proprietesets();
            pptets.setNomdelets(etsfr);
            pptets.setNomdeletsan(etsan);
            pptets.setDelegationregfr(regfr);
            pptets.setDelegationregan(regan);
            pptets.setDelegationdepfr(depfr);
            pptets.setDelegationdepan(depan);
            pptets.setTelephone(tel);
            pptets.setBoitepostale(bp);
            pptets.setEmail(email);
            pptets.setCheminlogo(chemin);
            pptets.setSituation(situation);
            pptets.setPrincipal(principal);
            pptets.setAnnee((String) uniqueligne[12]);
            pptFacade.edit(pptets); 
        }
        activerPanel = true;
        Enregistrerpptets = false;
        Modifierpptets = true;
        return "proprietesets.xhtml?faces-redirect=true";
    }

    public String proprieteets() {
        return "proprietesets.xhtml?faces-redirect=true";
    }

    public String cheminLogo() {
        String chemin = new File("/EASYNOTESREPORT/images/" + image.getSubmittedFileName()).toString();
        return chemin;
    }

    public String uploadfiles() {
        try {
            InputStream in = image.getInputStream();
            File f = new File("/EASYNOTESREPORT/images/" + image.getSubmittedFileName());
            f.createNewFile();
            cheminLogo = f.getAbsolutePath();
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
            upladed = true;
            //
            String nomFr = pptets.getNomdelets();
            String nomAn = pptets.getNomdeletsan();
            String delegationRegFr = pptets.getDelegationregfr();
            String delegationRegAn = pptets.getDelegationregan();
            String delegationDepFr = pptets.getDelegationdepfr();
            String delegationDepAn = pptets.getDelegationdepan();
            String tel = pptets.getTelephone();
            String bp = pptets.getBoitepostale();
            String email = pptets.getEmail();
            String chem = pptets.getCheminlogo();
            String situation = pptets.getSituation();
            String principal = pptets.getPrincipal();
            pptets = new Proprietesets();
            pptets.setNomdelets(nomFr);
            pptets.setNomdeletsan(nomAn);
            pptets.setDelegationregfr(delegationRegFr);
            pptets.setDelegationregan(delegationRegAn);
            pptets.setDelegationdepfr(delegationDepFr);
            pptets.setDelegationdepan(delegationDepAn);
            pptets.setTelephone(tel);
            pptets.setBoitepostale(bp);
            pptets.setEmail(email); 
            String chemin = new File("C:\\EASYNOTESREPORT\\images\\" + image.getSubmittedFileName()).toString();
            pptets.setCheminlogo(chemin);
            pptets.setSituation(situation);
            pptets.setPrincipal(principal);
        } catch (Exception e) {
        }
        return "proprietesets.xhtml?faces-redirect=true";
    }

    private String getFilename(Part p) {
        for (String cd : p.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public Proprietesets getPptets() {
        return pptets;
    }

    public void setPptets(Proprietesets pptets) {
        this.pptets = pptets;
    }

    public List<Proprietesets> getPptList() {
        return pptList;
    }

    public void setPptList(List<Proprietesets> pptList) {
        this.pptList = pptList;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public boolean isUpladed() {
        return upladed;
    }

    public void setUpladed(boolean upladed) {
        this.upladed = upladed;
    }

    public boolean isModifierpptets() {
        return Modifierpptets;
    }

    public void setModifierpptets(boolean Modifierpptets) {
        this.Modifierpptets = Modifierpptets;
    }

    public boolean isEnregistrerpptets() {
        return Enregistrerpptets;
    }

    public void setEnregistrerpptets(boolean Enregistrerpptets) {
        this.Enregistrerpptets = Enregistrerpptets;
    }

    public boolean isActiverPanel() {
        return activerPanel;
    }

    public void setActiverPanel(boolean activerPanel) {
        this.activerPanel = activerPanel;
    }

    public String getCheminLogo() {
        return cheminLogo;
    }

    public void setCheminLogo(String cheminLogo) {
        this.cheminLogo = cheminLogo;
    }

}
