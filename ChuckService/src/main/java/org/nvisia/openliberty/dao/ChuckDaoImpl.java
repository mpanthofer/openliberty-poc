package org.nvisia.openliberty.dao;

import java.io.IOException;
import java.util.Arrays;

import javax.enterprise.context.RequestScoped;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.nvisia.openliberty.dao.icndb.ChuckModel;
import org.nvisia.openliberty.exceptions.BadRequestException;
import org.nvisia.openliberty.exceptions.ChuckException;

import com.fasterxml.jackson.databind.ObjectMapper;

@RequestScoped
public class ChuckDaoImpl implements ChuckDao {

	private static final String ICNDB_BASE_URI = "http://api.icndb.com/jokes";

	/**
	 * Gets any number of random jokes, excluding explicit jokes (for the kids!)
	 */
	@Override
	public ChuckJoke getRandomChuckJoke() throws ChuckException {
		HttpResponse response;

		try {
			response = Request.Get(ICNDB_BASE_URI + "/random?exclude=[explicit]").connectTimeout(2500).execute()
					.returnResponse();
		} catch (IOException e) {
			throw new ChuckException(e);
		}

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ChuckException("Unexpected error calling ICNDB API");
		}

		ChuckModel result;
		try {
			result = new ObjectMapper().readValue(response.getEntity().getContent(), ChuckModel.class);
		} catch (IOException e) {
			throw new ChuckException(e);
		}

		if (!result.getType().equals("success")) {
			throw new ChuckException("Unexpected response from ICNDB API: " + result.getType());
		}

		return result.getJoke();
	}

	/**
	 * Gets a joke by id if it exists. Will throw an error if the joke is marked
	 * explicit.
	 */
	@Override
	public ChuckJoke getChuckJoke(long id) throws ChuckException {
		HttpResponse response;
		try {
			response = Request.Get(ICNDB_BASE_URI + "/" + id).connectTimeout(2500).execute().returnResponse();
		} catch (IOException e) {
			throw new ChuckException(e);
		}

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ChuckException("Unexpected error calling ICNDB API");
		}

		ChuckModel result;
		try {
			result = new ObjectMapper().readValue(response.getEntity().getContent(), ChuckModel.class);
		} catch (IOException e) {
			throw new ChuckException(e);
		}

		if (!result.getType().equals("success")) {
			throw new ChuckException("Unexpected response from ICNDB API: " + result.getType());
		}

		if (Arrays.asList(result.getJoke().getCategories()).contains("explicit")) {
			throw new BadRequestException("Aborting attempt to retrieve explicit joke by id: " + id);
		}

		return result.getJoke();
	}
}