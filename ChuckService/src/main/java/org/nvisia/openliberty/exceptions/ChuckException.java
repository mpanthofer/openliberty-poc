package org.nvisia.openliberty.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unexpected server error")
public class ChuckException extends RuntimeException {

	private static final long serialVersionUID = -1284046993062152938L;

	public ChuckException() {
		super();
	}

	public ChuckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChuckException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChuckException(String message) {
		super(message);
	}

	public ChuckException(Throwable cause) {
		super(cause);
	}

	
}
