package com.inventory.repository;

import com.inventory.config.DBConnection;
import com.inventory.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImpl implements ProductRepository {

    // ── Add Product ─────────────────────────────────────────
    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, category, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice());
            ps.setInt   (4, product.getQuantity());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding product: " + e.getMessage(), e);
        }
    }

    // ── Get By ID ────────────────────────────────────────────
    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching product: " + e.getMessage(), e);
        }
        return null;
    }

    // ── Get By Name ──────────────────────────────────────────
    @Override
    public Product getProductByName(String name) {
        String sql = "SELECT * FROM products WHERE name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching product: " + e.getMessage(), e);
        }
        return null;
    }

    // ── Get All Products ─────────────────────────────────────
    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             Statement  st   = conn.createStatement();
             ResultSet  rs   = st.executeQuery(sql)) {

            while (rs.next()) list.add(mapRow(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching products: " + e.getMessage(), e);
        }
        return list;
    }

    // ── Update Quantity ──────────────────────────────────────
    @Override
    public void updateQuantity(int id, int newQuantity) {
        String sql = "UPDATE products SET quantity = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newQuantity);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating quantity: " + e.getMessage(), e);
        }
    }

    // ── Remove Product ───────────────────────────────────────
    @Override
    public void removeProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error removing product: " + e.getMessage(), e);
        }
    }

    // ── Exists By ID ─────────────────────────────────────────
    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error checking product: " + e.getMessage(), e);
        }
        return false;
    }

    // ── Exists By Name ───────────────────────────────────────
    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) FROM products WHERE name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error checking product: " + e.getMessage(), e);
        }
        return false;
    }

    // ── Helper: ResultSet → Product ──────────────────────────
    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt   ("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt   ("quantity")
        );
    }
}