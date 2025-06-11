package com.pawelbugiel.wastenofood.mappers;

import com.pawelbugiel.wastenofood.dtos.ProductRequest;
import com.pawelbugiel.wastenofood.dtos.ProductResponse;
import com.pawelbugiel.wastenofood.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

    ProductRequest toProductRequest(Product product);

    /**
     * Updates existing entity 'product' using data from 'productRequest'.
     * @param productRequest - DTO with new data
     * @param product - existing entity from DB
     */
    @Mapping(target = "id", ignore = true)
    void updateProductFromRequest(ProductRequest productRequest, @MappingTarget Product product);
}