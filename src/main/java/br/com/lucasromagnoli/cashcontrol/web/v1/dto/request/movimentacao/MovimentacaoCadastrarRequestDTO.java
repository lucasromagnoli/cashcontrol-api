package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class MovimentacaoCadastrarRequestDTO {
    @NotEmpty
    private String data;
    @NotNull
    @Min(0)
    private BigDecimal valor;
    @NotNull
    private TipoMovimentacaoEnum tipoMovimentacao;
    @NotNull
    private Long origemId;
    @NotNull
    private Long categoriaId;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoMovimentacaoEnum getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacaoEnum tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Long getOrigemId() {
        return origemId;
    }

    public void setOrigemId(Long origemId) {
        this.origemId = origemId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
