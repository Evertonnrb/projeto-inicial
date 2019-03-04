package br.com.converter;

import br.com.persistence.model.AbstractEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Map;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || !value.matches("\\d+")){
            return null;
        }
        return this.getAtributesFrom(component).get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null && !value.equals("")){
            AbstractEntity entity = (AbstractEntity) value;
            if(entity.getId()!=null){
                this.addAtributes(component,entity);
                return entity.getId().toString();
            }
            return value.toString();
        }
        return "";
    }

    private void addAtributes(UIComponent uiComponent, AbstractEntity abstractEntity){
        this.getAtributesFrom(uiComponent).put(abstractEntity.getId().toString(),abstractEntity);
    }

    private Map<String,Object> getAtributesFrom(UIComponent uiComponent){
        return uiComponent.getAttributes();
    }
}
