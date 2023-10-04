package com.pawelbugiel.foodToEat.mapper;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductReadMapper {

    public static ProductDto mapToProductReadDto(Product product){
        return ProductDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withId(product.getId())
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }

}
