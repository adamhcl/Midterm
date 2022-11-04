package com.adam.spring.security.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adam.spring.security.login.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByPublished(boolean published);
  List<Product> findByNameContaining(String title);
}