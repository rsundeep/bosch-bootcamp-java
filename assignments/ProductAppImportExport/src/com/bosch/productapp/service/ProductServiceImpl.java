package com.bosch.productapp.service;

import com.bosch.productapp.exception.NoProductFoundException;
import com.bosch.productapp.exception.ProductException;
import com.bosch.productapp.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductInterface{

    private Map<Integer, Product> products = new HashMap<Integer, Product>();
    @Override
    public int addProduct(Product product,int id)
    {
        //TODO VALIDATION
        product.setId(products.size() + 1);
        products.put(product.getId(), product);
        return product.getId();
    }
    @Override
    public boolean updateProduct(int id, Product product) throws NoProductFoundException
    {
       // products.put(id, product);
        if(!products.containsKey(id))
        {
            throw new NoProductFoundException();

        }
        else {
            products.replace(id,product);
        }
        return true;
    }
    @Override
    public boolean deleteProduct(int id) throws ProductException
    {
        if (!products.containsKey(id))
        {
            //throw new NoProductFoundException();

        }
        else {
            products.remove(id);
        }
        return true;
    }
    @Override
    public Product viewProduct(int id)
    {
        return products.get(id);
    }

    @Override
    public List<Product> viewAllProduct() {
        return List.of();
    }

    @Override
    public void printStatistics() {

    }

    @Override
    public void importProducts() {

    }

    @Override
    public void exportProducts() {

    }


}
