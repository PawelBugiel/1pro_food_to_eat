package com.pawelbugiel.foodtoeat.controllers;

import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/products")
@Validated
public class ProductController {

    private final ProductService productService;
    private static final String PARTIAL_NAME_REGEX = "^[a-zA-Z0-9].*";

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//************** CREATE *************

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest,
                                                         UriComponentsBuilder uriBuilder) {
        ProductResponse resultProductResponse = productService
                .createProduct(productRequest);

        String resourceUri = getResourceUri(uriBuilder, resultProductResponse);

        return ResponseEntity
                .status(201)
                .header("Location", resourceUri)
                .body(resultProductResponse);
    }

    private static String getResourceUri(UriComponentsBuilder uriBuilder, ProductResponse resultProductResponse) {
        return uriBuilder
                .path("/api/product/products/{id}")
                .buildAndExpand(resultProductResponse.id())
                .toUriString();
    }

//************** READ *************

    @GetMapping
    public Page<ProductResponse> findAllProducts(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction sortDirection) {

        return productService
                .findAllProducts(page, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable UUID id) {
        ProductResponse productResponse = productService
                .findProductById(id);

        return ResponseEntity
                .ok(productResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findProductsByPartialName(
            @RequestParam @Pattern(regexp = PARTIAL_NAME_REGEX) String partialName,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction sortDirection) {

        Page<ProductResponse> productResponses = productService
                .findProductsByPartialName(partialName, page, pageSize, sortBy, sortDirection);

        return ResponseEntity
                .ok(productResponses);
    }

    @GetMapping("/expired")
    public ResponseEntity<Page<ProductResponse>> findProductsWithExpiredDate(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction sortDirection) {

        Page<ProductResponse> foundProducts = productService
                .findProductsWithExpiredDate(page, pageSize, sortBy, sortDirection);

        return ResponseEntity
                .ok(foundProducts);
    }

    //************** UPDATE *************

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id,
                                           @RequestBody @Valid ProductResponse productResponse) {
        ProductResponse updatedProductResponse = productService
                .updateProduct(id, productResponse);
        return ResponseEntity
                .status(200)
                .body(updatedProductResponse);
    }

//************** DELETE *************

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable UUID id) {
        ProductResponse deletedProductResponse = productService
                .deleteProductById(id);

        return ResponseEntity
                .status(200)
                .body(deletedProductResponse);
    }
}

