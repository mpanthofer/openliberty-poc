package org.nvisia.openliberty.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class BadRequestException extends WebApplicationException {

	private static final long serialVersionUID = 5685980176656585573L;

	private static final Status DEFAULT_STATUS = Status.BAD_REQUEST;

	public BadRequestException() {
		super(DEFAULT_STATUS);
	}

	public BadRequestException(Status status) {
		super(DEFAULT_STATUS);
	}

	public BadRequestException(String message) {
		super(message, DEFAULT_STATUS);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause, DEFAULT_STATUS);
	}

	public BadRequestException(Throwable cause) {
		super(cause, DEFAULT_STATUS);
	}	
}
