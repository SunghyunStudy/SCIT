package net.datasa.spring5.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring5.domain.dto.MemberDTO;
import net.datasa.spring5.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자 Controller
 */
@Slf4j
@RequestMapping("admin")
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // 이 클래스는 ADMIN일 경우에만 사용이 되는거임
public class AdminController {

    private final MemberService ms;

    @GetMapping("page")
    public String admin() {
        return "adminView/adminPage";
    }

    /**
     * 회원목록을 볼 수 있는 페이지로 이동
     * 
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {

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

    @GetMapping("update")
    public String update(@RequestParam("id") String memberId) {

        try {
            ms.update(memberId);
            log.debug("권한 변경 성공!");
        } catch (Exception e) {
            log.debug("[예외 발생] {}", e.getMessage());
            // TODO: handle exception
        }

        return "redirect:/admin/list";
    }

    /**
     * 계정 활성화/비활성화 토글 처리
     * 요청 URL: /member/status?id=abc
     */
    @GetMapping("updateEnabled")
    public String statusToggle(@RequestParam("id") String memberId,
            @RequestParam("enabled") boolean enabled) {

        log.debug("updateEnabled - param id : {}, enabled : {}", memberId, enabled);
        try {
            ms.updateEnabled(memberId, !enabled);
            log.debug("{} 계정의 활성 상태를 {}로 변경", memberId, !enabled);
        } catch (Exception e) {
            log.debug("[예외 발생] {}", e.getMessage());
        }

        return "redirect:/admin/list";
    }

}
