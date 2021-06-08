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
    private String campo;
    private Object valor;
    private String entidade;

    public ValidacaoException() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(String descricao) {
        super(descricao);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(Map<String, String> detalhes, String descricao) {
        super(descricao);
        this.detalhes = detalhes;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.tipoMensagem = TipoMensagem.VALIDACAO;
    }

    public ValidacaoException(Class clazz, String campo, Object valor) {
        this();
        this.campo = campo;
        this.valor = valor;
        this.entidade = clazz.getSimpleName();
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

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }
}
