package net.datasa.spring4_v2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 화면 콘트롤러
 */
@Slf4j
@Controller
public class HomeController {

    @GetMapping({"", "/"})
    public String home() {
        log.debug("메인화면 진입");
        return "home";
    }
}
