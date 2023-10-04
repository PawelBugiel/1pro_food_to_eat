package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.model.Product;

import java.util.List;

public interface ProductService {

    // --- CREATE

    void createProduct(Product product);


    // --- READ

    List<Product> getAllProducts();




}
