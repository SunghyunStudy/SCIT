package net.datasa.spring5.controller;

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

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

	private final MemberService ms;

	@GetMapping("test")
	public String test() {
		ms.test();
		return "redirect:/";
	}

	@GetMapping("join")
	public String join() {
		return "memberView/joinForm";
	}

	@PostMapping("join")
	public String join(MemberDTO memberDTO) {
		log.debug("dto 확인 {}", memberDTO);
		ms.join(memberDTO);
		return "redirect:/";
	}

	/**
	 * 아이디 중복 확인 폼으로 이동
	 * 
	 * @return
	 */
	@GetMapping("idCheck")
	public String idCheck() {
		return "/memberView/idCheck";
	}

	/**
	 * 아이디 중복확인
	 * 
	 * @param searchId
	 * @param model
	 * @return
	 */
	@PostMapping("idCheck")
	public String idCheck(@RequestParam(name = "searchId") String searchId,
			Model model) {
		log.debug("검색할 ID: {}", searchId);
		boolean result = ms.idCheck(searchId);

		model.addAttribute("result", result);
		model.addAttribute("searchId", searchId);
		return "/memberView/idCheck";
	}

	/**
	 * 로그인 폼으로 이동
	 * 
	 * @return loginForm.html
	 */
	@GetMapping("loginForm")
	public String loginForm() {
		return "memberView/loginForm";
	}

	/**
	 * 휴면계정 해제 페이지로 이동
	 * 
	 * @return inactive.html
	 */
	@GetMapping("inactive")
	public String inactive() {
		return "memberView/inactive";
	}

	/**
	 * 휴면계정 해제 처리
	 * 
	 * @return inactive.html
	 */
	@PostMapping("inactive")
	public String inactive(@RequestParam("memberId") String memberId,
			@RequestParam("memberPassword") String memberPassword,
			Model model,
			HttpServletRequest request) {

		try {
			ms.inactive(memberId, memberPassword);

			// 1. 수동 로그인
			// model.addAttribute("memberId", memberId);
			// model.addAttribute("msg", "휴면 계정이 해제되었습니다. 다시 로그인 해주세요");
			// return "memberView/loginForm";

			// 2. 자동로그인
			ms.autoLogin(memberId, memberPassword, request);
			return "redirect:/";

		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "memberView/inactive";
		}
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("info")
	public String info(@AuthenticationPrincipal UserDetails user,
			Model model) {
		String memberId = user.getUsername();

		log.debug("개인정보 수정 시 아이디 넘어가는 지 확인 {} ", memberId);

		try {
			MemberDTO dto = ms.getMember(memberId);
			model.addAttribute("member", dto);
			return "memberView/infoForm";
		} catch (Exception e) {
			log.debug("[조회 실패] {}", e.getMessage());
			return "redirect:/";
		}
	}

	/**
	 * 개인정보 수정 처리
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("info")
	@PreAuthorize("isAuthenticated()")
	public String info(MemberDTO dto) {
		// hidden 테그 만들어서 id 값 가져왔는데 @AuthenticationPrincipal UserDetails user(현재 로그인 중인
		// 계정 정보 불러오기) 써서 dto에 set 해주고 넘겨도 됨.
		log.debug("개인정보 수정 후 dto 출력 {}", dto);

		try {
			ms.updateMember(dto);
			log.debug("정보 수정 완");
		} catch (Exception e) {
			log.debug(e.getMessage());
			return "memberView/infoForm";
		}
		return "redirect:/";
	}
}
