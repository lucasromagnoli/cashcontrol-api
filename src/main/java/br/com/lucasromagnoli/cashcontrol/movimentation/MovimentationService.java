package br.com.lucasromagnoli.cashcontrol.movimentation;

import br.com.lucasromagnoli.cashcontrol.origin.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentationService {

    @Autowired
    private MovimentationRepository movimentationRepository;
    
    @Autowired
    private OriginService originService;
    
    @Autowired
    private MovimentationBusinessValidator movimentationBusinessValidator;
    
    @Transactional
    public Movimentation save(Movimentation movimentation) {
        movimentationBusinessValidator.validateSave(movimentation);
        movimentation.setOrigin(originService.findById(movimentation.getOrigin().getId()));
        return movimentationRepository.save(movimentation);
    }
    @Transactional(readOnly = true)
    public List<Movimentation> findAll() {
        return movimentationRepository.findAll();
    }
    
}
