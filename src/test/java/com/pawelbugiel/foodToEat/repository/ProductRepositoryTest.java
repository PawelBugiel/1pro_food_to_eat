package com.pawelbugiel.foodToEat.repository;

import com.pawelbugiel.foodToEat.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Autowired
    private TestEntityManager testEntityManager;
    private final LocalDate expiryDate_1 = LocalDate.of(2024, 1, 1);
    private final LocalDate expiryDate_2 = LocalDate.of(2025, 2, 2);
    private Product product_1;
    private Product product_2;

    @BeforeEach
    public void setUp() {
        product_1 = (Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(1)
//                .withExpiryDate(expiryDate_1)
                .build());

        product_2 = (Product.ProductBuilder.aProduct()
                .withName("Mars")
                .withQuantity(2)
//                .withExpiryDate(expiryDate_2)
                .build());
        productRepository.saveAll(List.of(product_1, product_2));
    }

    /*
     * ************* FIND *************
     */

    @Test
    @DisplayName("Successfully found product by id")
    public void testFindProductById_whenValidIdPass_returnProduct() {
        // Given
        Product saved = productRepository.save(product_2);
        UUID savedUUID = saved.getId();
        // When
        Product retrievedProduct = productRepository.findById(savedUUID).orElse(Product.ProductBuilder.aProduct().withName("UnnamedProduct").build());
        // Then
        assertThat(retrievedProduct).isNotNull();
        assertThat(retrievedProduct.getName()).isEqualTo("Mars");
        assertThat(retrievedProduct.getQuantity()).isEqualTo(2);
//        assertThat(retrievedProduct.getExpiryDate()).isEqualTo(expiryDate_2);
    }

    @Test
    @DisplayName("Product not found by id")
    public void testFindProductById_whenValidIdPass_returnOptionalEmpty() {
        // Given
        UUID notPresentUUID = UUID.randomUUID();
        // When
        Optional<Product> optionalRetrievedProduct = productRepository.findById(notPresentUUID);
        // Then
        assertThat(optionalRetrievedProduct).isEmpty();
    }

    @Test
    @DisplayName("Successfully retrieved all products")
    public void productRepository_getAllProducts_ListOfProducts() {
        // When
        List<Product> products = productRepository.findAll();
        // Then
        assertThat(products).isNotNull();
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(2);
    }

  /*  @Test
    @DisplayName("Successfully retrieved all products")
    void findByPartialName() {
        fail("Not implemented yet");
    }


    @Test
    void findWithExpiredDate() {
        fail("Not implemented yet");
    }*/
}
