package com.pawelbugiel.foodtoeat.openFoodFactsAPI.service;

import com.pawelbugiel.foodtoeat.openFoodFactsAPI.dto.LevelOneDto;
import com.pawelbugiel.foodtoeat.openFoodFactsAPI.webClient.OpenFoodFactsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenFoodFactsService {

    private final OpenFoodFactsClient openFoodFactsClient;
    private List<byte[]> images;

    @Autowired
    public OpenFoodFactsService(OpenFoodFactsClient openFoodFactsClient){
        this.openFoodFactsClient=openFoodFactsClient;
    }

    public LevelOneDto getProduct(String name) {
        LevelOneDto product = openFoodFactsClient.findProduct(name);
      /*  byte[] image = openFoodFactsClient.getImage(product.getProducts().get(0).getImage_url());
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
        return product;
    }

 /*   private byte[] getOpenFoodFactsClientImage(String url) {
        images.as;
        byte[] image = openFoodFactsClient.getImage(url);
        return image;
    }*/


}
