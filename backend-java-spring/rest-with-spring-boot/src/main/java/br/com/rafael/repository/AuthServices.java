package br.com.rafael.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rafael.models.AccountCredentials;
import br.com.rafael.models.Token;
import br.com.rafael.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity singnin(AccountCredentials accountCredentials) {
        try {
            var username = accountCredentials.getUserName();
            var password = accountCredentials.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = repository.findByUserName(username);
            var tokenResponse = new Token();
            if(user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("User " + username + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password");
        }
    }
}
