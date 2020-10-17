package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.exception.CashControlRuntimeException;
import br.com.lucasromagnoli.cashcontrol.validator.CheckRequired;
import br.com.lucasromagnoli.cashcontrol.validator.PredicatesValidator;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorSupport;

import java.math.BigDecimal;

public class IncomeInputValidator {
    private IncomeInputValidator() {
    }

    public static void validateSave(Income income) {
        try {
            ValidatorSupport.fieldType(String.class)
                    .target(income)
                    .operation(ValidatorOperation.CREATE)
                    .checkRequired(CheckRequired.SINGLE)
                    .field("description")
                    .predicate(PredicatesValidator.stringLengthBetween(1, 200))
                    .message("A descricão precisa estrar entre 1 a 200 caracteres")
                    .validate()
                    .field("value", BigDecimal.class)
                    .predicate(PredicatesValidator.bigDecimalFloatMinValue(0.00f))
                    .message("O valor precisa ser maior que de 0.00")
                    .validate()
                    .field("date")
                    .validate()
                    .field("origin.id")
                    .validate()
                    .field("subcategory.id")
                    .validate();

        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }
    }

    public static void validateDelete(Income income) {
        ValidatorSupport.requiredFields(income, ValidatorOperation.DELETE);
    }
}
