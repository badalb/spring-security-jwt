package com.justzaap.auth.jwt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.justzaap.auth.security.service.JwtUserDetailService;
import com.justzaap.auth.security.util.JustZaapPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 6)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private JwtUserDetailService jwtUserDetailService;


	@Autowired
	private JustZaapPasswordEncoder justZaapPasswordEncoder;
	
//	@Autowired
//	private Environment env;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/", "/login.html", "/app/**", "/assets/**", "/login","/failure",
						"/register", "/test/*").permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login").failureUrl("/").permitAll().and()

				.logout().logoutSuccessUrl("/login").permitAll();
		
		return;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder)
			throws Exception {
		
		authManagerBuilder.userDetailsService(jwtUserDetailService)
				.passwordEncoder(justZaapPasswordEncoder);
	}
	
}