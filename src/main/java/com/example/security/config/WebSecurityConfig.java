package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.filters.InitialAuthFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsernamePasswordAuthenticationProvider authenticationProvider;
	
	@Autowired
	private InitialAuthFilter initialAuthFilter;
	
	@Autowired
	private PostFilter postFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();
		//http.authenticationProvider(authenticationProvider);
		// @formatter:off
		http.addFilterAt(initialAuthFilter, BasicAuthenticationFilter.class)
		.addFilterAfter(postFilter, BasicAuthenticationFilter.class)
			.authorizeRequests()
			.mvcMatchers("/h2-console/**")
			.permitAll()
			.anyRequest()
			.authenticated();
		// @formatter:on
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

}
