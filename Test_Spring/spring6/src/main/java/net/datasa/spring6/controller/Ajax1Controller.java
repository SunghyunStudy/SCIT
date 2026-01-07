package net.datasa.spring6.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController // 컨트롤러 자체가 응답을 위한 컨트롤러면 쓰고 아니면 하나하나씩 쓰고 싶으면 아래의 @ResponseBody를 사용.
@Controller
@Slf4j
/*
 * *
 * Ajax 테스트 컨트롤러
 *
 */
public class Ajax1Controller {

	/*
	 * 테스트 페이지1로 이동
	 */
	@GetMapping("ajax1")
	public String ajax1() {
		return "ajax1";
	}

	/*
	 * Ajax 요청에 대한 응답 1
	 */
	@ResponseBody // 이게 없으면 오류 남 --->
					// 리턴 값을 화면(HTML)로 보내지 말고, HTTP 응답 데이터로 그대로 보내달라.
	@GetMapping("ajaxTest1")
	public void ajaxTest1() { // void임. 여기는
		log.debug("AjaxController의 ajaxTest1() 실행");
	}

	/**
	 * Ajax 요청에 대한 응답2
	 */
	@ResponseBody
	@PostMapping("ajaxTest2")
	public void ajaxTest2(@RequestParam("str") String str) {
		log.debug("AjaxController의 ajaxTest2()에서 출력: {}", str);
		throw new RuntimeException("서버에서 실행 중 예외 발생");
	}

	/**
	 * Ajax 요청에 대한 응답 3
	 */
	@ResponseBody
	@GetMapping("ajaxTest3")
	public String ajaxTest3() {
		String msg = "서버에서 보내는 메시지";
		log.debug("AjaxController의 ajaxTest3()에서 보냄: {}", msg);
		return msg;
	}

	/**
	 * Ajax 요청에 대한 응답 4
	 * 
	 * @param a 계산할 정수1
	 * @param b 계산할 정수2
	 * @return sum 계산 결과
	 */
	@ResponseBody
	@PostMapping("ajaxTest4")
	public int ajaxTest4(@RequestParam("num1") int a,
			@RequestParam("num2") int b) {
		log.debug("ajaxTest4 전달받은 값: num1={}, num2={}", a, b);
		int sum = a + b;
		return sum;
	}

	/**
	 * ResponseEntity : 응답객체(상태코드, 헤더, 바디)를 사용자가 직접 만들어서 제어하는 객체임.
	 * 
	 * @return
	 */
	// @ResponseBody : ResponseEntity가 있으면 안써줘도 됨.
	@PostMapping("ajaxTest5")
	public ResponseEntity<?> ajaxTest5(@RequestParam("num4") String a, @RequestParam("num5") String b) {
		// ResponseEntity<?> 안에 물음표는 int일지, string일지 json일지 등 entity 안에 들어갈 데이터가 뭐가 될지
		// 모른다는 의미임
		log.debug("ajaxTest5에서 전달받은 값 : num4={}, num5={}", a, b);

		try {
			int n1 = Integer.parseInt(a);
			int n2 = Integer.parseInt(b);
			int n3 = n1 / n2;

			return ResponseEntity.ok(n3); // 200 문제없음이라는 상태코드 + 결과값
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("점수가 아닙니다.");
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("0으로 나눌수 없습니다.");
		} catch (NullPointerException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("서버 오류");
		}
	}

	@PostMapping("ajaxTest6")
	public ResponseEntity<?> ajaxTest6(@RequestParam("num7") String a,
			@RequestParam("num8") String b,
			@RequestParam("op") String op) {
		try {
			int n1 = Integer.parseInt(a);
			int n2 = Integer.parseInt(b);
			int result = 0;
			switch (op) {
				case "+" -> result = n1 + n2;
				case "-" -> result = n1 - n2;
				case "/" -> result = n1 / n2;
				case "*" -> result = n1 * n2;
				default -> {
					return ResponseEntity.badRequest().body("연산자를 확인해주세요.");
				}
			}
			return ResponseEntity.ok(result);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("점수가 아닙니다.");
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("0으로 나눌수 없습니다.");
		} catch (NullPointerException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("서버 오류");
		}
	}

}
