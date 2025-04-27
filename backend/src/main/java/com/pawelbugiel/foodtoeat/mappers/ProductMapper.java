package com.pawelbugiel.foodtoeat.mappers;

import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


//    public  Product toProduct2(ProductRequest productRequest) {
//        return Product.ProductBuilder.aProduct()
//                .withName(productRequest.getName())
//                .withQuantity(productRequest.getQuantity())
//                .withExpiryDate(productRequest.getExpiryDate())
//                .build();
//    }

    public  Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .expiryDate(productRequest.getExpiryDate())
                .build();
    }

//    public  ProductResponse toProductResponse2(Product product) {
//        return ProductResponse.ProductDTOBuilder.aProductDto()
//                .withId(product.getId())
//                .withName(product.getName())
//                .withQuantity(product.getQuantity())
//                .withExpiryDate(product.getExpiryDate())
//                .build();
//    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .expiryDate(product.getExpiryDate())
                .build();
    }

//    public  ProductRequest toProductRequest3(Product product) {
//        return ProductRequest.ProductRequestBuilder.aProductWriteDto()
//                .withName(product.getName())
//                .withQuantity(product.getQuantity())
//                .withExpiryDate(product.getExpiryDate())
//                .build();
//    }
    public  ProductRequest toProductRequest(Product product) {
        return ProductRequest.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .expiryDate(product.getExpiryDate())
                .build();
    }
}

