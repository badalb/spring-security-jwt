package com.justzaap.auth.jwt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class JwtAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${resource.id:justzaap-authentication-service}")
	private String resourceId;

	@Autowired
	DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    // converter.setSigningKey("123");
    final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
    return converter;
    }

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		 clients.jdbc(dataSource);

//		clients.inMemory().withClient("my-trusted-client").authorizedGrantTypes("password", "refresh_token")
//				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust")
//				.accessTokenValiditySeconds(600).refreshTokenValiditySeconds(6000).and()
//				.withClient("my-trusted-client-with-secret")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("read", "write", "trust").secret("somesecret")
//				.and().withClient("my-less-trusted-client").authorizedGrantTypes("authorization_code", "implicit")
//				.authorities("ROLE_CLIENT").scopes("read", "write", "trust").and()
//				.withClient("my-less-trusted-autoapprove-client").authorizedGrantTypes("implicit")
//				.authorities("ROLE_CLIENT").scopes("read", "write", "trust").autoApprove(true);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter());

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}

}