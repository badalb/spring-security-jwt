package com.justzaap.resourceserver.config;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration  extends ResourceServerConfigurerAdapter {

	@Value("${resource.id:justzaap-authentication-service}")
	private String resourceId;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.PATCH, "/api/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/api/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("#oauth2.hasScope('write')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

    }


    @Bean
    public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
    }
    
    @SuppressWarnings("deprecation")
	@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    // converter.setSigningKey("123");
    final Resource resource = new ClassPathResource("public.txt");
    String publicKey = null;
    try {
        publicKey = IOUtils.toString(resource.getInputStream());
    } catch (final IOException e) {
        throw new RuntimeException(e);
    }
    converter.setVerifierKey(publicKey);
    return converter;
    }
    
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }
    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices()).resourceId(resourceId);
    }

}