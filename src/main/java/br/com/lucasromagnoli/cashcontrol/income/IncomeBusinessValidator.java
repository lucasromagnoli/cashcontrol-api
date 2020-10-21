package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.origin.OriginBusinessValidator;
import br.com.lucasromagnoli.cashcontrol.subcategory.SubcategoryBusinessValidator;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Component
public class IncomeBusinessValidator {
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private OriginBusinessValidator originBusinessValidator;

    @Autowired
    private SubcategoryBusinessValidator subcategoryBusinessValidator;

    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Income income) {
        originBusinessValidator.validateExists(income.getOrigin());
        subcategoryBusinessValidator.validateExists(income.getSubcategory());
        subcategoryBusinessValidator.validateTransactionType(income.getSubcategory(), TransactionTypeEnum.INCOME);
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
