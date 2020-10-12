package br.com.lucasromagnoli.cashcontrol.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OriginService {
    
    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private OriginBusinessValidator originBusinessValidator;

    @Transactional(readOnly = true)
    public List<Origin> findAll() {
        // TODO: 10/7/20 - Implementar a paginacão
        return originRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Origin save(Origin origin) {
        originBusinessValidator.validateSave(origin);
        // TODO: 10/12/20 - Capitalizar o nome da origem
        return originRepository.save(origin);
    }

    @Transactional(readOnly = false)
    public Origin update(Origin origin) {
        originBusinessValidator.validateUpdate(origin);
        // TODO: 10/12/20 - Capitalizar o nome da origem
        return originRepository.save(origin);
    }

    @Transactional(readOnly = false)
    public void delete(Origin origin) {
        originBusinessValidator.validateDelete(origin);
        originRepository.delete(origin);
    }

    @Transactional(readOnly = true)
    public boolean existsWithId(Integer id) {
        return originRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsWithName(String name) {
        return originRepository.existsByNameIgnoreCase(name);
    }
}
