package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador.OrigemValidacaoNegocio;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.OrigemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Service
@Transactional(readOnly = true)
public class OrigemService {
    @Autowired
    private OrigemRepository origemRepository;

    @Autowired
    private OrigemValidacaoNegocio origemValidacaoNegocio;

    public Page<Origem> listar(Pageable pageable) {
        return origemRepository.listar(pageable);
    }

    public Origem consultarPeloId(Long id) {
        return Optional.ofNullable(origemRepository.pesquisaPorId(id)).orElseThrow(() -> new RegistroNaoEncontrado(Origem.class, "id", id));
    }

    @Transactional(readOnly = false)
    public void remover(Long id) {
        Origem origem = new Origem(id);
        origemValidacaoNegocio.validarRemover(origem);
        origemRepository.remover(origem);
    }

    @Transactional(readOnly = false)
    public Origem salvar(Origem origem) {
        origemValidacaoNegocio.validarSalvar(origem);
        origemRepository.save(origem);
        return origem;
    }

    @Transactional(readOnly = false)
    public Origem atualizar(Origem origem) {
        origemValidacaoNegocio.validarAtualizar(origem);
        origemRepository.atualizar(origem);
        return origem;
    }

    public boolean existeComMesmoNome(Origem origem) {
        return origemRepository.existeByNome(origem);
    }

    public boolean existeById(Origem origem) {
        return origemRepository.existe(origem);
    }
}
