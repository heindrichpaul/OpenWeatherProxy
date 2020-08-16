package com.capgemini.logiusassesment.controller;

import com.capgemini.logiusassesment.model.proxymodel.OpenWeatherMapCommand;
import com.capgemini.logiusassesment.services.OpenWeatherMapCommandService;
import com.capgemini.logiusassesment.services.exceptions.CityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class WeatherProxyControllerTest {

    @Mock
    OpenWeatherMapCommandService service;

    @InjectMocks
    WeatherProxyController controller;

    MockMvc mockMvc;

    OpenWeatherMapCommand webResponse;

    @BeforeEach
    void setUp() throws IOException {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        webResponse = new ObjectMapper().readValue(new ClassPathResource("openweathermap_pretoria_command_response.json").getFile(), OpenWeatherMapCommand.class);
    }

    @Test
    void retrieveCityInformationFromH2() throws Exception {


        when(service.findByName(anyString())).thenReturn(webResponse);

        MvcResult mockResult = mockMvc.perform(get("/weatherproxy/cities/Pretoria"))
                .andExpect(status().isOk())
                .andReturn();

        OpenWeatherMapCommand result = new ObjectMapper().readValue(mockResult.getResponse().getContentAsString(),OpenWeatherMapCommand.class);

        verify(service,times(1)).findByName(anyString());
    }

    @Test
    void retrieveUnknownCityInformationFromH2() throws Exception {
        when(service.findByName(anyString())).thenThrow(CityNotFoundException.class);

        mockMvc.perform(get("/weatherproxy/cities/Pretoria"))
                .andExpect(status().isNotFound());

        verify(service,times(1)).findByName(anyString());
    }
}