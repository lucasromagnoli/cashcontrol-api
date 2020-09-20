package br.com.lucasromagnoli.cashcontrol.rest;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.exception.ValidationException;
import br.com.lucasromagnoli.cashcontrol.rest.commons.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.rest.commons.Validation;
import br.com.lucasromagnoli.javaee.useful.support.object.ObjectSupport;
import br.com.lucasromagnoli.javaee.useful.support.string.StringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private CashControlSupport cashControlSupport;

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<TemplateMessage> handleException(ValidationException exception) {
        Validation payload = exception.getValidation();
        if (!ObjectSupport.isNull(exception.getListFieldError()) && exception.getListFieldError().size() > 0) {
            Map<String, String> details = new LinkedHashMap<>();

            for (FieldError fieldError : exception.getListFieldError()) {
                details.put(StringSupport.camelToSnake(fieldError.getField()), cashControlSupport.getPropertie(fieldError.getCode()));
            }
            payload = new Validation(details, exception.getValidationType());
        }
        
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.validations.exception.default.message"))
                .messageType(MessageTypeEnum.WARNING)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .payload(payload)
                .build()
                .toResponseEntity();
    }
}
