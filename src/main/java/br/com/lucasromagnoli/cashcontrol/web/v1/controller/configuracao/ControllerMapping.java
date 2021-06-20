package br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public interface ControllerMapping {
    // ROOT
    String ROOT_ORIGEM = "/v1/origem";
    String ROOT_GRUPO = "/v1/grupo";
    String ROOT_CATEGORIA = "/v1/categoria";
    String ROOT_MOVIMENTACAO = "/v1/movimentacao";
    String ROOT_DESPESA = "/v1/despesa";

    String DESPESA_PARCELAMENTO = "parcelamento";

    // AÇÕES GENÉRICAS
    String ACAO_COM_ID = "/{id}";
}
