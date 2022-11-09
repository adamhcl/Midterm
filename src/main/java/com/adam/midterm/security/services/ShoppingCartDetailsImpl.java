package com.adam.midterm.security.services;

import java.math.BigDecimal;
import java.util.Map;

import com.adam.midterm.exception.NotEnoughProductsInStockException;
import com.adam.midterm.models.Product;

public interface ShoppingCartDetailsImpl {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
