package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * XXcontroller invoked it during Authentication
 * @author Rabi
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Value("${jwt.username1}")
	private  String USER_NAME1;  //javainuse
	
	@Value("${jwt.password1}")
	private String USER_PASSWORD1;  //password (encrypted)
	
	@Value("${jwt.username2}")
	private String USER_NAME2; //Roddur
	
	@Value("${jwt.password2}")
	private String USER_PASSWORD2; //tutu (non Encrypted)
	
	/**
	 * Encoded PAssoword using https://www.javainuse.com/onlineBcrypt
	 * e.g 
	 * password ="tutu"
	 * encoded password="$2a$10$1hPXDFtly4NmR2hiJ2CCzO0bd06V2mcSWcAk1Sx2M8H7F.d17gWkS"
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (USER_NAME1.equals(username)) { //javainuse/password(Encrypted)
			return new User(USER_NAME1, USER_PASSWORD1,new ArrayList<>());
		} 
		else if (USER_NAME2.equals(username)) {//Roddur/tutu
			return new User(USER_NAME2, USER_PASSWORD2,new ArrayList<>());
		} 
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}