package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

}
