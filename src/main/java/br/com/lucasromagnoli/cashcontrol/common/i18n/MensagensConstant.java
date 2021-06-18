package br.com.lucasromagnoli.cashcontrol.common.i18n;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public interface MensagensConstant {

    class Validacao {
        public static final String MENSAGEM_VERIFIQUE_CAMPOS = "cashcontrol.mensagens.validacao.descricao.padrao";
        public static final String MENSAGEM_REGISTRO_NAO_ENCONTRADO = "cashcontrol.mensagens.validacao.entidade.nao.encontrada";
        public static final String MENSAGEM_REGISTRO_DUPLICADO = "cashcontrol.mensagens.validacao.entidade.duplicada";
        public static final String MENSAGEM_REGISTRO_RELACIONAMENTO_ATIVO = "cashcontrol.mensagens.validacao.entidade.com.relacionamento";

        public static class Movimentacao {
            public static final String MENSAGEM_MOVIMENTACAO_TIPO_DIFERENTE = "cashcontrol.mensagens.validacao.movimentacao.tipo.categoria.diferente";
        }
    }

    class Geral {
        public static final String MENSAGEM_TESTE = "cashcontrol.mensagens.testando.i18n";
        public static final String MENSAGEM_TESTE_COM_PARAMETROS = "cashcontrol.mensagens.testando.i18n.com.variaveis";
    }

    class Documentacao {
        public static final String DESCRICAO = "cashcontrol.mensagens.validacao.documentacao.descricao";
        public static final String LICENSA_NOME = "cashcontrol.mensagens.validacao.documentacao.licensa.nome";
        public static final String LICENSA_URL = "cashcontrol.mensagens.validacao.documentacao.licensa.url";
        public static final String TITULO = "cashcontrol.mensagens.validacao.documentacao.titulo";
        public static final String VERSAO = "cashcontrol.mensagens.validacao.documentacao.versao";
    }
}
