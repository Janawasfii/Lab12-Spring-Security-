package com.example.labspringsecurity.Config;

import com.example.labspringsecurity.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {
    private final MyUserDetailsService myUserDetailsService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //focus on username
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        //focus on password
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/update/{id}").hasAuthority("USER")
                .requestMatchers("/api/v1/auth/delete/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/blog/get-all").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/blog/get-my").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/update/{blog_id}/{auth_id}").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/delete/{blog_id}/{auth_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/blog/getBlogById/{blog_id}/{auth_id}").hasAuthority("USER")
                .requestMatchers("/api/v1/blog/getBlogByTitle/{title}/{auth_id}").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();}




}
