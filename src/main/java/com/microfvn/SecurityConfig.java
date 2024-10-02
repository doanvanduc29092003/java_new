package com.microfvn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.microfvn.servicer.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Vô hiệu hoá CSRF nếu không cần thiết
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/fe/**", "/assets/**", "/static/**", "/uploads/**").permitAll()  // Cho phép truy cập các trang công khai
                .requestMatchers("/admin/**").hasAuthority("ADMIN")  // Chỉ cho phép người có quyền "ADMIN" truy cập /admin/**
                .anyRequest().authenticated())  // Các yêu cầu còn lại đều cần xác thực
            .formLogin(login -> login
                .loginPage("/login")  // Trang đăng nhập tùy chỉnh
                .loginProcessingUrl("/login")  // URL xử lý đăng nhập
                .usernameParameter("username")  // Tên tham số username
                .passwordParameter("password")  // Tên tham số password
                .defaultSuccessUrl("/admin", true))  // Sau khi đăng nhập thành công chuyển hướng tới /admin
            .logout(logout -> logout
                .logoutUrl("/admin-logout")  // Đường dẫn cho logout
                .logoutSuccessUrl("/login"))  // Sau khi logout chuyển về trang login
            .userDetailsService(customUserDetailService);  // Cấu hình UserDetailsService

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/static/**", "/fe/**", "/assets/**", "/uploads/**");  // Cho phép truy cập tài nguyên tĩnh mà không cần xác thực
    }
}
