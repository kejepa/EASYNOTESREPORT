/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entities.Matieres;
import entities.Specialite;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author KENFACK JP
 */
@FacesConverter(value = "specialiteConverter")
public class SpecialiteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Specialite sp = new Specialite(value);
        return sp;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Matieres)) {
            return null;
        }
//        String s = String.valueOf(((Clamatcoeffgrpe) value).getCodematiere());
        return null;
    }
}
