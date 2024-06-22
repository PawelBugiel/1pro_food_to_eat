package com.pawelbugiel.foodToEat.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse {

    private UUID id;

    @NotBlank(message = "Product name cannot be empty")
    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    private int quantity;

    @FutureOrPresent
    private LocalDate expiryDate;


    public static final class ProductResponseBuilder {
        private UUID id;
        private String name;
        private int quantity;
        private LocalDate expiryDate;

        private ProductResponseBuilder() {
        }

        public static ProductResponseBuilder aProductDto() {
            return new ProductResponseBuilder();
        }

        public ProductResponseBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ProductResponseBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductResponseBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductResponseBuilder withExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public ProductResponse build() {
            ProductResponse productResponse = new ProductResponse();
            productResponse.id = this.id;
            productResponse.name = this.name;
            productResponse.expiryDate = this.expiryDate;
            productResponse.quantity = this.quantity;
            return productResponse;
        }
    }
}
