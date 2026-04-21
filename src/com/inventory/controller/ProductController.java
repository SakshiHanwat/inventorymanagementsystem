package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import com.inventory.service.ProductServiceImpl;
import com.inventory.util.ConsoleHelper;

import java.util.List;

public class ProductController {

    private final ProductService service;

    public ProductController() {
        this.service = new ProductServiceImpl();
    }

    // ── 1. View All Products ─────────────────────────────────
    public void viewAllProducts() {
        List<Product> products = service.getAllProducts();

        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           ALL PRODUCTS                             ║");
        System.out.println("╠═══╦════════════════════════╦══════════════╦══════════╦══════════╣");
        System.out.printf ("║ %-3s║ %-22s║ %-12s║ %-8s ║ %-8s ║%n", "ID", "Name", "Category", "Price", "Qty");
        System.out.println("╠═══╬════════════════════════╬══════════════╬══════════╬══════════╣");

        if (products.isEmpty()) {
            System.out.println("║                  No products found in inventory!               ║");
        } else {
            for (Product p : products) {
                String status = p.getStockStatus();
                String flag   = status.equals("OUT OF STOCK") ? "🔴" :
                        status.equals("LOW STOCK")    ? "🟡" : "🟢";
                System.out.printf("║ %-3d║ %-22s║ %-12s║ Rs.%-6.2f║ %-3d %s   ║%n",
                        p.getId(), truncate(p.getName(), 22), truncate(p.getCategory(), 12),
                        p.getPrice(), p.getQuantity(), flag);
            }
        }
        System.out.println("╚═══╩════════════════════════╩══════════════╩══════════╩══════════╝");
        System.out.println("  🟢 IN STOCK   🟡 LOW STOCK (≤5)   🔴 OUT OF STOCK\n");
    }

    // ── 2. Add Product ───────────────────────────────────────
    public void addProduct() {
        System.out.println("\n── ADD NEW PRODUCT ──────────────────────────────");
        String name     = ConsoleHelper.readString ("  Product Name     : ");
        String category = ConsoleHelper.readString ("  Category         : ");
        double price    = ConsoleHelper.readDouble ("  Price (Rs.)      : ");
        int    quantity = ConsoleHelper.readInt    ("  Initial Quantity : ");

        try {
            service.addProduct(name, category, price, quantity);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ── 3. Update Quantity ───────────────────────────────────
    public void updateQuantity() {
        System.out.println("\n── UPDATE PRODUCT QUANTITY ──────────────────────");
        viewAllProducts();
        int id          = ConsoleHelper.readInt("  Enter Product ID  : ");
        int newQuantity = ConsoleHelper.readInt("  New Quantity      : ");

        try {
            service.updateQuantity(id, newQuantity);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ── 4. Remove Product ────────────────────────────────────
    public void removeProduct() {
        System.out.println("\n── REMOVE PRODUCT ───────────────────────────────");
        viewAllProducts();
        int id = ConsoleHelper.readInt("  Enter Product ID to remove : ");

        // Confirm
        try {
            Product p = service.getProductById(id);
            String confirm = ConsoleHelper.readString("  Remove '" + p.getName() + "'? (yes/no) : ");
            if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
                service.removeProduct(id);
            } else {
                System.out.println("ℹ️  Remove cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ── Helper ───────────────────────────────────────────────
    private String truncate(String s, int max) {
        return s.length() > max ? s.substring(0, max - 2) + ".." : s;
    }
}