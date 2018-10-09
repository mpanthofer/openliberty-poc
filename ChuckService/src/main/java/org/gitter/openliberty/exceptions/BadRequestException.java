package org.gitter.openliberty.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Client request error")
public class BadRequestException extends ChuckException {

	private static final long serialVersionUID = 5685980176656585573L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BadRequestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BadRequestException(String arg0) {
		super(arg0);
	}

	public BadRequestException(Throwable arg0) {
		super(arg0);
	}

	
}
