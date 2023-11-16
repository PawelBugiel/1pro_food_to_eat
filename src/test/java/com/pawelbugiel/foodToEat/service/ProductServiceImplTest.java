package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

/*
 * @ExtendWith(MockitoExtension.class) annotation is used to avoid implicitly implementing open and close a mock resource
 * 1.
 *
 * @BeforeEach
 * void setUp {
 * AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
 * }
 *2.
 * @AfterEach
 * void tearDown() throws Exception {
 * autoCloseable.close();
 * }
 * */

@ExtendWith(MockitoExtension.class)
public class  ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl underTest_ProductServiceImpl;

    @BeforeEach
    public void setUp() {
/*        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(2)
                .withExpiryDate(LocalDate.of(2033, 3, 3))
                .build());

        mockProducts.add(Product.ProductBuilder.aProduct()
                .withName("Bread")
                .withQuantity(4)
                .withExpiryDate(LocalDate.of(2044, 4, 4))
                .build());*/

        underTest_ProductServiceImpl = new ProductServiceImpl(productRepository);

    }

//    @Disabled
    @Test
    @DisplayName("should create Product and Return Product Write Dto")
    public void ProductService_createProduct_ReturnsProduct() {
        // GIVEN
        Product product = (Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(1)
                .withExpiryDate(LocalDate.of(2024, 1, 1))
                .build());

        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(product);

        // set how the productRepository mock should behave
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // WHEN
        ProductWriteDto savedProductWriteDto = underTest_ProductServiceImpl.createProduct(productWriteDto);

        // THEN
        assertThat(savedProductWriteDto).isNotNull();
        assertThat(savedProductWriteDto.getName()).isEqualTo(product.getName());
        assertThat(savedProductWriteDto.getQuantity()).isEqualTo(product.getQuantity());
        assertThat(savedProductWriteDto.getExpiryDate()).isEqualTo(product.getExpiryDate());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void ProductService_getAllProducts_returnsProductDtos() {

       /* // Mock the behavior of productRepository.findAll
        List<Product> products = new ArrayList<>();
        products.add(Product.ProductBuilder.aProduct()
                .withName("Milk")
                .withQuantity(2)
                .withExpiryDate(LocalDate.of(2033, 3,3))
                .build());

        products.add(Product.ProductBuilder.aProduct()
                .withName("Bread")
                .withQuantity(4)
                .withExpiryDate(LocalDate.of(2044, 4,4))
                .build());

        when(productRepository.findAll()).thenReturn(products);

        // Call the method to test
        List<ProductDto> productDtos = underTest_ProductServiceImpl.getAllProducts();

        assertThat(productDtos.get(1).getName()).isEqualTo(products.get(1).getName());
        assertThat(productDtos.size()).isEqualTo(2);
*/
        // when
        underTest_ProductServiceImpl.getAllProducts();
        //then
        verify(productRepository).findAll();
    }

    @Disabled
    @Test
    public void ProductService_getProduct_returnsProductDto() {
fail("ProductService_getProduct_returnsProductDto not implemented yet..");


    }
}
