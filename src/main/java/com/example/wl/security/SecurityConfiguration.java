package com.example.wl.security;

import com.example.wl.datamodel.dao.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LibraryUserDetailsService libraryUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/book/**").permitAll()
                .anyRequest().authenticated();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(libraryUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
