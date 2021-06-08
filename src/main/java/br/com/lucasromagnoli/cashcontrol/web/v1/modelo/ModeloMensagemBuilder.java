package br.com.lucasromagnoli.cashcontrol.web.v1.modelo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class ModeloMensagemBuilder {
    private String descricao;
    private Object payload;
    private final TipoMensagem tipoMensagem;
    private HttpStatus httpStatus;

    private ModeloMensagemBuilder(final TipoMensagem tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public static ModeloMensagemBuilder tipo(final TipoMensagem tipoMensagem) {
        return new ModeloMensagemBuilder(tipoMensagem);
    }

    public ModeloMensagemBuilder descricao(final String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ModeloMensagemBuilder payload(final Object payload) {
        this.payload = payload;
        return this;
    }

    public ModeloMensagemBuilder httpStatus(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ResponseEntity<ModeloMensagem> concluir() {
        final var modeloMensagem = new ModeloMensagem(this.descricao, this.payload, this.tipoMensagem);
        return ResponseEntity.status(Objects.isNull(this.httpStatus) ? HttpStatus.OK : this.httpStatus).body(modeloMensagem);
    }
}
