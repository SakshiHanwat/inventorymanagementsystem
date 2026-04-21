package com.inventory.repository;

import com.inventory.model.Product;
import java.util.List;

public interface ProductRepository {

    void         addProduct(Product product);
    Product      getProductById(int id);
    Product      getProductByName(String name);
    List<Product> getAllProducts();
    void         updateQuantity(int id, int newQuantity);
    void         removeProduct(int id);
    boolean      existsById(int id);
    boolean      existsByName(String name);
}