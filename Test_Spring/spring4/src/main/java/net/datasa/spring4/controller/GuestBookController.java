package net.datasa.spring4.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring4.domain.dto.GuestBookDTO;
import net.datasa.spring4.service.GuestBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GuestBookController {

	private final GuestBookService gs;
	// RequiredArgsConstructor가 없다면 Autowired를 쓰면 됨.

	@GetMapping("test")
	public String test() {
		gs.test();

		return "redirect:/";
	}

	/**
	 * 글쓰기 폼으로 이동
	 * 
	 * @return writeForm.html
	 */
	@GetMapping("write")
	public String write() {
		return "writeForm";
	}

	@PostMapping("write")
	public String write(GuestBookDTO dto) {
		log.debug("데이터 확인 : {}", dto);
		gs.write(dto);

		return "redirect:list";
	}

	/**
	 * 글 목록 보기
	 * 
	 * @param model
	 * @return list.html
	 */
	@GetMapping("list")
	public String list(Model model) {

		List<GuestBookDTO> dtoList = gs.getList();
		model.addAttribute("guestbookList", dtoList);
		log.debug(">> 글 목록 : {}", dtoList);

		return "list";
	}

	/**
	 * 삭제처리
	 * 
	 * @param num
	 * @param password
	 * @param ra       RedirectAttributes : redirect 후에도 임시로 저장될 데이터 객체
	 * @return
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("num") Integer num,
			@RequestParam("password") String password,
			RedirectAttributes ra) {

		try {
			gs.delete(num, password);
			log.debug("[삭제 성공]");
		} catch (Exception e) {
			log.debug("[삭제 실패] {}", e.getMessage());
			ra.addFlashAttribute("msg", e.getMessage());
		}

		return "redirect:/list";
	}

	@GetMapping("update")
	public String update(@RequestParam("num") Integer num,
			@RequestParam("password") String password,
			RedirectAttributes ra,
			Model model) {

		try {
			GuestBookDTO dto = gs.selectOne(num, password);
			log.debug("[수정 성공]");
			model.addAttribute("gb", dto);
			return "update";
		} catch (Exception e) {
			log.debug("[수정 실패] {}", e.getMessage());
			ra.addFlashAttribute("msg", e.getMessage());
			return "redirect:list";
		}

	}

	@PostMapping("update")
	public String update(GuestBookDTO dto) {
		log.debug("수정폼 dto 출력:{}", dto);
		gs.update(dto);
		return "redirect:list";
	}

	// 추천
	@PostMapping("recommend")
	public String recommend(
			@RequestParam("num") Integer num,
			HttpServletRequest request,
			RedirectAttributes ra) {
		// 현재 요청을 보낸 클라이언트의 IP주소를 문자열로 반환하는 메서드
		String clientIp = request.getRemoteAddr();

		try {
			gs.recommend(num, clientIp);
			ra.addFlashAttribute("msg", "추천되었습니다.");
		} catch (Exception e) {
			ra.addFlashAttribute("msg", e.getMessage());
		}
		return "redirect:/list";
	}

}
