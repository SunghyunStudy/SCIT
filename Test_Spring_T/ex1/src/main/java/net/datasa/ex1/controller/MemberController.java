package net.datasa.ex1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex1.domain.Member;
import net.datasa.ex1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public String join(Member member) {
		log.debug("> 회원가입 데이터: {}", member);
		boolean result = ms.save(member);
		
		if(result) {
			log.debug("> 회원가입 성공!");
			return "redirect:/";
		}else{
			log.debug("> 이미 존재하는 ID입니다.");
			return "member/join";
		}
		
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
	public String login(@CookieValue(name = "recentId", defaultValue = "") String recentId, Model model)
	{
		log.debug("> 최근 ID: {}", recentId);
		model.addAttribute("recentId", recentId);
		return "member/loginForm";
	}
	
	@PostMapping("login")
	public String login(
			HttpSession session,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam(name = "check", defaultValue = "false") boolean check,
			HttpServletResponse response
	){
		log.debug("> 로그인 데이터: id={}, pw={}, check={}", id,pw,check);
		
		boolean result = ms.loginCheck(id, pw);
		
		if(result){
			session.setAttribute("loginId", id);
			log.debug("> 로그인 성공! 현재 세션 ID: {}", id);
			
			Cookie c = new Cookie("recentId", id);
			c.setPath("/member/login");
			c.setMaxAge(0);
			if(check){
				c.setMaxAge(60*60*24*3);
				log.debug("> 쿠키 저장");
			}
			response.addCookie(c);
			
			return "redirect:/";
		}else {
			log.debug("> 로그인 실패");
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session){
		session.removeAttribute("loginId");
		session.invalidate();
		log.debug("> 로그 아웃!");
		return "redirect:/";
	}
	
	
	/**
	 * 3. 회원목록 처리
	 *  - 회원목록 가져오기
	 *  - 회원목록 페이지로 이동(HTML 출력을 위해 Model 저장)
	 */
	
	@GetMapping("list")
	public String list(Model model){
		
		List<Member> list = ms.selectAll();
		model.addAttribute("memberList", list);
		log.debug("> 회원 등록: {}", list);
		
		return "member/list";
	}
	
}
