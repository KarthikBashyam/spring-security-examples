package com.example.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.entity.User;
import com.example.repository.UserRepository;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.*")
@EntityScan(basePackages = "com.example.*")
public class SpringSecurityApplication {

	public static void main(String[] args) {
		//System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "memory");
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
	
	@Bean
	CommandLineRunner startup(UserRepository userRepository) {
		return (args) -> {
			var user = new User("admin","admin");
			userRepository.save(user);			
			userRepository.flush();
		};
		
	}

}