package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.OrigemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Service
@Transactional(readOnly = true)
public class OrigemService {
    @Autowired
    private OrigemRepository origemRepository;

    @Transactional(readOnly = false)
    public Origem salvar(Origem origem) {
        origemRepository.save(origem);
        return origem;
    }
}
