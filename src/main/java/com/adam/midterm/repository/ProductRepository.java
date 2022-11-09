package com.adam.midterm.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adam.midterm.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByPublished(boolean published);
  List<Product> findByNameContaining(String title);
  Optional<Product> findById(Long id);
  
 
  

}