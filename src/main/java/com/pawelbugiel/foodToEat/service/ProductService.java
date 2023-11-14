package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    // --- CREATE

//    ProductWriteDto createProduct(ProductWriteDto productWriteDto);
    ProductWriteDto createProduct(ProductWriteDto productWriteDto);

    // --- READ

    List<ProductDto> getAllProducts();

    Optional<ProductDto> getProductById(UUID id);




}
