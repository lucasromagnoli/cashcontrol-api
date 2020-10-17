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

    @Autowired
    private SubcategoryBusinessValidator subcategoryBusinessValidator;

    @Transactional(readOnly = true)
    public Page<Subcategory> findAll(Pageable pageable) {
        return subcategoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = false)
    public Subcategory save(Subcategory subcategory) {
        subcategoryBusinessValidator.validateSave(subcategory);
        return subcategoryRepository.save(subcategory);
    }

    @Transactional(readOnly = false)
    public Subcategory update(Subcategory subcategory) {
        subcategoryBusinessValidator.validateUpdate(subcategory);
        // TODO: 10/13/20 - Carregar os campos que não obrigatórios
        return subcategoryRepository.save(subcategory);
    }

    @Transactional(readOnly = false)
    public void delete(Subcategory subcategory) {
        subcategoryBusinessValidator.validateDelete(subcategory);
        subcategoryRepository.delete(subcategory);
    }

    @Transactional(readOnly = true)
    public boolean existsWithId(Integer id) {
        return subcategoryRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsWithNameAndCategoryId(String name, Integer categoryId) {
        return subcategoryRepository.existsByNameAndCategoryId(name, categoryId);
    }

    @Transactional(readOnly = true)
    public boolean existsWithCategoryId(Integer id) {
        return subcategoryRepository.existsByCategoryId(id);
    }

    @Transactional(readOnly = true)
    public TransactionTypeEnum findTransactionType(Integer subcategoryId) {
        return subcategoryRepository.findTransactionTypeSubcategoryId(subcategoryId);
    }
}
