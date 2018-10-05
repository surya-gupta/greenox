package com.greenox.order.security;


import com.greenox.order.dao.AuthorizationAndAuthenticationDao;
import com.greenox.order.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()
                .and().requestCache().requestCache(new NullRequestCache())
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
        //auth.inMemoryAuthentication().withUser("surya").password("{noop}test").roles("ORDER_ENTRY", "REPORTS");
    }
}

@Component
class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    @Autowired
    private AuthorizationAndAuthenticationDao authorizationAndAuthenticationDao;

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        //Authenticate and Assign Roles to User for Access
        User user = authorizationAndAuthenticationDao.authenticate(username, password);
        if (null != user) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            String displayName = user.getUsername();
            user.getRoles().forEach(value -> grantedAuths.add(new SimpleGrantedAuthority("ROLE_"+value)));
            final UserDetails principal = new org.springframework.security.core.userdetails.User(displayName, password, grantedAuths);
            return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}