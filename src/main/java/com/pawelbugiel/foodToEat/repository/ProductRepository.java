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

    /*
     * ************* FIND *************
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :partialName, '%'))")
    List<Product> findByPartialName(@Param("partialName") String partialName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.expiryDate <= CURRENT_DATE")
    List<Product> findProductsWithExpiredDate(Pageable pageable);
}
