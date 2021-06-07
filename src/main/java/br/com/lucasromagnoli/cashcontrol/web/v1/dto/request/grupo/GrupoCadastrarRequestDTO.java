package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.grupo;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_MAX_LENGTH;
import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_MIN_LENGTH;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class GrupoCadastrarRequestDTO {
    @NotEmpty
    @Size(min = PADRAO_NOME_MIN_LENGTH, max = PADRAO_NOME_MAX_LENGTH)
    private String nome;

    @NotNull
    private TipoMovimentacaoEnum tipoMovimentacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoMovimentacaoEnum getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacaoEnum tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}
