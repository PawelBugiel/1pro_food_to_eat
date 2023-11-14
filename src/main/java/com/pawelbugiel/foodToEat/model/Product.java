package com.pawelbugiel.foodToEat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@Table(name = "products")
@Entity
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column (name = "quantity")
    private int quantity;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    public static final class ProductBuilder {
        private UUID id;
        private String name;
        private int quantity;
        private LocalDate expiryDate;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder withExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.name = this.name;
            product.id = this.id;
            product.quantity = this.quantity;
            product.expiryDate = this.expiryDate;
            return product;
        }
    }
}
