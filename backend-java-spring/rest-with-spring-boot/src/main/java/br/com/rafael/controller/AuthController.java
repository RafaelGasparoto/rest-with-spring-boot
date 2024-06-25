package br.com.rafael.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.models.AccountCredentials;
import br.com.rafael.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthServices authServices;
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentials data) {
		if (checkIfParamsIsNotNull(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = authServices.signin(data);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return token;
	}

	@SuppressWarnings("rawtypes")
	@PutMapping(value ="/refresh/{username}")
	public ResponseEntity putMethodName(@PathVariable("username") String userName, @RequestHeader("Authorization") String refreshToken) {
		if(checkIfParamsIsNotNull(userName, refreshToken)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		}

		var token = authServices.refreshToken(userName, refreshToken);
		if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		return token;
	}
	
	private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() ||
				username == null || username.isBlank();
	}

	private boolean checkIfParamsIsNotNull(AccountCredentials data) {
		return data == null || data.getUserName() == null || data.getPassword().isBlank()
				 || data.getPassword() == null || data.getPassword().isBlank();
	}
}
