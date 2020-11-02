package com.example.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


@Configuration
public class UserDetailsSecurityConfig {

	//We can configure this by extending WebSecurityConfigurerAdpater also.
	@Bean	
	public UserDetailsService userDetailsService() {

		var userDetailsManager = new InMemoryUserDetailsManager();
		
		var user = User.withUsername("admin").password("admin").authorities("read").build();
		
		userDetailsManager.createUser(user);

		return userDetailsManager;
	}
	
	@Bean
	@Profile("db")
	public UserDetailsService userDetailsServiceDB(DataSource dataSource) {
		
		var userDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		return userDetailsManager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}