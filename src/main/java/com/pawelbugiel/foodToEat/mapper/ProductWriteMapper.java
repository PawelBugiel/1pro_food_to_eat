package com.pawelbugiel.foodToEat.mapper;

import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductWriteMapper {

    public static Product mapProductDtoToProduct(ProductWriteDto productWriteDto){
        return Product.ProductBuilder.aProduct()
                .withName(productWriteDto.getName())
                .withQuantity(productWriteDto.getQuantity())
                .withExpiryDate(productWriteDto.getExpiryDate())
                .build();
    }
}
