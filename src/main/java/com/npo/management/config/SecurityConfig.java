package com.npo.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.npo.management.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {

    http
        .csrf(csrf -> csrf.disable())

        .sessionManagement(session ->
            session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
            )
        )

        .authorizeHttpRequests(auth -> auth
           .requestMatchers(
    "/",
    "/index.html",
    "/login.html",
    "/register.html",
    "/donations.html",

    "/admin-dashboard.html",
    "/helper-dashboard.html",
    "/helper-requests.html",
    "/beneficiary-dashboard.html",
    "/history.html",
    "/leaderboard.html",
    "/inventory.html",

    "/css/**",
    "/js/**",

    "/register",
    "/login",
    "/donations",
    "/logo.png",
    "/QR.png",
    "/admin-donations.html",
    "/beneficiary-help-requests.html",
    "/beneficiary-dashboard.html",
    "/leaderboard.js",
    "/beneficiary-dashboard.html",
    "/child-management.html",
    "/children-records.html",
    "/staff-management.html",
    "/staff-records.html",
    "/education-management.html",
    "/education-records.html",
    "/inventory-records.html",
    "/users.html",
    "/helpers.html",
    "/inventory.html",
    "/helper-requests.html"

).permitAll()
            .requestMatchers("/admin/users", "/admin/helpers").hasRole("ADMIN")
            .requestMatchers("/leaderboard").hasAnyRole("ADMIN", "HELPER")
            .requestMatchers("/help-requests").hasAnyRole("ADMIN", "BENEFICIARY")
            .requestMatchers("/admin/children/**").hasAnyRole("ADMIN")
            .requestMatchers("/admin/staff/**").hasRole("ADMIN")
            .requestMatchers("/admin/education/**").hasRole("ADMIN")
            .requestMatchers("/admin/inventory/**").hasRole("ADMIN")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/helper/**").hasRole("HELPER")
            .anyRequest().authenticated()
        )

        .addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class
        );

    return http.build();
}

    
}
