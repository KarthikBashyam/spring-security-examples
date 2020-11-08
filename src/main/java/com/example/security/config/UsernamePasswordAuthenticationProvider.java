package com.example.security.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		var userName = authentication.getName();
		var password = String.valueOf(authentication.getCredentials());

		User userFromDB = userRepository.findByUserName(userName);
		System.out.println("User from DB: " + userFromDB);

		if (passwordEncoder.matches(password, userFromDB.getPassword())) {
			// Without ROLE the authentication provider will not work.
			Collection<? extends GrantedAuthority> authorities = Collections
					.singleton(new SimpleGrantedAuthority("ROLE_USER"));
			return new UsernamePasswordAuthenticationToken(userName, password, authorities);
		} else {			
			throw new BadCredentialsException("Invalid Credentials!!");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}