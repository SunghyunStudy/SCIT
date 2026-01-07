package net.datasa.spring2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("local")
public class LocalController {

	// localStorage, sessionStorage 에 값 저장하는 페이지
	@GetMapping("save")
	public String save() {
		return "localView/save";
	}

	// 저장한 값 출력
	@GetMapping("output")
	public String output() {
		return "localView/output";
	}

	// 지정한 값들 삭제 페이지
	@GetMapping("delete")
	public String delete() {
		return "localView/delete";
	}
}
