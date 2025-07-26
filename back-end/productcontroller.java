package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/top-products")
    public List<Product> getTopProducts() throws IOException {
        return productService.getTop5SoldProducts();
    }

    @GetMapping("/order-status/{id}")
    public String getOrderStatus(@PathVariable String id) throws IOException {
        return productService.getOrderStatus(id);
    }

    @GetMapping("/stock/{productId}")
    public String getStock(@PathVariable String productId) throws IOException {
        return productService.getStockByProductId(productId);
    }
}