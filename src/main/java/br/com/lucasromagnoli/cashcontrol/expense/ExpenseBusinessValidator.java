package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.origin.OriginBusinessValidator;
import br.com.lucasromagnoli.cashcontrol.subcategory.SubcategoryBusinessValidator;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import br.com.lucasromagnoli.cashcontrol.validator.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseBusinessValidator {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private OriginBusinessValidator originBusinessValidator;

    @Autowired
    private SubcategoryBusinessValidator subcategoryBusinessValidator;

    @Autowired
    private CashControlSupport cashControlSupport;

    public void validateSave(Expense expense) {
        originBusinessValidator.validateExists(expense.getOrigin());
        subcategoryBusinessValidator.validateExists(expense.getSubcategory());
        subcategoryBusinessValidator.validateTransactionType(expense.getSubcategory(), TransactionTypeEnum.EXPENSE);
    }

    public void validateDelete(Expense expense) {
        if (!expenseService.existsWithId(expense.getId())) {
            throw new BusinessValidationException("id",
                    cashControlSupport.getPropertie(
                            "cashcontrol.validation.business.not.found.object.with.id.detailed",
                            expense.getId()));
        }
    }
}
