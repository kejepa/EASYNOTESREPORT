/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entities.Ensgclamat;
import entities.Matieres;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author KENFACK JP
 */
@FacesConverter(value = "matiereAffectEnseigConverter")
public class MatiereAffectEnseigClaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Matieres cla = new Matieres(value);
        return cla;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Ensgclamat)) {
            return null;
        }
//        String s = String.valueOf(((Ensgclamat) value).getCodematiere());
        return null;
    }
}
