package com.example.crudd.doingetl.controller;

import com.example.crudd.doingetl.entity.Product;
import com.example.crudd.doingetl.entity.Product2;
import com.example.crudd.doingetl.repository.ProductRepository;
import com.example.crudd.doingetl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Product2Controller {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    private boolean schedulingEnabled = true;


//    private List<Product> makeList(List<Product> product) {
//        return productRepository.saveAll(product);
//    }

    @PostMapping("/add2secondtable")
    @Scheduled(fixedRate = 15000)   // Runs after every 15 seconds
    public String saveproduct2() {
//        makeList(product);
        if(schedulingEnabled) {
            List<Product> products = productRepository.findAll();
            List<Product2> savedProducts = productService.addToProduct2(products);
            if (!savedProducts.isEmpty()) {
                return "Data saved successfully";
            } else {
                return "Failed to save data";
            }
        }else{
            return "Scheduling stopped because data already exists in product2 table.";
        }

    }
    // Method to stop scheduling
    public void stopScheduling() {
        schedulingEnabled = false;
    }

    // Method to start scheduling
    public void startScheduling() {
        schedulingEnabled = true;
    }

}
