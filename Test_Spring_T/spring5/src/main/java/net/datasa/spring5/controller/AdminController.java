package net.datasa.spring5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring5.domain.dto.MemberDTO;
import net.datasa.spring5.service.MemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 관리자 Controller
 */
@Slf4j
@RequestMapping("admin")
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	
	private final MemberService ms;
	
	@GetMapping("page")
	public String admin() { return "adminView/adminPage"; }
	
	/**
	 * 회원목록을 볼 수 있는 페이지로 이동
	 * @param model
	 * @param keyword
	 * @return
	 */
	@GetMapping("list")
	public String list(
			@RequestParam(name = "keyword", required = false) String keyword,
			Model model) {
		
		List<MemberDTO> memberList = null;
		
		if (keyword == null || keyword.trim().isEmpty()) {
			memberList = ms.selectAll();
		} else {
			memberList = ms.searchById(keyword);
		}
		model.addAttribute("memberList", memberList);
		model.addAttribute("keyword", keyword);
		
		return "adminView/memberList";
	}
	
	/**
	 * 권한 변경
	 * @param memberId
	 * @return 회원목록
	 */
	@GetMapping("update")
	public String update(
			@RequestParam("id") String memberId
	) {
		try {
			ms.update(memberId);
			log.debug("권한 변경 성공!");
		} catch (Exception e) {
			log.debug("[예외 발생] {}", e.getMessage());
		}
		
		return "redirect:/admin/list";
	}
	
	/**
	 * 계정상태 변경
	 * @param memberId
	 * @param enabled
	 * @return
	 */
	@GetMapping("updateEnabled")
	public String toggleEnabled(
			@RequestParam("id") String memberId
			, @RequestParam("enabled") boolean enabled
	) {
		log.debug("updateEnabled - param id: {}, enabled: {}"
				, memberId, enabled);
		
		try {
			ms.updateEnabled(memberId, !enabled);
			log.debug("{} 계정의 활성 상태를 {}로 변경"
					, memberId, !enabled);
		} catch (Exception e) {
			log.debug("[예외 발생] {}", e.getMessage());
		}
		return "redirect:/admin/list";
	}
	
}
