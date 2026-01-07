package net.datasa.spring4_v2.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring4_v2.domain.dto.GuestbookDTO;
import net.datasa.spring4_v2.service.GuestbookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class GuestbookController {

    private final GuestbookService guestbookService;

    //저장 테스트
    @GetMapping("test")
    public String test() {
        guestbookService.test();
        return "redirect:/";
    }

    /********************************************************************************************************
     * 글쓰기 폼으로 이동
     * @return writeForm.html
     */
    @GetMapping("write")
    public String write() {
        return "writeForm";
    }
	
    /********************************************************************************************************
     * 글쓰기 폼에서 전달된 입력값 DB에 저장
     * @param dto 폼에 입력한 내용
     * @return 글 목록 보기 경로
     */
    @PostMapping("write")
    public String write(@ModelAttribute GuestbookDTO dto) {
        guestbookService.write(dto);
        return "redirect:list";
    }
	
	/********************************************************************************************************
	 * 글 목록 보기
	 * @param model	(출력 내용을 HTML로 전달할 객체)
	 * @return list.html
	 */
    @GetMapping("list")
    public String list(Model model) {
        List<GuestbookDTO> dtoList = guestbookService.getList();
        model.addAttribute("guestbookList", dtoList);
        return "list";
    }
	
	/********************************************************************************************************
	 * 삭제 처리
	 * @param num
	 * @param password
	 * @param ra
	 * @return
	 */
    @PostMapping("delete")
    public String delete(@RequestParam("num") Integer num,
                         @RequestParam("password") String password,
                         RedirectAttributes ra) {
        try {
            guestbookService.delete(num, password);
            log.debug("글 삭제 성공!");
        }
        catch (Exception e) {
            log.debug("글 삭제 실패.. : {}", e.getMessage());
            ra.addFlashAttribute("msg", e.getMessage());
        }
        return "redirect:list";
    }
	
	/********************************************************************************************************
	 * 수정 폼으로 이동
	 * @param num
	 * @param password
	 * @param model
	 * @param ra
	 * @return
	 */
	@GetMapping("update")
	public String update(@RequestParam("num") Integer num,
						 @RequestParam("password") String password,
						 Model model,
						 RedirectAttributes ra) {
		try {
			guestbookService.passwordCheck(num, password);
			GuestbookDTO dto = guestbookService.selectGuestbook(num);
			model.addAttribute("guestbook", dto);
			log.debug("글 수정 폼으로 이동");
			return  "updateForm";
		}
		catch (Exception e) {
			log.debug("글 수정 실패.. : {}", e.getMessage());
			ra.addFlashAttribute("msg", e.getMessage());
			return "redirect:list";
		}
	}
	
	/********************************************************************************************************
	 * 수정 처리
	 * @param dto
	 * @return
	 */
	@PostMapping("update")
	public String update(GuestbookDTO dto) {
		log.debug("수정할 정보: {}", dto);
		try {
			guestbookService.update(dto);
			log.debug("글 수정 성공!");
		}
		catch (Exception e) {
			log.debug("글 수정 실패.. : {}", e.getMessage());
		}
		return "redirect:list";
	}
	
	
	/********************************************************************************************************
	 * 추천 처리
	 * @return
	 */
	@PostMapping("/recommend")
	public String recommend(
			@RequestParam("num") Integer num,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes
	) {
		// IP
		String clientIp = request.getRemoteAddr();
		try {
			guestbookService.recommend(num, clientIp);
			redirectAttributes.addFlashAttribute("msg", "추천되었습니다.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
		}
		// 목록 페이지로 리다이렉트
		return "redirect:/list";
	}
}

