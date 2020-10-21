package br.com.lucasromagnoli.cashcontrol.api;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.exception.CashControlRuntimeException;
import br.com.lucasromagnoli.cashcontrol.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private CashControlSupport cashControlSupport;

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<TemplateMessage> handleValidationException(ValidationException e) {
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.validations.exception.default.message"))
                .messageType(MessageTypeEnum.WARNING)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .payload(e.getValidationMessage())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(value = CashControlRuntimeException.class)
    protected ResponseEntity<TemplateMessage> handleRuntimeException(CashControlRuntimeException e) {
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.handler.exception.runtime"))
                .messageType(MessageTypeEnum.ERROR)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build()
                .toResponseEntity();
    }
}
