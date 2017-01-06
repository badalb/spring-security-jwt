package com.justzaap.auth.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justzaap.auth.security.model.User;
import com.justzaap.auth.security.persistence.UserRepository;
import com.justzaap.auth.security.util.AuthConstant;

@Service
public class UserServiceImpl implements UserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository users;

	// @Override
	public User findByUserName(String userName) {
		return users.findByUserName(userName);
	}

	// @Override
	public User findUserById(String userId) {
		log.info("Request to fetch user with Id : " + userId);
		User user = users.findByUserIdAndIsDeleted(userId, AuthConstant.IS_NOT_DELETED);
		log.info("Request to fetch user with Id success : " + user);
		return user;
	}

}
