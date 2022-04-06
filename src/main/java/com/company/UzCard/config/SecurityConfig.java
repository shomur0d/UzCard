package com.company.UzCard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final AuthenticationManagerBuilder auth)throws Exception{

        auth.inMemoryAuthentication()
                .withUser("BANK").password("{noop}123bank").roles("bank")
                .and()
                .withUser("PAYMENT").password("{noop}123pay").roles("payment")
                .and()
                .withUser("admin").password("{noop}123admin").roles("admin");
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception{

        /*http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();*/
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/profile/**").hasRole("bank")
                .antMatchers("/card/**").permitAll()
                .antMatchers("/transaction/**").hasAnyRole("bank", "payment")
                .antMatchers("/transaction/cid/**").hasAnyRole("bank", "payment")
                .antMatchers("/transaction/pid/*").hasRole("bank")
                .anyRequest().authenticated()
                .and().httpBasic();
    }


}
