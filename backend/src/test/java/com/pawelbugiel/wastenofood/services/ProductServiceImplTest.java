
package com.pawelbugiel.wastenofood.services;

import com.pawelbugiel.wastenofood.dtos.ProductRequest;
import com.pawelbugiel.wastenofood.dtos.ProductResponse;
import com.pawelbugiel.wastenofood.exceptions.ProductNotFoundException;
import com.pawelbugiel.wastenofood.mappers.ProductMapper;
import com.pawelbugiel.wastenofood.models.Product;
import com.pawelbugiel.wastenofood.repositories.ProductRepository;
import com.pawelbugiel.wastenofood.validators.ObjectValidator;
import jakarta.validation.ValidationException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ObjectValidator<ProductRequest> objectValidator;
    @InjectMocks
    private ProductServiceImpl underTest_ProductServiceImpl;

    private final UUID VALID_UUID = UUID.fromString("55a85f64-5717-4562-b3fc-2c963f66af55");
    private final LocalDate DATE_YEAR_3K = LocalDate.of(3000, 3, 3);
    private final String NAME_MILK = "Milk";
    private final int QTY_1 = 1;


//************** CREATE *************

    @Test
    @DisplayName("CREATE: Should create new Product and Return ProductResponse")
    public void testCreateProduct_whenValidProductRequestPassedAndProductWithSameNameAndExpiryDateDoesNotExist_ReturnsProductResponse() {

        // GIVEN
        ProductRequest requestedProduct = new ProductRequest(NAME_MILK, QTY_1, DATE_YEAR_3K);
        Product productToCreate = new Product(null, NAME_MILK, QTY_1, DATE_YEAR_3K);
        Product savedProduct = new Product(VALID_UUID, NAME_MILK, QTY_1, DATE_YEAR_3K);
        ProductResponse expectedResponse = new ProductResponse(VALID_UUID, NAME_MILK, QTY_1, DATE_YEAR_3K);

        when(productRepository.findByNameAndExpiryDate(NAME_MILK, DATE_YEAR_3K))
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

        //GIVEN
        ProductRequest requestedProductWith_2_Milk = new ProductRequest(NAME_MILK, 2, DATE_YEAR_3K);
        Product existingProductWith_1_Milk = new Product(VALID_UUID, NAME_MILK, 1, DATE_YEAR_3K);
        ProductResponse expectedResponseWith_3_Milk = new ProductResponse(VALID_UUID, NAME_MILK, 3, DATE_YEAR_3K);

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
        verify(productMapper).toProductResponse(existingProductWith_1_Milk);
        verifyNoMoreInteractions(productRepository);
        verify(productMapper, never()).toProduct(any(ProductRequest.class));
    }

    @Test
    @DisplayName("CREATE: Should throw ValidationException when ProductRequest has negative quantity")
    public void testCreateProduct_whenProductRequestNegativeQuantityPassed_ThrowsValidationException() {

        //GIVEN
        ProductRequest invalidRequest = new ProductRequest(NAME_MILK, -22, DATE_YEAR_3K);

        doThrow(new ValidationException("Validation failed"))
                .when(objectValidator)
                .validate(invalidRequest);

        //WHEN & THEN
        assertThatThrownBy(() -> underTest_ProductServiceImpl.createProduct(invalidRequest))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Validation failed");

        verify(objectValidator).validate(invalidRequest);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("CREATE: Should throw ValidationException when ProductRequest is null")
    public void testCreateProduct_whenProductRequestIsNull_ThrowsValidationException() {

        //GIVEN
        doThrow(new ValidationException("Object to validate cannot be null"))
                .when(objectValidator)
                .validate(isNull());

        // WHEN & THEN
        assertThrows(ValidationException.class, () -> underTest_ProductServiceImpl
                .createProduct(null));


        verify(objectValidator).validate(null);
        verify(productRepository, never()).findByNameAndExpiryDate(anyString(), any(LocalDate.class));
        verify(productRepository, never()).save(any(Product.class));
    }

//************** READ *************


    //************** UPDATE *************
    @Test
    @DisplayName("UPDATE: Should update product and return ProductResponse")
    public void testUpdateProduct_whenProductRequestIsValid_ReturnsProductResponse() {

        // GIVEN
        UUID productId = VALID_UUID;
        ProductRequest productRequest = new ProductRequest(NAME_MILK, 5, DATE_YEAR_3K);
        Product productToUpdate = new Product(VALID_UUID, NAME_MILK, 10, DATE_YEAR_3K);
        Product updatedProduct = new Product(VALID_UUID, NAME_MILK, 5, DATE_YEAR_3K);
        ProductResponse expectedResponse = new ProductResponse(VALID_UUID, NAME_MILK, 5, DATE_YEAR_3K);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(productToUpdate));

        when(productRepository.save(productToUpdate))
                .thenReturn(updatedProduct);

        when(productMapper.toProductResponse(updatedProduct))
                .thenReturn(expectedResponse);

        // WHEN
        ProductResponse actualResponse = underTest_ProductServiceImpl.updateProduct(productId, productRequest);

        // THEN
        assertThat(actualResponse.name()).isEqualTo(expectedResponse.name());
        assertThat(actualResponse.expiryDate()).isEqualTo(expectedResponse.expiryDate());
        assertThat(actualResponse.quantity()).isEqualTo(expectedResponse.quantity());

        verify(objectValidator).validate(productRequest);
        verify(productRepository).findById(productId);
        verify(productMapper).updateProductFromRequest(productRequest, productToUpdate);
        verify(productRepository).save(productToUpdate);
        verify(productMapper).toProductResponse(updatedProduct);
        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(productMapper);
    }

    @Test
    @DisplayName("UPDATE: Should throw ValidationException when ProductRequest quantity is negative")
    public void testUpdateProduct_whenProductRequestNegativeQuantityPassed_ThrowsValidationException() {
        // GIVEN
        UUID productId = VALID_UUID;
        ProductRequest invalidRequest = new ProductRequest("Chips", -10, DATE_YEAR_3K);

        doThrow(new ValidationException("Invalid product data"))
                .when(objectValidator)
                .validate(invalidRequest);

        // WHEN + THEN
        assertThatThrownBy(() -> underTest_ProductServiceImpl.updateProduct(productId, invalidRequest))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Invalid product data");

        verify(objectValidator).validate(invalidRequest);
        verifyNoInteractions(productRepository);
        verifyNoInteractions(productMapper);
    }

    @Test
    @DisplayName("UPDATE: Should throw ProductNotFoundException when product with given ID does not exist")
    public void testUpdateProduct_whenProductWithGivenIdDoesNotExist_ThrowsProductNotFoundException() {
        // GIVEN
        UUID productId = VALID_UUID;
        ProductRequest updateRequest = new ProductRequest(NAME_MILK, 3, DATE_YEAR_3K);

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThatThrownBy(() -> underTest_ProductServiceImpl.updateProduct(productId, updateRequest))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining(productId.toString());

        verify(objectValidator).validate(updateRequest);
        verify(productRepository).findById(productId);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(productMapper);
    }

}
//************** DELETE *************


