package com.pawelbugiel.foodToEat.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@ToString
public class ProductWriteDto {

    private final String productNameRegex = "^[a-zA-Z0-9]{3}.*$";

    @NotBlank(message = "Product name cannot consists with whitespaces only")
    @NotNull(message = "Product name cannot be empty")
    @Pattern(regexp = productNameRegex)
    @Size(min = 3, max = 33, message = "The name must be between 3 and 33 characters long")
    private String name;

    @Range(min = 1, max = 10_000,  message = "Product quantity must be in range from 1 to 10 000")
    private int quantity;

    @NotNull(message = "Product expiry date cannot be empty")
    @FutureOrPresent
    private LocalDate expiryDate;

    // #q instead the builder use StreamAPI to create a product ?
    public static final class ProductWriteDtoBuilder {

        private  String name;
        private int quantity;
        private LocalDate expiryDate;

        private ProductWriteDtoBuilder() {
        }

        public static ProductWriteDtoBuilder aProductWriteDto() {
            return new ProductWriteDtoBuilder();
        }

        public ProductWriteDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductWriteDtoBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductWriteDtoBuilder withExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public ProductWriteDto build() {
            ProductWriteDto productWriteDto = new ProductWriteDto();
            productWriteDto.expiryDate = this.expiryDate;
            productWriteDto.quantity = this.quantity;
            productWriteDto.name = this.name;
            return productWriteDto;
        }
    }
}
