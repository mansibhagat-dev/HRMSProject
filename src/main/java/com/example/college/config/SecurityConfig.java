package com.example.college.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // 🌐 Public access
                .requestMatchers("/css/**","/js/**","/images/**").permitAll()
                .requestMatchers("/login", "/register").permitAll()

                // 👨‍💼 ADMIN ONLY
                .requestMatchers("/employees/**", "/departments/**").hasRole("ADMIN")

                // 👤 ADMIN + USER
                .requestMatchers("/leaves/**").hasAnyRole("ADMIN", "USER")

                // 📊 Dashboard (any logged-in user)
                .requestMatchers("/dashboard").authenticated()

                // 🔐 All other requests
                .anyRequest().authenticated()
            )

            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}