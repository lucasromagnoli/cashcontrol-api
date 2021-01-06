package br.com.lucasromagnoli.cashcontrol.transaction;

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
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        // TODO: 10/7/20 - Inserir validacã́o de negócios
        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public Page<Transaction> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public boolean existsWithIncome(Integer id) {
        return transactionRepository.existsByIncomeId(id);
    }
}
