package Utils;

import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@ApplicationScoped
@Getter
@Setter
@FacesValidator("yValidator")
public class YValueValidator implements Validator {
    private double fromVal = -3;
    private double toVal = 5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        double y = (double) o;
        if (y <= fromVal || y >= toVal){
            FacesMessage msg = new FacesMessage(String.format("Y должен быть от %d до %d", (int)fromVal, (int)toVal));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
