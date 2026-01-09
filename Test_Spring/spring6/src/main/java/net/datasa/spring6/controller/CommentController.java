package net.datasa.spring6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentController {

    @GetMapping("board/comment")
    public String comment() {
        return "comment";
    }
}
