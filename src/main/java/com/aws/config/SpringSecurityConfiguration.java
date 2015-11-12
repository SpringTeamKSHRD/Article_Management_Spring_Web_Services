package com.aws.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT username,password,enable FROM tbuser WHERE username=?")
			.authoritiesByUsernameQuery("SELECT username,role FROM tbuser WHERE username=? ");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.authorizeRequests().antMatchers("/","/home").permitAll();
		//http.authorizeRequests().antMatchers("/admin/**").access("hasRole('admin')");
		//http.authorizeRequests().antMatchers("/author/**").access("hasRole('author')");
		http
			.formLogin()
				.and()
			.authorizeRequests()
				//.antMatchers("/","/home").permitAll()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
				//.antMatchers("/author/**").hasRole("author");
		
		//http.exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	
	
	
	
	
	
	
	
	

	
}






