package com.example.security.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class PostFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var authorization = request.getHeader("Authorization");		
		if(authorization == null) {
			throw new BadCredentialsException("Authorization missing");
		}
		Collection<? extends GrantedAuthority> authorities = Collections
				.singleton(new SimpleGrantedAuthority("ROLE_USER"));		
		Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "admin",authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);	
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		return request.getServletPath().equals("/login");
	}
	
	
}
