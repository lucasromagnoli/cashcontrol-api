package br.com.lucasromagnoli.cashcontrol.web.controller;

import br.com.lucasromagnoli.cashcontrol.web.modelo.ModeloMensagem;
import br.com.lucasromagnoli.cashcontrol.web.modelo.ModeloMensagemBuilder;
import br.com.lucasromagnoli.cashcontrol.web.modelo.TipoMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@RestControllerAdvice
public class ExceptionHandlerRestController {
    private final static Logger log = LoggerFactory.getLogger(ExceptionHandlerRestController.class);

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<ModeloMensagem> handleRuntimeException(RuntimeException exception) {
        log.error("Tratando exception: handleRuntimeException ->", exception);
        return ModeloMensagemBuilder.tipo(TipoMensagem.ERRO)
                .descricao(exception.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .payload(exception)
                .concluir();
    }

}
