/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entities.Classes;
import entities.Donnespedagogiques;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author KENFACK JP
 */
@FacesConverter(value = "classeDPConverter")
public class ClasseDPConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Classes cla = new Classes(value);
        return cla;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Donnespedagogiques)) {
            return null;
        }
//        String s = String.valueOf(((Clamatcoeffgrpe) value).getCodeclasse());
        return null;
    }
}
