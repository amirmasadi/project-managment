package com.amirasadi.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

  @Autowired
  DataSource dataSource;

  @Autowired
  BCryptPasswordEncoder bCryptEncoder;

  @Bean
  public String configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from user_accounts where username = ?")
            .authoritiesByUsernameQuery("select username, role from user_accounts where username = ?")
            .passwordEncoder(bCryptEncoder);
    return "sag";
  }
/*  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user1 = User.withDefaultPasswordEncoder()
            .username("myuser")
            .password("pass")
            .roles("USER")
            .build();
    UserDetails user2 = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("pass1")
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user1, user2);
  }*/

  @Bean
  SecurityFilterChain web(HttpSecurity http) throws Exception {
    http
            // ...
            .authorizeHttpRequests(authorize -> {
                      try {
                        authorize
                                .requestMatchers("/project/new").hasAuthority("ADMIN")
                                .requestMatchers("/project/save").hasAuthority("ADMIN")
                                .requestMatchers("/employee/new").hasAuthority("ADMIN")
                                .requestMatchers("/employee/save").hasAuthority("ADMIN")
                                .requestMatchers("/", "/**").permitAll().and().formLogin();
                      } catch (Exception e) {
                        throw new RuntimeException(e);
                      }
                    }
            );

    return http.build();
  }
}
