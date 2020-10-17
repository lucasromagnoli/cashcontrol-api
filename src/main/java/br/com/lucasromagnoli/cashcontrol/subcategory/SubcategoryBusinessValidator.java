package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.category.CategoryService;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryBusinessValidator {
    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Subcategory subcategory) {

        if (!categoryService.existsWithId(subcategory.getCategory().getId())) {
            throw new BusinessValidationException("category_id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed",
                            subcategory.getCategory().getId()));
        }

        if (subcategoryService.existsWithNameAndCategoryId(subcategory.getName(),
                subcategory.getCategory().getId())) {
            throw new BusinessValidationException("name",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.already.exists.detailed", subcategory.getName()));
        }
    }

    public void validateUpdate(Subcategory subcategory) {
        validateExists(subcategory);
        validateSave(subcategory);
    }

    public void validateDelete(Subcategory subcategory) {
        validateExists(subcategory);
        // TODO: 14/12/20 - Validar se existe alguma despesa/receita cadastrada com essa subcategoria.
    }

    private void validateExists(Subcategory subcategory) {
        if (!subcategoryService.existsWithId(subcategory.getId())) {
            throw new BusinessValidationException("id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed",
                            subcategory.getId()));
        }
    }
}
