package org.nvisia.openliberty.dao.icndb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChuckModel {

	private String type;
	@JsonProperty("value")
	private ChuckJoke joke;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ChuckJoke getJoke() {
		return joke;
	}

	public void setJoke(ChuckJoke joke) {
		this.joke = joke;
	}
}
