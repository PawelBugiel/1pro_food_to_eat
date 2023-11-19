package com.pawelbugiel.foodToEat.mappers;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

class ProductAndProductDtoMapperTest {

    private final UUID tempUUID = UUID.randomUUID();
    private final LocalDate expiryDate_1 = LocalDate.of(2077, 7, 7);
    private final Product product_1 = Product.ProductBuilder.aProduct()
            .withName("Water")
            .withQuantity(5)
//            .withExpiryDate(expiryDate_1)
            .withId(tempUUID)
            .build();

    @Test
    @DisplayName("Product Write Dto correctly mapped to Product")
    void mapProductWriteDtoToProduct() {
        // Given
        ProductWriteDto productWriteDto = ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName("Water")
                .withQuantity(5)
//                .withExpiryDate(LocalDate.of(2077, 7, 7))
                .build();

        // When
        Product product = ProductAndProductDtoMapper.mapProductWriteDtoToProduct(productWriteDto);

        // Then
        Assertions.assertThat(productWriteDto.getName()).isEqualTo(product.getName());
        Assertions.assertThat(productWriteDto.getQuantity()).isEqualTo(5);
//        Assertions.assertThat(productWriteDto.getExpiryDate()).isEqualTo(expiryDate_1);
    }

    @Test
    @DisplayName("Product correctly mapped to Product Dto")
    void mapProductToProductDto() {
        // When
        ProductDto productDto = ProductAndProductDtoMapper.mapProductToProductDto(product_1);

        // Then
        Assertions.assertThat(productDto.getQuantity()).isEqualTo(product_1.getQuantity());
//        Assertions.assertThat(productDto.getExpiryDate()).isEqualTo(expiryDate_1);
        Assertions.assertThat(productDto.getName()).isEqualTo("Water");
        Assertions.assertThat(productDto.getId()).isEqualTo(tempUUID);
    }

    @Test
    @DisplayName("Product correctly mapped to Product Write Dto")
    void mapProductToProductWriteDto() {
        // When
        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(product_1);

        // Then
        Assertions.assertThat(productWriteDto.getName()).isEqualTo("Water");
//        Assertions.assertThat(productWriteDto.getExpiryDate()).isEqualTo(expiryDate_1);
        Assertions.assertThat(productWriteDto.getQuantity()).isEqualTo(5);
}
}
