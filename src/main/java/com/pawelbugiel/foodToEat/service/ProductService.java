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

    // --- FIND

    List<ProductDto> findAllProducts(String page, Sort.Direction sort);

    Optional<ProductDto> findProductById(String id);

    List<ProductDto> findProductsByPartialName(String partialName,  String page, Sort.Direction sort);

    List<ProductDto> findProductsWithExpiryDateUntilToday(String page, Sort.Direction sort);




}
