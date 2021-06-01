package br.com.lucasromagnoli.cashcontrol.common.exception;

import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.TipoMensagem;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class ValidacaoException extends CashControlRuntimeException {
    private Map<String, String> detalhes;
    private final HttpStatus httpStatus;
    private final TipoMensagem tipoMensagem;

    public ValidacaoException() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(String descricao) {
        super(descricao);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(Map<String, String> detalhes) {
        this.detalhes = detalhes;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(Map<String, String> detalhes, String descricao) {
        super(descricao);
        this.detalhes = detalhes;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(Map<String, String> detalhes, HttpStatus httpStatus) {
        this.detalhes = detalhes;
        this.httpStatus = httpStatus;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(Map<String, String> detalhes, HttpStatus httpStatus, String descricao) {
        super(descricao);
        this.detalhes = detalhes;
        this.httpStatus = httpStatus;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public Map<String, String> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(Map<String, String> detalhes) {
        this.detalhes = detalhes;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public TipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }
}
