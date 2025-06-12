
package com.pawelbugiel.wastenofood.services;

import com.pawelbugiel.wastenofood.dtos.ProductRequest;
import com.pawelbugiel.wastenofood.dtos.ProductResponse;
import com.pawelbugiel.wastenofood.mappers.ProductMapper;
import com.pawelbugiel.wastenofood.models.Product;
import com.pawelbugiel.wastenofood.repositories.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/* @ExtendWith(MockitoExtension.class) annotation is used to avoid implicitly implementing open and close a mock resource
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
 */


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl underTest_ProductServiceImpl;

    private final UUID VALID_UUID = UUID.fromString("55a85f64-5717-4562-b3fc-2c963f66af55");
    private final LocalDate DATE_2025_05_05 = LocalDate.of(2025, 5, 5);
    private final String NAME_MILK = "Milk";
    private final int QTY_1 = 1;


//************** CREATE *************

    @Test
    @DisplayName("CREATE: Should create new Product and Return ProductResponse")
    public void testCreateProduct_whenValidProductRequestPassedAndProductWithSameNameAndExpiryDateDoesNotExist_ReturnsProductResponse() {

        // GIVEN
        ProductRequest requestedProduct = new ProductRequest(NAME_MILK, QTY_1, DATE_2025_05_05);

        Product productToCreate = new Product(null, NAME_MILK, QTY_1, DATE_2025_05_05);

        Product savedProduct = new Product(VALID_UUID, NAME_MILK, QTY_1, DATE_2025_05_05);

        ProductResponse expectedResponse = new ProductResponse(VALID_UUID, NAME_MILK, QTY_1, DATE_2025_05_05);

        // set how the productRepository mock should behave
        when(productRepository.findByNameAndExpiryDate(NAME_MILK, DATE_2025_05_05))
                .thenReturn(Optional.empty());

        when(productMapper.toProduct(requestedProduct))
                .thenReturn(productToCreate);

        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        when(productMapper.toProductResponse(any(Product.class)))
                .thenReturn(expectedResponse);

        // WHEN
        ProductResponse actualResponse = underTest_ProductServiceImpl.createProduct(requestedProduct);

        // THEN

        assertThat(actualResponse.name()).isEqualTo(expectedResponse.name());
        assertThat(actualResponse.expiryDate()).isEqualTo(expectedResponse.expiryDate());
        assertThat(actualResponse.quantity()).isEqualTo(expectedResponse.quantity());

        verify(productRepository).findByNameAndExpiryDate(requestedProduct.getName(), requestedProduct.getExpiryDate());
        verify(productRepository).save(productToCreate);
        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(productMapper);
    }

    @Test
    @DisplayName("CREATE: Should add quantity to existing Product and Return ProductResponse")
    public void testCreateProduct_whenValidProductRequestPassedAndProductWithTheSameNameAndExpiryDateExists_ReturnsProductResponse() {

        ProductRequest requestedProductWith_2_Milk = new ProductRequest(NAME_MILK, 2, DATE_2025_05_05);

        Product existingProductWith_1_Milk = new Product(VALID_UUID, NAME_MILK, 1, DATE_2025_05_05);

        ProductResponse expectedResponseWith_3_Milk = new ProductResponse(VALID_UUID, NAME_MILK, 3, DATE_2025_05_05);

        //GIVEN
        when(productRepository.findByNameAndExpiryDate(anyString(), any(LocalDate.class)))
                .thenReturn(Optional.of(existingProductWith_1_Milk));

        when(productRepository.save(any(Product.class)))
                .thenReturn(existingProductWith_1_Milk);

        when(productMapper.toProductResponse(any(Product.class)))
                .thenReturn(expectedResponseWith_3_Milk);

        // WHEN
        ProductResponse actualResponse = underTest_ProductServiceImpl.createProduct(requestedProductWith_2_Milk);

        // THEN
        assertThat(actualResponse.name()).isEqualTo(expectedResponseWith_3_Milk.name());
        assertThat(actualResponse.expiryDate()).isEqualTo(expectedResponseWith_3_Milk.expiryDate());
        assertThat(actualResponse.quantity()).isEqualTo(expectedResponseWith_3_Milk.quantity());

        verify(productRepository).findByNameAndExpiryDate(requestedProductWith_2_Milk.getName(), requestedProductWith_2_Milk.getExpiryDate());
        verify(productRepository).save(existingProductWith_1_Milk);
        verifyNoMoreInteractions(productRepository);
        verify(productMapper).toProductResponse(existingProductWith_1_Milk);
        verify(productMapper, never()).toProduct(any(ProductRequest.class));
    }
}

//************** READ *************



//************** UPDATE *************

//************** DELETE *************


