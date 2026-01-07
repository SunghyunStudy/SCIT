package net.datasa.spring2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("ck")
public class CookieController {

	@GetMapping("cookie1")
	public String cookie1(HttpServletResponse response) { // 응답개체에 쿠키를 넣어줘서 보내줄것이다.

		// 쿠키 생성(키, 값)
		Cookie c1 = new Cookie("str", "abcde");
		Cookie c2 = new Cookie("num", "123");

		// 쿠키 종료 시점 지정(쿠키는 수명이 존재)
		// 쿠키 수명, setMaxAge(초)는 쿠키의 유효기간을 초 단위로 설정
		// 설정이 없으면 브라우저를 닫을 때 쿠키가 삭제됨.

		c1.setMaxAge(60 * 60 * 24 * 3); // 쿠키의 수명 설정
		c2.setMaxAge(60 * 60 * 24 * 3); // 3일임

		// 쿠키 경로 설정 -----> 자동으로 들고가려는 경로를 설정하려는것
		c1.setPath("/"); // --> 해당 도메인의 모든 경로에 대해 브라우저가 자동으로 쿠키를 전송
		c2.setPath("/");

		// 클라이언트로 쿠키를 보내기 --> 위에 response로 보냄.
		response.addCookie(c1);
		response.addCookie(c2);

		return "redirect:/";
	}

	// 쿠키 삭제
	@GetMapping("cookie2")
	public String cookie2(HttpServletResponse response) {

		// 이미 저장된 쿠키와 같은 이름을 가진 쿠키를 생성
		Cookie c3 = new Cookie("str", null);
		Cookie c4 = new Cookie("num", null);
		// 쿠키의 수명을 0초로 지정
		c3.setMaxAge(0);
		c4.setMaxAge(0);
		c3.setPath("/");
		c4.setPath("/");
		// 쿠키를 클라이언트로 전송
		response.addCookie(c3);
		response.addCookie(c4);

		return "redirect:/";
	}

	// 쿠키 읽기
	@GetMapping("cookie3")
	public String cookie3(
			// 요청시 HTTP 요청 헤더에서 Cookie 값을 수출하여 매핑
			@CookieValue(name = "str", defaultValue = "없음") String str,
			@CookieValue(name = "num", defaultValue = "0") int num) {
		log.debug("쿠키 str: {}, num: {}", str, num);
		return "redirect:/";
	}

}
