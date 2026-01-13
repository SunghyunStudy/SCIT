package net.dima.prac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .anyRequest().permitAll() // 모든 요청 허용 (로그인 화면 안 뜸)
            )
            .csrf((csrf) -> csrf.disable()) // CSRF 비활성화 (POST 요청 테스트 위해 필수)
            .headers((headers) -> headers.frameOptions((frame) -> frame.disable())); // h2-console 등 iframe 허용

        return http.build();
    }
}
