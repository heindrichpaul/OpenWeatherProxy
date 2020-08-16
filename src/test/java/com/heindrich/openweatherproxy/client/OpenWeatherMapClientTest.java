package com.heindrich.openweatherproxy.client;

import com.heindrich.openweatherproxy.model.openweathermap.OpenWeatherMapResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ConfigurationProperties("test-application.properties")
class OpenWeatherMapClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    OpenWeatherMapClient client;

    OpenWeatherMapResponse response;

    @BeforeEach
    void setUp() throws IOException {
        response = new ObjectMapper().readValue(new ClassPathResource("openweathermap_response.json").getFile(), OpenWeatherMapResponse.class);
    }

    @Test
    void testGetData(){
        when(restTemplate.getForObject(anyString(),eq(OpenWeatherMapResponse.class))).thenReturn(response);

        OpenWeatherMapResponse openWeatherMapResponse = client.getDataForCity("Utrecht");


    }
}