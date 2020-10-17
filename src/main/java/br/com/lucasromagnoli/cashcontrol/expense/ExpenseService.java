package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.support.TransactionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseBusinessValidator expenseBusinessValidator;

    public Expense save(Expense expense) {
        expenseBusinessValidator.validateSave(expense);
        expense.setTransactions(TransactionSupport.generateTransaction(expense));

        if (!ExpenseSupport.isInstallment(expense)) {
            expense.setInstallment(null);
        } else if (!ExpenseSupport.isSubscription(expense)) {
            expense.setSubscription(null);
        }

        return expenseRepository.save(expense);
    }

    public boolean existsWithId(Integer id) {
        return expenseRepository.existsById(id);
    }
}
