package br.com.lucasromagnoli.cashcontrol.bootstrap;

import br.com.lucasromagnoli.cashcontrol.administrator.importation.ImportationCategoriesExcel;
import br.com.lucasromagnoli.cashcontrol.category.Category;
import br.com.lucasromagnoli.cashcontrol.category.CategoryService;
import br.com.lucasromagnoli.cashcontrol.expense.Expense;
import br.com.lucasromagnoli.cashcontrol.income.Income;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

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
public class CashControlAplication implements CommandLineRunner {
    
    @Autowired
    ImportationCategoriesExcel importationCategoriesExcel;
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    ObjectMapper objectMapper;
            
    public static void main(String[] args) {
        SpringApplication.run(CashControlAplication.class, args);
    }
    

    
    @Override
    public void run(String... args) throws Exception {
        Resource excelFile = new ClassPathResource("/input/input-category-and-subcategory.xls");
        List<Category> categories = importationCategoriesExcel.parse(excelFile.getInputStream());
        categoryService.saveAll(categories);
    }
}
