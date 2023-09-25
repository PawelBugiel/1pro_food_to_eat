package com.pawelbugiel.foodToEat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Getter
@ToString
@Table(name = "products")
@Entity
public class Product {

    public Product() {
    }

    @Autowired
    public Product(String name, double quantity, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Product name cannot be empty")
    @Column(name = "name")
    private String name;

    @Column (name = "quantity")
    private double quantity;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;


}
