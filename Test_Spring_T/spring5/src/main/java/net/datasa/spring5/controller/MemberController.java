package net.datasa.spring5.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring5.domain.dto.MemberDTO;
import net.datasa.spring5.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService ms;
	
	@GetMapping("/test")
	public String test() {
		ms.test();
		return "redirect:/";
	}
	
	/**
	 * 가입 폼으로 이동
	 * @return joinForm.html
	 */
	@GetMapping("join")
	public String join() {
		return "memberView/joinForm";
	}
	
	/**
	 * 가입 처리
	 * @param member 입력한 회원정보
	 * @return 메인페이지로
	 */
	@PostMapping("join")
	public String join(MemberDTO member) {
		log.debug("전달된 가입정보: {}", member);
		
		ms.join(member);
		
		return "redirect:/";
	}
	
	/**
	 * 아이디 중복 확인 폼으로 이동
	 * @return
	 */
	@GetMapping("idCheck")
	public String idCheck() {
		return "/memberView/idCheck";
	}
	
	/**
	 * 아이디 중복확인
	 * @param searchId
	 * @param model
	 * @return idCheck.html
	 */
	@PostMapping("idCheck")
	public String idCheck(
			@RequestParam(name = "searchId") String searchId
			, Model model
			) {
		log.debug("검색할ID: {}", searchId);
		boolean result = ms.idCheck(searchId);
		
		model.addAttribute("result", result);
		model.addAttribute("searchId", searchId);
		return "/memberView/idCheck";
	}
	
	/**
	 * 로그인 폼으로 이동
	 * @return loginForm.html
	 */
	@GetMapping("loginForm")
	public String loginForm() {
		return "memberView/loginForm";
	}
	
	
	/**
	 * 휴면계정 해제 페이지로 이동
	 * @return inactive.html
	 */
	@GetMapping("/inactive")
	public String inactive() {
		return "memberView/inactive";
	}
	
	/**
	 * 휴면계정 해제 처리
	 * @return
	 */
	@PostMapping("/inactive")
	public String inactive(
			@RequestParam("memberId") String memberId,
			@RequestParam("memberPassword") String memberPassword,
			Model model,
			HttpServletRequest request
	) {
		try {
			ms.inactive(memberId, memberPassword);
			
			// 1. 수동 로그인
//			model.addAttribute("memberId", memberId);
//			model.addAttribute("msg"
//				, "휴면 계정이 해제되었습니다. 다시 로그인해 주세요.");
//			return "memberView/loginForm";
			
			// 2. 자동 로그인
			ms.autoLogin(memberId, memberPassword, request);
			return "redirect:/";
			
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "memberView/inactive";
		}
	}
	
	/**
	 * 개인정보 수정페이지로 이동
	 * @param user 로그인한 사용자 정보
	 * @param model
	 * @return infoForm.html
	 */
	@PreAuthorize("isAuthenticated()")	//로그인한 유저만 접근
	@GetMapping("info")
	public String info(
			@AuthenticationPrincipal UserDetails user,
			Model model
			) {
		log.debug("Userdetails 정보: {}", user.getUsername());
		
		try {
			MemberDTO member = ms.getMember(user.getUsername());
			model.addAttribute("member", member);
			log.debug("[조회 성공] {}", member);
			return "/memberView/infoForm";
		} catch (Exception e) {
			log.debug("[조회 실패] {}", e.getMessage());
			return "redirect:/";
		}
	}
	
	/**
	 * 개인정보 수정 처리
	 * @param user
	 * @param memberDTO
	 */
	@PostMapping("info")
	@PreAuthorize("isAuthenticated()")
	public String info(
		@AuthenticationPrincipal UserDetails user,
		MemberDTO memberDTO
	) {
		log.debug("수정할 정보: {}", memberDTO);
		memberDTO.setMemberId(user.getUsername());
		
		try {
			ms.updateMember(memberDTO);
			log.debug("[수정 성공]");
		} catch (Exception e) {
			log.debug("[수정 실패] {}", e.getMessage());
		}
		return "redirect:/";
	}
	
}
