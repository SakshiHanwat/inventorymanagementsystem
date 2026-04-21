package com.inventory;

import com.inventory.config.DBConnection;
import com.inventory.controller.ProductController;
import com.inventory.util.ConsoleHelper;

public class Main {

    public static void main(String[] args) {

        printBanner();

        ProductController controller = new ProductController();

        // Verify DB connection on startup
        try {
            DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("\n Database connection failed: " + e.getMessage());
            System.out.println("   → Check MySQL is running and password in DBConfig.java is correct.");
            return;
        }

        // ── Main Menu Loop ──────────────────────────────────
        boolean running = true;
        while (running) {
            printMenu();
            int choice = ConsoleHelper.readInt("  Enter choice (1-5): ");

            switch (choice) {
                case 1 -> controller.viewAllProducts();
                case 2 -> controller.addProduct();
                case 3 -> controller.updateQuantity();
                case 4 -> controller.removeProduct();
                case 5 -> {
                    System.out.println("\n  👋 Thank you! Exiting system...\n");
                    running = false;
                }
                default -> System.out.println("\n  Invalid choice! Please enter 1-5.\n");
            }
        }

        DBConnection.closeConnection();
    }

    // ── UI Helpers ──────────────────────────────────────────
    private static void printBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║   INVENTORY MANAGEMENT SYSTEM        ║");
        System.out.println("  ║        Small Retail Store            ║");
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("  ┌─────────────────────────────────────┐");
        System.out.println("  │              MAIN MENU              │");
        System.out.println("  ├─────────────────────────────────────┤");
        System.out.println("  │  1.  View All Products              │");
        System.out.println("  │  2.  Add New Product                │");
        System.out.println("  │  3.  Update Product Quantity        │");
        System.out.println("  │  4.  Remove Product                 │");
        System.out.println("  │  5.  Exit                           │");
        System.out.println("  └─────────────────────────────────────┘");
    }
}