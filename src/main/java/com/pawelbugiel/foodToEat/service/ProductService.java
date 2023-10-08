package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;

import java.util.List;

public interface ProductService {

    // --- CREATE

    ProductWriteDto createProduct(ProductWriteDto productWriteDto);


    // --- READ

    List<ProductDto> getAllProducts();




}
