package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
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

    public void validateUpdate(Origin origin) {
        validateExists(origin);
        validateSave(origin);
    }

    public void validateDelete(Origin origin) {
        validateExists(origin);
        // TODO: 10/12/20 - Validar se existe alguma receita/despesa cadastrada com essa origem.
    }

    public void validateExists(Origin origin) {
        if (!originService.existsWithId(origin.getId())) {
            throw new BusinessValidationException("id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed",
                            origin.getId()));
        }
    }
}
