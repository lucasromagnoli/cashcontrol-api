package br.com.lucasromagnoli.cashcontrol.origin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Service
public class OriginService {
    
    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private OriginBusinessValidator originBusinessValidator;

    @Transactional(readOnly = true)
    public List<Origin> findAll() {
        return originRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Origin> findAll(Pageable pageable) {
        return originRepository.findAll(pageable);
    }

    @Transactional(readOnly = false)
    public Origin save(Origin origin) {
        originBusinessValidator.validateSave(origin);
        return originRepository.save(origin);
    }

    @Transactional(readOnly = false)
    public Origin update(Origin origin) {
        originBusinessValidator.validateUpdate(origin);
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
