package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductRequest;
import com.pawelbugiel.foodToEat.dto.ProductResponse;
import com.pawelbugiel.foodToEat.model.ProductProperties;
import com.pawelbugiel.foodToEat.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v1")
@Validated
public class ProductController {

    private final ProductService productService;
    private static final String PARTIAL_NAME_REGEX = "^[a-zA-Z0-9].*";

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//************** CREATE *************

    @PostMapping(value = "/products")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest,
                                                         UriComponentsBuilder uriBuilder) {
        ProductResponse resultProductResponse = productService.createProduct(productRequest);
        String resourceUri = getResourceUri(uriBuilder, resultProductResponse);
        return ResponseEntity.status(201)
                .header("Location", resourceUri)
                .body(resultProductResponse);
    }

    private static String getResourceUri(UriComponentsBuilder uriBuilder, ProductResponse resultProductResponse) {
        return uriBuilder.path("/api/products/{id}")
                .buildAndExpand(resultProductResponse.getId())
                .toUriString();
    }

//************** READ *************

    @GetMapping("/products")
    public List<ProductResponse> findAllProducts(@RequestParam(required = false) String page,
                                                 @RequestParam(required = false) Sort.Direction sortDirection,
                                                 @RequestParam(required = false) ProductProperties sortBy) {
        return productService.findAllProducts(page, sortDirection, sortBy);
    }

    @GetMapping("products/id/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable String id) {
        ProductResponse productResponse = productService.findProductById(id);
        return ResponseEntity.status(200)
                .body(productResponse);
    }

    @GetMapping("/products/partial-name")
    public ResponseEntity<?> findProductsByPartialName(@RequestParam @Pattern(regexp = PARTIAL_NAME_REGEX) String partialName,
                                                       @RequestParam(required = false) String page,
                                                       @RequestParam(required = false) Sort.Direction sortDirection,
                                                       @RequestParam(required = false) ProductProperties sortBy) {
        var productDtos = productService.findProductsByPartialName(partialName, page, sortDirection, sortBy);

        return ResponseEntity.status(200)
                .body(productDtos);
    }

    @GetMapping("/products/expired")
    public ResponseEntity<?> findProductsWithExpiredDate(@RequestParam(required = false) String page,
                                                         @RequestParam(required = false) Sort.Direction sortDirection) {
        var foundProducts = productService.findProductsWithExpiredDate(page, sortDirection);

        return ResponseEntity.status(200)
                .body(foundProducts);
    }

    //************** UPDATE *************

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestParam String id,
                                           @RequestBody @Valid ProductResponse productResponse) {
        ProductResponse updatedProductResponse = productService.updateProduct(id, productResponse);
        return ResponseEntity.status(200)
                .body(updatedProductResponse);
    }

//************** DELETE *************

    @DeleteMapping("products/id/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable String id) {
        ProductResponse deletedProductResponse = productService.deleteProductById(id);
        return ResponseEntity.status(200)
                .body(deletedProductResponse);
    }
}

