package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.ProductProperties;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface ProductService {

//************** CREATE *************

    ProductDto createProduct(ProductWriteDto productWriteDto);

//************** READ *************

    List<ProductDto> findAllProducts(String page, Sort.Direction sort, ProductProperties sortBy);

    ProductDto findProductById(String id);

    List<ProductDto> findProductsByPartialName(String partialName, String page, Sort.Direction sort, ProductProperties sortBy);

    List<ProductDto> findProductsWithExpiredDate(String page, Sort.Direction sort);

//************** UPDATE *************

    ProductDto updateProduct(String id, ProductDto productDto);

//************** DELETE *************

    ProductDto deleteProductById(String id);

}
