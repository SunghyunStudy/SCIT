package net.datasa.spring2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.datasa.spring2.domain.Calc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping("ex")
public class ExController {
	/*
		[연습문제1]
		1. home.html에서 ex/calc1 경로 요청을 처리할 메소드 생성
		2. calcForm.html 입력 폼을 출력(입력란 2개, select 1개, submit 버튼 1개)
		3. 숫자 2개를 입력하고 연산자(사칙연산자)를 선택한 후 submit 클릭
		4. 숫자가 아닌 값을 입력하면 JS에서 유효성검사 후 오류메시지 출력
		5. 숫자 2개, 연산자를 정상적으로 입력 및 선택 했다면 서버로 전송
		6. 컨트롤러에서 값을 전달받아 계산
		7. 계산한 결과를 Model에 저장하고 calcOutput.html로 이동
		8. 화면에 계산한 결과 출력
	 */
	
	@GetMapping("calc1")
	public String calc1(){
		return "exView/calcForm";
	}
	
	/**
	 * 연산 처리 및 결과화면 이동
	 * @param cal
	 * @param model
	 * @return 연산결과를 출력하는 html
	 */
	@PostMapping("calc1")
	public String result(@RequestParam("num1") String num1,
						 @RequestParam("oper") String oper,
						 @RequestParam("num2") String num2,
						 @ModelAttribute Calc cal, // ModelAttribute 이거 없어도 자동으로 매핑해줌
						 Model model){

		log.debug("전달된 값 : num1 : {}, oper : {}, num2 : {}"
				, num1, oper, num2);
		log.debug("param:{}", cal);
		
		// 주석처리는 내 코드
//		double result=0;
//		double n1, n2;
//		n1 = Double.parseDouble(num1);
//		n2 = Double.parseDouble(num2);
//
//		switch (oper){
//			case "+" : result = n1 + n2;break;
//			case "-" : result = n1 - n2;break;
//			case "*" : result = n1 * n2;break;
//			case "/" : result = n1 / n2;break;
//			default: break;
//		}
//
//		model.addAttribute("n1", num1);
//		model.addAttribute("n2", num2);
//		model.addAttribute("oper", oper);
//		model.addAttribute("result", result);
		
		
		int res = 0, n1, n2;
		try{
			switch (cal.getOper()){
				case "+" : res = cal.getNum1() + cal.getNum2();break;
				case "-" : res = cal.getNum1() - cal.getNum2();break;
				case "*" : res = cal.getNum1() * cal.getNum2();break;
				case "/" : res = cal.getNum1() / cal.getNum2();break;
				default: throw new Exception("연산자 선택 오류");
			}
			model.addAttribute("calc", cal);
			model.addAttribute("result", res);
		}catch (Exception e){
			log.debug("[예외 발생] 원인: {}", e.getMessage());
			return "/exView/calcForm";
		}
		
		return "exView/calcOutput.html";
	}
	
	
	@GetMapping("info")
	public String info(){
		
		return "exView/info";
	}
	
	// 이건 내가한 코드
//	@PostMapping("info")
//	public String infoOutput(@RequestParam("name")String name,
//							 @RequestParam("ssn")String ssn,
//							 Model model){
//		int year, month, day, age=0;
//		String gender = "";
//		String yearOutput, monthOutput, dayOutput;
//		
//		log.debug("ssn:{}", ssn.charAt(0));
//		
//		year = Integer.parseInt(ssn.substring(0,2));
//		yearOutput = ssn.substring(0,2);
//		monthOutput = ssn.substring(2,4);
//		dayOutput = ssn.substring(4,6);
//		
//		if(ssn.charAt(7) % 2 == 0){
//			gender = "여자";
//		}else if(ssn.charAt(7) % 2 == 1){
//			gender = "남자";
//		}
//		
//		if(ssn.charAt(7) == '1' || ssn.charAt(7) == '2'){
//			age = 2025 - (1900 + year);
//		}
//		else if(ssn.charAt(7) == '3' || ssn.charAt(7) == '4'){
//			age = 2025 - (2000 + year);
//		}
//		
//		model.addAttribute("name", name);
//		model.addAttribute("year", yearOutput);
//		model.addAttribute("month", monthOutput);
//		model.addAttribute("day", dayOutput);
//		model.addAttribute("age", age);
//		model.addAttribute("gender", gender);
//		
//		return "exView/infoOutput";
//	}
	
