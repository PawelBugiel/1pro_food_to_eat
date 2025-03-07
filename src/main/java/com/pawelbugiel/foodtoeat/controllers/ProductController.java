package com.pawelbugiel.foodtoeat.controllers;

import com.pawelbugiel.foodtoeat.dtos.ProductDTO;
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
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid  ProductRequest productRequest,
                                                    UriComponentsBuilder uriBuilder) {
        ProductDTO resultProductDTO = productService.createProduct(productRequest);
        String resourceUri = getResourceUri(uriBuilder, resultProductDTO);
        return ResponseEntity.status(201)
                .header("Location", resourceUri)
                .body(resultProductDTO);
    }

    private static String getResourceUri(UriComponentsBuilder uriBuilder, ProductDTO resultProductDTO) {
        return uriBuilder.path("/api/products/{id}")
                .buildAndExpand(resultProductDTO.getId())
                .toUriString();
    }

//************** READ *************

    @GetMapping("/products")
    public Page<ProductDTO> findAllProducts(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction sortDirection) {


        return productService.findAllProducts(page, pageSize, sortBy, sortDirection);
    }

    @GetMapping("products/id/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable String id) {
        ProductDTO productDTO = productService.findProductById(id);
        return ResponseEntity.status(200)
                .body(productDTO);
    }

    @GetMapping("/products/partial-name")
    public ResponseEntity<?> findProductsByPartialName(
            @RequestParam @Pattern(regexp = PARTIAL_NAME_REGEX) String partialName,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction sortDirection) {

        Page<ProductDTO> productDtos = productService.findProductsByPartialName(partialName, page, pageSize, sortBy, sortDirection);

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/expired")
    public ResponseEntity<?> findProductsWithExpiredDate(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction sortDirection) {

        var foundProducts = productService.findProductsWithExpiredDate(page, pageSize, sortBy, sortDirection);
        return ResponseEntity.status(200)
                .body(foundProducts);
    }

    //************** UPDATE *************

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestParam String id,
                                           @RequestBody @Valid ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.updateProduct(id, productDTO);
        return ResponseEntity.status(200)
                .body(updatedProductDTO);
    }

//************** DELETE *************

    @DeleteMapping("products/id/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable String id) {
        ProductDTO deletedProductDTO = productService.deleteProductById(id);
        return ResponseEntity.status(200)
                .body(deletedProductDTO);
    }
}

