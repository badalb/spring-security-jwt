package com.justzaap.auth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import com.justzaap.auth.security.service.OAuth2UserDetailService;
import com.justzaap.auth.security.util.JustZaapPasswordEncoder;

//@Configuration
public class OAuth2AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter{

	@Autowired
	@Qualifier("oAuthUserDetailService")
	private OAuth2UserDetailService oAuthUserDetailService;

	
	
	@Autowired
	private JustZaapPasswordEncoder justZaapPasswordEncoder;
	
      
    @Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(oAuthUserDetailService).passwordEncoder(justZaapPasswordEncoder);

	}
}
