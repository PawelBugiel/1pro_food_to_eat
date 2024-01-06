
package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl underTest_ProductServiceImpl;

    private final Product validProductEntity_1 = Product.ProductBuilder.aProduct()
            .withName("Milk")
            .withQuantity(1)
            .withExpiryDate(LocalDate.of(2024, 1, 1))
            .build();

    private final Product validProductEntity_2 = Product.ProductBuilder.aProduct()
            .withName("Bread")
            .withQuantity(4)
            .withExpiryDate(LocalDate.of(2044, 4, 4))
            .build();

//************** CREATE *************

    @Test
    @DisplayName("Should create Product and Return Product Write Dto")
    public void testCreateProduct_whenValidDetailsPassed_ReturnsProduct() {
        // GIVEN
        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(validProductEntity_1);
        // set how the productRepository mock should behave
        Mockito.<Product>when(productRepository.save(any(Product.class))).thenReturn(validProductEntity_1);
        // WHEN
        ProductWriteDto savedProductDto = underTest_ProductServiceImpl.createProduct(productWriteDto);
        // THEN
        assertThat(savedProductDto).isNotNull();
        assertThat(savedProductDto.getName()).isEqualTo(validProductEntity_1.getName());
        assertThat(savedProductDto.getQuantity()).isEqualTo(validProductEntity_1.getQuantity());
        assertThat(savedProductDto.getExpiryDate()).isEqualTo(validProductEntity_1.getExpiryDate());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @Disabled
    @DisplayName("Create Product : Invalid details should throw MethodArgumentNotValidException")
    public void testCreateProduct_whenInvalidDetailPassed_throwsMethodArgumentNotValidException() {
        //GIVEN
        Product invalidProduct = Product.ProductBuilder.aProduct()
                .withName("some name")
                .withQuantity(11)
                .withExpiryDate(LocalDate.of(2023, 12, 22))
                .build();

        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(invalidProduct);
        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> underTest_ProductServiceImpl.createProduct(productWriteDto));

        Mockito.<ProductRepository>verify(productRepository, never()).save(any());
        //WHEN
    }

    @Test
    @Disabled
    @DisplayName("Create Product : Invalid details should throw MethodArgumentNotValidException")
    public void testCreateProduct_whenInvalidDetailPassed_throwsMethodArgumentNotValidException222222() {
        //GIVEN
        ProductWriteDto invalidProductDto = ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName("some name")
                .withExpiryDate(LocalDate.of(2023, 12, 12))
                .build();

        ProductWriteDto productWriteDto = underTest_ProductServiceImpl.createProduct(invalidProductDto);

        // Assert
//        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> underTest_ProductServiceImpl.createProduct(productWriteDto));

        Mockito.<ProductRepository>verify(productRepository, never()).save(any());
        //WHEN
    }

 /*  @Test
    @DisplayName("Should returns all (two) products dtos ")
    public void testGetAllProducts_whenTwoValidProductsExists_returnsProductDtos() {
        // GIVEN
        List<Product> products = new ArrayList<>();
        products.add(product_1);
        products.add(product_2);
        when(productRepository.findAll()).thenReturn(products);
        // WHEN
        List<ProductDto> productDtos = underTest_ProductServiceImpl.findAllProducts();
        // THEN
        Assertions.assertNotNull(productDtos);
        assertThat(productDtos.size()).isEqualTo(2);

        assertThat(productDtos.get(0).getId()).isEqualTo(products.get(0).getId());
        assertThat(productDtos.get(0).getName()).isEqualTo(products.get(0).getName());
        assertThat(productDtos.get(0).getQuantity()).isEqualTo(products.get(0).getQuantity());
        assertThat(productDtos.get(0).getExpiryDate()).isEqualTo(products.get(0).getExpiryDate());

        assertThat(productDtos.get(1).getId()).isEqualTo(products.get(1).getId());
        assertThat(productDtos.get(1).getName()).isEqualTo(products.get(1).getName());
        assertThat(productDtos.get(1).getQuantity()).isEqualTo(products.get(1).getQuantity());
        assertThat(productDtos.get(1).getExpiryDate()).isEqualTo(products.get(1).getExpiryDate());

        verify(productRepository, times(1)).findAll();
    }*/

    @Test
    @DisplayName("should return a product with given id")
    public void testGetProductById_whenProperIdPassed_returnsOptionalProductDto() {
        // GIVEN
        UUID uuid = UUID.randomUUID();
        String stringUUID = uuid.toString();
        ProductDto productDto_1 = ProductAndProductDtoMapper.mapProductToProductDto(validProductEntity_1);
        when(productRepository.findById(eq(uuid))).thenReturn(Optional.of(validProductEntity_1));
        // WHEN
        Optional<ProductDto> resultOptionalProductDto = underTest_ProductServiceImpl.findProductById(stringUUID);
        // THEN
        Assertions.assertTrue(resultOptionalProductDto.isPresent());
        ProductDto resultProductDto = resultOptionalProductDto.get();

        assertThat(resultProductDto.getId()).isEqualTo(productDto_1.getId());
        assertThat(resultProductDto.getName()).isEqualTo(productDto_1.getName());
        assertThat(resultProductDto.getQuantity()).isEqualTo(productDto_1.getQuantity());
        assertThat(resultProductDto.getExpiryDate()).isEqualTo(productDto_1.getExpiryDate());

        verify(productRepository, times(1)).findById(eq(uuid));
    }

  /* @Test
    @DisplayName("Should returns empty collection")
    public void testGetAllProducts_whenTwoValidProductsExists_returnsEmptyCollection() {
        // GIVEN
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        // WHEN
        List<ProductDto> productDtos = underTest_ProductServiceImpl.findAllProducts();
        // THEN
        assertTrue(productDtos.isEmpty());
        verify(productRepository, times(1)).findAll();
    }*/

    @Test
    @DisplayName("Pass an invalid Id, return an empty product optional")
    public void testGetProductById_whenInvalidIdPassed_returnsOptionalProductDto() {
        // GIVEN
        UUID uuid = UUID.randomUUID();
        String stringUUID = uuid.toString();
        when(productRepository.findById(eq(uuid))).thenReturn(Optional.empty());
        // WHEN
        Optional<ProductDto> resultOptionalProductDto = underTest_ProductServiceImpl.findProductById(stringUUID);
        // THEN
        Assertions.assertTrue(resultOptionalProductDto.isEmpty());
        verify(productRepository, times(1)).findById(eq(uuid));
    }
}

