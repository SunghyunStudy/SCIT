package net.practice4.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.practice4.service.GuestBookService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GuestBookController {
    private final GuestBookService gs;

    @GetMapping("test")
    public String test() {
        gs.test();
        return 'redirect:/';
    }

}
