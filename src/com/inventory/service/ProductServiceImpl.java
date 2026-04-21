package com.inventory.service;

import com.inventory.exception.DuplicateProductException;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.ProductRepositoryImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    // Dependency — Repository layer
    private final ProductRepository repository;

    public ProductServiceImpl() {
        this.repository = new ProductRepositoryImpl();
    }

    // ── Add Product ──────────────────────────────────────────
    @Override
    public void addProduct(String name, String category, double price, int quantity) {
        // Validation
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty!");
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative!");
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative!");

        // Duplicate check
        if (repository.existsByName(name.trim()))
            throw new DuplicateProductException("Product '" + name + "' already exists!");

        Product product = new Product(name.trim(), category.trim(), price, quantity);
        repository.addProduct(product);
        System.out.println("✅ Product '" + name + "' added successfully!");
    }

    // ── Get All Products ─────────────────────────────────────
    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    // ── Update Quantity ──────────────────────────────────────
    @Override
    public void updateQuantity(int id, int newQuantity) {
        if (newQuantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative!");

        if (!repository.existsById(id))
            throw new ProductNotFoundException("Product with ID " + id + " not found!");

        repository.updateQuantity(id, newQuantity);
        System.out.println("✅ Quantity updated successfully!");
    }

    // ── Remove Product ───────────────────────────────────────
    @Override
    public void removeProduct(int id) {
        if (!repository.existsById(id))
            throw new ProductNotFoundException("Product with ID " + id + " not found!");

        repository.removeProduct(id);
        System.out.println("✅ Product removed successfully!");
    }

    // ── Get By ID ────────────────────────────────────────────
    @Override
    public Product getProductById(int id) {
        Product p = repository.getProductById(id);
        if (p == null)
            throw new ProductNotFoundException("Product with ID " + id + " not found!");
        return p;
    }
}