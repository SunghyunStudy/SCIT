package net.datasa.spring5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 시큐리티 환경설정
 */
// Spring이 이 클래스를 설정 클래스로 인식하여 빈 등록 대상으로 만듬
@Configuration // 스프링 설정 클래스임을 명시
@EnableWebSecurity // Spring Security 활성화
// "메서드 단위 보안" 기능을 활성화 하는 Annotation
// - @PreAuthorize 메서드 실행 "전"에 권한 검사
// - @PostAutorize 메서드 실행 "후"에 결과를 보고 권한 검사
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
        // 로그인 없이 접근 가능 경로
        private static final String[] PUBLIC_URLS = {
                        "/" // root
                        , "/images/**" // 이미지 경로
                        , "/css/**" // CSS파일들
                        , "/js/**" // JavaSCript 파일들
                        , "/thymeleaf", "/member/join", "/member/idCheck"// 회원가입 "/member/idCheck" // ID중복확인
                        , "/member/info" // 개인정보 수정
                        , "/member/inactive", "/member/test" // 임시
                        , "/board/listAll" // 게시판 전체 목록 보기
                        , "/board/list" // 게시판 목록
                        , "/board/read" // 게시판 글 읽기
                        , "/board/download", "/board/preview", "/board/reply"

        };

        /**
         * 시큐리티 필터 설정 메서드
         * - 인증 및 인가, 로그인/로그아웃, CORS, CSRF 설정 등을 포함함
         */
        @Bean
        protected SecurityFilterChain config(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(author -> author
                                                .requestMatchers(PUBLIC_URLS).permitAll()
                                                .requestMatchers("/member/loginForm", "/member/login").permitAll()
                                                .requestMatchers("/admin/**").hasRole("ADMIN") // role이 admin인 경우에만
                                                                                               // admin/ 경로의 페이지에 접속가능
                                                .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                                )
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/member/loginForm") // 로그인폼 페이지 경로
                                                .usernameParameter("id") // (logiForm의 name과 일치해야됨)
                                                .passwordParameter("password") // (logiForm의 name과 일치해야됨)
                                                .loginProcessingUrl("/member/login") // 로그인폼 제출하여 처리할 경로 // 컨트롤러에 매핑 만들지
                                                                                     // 않아도 자동 처리됨
                                                .defaultSuccessUrl("/", true)
                                                .permitAll() // 로그인 페이지는 모두 접근 허용
                                )
                                .logout(logout -> logout
                                                .logoutUrl("/member/logout") // 로그아웃 처리 경로
                                                .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 경로
                                );

                // 개발용 설정: CORS, CSRF 비활성화 (실제 서비스 시에는 활성화 필요)
                http
                                .httpBasic(AbstractHttpConfigurer::disable)
                                .cors(AbstractHttpConfigurer::disable)
                                .csrf(AbstractHttpConfigurer::disable);

                return http.build();
        }

        @Bean
        public BCryptPasswordEncoder getPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
