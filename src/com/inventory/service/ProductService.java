package com.inventory.service;

import com.inventory.model.Product;
import java.util.List;

public interface ProductService {

    void          addProduct(String name, String category, double price, int quantity);
    List<Product> getAllProducts();
    void          updateQuantity(int id, int newQuantity);
    void          removeProduct(int id);
    Product       getProductById(int id);
}