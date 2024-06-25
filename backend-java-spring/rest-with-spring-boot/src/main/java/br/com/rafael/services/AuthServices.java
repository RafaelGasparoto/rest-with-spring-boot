package br.com.rafael.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rafael.models.AccountCredentials;
import br.com.rafael.models.Token;
import br.com.rafael.repository.UserRepository;
import br.com.rafael.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentials data) {
        try {
            var username = data.getUserName();
            var password = data.getPassword();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);

            authenticationManager.authenticate(auth);

            var user = repository.findByUserName(username);

            var tokenResponse = new Token();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String userName, String refreshToken) {
        var user = repository.findByUserName(userName);

        var tokenResponse = new Token();

        if(user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + userName + " not found!");
        }

        return ResponseEntity.ok(tokenResponse);
    }

}
