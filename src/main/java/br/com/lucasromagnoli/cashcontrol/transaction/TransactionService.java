package br.com.lucasromagnoli.cashcontrol.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean existsWithIncome(Integer id) {
        return transactionRepository.existsByIncomeId(id);
    }
}
