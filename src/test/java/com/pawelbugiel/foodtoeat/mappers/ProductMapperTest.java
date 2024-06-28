package com.pawelbugiel.foodtoeat.mappers;

import com.pawelbugiel.foodtoeat.dtos.ProductRequest;
import com.pawelbugiel.foodtoeat.dtos.ProductResponse;
import com.pawelbugiel.foodtoeat.models.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

class ProductMapperTest {

    private final UUID tempUUID = UUID.randomUUID();
    private final LocalDate expiryDate_1 = LocalDate.of(2077, 7, 7);
    private final Product validProductEntity_1 = Product.ProductBuilder.aProduct()
            .withName("Water")
            .withQuantity(5)
            .withExpiryDate(expiryDate_1)
            .withId(tempUUID)
            .build();
    private final Product productWithDefaultValues = Product.ProductBuilder
            .aProduct()
            .build();
    private final ProductRequest productRequestWithDefaultValues = ProductRequest.ProductRequestBuilder
            .aProductWriteDto()
            .build();

    /*
    * Product Write Dto ---> Product
    * */

    @Test
    @DisplayName("Product Write Dto correctly mapped to Product")
    void testMapProductWriteDtoToProduct_whenPassValidDetails_returnsProperProduct() {
        // GIVEN
        ProductRequest productRequest = ProductRequest.ProductRequestBuilder.aProductWriteDto()
                .withName("Water")
                .withQuantity(5)
                .withExpiryDate(LocalDate.of(2077, 7, 7))
                .build();

        // WHEN
        Product product = ProductMapper.toProduct(productRequest);

        // THEN
        Assertions.assertThat(productRequest.getName()).isEqualTo(product.getName());
        Assertions.assertThat(productRequest.getQuantity()).isEqualTo(product.getQuantity());
        Assertions.assertThat(productRequest.getExpiryDate()).isEqualTo(product.getExpiryDate());
    }

    @Test
    @DisplayName("Map Product Write Dto with default values to a Product")
    void testMapProductWriteDtoToProduct_whenPassProductWriteDtoWithDefaultValues_returnsProductWithDefaultValues() {
        // GIVEN
        // WHEN
        Product product = ProductMapper.toProduct(productRequestWithDefaultValues);

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
    @DisplayName("Product to Product Dto - valid params")
    void testMapProductToProductDto_whenPassValidDetails_returnsProperProductResponse() {
        // GIVEN
        // WHEN
        ProductResponse productResponse = ProductMapper.toProductResponse(validProductEntity_1);

        // THEN
        Assertions.assertThat(productResponse.getQuantity()).isEqualTo(validProductEntity_1.getQuantity());
        Assertions.assertThat(productResponse.getExpiryDate()).isEqualTo(validProductEntity_1.getExpiryDate());
        Assertions.assertThat(productResponse.getName()).isEqualTo(validProductEntity_1.getName());
        Assertions.assertThat(productResponse.getId()).isEqualTo(tempUUID);
    }



    @Test
    @DisplayName("Map Product with default values to a Product Dto")
    void testMapProductToProductDto_whenPassProductWithDefaultValues_returnsProductResponseDefaultValues() {
        // GIVEN
        // WHEN
        ProductResponse productResponse = ProductMapper.toProductResponse(productWithDefaultValues);

        // THEN
        Assertions.assertThat(productResponse.getName()).isNull();
        Assertions.assertThat(productResponse.getQuantity()).isEqualTo(0);
        Assertions.assertThat(productResponse.getExpiryDate()).isNull();
        Assertions.assertThat(productResponse.getId()).isNull();
    }

    /*
    * Product ---> Product Write Dto
    * */

    @Test
    @DisplayName("Product correctly mapped to Product Write Dto")
    void testMapProductToProductWriteDto_whenPassValidDetails_returnsProperProductRequest() {
        // GIVEN
        // WHEN
        ProductRequest productRequest = ProductMapper.toProductRequest(validProductEntity_1);

        // THEN
        Assertions.assertThat(productRequest.getName()).isEqualTo(validProductEntity_1.getName());
        Assertions.assertThat(productRequest.getExpiryDate()).isEqualTo(validProductEntity_1.getExpiryDate());
        Assertions.assertThat(productRequest.getQuantity()).isEqualTo(validProductEntity_1.getQuantity());
    }

    @Test
    @DisplayName("Map Product with default values to a Product Write Dto")
    void testMapProductToProductWriteDto_whenPassProductWithDefaultValues_returnsProductRequestDefaultValues() {
        // GIVEN
        // WHEN
        ProductRequest productRequest = ProductMapper.toProductRequest(productWithDefaultValues);

        // THEN
        Assertions.assertThat(productRequest.getName()).isNull();
        Assertions.assertThat(productRequest.getQuantity()).isEqualTo(0);
        Assertions.assertThat(productRequest.getExpiryDate()).isNull();
    }

}
