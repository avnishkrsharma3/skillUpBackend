package com.cognizant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * author Avnish
 * Security Configuration class
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * To override the auto configurations provided by spring security
	 * 
	 */
	public static final String[] PUBLIC_URLS = {
			"/api/v1/auth/**", 
			"/v3/api-docs", 
			"/v2/api-docs",
			"/swagger-resources/**", 
			"/swagger-ui/**", "/webjars/**"
			};
	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		 http.headers().frameOptions().disable();
		http.csrf().disable().authorizeRequests()
		.antMatchers(PUBLIC_URLS).permitAll()
		.antMatchers(HttpMethod.POST, "/**")
		.permitAll()
				.antMatchers(HttpMethod.GET, "/**").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().permitAll();
	}
}
