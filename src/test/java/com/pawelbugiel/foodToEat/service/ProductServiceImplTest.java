
package com.pawelbugiel.foodToEat.service;

import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.mappers.ProductAndProductDtoMapper;
import com.pawelbugiel.foodToEat.model.Product;
import com.pawelbugiel.foodToEat.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
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
*/

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl underTest_ProductServiceImpl;

    private final Product product_1 = Product.ProductBuilder.aProduct()
            .withName("Milk")
            .withQuantity(1)
            .withExpiryDate(LocalDate.of(2024, 1, 1))
            .build();

    private final Product product_2 = Product.ProductBuilder.aProduct()
            .withName("Bread")
            .withQuantity(4)
            .withExpiryDate(LocalDate.of(2044, 4, 4))
            .build();

    @Test
    @DisplayName("should create Product and Return Product Write Dto")
    public void testCreateProduct_whenAllDetailsPassed_ReturnsProduct() {
        // GIVEN
        ProductWriteDto productWriteDto = ProductAndProductDtoMapper.mapProductToProductWriteDto(product_1);
        // set how the productRepository mock should behave
        when(productRepository.save(any(Product.class))).thenReturn(product_1);
        // WHEN
        ProductDto savedProductDto = underTest_ProductServiceImpl.createProduct(productWriteDto);
        // THEN
        assertThat(savedProductDto).isNotNull();
        assertThat(savedProductDto.getName()).isEqualTo(product_1.getName());
        assertThat(savedProductDto.getQuantity()).isEqualTo(product_1.getQuantity());
        assertThat(savedProductDto.getExpiryDate()).isEqualTo(product_1.getExpiryDate());

        verify(productRepository, times(1)).save(any(Product.class));
    }

   /* @Test
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

    /*@Test
    @DisplayName("should return a product with given id")
    public void testGetProductById_whenProperIdPassed_returnsOptionalProductDto() {
        // GIVEN
        UUID uuid = UUID.randomUUID();
        String stringUUID = uuid.toString();
        ProductDto productDto_1 = ProductAndProductDtoMapper.mapProductToProductDto(product_1);
        when(productRepository.findById(eq(uuid))).thenReturn(Optional.of(product_1));
        // WHEN
        Optional<ProductDto> resultOptionalProductDto = underTest_ProductServiceImpl.getProductById(stringUUID);
        // THEN
        Assertions.assertTrue(resultOptionalProductDto.isPresent());
        ProductDto resultProductDto = resultOptionalProductDto.get();

        assertThat(resultProductDto.getId()).isEqualTo(productDto_1.getId());
        assertThat(resultProductDto.getName()).isEqualTo(productDto_1.getName());
        assertThat(resultProductDto.getQuantity()).isEqualTo(productDto_1.getQuantity());
        assertThat(resultProductDto.getExpiryDate()).isEqualTo(productDto_1.getExpiryDate());

        verify(productRepository, times(1) ).findById(eq(uuid));
    }*/

   /* @Test
    @DisplayName("Should returns empty collection")
    public void testGetAllProducts_whenTwoValidProductsExists_returnsEmptyCollection() {
        // GIVEN
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        // WHEN
        List<ProductDto> productDtos = underTest_ProductServiceImpl.getAllProducts();
        // THEN
        assertTrue(productDtos.isEmpty());
        verify(productRepository, times(1)).findAll();
    }*/

   /* @Test
    @DisplayName("Pass an invalid Id, return an empty product optional")
    public void testGetProductById_whenInvalidIdPassed_returnsOptionalProductDto() {
        // GIVEN
        UUID uuid = UUID.randomUUID();
        String stringUUID = uuid.toString();
        when(productRepository.findById(eq(uuid))).thenReturn(Optional.empty());
        // WHEN
        Optional<ProductDto> resultOptionalProductDto = underTest_ProductServiceImpl.getProductById(stringUUID);
        // THEN
        Assertions.assertTrue(resultOptionalProductDto.isEmpty());
        verify(productRepository, times(1) ).findById(eq(uuid));
    }*/
}

