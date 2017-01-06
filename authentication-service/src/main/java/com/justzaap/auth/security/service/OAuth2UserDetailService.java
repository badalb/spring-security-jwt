package com.justzaap.auth.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.justzaap.auth.security.model.User;

@Component("oAuthUserDetailService")
public class OAuth2UserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	// @Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + userName + " not found");
		}
		return new OAuth2User(user);
	}

}
