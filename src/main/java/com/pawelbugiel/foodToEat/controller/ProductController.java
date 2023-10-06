package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mapper.ProductReadMapper;
import com.pawelbugiel.foodToEat.mapper.ProductWriteDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.service.ProductService;
import com.pawelbugiel.foodToEat.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pawelbugiel.foodToEat.mapper.ProductWriteMapper.mapProductDtoToProduct;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ObjectValidator<ProductWriteDto> validator;

    @Autowired
    public ProductController(ProductService productService, ProductWriteDtoMapper productWriteDtoMapper, ObjectValidator<ProductWriteDto> validator) {
        this.productService = productService;
        this.validator = validator;
    }


    // @ResponseStatus can be used to return http statuses
//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody ProductWriteDto productWriteDto) {
        validator.validate(productWriteDto);


        Product product = mapProductDtoToProduct(productWriteDto);

        productService.createProduct(product);

        return ResponseEntity.ok().body("");
    }


    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(ProductReadMapper::mapToProductReadDto)
                .toList();
    }
}
