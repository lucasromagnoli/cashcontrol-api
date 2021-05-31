package br.com.lucasromagnoli.cashcontrol.web.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import br.com.lucasromagnoli.cashcontrol.web.dto.request.OrigemRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.modelo.ModeloMensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.lucasromagnoli.cashcontrol.web.controller.configuracao.ControllerMapping.ROOT_ORIGEM;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@RestController
@RequestMapping(ROOT_ORIGEM)
public class OrigemRestController implements BaseRestController {
    @Autowired
    private OrigemService origemService;

    @PostMapping
    public ResponseEntity<ModeloMensagem> cadastrar(@RequestBody OrigemRequestDTO origemRequestDTO) {
        Origem origem = new Origem();
        origem.setNome(origemRequestDTO.getNome());
        origemService.salvar(origem);
        return construirModeloMensagemSucesso(origem);
    }
}
