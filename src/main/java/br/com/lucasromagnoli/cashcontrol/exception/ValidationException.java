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
    
    public ValidationException(Validation validation, ValidationType validationType) {
        this.validationType = validationType;
        this.validation = validation;
        this.listFieldError = null;
    }
    
    public ValidationException(List<FieldError> listFieldError, ValidationType validationType) {
        this.listFieldError = listFieldError;
        this.validationType = validationType;
        this.validation = null;
    }
}
