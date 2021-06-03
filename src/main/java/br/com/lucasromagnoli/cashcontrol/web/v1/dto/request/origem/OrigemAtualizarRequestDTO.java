package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.origem;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_LENGTH;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class OrigemAtualizarRequestDTO {
    @NotNull
    @Min(1)
    private Long id;

    @NotEmpty
    @Size(max = PADRAO_NOME_LENGTH)
    private String nome;

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
}
