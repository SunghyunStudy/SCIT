package net.datasa.spring3.controller;

import lombok.extern.slf4j.Slf4j;
import net.datasa.spring3.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
@Slf4j
public class HomeController {
	//	TestService ts = new TestService(); ==> 이거 없어도 됨
	// 스프링이 객체로 등록된 클래스들을 자동으로 주입
	@Autowired
	@Qualifier("impl2")	// 이거로 직접 어떤 구현체를 쓸건지 선택 가능. + properties에서 어떤 구현체를 선택할지 변수로 설정해서  넣을수도 있음
	TestService ts;	// 타입은 TestService 인터페이스, ts는 impl의 객체임.
	// TestServiceImpl1 ts 로 선언하면 나중에 TestServiceImpl1를 TestServiceImpl2수정하게 될 때 타입명을 바꿔줘야함.
	
	@GetMapping({"","/"})
	public String home() {
		ts.testLog();
		return "home";
	}
	
	
	/*
		Optional<T>
			- null 값으로 인한 NullPointerException(NPE)를 방지하기위한 클래스
				"값이 있을 수도, 없을 수도 있음"을 명시적으로 표현해주는 클래스
			- null을 직접 사용하는 것보다 안정적이고 가독성이 높음.
	 */
	@GetMapping("optional")
	public String optional(){
		
		// 1. null을 그대로 사용하는 경우
		// null 체크를 직접 해줘야 하며, 체크하지 않을 시 NPE 발생
		String name = null;
		if (name != null){
			log.debug("이름 : {}", name.toUpperCase());
		}else{log.debug("이름이 없습니다.");}
		
		// 2. Optional을 사용하는 경우
		String name2 = null;
		Optional<String> opt = Optional.ofNullable(name2);
		String result = opt.orElse("이름이 없습니다.");
		log.debug("결과 : {}", result);
		opt.ifPresent(res -> log.debug("결과 : {}", res)); // null이 아닌 경우에만 사용되어지는 메서드..
		
		// 3. JPA 사용 예시
		Map<String, String> database = new HashMap<>();
		database.put("hong", "gildong");
		database.put("kim", "cheolsoo");
		
		// 존재하는 키 조회
		Optional<String> data1 = Optional.ofNullable(database.get("hong"));
		log.debug("데이터가 존재할 경우 : {}", data1.orElse("데이터 없음"));
		
		// 존재하지 않는 키 조회
		Optional<String> data2 = Optional.ofNullable(database.get("lee"));
		log.debug("데이터가 존재하지 않는 경우 : {}", data2.orElse("데이터 없음"));
		
		// 값이 없으면 예외 던지기
		try{
			String data3 = Optional.ofNullable(database.get("kim"))
					.orElseThrow( () -> new RuntimeException("데이터 없음"));
			log.debug("orElseThrow 패턴: {}", data3);
			String data4 = Optional.ofNullable(database.get("lee"))
					// new Supplier는 익명 클래스임. 아래 방식을 간단하게 하는게 data3의 람다식임.
					.orElseThrow(new Supplier<RuntimeException>() {
						@Override
						public RuntimeException get(){
							return new RuntimeException("데이터 없음");
						}
					});
		}catch(Exception e){
			log.debug("[예외 발생] {}", e.getMessage());
		}
		
		
		return "redirect:/";
	}
}
