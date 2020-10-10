package br.com.lucasromagnoli.cashcontrol.exception;

import br.com.lucasromagnoli.cashcontrol.rest.commons.Validation;
import br.com.lucasromagnoli.cashcontrol.rest.commons.ValidationType;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final Validation validation;
    private final List<FieldError> listFieldError;
    private final ValidationType validationType;

    public ValidationException(String message) {
        super(message);
        this.validationType = null;
        this.listFieldError = null;
        this.validation = null;
    }
    public ValidationException(Validation validation) {
        this.validation = validation;
        this.validationType = null;
        this.listFieldError = null;
    }
    
    public ValidationException(List<FieldError> listFieldError, ValidationType validationType) {
        this.listFieldError = listFieldError;
        this.validationType = validationType;
        this.validation = null;
    }
}
