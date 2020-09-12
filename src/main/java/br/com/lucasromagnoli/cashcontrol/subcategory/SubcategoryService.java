package br.com.lucasromagnoli.cashcontrol.subcategory;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public List<Subcategory> list() {
        return subcategoryRepository.findAll();
    }
}
