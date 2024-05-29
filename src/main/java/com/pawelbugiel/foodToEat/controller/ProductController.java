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
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductWriteDto productWriteDto) {
        ProductDto resultProductDto = productService.createProduct(productWriteDto);
        return new ResponseEntity<>(resultProductDto, HttpStatus.CREATED);
    }

//************** READ *************

    @GetMapping("/products")
    public List<ProductDto> findAllProducts(@RequestParam(required = false)  String page,
                                            @RequestParam(required = false) Sort.Direction sort) {
        return productService.findAllProducts(page, sort);
    }

    @GetMapping("products/id/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable String id) {
        ProductDto productDto = productService.findProductById(id);
        return new ResponseEntity<>(productDto, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/products/partial-name")
    public ResponseEntity<?> findProductsByPartialName(@RequestParam String partialName,
                                                       @RequestParam(required = false) String page, Sort.Direction sort) {
        List<ProductDto> productDtos = productService.findProductsByPartialName(partialName, page, sort);
        if (productDtos.isEmpty())
            return new ResponseEntity<>("No products with given partial name : " + partialName, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productDtos, HttpStatus.FOUND);
    }

    @GetMapping("/products/expired")
    public ResponseEntity<?> findProductsWithExpiredDate(@RequestParam(required = false) String page, Sort.Direction sort) {
        List<ProductDto> foundProducts = productService.findProductsWithExpiredDate(page, sort);
        if (foundProducts.isEmpty())
            return new ResponseEntity<>("No products with expired date found", HttpStatus.NOT_FOUND);  // change status-code 404
        return new ResponseEntity<>(foundProducts, HttpStatus.FOUND);
    }

    //************** UPDATE *************

    // including ID in Path: This aligns more with RESTful conventions, where the resource identifier (ID) is part of the URL. It makes the URL more descriptive and is often used for update operations.
    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(productDto), HttpStatus.ACCEPTED);
    }

//************** DELETE *************

    @DeleteMapping("products/id/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String id) {
        return new ResponseEntity<>(productService.deleteProductById(id) , HttpStatusCode.valueOf(200));
    }
}

