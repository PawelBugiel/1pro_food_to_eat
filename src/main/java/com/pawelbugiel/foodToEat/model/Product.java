package com.pawelbugiel.foodToEat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Table(name = "products")
@Entity
public class Product {

    @Id
    @JsonIgnore
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column (name = "quantity")
    private double quantity;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;


}
