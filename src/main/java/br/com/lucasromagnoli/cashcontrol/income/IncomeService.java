package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeBusinessValidator incomeBusinessValidator;

    @Transactional(readOnly = false)
    public Income save(Income income) {
        incomeBusinessValidator.validateSave(income);
        income.setTransaction(TransactionSupport.generateTransaction(income));
        return incomeRepository.save(income);
    }

    @Transactional(readOnly = true)
    public void delete(Income income) {
        incomeBusinessValidator.validateDelete(income);
        incomeRepository.delete(income);
    }

    @Transactional(readOnly = true)
    public Page<Income> findAll(Pageable pageable) {
        return incomeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public boolean existsWithId(Integer id) {
        return incomeRepository.existsById(id);
    }
}
