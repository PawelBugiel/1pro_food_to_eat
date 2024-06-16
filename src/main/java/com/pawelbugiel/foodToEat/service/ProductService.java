package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductRequest;
import com.pawelbugiel.foodToEat.dto.ProductResponse;
import com.pawelbugiel.foodToEat.model.ProductProperties;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

//************** CREATE *************

    ProductResponse createProduct(ProductRequest productRequest);

//************** READ *************

    List<ProductResponse> findAllProducts(String page, Sort.Direction sort, ProductProperties sortBy);

    ProductResponse findProductById(String id);

    List<ProductResponse> findProductsByPartialName(String partialName, String page, Sort.Direction sort, ProductProperties sortBy);

    List<ProductResponse> findProductsWithExpiredDate(String page, Sort.Direction sort);

//************** UPDATE *************

    ProductResponse updateProduct(String id, ProductResponse productResponse);

//************** DELETE *************

    ProductResponse deleteProductById(String id);

}
