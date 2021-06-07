package br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.grupo;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class GrupoConsultarResponseDTO {
    private Long id;
    private String nome;
    private TipoMovimentacaoEnum tipoMovimentacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
