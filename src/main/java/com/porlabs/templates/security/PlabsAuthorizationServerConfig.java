package com.porlabs.templates.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;




@Configuration
@EnableAuthorizationServer
public class PlabsAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{


    @Lazy
    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private UserDetailsService plabUserDetailsService;

    @Autowired
    private PasswordEncoder clientPasswordEncoder;

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer.authenticationManager(authenticationManagerBean)
                .userDetailsService(this.plabUserDetailsService)
                .tokenStore(this.tokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("plabclient")
                .secret("secret")
                .accessTokenValiditySeconds(60*60)
                .refreshTokenValiditySeconds(10000)
//                .scopes(ParamUtil.SCOPE_READ, ParamUtil.SCOPE_WRITE)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, GRANT_TYPE_REFRESH,
//                        GRANT_TYPE_IMPLICIT, GRANT_TYPE_AUTHORIZATION_CODE)
                .and()
                .withClient("psap")
//                .accessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(15L))
//                .scopes(ParamUtil.SCOPE_READ, ParamUtil.SCOPE_WRITE)
//                .authorizedGrantTypes(ParamUtil.CLIENT_CREDENTIALS)
//                .and()
//                .withClient("authapp")
//                .accessTokenValiditySeconds(60*60)
//                .refreshTokenValiditySeconds(5000)
//                .scopes(ParamUtil.SCOPE_READ, ParamUtil.SCOPE_WRITE)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, GRANT_TYPE_REFRESH,
//                        GRANT_TYPE_IMPLICIT, GRANT_TYPE_AUTHORIZATION_CODE,
//                        CLIENT_CREDENTIALS);
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security.passwordEncoder(this.clientPasswordEncoder);
    }


}