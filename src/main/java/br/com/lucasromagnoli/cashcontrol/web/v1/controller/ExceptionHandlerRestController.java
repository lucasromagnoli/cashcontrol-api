package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroDuplicado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroRelacionamentoAtivo;
import br.com.lucasromagnoli.cashcontrol.common.exception.ValidacaoException;
import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagem;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagemBuilder;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.TipoMensagem;
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

import java.util.Map;
import java.util.stream.Collectors;

import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_REGISTRO_DUPLICADO;
import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_REGISTRO_NAO_ENCONTRADO;
import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_REGISTRO_RELACIONAMENTO_ATIVO;
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
    protected ResponseEntity<ModeloMensagem> handleRuntimeException(RuntimeException ex) {
        log.info("Tratando exception: handleRuntimeException -> {}", ex.getMessage());
        return construirResponse(TipoMensagem.ERRO, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ModeloMensagem> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
        return handleValidacaoException(new ValidacaoException(detalhes, mensagem.get(MENSAGEM_VERIFIQUE_CAMPOS)));
    }

    @ExceptionHandler(value = RegistroNaoEncontrado.class)
    protected ResponseEntity<ModeloMensagem> handleRegistroNaoEncontrado(RegistroNaoEncontrado ex) {
        ex.setDescricao(mensagem.get(MENSAGEM_REGISTRO_NAO_ENCONTRADO, ex.getEntidade(), ex.getCampo(), ex.getValor()));
        return handleValidacaoException(ex);
    }

    @ExceptionHandler(value = RegistroDuplicado.class)
    protected ResponseEntity<ModeloMensagem> handleRegistroDuplicado(RegistroDuplicado ex) {
        ex.setDescricao(mensagem.get(MENSAGEM_VERIFIQUE_CAMPOS));
        ex.setDetalhes(Map.of(ex.getCampo(), mensagem.get(MENSAGEM_REGISTRO_DUPLICADO, ex.getValor())));
        return handleValidacaoException(ex);
    }

    @ExceptionHandler(value = RegistroRelacionamentoAtivo.class)
    protected ResponseEntity<ModeloMensagem> handleRegistroRelacionamentoAtivo(RegistroRelacionamentoAtivo ex) {
        ex.setDescricao(mensagem.get(MENSAGEM_REGISTRO_RELACIONAMENTO_ATIVO, ex.getEntidade(), ex.getValor()));
        return handleValidacaoException(ex);
    }

    @ExceptionHandler(value = ValidacaoException.class)
    protected ResponseEntity<ModeloMensagem> handleValidacaoException(ValidacaoException ex) {
        log.info("Tratando exception: handleValidacaoException -> {}", ex.getDescricao());
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
