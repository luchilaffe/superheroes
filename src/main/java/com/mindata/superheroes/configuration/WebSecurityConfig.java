package com.mindata.superheroes.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.mindata.superheroes.interceptor.JwtRequestFilter;
import com.mindata.superheroes.utils.RestEndpoints;

/**
 * Configuration of Web Security that implements WebSecurityConfigurerAdapter.
 * 
 * @author carlos.lafferriere
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService jwtUserDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * Configure AuthenticationManager so that it knows from where to load user for matching
         * credentials. Use BCryptPasswordEncoder.
         */
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    public WebSecurityConfig(UserDetailsService jwtUserDetailsService,
            JwtRequestFilter jwtRequestFilter) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /** We don't need CSRF */
        httpSecurity.csrf().disable()
                /** Don't authenticate these particular requests */
                .authorizeRequests()
                /** Method to add Users */
                .antMatchers(HttpMethod.POST, RestEndpoints.USER_ADD).permitAll()
                /** Method to authenticate Users */
                .antMatchers(RestEndpoints.USER_AUTH).permitAll()
                /** All other requests need to be authenticated */
                .anyRequest().authenticated().and().exceptionHandling().and()
                /** Use stateless session. Session won't be used to store user's state. */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /** Add a filter to validate the tokens with every request */
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
