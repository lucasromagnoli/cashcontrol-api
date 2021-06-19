package br.com.lucasromagnoli.cashcontrol.web.v1.dto.response;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.PeriodicidadeEnum;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.categoria.CategoriaConsultarResponseDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.origem.OrigemConsultarResponseDTO;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class ParcelamentoConsultarResponseDTO {
    private Long id;
    private String valorTotal;
    private String valorParcela;
    private Byte quantidadeParcelas;
    private String dataPrimeiraParcela;
    private PeriodicidadeEnum periodicidade;
    private OrigemConsultarResponseDTO origem;
    private CategoriaConsultarResponseDTO categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Byte getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Byte quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public String getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(String dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public PeriodicidadeEnum getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(PeriodicidadeEnum periodicidade) {
        this.periodicidade = periodicidade;
    }

    public OrigemConsultarResponseDTO getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemConsultarResponseDTO origem) {
        this.origem = origem;
    }

    public CategoriaConsultarResponseDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaConsultarResponseDTO categoria) {
        this.categoria = categoria;
    }
}
