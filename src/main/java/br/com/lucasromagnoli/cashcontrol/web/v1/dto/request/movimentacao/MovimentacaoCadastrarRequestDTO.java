package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class MovimentacaoCadastrarRequestDTO {
    private String data;
    private String valor;
    private Long origemId;
    private Long categoriaId;

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
