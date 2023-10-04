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
    private double quantity;

    @FutureOrPresent
    private LocalDate expiryDate;


    public static final class ProductWriteDtoBuilder {
        private long id;
        private @NotBlank(message = "Product name cannot be empty")
        @NotEmpty String name;
        private @NotNull @Min(1) double quantity;
        private @FutureOrPresent LocalDate expiryDate;

        private ProductWriteDtoBuilder() {
        }

        public static ProductWriteDtoBuilder aProductWriteDto() {
            return new ProductWriteDtoBuilder();
        }

        public ProductWriteDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ProductWriteDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductWriteDtoBuilder withQuantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductWriteDtoBuilder withExpiryDate(LocalDate expiryDate) {
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
