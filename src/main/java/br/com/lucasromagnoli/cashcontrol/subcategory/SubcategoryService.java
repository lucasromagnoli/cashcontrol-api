package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public Page<Subcategory> findAll(Pageable pageable) {
        return subcategoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = false)
    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    @Transactional(readOnly = false)
    public Subcategory update(Subcategory subcategory) {
//        categoryBusinessValidator.validateUpdate(category);
        // TODO: 10/13/20 - Carregar os campos que não obrigatórios
        return subcategoryRepository.save(subcategory);
    }

    @Transactional(readOnly = false)
    public void delete(Subcategory subcategory) {
//        categoryBusinessValidator.validateDelete(category);
        subcategoryRepository.delete(subcategory);
    }

    @Transactional(readOnly = true)
    public boolean existsWithId(Integer id) {
        return subcategoryRepository.existsById(id);
    }

    public boolean existsWithNameAndTransactionType(String name, TransactionTypeEnum transactionTypeEnum) {
        return subcategoryRepository.existsByNameAndCategoryType(name, transactionTypeEnum);
    }
}
