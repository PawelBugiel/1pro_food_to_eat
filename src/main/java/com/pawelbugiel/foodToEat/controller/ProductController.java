package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
@Validated
public class ProductController {

    private final ProductService productService;
//    private Integer page;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ProductDto createProduct(@RequestBody @Valid ProductWriteDto productWriteDto) {
        ProductDto tempProductWriteDto = productService.createProduct(productWriteDto);
        return tempProductWriteDto;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        return productService.getAllProducts(page, sort);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("product/{id}")
    public ResponseEntity<?> findProductById(@PathVariable String id) {
        Optional<ProductDto> productDtoOptional = productService.getProductById(id);
        if (productDtoOptional.isEmpty()) {
            return new ResponseEntity<>("Product not found\n", HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(productDtoOptional.get(), HttpStatusCode.valueOf(200));
    }
}
