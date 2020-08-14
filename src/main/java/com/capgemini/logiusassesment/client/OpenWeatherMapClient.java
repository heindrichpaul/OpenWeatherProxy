package com.capgemini.logiusassesment.client;

import com.capgemini.logiusassesment.model.openweathermap.OpenWeatherMapResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherMapClient {

    private String URL;
    private final RestTemplate restTemplate;

    public OpenWeatherMapClient(@Value("${openweathermap_url}") String url, @Value("${openweathermap_appId}") String apiKey, RestTemplate restTemplate) {
        URL = String.format("%s?q=%s&appid=%s",url,"%s",apiKey);
        this.restTemplate = restTemplate;
    }

    public OpenWeatherMapResponse getDataForCity(String name) {
        OpenWeatherMapResponse response = this.restTemplate.getForObject(String.format(URL,name),OpenWeatherMapResponse.class);
        return response;
    }
}
