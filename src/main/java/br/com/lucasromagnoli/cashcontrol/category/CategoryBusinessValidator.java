package br.com.lucasromagnoli.cashcontrol.category;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.subcategory.SubcategoryService;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Component
public class CategoryBusinessValidator {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

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
        if (subcategoryService.existsWithCategoryId(category.getId())) {
            throw new BusinessValidationException("subcategory",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.delete.childreen.exists.detailed",
                            "subcategorias"));
        }
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
