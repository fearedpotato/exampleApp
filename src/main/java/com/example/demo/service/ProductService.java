package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void addProduct(String name, String description, double price, Category category) {
        productRepo.save(new Product(name, description, price, category));
    }

    public void removeProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> getAllProducts () {
        return productRepo.findAll();
    }
}
