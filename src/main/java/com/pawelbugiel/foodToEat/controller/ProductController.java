package com.pawelbugiel.foodToEat.controller;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.service.ProductService;
import com.pawelbugiel.foodToEat.validators.ProductProperties;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//************** CREATE *************

    @PostMapping(value = "/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductWriteDto productWriteDto, UriComponentsBuilder uriBuilder) {
        ProductDto resultProductDto = productService.createProduct(productWriteDto);
        String resourceUri = getResourceUri(uriBuilder, resultProductDto);
        return ResponseEntity.status(200)
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
    public ResponseEntity<?> findProductsByPartialName(@RequestParam String partialName,
                                                       @RequestParam(required = false) String page,
                                                       @RequestParam(required = false) Sort.Direction sort) {
        List<ProductDto> productDtos = productService.findProductsByPartialName(partialName, page, sort);
        if (productDtos.isEmpty())
            return new ResponseEntity<>("No products with given partial name : " + partialName, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(200)
                .body(productDtos);
    }

    @GetMapping("/products/expired")
    public ResponseEntity<?> findProductsWithExpiredDate(@RequestParam(required = false) String page, Sort.Direction sort) {
        List<ProductDto> foundProducts = productService.findProductsWithExpiredDate(page, sort);
        if (foundProducts.isEmpty())
            return new ResponseEntity<>("No products with expired date found", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(200)
                .body(foundProducts);
    }

    //************** UPDATE *************

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductDto productDto) {
        ProductDto updatedProductDto = productService.updateProduct(productDto);
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

