package com.example.crudd.doingetl.repository;

import com.example.crudd.doingetl.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
Product findByName(String name);
}
