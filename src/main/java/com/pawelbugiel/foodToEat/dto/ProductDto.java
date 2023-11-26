package com.pawelbugiel.foodToEat.dto;

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
public class ProductDto {

    private UUID id;

    @NotBlank(message = "Product name cannot be empty")
    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    private int quantity;

//    @FutureOrPresent
    private LocalDate expiryDate;


    public static final class ProductDtoBuilder {
        private UUID id;
        private String name;
        private int quantity;
        private LocalDate expiryDate;

        private ProductDtoBuilder() {
        }

        public static ProductDtoBuilder aProductDto() {
            return new ProductDtoBuilder();
        }

        public ProductDtoBuilder withId(UUID id) {
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
