package com.ecommerce.service;

import com.ecommerce.dao.ClienteDAO;
import com.ecommerce.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ClienteDAO productDAO;

    public ProductService() {
        this.productDAO = new ClienteDAO();
    }

    public Product createProduct(Product product) throws SQLException {
        return productDAO.create(product);
    }

    public Optional<Product> getProduct(Long id) throws SQLException {
        return productDAO.findById(id);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.findAll();
    }

    public boolean updateProduct(Product product) throws SQLException {
        return productDAO.update(product);
    }

    public boolean deleteProduct(Long id) throws SQLException {
        return productDAO.delete(id);
    }
}