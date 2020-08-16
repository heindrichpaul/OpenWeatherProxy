package com.heindrich.openweatherproxy.model.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class Coord{

	@JsonProperty("lon")
	private Double lon;

	@JsonProperty("lat")
	private Double lat;
}