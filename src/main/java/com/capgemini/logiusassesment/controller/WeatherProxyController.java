package com.capgemini.logiusassesment.controller;

import com.capgemini.logiusassesment.model.proxymodel.OpenWeatherMapCommand;
import com.capgemini.logiusassesment.services.OpenWeatherMapCommandService;
import com.capgemini.logiusassesment.services.exceptions.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/weatherproxy")
public class WeatherProxyController {

    private final OpenWeatherMapCommandService openWeatherMapCommandService;

    @Autowired
    public WeatherProxyController(OpenWeatherMapCommandService openWeatherMapCommandService) {
        this.openWeatherMapCommandService = openWeatherMapCommandService;
    }

    @GetMapping("/cities/{cityName}")
    public @ResponseBody
    OpenWeatherMapCommand RetrieveCityInformationFromH2(@PathVariable String cityName) throws CityNotFoundException {
        return this.openWeatherMapCommandService.findByName(cityName);
    }

    @GetMapping("/cities")
    public @ResponseBody List<OpenWeatherMapCommand> GetAllRecords() {
        return this.openWeatherMapCommandService.findAll();
    }

    @DeleteMapping("/cities/{cityName}")
    public void DeleteCityInformationFromH2( @PathVariable @NotNull String cityName) {
        this.openWeatherMapCommandService.removeByName(cityName);
    }

    @PostMapping("/cities/{cityName}")
    public void UpdateCityInformation(@PathVariable String cityName) {
        this.openWeatherMapCommandService.updateDataByName(cityName);
    }
}
