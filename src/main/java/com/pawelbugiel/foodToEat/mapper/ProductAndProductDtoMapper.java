package com.pawelbugiel.foodToEat.mapper;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductAndProductDtoMapper {

    public static Product mapProductDtoToProduct(ProductWriteDto productWriteDto) {
        return Product.ProductBuilder.aProduct()
                .withName(productWriteDto.getName())
                .withQuantity(productWriteDto.getQuantity())
                .withExpiryDate(productWriteDto.getExpiryDate())
                .build();
    }

    public static ProductDto mapProductToProductDto(Product product) {
        return ProductDto.ProductDtoBuilder.aProductDto()
                .withId(product.getId())
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }
}
