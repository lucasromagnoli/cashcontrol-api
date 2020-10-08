package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Transactional
    public Income save(Income income) {
        // TODO: 10/7/20 - Realizar a validacã́o de negócios.

        // TODO: 10/7/20 - Criar método responsável por gerar a transacão
        Transaction transaction = new Transaction();
        transaction.setDate(income.getDate());
        transaction.setValue(income.getValue());
        transaction.setTransactionTypeEnum(TransactionTypeEnum.INCOME);
        transaction.setIncome(income);

        List<Transaction> transactions = new LinkedList<>();
        transactions.add(transaction);
        income.setTransactions(transactions);
        return incomeRepository.save(income);
    }

    public void delete(Income incomeSaved) {
        // TODO: 10/7/20 - Realizar as validacões de negócios
        incomeRepository.delete(incomeSaved);
    }

    public List<Income> findAll() {
        // TODO: 10/7/20 - Implementar a paginacão
        return incomeRepository.findAll();
    }
}
