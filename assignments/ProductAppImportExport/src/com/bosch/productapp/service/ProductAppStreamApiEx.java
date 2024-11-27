package com.bosch.productapp.service;

import com.bosch.productapp.model.Product;
import com.bosch.productapp.model.ProductCategory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProductAppStreamApiEx {
    public static void main(String[] args) {
        Map<Integer, Product> products = new HashMap<>();
        products.put(100,new Product(105, ProductCategory.MOBILES,"Smart phone",45000,true));
        products.put(101,new Product(105, ProductCategory.LAPTOPS,"Laptops",15000,true));
        products.put(102,new Product(105, ProductCategory.LAPTOPS,"Smart phone 2",5000,true));
        products.put(103,new Product(105, ProductCategory.STATIONARY,"Stationary",50,true));
        products.put(104,new Product(105, ProductCategory.LAPTOPS,"Laptops 2",200.0,true));
        products.put(105,new Product(105, ProductCategory.MOBILES,"Smart phone",46000,true));
        products.put(106,new Product(105, ProductCategory.LAPTOPS,"Laptops",15003,true));
        products.put(107,new Product(105, ProductCategory.MOBILES,"Smart phone 2",35000,true));
        products.put(108,new Product(105, ProductCategory.STATIONARY,"Stationary",500,true));
        products.put(109,new Product(105, ProductCategory.LAPTOPS,"Laptops 2",203.0,true));

        //1: Count products with price > 10,000
        long count = products.values().stream()
                .filter(product -> product.getPrice() > 10000)
                .count();
        System.out.println("Count of products with price > 10,000: " + count);
        System.out.println("2: show no of products by category with sorting");
        //2: show no of products by category with sorting
        products.values().stream()
                .collect(Collectors.groupingBy(Product::getCategory,Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);

        //3:show avg price of products category wise
        System.out.println("3:show avg price of products category wise");
        products.values().stream()
                .collect(Collectors.groupingBy(Product::getCategory,Collectors.averagingDouble(Product::getPrice)))
                .entrySet().stream()
                .forEach(System.out::println);

        //4:List product ids whose product name contains given name
        System.out.println("4:List product ids whose product name contains given name");
        String name = "Smart";
        products.values().stream()
                .filter(product -> product.getDescription().contains(name))
                .map(Product::getId)
                .forEach(System.out::println);

    }
}
