package com.djbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.djbank.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from User u where u.username=?1")
	User findByUsername(String string);

	@Query("select u from User u where u.account_number=?1")
	User findByAccount_Number(int account_number);
}
