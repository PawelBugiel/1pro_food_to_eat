package com.pawelbugiel.foodToEat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
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
    private final ProductWriteDto validProductDto_1 = ProductWriteDto.ProductWriteDtoBuilder
            .aProductWriteDto()
            .withName("Maslanka")
            .withQuantity(66)
            .withExpiryDate(LocalDate.of(2066, 6, 6))
            .build();

    @Test
    @DisplayName("Create a product using valid data - happy path" )
    void testCreateProduct_whenPassValidDetails_returnsCreatedProduct() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        String requestBody = objectMapper.writeValueAsString(validProductDto_1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        Mockito.<ProductWriteDto>when(productServiceMock.createProduct(any(ProductWriteDto.class))).thenReturn(validProductDto_1);
        // WHEN
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

       /* StreamReadException – if underlying input contains invalid content of type JsonParser supports (JSON for default case)
        DatabindException – if the input JSON structure does not match structure expected for result type (or has other mismatch issues)*/
        ProductWriteDto responseProductWriteDto = objectMapper.readValue(responseBodyAsString, ProductWriteDto.class);
        // THEN
        Assertions.assertNotNull(responseProductWriteDto);
        Assertions.assertEquals(requestBody, responseBodyAsString);
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Create a product using low edge values - should create")
    void testCreateProduct_whenPassValidLowEdgeDetails_returnsCreatedProduct() throws Exception {
        // GIVEN
        //////////////////////    MOCK a HTTP REQUEST
        ProductWriteDto validProductWriteDto = ProductWriteDto.ProductWriteDtoBuilder.aProductWriteDto()
                .withName("sol")
                .withQuantity(1)
                .withExpiryDate(LocalDate.of(2023,12,05))
                .build();

        String requestBody = objectMapper.writeValueAsString(validProductWriteDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        Mockito.<ProductWriteDto>when(productServiceMock.createProduct(any(ProductWriteDto.class))).thenReturn(validProductWriteDto);

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
        String requestBody = objectMapper.writeValueAsString(validProductDto_1);

        RequestBuilder requestBuilder = post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);
        // the mock behavior
        Mockito.<ProductWriteDto>when(productServiceMock.createProduct(any(ProductWriteDto.class))).thenReturn(validProductDto_1);
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
        String requestBody = objectMapper.writeValueAsString(validProductDto_1);

        RequestBuilder requestBuilder = post("/api/products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

//        ProductWriteDto productWriteDto = objectMapper.readValue(requestBody, ProductWriteDto.class);

        // WHEN
        Mockito.<ProductWriteDto>when(productServiceMock.createProduct(any(ProductWriteDto.class))).thenReturn(validProductDto_1);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString();
        System.out.println("===============" + resultContent);

        ProductWriteDto createduProductWriteDto = objectMapper.readValue(resultContent, ProductWriteDto.class);

        Assertions.assertNotNull(createduProductWriteDto);
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

