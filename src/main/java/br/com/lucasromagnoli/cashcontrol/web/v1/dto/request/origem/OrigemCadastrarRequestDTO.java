package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.origem;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_LENGTH;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class OrigemCadastrarRequestDTO {
    private String nome;

    @NotEmpty
    @Size(max = PADRAO_NOME_LENGTH)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
