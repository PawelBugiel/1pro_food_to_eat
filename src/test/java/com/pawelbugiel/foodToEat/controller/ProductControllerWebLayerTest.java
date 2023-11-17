package com.pawelbugiel.foodToEat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawelbugiel.foodToEat.dto.ProductWriteDto;
import com.pawelbugiel.foodToEat.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.fail;


@WebMvcTest(controllers = ProductController.class)
class ProductControllerWebLayerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Create a product and return it")
    void testCreateProduct_whenValidDetailsProvided_returnsCreatedProduct() throws Exception {
        // GIVEN
        ProductWriteDto productWriteDto = ProductWriteDto.ProductWriteDtoBuilder
                .aProductWriteDto()
                .withName("Maslanka")
                .withQuantity(66)
//                .withExpiryDate(LocalDate.of(2066, 6, 6))
                .build();

        //////////////////////    MOCK a HTTP REQUEST
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productWriteDto));

        // WHEN
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        ProductWriteDto createdProductWriteDto = new ObjectMapper().convertValue(responseBodyAsString, ProductWriteDto.class);

        // THEN


    }

    @Test
    @DisplayName("Get all products with success")
    void testGetAllProducts_whenGoesFine_returnsAllProducts() {
        fail("Not implemented yet");
    }

    @Test
    void findProductById() {
        fail("Not implemented yet");
    }
}