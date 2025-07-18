package dev.golf_app.config;

import dev.golf_app.util.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Autowired
  private JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth
          .anyRequest().permitAll()
//        .requestMatchers("/api/auth/**",
//          "/api/users/register",
//          "/v3/api-docs/**",
//          "/swagger-ui/**",
//          "/swagger-ui.html")
//        .permitAll()
//        .anyRequest().authenticated()
//      )
//      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

