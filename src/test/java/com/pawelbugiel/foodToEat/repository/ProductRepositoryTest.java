package com.pawelbugiel.foodToEat.repository;

import com.pawelbugiel.foodToEat.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * a naming convention is "aClass_aMethod_whatWillBeReturned"
 * */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
// The above annotation is to avoid a problem with cleaning up the embedded database before running each of test methods.
@AutoConfigureTestDatabase
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Product product_1 = (Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(1)
                .withExpiryDate(LocalDate.of(2024, 1, 1))
                .build());

        Product product_2 = (Product.ProductBuilder.aProduct()
                .withName("Mars")
                .withQuantity(2)
                .withExpiryDate(LocalDate.of(2055, 2, 2))
                .build());

        productRepository.saveAll(List.of(product_1, product_2));
    }

    @Test
    public void productRepository_saveProduct_Product() {
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
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void productRepository_getProduct_aProduct() {

        // Act
        Product retreivedProduct = productRepository.findById(2L).orElse(null);

        // Assert
        assert retreivedProduct != null;
        assertThat(retreivedProduct.getId()).isEqualTo(2);
    }

    @Test
    public void productRepository_getAllProducts_ListOfProducts() {

        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
    }
}
