package com.djbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.djbank.security.service.MyUserServiceDetails;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserServiceDetails myUserServiceDetails;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthProvider());
		
		
	}
	
	private AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(myUserServiceDetails);
		auth.setPasswordEncoder(getPassEncoder());
		
		return auth;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers(HttpMethod.POST,"/post/user").permitAll()
					.antMatchers(HttpMethod.GET,"/get/user").permitAll()
					.antMatchers(HttpMethod.GET,"/get/user/{username}").permitAll()
					.antMatchers(HttpMethod.PUT,"/put/user/{username}").permitAll()
					.antMatchers(HttpMethod.PUT,"/put/payment/{uid}/{account_number}/{amount}").permitAll()
					.anyRequest().permitAll()
					.and()
					.httpBasic()
					.and()
					.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder getPassEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
