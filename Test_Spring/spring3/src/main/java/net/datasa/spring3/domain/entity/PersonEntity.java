package net.datasa.spring3.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// JPA에서 DB 테이블과 매핑되는 자바 클래스를 정의하기 위해 사용하는 Annotation
@Entity
@Data
// Entity가 매핑될 테이블명을 지정(생략하면 클래스 이름이 테이블 이름으로 사용됨)
@Table(name="person")
public class PersonEntity {
	
	// Entity 클래스의 PK값임.
	@Id  // PK
	@Column(name = "id", nullable = false, length = 30)  // 실제 테이블의 컬럼명, 제약조건 NN, Varchar(30)
	private String id;  // 여기 id는 자바에서 쓸 변수명임. 만약에 column 어노테이션을 안쓸거면 테이블 컬럼명이랑 변수명이랑 일치해야됨.
	
	@Column(name="name", length = 50)
	private String name;
	
	@Column(name="age")
	private Integer age;
	
	// 이제 properties에 spring.jpa.hibernate.ddl-auto=validate 이 설정이 DB와 entity를 비교해서 같은지 아닌지 검증을함.
	// 다르면 에러가 뜸.
}
