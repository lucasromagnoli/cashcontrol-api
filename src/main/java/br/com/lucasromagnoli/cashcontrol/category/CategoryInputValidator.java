package br.com.lucasromagnoli.cashcontrol.category;

import br.com.lucasromagnoli.cashcontrol.exception.CashControlRuntimeException;
import br.com.lucasromagnoli.cashcontrol.validator.CheckRequired;
import br.com.lucasromagnoli.cashcontrol.validator.PredicatesValidator;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorSupport;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class CategoryInputValidator {
    private CategoryInputValidator() {
    }

    private static ValidatorSupport<?> validateCommonsSaveAndUpdate(Category category,
                                                                    ValidatorOperation validatorOperation) {
        try {
            return ValidatorSupport.fieldType(String.class)
                    .target(category)
                    .operation(validatorOperation)
                    .checkRequired(CheckRequired.SINGLE)
                    .predicate(PredicatesValidator.stringLengthBetween(3, 50))
                    .message("O nome precisa estrar entre 3 a 50 caracteres")
                    .field("name")
                    .validate()
                    .field("description")
                    .predicate(PredicatesValidator.stringLengthBetween(1, 200))
                    .message("A descricão precisa estrar entre 1 a 200 caracteres")
                    .validate()
                    .field("type")
                    .validate();

        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }
    }

    public static void validateSave(Category category) {
        validateCommonsSaveAndUpdate(category, ValidatorOperation.CREATE);
    }

    public static void validateUpdate(Category category) {
        try {
            validateCommonsSaveAndUpdate(category, ValidatorOperation.UPDATE)
                    .field("id")
                    .validate();
        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }
    }

    public static void validateDelete(Category category) {
        ValidatorSupport.requiredFields(category, ValidatorOperation.DELETE);
    }
}
