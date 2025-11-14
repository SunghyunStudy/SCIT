package net.datasa.spring2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.spring2.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j // log를 쓰기 위한 어노테이션
@RequestMapping("param")  // 클래스 전체에 공통 URL을 추가
public class ParamController {
	@GetMapping("view1")
	public String view1(){
		return "paramView/view1";
	}
	
	/*
	 *  @RequestParam ?
	 *  HTTP 요청 파라미터를 자바 변수로 받아오는 어노테이션
	 * ex.
	 * 	http://localhost:9992/param?id=abc&name=aaa
	 * 	key				value
	 * 	id				abc
	 * 	name			aaa
	 * 이 두 값을 컨트롤러 메서드의 매개변수로 자동 매핑
	 *
	 * 속성			기능
	 * name			요청 파라미터와 메서드의 파라미터를 매핑
	 * defaultValue	요청 파라미터로부터 value값이 없으면 default 설정
	 * required		요청 파라미터로부터 해당 key값이 없으면 에러발생
	 *
	 * */
	
	// view1.html 에서 입력한 값들 받기
	@GetMapping("input1")
	public String input1(@RequestParam(name = "id") String id,
						 @RequestParam("password") String password,
						 @RequestParam("name") String name,
						 @RequestParam("phone") String phone,
						 @RequestParam("com") String com){

		log.debug("전달된 값 : ID : {}, PW : {}, 이름 : {}, 전화 : {}, 통신 : {}"
		, id, password, name, phone, com);
		return "redirect:/";
	}
	
	@GetMapping("view2")
	public String view2(){
		return "paramView/view2";
	}
	
	/*
		Get :
			데이터를 URL 쿼리스트링으로 전송
			주소 표시줄에 사용자가 입력한 내용이 그대로 노출되기 때문에
			주로 데이터를 조회할 때 사용.
			최대 4096byte 까지의 데이터 전송 가능
			주로 조회, 검색, 읽기, 페이지 이동 등..
			
		Post :
			HTTP 메시지의 본문(body)에 데이터를 포함시켜 전송
			입력 내용의 길이 제한이 없으며, 입력값이 주소창에 노출되지 않음.
			주로 서버의 상태를 변경하는 요청(쓰기, 등록, 수정, 삭제)에 사용
			로그인, 폼 데이터 제출, 파일 업로드, DB갱신 등...
			
			
		요청 메시지:
			[요청라인] - url(get방식)이 포함되어 있음 ; 브라우저에 노출될 수 있음.
						http://localhost:9992/param?id=abc&name=aaa
			[헤더] -
			[바디] - post 방식 (url에 노출되지 않음)dd
	 */
	
	@PostMapping ("input2")
	public String input2(@RequestParam(name = "id") String id,
						 @RequestParam("password") String password,
						 @RequestParam("name") String name,
						 @RequestParam("phone") String phone,
						 @RequestParam("com") String com){
		
		log.debug("전달된 값 : ID : {}, PW : {}, 이름 : {}, 전화 : {}, 통신 : {}"
				, id, password, name, phone, com);
		return "redirect:/";
	}
	
	// view3으로 이동
	@GetMapping("view3")
	public String view3(){
		return "paramView/view3";
	}
	
	// ModelAttribute 서버측 데이터를 html로 가져오기도 하고 데이터를 자동으로 객체로 매핑해줌
	@PostMapping("input3")
	public String input3(@ModelAttribute Person p){ // html상의 name과 dto의 변수 명이 완전히 일치하면 알아서 가져오나?
		log.debug("전달된 객체: {}", p);  // toString이 있기 때문에 알아서 출력이 될거임.
		return "redirect:/";
	}
	
	@GetMapping("input4")
	public String input4(
			@RequestParam(name = "name", defaultValue = "기본값") String name,
			@RequestParam(name="age", defaultValue = "0") int age
	){
		log.debug("name:{}, age:{}", name, age);
		return "redirect:/";
	}
	
	/*
		Model : 컨트롤러에서 뷰(JSP, Thymeleaf 등)로 데이터를 넘겨주는 데 사용되는 데이터 전달 객체
		controller에서 html로 넘어가는 순간만 유효한 1회성 객체임.
		(Spring은 Model 객첼ㄹ 요청단위로 유지함)
		redirect:/ 등의 재요청에는 저장된 데이터가 없어짐.
	 */
	@GetMapping("model")
	public String model(Model model){
		
		String str = "문자열";
		int num = 100;
		Person person = new Person("abc", "123", "홍길동", "010-000", "kt");
		
		// key - value 형태로 매개변수를 입력.
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("person", person);
		
		return "paramView/model";
	}
}
