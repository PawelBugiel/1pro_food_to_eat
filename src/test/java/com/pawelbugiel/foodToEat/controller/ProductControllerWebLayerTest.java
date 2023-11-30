/*
package com.pawelbugiel.foodToEat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawelbugiel.foodToEat.dto.ProductDto;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = ProductController.class)
class ProductControllerWebLayerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductServiceImpl productService;

    private final ProductWriteDto productWriteDto_1 = ProductWriteDto.ProductWriteDtoBuilder
            .aProductWriteDto()
            .withName("Maslanka")
            .withQuantity(66)
            .withExpiryDate(LocalDate.of(2066, 6, 6))
            .build();

   @Test
    @DisplayName("Create a product and return it")
    void testCreateProduct_whenValidDetailsProvided_returnsCreatedProduct() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        String requestBody = objectMapper.writeValueAsString(productWriteDto_1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        when(productService.createProduct(productWriteDto_1)).thenReturn(productWriteDto_1);

        // WHEN
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("start");
        System.out.println(mvcResult.getResponse().getContentLength());
        System.out.println("stop");

        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

        System.out.println("==================" + responseBodyAsString);

        ProductWriteDto createdProductWriteDto = objectMapper.readValue(responseBodyAsString, ProductWriteDto.class);

        // THEN
        Assertions.assertNotNull(createdProductWriteDto);

    }

    @Test
    @DisplayName("Create a product and return a status created")
    void testCreateProduct_2() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        String requestBody = objectMapper.writeValueAsString(productWriteDto_1);

        ProductDto productDto_fromJson = objectMapper.readValue(requestBody, ProductDto.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productWriteDto_1));

        when(productService.createProduct(any(ProductWriteDto.class))).thenReturn(productDto_fromJson);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        // WHEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(jsonPath("$.name", is));
    }

   @Test
    @DisplayName("Create a product and return a status created")
    void testCreateProduct_3() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productWriteDto_1));

        // the mock behavior
        when(productService.createProduct(any(ProductWriteDto.class))).thenReturn(productWriteDto_1);

        // WHEN, THEN
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Create a product and return it")
    void testCreateProduct_4() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        String requestBody = objectMapper.writeValueAsString(productWriteDto_1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

//        ProductWriteDto productWriteDto = objectMapper.readValue(requestBody, ProductWriteDto.class);

        when(productService.createProduct(productWriteDto_1)).thenReturn(productWriteDto_1);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ProductWriteDto createduProductWriteDto = objectMapper.readValue(contentAsString, ProductWriteDto.class);


        Assertions.assertNotNull(createduProductWriteDto);
    }


    @DisplayName("User can be created")
    @Test
    void testCreateUser_whenValidUserDetailsProvided_returnsCreatedUserDetails() throws Exception {
        // Arrange
        ProductDto productDto = objectMapper.readValue(userDetailsRequestModel, ProductDto.class);
        userDto.setUserId(UUID.randomUUID().toString());

        when(usersService.createUser(any(UserDto.class))).thenReturn(userDto);


    //MOCK a HTTP REQUEST
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

        // Act

    //EXECUTES a REQUEST
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        UserRest createdUser = new ObjectMapper().readValue(responseBodyAsString, UserRest.class);

        // Assert
        assertEquals(userDetailsRequestModel.getFirstName(),
                createdUser.getFirstName(),
                "The returned first name is incorrect");


        assertEquals(userDetailsRequestModel.getLastName(),
                createdUser.getLastName(),
                "The returned last name is incorrect");

        assertEquals(userDetailsRequestModel.getEmail(),
                createdUser.getEmail(),
                "The returned email is incorrect"
        );

        assertFalse(createdUser.getUserId().isEmpty(),
                "User id should not be empty");
    }


 @DisplayName("Http status 400 for empty first name")
    @Test
    void testCreateUser_whenFirstNameIsNotProvided_returnsHttpStatus400() throws Exception {
        // Arrange
        userDetailsRequestModel.setFirstName("");


    //MOCK a HTTP REQUEST
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(),
                "Incorrect HTTP status returned - should be 400");
    }

    @Disabled
    @Test
    @DisplayName("Get all products with success")
    void testGetAllProducts_whenGoesFine_returnsAllProducts() {
        fail("Not implemented yet");
    }

    @Disabled
    @Test
    void findProductById() {
        fail("Not implemented yet");
    }
}

*/
