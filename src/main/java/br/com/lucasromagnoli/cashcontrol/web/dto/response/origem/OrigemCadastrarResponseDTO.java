package br.com.lucasromagnoli.cashcontrol.web.dto.response.origem;

import br.com.lucasromagnoli.cashcontrol.web.dto.BaseDTO;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class OrigemCadastrarResponseDTO extends BaseDTO {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
