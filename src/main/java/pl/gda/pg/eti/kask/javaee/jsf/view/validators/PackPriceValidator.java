package pl.gda.pg.eti.kask.javaee.jsf.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class PackPriceValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof Double) {
            Double price = (Double) value;
            if (price < 0) {
                throw new ValidatorException(new FacesMessage("Niepoprawna cena"));
            }
        } else {
            throw new ValidatorException(new FacesMessage("Niepoprawny obiekt."));
        }
    }
}
