package br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public interface ControllerMapping {
    // ROOT
    String ROOT_ORIGEM = "/v1/origem";
    String ROOT_RECEITA = "/v1/receita";

    // AÇÕES GENÉRICAS
    String ACAO_COM_ID = "/{id}";
}
