package org.nvisia.openliberty.endpoint;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.nvisia.openliberty.client.ChuckClient;
import org.nvisia.openliberty.model.ChuckJoke;

@RequestScoped
@Traced
@Path("/chucks")
public class ChuckController implements Serializable {

	private static final long serialVersionUID = -5643833038631003300L;
	
	@Inject
	@RestClient
	private ChuckClient client;

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ChuckJoke getRandomJoke() {
		return client.getRandomJoke();
	}
}
