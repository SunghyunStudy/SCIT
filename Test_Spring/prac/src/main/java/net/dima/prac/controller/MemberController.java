package net.dima.prac.controller;

import lombok.RequiredArgsConstructor;
import net.dima.prac.dto.MemberSignupDto;
import net.dima.prac.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(MemberSignupDto dto) {
        memberService.join(dto);
        return "redirect:/login";
    }
}
