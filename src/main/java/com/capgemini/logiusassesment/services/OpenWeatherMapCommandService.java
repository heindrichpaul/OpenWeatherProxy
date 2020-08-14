package com.capgemini.logiusassesment.services;

import com.capgemini.logiusassesment.client.OpenWeatherMapClient;
import com.capgemini.logiusassesment.model.openweathermap.OpenWeatherMapResponse;
import com.capgemini.logiusassesment.model.proxymodel.OpenWeatherMapCommand;
import com.capgemini.logiusassesment.repositories.OpenWeatherMapCommandRepository;
import com.capgemini.logiusassesment.services.exceptions.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenWeatherMapCommandService {
    private final OpenWeatherMapCommandRepository openWeatherMapCommandRepository;
    private final OpenWeatherMapClient openWeatherMapClient;
    private final ConversionService conversionService;

    @Autowired
    public OpenWeatherMapCommandService(OpenWeatherMapCommandRepository openWeatherMapCommandRepository, OpenWeatherMapClient openWeatherMapClient, ConversionService conversionService) {
        this.openWeatherMapCommandRepository = openWeatherMapCommandRepository;
        this.openWeatherMapClient = openWeatherMapClient;
        this.conversionService = conversionService;
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
        OpenWeatherMapCommand openWeatherMapCommand = conversionService.convert(response,OpenWeatherMapCommand.class);
        this.openWeatherMapCommandRepository.save(openWeatherMapCommand);
        //call to API
        //Convert
        //Save
        //
//        this.openWeatherMapCommandRepository.save()
    }
}
