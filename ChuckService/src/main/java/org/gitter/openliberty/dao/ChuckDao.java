package org.gitter.openliberty.dao;

import org.gitter.openliberty.dao.icndb.ChuckJoke;
import org.gitter.openliberty.exceptions.ChuckException;

public interface ChuckDao {
	ChuckJoke getRandomChuckJoke() throws ChuckException;
	ChuckJoke getChuckJoke(long id) throws ChuckException;
}