package br.com.rafael.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rafael.models.User;
import br.com.rafael.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	public UserServices(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = new User();
		try {
			user = userRepository.findByUserName(username);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (user != null) {
			return (UserDetails) user;
		} else {
			throw new UsernameNotFoundException("User " + username + " not found!");
		}
	}
}
