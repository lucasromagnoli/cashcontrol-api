package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.CategoriaService;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.categoria.CategoriaAtualizarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.categoria.CategoriaCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.mapper.CategoriaMapper;
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
import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ROOT_CATEGORIA;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@RestController
@RequestMapping(ROOT_CATEGORIA)
public class CategoriaRestController implements BaseRestController{
    @Autowired
    private CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper = Mappers.getMapper(CategoriaMapper.class);
    private final Logger log = LoggerFactory.getLogger(CategoriaRestController.class);

    @GetMapping
    @Operation(summary = "Listagem paginada e ordenada das categorias")
    public ResponseEntity<ModeloMensagem> listar (Pageable pageable) {
        log.info("Listagem das categorias conforme a paginação: [{}]", pageable);
        Page<Categoria> categorias = categoriaService.listar(pageable);
        return construirModeloMensagemSucesso(categorias.stream().map(categoriaMapper::entidadeParaResponse));
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma Categoria")
    public ResponseEntity<ModeloMensagem> cadastrar(@Valid @RequestBody final CategoriaCadastrarRequestDTO categoriaCadastrarRequestDTO) {
        log.info("Cadastrando a categoria: [{}], ", categoriaCadastrarRequestDTO);
        Categoria categoria = categoriaService.salvar(categoriaMapper.requestParaEntidade(categoriaCadastrarRequestDTO));
        return construirModeloMensagemSucesso(categoriaMapper.entidadeParaResponse(categoria));
    }

    @PutMapping
    @Operation(summary = "Atualizar uma categoria")
    public ResponseEntity<ModeloMensagem> atualizar(@Valid @RequestBody final CategoriaAtualizarRequestDTO categoriaAtualizarRequestDTO) {
        log.info("Atualizando a categoria: [{}], ", categoriaAtualizarRequestDTO);
        Categoria categoria = categoriaService.atualizar(categoriaMapper.requestParaEntidade(categoriaAtualizarRequestDTO));
        return construirModeloMensagemSucesso(categoriaMapper.entidadeParaResponse(categoria));
    }

    @DeleteMapping(ACAO_COM_ID)
    @Operation(summary = "Remover uma categoria especifico")
    public ResponseEntity<ModeloMensagem> remover(@PathVariable(required = true) final Long id) {
        log.info("Removendo a categoria de id: [{}]", id);
        categoriaService.remover(id);
        return construirModeloMensagemSucesso();
    }
}
