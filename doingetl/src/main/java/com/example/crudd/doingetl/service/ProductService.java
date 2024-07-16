package com.example.crudd.doingetl.service;

import com.example.crudd.doingetl.entity.Product;
import com.example.crudd.doingetl.entity.Product2;
import com.example.crudd.doingetl.repository.Product2Repository;
import com.example.crudd.doingetl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Product2Repository product2Repository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }


    //    Following method is GET
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductByID(int id){
        return productRepository.findById(id).orElse(null);
    }
    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return " Record Removed !!"+id;
    }

    public Product updateProduct(Product product){
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        return productRepository.save(existingProduct);
    }

    public List<Product2> addToProduct2(List<Product> products) {

        if (product2Repository.count() > 0) {
            // Data already exists, return an empty list or handle as per your requirement
            return Collections.emptyList();
        }

        List<Product2> product2List = new ArrayList<>();

        for (Product product : products) {
            Product2 product2 = new Product2();
            product2.setName(product.getName());
            product2.setPrice(product.getPrice());
            product2.setQuantity(product.getQuantity());

            product2List.add(product2Repository.save(product2));
        }

        return product2List;
    }
}
