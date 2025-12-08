package net.datasa.spring3.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary	// 여러구현체중(Impl1, Impl2 ... ) 기본으로 사용.
@Service("impl1")	// 여기 안에 component 어노테이션이 있음 = 스프링에서 자동으로 객체를 생성하고 관리해줌.
@Slf4j
public class TestServiceImpl1 implements TestService {
	@Override
	public void testLog() {
		log.debug("1번 서비스 로직 실행");
	}
}
