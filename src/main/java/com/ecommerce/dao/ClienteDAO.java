package com.ecommerce.dao;

import com.ecommerce.model.Product;
import com.ecommerce.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    // Create
    public Product create(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, stock, category) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setString(5, product.getCategory());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }

            return product;
        }
    }

    // Read
    public Optional<Product> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Product(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getBigDecimal("price"),
                            rs.getInt("stock"),
                            rs.getString("category")
                    ));
                }
                return Optional.empty();
            }
        }
    }

    // Update
    public boolean update(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock = ?, category = ? WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setString(5, product.getCategory());
            pstmt.setLong(6, product.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Delete
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // List All
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        rs.getString("category")
                ));
            }
        }

        return products;
    }
}