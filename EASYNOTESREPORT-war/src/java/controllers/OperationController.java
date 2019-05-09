/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Operations;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.component.commandbutton.CommandButton;
import sessions.OperationsFacadeLocal;

/**
 *
 * @author KENFACK JP
 */
@Named(value = "operationController")
@SessionScoped
public class OperationController implements Serializable {

    @EJB
    private OperationsFacadeLocal operationFacade;
    private List<Operations> operationList = new ArrayList<>();
    private Operations mouchard;
    private String msg = "";
    private String operation = "add";
    private boolean champActif = true;

    public OperationController() {
    }

    public String mouchards() {
        operationList.clear();
        operationList.addAll(operationFacade.findAll());
        mouchard = new Operations();
        return "mouchard.xhtml?faces-redirect=true";
    }

    public void btnClick(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        switch (operation) {
            case "supprimer":
                champActif = true;
                break;
            case "supprimertout":
                champActif = true;
                break;
            case "consulter":
                champActif = false;
                break;
        }
    }

    public String persistMouchard() {
        try {
            switch (operation) {
                case "supprimer":
                    operationFacade.remove(mouchard);
                    break;
                case "supprimertout":
                    operationFacade.deleteAll();
                    break;
            }
            msg = "";
        } catch (Exception e) {
            msg = "Echec de l'opération";
        }
        return mouchards();
    }

    public String imprimer() {
        try {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(operationList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("rapports/ListeOperations.jasper");
            Map parameters = new HashMap();
            parameters.put("REPORT_TITLE", "");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=ListeOperations.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            msg = "Opération effectuée avec succès";
        } catch (JRException | IOException e) {
            msg = "Echec de l'opération";
        }
        return "mouchard.xhtml?faces-redirect=true";
    }

    public OperationsFacadeLocal getOperationFacade() {
        return operationFacade;
    }

    public void setOperationFacade(OperationsFacadeLocal operationFacade) {
        this.operationFacade = operationFacade;
    }

    public List<Operations> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operations> operationList) {
        this.operationList = operationList;
    }

    public Operations getMouchard() {
        return mouchard;
    }

    public void setMouchard(Operations mouchard) {
        this.mouchard = mouchard;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

}
