package net.datasa.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"","/"})
	public String home(){
		return "home"; // forwarding 방식 이동임 (url에 경로값이 남음)
	}
}
