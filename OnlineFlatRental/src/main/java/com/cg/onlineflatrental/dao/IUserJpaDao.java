package com.cg.onlineflatrental.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.onlineflatrental.model.*;


@Repository
public interface IUserJpaDao extends JpaRepository<User,Integer> {
//	@Query("select u from User u where u.userName=userName and u.password=password ")
//	User validateUser(String userName,String password);
	@Query("SELECT u FROM User u WHERE u.userName=?1 AND u.password=?2")
	User validateUser(String username,String password);




}
