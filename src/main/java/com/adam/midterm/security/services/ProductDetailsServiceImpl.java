package com.adam.midterm.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adam.midterm.models.Product;
import com.adam.midterm.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsImpl {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDetailsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}