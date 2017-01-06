package com.justzaap.auth.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import com.justzaap.auth.security.service.JwtUserDetailService;
import com.justzaap.auth.security.util.JustZaapPasswordEncoder;

@Configuration
public class JwtAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	@Qualifier("jwtUserDetailService")
	private JwtUserDetailService jwtUserDetailService;

	@Autowired
	private JustZaapPasswordEncoder justZaapPasswordEncoder;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailService).passwordEncoder(justZaapPasswordEncoder);

	}
}