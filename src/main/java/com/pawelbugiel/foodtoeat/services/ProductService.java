package com.pawelbugiel.foodtoeat.services;

import com.pawelbugiel.foodtoeat.dtos.ProductDTO;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.QueryParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

//************** CREATE *************

    ProductDTO createProduct(ProductRequest productRequest);

//************** READ *************

    Page<ProductDTO> findAllProducts(QueryParams params, Pageable pageable);

    ProductDTO findProductById(String id);

    List<ProductDTO> findProductsByPartialName(String partialName, QueryParams params);

    List<ProductDTO> findProductsWithExpiredDate(String page, Sort.Direction sort);

//************** UPDATE *************

    ProductDTO updateProduct(String id, ProductDTO productDTO);

//************** DELETE *************

    ProductDTO deleteProductById(String id);

}
