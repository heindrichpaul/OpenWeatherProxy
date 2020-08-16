package com.heindrich.openweatherproxy.model.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class Wind{

	@JsonProperty("deg")
	private Long deg;

	@JsonProperty("speed")
	private Double speed;

	@JsonProperty("gust")
	private Double gust;
}