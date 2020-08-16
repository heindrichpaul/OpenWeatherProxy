package com.heindrich.openweatherproxy.services;

import com.heindrich.openweatherproxy.client.OpenWeatherMapClient;
import com.heindrich.openweatherproxy.converters.OpenWeatherMapToOpenWeatherCommandConverter;
import com.heindrich.openweatherproxy.model.openweathermap.OpenWeatherMapResponse;
import com.heindrich.openweatherproxy.model.proxymodel.OpenWeatherMapCommand;
import com.heindrich.openweatherproxy.repositories.OpenWeatherMapCommandRepository;
import com.heindrich.openweatherproxy.services.exceptions.CityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OpenWeatherMapCommandServiceTest {

    OpenWeatherMapToOpenWeatherCommandConverter converter = new OpenWeatherMapToOpenWeatherCommandConverter();

    @Mock
    OpenWeatherMapClient client;

    @Mock
    OpenWeatherMapCommandRepository repository;

    OpenWeatherMapResponse response;

    OpenWeatherMapCommandService service;

    @BeforeEach
    void setUp() throws IOException {
        response = new ObjectMapper().readValue(new ClassPathResource("openweathermap_response.json").getFile(), OpenWeatherMapResponse.class);
        service = new OpenWeatherMapCommandService(repository, client, converter);
    }

    @Test
    void UpdateWeatherForUtrechtTest() {
        when(client.getDataForCity(anyString())).thenReturn(response);
        when(repository.save(any(OpenWeatherMapCommand.class))).thenReturn(new OpenWeatherMapCommand());

        service.updateDataByName("Utrecht");

        verify(client, times(1)).getDataForCity(anyString());
        verify(repository, times(1)).save(any(OpenWeatherMapCommand.class));

    }

    @Test
    void FindByNameUtrechtTest() throws CityNotFoundException {
        when(repository.findByNameLike(anyString())).thenReturn(converter.convert(response));

        OpenWeatherMapCommand openWeatherMapCommand = service.findByName("Utrecht");


        assertNotNull(openWeatherMapCommand);
        assertEquals(2745909, openWeatherMapCommand.getId());
        assertEquals(293.71, openWeatherMapCommand.getTempMin());
        assertEquals(295.93, openWeatherMapCommand.getTempMax());
        assertEquals(1597378926, openWeatherMapCommand.getSunrise());
        assertEquals("Provincie Utrecht", openWeatherMapCommand.getName());

        verify(repository, times(1)).findByNameLike(anyString());

    }


    @Test
    void FindByNameUnknownTest() {
        when(repository.findByNameLike(anyString())).thenReturn(null);

        assertThrows(CityNotFoundException.class,()->{
            service.findByName("lkja");
        });
    }

    @Test
    void RemoveByNameUtrechtTest(){
        when(repository.findByNameLike(anyString())).thenReturn(converter.convert(response));

        service.removeByName("Utrecht");

        verify(repository, times(1)).deleteById(anyLong());

    }

    @Test
    void RemoveByNameUnknownTest() {
        when(repository.findByNameLike(anyString())).thenReturn(null);

        service.removeByName("Utrecht");

        verify(repository, times(0)).deleteById(anyLong());
    }
}