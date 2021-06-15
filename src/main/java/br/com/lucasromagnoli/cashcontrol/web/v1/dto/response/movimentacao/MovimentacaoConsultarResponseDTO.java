package br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.movimentacao;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.categoria.CategoriaConsultarResponseDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.origem.OrigemConsultarResponseDTO;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class MovimentacaoConsultarResponseDTO {
    private Long id;
    private String data;
    private String valor;
    private OrigemConsultarResponseDTO origem;
    private TipoMovimentacaoEnum tipoMovimentacao;
    private CategoriaConsultarResponseDTO categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimentacaoEnum getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacaoEnum tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
