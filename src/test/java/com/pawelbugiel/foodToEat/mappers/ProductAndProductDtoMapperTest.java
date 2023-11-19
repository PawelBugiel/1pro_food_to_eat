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
            .withExpiryDate(expiryDate_1)
            .withId(tempUUID)
            .build();
    private final Product productWithDefaultValues = Product.ProductBuilder
            .aProduct()
            .build();
    private final ProductWriteDto productWriteDtoWithDefaultValues = ProductWriteDto.ProductWriteDtoBuilder
            .aProductWriteDto()
            .build();

    /*
    * Product Write Dto ---> Product
    * */

    @Test
    @DisplayName("Product Write Dto correctly mapped to Product")
    void testMapProductWriteDtoToProduct_whenPassValidDetails_returnsProperProduct() {
        // GIVEN
        ProductWriteDto productWriteDto = ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName("Water")
                .withQuantity(5)
                .withExpiryDate(LocalDate.of(2077, 7, 7))
                .build();

        // WHEN
        Product product = ProductAndProductDtoMapper.mapProductWriteDtoToProduct(productWriteDto);

        // THEN
        Assertions.assertThat(productWriteDto.getName()).isEqualTo(product.getName());
        Assertions.assertThat(productWriteDto.getQuantity()).isEqualTo(product.getQuantity());
        Assertions.assertThat(productWriteDto.getExpiryDate()).isEqualTo(product.getExpiryDate());
    }

    @Test
    @DisplayName("Map Product Write Dto with default values to a Product")
    void testMapProductWriteDtoToProduct_whenPassProductWriteDtoWithDefaultValues_returnsProductWithDefaultValues() {
        // GIVEN
        // WHEN
        Product product = ProductAndProductDtoMapper.mapProductWriteDtoToProduct(productWriteDtoWithDefaultValues);

        // THEN
        Assertions.assertThat(product.getName()).isNull();
        Assertions.assertThat(product.getQuantity()).isEqualTo(0);
        Assertions.assertThat(product.getExpiryDate()).isNull();
        Assertions.assertThat(product.getId()).isNull();
    }

    /*
    * Product ---> Product Dto
    * */

    @Test
    @DisplayName("Product correctly mapped to Product Dto")
    void testMapProductToProductDto_whenPassValidDetails_returnsProperProductDto() {
        // GIVEN
        // WHEN
        ProductDto productDto = ProductAndProductDtoMapper.mapProductToProductDto(product_1);

        // THEN
        Assertions.assertThat(productDto.getQuantity()).isEqualTo(product_1.getQuantity());
        Assertions.assertThat(productDto.getExpiryDate()).isEqualTo(product_1.getExpiryDate());
        Assertions.assertThat(productDto.getName()).isEqualTo(product_1.getName());
        Assertions.assertThat(productDto.getId()).isEqualTo(tempUUID);
    }

    @Test
    @DisplayName("Map Product with default values to a Product Dto")
    void testMapProductToProductDto_whenPassProductWithDefaultValues_returnsProductDtoDefaultValues() {
        // GIVEN
        // WHEN
        ProductDto productDto = ProductAndProductDtoMapper.mapProductToProductDto(productWithDefaultValues);

        // THEN
        Assertions.assertThat(productDto.getName()).isNull();
        Assertions.assertThat(productDto.getQuantity()).isEqualTo(0);
        Assertions.assertThat(productDto.getExpiryDate()).isNull();
        Assertions.assertThat(productDto.getId()).isNull();
    }

    /*
    * Product ---> Product Write Dto
    * */

    @Test
    @DisplayName("Product correctly mapped to Product Write Dto")
    void testMapProductToProductWriteDto_whenPassValidDetails_returnsProperProductWriteDto() {
        // GIVEN
        // WHEN
        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(product_1);

        // THEN
        Assertions.assertThat(productWriteDto.getName()).isEqualTo(product_1.getName());
        Assertions.assertThat(productWriteDto.getExpiryDate()).isEqualTo(product_1.getExpiryDate());
        Assertions.assertThat(productWriteDto.getQuantity()).isEqualTo(product_1.getQuantity());
    }

    @Test
    @DisplayName("Map Product with default values to a Product Write Dto")
    void testMapProductToProductWriteDto_whenPassProductWithDefaultValues_returnsProductWriteDtoDefaultValues() {
        // GIVEN
        // WHEN
        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(productWithDefaultValues);

        // THEN
        Assertions.assertThat(productWriteDto.getName()).isNull();
        Assertions.assertThat(productWriteDto.getQuantity()).isEqualTo(0);
        Assertions.assertThat(productWriteDto.getExpiryDate()).isNull();
    }

}
