//package com.example.artisan.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.example.artisan.filter.JwtTokenFilter;
//import com.example.artisan.util.JwtTokenUtil;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig{
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenUtil);
//
//        http.csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//            .requestMatchers("/api/users/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//}