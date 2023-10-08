package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mapper.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void ProductService_createProduct_ReturnsPokemon(){

        // Arrange
        Product product = (Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(1)
                .withExpiryDate(LocalDate.of(2024, 1, 1))
                .build());

        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(product);

        // when(*method*).thenReturn(*value*)
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductWriteDto savedProductWriteDto = productService.createProduct(productWriteDto);

        // Assert

        assertThat(savedProductWriteDto).isNotNull();
    }

    @Test
    public void ProductService_getAllProducts_returnsProductDtos(){

        // Mock the behavior of productRepository.findAll
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(2)
                .withExpiryDate(LocalDate.of(2033, 03,03))
                .build());

        mockProducts.add(Product.ProductBuilder.aProduct()
                .withName("Bread")
                .withQuantity(4)
                .withExpiryDate(LocalDate.of(2044, 04,04))
                .build());

        when(productRepository.findAll()
        ).thenReturn(mockProducts);

        // Call the method to test
        List<ProductDto> productDtos = productService.getAllProducts();

        assertThat(productDtos.get(1).getName()).isEqualTo(mockProducts.get(1).getName());
        assertThat(productDtos.size()).isEqualTo(2);
    }

}
