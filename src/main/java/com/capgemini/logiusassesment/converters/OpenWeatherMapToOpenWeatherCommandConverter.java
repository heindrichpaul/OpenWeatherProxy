package com.capgemini.logiusassesment.converters;

import com.capgemini.logiusassesment.model.openweathermap.OpenWeatherMapResponse;
import com.capgemini.logiusassesment.model.proxymodel.OpenWeatherMapCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherMapToOpenWeatherCommandConverter implements Converter<OpenWeatherMapResponse, OpenWeatherMapCommand> {


    @Override
    @Nullable
    public OpenWeatherMapCommand convert(OpenWeatherMapResponse source) {
        if (source == null){
            return null;
        }else{
            return OpenWeatherMapCommand.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .tempMin(source.getMain().getTempMin())
                    .tempMax(source.getMain().getTempMax())
                    .sunrise(source.getSys().getSunrise())
                    .build();
        }
    }
}
