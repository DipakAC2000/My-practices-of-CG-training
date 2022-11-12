package com.djbank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.djbank.dto.AccountDto;
import com.djbank.model.User;
import com.djbank.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	//post user
	@PostMapping("/post/user")
	public User postUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	//get all users
	@GetMapping("/get/user")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	//get user by user-name
	@GetMapping("/get/user/{username}")
	public User getUser(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}
	
	//update user details 
	@PutMapping("/put/user/{username}") 
	public User updateUser(@PathVariable("username") String username,@RequestBody User user1) {
		User user2 =userRepository.findByUsername(username);
		
		if(user1.getEmail() != null)
			user2.setEmail(user1.getEmail());
		if(user1.getAddress()!= null)
			user2.setAddress(user1.getAddress());
		if(user1.getMobile_no() != null)
			user2.setMobile_no(user1.getMobile_no());
		
		return userRepository.save(user2);
	}
	
	
	//get all information of account
	@GetMapping("/get/AccountInfo")
	public List<AccountDto> getAccountInfo() {
		List<User> list = userRepository.findAll();
		
		List<AccountDto> dtoList = new ArrayList<>();
		
		list.stream().forEach(u->{
			AccountDto dto = new AccountDto();
			
			dto.setAccount_number(u.getAccount_number());
			dto.setBalance(u.getBalance());
			
			dtoList.add(dto);
		});
	 return dtoList;
	}
	
	//update the account after fund transfer
	@PutMapping("/put/payment/{uid}/{account_number}/{amount}")
	public void FundTransfer(@PathVariable("uid") long  uid, @PathVariable("account_number") int account_number,
			@PathVariable("amount") double amount) {
		
		User Sender = userRepository.getById(uid);
		User Receiver = userRepository.findByAccount_Number(account_number);
		
		Receiver.setBalance(Receiver.getBalance() + amount);
		Sender.setBalance(Sender.getBalance() - amount);
		
		userRepository.save(Sender);
		userRepository.save(Receiver);
	}
	
}
