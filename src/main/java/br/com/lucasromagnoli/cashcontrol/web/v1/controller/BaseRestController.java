package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagem;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagemBuilder;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.TipoMensagem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public interface BaseRestController {
    default ResponseEntity<ModeloMensagem> construirModeloMensagemSucesso() {
        return ModeloMensagemBuilder.tipo(TipoMensagem.SUCESSO)
                .httpStatus(HttpStatus.OK)
                .concluir();
    }

    default ResponseEntity<ModeloMensagem> construirModeloMensagemSucesso(Object payload) {
        return ModeloMensagemBuilder.tipo(TipoMensagem.SUCESSO)
                .httpStatus(HttpStatus.OK)
                .payload(payload)
                .concluir();
    }

    default ResponseEntity<ModeloMensagem> construirModeloMensagemSucesso(String descricao) {
        return ModeloMensagemBuilder.tipo(TipoMensagem.SUCESSO)
                .httpStatus(HttpStatus.OK)
                .descricao(descricao)
                .concluir();
    }

    default ResponseEntity<ModeloMensagem> construirModeloMensagemSucesso(String descricao, Object payload) {
        return ModeloMensagemBuilder.tipo(TipoMensagem.SUCESSO)
                .httpStatus(HttpStatus.OK)
                .descricao(descricao)
                .payload(payload)
                .concluir();
    }
}
