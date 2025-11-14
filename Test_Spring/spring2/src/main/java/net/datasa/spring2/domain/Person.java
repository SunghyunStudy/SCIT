package net.datasa.spring2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
	Lombok
	자바 어플리케이션 개발에서 중복코드를 줄여주는 라이브러리
	Annotation 기반으로 코드를 줄여주고 가독성을 높여줌.
	
		@Data
			= @Getter, @Setter, @ToString, @EqualsAndHashCode
		@NoArgsConstructor	: 매개변수가 없는 기본 생성자
		@AllArgsConstructor	: 모든 필드를 매개변수로 가지는 생성자
*/
@Builder
@Data					//
@AllArgsConstructor		//
@NoArgsConstructor		//
public class Person {
		String id;
		String password;
		String name;
		String phone;
		String com;
		
//		public Person(){}

//		public Person(String id, String password, String name, String phone, String com){
//			this.id = id;
//			this.password = password;
//			this.name = name;
//			this.phone = phone;
//			this.com = com;
//		}
		
		

}
