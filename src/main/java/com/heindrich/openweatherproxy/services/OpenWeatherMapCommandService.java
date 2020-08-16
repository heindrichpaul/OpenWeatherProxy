package com.heindrich.openweatherproxy.services;

import com.heindrich.openweatherproxy.client.OpenWeatherMapClient;
import com.heindrich.openweatherproxy.converters.OpenWeatherMapToOpenWeatherCommandConverter;
import com.heindrich.openweatherproxy.model.openweathermap.OpenWeatherMapResponse;
import com.heindrich.openweatherproxy.model.proxymodel.OpenWeatherMapCommand;
import com.heindrich.openweatherproxy.repositories.OpenWeatherMapCommandRepository;
import com.heindrich.openweatherproxy.services.exceptions.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenWeatherMapCommandService {
    private final OpenWeatherMapCommandRepository openWeatherMapCommandRepository;
    private final OpenWeatherMapClient openWeatherMapClient;
    private final OpenWeatherMapToOpenWeatherCommandConverter openWeatherMapToOpenWeatherCommandConverter;

    @Autowired
    public OpenWeatherMapCommandService(OpenWeatherMapCommandRepository openWeatherMapCommandRepository, OpenWeatherMapClient openWeatherMapClient, OpenWeatherMapToOpenWeatherCommandConverter openWeatherMapToOpenWeatherCommandConverter) {
        this.openWeatherMapCommandRepository = openWeatherMapCommandRepository;
        this.openWeatherMapClient = openWeatherMapClient;
        this.openWeatherMapToOpenWeatherCommandConverter = openWeatherMapToOpenWeatherCommandConverter;
    }

    public OpenWeatherMapCommand findByName(String name) throws CityNotFoundException {
        OpenWeatherMapCommand openWeatherMapCommand = this.openWeatherMapCommandRepository.findByNameLike("%"+name+"%");
        if (openWeatherMapCommand == null)
        {
            throw new CityNotFoundException(name);
        }
        return openWeatherMapCommand;
    }

    public List<OpenWeatherMapCommand> findAll(){
        ArrayList<OpenWeatherMapCommand> openWeatherMapCommands = new ArrayList<>();
        this.openWeatherMapCommandRepository.findAll().forEach(openWeatherMapCommands::add);
        return openWeatherMapCommands;
    }

    public void removeByName(String name){
        OpenWeatherMapCommand openWeatherMapCommand = this.openWeatherMapCommandRepository.findByNameLike("%"+name+"%");
        if (openWeatherMapCommand != null) {
            this.openWeatherMapCommandRepository.deleteById(openWeatherMapCommand.getId());
        }
    }

    public void updateDataByName(String name){
        OpenWeatherMapResponse response = openWeatherMapClient.getDataForCity(name);
        OpenWeatherMapCommand openWeatherMapCommand = openWeatherMapToOpenWeatherCommandConverter.convert(response);
        this.openWeatherMapCommandRepository.save(openWeatherMapCommand);
    }
}
