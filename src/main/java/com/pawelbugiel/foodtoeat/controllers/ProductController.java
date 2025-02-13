package com.pawelbugiel.foodtoeat.controllers;

import com.pawelbugiel.foodtoeat.dtos.ProductDTO;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.QueryParams;
import com.pawelbugiel.foodtoeat.services.ProductService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ProductDTO> findAllProducts(@Valid @Nullable QueryParams queryParams) {
        Pageable pageable = PageRequest.of(
                Integer.parseInt(queryParams.getPage()), 10, // Liczba wyników na stronie (10)
                Sort.by(queryParams.getSortDirection(), queryParams.getSortBy())
        );
        return productService.findAllProducts(queryParams, pageable);
    }

    @GetMapping("products/id/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable String id) {
        ProductDTO productDTO = productService.findProductById(id);
        return ResponseEntity.status(200)
                .body(productDTO);
    }

    @GetMapping("/products/partial-name")
    public ResponseEntity<Page<ProductDTO>> findProductsByPartialName(
            @RequestParam @Pattern(regexp = PARTIAL_NAME_REGEX) String partialName,
            Pageable pageable) {

        Page<ProductDTO> productDtos = productService.findProductsByPartialName(partialName, pageable);
        return ResponseEntity.ok(productDtos);
    }

//    @GetMapping("/products/partial-name")
//    public ResponseEntity<?> findProductsByPartialName(@RequestParam @Pattern(regexp = PARTIAL_NAME_REGEX) String partialName,
//                                                       @RequestBody QueryParams queryParams) {
//        var productDtos = productService.findProductsByPartialName(
//                partialName,
//                queryParams
//        );
//
//        return ResponseEntity.status(200)
//                .body(productDtos);
//    }

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

