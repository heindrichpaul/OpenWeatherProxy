package com.capgemini.logiusassesment.model.proxymodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "weather")
public class OpenWeatherMapCommand {

    @JsonProperty("id")
    @Id
    private Long id;

    @JsonProperty("name")
    @Column(name = "cityName")
    private String name;

    @Column(name = "tempMin")
    @JsonProperty("temp_min")
    private Double tempMin;

    @Column(name = "tempMax")
    @JsonProperty("temp_max")
    private Double tempMax;

    @Column(name = "sunrise")
    @JsonProperty("sunrise")
    private Long sunrise;

}
