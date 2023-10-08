/*package com.pawelbugiel.foodToEat.mapper;

import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.stereotype.Component;

@Component
public class MapperToProductWriteDto {

    public static ProductWriteDto mapProductToProductWriteDto(Product product) {
        return ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }

}*/
