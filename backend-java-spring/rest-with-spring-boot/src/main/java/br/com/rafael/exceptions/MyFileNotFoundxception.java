package br.com.rafael.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundxception extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyFileNotFoundxception(String ex) {
		super(ex);
	}

	public MyFileNotFoundxception(String ex, Throwable cause) {
		super(ex, cause);
	}
}
