package com.pawelbugiel.foodtoeat.mappers;

import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product toProduct(ProductRequest productRequest) {
        return Product.ProductBuilder.aProduct()
                .withName(productRequest.getName())
                .withQuantity(productRequest.getQuantity())
                .withExpiryDate(productRequest.getExpiryDate())
                .build();
    }

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.ProductResponseBuilder.aProductDto()
                .withId(product.getId())
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }

    public static ProductRequest toProductRequest(Product product){
        return ProductRequest.ProductRequestBuilder.aProductWriteDto()
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }
}
