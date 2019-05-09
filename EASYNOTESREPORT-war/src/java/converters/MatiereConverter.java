/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entities.Clamatcoeffgrpe;
import entities.Matieres;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author KENFACK JP
 */
@FacesConverter(value = "matiereConverter")
public class MatiereConverter implements Converter {
     @Override
     public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Matieres mat = new Matieres(value);
        return mat;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Clamatcoeffgrpe)) {
            return null;
        }
//        String s = String.valueOf(((Clamatcoeffgrpe) value).getCodematiere());
        return null;
    }
}
