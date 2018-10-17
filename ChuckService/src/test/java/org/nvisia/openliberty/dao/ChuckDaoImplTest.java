package org.nvisia.openliberty.dao;

import javax.ws.rs.ProcessingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.nvisia.openliberty.dao.icndb.ChuckModel;
import org.nvisia.openliberty.dao.icndb.ICNDBClient;
import org.nvisia.openliberty.exceptions.ChuckException;

public class ChuckDaoImplTest {

	private ChuckDaoImpl dao;

	@Mock
	private ICNDBClient client;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	public ChuckDaoImplTest() {
		dao = new ChuckDaoImpl();
	}

	@Before
	public void setup() {
		dao.setClient(client);
	}
	
	@Test
	public void testGetRandomJoke_Success() {
		// setup
		ChuckModel model = buildDefaultModel();
		Mockito.when(client.getRandomJoke(Mockito.anyString())).thenReturn(model);

		// test
		ChuckJoke actual = dao.getRandomChuckJoke();

		// verify
		Assert.assertNotNull(actual);
		Assert.assertEquals(model.getJoke().getId(), actual.getId());
		Assert.assertArrayEquals(model.getJoke().getCategories(), actual.getCategories());
		Assert.assertEquals(model.getJoke().getJoke(), actual.getJoke());
	}

	@Test(expected = ChuckException.class)
	public void testGetRandomJoke_Failure() {
		// setup
		Mockito.when(client.getRandomJoke(Mockito.anyString())).thenThrow(new ProcessingException("test"));

		// test
		dao.getRandomChuckJoke();
	}

	@Test
	public void testGetById_Success() {
		// setup
		ChuckModel model = buildDefaultModel();
		Mockito.when(client.getJoke(Mockito.anyInt())).thenReturn(model);

		// test
		ChuckJoke actual = dao.getChuckJoke(1);

		// verify
		Assert.assertNotNull(actual);
		Assert.assertEquals(model.getJoke().getId(), actual.getId());
		Assert.assertArrayEquals(model.getJoke().getCategories(), actual.getCategories());
		Assert.assertEquals(model.getJoke().getJoke(), actual.getJoke());
	}

	@Test(expected = ChuckException.class)
	public void testGetById_Failure() {
		// setup
		Mockito.when(client.getJoke(Mockito.anyInt())).thenThrow(new ProcessingException("test"));

		// test
		dao.getChuckJoke(1);
	}

	private ChuckModel buildDefaultModel() {
		ChuckJoke joke = new ChuckJoke();
		joke.setId(1);
		joke.setCategories(new String[] { "a", "b" });
		joke.setJoke("funny");

		ChuckModel model = new ChuckModel();
		model.setType("test");
		model.setJoke(joke);

		return model;
	}

}
