package com.pawelbugiel.foodtoeat.mappers;

import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

    ProductRequest toProductRequest(Product product);
}