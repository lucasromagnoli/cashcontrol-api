package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.MovimentacaoService;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao.MovimentacaoAtualizarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao.MovimentacaoCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.mapper.MovimentacaoMapper;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagem;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ACAO_COM_ID;
import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.MOVIMENTACAO_RECEITA;
import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ROOT_MOVIMENTACAO;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@RestController
@RequestMapping(ROOT_MOVIMENTACAO)
public class MovimentacaoRestController implements BaseRestController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    private final MovimentacaoMapper movimentacaoMapper = Mappers.getMapper(MovimentacaoMapper.class);
    private final Logger log = LoggerFactory.getLogger(MovimentacaoRestController.class);

    @GetMapping
    @Operation(summary = "Listagem paginada e ordenada das movimentações")
    public ResponseEntity<ModeloMensagem> listar(@PageableDefault Pageable pageable) {
        log.info("Listagem das Movimentações conforme a paginação: [{}]", pageable);
        Page<Movimentacao> movimentacoes = movimentacaoService.listar(pageable);
        return construirModeloMensagemSucesso(movimentacoes.stream().map(movimentacaoMapper::entidadeParaResponse));
    }

    @GetMapping(ACAO_COM_ID)
    @Operation(summary = "Consultar uma movimentação especifica")
    public ResponseEntity<ModeloMensagem> consultar(@PathVariable(required = true) final Long id) {
        log.info("Consultando a Movimentação de id: [{}]", id);
        return construirModeloMensagemSucesso(movimentacaoMapper.entidadeParaResponse(movimentacaoService.consultarPeloId(id)));
    }

    @PostMapping(MOVIMENTACAO_RECEITA)
    @Operation(summary = "Cadastrar uma movimentação")
    public ResponseEntity<ModeloMensagem> cadastrar(@Valid @RequestBody final MovimentacaoCadastrarRequestDTO movimentacaoCadastrarRequestDTO) {
        log.info("Cadastrando a movimentação: [{}], ", movimentacaoCadastrarRequestDTO);
        Movimentacao movimentacao = movimentacaoMapper.requestParaEntidade(movimentacaoCadastrarRequestDTO);
        movimentacao.setTipoMovimentacao(TipoMovimentacaoEnum.RECEITA);
        return construirModeloMensagemSucesso(movimentacaoMapper.entidadeParaResponse(movimentacaoService.salvar(movimentacao)));
    }

    @PutMapping(MOVIMENTACAO_RECEITA)
    @Operation(summary = "Cadastrar uma movimentação")
    public ResponseEntity<ModeloMensagem> atualizar(@Valid @RequestBody final MovimentacaoAtualizarRequestDTO movimentacaoAtualizarRequestDTO) {
        log.info("Atualizando a movimentação: [{}], ", movimentacaoAtualizarRequestDTO);

        Movimentacao movimentacao = movimentacaoMapper.requestParaEntidade(movimentacaoAtualizarRequestDTO);
        return construirModeloMensagemSucesso(movimentacaoMapper.entidadeParaResponse(movimentacaoService.atualizar(movimentacao)));
    }

    @DeleteMapping(MOVIMENTACAO_RECEITA+ACAO_COM_ID)
    @Operation(summary = "Remover uma movimentação")
    public ResponseEntity<ModeloMensagem> remover(@PathVariable(required = true) final Long id) {
        log.info("Removendo a movimentação de id: [{}], ", id);
        movimentacaoService.remover(id);
        return construirModeloMensagemSucesso();
    }
}
