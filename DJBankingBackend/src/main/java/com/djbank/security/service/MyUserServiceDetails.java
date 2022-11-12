package com.djbank.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.djbank.repository.UserRepository;

@Service

public class MyUserServiceDetails implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 * First go to db and check whether the username is present or not
		 * If present fetch the details
		 * If not present then throw  UsernameNotFoundException
		 */
		
		com.djbank.model.User user1 = userRepository.findByUsername(username);
		if(user1 == null)
			throw new UsernameNotFoundException("Invalid Details");
		
		/*
		 * convert role to authority as spring user doesn't accept string values it needs 
		 * list of authority as a parameter
		 */
		List<GrantedAuthority> list = new ArrayList<>();
		
		org.springframework.security.core.userdetails.User springUser = 
				new org.springframework.security.core.userdetails.User(user1.getUsername(),user1.getPassword(),list);
		
		/*
		 * Adding username password and list of granted authorities details of database to spring internal user
		 */
		
		return springUser;
	}

}

