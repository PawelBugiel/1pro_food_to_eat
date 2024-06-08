package com.pawelbugiel.foodToEat.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@ToString
public class ProductRequest {

    private static final String PRODUCT_NAME_REGEX = "^[a-zA-Z0-9]{3}.*$";

    @NotBlank(message = "Product name cannot consists with whitespaces only")
    @NotNull(message = "Product name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{3}.*$")
    @Size(min = 3, max = 33, message = "The name must be between 3 and 33 characters long")
    private String name;

    @Range(min = 1, max = 10_000,  message = "Product quantity must be in range from 1 to 10 000")
    private int quantity;

    @NotNull(message = "Product expiry date cannot be empty")
    @FutureOrPresent
    private LocalDate expiryDate;

    // #q instead the builder use StreamAPI to create a product ?
    public static final class ProductRequestBuilder {

        private  String name;
        private int quantity;
        private LocalDate expiryDate;

        private ProductRequestBuilder() {
        }

        public static ProductRequestBuilder aProductWriteDto() {
            return new ProductRequestBuilder();
        }

        public ProductRequestBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductRequestBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductRequestBuilder withExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public ProductRequest build() {
            ProductRequest productRequest = new ProductRequest();
            productRequest.expiryDate = this.expiryDate;
            productRequest.quantity = this.quantity;
            productRequest.name = this.name;
            return productRequest;
        }
    }
}
