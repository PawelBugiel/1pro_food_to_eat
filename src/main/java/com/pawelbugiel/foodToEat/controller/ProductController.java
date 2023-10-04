package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mapper.ProductReadMapper;
import com.pawelbugiel.foodToEat.mapper.ProductWriteDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pawelbugiel.foodToEat.mapper.ProductWriteMapper.mapProductDtoToProduct;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService, ProductWriteDtoMapper productWriteDtoMapper) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody @Valid ProductWriteDto productWriteDto) {
        Product product = mapProductDtoToProduct(productWriteDto);
        productService.createProduct(product);
    }


    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(ProductReadMapper::mapToProductReadDto)
                .toList();
    }
}
