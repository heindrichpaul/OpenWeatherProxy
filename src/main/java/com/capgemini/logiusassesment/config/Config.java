package com.capgemini.logiusassesment.config;

import com.capgemini.logiusassesment.converters.OpenWeatherMapToOpenWeatherCommandConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    private final OpenWeatherMapToOpenWeatherCommandConverter openWeatherMapToOpenWeatherCommandConverter;

    @Autowired
    public Config(OpenWeatherMapToOpenWeatherCommandConverter openWeatherMapToOpenWeatherCommandConverter) {
        this.openWeatherMapToOpenWeatherCommandConverter = openWeatherMapToOpenWeatherCommandConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(openWeatherMapToOpenWeatherCommandConverter);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
