package com.heindrich.openweatherproxy.repositories;

import com.heindrich.openweatherproxy.model.proxymodel.OpenWeatherMapCommand;
import org.springframework.data.repository.CrudRepository;

public interface OpenWeatherMapCommandRepository extends CrudRepository<OpenWeatherMapCommand,Long> {

    OpenWeatherMapCommand findByName(String name);
    OpenWeatherMapCommand findByNameLike(String name);

    void deleteByName(String name);
    void deleteByNameLike(String name);
}
