/*
package com.pawelbugiel.foodToEat.mappers;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ProductAndProductDtoMapperTest {

    @Test
    void mapProductWriteDtoToProduct() {
        // Arrange
        ProductWriteDto productWriteDto = ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName("Water")
                .withQuantity(5)
                .withExpiryDate(LocalDate.of(2077,7,7))
                .build();

        // Act
        Product product = ProductAndProductDtoMapper.mapProductWriteDtoToProduct(productWriteDto);

        // Assert
        Assertions.assertThat(productWriteDto.getName()).isEqualTo(product.getName());
    }

    @Test
    void mapProductToProductDto() {
        // Arrange
        Product product = Product.ProductBuilder.aProduct()
                .withName("Water")
                .withQuantity(5)
                .withExpiryDate(LocalDate.of(2077,7,7))
                .withId(44L)
                .build();

        // Act
        ProductDto productDto = ProductAndProductDtoMapper.mapProductToProductDto(product);

        // Assert
        Assertions.assertThat(productDto.getQuantity()).isEqualTo(product.getQuantity());
    }

    @Test
    void mapProductToProductWriteDto() {
//        fail("this test has yet to be implemented");
        // Arrange
        Product product = Product.ProductBuilder.aProduct()
                .withName("Water")
                .withQuantity(5)
                .withExpiryDate(LocalDate.of(2077,7,7))
                .withId(44L)
                .build();

        // Act
        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(product);

        // Assert
        Assertions.assertThat(productWriteDto.getExpiryDate()).isEqualTo(product.getExpiryDate());
    }
}
*/
