package com.bosch.productapp.service;

import com.bosch.productapp.exception.NoProductFoundException;
import com.bosch.productapp.exception.ProductException;
import com.bosch.productapp.model.Product;

import java.util.List;

public interface ProductInterface {
    int addProduct(Product product, int id);
    boolean deleteProduct(int id) throws ProductException;
    boolean updateProduct(int id,Product product) throws NoProductFoundException;
    Product viewProduct(int id);
    List<Product> viewAllProduct();
    void printStatistics();
    void importProducts();
    void exportProducts();
}
