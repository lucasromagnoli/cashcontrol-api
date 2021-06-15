package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Service
@Transactional(readOnly = true)
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public Page<Movimentacao> listar(Pageable pageable) {
        return movimentacaoRepository.listar(pageable);
    }

    public Movimentacao consultarPeloId(Long id) {
        return Optional.ofNullable(movimentacaoRepository.pesquisaPorId(id)).orElseThrow(() -> new RegistroNaoEncontrado(Movimentacao.class, "id", id));
    }

    @Transactional(readOnly = false)
    public Movimentacao salvar(Movimentacao movimentacao) {
        // Validar salvar
        movimentacaoRepository.save(movimentacao);
        return movimentacao;
    }
}
