package br.com.lucasromagnoli.cashcontrol.configuracao;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public interface Constantes {
    String DATE_FORMAT = "dd/MM/yyyy";
    String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    byte PADRAO_NOME_MAX_LENGTH = 127;
    byte PADRAO_NOME_MIN_LENGTH = 3;
    short PADRAO_DESCRICAO_MAX_LENGTH = 255;

    class Packages {
        public static final String V1_CONTROLLERS = "br.com.lucasromagnoli.cashcontrol.web.v1.controller";
    }
}