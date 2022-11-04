package com.adam.spring.security.login.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adam.spring.security.login.models.Product;
import com.adam.spring.security.login.repository.ProductRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  //GET request, to READ (view) all products by USER and ADMIN
  @GetMapping("/products")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
    try {
      List<Product> products = new ArrayList<Product>();

      if (name == null)
        productRepository.findAll().forEach(products::add);
      else
        productRepository.findByNameContaining(name).forEach(products::add);

      if (products.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  //GET request, to READ (view) a specific product via ID by USER or ADMIN
  @GetMapping("/products/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
    Optional<Product> productData = productRepository.findById(id);

    if (productData.isPresent()) {
      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  //POST REQUEST, for ADMIN to CREATE one or more products
  @PostMapping("/products/create")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    try {
      Product _product = productRepository
          .save(new Product(product.getName(), product.getDescription(), false));
      return new ResponseEntity<>(_product, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //PUT request, for ADMIN to UPDATE a specific product via ID
  @PutMapping("/products/update/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
    Optional<Product> productData = productRepository.findById(id);

    if (productData.isPresent()) {
      Product _product = productData.get();
      _product.setName(product.getName());
      _product.setDescription(product.getDescription());
      _product.setPublished(product.isPublished());
      return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  //DELETE request, for ADMIN to DELETE a specific product via ID
  @DeleteMapping("/products/delete/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
    try {
      productRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //DELETE request, for ADMIN to DELETE all products
  @DeleteMapping("/products/deleteAll")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<HttpStatus> deleteAllProducts() {
    try {
      productRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
 
  //GET request, to READ (view) all published products for USER and ADMIN
  @GetMapping("/products/published")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public ResponseEntity<List<Product>> findByPublished() {
    try {
      List<Product> products = productRepository.findByPublished(true);

      if (products.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}