package org.nvisia.openliberty.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class ChuckException extends WebApplicationException {

	private static final long serialVersionUID = -1284046993062152938L;
	private static final Status DEFAULT_STATUS = Status.INTERNAL_SERVER_ERROR;

	public ChuckException() {
		super(DEFAULT_STATUS);
	}

	public ChuckException(Status status) {
		super(DEFAULT_STATUS);
	}

	public ChuckException(String message) {
		super(message, DEFAULT_STATUS);
	}

	public ChuckException(String message, Throwable cause) {
		super(message, cause, DEFAULT_STATUS);
	}

	public ChuckException(Throwable cause) {
		super(cause, DEFAULT_STATUS);
	}
}
