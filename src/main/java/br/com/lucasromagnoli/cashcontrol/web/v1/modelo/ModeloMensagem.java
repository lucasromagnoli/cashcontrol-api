package br.com.lucasromagnoli.cashcontrol.web.v1.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class ModeloMensagem {
    private String descricao;
    private Object payload;
    private TipoMensagem tipoMensagem;
    private String tipoPayload;
    private LocalDateTime timestamp;

    public ModeloMensagem(final String descricao, final Object payload, final TipoMensagem tipoMensagem) {
        this.descricao = descricao;
        this.payload = payload;
        this.tipoMensagem = tipoMensagem;
        this.timestamp = LocalDateTime.now();
        if (!Objects.isNull(payload)) {
            this.tipoPayload = payload.getClass().getSimpleName();
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public TipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagem tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public String getTipoPayload() {
        return tipoPayload;
    }

    public void setTipoPayload(String tipoPayload) {
        this.tipoPayload = tipoPayload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
