package com.porlabs.templates.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class PlabsResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment environment;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // allow anonymous resource requests
                .antMatchers(HttpMethod.GET, "/", "/*.html","/*.jpg", "/favicon.ico","/**/assets/**", "/**/*.do", "/**/*.jsp", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .antMatchers(HttpMethod.GET, "/home/**", "/home", "/login").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/h2/**","/h2").permitAll()
                .antMatchers("/doc","/v2/**","/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/hc").permitAll()
                .antMatchers(HttpMethod.POST,"/user/authenticate/**").permitAll()
                .antMatchers(HttpMethod.GET,"/excel/**").permitAll()
                .antMatchers(HttpMethod.GET,"/download/**").permitAll()
                .antMatchers(HttpMethod.GET,"/loginUser").permitAll()
                .anyRequest()
                .authenticated()
//                .and()
//                .formLogin()
                .and()
                .httpBasic();
    }

    @Bean
    public TokenStore tokenStore() {
        final DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(environment.getProperty("token.database.driver"))
                .url(environment.getProperty("token.database.url"))
                .username(environment.getProperty("token.database.username"))
                .password(environment.getProperty("token.database.password"))
                .build();
        return new JdbcTokenStore(dataSource);
    }
}