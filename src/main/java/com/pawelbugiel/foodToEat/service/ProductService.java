package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    // --- CREATE

//    ProductWriteDto createProduct(ProductWriteDto productWriteDto);
    ProductDto createProduct(ProductWriteDto productWriteDto);

    // --- READ

    List<ProductDto> getAllProducts(String page, Sort.Direction sort);

    Optional<ProductDto> getProductById(String id);




}
