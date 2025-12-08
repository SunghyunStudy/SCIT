package net.datasa.ex1_db.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex1_db.domain.dto.UserDTO;
import net.datasa.ex1_db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
public class UserController {
	
	@Autowired
	UserService us;
	
	// 회원가입 페이지 이동
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	// 회원가입 데이터 전송
	@PostMapping("join")
	public String join(UserDTO dto){
		log.debug("전달된 데이터: {}", dto);
		us.insert(dto);
		
		return "redirect:/";
	}
	
	
	@GetMapping("login")
	public String login(){
		return "member/loginForm";
	}
	
	@PostMapping("login")
	public String login(@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						HttpSession session){
		
		UserDTO dto = us.idCheck(id, pw);
		if(dto == null){
			log.debug("로그인 실패");
			return "redirect:/member/login";
		}
		
		session.setAttribute("user", dto);
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		session.invalidate();
		log.debug("로그아웃 성공");
		return "redirect:/";
	}
	
	@GetMapping("list")
	public String list(Model model){
		log.debug("전체 목록 조회");
		
		List<UserDTO> userList = us.selectAll();
		model.addAttribute("userList", userList);
		
		return "member/list";
	}
	
}
