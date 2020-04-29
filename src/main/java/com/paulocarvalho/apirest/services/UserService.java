package com.paulocarvalho.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulocarvalho.apirest.domain.User;
import com.paulocarvalho.apirest.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}

}
