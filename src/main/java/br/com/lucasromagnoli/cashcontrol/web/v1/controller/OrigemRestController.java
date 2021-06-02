package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.origem.OrigemCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.mapper.OrigemMapper;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ACAO_COM_ID;
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

    private final Logger log = LoggerFactory.getLogger(OrigemRestController.class);
    private final OrigemMapper origemMapper = Mappers.getMapper(OrigemMapper.class);

    @Operation(summary = "Listagem paginada e ordenada das origens")
    @GetMapping
    public ResponseEntity<ModeloMensagem> listar(@PageableDefault Pageable pageable) {
        log.info("Listagem das Origens conforme a paginação: [{}]", pageable);
        Page<Origem> origens = origemService.listar(pageable);
        return construirModeloMensagemSucesso(origens.stream().map(origemMapper::entidadeParaResponse));
    }

    @Operation(summary = "Consultar uma origem especifica")
    @GetMapping(ACAO_COM_ID)
    public ResponseEntity<ModeloMensagem> consultar(@PathVariable(required = true) final Long id) {
        log.info("Consultando a Origem de id: [{}]", id);
        return construirModeloMensagemSucesso(origemMapper.entidadeParaResponse(origemService.consultarPeloId(id)));
    }

    @Operation(summary = "Cadastrar uma origem")
    @PostMapping
    public ResponseEntity<ModeloMensagem> cadastrar(@Valid @RequestBody final OrigemCadastrarRequestDTO origemCadastrarRequestDTO) {
        Origem origem = origemService.salvar(origemMapper.requestParaEntidade(origemCadastrarRequestDTO));
        return construirModeloMensagemSucesso(origemMapper.entidadeParaResponse(origem));
    }

    @Operation(summary = "Remover uma origem especifica")
    @DeleteMapping(ACAO_COM_ID)
    public ResponseEntity<ModeloMensagem> remover(@PathVariable(required = true) final Long id) {
        log.info("Removendo a Origem de id: [{}]", id);
        origemService.remover(id);
        return construirModeloMensagemSucesso();
    }
}
