package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OriginBusinessValidator {

    @Autowired
    private OriginService originService;

    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Origin origin) {
        if (originService.existsWithName(origin.getName())) {
            throw new BusinessValidationException("name",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.already.exists.detailed", origin.getName()));
        }
    }
}
