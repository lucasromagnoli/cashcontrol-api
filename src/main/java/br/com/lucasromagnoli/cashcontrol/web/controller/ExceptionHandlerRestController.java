package br.com.lucasromagnoli.cashcontrol.web.controller;

import br.com.lucasromagnoli.cashcontrol.common.exception.ValidacaoException;
import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.web.modelo.ModeloMensagem;
import br.com.lucasromagnoli.cashcontrol.web.modelo.ModeloMensagemBuilder;
import br.com.lucasromagnoli.cashcontrol.web.modelo.TipoMensagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_VERIFIQUE_CAMPOS;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@RestControllerAdvice
public class ExceptionHandlerRestController implements BaseRestController {
    @Autowired
    private Mensagem mensagem;

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ModeloMensagem> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
        return handleValidacaoException(new ValidacaoException(detalhes, mensagem.get(MENSAGEM_VERIFIQUE_CAMPOS)));
    }

    @ExceptionHandler(value = ValidacaoException.class)
    protected ResponseEntity<ModeloMensagem> handleValidacaoException(ValidacaoException ex) {
        log.error("Tratando exception: handleValidacaoException ->", ex);
        return construirResponse(ex.getTipoMensagem(), ex.getDetalhes(), ex.getHttpStatus(), ex.getDescricao());
    }

    private ResponseEntity<ModeloMensagem> construirResponse(TipoMensagem tipoMensagem, Object payload, HttpStatus httpStatus, String descricao) {
        return ModeloMensagemBuilder.tipo(tipoMensagem)
                .descricao(descricao)
                .httpStatus(httpStatus)
                .payload(payload)
                .concluir();
    }
}
