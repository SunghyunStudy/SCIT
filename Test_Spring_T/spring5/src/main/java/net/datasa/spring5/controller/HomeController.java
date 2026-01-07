package net.datasa.spring5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

	@GetMapping({"", "/"})
	public String home() {
		return "home";
	}
	
	@GetMapping("thymeleaf")
	public String thymeleaf(
			@AuthenticationPrincipal UserDetails user
			) {
		
		/**
		 * @AuthenticationPrincipal
		 *  - Spring Security가 직접 로그인한 사용자 정보를 주입해줌
		 *  - 로그인 성공 > SecurityContext에 Authentication 객체 저장
		 *  - Controller 에서 Authentication 객체에 접근
		 */
		if (user != null) {
			log.debug("== Authentication 객체 정보 출력 ==");
			log.debug( user.getUsername()                 );
			log.debug( user.getPassword()                 );
			log.debug("" + user.getAuthorities()          );
			log.debug("" + user.isAccountNonExpired()     );
			log.debug("" + user.isAccountNonLocked()      );
			log.debug("" + user.isCredentialsNonExpired() );
			log.debug("" + user.isEnabled()               );
		} else {
			log.debug("로그인 안 된 상태");
		}
		
		
		return "thymeleaf";
	}
}
