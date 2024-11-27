package com.bosch.productapp;

import com.bosch.productapp.exception.NoProductFoundException;
import com.bosch.productapp.exception.ProductException;
import com.bosch.productapp.model.Product;
import com.bosch.productapp.model.ProductCategory;
import com.bosch.productapp.service.ProductServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.*;
import java.util.concurrent.*;


//TODO Show menu to the user
//        1] Add Product
//        2] Update Product
//        3] Delete Product
//        4] View Product
//        5] View All Products
//        6] Print Statistics [Implement using Stream API]
//             a] No of products whose price more than 10k
//              b] Show no of products by product category with sorting
//              c] Show avg price by product category
//              d] List product ids whose product name contains given name
//        7] Import [Product Details]
//        8] Export [Product Details]
//        9] Exit

public class ProductAppMain {
    private static final Map<Integer, Product> productMap = new ConcurrentHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
     public static  void ShowMenu()
     {
         System.out.println("Welcome to ProductApp!");
         System.out.println("1] Add Product");
         System.out.println("[2] Update Product");
         System.out.println("[3] Delete Product");
         System.out.println("[4] View Product");
         System.out.println("[5] View All Products");
         System.out.println("[6] Print Statistics");
         System.out.println("[7] Import Product`");
         System.out.println("[8] Export Product`");
         System.out.println("[9] Exit");
         System.out.println("Enter your choice");
     }
     public  static void extracted(Scanner in)
     {

     }
    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();
         while(true) {
              {
                 ShowMenu();
                 int choice = scanner.nextInt();
                 switch (choice) {
                     case 1 -> {
                         System.out.println("Add Product");
                        addProduct();
                     }
                     case 2 ->
                     {
                         System.out.println("Update Product");
                         updateProduct();
                     }
                     case 3 ->
                     {
                         System.out.println("Delete Product");
                         deleteProduct();
                     }
                     case 4 -> {
                         System.out.println("View Product");
                         viewProduct();
                     }
                     case 5 -> {
                         System.out.println("View All Products");
                         viewAllProducts();
                     }

                     case 6 -> {
                         System.out.println("Print Statistics");
                        printStatistics();
                     }
                     case 7 ->
                     {
                         System.out.println("Import Product`");
                         //Import the data from File
                         importProducts();
                     }
                     case 8 ->
                     {
                         System.out.println("Export Product`");
                         exportProducts();
                     }
                     //Export the DATA TO file
                     case 9 -> {
                         System.out.println("THANK YOU FOR USING PRODUCTAPP");
                         System.exit(0);
                     }
                     default -> System.out.println("Invalid choice");
                 }
             }

         }

    }

    private static Product createProduct(int id) {
        System.out.print("Enter product category (MOBILES, LAPTOPS, FURNITURES, STATIONARY): ");
        ProductCategory category = ProductCategory.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Is the product active? (true/false): ");
        boolean active = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        return new Product(id, category, description, price, active);
    }
    private static void addProduct() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (productMap.containsKey(id)) {
            System.out.println("Product with this ID already exists.");
            return;
        }

        Product product = createProduct(id);
        productMap.put(id, product);
        System.out.println("Product added successfully.");
    }
    private static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (!productMap.containsKey(id)) {
            System.out.println("Product with this ID does not exist.");
            return;
        }

        Product product = createProduct(id);
        productMap.put(id, product);
        System.out.println("Product updated successfully.");
    }


    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (productMap.remove(id) != null) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product with this ID does not exist.");
        }
    }

    private static void viewProduct() {
        System.out.print("Enter product ID to view: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = productMap.get(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product with this ID does not exist.");
        }
    }

    private static void viewAllProducts() {
        productMap.values().forEach(System.out::println);
    }

    private static void printStatistics() {
        // a] Number of products whose price is more than 10k
        long count = productMap.values().stream()
                .filter(p -> p.getPrice() > 10000)
                .count();
        System.out.println("Number of products with price more than 10k: " + count);

        // b] Show number of products by product category with sorting
        Map<ProductCategory, Long> productsByCategory = productMap.values().stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        productsByCategory.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println("Category: " + entry.getKey() + ", Count: " + entry.getValue()));

        // c] Show average price by product category
        Map<ProductCategory, Double> avgPriceByCategory = productMap.values().stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));
        avgPriceByCategory.forEach((category, avgPrice) -> System.out.println("Category: " + category + ", Average Price: " + avgPrice));

        // d] List product IDs whose product name contains given name
        System.out.print("Enter name to search: ");
        String searchName = scanner.nextLine();
        List<Integer> productIds = productMap.values().stream()
                .filter(p -> p.getDescription().contains(searchName))
                .map(Product::getId)
                .collect(Collectors.toList());
        System.out.println("Product IDs with name containing '" + searchName + "': " + productIds);
    }

    private static void importProducts() {
        System.out.print("Enter file path to import: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                ProductCategory category = ProductCategory.valueOf(parts[1]);
                String description = parts[2];
                double price = Double.parseDouble(parts[3]);
                boolean active = Boolean.parseBoolean(parts[4]);

                Product product = new Product(id, category, description, price, active);
                productMap.put(id, product);
            }
            System.out.println("Products imported successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void exportProducts() {
        System.out.print("Enter file path to export: ");
        String filePath = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : productMap.values()) {
                writer.write(product.getId() + "," +
                        product.getCategory() + "," +
                        product.getDescription() + "," +
                        product.getPrice() + "," +
                        product.isAvailable());
                writer.newLine();
            }
            System.out.println("Products exported successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
