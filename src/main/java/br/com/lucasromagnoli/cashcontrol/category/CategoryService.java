package br.com.lucasromagnoli.cashcontrol.category;

import br.com.lucasromagnoli.cashcontrol.subcategory.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CategoryBusinessValidator categoryBusinessValidator;

    @Transactional(readOnly = false)
    public Category save(Category category) {
        categoryBusinessValidator.validateSave(category);
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = false)
    public void saveAll(List<Category> categories) {
        // TODO: 10/15/20 - Validar para cadastrar as subcategorias, a mesma só deve ser utilizada nas importacões
        categoryRepository.saveAll(categories);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = false)
    public Category update(Category category) {
        categoryBusinessValidator.validateUpdate(category);
        // TODO: 10/13/20 - Carregar os campos que não obrigatórios
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = false)
    public void delete(Category category) {
        categoryBusinessValidator.validateDelete(category);
        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public boolean existsWithId(Integer id) {
        return categoryRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsWithName(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }
}
