package pl.gda.pg.eti.kask.javaee.jsf.view.validators;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@ManagedBean
@RequestScoped
public class CourierPhoneValidator implements Validator {
    private static String PATTERN = "^\\d{9}$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value instanceof String) {
            String title = (String) value;
            if (!Pattern.matches(PATTERN, title)) {
                throw new ValidatorException(new FacesMessage("Niepoprawny numer telefonu."));
            }
        } else {
            throw new ValidatorException(new FacesMessage("Niepoprawny obiekt."));
        }
    }
}
