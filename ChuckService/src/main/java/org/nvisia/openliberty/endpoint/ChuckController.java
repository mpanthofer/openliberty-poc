package org.nvisia.openliberty.endpoint;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.nvisia.openliberty.dao.ChuckDao;
import org.nvisia.openliberty.dao.icndb.ChuckJoke;

@RequestScoped
@Path("/chucks")
public class ChuckController implements Serializable {

	private static final long serialVersionUID = -7120710481036735303L;

	@Inject
	private ChuckDao dao;

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
	public ChuckJoke get() {
		return dao.getRandomChuckJoke();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChuckJoke get(@PathParam("id") long id, HttpServletResponse response) {
		return dao.getChuckJoke(id);
	}
}