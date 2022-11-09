package com.adam.midterm.exception;

import com.adam.midterm.models.Product;

@SuppressWarnings("serial")
public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Product product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getName(), product.getQuantity()));
    }

}