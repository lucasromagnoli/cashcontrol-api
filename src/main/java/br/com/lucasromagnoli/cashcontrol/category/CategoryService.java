package br.com.lucasromagnoli.cashcontrol.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    public List<Category> list() {
        return categoryRepository.findAll();
    }
}
