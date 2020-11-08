package com.example.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Spring security ensures OncePerRequestFilter is called only once in the
 * filter chain. OncePerRequestFilter is preferred over regular filters.
 * 
 * This filter is used only to validate the username and password in the login flow.
 * 
 */
@Configuration
public class InitialAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private AuthenticationManager authenticationManager;  

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("User:" + request.getHeader("username"));
		var username = request.getHeader("username");
		var password = request.getHeader("password");
		
		Collection<? extends GrantedAuthority> authorities = Collections
				.singleton(new SimpleGrantedAuthority("ROLE_USER"));		
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, password,authorities);		
			authenticationManager.authenticate(authentication);
		filterChain.doFilter(request, response);
	}

}
