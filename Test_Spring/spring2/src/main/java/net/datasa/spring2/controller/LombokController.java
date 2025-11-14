package net.datasa.spring2.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.spring2.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j  // 롬복 불러오기
@Controller
public class LombokController {
	@GetMapping("lom/logger")
	public String logger(){
		log.error("error 출력");
		log.warn("warn 출력");
		log.info("info 출력");
		log.debug("debug 출력");
		log.trace("trace 출력");	// 출력 레벨을 debug 까지만 설정했기 때문에 출력되지 않음.
		
		String str = "문자열";
		log.debug("값 확인 로그 : {}", str);
		
		return "redirect:/";  // 경로값이 / 로 바뀜 (url을 초기화할 때 사용)
	}
	
	@GetMapping("lom/lombok")
	public String lombok(){
		Person p = new Person();
		p.setId("aaa");
		p.setName("홍길동");
		// getter, setter를 만들지 않았음에도 위에 set이 됨.
		System.out.println(	p.getId()		);
		System.out.println(	p.getPassword()	);
		System.out.println( p				);
		
		Person p2 = new Person("abc", "123", "홍길동", "010-1111-1111", "SKT");
		
		System.out.println(p2);
		
		Person p3 = Person.builder()
				.id("bbb")
				.name("조조조")
				.password("322")
				.phone("010-0000-0000")
				.com("KT")
				.build();
		System.out.println(p3);
		
		return "redirect:/";  // 홈컨트롤러의 home()으로 매핑이됨.
	}
}
