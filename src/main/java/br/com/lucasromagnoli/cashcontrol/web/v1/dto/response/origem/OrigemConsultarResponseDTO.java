package br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.origem;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class OrigemConsultarResponseDTO {
    private Long id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
