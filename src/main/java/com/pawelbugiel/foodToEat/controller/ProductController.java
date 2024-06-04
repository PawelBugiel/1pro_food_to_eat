package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.service.ProductService;
import com.pawelbugiel.foodToEat.model.ProductProperties;
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
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductWriteDto productWriteDto,
                                                    UriComponentsBuilder uriBuilder) {
        ProductDto resultProductDto = productService.createProduct(productWriteDto);
        String resourceUri = getResourceUri(uriBuilder, resultProductDto);
        return ResponseEntity.status(201)
                .header("Location", resourceUri)
                .body(resultProductDto);
    }

    private static String getResourceUri(UriComponentsBuilder uriBuilder, ProductDto resultProductDto) {
        return uriBuilder.path("/api/products/{id}")
                .buildAndExpand(resultProductDto.getId())
                .toUriString();
    }

//************** READ *************

    @GetMapping("/products")
    public List<ProductDto> findAllProducts(@RequestParam(required = false) String page,
                                            @RequestParam(required = false) Sort.Direction sortDirection,
                                            @RequestParam(required = false) ProductProperties sortBy) {
        return productService.findAllProducts(page, sortDirection, sortBy);
    }

    @GetMapping("products/id/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable String id) {
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity.status(200)
                .body(productDto);
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
                                           @RequestBody @Valid ProductDto productDto) {
        ProductDto updatedProductDto = productService.updateProduct(id, productDto);
        return ResponseEntity.status(200)
                .body(updatedProductDto);
    }

//************** DELETE *************

    @DeleteMapping("products/id/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String id) {
        ProductDto deletedProductDto = productService.deleteProductById(id);
        return ResponseEntity.status(200)
                .body(deletedProductDto);
    }
}

