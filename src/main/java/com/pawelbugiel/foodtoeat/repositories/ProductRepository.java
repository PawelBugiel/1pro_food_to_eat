package com.pawelbugiel.foodtoeat.repositories;

import com.pawelbugiel.foodtoeat.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

//************** FIND *************

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :partialName, '%'))")
    Page<Product> findByPartialName(@Param("partialName") String partialName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.expiryDate <= CURRENT_DATE")
    List<Product> findWithExpiredDate(Pageable pageable);

    Optional<Product> findByNameAndExpiryDate(String name, LocalDate expiryDate);

}
