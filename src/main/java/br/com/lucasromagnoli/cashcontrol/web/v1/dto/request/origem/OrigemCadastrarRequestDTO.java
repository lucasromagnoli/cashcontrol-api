package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.origem;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_MAX_LENGTH;
import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_MIN_LENGTH;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class OrigemCadastrarRequestDTO {
    @NotEmpty
    @Size(min = PADRAO_NOME_MIN_LENGTH, max = PADRAO_NOME_MAX_LENGTH)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
