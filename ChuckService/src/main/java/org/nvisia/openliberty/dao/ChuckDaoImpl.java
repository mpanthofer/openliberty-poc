package org.nvisia.openliberty.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.nvisia.openliberty.dao.icndb.ICNDBClient;
import org.nvisia.openliberty.exceptions.ChuckException;

@Traced
@RequestScoped
public class ChuckDaoImpl implements ChuckDao {

	private static final long serialVersionUID = -3784958743999260983L;

	@Inject
	@RestClient
	private ICNDBClient client;

	@Override
	@Counted(name = "getRandomJoke-CallCount")
	@Timed(name = "getRandomJoke-Time", unit = MetricUnits.MILLISECONDS)
	public ChuckJoke getRandomChuckJoke() throws ChuckException {
		try {
			return client.getRandomJoke("[explicit]").getJoke();
		} catch (ProcessingException e) {
			throw new ChuckException(e);
		}
	}

	@Override
	public ChuckJoke getChuckJoke(long id) throws ChuckException {
		try {
			return client.getJoke(id).getJoke();
		} catch (ProcessingException e) {
			throw new ChuckException(e);
		}
	}

	// package-private for testing only
	void setClient(ICNDBClient client) {
		this.client = client;
	}
}
