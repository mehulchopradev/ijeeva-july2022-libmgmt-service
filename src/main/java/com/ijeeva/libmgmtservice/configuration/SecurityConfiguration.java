package com.ijeeva.libmgmtservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("mehul").password("{noop}123").roles("USER") // {noop} tells spring security this is a plain text password
			.and()
			.withUser("admin").password("{noop}admin123").roles("USER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/books/**").hasRole("USER")
			.antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
			.and()
            .csrf().disable()
            .formLogin().disable();
	}
}
