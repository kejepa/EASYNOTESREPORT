/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Calculnotesdesclasses;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface CalculnotesdesclassesFacadeLocal {

    void create(Calculnotesdesclasses calculnotesdesclasses);

    void edit(Calculnotesdesclasses calculnotesdesclasses);

    void remove(Calculnotesdesclasses calculnotesdesclasses);

    Calculnotesdesclasses find(Object id);

    List<Calculnotesdesclasses> findAll();

    List<Calculnotesdesclasses> findRange(int[] range);

    int count();

    BigDecimal sommeCoef(String codeClasse);

    BigDecimal moyenne_de_la_classe(String codeClasse);

    BigDecimal moyenne_premier_de_la_classe(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classe(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classe(String codeClasse);

    boolean existe_classe_et_sequence(String codeclasse, String sequence);

    //2é sequence
    BigDecimal moyenne_de_la_classeSeq2(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeSeq2(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeSeq2(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeSeq2(String codeClasse);
    //trosième séquence

    BigDecimal moyenne_de_la_classeSeq3(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeSeq3(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeSeq3(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeSeq3(String codeClasse);

    BigDecimal moyenne_de_la_classeSeq4(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeSeq4(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeSeq4(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeSeq4(String codeClasse);

    BigDecimal moyenne_de_la_classeSeq5(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeSeq5(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeSeq5(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeSeq5(String codeClasse);

    BigDecimal moyenne_de_la_classeSeq6(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeSeq6(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeSeq6(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeSeq6(String codeClasse);

    BigDecimal moyenne_de_la_classeTrim1(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeTrim1(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeTrim1(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeTrim1(String codeClasse);

    BigDecimal moyenne_de_la_classeTrim2(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeTrim2(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeTrim2(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeTrim2(String codeClasse);

    BigDecimal moyenne_de_la_classeTrim3(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeTrim3(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeTrim3(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeTrim3(String codeClasse);

    BigDecimal moyenne_de_la_classeAnn(String codeClasse);

    BigDecimal moyenne_premier_de_la_classeAnn(String codeClasse);

    BigDecimal moyenne_dernier_de_la_classeAnn(String codeClasse);

    BigDecimal taux_de_reussite_de_la_classeAnn(String codeClasse, BigDecimal moySupA);
}
