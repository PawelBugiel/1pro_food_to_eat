package com.pawelbugiel.foodtoeat.openFoodFactsAPI.webClient;

import com.pawelbugiel.foodtoeat.openFoodFactsAPI.dto.LevelOneDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class OpenFoodFactsClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String OPEN_FOOD_FACTS_URI2 = "https://world.openfoodfacts.org/cgi/search.pl?search_terms={name}&page_size=1&json=1";

    public LevelOneDto findProduct(String name) {
        return restTemplate.getForObject(OPEN_FOOD_FACTS_URI2, LevelOneDto.class, name);
    }

    public byte[] getImage(String url) {
        return restTemplate.getForObject(url, byte[].class);
    }
}
