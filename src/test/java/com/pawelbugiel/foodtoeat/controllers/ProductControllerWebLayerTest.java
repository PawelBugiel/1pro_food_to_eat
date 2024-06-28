/*
package com.pawelbugiel.foodToEat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawelbugiel.foodToEat.dto.ProductRequest;
import com.pawelbugiel.foodToEat.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerWebLayerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductServiceImpl productServiceMock;
    private final ProductRequest validProductResponse_1 = ProductRequest.ProductRequestBuilder
            .aProductRequest()
            .withName("Maslanka")
            .withQuantity(66)
            .withExpiryDate(LocalDate.of(2066, 6, 6))
            .build();

    @Test
    @DisplayName("Create a product using valid data - happy path" )
    void testCreateProduct_whenPassValidDetails_returnsCreatedProduct() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        String requestBody = objectMapper.writeValueAsString(validProductResponse_1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        Mockito.<ProductRequest>when(productServiceMock.createProduct(any(ProductRequest.class))).thenReturn(validProductResponse_1);
        // WHEN
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

       */
/* StreamReadException – if underlying input contains invalid content of type JsonParser supports (JSON for default case)
        DatabindException – if the input JSON structure does not match structure expected for result type (or has other mismatch issues)*//*

        ProductRequest responseProductRequest = objectMapper.readValue(responseBodyAsString, ProductRequest.class);
        // THEN
        Assertions.assertNotNull(responseProductRequest);
        Assertions.assertEquals(requestBody, responseBodyAsString);
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Create a product using low edge values - should create")
    void testCreateProduct_whenPassValidLowEdgeDetails_returnsCreatedProduct() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        ProductRequest validProductRequest = ProductRequest.ProductRequestBuilder.aProductRequest()
                .withName("sol")
                .withQuantity(1)
                .withExpiryDate(LocalDate.of(2023,12,05))
                .build();

        String requestBody = objectMapper.writeValueAsString(validProductRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        Mockito.<ProductRequest>when(productServiceMock.createProduct(any(ProductRequest.class))).thenReturn(validProductRequest);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        // WHEN
        // THEN

    }

    @Test
    @DisplayName("Create a product and return a status created")
    void testCreateProduct_3() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        String requestBody = objectMapper.writeValueAsString(validProductResponse_1);

        RequestBuilder requestBuilder = post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);
        // the mock behavior
        Mockito.<ProductRequest>when(productServiceMock.createProduct(any(ProductRequest.class))).thenReturn(validProductResponse_1);
        // WHEN, THEN
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        //THEN
        Assertions.assertEquals(requestBody, resultContent);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.name", is));

    }

    @Test
    @DisplayName("Create a product and return it")
    void testCreateProduct_4() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        String requestBody = objectMapper.writeValueAsString(validProductResponse_1);

        RequestBuilder requestBuilder = post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

//        ProductRequest ProductRequest = objectMapper.readValue(requestBody, ProductRequest.class);

        // WHEN
        Mockito.<ProductRequest>when(productServiceMock.createProduct(any(ProductRequest.class))).thenReturn(validProductResponse_1);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        System.out.println("===============" + resultContent);

        ProductRequest createduProductRequest = objectMapper.readValue(resultContent, ProductRequest.class);

        Assertions.assertNotNull(createduProductRequest);
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
