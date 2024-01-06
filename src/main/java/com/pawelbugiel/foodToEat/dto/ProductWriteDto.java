package com.pawelbugiel.foodToEat.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@ToString
public class ProductWriteDto {

    @NotNull(message = "Product name cannot be empty")
    @Size(min = 3, max = 33, message = "The name must be between 3 and 33 characters long")
    private String name;

    @Range(min = 1, max = 10_000,  message = "Product quantity must be in range from 1 to 10 000")
    private int quantity;

    @FutureOrPresent
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expiryDate;

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
