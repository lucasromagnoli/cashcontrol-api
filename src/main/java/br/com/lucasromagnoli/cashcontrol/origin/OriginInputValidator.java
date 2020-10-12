package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.exception.CashControlRuntimeException;
import br.com.lucasromagnoli.cashcontrol.validator.PredicatesValidator;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorSupport;

public class OriginInputValidator {
    private void originInputValidator() {
    }

    private static void validateCommons(Origin origin, ValidatorOperation validatorOperation) {
        try {
            ValidatorSupport.fieldType(String.class)
                    .target(origin)
                    .field("name")
                    .message("O nome precisa estrar entre 3 a 50 caracteres")
                    .predicate(PredicatesValidator.stringLengthBetween(3, 50))
                    .operation(validatorOperation)
                    .validate();
        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }
    }

    public static void validateSave(Origin origin) {
        validateCommons(origin, ValidatorOperation.CREATE);
    }

    public static void validateUpdate(Origin origin) {
        validateCommons(origin, ValidatorOperation.UPDATE);
    }

    public static void validateDelete(Origin origin) {
        ValidatorSupport.requiredFields(origin, ValidatorOperation.DELETE);
    }
}
