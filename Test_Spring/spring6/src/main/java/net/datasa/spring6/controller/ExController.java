package net.datasa.spring6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring6.service.ExService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ExController {
    private final ExService es;

    // @GetMapping("like")
    // public String like(Model model) {
    // int cnt = es.getCount(1);
    // model.addAttribute("cnt", cnt);
    // return "posts/like";
    // }

    // @ResponseBody
    // @PostMapping("pressLike")
    // public Integer pressLike() {
    // int num = 1;
    // int cnt = es.getCount(num);
    // cnt++;
    // es.setCount(num, cnt);
    // return cnt;
    // }
    @GetMapping("posts/like")
    public String like() {
        return "like";
    }

    @ResponseBody
    @GetMapping("posts/{num}/like")
    public int readLise(@PathVariable("num") Integer num) {
        int cnt = es.readLike(num);
        return cnt;
    }

    @ResponseBody
    @PostMapping("posts/{num}/like")
    public int likePlus(@PathVariable("num") Integer num) {
        int cnt = es.likePlus(num);
        return cnt;
    }

    @GetMapping("member/join")
    public String join() {
        return "joinForm";
    }

    @ResponseBody
    @GetMapping("member/idCheck")
    public String idCheck(@RequestParam("id") String id) {

        boolean s = es.idCheck(id);

        if (!s) {
            return "";
        } else
            return "존재하는 아이디입니다.";
    }

}
