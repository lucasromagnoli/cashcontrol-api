package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.GrupoService;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.grupo.GrupoAtualizarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.grupo.GrupoCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.mapper.GrupoMapper;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagem;
import io.swagger.v3.oas.annotations.Operation;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ACAO_COM_ID;
import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ROOT_GRUPO;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@RestController
@RequestMapping(ROOT_GRUPO)
public class GrupoRestController implements BaseRestController{

    @Autowired
    private GrupoService grupoService;
    private final GrupoMapper grupoMapper = Mappers.getMapper(GrupoMapper.class);
    private final Logger log = LoggerFactory.getLogger(GrupoRestController.class);

    @GetMapping
    @Operation(summary = "Listagem paginada e ordenada dos grupos")
    public ResponseEntity<ModeloMensagem> listar (Pageable pageable) {
        log.info("Listagem dos Grupos conforme a paginação: [{}]", pageable);
        Page<Grupo> grupos = grupoService.listar(pageable);
        return construirModeloMensagemSucesso(grupos.stream().map(grupoMapper::entidadeParaResponse));
    }

    @GetMapping(ACAO_COM_ID)
    @Operation(summary = "Consultar um grupo especifico")
    public ResponseEntity<ModeloMensagem> consultar(@PathVariable(required = true) final Long id) {
        log.info("Consultando o grupo de id: [{}]", id);
        return construirModeloMensagemSucesso(grupoMapper.entidadeParaResponse(grupoService.consultarPeloId(id)));
    }

    @PostMapping
    @Operation(summary = "Cadastrar um grupo")
    public ResponseEntity<ModeloMensagem> cadastrar(@Valid @RequestBody final GrupoCadastrarRequestDTO grupoCadastrarRequestDTO) {
        log.info("Cadastrando o grupo: [{}], ", grupoCadastrarRequestDTO);
        Grupo grupo = grupoService.salvar(grupoMapper.requestParaEntidade(grupoCadastrarRequestDTO));
        return construirModeloMensagemSucesso(grupoMapper.entidadeParaResponse(grupo));
    }

    @PutMapping
    @Operation(summary = "Atualizar um grupo")
    public ResponseEntity<ModeloMensagem> atualizar(@Valid @RequestBody final GrupoAtualizarRequestDTO grupoAtualizarRequestDTO) {
        log.info("Atualizando o grupo: [{}], ", grupoAtualizarRequestDTO);
        Grupo grupo = grupoService.atualizar(grupoMapper.requestParaEntidade(grupoAtualizarRequestDTO));
        return construirModeloMensagemSucesso(grupoMapper.entidadeParaResponse(grupo));
    }

    @DeleteMapping(ACAO_COM_ID)
    @Operation(summary = "Remover um grupo especifico")
    public ResponseEntity<ModeloMensagem> remover(@PathVariable(required = true) final Long id) {
        log.info("Removendo o grupo de id: [{}]", id);
        grupoService.remover(id);
        return construirModeloMensagemSucesso();
    }
}
