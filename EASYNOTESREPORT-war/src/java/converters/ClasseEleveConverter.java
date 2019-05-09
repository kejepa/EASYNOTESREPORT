/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entities.Classes;
import entities.Listedeseleves;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author KENFACK JP
 */
@FacesConverter(value = "classeEleveConverter")
public class ClasseEleveConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Classes cla = new Classes(value);
        return cla;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Listedeseleves)) {
            return null;
        }
        String s = String.valueOf(((Listedeseleves) value).getCodeclasse());
        return s;
    }
}
