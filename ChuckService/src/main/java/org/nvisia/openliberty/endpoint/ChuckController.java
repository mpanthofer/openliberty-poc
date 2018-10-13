package org.nvisia.openliberty.endpoint;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.Traced;
import org.nvisia.openliberty.dao.ChuckDao;
import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.nvisia.openliberty.exceptions.BadRequestException;

import io.opentracing.Tracer;

@RequestScoped
@Path("/chucks")
public class ChuckController implements Serializable {

	private static final long serialVersionUID = -7120710481036735303L;

	@Inject
	private ChuckDao dao;
	
	@Inject
	private Tracer tracer;

	/*
	 * Oddly, constructor injection doesn't appear to work in CDI 2.0 on Liberty?
	 * 
	@Inject
	public ChuckController(ChuckDao dao) {
		this.dao = dao;
	}
	*/

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	@Traced
	public ChuckJoke get() {
		tracer.activeSpan().log("Getting random joke");
		return dao.getRandomChuckJoke();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Traced
	public ChuckJoke get(@PathParam("id") long id) {
		if (id < 0) {
			tracer.activeSpan().log(String.format("id value of %d is inavlid", id));
			throw new BadRequestException("id must be a non-negative integer");
		}
		
		tracer.activeSpan().log("Getting joke #" + id);
		
		return dao.getChuckJoke(id);
	}
}