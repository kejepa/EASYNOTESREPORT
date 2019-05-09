/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import controllers.ConnexionController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KENFACK JP
 */
public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        ConnexionController session = (ConnexionController) req.getSession().getAttribute("connexionController");
        String url = req.getRequestURI();
        if (session == null || !session.isIslogged()) {
            if (url.contains("pp.xhtml")
                    || url.contains("accueil.xhtml")
                    || url.contains("affectationEnseigClasse.xhtml")
                    || url.contains("affectationMatiere.xhtml")
                    || url.contains("ajouteleveparliste.xhtml")
                    || url.contains("bulletin.xhtml")
                    || url.contains("calculnote.xhtml")
                    || url.contains("changePassword.xhtml")
                    || url.contains("classe.xhtml")
                    || url.contains("conseilann.xhtml")
                    || url.contains("conseiltrim1.xhtml")
                    || url.contains("conseiltrim2.xhtml")
                    || url.contains("conseiltrim3.xhtml")
                    || url.contains("consulterDisciplineann.xhtml")
                    || url.contains("consulterDisciplineseq.xhtml")
                    || url.contains("consulterDisciplineseq1.xhtml")
                    || url.contains("consulterDisciplineseq2.xhtml")
                    || url.contains("consulterDisciplineseq3.xhtml")
                    || url.contains("consulterDisciplineseq4.xhtml")
                    || url.contains("consulterDisciplineseq5.xhtml")
                    || url.contains("consulterDisciplineseq6.xhtml")
                    || url.contains("consulterDisciplinetrim1.xhtml")
                    || url.contains("consulterDisciplinetrim2.xhtml")
                    || url.contains("consulterDisciplinetrim3.xhtml")
                    || url.contains("disciplineseq1.xhtml")
                    || url.contains("disciplineseq2.xhtml")
                    || url.contains("disciplineseq3.xhtml")
                    || url.contains("disciplineseq4.xhtml")
                    || url.contains("disciplineseq5.xhtml")
                    || url.contains("disciplineseq6.xhtml")
                    || url.contains("dp.xhtml")
                    || url.contains("eleve.xhtml")
                    || url.contains("eleveAdmis.xhtml")
                    || url.contains("eleveParClasse.xhtml")
                    || url.contains("ficheStat.xhtml")
                    || url.contains("ficheSynth.xhtml")
                    || url.contains("ficheconseil.xhtml")
                    || url.contains("fichedediscipline.xhtml")
                    || url.contains("fichedesnotes.xhtml")
                    || url.contains("managedConseil.xhtml")
                    || url.contains("manageddiscipline.xhtml")
                    || url.contains("managedsaisiepareleve.xhtml")
                    || url.contains("matiere.xhtml")
                    || url.contains("mouchard.xhtml")
                    || url.contains("noteseq1.xhtml")
                    || url.contains("noteseq2.xhtml")
                    || url.contains("noteseq3.xhtml")
                    || url.contains("noteseq4.xhtml")
                    || url.contains("noteseq5.xhtml")
                    || url.contains("noteseq6.xhtml")
                    || url.contains("notesreconduites2.xhtml")
                    || url.contains("notesreconduites3.xhtml")
                    || url.contains("notesreconduites4.xhtml")
                    || url.contains("notesreconduites5.xhtml")
                    || url.contains("notesreconduites6.xhtml")
                    || url.contains("password.xhtml")
                    || url.contains("personnel.xhtml")
                    || url.contains("proprietesets.xhtml")
                    || url.contains("pv.xhtml")
                    || url.contains("reconduirenotes.xhtml")
                    || url.contains("saisiedp.xhtml")
                    || url.contains("saisienotepareleveseq1.xhtml")
                    || url.contains("saisienotepareleveseq2.xhtml")
                    || url.contains("saisienotepareleveseq3.xhtml")
                    || url.contains("saisienotepareleveseq4.xhtml")
                    || url.contains("saisienotepareleveseq5.xhtml")
                    || url.contains("saisienotepareleveseq6.xhtml")
                    || url.contains("saisienotes.xhtml")
                    || url.contains("specialite.xhtml")
                    || url.contains("statDP.xhtml")
                    || url.contains("tableau.xhtml")
                    || url.contains("utilisateur.xhtml")
                    || url.contains("verifienotepareleveseq1.xhtml")
                    || url.contains("verifienotepareleveseq2.xhtml")
                    || url.contains("verifienotepareleveseq3.xhtml")
                    || url.contains("verifienotepareleveseq4.xhtml")
                    || url.contains("verifienotepareleveseq5.xhtml")
                    || url.contains("verifienotepareleveseq6.xhtml")
                    || url.contains("activersequence.xhtml")
                    || url.contains("sms.xhtml")
                    || url.contains("statparniveau.xhtml")) {
                res.sendRedirect(req.getServletContext().getContextPath() + "/faces/index.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Nothing to do here!
    }
}
