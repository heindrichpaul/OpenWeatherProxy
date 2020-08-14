package com.capgemini.logiusassesment.model.openweathermap;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class OpenWeatherMapResponse{

	@JsonProperty("visibility")
	private Long visibility;

	@JsonProperty("timezone")
	private Long timezone;

	@JsonProperty("main")
	private Main main;

	@JsonProperty("clouds")
	private Clouds clouds;

	@JsonProperty("sys")
	private Sys sys;

	@JsonProperty("dt")
	private Long dt;

	@JsonProperty("coord")
	private Coord coord;

	@JsonProperty("weather")
	private List<Weather> weather;

	@JsonProperty("name")
	private String name;

	@JsonProperty("cod")
	private Long cod;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("base")
	private String base;

	@JsonProperty("wind")
	private Wind wind;
}