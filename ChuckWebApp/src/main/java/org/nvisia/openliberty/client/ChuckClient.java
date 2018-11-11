package org.nvisia.openliberty.client;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.nvisia.openliberty.model.ChuckJoke;

@Dependent
@RegisterRestClient
@Path("/chucks")
public interface ChuckClient {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	ChuckJoke getRandomJoke();
}
