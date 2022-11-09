package com.adam.midterm.security.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adam.midterm.models.Product;

import java.util.Optional;

public interface ProductDetailsImpl {

    Optional<Product> findById(Long id);

    Page<Product> findAllProductsPageable(Pageable pageable);

}