package com.jonak.rnd.config;

import com.jonak.rnd.service.CustomRemoteTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/*
Fahim created at 11/12/2020
*/
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resource-server-rest-api");
    }

    @Bean
    public ResourceServerTokenServices tokenService() {
        CustomRemoteTokenService tokenServices = new CustomRemoteTokenService();
        return tokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().anonymous()
                .and().authorizeRequests().antMatchers("/api/**").authenticated()
                .antMatchers("/public/**").permitAll();
    }
}