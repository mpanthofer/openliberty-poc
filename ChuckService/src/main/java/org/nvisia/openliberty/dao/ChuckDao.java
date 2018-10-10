package org.nvisia.openliberty.dao;

import java.io.Serializable;

import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.nvisia.openliberty.exceptions.ChuckException;

public interface ChuckDao extends Serializable {
	ChuckJoke getRandomChuckJoke() throws ChuckException;
	ChuckJoke getChuckJoke(long id) throws ChuckException;
}