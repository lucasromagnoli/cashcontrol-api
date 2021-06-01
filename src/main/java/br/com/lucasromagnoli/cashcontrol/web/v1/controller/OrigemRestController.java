package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.origem.OrigemCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.mapper.OrigemMapper;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ROOT_ORIGEM;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@RestController
@RequestMapping(ROOT_ORIGEM)
@Tag(name = "Origem", description = "Operações para gerênciar as origens")
public class OrigemRestController implements BaseRestController {
    @Autowired
    private OrigemService origemService;

    private final OrigemMapper origemMapper = Mappers.getMapper(OrigemMapper.class);

    @Operation(summary = "Cadastrar uma origem")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModeloMensagem> cadastrar(@Valid @RequestBody OrigemCadastrarRequestDTO origemCadastrarRequestDTO) {
        Origem origem = origemService.salvar(origemMapper.requestParaEntidade(origemCadastrarRequestDTO));
        return construirModeloMensagemSucesso(origemMapper.entidadeParaResponse(origem));
    }
}
