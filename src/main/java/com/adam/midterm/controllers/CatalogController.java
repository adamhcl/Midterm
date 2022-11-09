package com.adam.midterm.controllers;

import com.adam.midterm.models.Product;
import com.adam.midterm.security.services.ProductDetailsServiceImpl;
import com.adam.midterm.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CatalogController {

    private static final int INITIAL_PAGE = 0;

    private final ProductDetailsServiceImpl productService;

    @Autowired
    public CatalogController(ProductDetailsServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ModelAndView home(@RequestParam("page") Optional<Integer> page) {

        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> products = productService.findAllProductsPageable(new PageRequest(evalPage, 5));
        Pager pager = new Pager(products);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", products);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/home");
        return modelAndView;
    }

}
