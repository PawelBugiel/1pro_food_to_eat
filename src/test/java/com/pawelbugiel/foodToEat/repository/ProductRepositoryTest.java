package com.pawelbugiel.foodToEat.repository;

import com.pawelbugiel.foodToEat.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@AutoConfigureTestDatabase
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createProductTest() {

        // Arrange
        LocalDate expiryDate = LocalDate.of(2023, 12, 12);

        Product product = Product.ProductBuilder.aProduct()
                .withName("Water")
                .withQuantity(11)
                .withExpiryDate(expiryDate)
                .build();

        // Act

        Product savedProduct = productRepository.save(product);

        // Assert

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getName()).isEqualTo("Water");
    }
}
