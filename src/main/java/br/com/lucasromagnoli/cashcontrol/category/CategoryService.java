package br.com.lucasromagnoli.cashcontrol.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = false)
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = false)
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Transactional(readOnly = true)
    public List<Category> list() {
        return categoryRepository.findAll();
    }
}
