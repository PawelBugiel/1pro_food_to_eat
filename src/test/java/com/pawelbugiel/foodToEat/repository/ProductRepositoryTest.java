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
 * When to write tests for @Repository ?  Only for methods implemented on my own
 *
 *
 * @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 *  annotation is to avoid a problem with cleaning up the embedded H2 database before running each of test methods.
 * alternatively it is possible to use @AfterEach tearDown()
 *
 * @AutoConfigureTestDatabase to use H2
 *
 * @DataJpaTest to test Jpa Repository
 * */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
        // given
        LocalDate expiryDate = LocalDate.of(2023, 12, 12);
        Product product = Product.ProductBuilder.aProduct()
                .withName("Water")
                .withQuantity(11)
                .withExpiryDate(expiryDate)
                .build();

        // when
        Product savedProduct = productRepository.save(product);

        // then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void productRepository_getProduct_aProduct() {

        // Act
        Product retrievedProduct = productRepository.findById(2L).orElse(null);

        // Assert
        assertThat(retrievedProduct).isNotNull();
        assertThat(retrievedProduct.getId()).isEqualTo(2);
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
