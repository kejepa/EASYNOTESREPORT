/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Classes;
import entities.Donnespedagogiques;
import entities.Matieres;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface DonnespedagogiquesFacadeLocal {

    void create(Donnespedagogiques donnespedagogiques);

    void edit(Donnespedagogiques donnespedagogiques);

    void remove(Donnespedagogiques donnespedagogiques);

    Donnespedagogiques find(Object id);

    List<Donnespedagogiques> findAll();

    List<Donnespedagogiques> findRange(int[] range);

    int count();

    List<Donnespedagogiques> listeDesDp(String niveaux, String codematiere, String periode);

    List<Classes> listedesNiveaux();

    List<Object[]> listedesMatieresParClasse(String classcorresp);

    List<Matieres> listedesMatieres(String classcorresp);

    String typeClasse(String classcorresp);

    BigDecimal effectifParNiveau(String classcorresp);

    List<Object[]> ficheApParMatiere(String periode, String codematiere,String section);

    List<Object[]> ficheAPall(String periode,String section);

    List<Object[]> ficheExamInter(String typedeclasse, String periode,String section);

    BigDecimal moySup10(String classcorresp, String codematiere);

    BigDecimal mg(String classcorresp, String codematiere);

    BigDecimal effectifAbst(String codematiere, String classcorresp);

    BigDecimal moySup10S2(String classcorresp, String codematiere);

    BigDecimal mgS2(String classcorresp, String codematiere);

    BigDecimal effectifAbstS2(String codematiere, String classcorresp);

    BigDecimal moySup10S3(String classcorresp, String codematiere);

    BigDecimal mgS3(String classcorresp, String codematiere);

    BigDecimal effectifAbstS3(String codematiere, String classcorresp);

    BigDecimal moySup10S4(String classcorresp, String codematiere);

    BigDecimal mgS4(String classcorresp, String codematiere);

    BigDecimal effectifAbstS4(String codematiere, String classcorresp);

    BigDecimal moySup10S5(String classcorresp, String codematiere);

    BigDecimal mgS5(String classcorresp, String codematiere);

    BigDecimal effectifAbstS5(String codematiere, String classcorresp);

    BigDecimal moySup10S6(String classcorresp, String codematiere);

    BigDecimal mgS6(String classcorresp, String codematiere);

    BigDecimal effectifAbstS6(String codematiere, String classcorresp);
    
    String section(String classcorresp);
}
