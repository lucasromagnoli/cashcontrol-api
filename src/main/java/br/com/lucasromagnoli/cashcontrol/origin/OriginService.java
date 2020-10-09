package br.com.lucasromagnoli.cashcontrol.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OriginService {
    
    @Autowired
    private OriginRepository originRepository;

    @Transactional(readOnly = false)
    public Origin save(Origin origin) {
        // TODO: 10/7/20 - Inserir as validacões de negócios
        return originRepository.save(origin);
    }

    @Transactional(readOnly = true)
    public List<Origin> findAll() {
        // TODO: 10/7/20 - Implementar a paginacão
        return originRepository.findAll();
    }
  
    @Transactional(readOnly = true)
    public boolean existsWithId(Integer id) {
        return originRepository.existsById(id);
    }

    @Transactional(readOnly = false)
    public Origin update(Origin origin) {
        // TODO: 10/7/20 - Inserir as validacões de negócios
        return originRepository.save(origin);
    }

    @Transactional(readOnly = false)
    public void delete(Origin origin) {
        // TODO: 10/7/20 - Inserir as validacões de negócios
        originRepository.delete(origin);
    }
}
