package com.example.backend.service;

import com.example.backend.model.Product;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final String productsPath = "data/products.csv";
    private final String ordersPath = "data/orders.csv";

    public List<Product> getTop5SoldProducts() throws IOException {
        List<Product> products = readProducts();
        return products.stream()
                .sorted((a, b) -> b.getSold() - a.getSold())
                .limit(5)
                .collect(Collectors.toList());
    }

    public String getOrderStatus(String orderId) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(ordersPath))) {
            reader.skip(1);
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(orderId)) {
                    return line[2];
                }
            }
        }
        return "Order ID not found";
    }

    public String getStockByProductId(String productId) throws IOException {
        List<Product> products = readProducts();
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return "Stock left: " + p.getStock();
            }
        }
        return "Product not found";
    }

    private List<Product> readProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(productsPath))) {
            reader.skip(1);
            String[] line;
            while ((line = reader.readNext()) != null) {
                Product p = new Product();
                p.setProductId(line[0]);
                p.setProductName(line[1]);
                p.setStock(Integer.parseInt(line[2]));
                p.setSold(Integer.parseInt(line[3]));
                products.add(p);
            }
        }
        return products;
    }
}