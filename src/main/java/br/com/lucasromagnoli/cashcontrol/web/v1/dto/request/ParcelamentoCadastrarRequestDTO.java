package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.PeriodicidadeEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class ParcelamentoCadastrarRequestDTO {
    @NotEmpty
    private String dataPrimeiraParcela;
    @NotNull
    @Min(0)
    private BigDecimal valorParcela;
    @NotNull
    @Min(1)
    private Byte quantidadeParcelas;
    @NotNull
    private PeriodicidadeEnum periodicidade;
    @NotNull
    private Long origemId;
    @NotNull
    private Long categoriaId;

    public String getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(String dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Byte getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Byte quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public PeriodicidadeEnum getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(PeriodicidadeEnum periodicidade) {
        this.periodicidade = periodicidade;
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
