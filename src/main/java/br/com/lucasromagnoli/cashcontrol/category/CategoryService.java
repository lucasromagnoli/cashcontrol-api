package br.com.lucasromagnoli.cashcontrol.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryBusinessValidator categoryBusinessValidator;

    @Transactional(readOnly = false)
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = false)
    public void saveAll(List<Category> categories) {
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
