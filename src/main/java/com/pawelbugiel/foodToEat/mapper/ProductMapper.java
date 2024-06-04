package com.pawelbugiel.foodToEat.mapper;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product toProduct(ProductWriteDto productWriteDto) {
        return Product.ProductBuilder.aProduct()
                .withName(productWriteDto.getName())
                .withQuantity(productWriteDto.getQuantity())
                .withExpiryDate(productWriteDto.getExpiryDate())
                .build();
    }

    public static ProductDto toProductDto(Product product) {
        return ProductDto.ProductDtoBuilder.aProductDto()
                .withId(product.getId())
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }

    public static ProductWriteDto toProductWriteDto(Product product){
        return ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }
}
