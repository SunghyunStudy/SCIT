package net.datasa.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
	@GetMapping("image")    // href image를 클릭하는 순간 해당 경로를 받아오나봄 //
	public String image(){
		
		System.out.println("이미지 경로 처리");
		return "image";   // html 파일 이름
	}
	 
	// 애는 image.html에서 상대경로로 경로를 지정한 링크에 접속하려고하면 안됨
	// sub라는 경로를 기준으로 바뀌었기 때문임
	// image.html과 경로가 아예 다른거임.
	@GetMapping("sub/image")   // home.html의 sub/image와 일치하면 templates/image를 불러와서 출력함.
	public String image2() {
		return "image";  // templates에서 찾음.
	}
	
	@GetMapping("css")  // html 어딘가에서  href가 css라는 이름의 a태그를 누르면 여기서 반응하는거임
	public String css(){
		return "css";  // 이거 html임 헷갈리지 말자.
	}
	
	// home.html에서 a태그의 href"js"   <-  이 요청을 매핑
	@GetMapping("js")
	public String js(){
		// templates 폴더로부터 js 라고 하는 html을 찾겠다.
		return "js";
	}
	
	@GetMapping("path")
	public String path(){
		return "path";
	}
	
	@GetMapping("sub/path")
	public String sub(){
		return "sub/path2";  // 링크 이름은 매핑한 경로가 됨. localhost:9991/sub.path2 가 아님.
	}
	
	
}
