//
//package com.pawelbugiel.foodtoeat.services;
//
//import com.pawelbugiel.foodtoeat.models.Product;
//import com.pawelbugiel.foodtoeat.repositories.ProductRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
///* @ExtendWith(MockitoExtension.class) annotation is used to avoid implicitly implementing open and close a mock resource
// * 1.
// *
// * @BeforeEach
// * void setUp {
// * AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
// * }
// *2.
// * @AfterEach
// * void tearDown() throws Exception {
// * autoCloseable.close();
// * }
// */
//
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceImplTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private ProductServiceImpl underTest_ProductServiceImpl;
//
////    private final UUID VALID_UUID_1 = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
////    private final UUID VALID_UUID_2 = UUID.fromString("99185f55-4417-3362-f1fc-g1e63f66afa6");
//    private final static Logger log = LoggerFactory.getLogger(ProductServiceImplTest.class);
//
//    private final Product validProductEntity_1 = Product.ProductBuilder.aProduct()
//            .withId(UUID.randomUUID())
//            .withName("Milk")
//            .withQuantity(1)
//            .withExpiryDate(LocalDate.of(2025, 1, 1))
//            .build();
//
//    private final Product validProductEntity_2 = Product.ProductBuilder.aProduct()
////            .withId(VALID_UUID_2)
//            .withName("Bread")
//            .withQuantity(4)
//            .withExpiryDate(LocalDate.of(2044, 4, 4))
//            .build();
//
//
////************** CREATE *************
//
////    @Test
////    @DisplayName("CREATE: Should create Product and Return Product Dto")
////    public void testCreateProduct_whenValidDetailsPassed_ReturnsProductDto() {
////
////        // GIVEN
////        ProductRequest productRequest = ProductMapper.toProductRequest(validProductEntity_1);
////        // set how the productRepository mock should behave
////        Mockito.when(productRepository.save(any(Product.class))).thenReturn(validProductEntity_1);
////
////        // WHEN
////        ProductResponse resultProductDTO = underTest_ProductServiceImpl.createProduct(productRequest);
////
////        // THEN
////        Assertions.assertNotNull(resultProductDTO);
////        Assertions.assertEquals(validProductEntity_1.getName(), resultProductDTO.getName());
////        Assertions.assertEquals(validProductEntity_1.getQuantity(), resultProductDTO.getQuantity());
////        Assertions.assertEquals(validProductEntity_1.getExpiryDate(), resultProductDTO.getExpiryDate());
////
////        verify(productRepository, times(1)).save(any(Product.class));
////    }
//
//   /* @Test
//    @DisplayName("CREATE: Invalid email value should throw NullPointerException")
//    public void testCreateProduct_whenInvalidNamePassed_throwsNullPointerException() {
//        //GIVEN
//        Product invalidProduct = Product.ProductBuilder.aProduct()
//                .withName("abcn")
//                .withQuantity(11)
//                .withExpiryDate(LocalDate.of(2055, 12, 22))
//                .withId(UUID.randomUUID())
//                .build();
//
//        Mockito.when(productRepository.save(invalidProduct)).thenThrow(NullPointerException.class);
//
//        log.info(invalidProduct.toString());
//
//        ProductRequest invalidProductWriteDto = ProductMapper_old.toProductRequest(invalidProduct);
//
//        log.info(invalidProductWriteDto.toString());
//        // WHEN, THEN
//        Assertions.assertThrows(NullPointerException.class, () -> underTest_ProductServiceImpl.createProduct(invalidProductWriteDto));
//        verify(productRepository, times(1)).save(any(Product.class));
//    }*/
//
//    @Test
//    @DisplayName("CREATE: Invalid email value should throw NullPointerException")
//    public void testCreateProduct_henInvalidQuantityPassed_throwsNullPointerException() {
//        Product invalidProduct = Product.ProductBuilder.aProduct()
//                .withName("some email")
//                .withQuantity(11)
//                .withExpiryDate(LocalDate.of(2055, 12, 22))
//                .build();
//    }
//    //************** READ *************
//
// /*  @Test
//    @DisplayName("Should returns all (two) products dtos ")
//    public void testGetAllProducts_whenTwoValidProductsExists_returnsProductDtos() {
//        // GIVEN
//        List<Product> products = new ArrayList<>();
//        products.add(product_1);
//        products.add(product_2);
//        when(productRepository.findAll()).thenReturn(products);
//        // WHEN
//        List<ProductResponse> productDtos = underTest_ProductServiceImpl.findAllProducts();
//        // THEN
//        Assertions.assertNotNull(productDtos);
//        assertThat(productDtos.size()).isEqualTo(2);
//
//        assertThat(productDtos.get(0).getId()).isEqualTo(products.get(0).getId());
//        assertThat(productDtos.get(0).getName()).isEqualTo(products.get(0).getName());
//        assertThat(productDtos.get(0).getQuantity()).isEqualTo(products.get(0).getQuantity());
//        assertThat(productDtos.get(0).getExpiryDate()).isEqualTo(products.get(0).getExpiryDate());
//
//        assertThat(productDtos.get(1).getId()).isEqualTo(products.get(1).getId());
//        assertThat(productDtos.get(1).getName()).isEqualTo(products.get(1).getName());
//        assertThat(productDtos.get(1).getQuantity()).isEqualTo(products.get(1).getQuantity());
//        assertThat(productDtos.get(1).getExpiryDate()).isEqualTo(products.get(1).getExpiryDate());
//
//        verify(productRepository, times(1)).findAll();
//    }*/
//
//   /* @Test
//    @DisplayName("should return a product with given id")
//    public void testGetProductById_whenProperIdPassed_returnsOptionalProductDto() {
//        // GIVEN
//        UUID uuid = UUID.randomUUID();
//        String stringUUID = uuid.toString();
//        ProductResponse productDto_1 = ProductMapper_old.toProductResponse(validProductEntity_1);
//        when(productRepository.findById(eq(uuid))).thenReturn(Optional.of(validProductEntity_1));
//        // WHEN
//        Optional<ProductResponse> resultOptionalProductDto = underTest_ProductServiceImpl.findProductById(stringUUID);
//        // THEN
//        Assertions.assertTrue(resultOptionalProductDto.isPresent());
//        ProductResponse resultProductDto = resultOptionalProductDto.get();
//
//        assertThat(resultProductDto.getId()).isEqualTo(productDto_1.getId());
//        assertThat(resultProductDto.getName()).isEqualTo(productDto_1.getName());
//        assertThat(resultProductDto.getQuantity()).isEqualTo(productDto_1.getQuantity());
//        assertThat(resultProductDto.getExpiryDate()).isEqualTo(productDto_1.getExpiryDate());
//
//        verify(productRepository, times(1)).findById(eq(uuid));
//    }*/
//
//  /* @Test
//    @DisplayName("Should returns empty collection")
//    public void testGetAllProducts_whenTwoValidProductsExists_returnsEmptyCollection() {
//        // GIVEN
//        when(productRepository.findAll()).thenReturn(new ArrayList<>());
//        // WHEN
//        List<ProductResponse> productDtos = underTest_ProductServiceImpl.findAllProducts();
//        // THEN
//        assertTrue(productDtos.isEmpty());
//        verify(productRepository, times(1)).findAll();
//    }*/
//
//    /*@Test
//    @DisplayName("Pass an invalid Id, return an empty product optional")
//    public void testGetProductById_whenInvalidIdPassed_returnsOptionalProductDto() {
//        // GIVEN
//        UUID uuid = UUID.randomUUID();
//        String stringUUID = uuid.toString();
//        when(productRepository.findById(eq(uuid))).thenReturn(Optional.empty());
//        // WHEN
//        Optional<ProductResponse> resultOptionalProductDto = underTest_ProductServiceImpl.findProductById(stringUUID);
//        // THEN
//        Assertions.assertTrue(resultOptionalProductDto.isEmpty());
//        verify(productRepository, times(1)).findById(eq(uuid));
//    }*/
//
//    //************** UPDATE *************
//
//    //************** DELETE *************
//}
//
