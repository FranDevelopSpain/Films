package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.authorizeHttpRequests()
        	.requestMatchers(HttpMethod.GET, "/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_CINECHECKER")//Todos los usuarios pueden hacer peticiones GET
        	.requestMatchers(HttpMethod.POST, "/cine**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CINECHECKER")
        	.requestMatchers(HttpMethod.PUT, "/cine**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CINECHECKER")
        	.anyRequest().hasAuthority("ROLE_ADMIN") //Sólo el admin tiene permisos para todas las demás peticiones
        	.and().httpBasic();
        return http.build();
    }
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.authorities("ROLE_ADMIN")
				.build();
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("user"))
				.authorities("ROLE_USER")
				.build();
		UserDetails cineChecker = User.builder()
				.username("cineChecker")
				.password(passwordEncoder().encode("cineChecker"))
				.authorities("ROLE_CINECHECKER")
				.build();
				
		return new 	InMemoryUserDetailsManager(user, admin, cineChecker);	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
