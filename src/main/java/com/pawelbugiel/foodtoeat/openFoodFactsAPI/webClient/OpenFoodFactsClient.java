package com.pawelbugiel.foodtoeat.openFoodFactsAPI.webClient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenFoodFactsClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String Open_Food_Facts_URI = "https://world.openfoodfacts.org/api/v2/search?categories_tags_en=chocolates&labels_tags_en=organic,fair%20trade&fields=code,product_name";


}
