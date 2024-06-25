package br.com.rafael.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationServiceException {
	private static final long serialVersionUID = 1L;
	
	public InvalidJwtAuthenticationException(String ex) {
		super(ex);
	}
}
