package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class MovimentacaoAtualizarRequestDTO {
    @NotNull
    @Min(1)
    private Long id;

    private String data;
    @NotNull
    @Min(0)
    private BigDecimal valor;
    private Long origemId;
    private Long categoriaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
