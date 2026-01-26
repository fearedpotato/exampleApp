package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;

        // Hardcoded categories
        // TODO delete later
        addCategory(new Category());
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    public void addCategory(String name, String description) {
        categoryRepo.save(new Category(name, description));
    }

    public void removeCategory(int id) {
        categoryRepo.deleteById(id);
    }

    public Category getCategoryById(int id) {
        return categoryRepo.getReferenceById(id);
    }

    public List<Category> getAllCategories () {
        return categoryRepo.findAll();
    }
}
