package br.com.lucasromagnoli.cashcontrol.bootstrap;

import br.com.lucasromagnoli.cashcontrol.category.Category;
import br.com.lucasromagnoli.cashcontrol.expense.Expense;
import br.com.lucasromagnoli.cashcontrol.income.Income;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackageClasses = {
        Category.class,
        Subcategory.class,
        Transaction.class,
        Origin.class,
        Expense.class,
        Income.class
})
@EnableJpaRepositories("br.com.lucasromagnoli.cashcontrol")
@SpringBootApplication(scanBasePackages = "br.com.lucasromagnoli.cashcontrol")
public class CashControlAplication {
    

    public static void main(String[] args) {
        SpringApplication.run(CashControlAplication.class, args);
    }
}
