package br.com.lucasromagnoli.cashcontrol.movimentation;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.exception.ValidationException;
import br.com.lucasromagnoli.cashcontrol.origin.OriginService;
import br.com.lucasromagnoli.cashcontrol.rest.commons.Validation;
import br.com.lucasromagnoli.cashcontrol.rest.commons.ValidationType;
import br.com.lucasromagnoli.cashcontrol.subcategory.SubcategoryService;
import br.com.lucasromagnoli.javaee.useful.support.object.ObjectSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MovimentationBusinessValidator {
    @Autowired
    private OriginService originService;

    @Autowired
    private SubcategoryService subcategoryService;
    
    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Movimentation movimentation) {
        Map<String, String> details = new HashMap<>();

        if (!originService.existsById(movimentation.getOrigin().getId())) {
            details.put("origin_id", String.format(
                    cashControlSupport.getPropertie("cashcontrol.validations.generic.object.notfound"),
                    movimentation.getOrigin().getId()));

        }
        
        if (!subcategoryService.existsByIdAndMovimentationType(
                movimentation.getSubcategory().getId(), movimentation.getType())) {
            details.put("subcategory_id", String.format(
                    cashControlSupport.getPropertie("cashcontrol.validations.generic.object.notfound"),
                    movimentation.getSubcategory().getId()));
        }
        
        if (details.size() >= 1) {
            throw new ValidationException(new Validation(details, ValidationType.BUSINESS));
        }
    }
}
