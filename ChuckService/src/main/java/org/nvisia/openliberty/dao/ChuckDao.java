package org.nvisia.openliberty.dao;

import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.nvisia.openliberty.exceptions.ChuckException;

public interface ChuckDao {
	ChuckJoke getRandomChuckJoke() throws ChuckException;
	ChuckJoke getChuckJoke(long id) throws ChuckException;
}