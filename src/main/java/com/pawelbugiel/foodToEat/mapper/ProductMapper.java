package com.pawelbugiel.foodToEat.mapper;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductMapper {
    public ProductDto toProductDto(Product product){
        String name = product.getName();
        double quantity = product.getQuantity();
        LocalDate expiryDate = product.getExpiryDate();

        return new ProductDto(name, quantity,expiryDate);
    }

    public Product toProduct(ProductDto productDto){
        String name = productDto.getName();
        double quantity = productDto.getQuantity();
        LocalDate expiryDate = productDto.getExpiryDate();

        return new Product(name, quantity, expiryDate);
    }
}
