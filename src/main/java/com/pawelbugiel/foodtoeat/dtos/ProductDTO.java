package com.pawelbugiel.foodtoeat.dtos;

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
public class ProductDTO {

    private UUID id;

    @NotBlank(message = "Product name cannot be empty")
    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    private int quantity;

    @FutureOrPresent
    private LocalDate expiryDate;


    public static final class ProductDTOBuilder {
        private UUID id;
        private String name;
        private int quantity;
        private LocalDate expiryDate;

        private ProductDTOBuilder() {
        }

        public static ProductDTOBuilder aProductDto() {
            return new ProductDTOBuilder();
        }

        public ProductDTOBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ProductDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductDTOBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductDTOBuilder withExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public ProductDTO build() {
            ProductDTO productDTO = new ProductDTO();
            productDTO.id = this.id;
            productDTO.name = this.name;
            productDTO.expiryDate = this.expiryDate;
            productDTO.quantity = this.quantity;
            return productDTO;
        }
    }
}
