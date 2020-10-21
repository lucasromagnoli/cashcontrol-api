package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionSupport;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Configuration
@EnableScheduling
public class SubscriptionJob {

    @Autowired
    private ExpenseService expenseService;

    // TODO: 10/19/20 - Atualizar para a cron correta após implementacão e testes
    @Scheduled(cron = "0/10 * * * * *")
    public void insertNewTransaction() {
        // TODO: 10/16/20 - Inserir logger
        // TODO: 10/17/20 - Pensar uma maneira mais perfomatica de trazer a carga (Obs.: melhoria arquitetural)
        List<Expense> expenses = expenseService.findAllSubscriptionsActiveAndReadyToInsertNextTransaction();

        for (Expense expense : expenses) {
            LocalDate nextTransaction = expense.getSubscription().getNextTransaction();
            long diff = ChronoUnit.MONTHS.between(nextTransaction, LocalDate.now());

            for (int index = 0; index <= diff; index++) {
                Transaction transaction = TransactionSupport
                        .genereteTransaction(expense, expense.getSubscription().getNextTransaction());

                // TODO: 10/19/20 - Criar um método que deixe o código abaixo mais legível
                expense.getSubscription().setNextTransaction(expense.getSubscription().getNextTransaction()
                        .plusMonths(expense.getSubscription().getFrequencyType().getMonths()));
                expense.getSubscription().setAmount(expense.getSubscription().getAmount().add(expense.getValue()));
                expense.getTransactions().add(transaction);
            }

            // TODO: 10/16/20 - Desmembrar em operacões isoladas afim de melhorar a perfomance
            expenseService.update(expense);
        }
    }
}
