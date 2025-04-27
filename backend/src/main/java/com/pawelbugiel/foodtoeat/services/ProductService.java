package com.pawelbugiel.foodtoeat.services;

import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProductService {

//************** CREATE *************

    ProductResponse createProduct(ProductRequest productRequest);

//************** READ *************

    Page<ProductResponse> findAllProducts(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection);

    ProductResponse findProductById(UUID id);

    Page<ProductResponse> findProductsByPartialName(String partialName, int page, Integer pageSize, String sortBy, Sort.Direction sortDirection);

    List<ProductResponse> findProductsWithExpiredDate(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection);

//************** UPDATE *************

    ProductResponse updateProduct(UUID id, ProductResponse productResponse);

//************** DELETE *************

    ProductResponse deleteProductById(UUID id);

}
