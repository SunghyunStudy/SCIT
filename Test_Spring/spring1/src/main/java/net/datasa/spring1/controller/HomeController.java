package net.datasa.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
	Spring에게 "이 클래스는 HTTP 요청을 처리하는 클래스입니다"라고 명시
 */
@Controller // 요청을 처리할 수 있는 클래스로 만듦
public class HomeController {
	// http://localhost:9991/ : 루트 경로(오리진경로 = 기본경로..?)
	@GetMapping({ "", "/" }) // 루트 경로 뒤에 아무것도 없거나 /가 있으면 home페이지로.
	public String home() {
		// resources/templates 패키지에서 해당 html 찾음
		return "home"; // home이라는 이름의 html 파일을 templates 폴더에서 찾음
	}
}
