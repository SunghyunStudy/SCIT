package net.datasa.spring2.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("ss")
public class SessionController {

	// 세션에 값 저장
	@GetMapping("session1")
	public String session1(HttpSession session) {

		session.setAttribute("name", "홍길동"); // 세션에다 저장. name이라는 곳에 홍길동이 저장.

		return "redirect:/";
	}

	// 세션 값 읽기.
	@GetMapping("session2")
	public String session2(HttpSession session) {

		String name = (String) session.getAttribute("name");
		log.debug("세션 값: {}", name);
		return "redirect:/";
	}

	// 세션 값 삭제
	@GetMapping("session3")
	public String session3(HttpSession session) {

		session.removeAttribute("name");
		session.invalidate(); // 세션 자체 삭제

		return "redirect:/";
	}

	// 로그인 페이지로 이동
	@GetMapping("login")
	public String login() {
		log.debug("로그인 페이지 이동");
		return "ssView/loginForm";// templates 안의 ssView폴더안에 loginForm이라는 html이 존재해야 함
	}

	@PostMapping("login")
	public String login(@RequestParam("id") String id, // 받은 id를 String id에 맵핑하겠다.
			@RequestParam("pw") String pw, // 받은 pw를 String pw에 맵핑하겠다.
			HttpSession session) {
		log.debug("사용자가 입력한 값= id:{}, pw:{}", id, pw);

		// ID가 "abc"이고, 비밀번호가 "123"인 경우에만 로그인 처리
		if (id.equals("abc") && pw.equals("123")) { // 항상 얘기하지만 Sting이니까 .equals로 비교
			session.setAttribute("loginId", id);
			log.debug("{}님 로그인", id);
			return "redirect:/";
		}
		// 불일치시 로그 출력 후 로그인 페이지
		else {
			log.debug("로그인 실패..");
			return "ssView/loginForm"; // 실패했을 경우에는 ssView/loginForm --> 으로 보낸다.
		}
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId"); // 값만 없애는 거.
		session.invalidate(); // 현재 요청을 보내는 사용자에 대한 세션을 없애는 것.
		return "redirect:/";
	}

	// 로그인 해야만 들어올 수 있는 페이지
	@GetMapping("loginTest")
	public String loginTes(HttpSession session) { // 세션 개체 불러오기

		// 현재 세션에 loginId라고 하는 이름의 값이 존재하는지 읽어오기
		String id = (String) session.getAttribute("loginId");

		// 조건문 비교(그 값이 abc 같은지)
		if (id == null || !id.equals("abc")) {
			// 없거나 일치하지 않으면 loginForm.html로 이동
			return "redirect:/ss/login";
		} else {

			// 같으면 loginTest.Form로 이동
			return "ssView/loginTest";
		}

	}
}
