package com.pawelbugiel.foodtoeat.openFoodFactsAPI.controller;

import com.pawelbugiel.foodtoeat.openFoodFactsAPI.dto.LevelOneDto;
import com.pawelbugiel.foodtoeat.openFoodFactsAPI.service.OpenFoodFactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class OpenFoodFactsController {

//    private final OpenFoodFactsClient openFoodFactsClient;
    private final OpenFoodFactsService openFoodFactsService;
    private List<byte[]> images;

    @Autowired
    public OpenFoodFactsController(OpenFoodFactsService openFoodFactsService){
//        this.openFoodFactsClient = openFoodFactsClient;
        this.openFoodFactsService = openFoodFactsService;
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<LevelOneDto> getProduct(@PathVariable String name){
        LevelOneDto product = openFoodFactsService.getProduct(name);
   /*     byte[] image = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_url());
        byte[] image1 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_front_url());
        byte[] image2 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_small_url());
        byte[] image3 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_thumb_url());
        byte[] image4 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_front_thumb_url());
        byte[] image5 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_front_small_url());
        images.add(image);
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);*/
        return ResponseEntity.status(200)
                .body(product);
    }

    @GetMapping("/image/{name}")
    public ResponseEntity<List<byte[]>> getImage(String name){
//        images.forEach();
//        byte[] image = getOpenFoodFactsClientImage();
//        LevelOneDto product = openFoodFactsClient.findProduct(name);
//        byte[] image = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_url());
//        byte[] image1 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_front_url());
//        byte[] image2 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_small_url());
//        byte[] image3 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_thumb_url());
//        byte[] image4 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_front_thumb_url());
//        byte[] image5 = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_front_small_url());
//        images.add(image);
//        images.add(image1);
//        images.add(image2);
//        images.add(image3);
//        images.add(image4);
//        images.add(image5);
        return ResponseEntity.status(200)
                .contentType(MediaType.IMAGE_JPEG)
                .body(images);
    }



}
