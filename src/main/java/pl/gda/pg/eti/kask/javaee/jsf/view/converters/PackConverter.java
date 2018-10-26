package pl.gda.pg.eti.kask.javaee.jsf.view.converters;


import pl.gda.pg.eti.kask.javaee.jsf.CourierService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Pack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class PackConverter implements Converter {

    @ManagedProperty("#{courierService}")
    private CourierService courierService;

    public void setCourierService( CourierService courierService) {
        this.courierService = courierService;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("---".equals(value)) {
            return null;
        }
        return courierService.findPack(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "---";
        }
        return ((Pack) value).getId() + "";
    }
}
