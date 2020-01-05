package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig 
		extends WebSecurityConfigurerAdapter{

	// 2 ROLE defined for AUTH user!!!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
					.withUser("Roddur").password("{noop}p") // M-2 "{noop}p"
					.roles("ADMIN");  //ADMIN Role
		
		auth.inMemoryAuthentication()
				.withUser("Swagata").password("{noop}p")
				.roles("USER"); //USER Role ....
		 
		}
	
	
	
	
	// Case#1 - Allowed Any URL  (Auth+No Auth ) .."/auth" and "/noAuth"
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	}*/
	
	//Case#2A - Allowed AUTH  ONLY!!!   "/auth" -****ADMIN+USER Role****
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/auth/**").fullyAuthenticated().and().httpBasic();
	}*/
	
	//Case#2B - Allowed AUTH  ONLY!!!   "/auth" -****ADMIN ONLY****
		/*@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.authorizeRequests()
			.antMatchers("/auth/**")
			.hasAnyRole("ADMIN").anyRequest()
			.fullyAuthenticated().and().httpBasic();
		}*/
	
	//Case-3 M-1  Access from Angular Login Page...Any URL will be allowed.
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors(); //Allow Angular to Access
			http.csrf().disable();
			http.authorizeRequests().antMatchers("/**")
				.fullyAuthenticated().and().httpBasic();
		}
		
	//CASE-3 M-2 Access from Angular Login Page	
		/*@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors(); //Allow Angular to Access
			http.csrf().disable().
					authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**")
					.permitAll().anyRequest().authenticated()
					.and().httpBasic();
		}*/

	
	/*
	 * Alternatively use .password("{noop}p")  
	 */ 
	/*@Bean
	private static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();

	}*/
	
	
}
