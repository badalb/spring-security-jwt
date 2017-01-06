package com.justzaap.auth.security.service;

import org.springframework.stereotype.Service;

import com.justzaap.auth.security.model.User;

@Service
public interface UserService {
	public User findByUserName(String userName);

	public User findUserById(String userId);

}
