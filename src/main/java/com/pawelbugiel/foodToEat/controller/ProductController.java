package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
