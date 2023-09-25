package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.mapper.ProductMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody ProductDto productDto){
        Product product = productMapper.toProduct(productDto);
        productService.createProduct(product);
    }


    @GetMapping("/products")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts()
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }
}
