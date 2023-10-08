package com.pawelbugiel.foodToEat.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProductDto {

    private long id;

    @NotBlank(message = "Product name cannot be empty")
    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    private int quantity;

    @FutureOrPresent
    private LocalDate expiryDate;


    public static final class ProductDtoBuilder {
        private long id;
        private @NotBlank(message = "Product name cannot be empty")
        @NotEmpty String name;
        private @NotNull @Min(1) int quantity;
        private @FutureOrPresent LocalDate expiryDate;

        private ProductDtoBuilder() {
        }

        public static ProductDtoBuilder aProductDto() {
            return new ProductDtoBuilder();
        }

        public ProductDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ProductDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductDtoBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductDtoBuilder withExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public ProductDto build() {
            ProductDto productDto = new ProductDto();
            productDto.id = this.id;
            productDto.name = this.name;
            productDto.expiryDate = this.expiryDate;
            productDto.quantity = this.quantity;
            return productDto;
        }
    }
}
