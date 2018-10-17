package org.nvisia.openliberty.endpoint;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.nvisia.openliberty.dao.ChuckDao;
import org.nvisia.openliberty.dao.icndb.ChuckJoke;
import org.nvisia.openliberty.exceptions.BadRequestException;
import org.nvisia.openliberty.exceptions.ChuckException;

import io.opentracing.Span;
import io.opentracing.Tracer;

public class ChuckControllerTest {

	@Mock
	private ChuckDao dao;

	@Mock
	private Tracer tracer;

	@Mock
	private Span span;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	private ChuckController controller;

	public ChuckControllerTest() {
		controller = new ChuckController();
	}

	@Before
	public void setup() {
		controller.setChuckDao(dao);
		controller.setTracer(tracer);

		Mockito.when(tracer.activeSpan()).thenReturn(span);
	}

	@Test
	public void testGet_Success() {
		// setup

		ChuckJoke joke = getDefaultChuckJoke();
		Mockito.when(dao.getRandomChuckJoke()).thenReturn(joke);

		// test
		ChuckJoke actual = controller.get();

		// verify
		Assert.assertNotNull(actual);
		Assert.assertEquals(actual.getId(), joke.getId());
		Assert.assertEquals(actual.getJoke(), joke.getJoke());
		Assert.assertArrayEquals(actual.getCategories(), joke.getCategories());
	}

	@Test(expected = ChuckException.class)
	public void testGet_Failure() {
		// setup
		Mockito.when(dao.getRandomChuckJoke()).thenThrow(new ChuckException());

		// test
		controller.get();
	}

	@Test
	public void testGetById_Success() {
		// setup
		ChuckJoke joke = getDefaultChuckJoke();
		Mockito.when(dao.getChuckJoke(Mockito.anyInt())).thenReturn(joke);

		// test
		ChuckJoke actual = controller.get(1);

		// verify
		Assert.assertNotNull(actual);
		Assert.assertEquals(actual.getId(), joke.getId());
		Assert.assertEquals(actual.getJoke(), joke.getJoke());
		Assert.assertArrayEquals(actual.getCategories(), joke.getCategories());
	}

	@Test(expected = ChuckException.class)
	public void testGetById_DaoFailure() {
		// setup
		Mockito.when(dao.getChuckJoke(Mockito.anyInt())).thenThrow(new ChuckException());

		// test
		controller.get(1);
	}

	@Test(expected = BadRequestException.class)
	public void testGetById_InvalidId() {
		// test
		controller.get(-1);
	}

	private ChuckJoke getDefaultChuckJoke() {
		ChuckJoke joke = new ChuckJoke();

		joke.setId(1);
		joke.setCategories(new String[] { "a", "b" });
		joke.setJoke("haha");

		return joke;
	}
}
