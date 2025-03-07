package com.pawelbugiel.foodtoeat.services;

import com.pawelbugiel.foodtoeat.dtos.ProductDTO;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

//************** CREATE *************

    ProductDTO createProduct(ProductRequest productRequest);

//************** READ *************

    // Jest to interfejs zdefiniowany w Spring Data.
    //Zawiera nie tylko dane, ale także metadane dotyczące paginacji, takie jak:
    //
    //    Liczba wszystkich elementów (getTotalElements()),
    //    Liczba stron (getTotalPages()),
    //    Bieżąca strona (getNumber()),
    //    Rozmiar strony (getSize()).
    Page<ProductDTO> findAllProducts(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection);

    ProductDTO findProductById(String id);

    Page<ProductDTO> findProductsByPartialName(String partialName, int page, Integer pageSize, String sortBy, Sort.Direction sortDirection);

    List<ProductDTO> findProductsWithExpiredDate(int page, Integer pageSize, String sortBy, Sort.Direction sortDirection);

//************** UPDATE *************

    ProductDTO updateProduct(String id, ProductDTO productDTO);

//************** DELETE *************

    ProductDTO deleteProductById(String id);

}
