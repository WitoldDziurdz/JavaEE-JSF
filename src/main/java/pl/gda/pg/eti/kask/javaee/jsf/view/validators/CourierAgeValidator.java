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
public class CourierAgeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof Integer) {
            Integer age = (Integer) value;
            if (age < 16) {
                throw new ValidatorException(new FacesMessage("Niepoprawny wiek"));
            }
        } else {
            throw new ValidatorException(new FacesMessage("Niepoprawny obiekt."));
        }
    }
}