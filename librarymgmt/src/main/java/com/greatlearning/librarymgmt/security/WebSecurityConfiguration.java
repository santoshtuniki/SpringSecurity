package com.greatlearning.librarymgmt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.librarymgmt.serviceImpl.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsServiceImpl getUserDetailsServiceImpl() {
		UserDetailsServiceImpl impl = new UserDetailsServiceImpl();
		return impl;
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getUserDetailsServiceImpl());
		auth.setPasswordEncoder(getBCryptPasswordEncoder());
		return auth;
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(getDaoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/books/save", "/books/showFormForAdd", "/books/403").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/books/showFormForUpdate", "/books/delete").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginProcessingUrl("/login").successForwardUrl("/books/list").permitAll()
			.and()
			.logout().logoutSuccessUrl("/login").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/books/403")
			.and()
			.cors().and().csrf().disable();
	}

}