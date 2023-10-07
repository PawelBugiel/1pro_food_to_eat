package com.pawelbugiel.foodToEat.repository;

import com.pawelbugiel.foodToEat.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
* a naming convention is "aClass_aMethod_whatWillBeReturned"
* */

@AutoConfigureTestDatabase
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

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
        assertThat(savedProduct.getName()).isEqualTo("Water");
        assertThat(savedProduct.getId()).isGreaterThan(0);

    }

    @Test
    public void productRepository_getProduct_aProduct() {
        // Arrange
        Product tempProduct_1 = (Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(1)
                .withExpiryDate(LocalDate.of(2024, 1, 1))
                .build());

        productRepository.save(tempProduct_1);

        Product tempProduct_2 = (Product.ProductBuilder.aProduct()
                .withName("Mars")
                .withQuantity(2)
                .withExpiryDate(LocalDate.of(2055, 2, 2))
                .build());

        productRepository.save(tempProduct_2);

        // Act
                        // uses Optional 
        Product retreivedProduct = productRepository.findById(tempProduct_2.getId()).orElse(null);

        // Assert
        assert retreivedProduct != null;
        assertThat(retreivedProduct.getId()).isEqualTo(2);
    }

    @Test
    public void productRepository_getAllProducts_ListOfProducts() {

        // Arrange
        Product tempProduct = (Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(1)
                .withExpiryDate(LocalDate.of(2024, 1, 1))
                .build());

        productRepository.save(tempProduct);

        tempProduct = (Product.ProductBuilder.aProduct()
                .withName("Mars")
                .withQuantity(2)
                .withExpiryDate(LocalDate.of(2055, 2, 2))
                .build());

        productRepository.save(tempProduct);

        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);

    }


}
