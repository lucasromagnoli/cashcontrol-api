package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.origin.OriginService;
import br.com.lucasromagnoli.cashcontrol.subcategory.SubcategoryService;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncomeBusinessValidator {
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private OriginService originService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Income income) {
        if (!originService.existsWithId(income.getOrigin().getId())) {
            throw new BusinessValidationException("origin_id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed",
                            income.getOrigin().getId()));
        }

        Integer subcategoryId = income.getSubcategory().getId();
        if (!subcategoryService.existsWithId(subcategoryId)) {
            throw new BusinessValidationException("subcategory_id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed", subcategoryId));
        }

        if (!TransactionTypeEnum.INCOME.equals(subcategoryService.findTransactionType(subcategoryId))) {
            throw new BusinessValidationException("subcategory_id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.transaction.type.invalid.detailed", "receita"));
        }

    }

    public void validateDelete(Income income) {
        if (!incomeService.existsWithId(income.getId())) {
            throw new BusinessValidationException("id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed",
                            income.getId()));
        }
    }

}
