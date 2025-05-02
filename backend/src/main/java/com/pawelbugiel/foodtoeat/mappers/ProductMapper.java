package com.pawelbugiel.foodtoeat.mappers;

import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

    ProductRequest toProductRequest(Product product);
}

