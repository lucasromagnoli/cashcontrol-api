package br.com.lucasromagnoli.cashcontrol.category;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryBusinessValidator {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Category category) {
        if (categoryService.existsWithName(category.getName())) {
            throw new BusinessValidationException("name",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.already.exists.detailed", category.getName()));
        }
    }

    public void validateUpdate(Category category) {
        validateExists(category);
        validateSave(category);
    }

    public void validateDelete(Category category) {
        validateExists(category);
        // TODO: 10/12/20 - Validar se existe alguma subcategoria cadastrada com essa categoria.
    }

    private void validateExists(Category category) {
        if (!categoryService.existsWithId(category.getId())) {
            throw new BusinessValidationException("id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed",
                            category.getId()));
        }
    }
}
