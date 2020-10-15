package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.exception.CashControlRuntimeException;
import br.com.lucasromagnoli.cashcontrol.validator.CheckRequired;
import br.com.lucasromagnoli.cashcontrol.validator.PredicatesValidator;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorSupport;

public class SubcategoryInputValidator {
    private SubcategoryInputValidator() {}

    private static ValidatorSupport<?> validateCommonsSaveAndUpdate(Subcategory subcategory,
                                                                    ValidatorOperation validatorOperation) {
        try {
            return ValidatorSupport.fieldType(String.class)
                    .target(subcategory)
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
                    .field("category.id")
                    .validate();

        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }
    }

    public static void validateSave(Subcategory subcategory) {
        validateCommonsSaveAndUpdate(subcategory, ValidatorOperation.CREATE);
    }

    public static void validateUpdate(Subcategory subcategory) {
        try {
            validateCommonsSaveAndUpdate(subcategory, ValidatorOperation.UPDATE)
                    .field("id")
                    .validate();
        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }
    }

    public static void validateDelete(Subcategory subcategory) {
        ValidatorSupport.requiredFields(subcategory, ValidatorOperation.DELETE);
    }
}
