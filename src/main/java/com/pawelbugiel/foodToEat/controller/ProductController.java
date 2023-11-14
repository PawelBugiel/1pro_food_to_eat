package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.service.ProductService;
import com.pawelbugiel.foodToEat.validators.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ObjectValidator<ProductWriteDto> validator;

    @Autowired
    public ProductController(ProductService productService, ObjectValidator<ProductWriteDto> validator) {
        this.productService = productService;
        this.validator = validator;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ResponseEntity<ProductWriteDto> createProduct(@RequestBody ProductWriteDto productWriteDto) {
        validator.validate(productWriteDto);
        ProductWriteDto tempProductWriteDto = productService.createProduct(productWriteDto);
        return new ResponseEntity<>(tempProductWriteDto, HttpStatus.OK);
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("product/{id}")
    public ResponseEntity<?> findProductById(@PathVariable UUID id) {
        Optional<ProductDto> productDtoOptional = productService.getProductById(id);
        return (productDtoOptional.isEmpty()) ?
                new ResponseEntity<>("Product not found\n", HttpStatusCode.valueOf(404)) :
                new ResponseEntity<>(productDtoOptional.get(), HttpStatusCode.valueOf(200));
    }
}
