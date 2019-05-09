/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Listepassword;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author KENFACK JP
 */
@Local
public interface ListepasswordFacadeLocal {

    void create(Listepassword listepassword);

    void edit(Listepassword listepassword);

    void remove(Listepassword listepassword);

    Listepassword find(Object id);

    List<Listepassword> findAll();

    List<Listepassword> findRange(int[] range);

    int count();

    String Enseignat_de_la_Matiere(String pwd, String specialite);

    String pwdExisteDeja(String pwd);

    List<Listepassword> changerMotDePasse(String pwd);

    String selfpwdExisteDeja(String pwd, String matri);
    
    String findpwd(String matri);
}
