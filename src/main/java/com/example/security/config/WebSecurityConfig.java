package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authenticationProvider(authenticationProvider);
		http.authorizeRequests().mvcMatchers("/h2-console/**").permitAll().anyRequest().authenticated();
	}

	 /* 
	  
	// @formatter:off
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		var userDetailsService = new InMemoryUserDetailsManager();
		
		var user = User.withUsername("admin").password("admin").authorities("read").build();
		userDetailsService.createUser(user);
		
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
		
		
	}
	
	// @formatter:on */
	

}