	/**
	 * 이건 쌤이 한거
	 * 입력한 이름과 주민등록번호를 전달받아 infoOutput.html에 출력
	 * @param name
	 * @param ssn
	 * @param model
	 * @return
	 */
	
	@PostMapping("info")
	public String info(@RequestParam("name")String name,
					   @RequestParam("ssn")String ssn,
					   Model model){
		
		
		log.debug("전달된 값: {}, {}", name, ssn);
		model.addAttribute("name", name);
		model.addAttribute("ssn", ssn);
		
		// 기본 검증
		if(ssn == null || ssn.length() != 14 || ssn.charAt(6) != '-'){
			model.addAttribute("error", "error");
			return "exView/info";
		}
		
		try{
			// 숫자 분리
			String ssnYear = ssn.substring(0,2);
			int month = Integer.parseInt(ssn.substring(2,4));
			int day = Integer.parseInt(ssn.substring(4,6));
			char genderCode = ssn.charAt(7);
			
			// 성별 판별
			String gender = (genderCode == '1' || genderCode == '3') ? "남자" : "여자";
			
			// 출생 연도
			int yearPrepix = (genderCode == '1' || genderCode == '2') ? 1900 : 2000;
			
			int year = yearPrepix + Integer.parseInt(ssnYear);
			
			// 나이 계산
			int thisYear = LocalDate.now().getYear();
			int age = thisYear - year;
			
			// 포맷
			String birth = String.format("""
					%d년 %d월 %d일
					""", year, month, day);
			
			model.addAttribute("y", year);
			model.addAttribute("m", month);
			model.addAttribute("d", day);
			model.addAttribute("age", age);
			model.addAttribute("gender", gender);
			model.addAttribute("birth", birth);
			
			
			
			return "exView/infoOutput";
		}catch (Exception e){
			model.addAttribute("error", "error");
			return "exView/info";
		}
	}
	
	
	/**
	 * [연습문제3]
	 * 방문횟수 카운트 예제
	 * 	1. 방문횟수가 저장된 쿠키를 읽어온다.
	 * 	2. 없으면 방문횟수는 현재 0으로 처리
	 * 	3. 있으면 쿠키에 저장된 숫자가 기존 방문횟수
	 * 	4. 방문횟수에 1을 더한다.
	 * 	5. 쿠키에 증가된 방문횟수를 저장하여 클라이언트로 전송
	 * 	6. 방문횟수를 model에 저장하여 html 페이지에서 출력
	 * @return
	 */
	@GetMapping("/count")
	public String count(@CookieValue(name = "count", defaultValue = "0") int count,
						HttpServletResponse response,
						Model model){
		
		// 쿠키 생성을 하지도 않았는데도 받아올 수 있나봄
		
		count++;
		model.addAttribute("count", count);
		
		Cookie cookie = new Cookie("count", Integer.toString(count));
		cookie.setMaxAge(60*60*24*3);
		cookie.setPath("/ex/count");  // 얘랑 다른 루트에서의 쿠키는 전송되지 않음.
		response.addCookie(cookie);
		
		return "exView/count";
	}
	
	/**
	 * [연습문제 4]
	 * Web Storage를 활용한 다크모드
	 * @return
	 */
	@GetMapping("darkmode")
	public String darkmode(){
		
		return "exView/darkmode";
	}
	
	
	/**
	 * [연습문제 5]
	 * 임시 저장
	 */
	@GetMapping("temp")
	public String temp(){
		return "exView/temp";
	}
	
}
