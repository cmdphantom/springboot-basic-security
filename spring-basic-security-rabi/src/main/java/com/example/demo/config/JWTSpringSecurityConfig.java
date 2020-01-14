package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService; //for JWT
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //for JWT
//@EnableGlobalMethodSecurity(prePostEnabled = true) //for JWT
public class JWTSpringSecurityConfig 
		extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService jwtUserDetailsService;//for JWT  JwtUserDetailsService.java-loadUserByUsername()

	@Autowired
	private JwtRequestFilter jwtRequestFilter;//for JWT
		
	/**
	 * Case 4 JWT
	 * 1. configure AuthenticationManager so that it knows from where to load
	 * 2. user for matching credentials  Use BCryptPasswordEncoder
	 * 3. JwtUserDetailsService.java >> loadUserByUsername()
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService);
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 1. dont authenticate this particular request
	 * 2. all other requests need to be authenticated
	 * 3.  make sure we use stateless session; session won't be used to
	 * 4. store user's state.
	 * 5. Add a filter to validate the tokens with every request
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors(); //Allow Angular to Access
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/authenticate")
				.permitAll().
				 anyRequest().authenticated().and().
				exceptionHandling()
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
		
	//Case-4 JWT implemantation --End
	/*
	 * Non-Encoded Password
	 * Alternatively use .password("{noop}p")  
	 */ 
	/*@Bean
	private static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();

	}*/
	
	/**
	 * To Encode PAssword
	 * Encoded PAssoword using https://www.javainuse.com/onlineBcrypt
	 * e.g 
	 * password ="tutu"
	 * encoded password="$2a$10$1hPXDFtly4NmR2hiJ2CCzO0bd06V2mcSWcAk1Sx2M8H7F.d17gWkS"
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
