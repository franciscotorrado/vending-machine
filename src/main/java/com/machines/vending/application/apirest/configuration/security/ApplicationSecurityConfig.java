package com.machines.vending.application.apirest.configuration.security;

import com.machines.vending.domain.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    public ApplicationSecurityConfig(final AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/user").permitAll()
                .antMatchers("/deposit","/reset").hasRole(Role.BUYER.name())
                .antMatchers("/products").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll();

        httpSecurity
                .logout(logout -> logout
                        .logoutUrl("/logout/all")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));

        httpSecurity.httpBasic().authenticationEntryPoint(authenticationEntryPoint);

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
