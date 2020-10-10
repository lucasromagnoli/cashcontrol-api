package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.validator.PredicatesValidator;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorSupport;

public class OriginInputValidator {
    private void originInputValidator() {};

    public static void validateSave(Origin origin) throws NoSuchFieldException {
        ValidatorSupport.fieldType(String.class)
                .target(origin)
                .field("name")
                .message("O nome precisa estrar entre 3 a 50 caracteres")
                .predicate(PredicatesValidator.stringLengthBetween(3, 50))
                .operation(ValidatorOperation.CREATE)
                .validate();
    }
}
