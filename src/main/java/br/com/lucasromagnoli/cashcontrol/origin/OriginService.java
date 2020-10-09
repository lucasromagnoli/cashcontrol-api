package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.javaee.useful.support.validation.ObjectNotFoundValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService {
    
    @Autowired
    private OriginRepository originRepository;
    
    public Origin save(Origin origin) {
        return originRepository.save(origin);
    }
    
    public Origin findById(int id) {
        return originRepository.findById(id).orElseThrow(ObjectNotFoundValidationException::new);
    }

    public List<Origin> findAll() {
        return originRepository.findAll();
    }
    
    public boolean existsWithId(Integer id) {
        return originRepository.existsById(id);
    }

    public void delete(Origin originSaved) {
        // TODO: 10/8/20 - Realizar as validacões de negócios
        originRepository.delete(originSaved);
    }
}
