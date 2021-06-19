package br.com.lucasromagnoli.cashcontrol.web.v1.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Parcelamento;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.DespesaService;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.ParcelamentoCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.mapper.DespesaMapper;
import br.com.lucasromagnoli.cashcontrol.web.v1.modelo.ModeloMensagem;
import io.swagger.v3.oas.annotations.Operation;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.DESPESA_PARCELAMENTO;
import static br.com.lucasromagnoli.cashcontrol.web.v1.controller.configuracao.ControllerMapping.ROOT_DESPESA;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@RestController
@RequestMapping(ROOT_DESPESA)
public class DespesaRestController implements BaseRestController {
    @Autowired
    private DespesaService despesaService;

    private final DespesaMapper despesaMapper = Mappers.getMapper(DespesaMapper.class);
    private final Logger log = LoggerFactory.getLogger(DespesaRestController.class);

    @PostMapping(DESPESA_PARCELAMENTO)
    @Operation(summary = "Cadastrar um parcelamento")
    public ResponseEntity<ModeloMensagem> cadastrar(@Valid @RequestBody final ParcelamentoCadastrarRequestDTO parcelamentoCadastrarRequestDTO) {
        log.info("Cadastrando o parcelamento: [{}], ", parcelamentoCadastrarRequestDTO);
        Parcelamento parcelamento = despesaService.cadastrar(despesaMapper.requestParaEntidade(parcelamentoCadastrarRequestDTO));
        return construirModeloMensagemSucesso(despesaMapper.entidadeParaResponse(parcelamento));
    }
}
