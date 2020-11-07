package com.example.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Spring security ensures OncePerRequestFilter is called only once in the
 * filter chain. OncePerRequestFilter is preferred over regular filters.
 */
@Configuration
public class InitialAuthFilter extends OncePerRequestFilter {

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return super.shouldNotFilter(request);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("User:" + request.getHeader("username"));
		filterChain.doFilter(request, response);
	}

}
