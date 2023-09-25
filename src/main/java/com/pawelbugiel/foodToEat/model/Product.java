package com.pawelbugiel.foodToEat.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "products")
public class Product {

    @Id
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
