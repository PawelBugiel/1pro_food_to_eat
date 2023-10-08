package com.pawelbugiel.foodToEat.repository;

import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
