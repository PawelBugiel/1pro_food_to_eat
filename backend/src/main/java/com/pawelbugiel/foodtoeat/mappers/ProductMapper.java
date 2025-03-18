package com.pawelbugiel.foodtoeat.mappers;

import com.pawelbugiel.foodtoeat.dtos.ProductDTO;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public  Product toProduct(ProductRequest productRequest) {
        return Product.ProductBuilder.aProduct()
                .withName(productRequest.getName())
                .withQuantity(productRequest.getQuantity())
                .withExpiryDate(productRequest.getExpiryDate())
                .build();
    }

    public  ProductDTO toProductResponse(Product product) {
        return ProductDTO.ProductDTOBuilder.aProductDto()
                .withId(product.getId())
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }

    public  ProductRequest toProductRequest(Product product) {
        return ProductRequest.ProductRequestBuilder.aProductWriteDto()
                .withName(product.getName())
                .withQuantity(product.getQuantity())
                .withExpiryDate(product.getExpiryDate())
                .build();
    }
}

