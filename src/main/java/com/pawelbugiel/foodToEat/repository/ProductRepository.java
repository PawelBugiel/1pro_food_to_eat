package com.pawelbugiel.foodToEat.repository;

import com.pawelbugiel.foodToEat.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findProductByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(CONCAT('%', :partialName, '%'))", nativeQuery = true)
    List<Product> findByPartialName(@Param("partialName") String partialName, Pageable pageable);
}
