package com.pawelbugiel.foodToEat.mappers;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductAndProductDtoMapper {

    public static Product mapProductWriteDtoToProduct(ProductWriteDto productWriteDto) {
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

    public static ProductWriteDto mapProductToProductWriteDto(Product product){
        return ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }

    public static Product mapProductDtoToProduct(ProductDto productDto){
        return Product.ProductBuilder.aProduct()
                .withId(productDto.getId())
                .withName(productDto.getName())
                .withQuantity(productDto.getQuantity())
                .withExpiryDate(productDto.getExpiryDate())
                .build();
    }

}
