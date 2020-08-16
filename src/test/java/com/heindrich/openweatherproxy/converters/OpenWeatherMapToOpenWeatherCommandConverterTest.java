package com.heindrich.openweatherproxy.converters;

import com.heindrich.openweatherproxy.model.openweathermap.OpenWeatherMapResponse;
import com.heindrich.openweatherproxy.model.proxymodel.OpenWeatherMapCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherMapToOpenWeatherCommandConverterTest {

    private final OpenWeatherMapToOpenWeatherCommandConverter converter = new OpenWeatherMapToOpenWeatherCommandConverter();

    @Test
    void TestSuccessfulConversion() throws IOException {

        OpenWeatherMapCommand command= new ObjectMapper().readValue(new ClassPathResource("openweathermap_pretoria_command_response.json").getFile(), OpenWeatherMapCommand.class);
        OpenWeatherMapResponse response = new ObjectMapper().readValue(new ClassPathResource("openweathermap_pretoria_response.json").getFile(), OpenWeatherMapResponse.class);

        OpenWeatherMapCommand commandUnderTest = converter.convert(response);

        assertNotNull(commandUnderTest);
        assertEquals(command.getId(),commandUnderTest.getId());
        assertEquals(command.getSunrise(),commandUnderTest.getSunrise());
        assertEquals(command.getTempMin(),commandUnderTest.getTempMin());
        assertEquals(command.getTempMax(),commandUnderTest.getTempMax());
        assertEquals(command.getName(),commandUnderTest.getName());
    }

    @Test
    void TestNullConversion() throws IOException {

        OpenWeatherMapCommand commandUnderTest = converter.convert(null);

        assertNull(commandUnderTest);
    }
}