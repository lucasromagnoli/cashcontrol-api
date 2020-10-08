package br.com.lucasromagnoli.cashcontrol;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlAplication;
import br.com.lucasromagnoli.cashcontrol.income.Income;
import br.com.lucasromagnoli.cashcontrol.income.IncomeService;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

//@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = CashControlAplication.class)
public class IncomeServiceTest {
    
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private TransactionService transactionService;

    private static Income incomeSaved;

    @Test
    @Order(1)
    void loadContexts() {
        Assert.isInstanceOf(IncomeService.class, incomeService);
        Assert.isInstanceOf(TransactionService.class, transactionService);
    }

    @Test
    @Order(2)
    void insertIncome() {
        // TODO: 10/7/20 - Gerar uma factory para testes
        Origin origin = new Origin();
        origin.setName("Empresa De Tecnologias Ltda");

        Subcategory subcategory = new Subcategory();
        subcategory.setName("Salário");
        subcategory.setDescription("Salário Mensal");

        Income income = new Income();
        income.setOrigin(origin);
        income.setDate(LocalDate.now());
        income.setValue(new BigDecimal(100));
        incomeSaved = income;
        incomeService.save(income);

        Assert.notNull(income.getId(), "O id não pode estar nulo após uma insercão.");
        Assert.notEmpty(income.getTransactions(), "A lista de transacões não pode ser nula");

        for (Transaction transaction : income.getTransactions()) {
            Assert.state(transaction.getIncome().equals(income),
                    "O id do Income da transacã́o precisa ser o mesmo que do income inserido.");
        }
    }

    @Test
    @Order(3)
    void selectIncome() {
        Assert.notEmpty(incomeService.findAll(), "É necessário encontrar pelo menos 1 registro");
    }

    @Test
    @Order(4)
    void deleteIncome() {
        Integer incomeId = incomeSaved.getId();
        incomeService.delete(incomeSaved);
        Assert.state(!transactionService.existsWithIncome(incomeId), "A transacão não foi removida.");
    }
}
