package net.datasa.ex1.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.ex1.domain.Member;
import net.datasa.ex1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
	
	// 서비스 클래스 객체주입.
	@Autowired
	MemberService ms;
	
	/**
	 * 1. 회원가입 처리
	 *  - 회원가입 페이지 이동.
	 *  - 회원가입 페이지로부터 입력받은 데이터 저장
	 */
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(@RequestParam("id") String id,
					   @RequestParam("pw") String pw,
					   @RequestParam("name") String name,
					   @RequestParam("phone") String phone){
		Member member = new Member(id, pw, name, phone);
		ms.save(member);
		return "redirect:/";
	}
	/**
	 * 2. 로그인, 로그아웃 처리
	 *  - 로그인 페이지로 이동.
	 *  - 로그인 페이지로부터 입력받은 데이터를 저장된 회원목록으로부터 비교
	 *    일치할 경우,
	 *    	  세션에 로그인아이디 저장 후 메인페이지로 이동하고,
	 *    	  로그인 페이지로부터 "ID기억하기" 를 체크했다면 쿠키에 로그인아이디 저장 후 응답처리
	 *    일치하지 않을 경우,
	 *    	  로그인 페이지로 이동
	 *  - 로그아웃 처리(세션에 ID 삭제 후 메인페이지 이동)
	 */
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(@RequestParam("id") String id,
						@RequestParam("pw") String pw) {
		
		boolean loginResult = ms.loginCheck(id,pw);
		
		if(!loginResult){
			return "login";
		}
		return "redirect:/";
	}
	
	/**
	 * 3. 회원목록 처리
	 *  - 회원목록 가져오기
	 *  - 회원목록 페이지로 이동(HTML 출력을 위해 Model 저장)
	 */
	
	
}
