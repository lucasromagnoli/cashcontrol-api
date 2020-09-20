package br.com.lucasromagnoli.cashcontrol.movimentation;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.exception.ValidationException;
import br.com.lucasromagnoli.cashcontrol.origin.OriginService;
import br.com.lucasromagnoli.cashcontrol.rest.commons.Validation;
import br.com.lucasromagnoli.cashcontrol.rest.commons.ValidationType;
import br.com.lucasromagnoli.javaee.useful.support.validation.ObjectNotFoundValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MovimentationBusinessValidator {
    @Autowired
    private OriginService originService;

    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Movimentation movimentation) {
        try {
            originService.findById(movimentation.getOrigin().getId());
        } catch (ObjectNotFoundValidationException e) {
            Map<String, String> details = new HashMap<>();
            details.put("origin_id", String.format(
                    cashControlSupport.getPropertie("cashcontrol.validations.generic.object.notfound"),
                    movimentation.getOrigin().getId()));
            
            throw new ValidationException(new Validation(details, ValidationType.BUSINESS));
        }
    }
}
