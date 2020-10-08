package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public List<Subcategory> list() {
        return subcategoryRepository.findAll();
    }
    
    public boolean existsByIdAndMovimentationType(int id, TransactionTypeEnum transactionTypeEnum) {
        return subcategoryRepository.existsByIdAndCategoryType(id, transactionTypeEnum);
    }
    
}
