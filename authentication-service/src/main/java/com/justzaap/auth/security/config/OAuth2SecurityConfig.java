package com.justzaap.auth.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.justzaap.auth.security.service.OAuth2UserDetailService;
import com.justzaap.auth.security.util.JustZaapPasswordEncoder;

//@Configuration
//@EnableWebSecurity
//@Order(Ordered.LOWEST_PRECEDENCE - 6)
//public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	@Qualifier("dataSource")
//	DataSource dataSource;
//
//	@Autowired
//	private OAuthUserDetailService oauthUserDetailService;
//
//	@Autowired
//	CustomSecuritySuccessHandler customSecuritySuccessHandler;
//
//	@Autowired
//	CustomSecurityFailureHandler customSecurityFailureHandler;
//
//	@Autowired
//	private JustZaapPasswordEncoder justZaapPasswordEncoder;
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**");
//		//web.ignoring().antMatchers("/api/v1/**");
//	}
//
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests()
//				.antMatchers("/", "/login.html", "/app/**", "/assets/**", "/login","/failure",
//						"/register", "/test/*").permitAll().anyRequest().authenticated();
//		http.formLogin().loginPage("/login").failureUrl("/")
//				.successHandler(customSecuritySuccessHandler)
//				.failureHandler(customSecurityFailureHandler).permitAll().and()
//
//				.logout().logoutSuccessUrl("/login").permitAll();
//		
//		return;
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder authManagerBuilder)
//			throws Exception {
//		
//		authManagerBuilder.userDetailsService(oauthUserDetailService)
//				.passwordEncoder(justZaapPasswordEncoder);
//	}
//	
//}