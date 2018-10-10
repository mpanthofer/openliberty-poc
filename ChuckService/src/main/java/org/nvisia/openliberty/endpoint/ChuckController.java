package org.nvisia.openliberty.endpoint;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.nvisia.openliberty.dao.ChuckDao;
import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chucks")
public class ChuckController {

	private static final Logger logger = Logger.getLogger(ChuckController.class);

	@Autowired
	private ChuckDao dao;

	public ChuckController(ChuckDao dao) {
		this.dao = dao;
	}

	@CrossOrigin
	@RequestMapping(path = "", method = RequestMethod.GET)
	public @ResponseBody ChuckJoke get() {
		return dao.getRandomChuckJoke();
	}
	
	@CrossOrigin
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ChuckJoke get(@PathVariable("id") long id, HttpServletResponse response) {
		return dao.getChuckJoke(id);
	}
}