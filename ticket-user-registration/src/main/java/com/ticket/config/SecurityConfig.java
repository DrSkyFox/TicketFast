package com.ticket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth,
                              LssUserDetailsService userDetailService,
                              PasswordEncoder passwordEncoder) throws Exception {



        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(provider);

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("root"))
                .roles("ADMIN");

    }

     // @formatter:on


    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
                .authorizeRequests()
                .antMatchers(
                        "/signup",
                        "/reg",
                        "/sendmail",
                        "/forgotPassword*",
                        "/account/resetPassword*",
                        "/account/changePassword*",
                        "/account/savePassword*",
                        "/badUser",
                        "/account/registrationConfirm*",
                        "/webjars/**",
                        "/css/**",
                        "/js/**",
                        "/img/**").permitAll()
                .antMatchers("/delete/**").hasRole("ADMIN")

                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/doLogin")
                .successForwardUrl("/home")
                .and().logout().permitAll().logoutUrl("/doLogout")
                .and()
                .csrf().disable();

    } // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
