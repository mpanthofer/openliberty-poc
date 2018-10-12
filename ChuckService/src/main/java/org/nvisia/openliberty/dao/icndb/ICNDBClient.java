package org.nvisia.openliberty.dao.icndb;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Dependent
@RegisterRestClient
@Path("/jokes")
public interface ICNDBClient extends Serializable {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/random")
	ChuckModel getRandomJoke(@QueryParam("exclude") String exclude) throws ProcessingException;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	ChuckModel getJoke(@PathParam("id") long id) throws ProcessingException;

}
