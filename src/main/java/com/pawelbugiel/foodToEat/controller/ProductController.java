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
@CrossOrigin("http://localhost:8080/")
public class ProductController {

    private final ProductService productService;
//    private Integer page;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//************** CREATE *************

    @PostMapping(value = "/product")
    public ResponseEntity<ProductWriteDto> createProduct(@RequestBody @Valid ProductWriteDto productWriteDto) {
        ProductWriteDto resultProductWriteDto = productService.createProduct(productWriteDto);
        return new ResponseEntity<>(resultProductWriteDto, HttpStatus.CREATED);
    }

//************** FIND *************

    @GetMapping("/products")
    public List<ProductDto> findAllProducts(@RequestParam(required = false) String page,
                                            @RequestParam(required = false) Sort.Direction sort) {
        return productService.findAllProducts(page, sort);
    }

    @GetMapping("product/id/{id}")
    public ResponseEntity<?> findProductById(@PathVariable String id) {
        Optional<ProductDto> productDtoOptional = productService.findProductById(id);
        if (productDtoOptional.isEmpty())
            return new ResponseEntity<>("Product not found\n", HttpStatusCode.valueOf(404));
        return new ResponseEntity<>(productDtoOptional.get(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/product/partial-name")
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
    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(productDto), HttpStatus.ACCEPTED);
    }

//************** DELETE *************

    @DeleteMapping("product")
    public ResponseEntity<?> deleteProduct(@RequestParam String id) {
        if (productService.deleteProductById(id))
            return new ResponseEntity<>("Product with given id successfully deleted. Id : " + id, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>("Product with given id not deleted. Id : " + id, HttpStatus.NOT_FOUND);
    }
}

