package com.capgemini.logiusassesment.model.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class Clouds{

	@JsonProperty("all")
	private Long all;
}