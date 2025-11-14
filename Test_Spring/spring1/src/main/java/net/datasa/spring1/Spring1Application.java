package net.datasa.spring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 아래 어노테이션은 동일하거나 하위에 존재하는 패키지를 스캔함.
// 컨트롤러보다 얘보다 상위에 있으면 안됨.
@SpringBootApplication
public class Spring1Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring1Application.class, args);
	}

}
