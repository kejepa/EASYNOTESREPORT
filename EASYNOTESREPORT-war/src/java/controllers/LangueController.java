/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "langueController")
@SessionScoped
public class LangueController implements Serializable {

    private String langue = "fr";

    public LangueController() {
    }

    public String francais() {
        langue = "fr";
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        return ext.getRequestPathInfo() + "?faces-redirect=true";
    }

    public String anglais() {
        langue = "en";
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        return ext.getRequestPathInfo() + "?faces-redirect=true";
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

}
